package jp.ac.shibaura_it.se.minet.stun;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Date;
import org.slf4j.Logger;

public class EchoTask implements Runnable {
    private DatagramSocket datagramSocket;
    private InetAddress IPAddress;
    private int port;
    private Logger logger;

    public EchoTask(DatagramSocket datagramSocket, InetAddress IPAddress, int port, Logger logger) {
        this.datagramSocket = datagramSocket;
        this.IPAddress = IPAddress;
        this.port = port;
        this.logger = logger;
    }

    @Override
    public void run() {
        String adderPort = "{ type : STUN, ip : " + IPAddress.getHostAddress().toString() + ", port : " + port + "}";
        this.logger.debug("Accepted!", adderPort, "----");
        try {
            datagramSocket.send(new DatagramPacket(adderPort.getBytes(),
                    adderPort.getBytes().length, IPAddress, port));
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.logger.debug("[%s] %s : Task succeeded. " + new Date().toString(), adderPort);
    }
}
