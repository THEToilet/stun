package jp.ac.shibaura_it.se.minet.stun;

import java.io.IOException;
import java.net.*;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    static final int PORT_NUMBER = 55554;
    static final int MAX_THREADS = 100;

    public static void main(String[] args) throws IOException {
        ExecutorService executor = Executors.newFixedThreadPool(MAX_THREADS);

        DatagramSocket datagramSocket = new DatagramSocket(PORT_NUMBER);
        System.out.printf("[%s] Server running. port->%d\n", new Date(),  PORT_NUMBER);
        while (true) {
            DatagramPacket receivePacket = new DatagramPacket(new byte[1024], 1024);
            try {
                datagramSocket.receive(receivePacket);
                InetAddress IPAddress = receivePacket.getAddress();
                int port = receivePacket.getPort();
                executor.submit(new EchoTask(datagramSocket, IPAddress, port));
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
