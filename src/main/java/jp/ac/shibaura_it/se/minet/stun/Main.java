package jp.ac.shibaura_it.se.minet.stun;

import java.io.IOException;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import net.logstash.logback.argument.StructuredArguments;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    static final int PORT_NUMBER = 55554;
    static final int MAX_THREADS = 100;

    public static void main(String[] args) throws IOException {
        Logger logger = LoggerFactory.getLogger(Main.class);

        ExecutorService executor = Executors.newFixedThreadPool(MAX_THREADS);

        DatagramSocket datagramSocket = new DatagramSocket(PORT_NUMBER);
        logger.debug("Server is running.", StructuredArguments.keyValue("PORT", PORT_NUMBER));
        while (true) {
            DatagramPacket receivePacket = new DatagramPacket(new byte[1024], 1024);
            try {
                datagramSocket.receive(receivePacket);
                InetAddress IPAddress = receivePacket.getAddress();
                int port = receivePacket.getPort();
                logger.debug("PACKET-SIZE", StructuredArguments.keyValue("size", receivePacket.getLength()), StructuredArguments.keyValue("rawPacket", receivePacket));
                executor.submit(new EchoTask(datagramSocket, IPAddress, port, logger));
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
