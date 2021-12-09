package stun;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Date;

public class EchoTask implements Runnable {
    private DatagramSocket datagramSocket;
    private InetAddress IPAddress;
    private int port;

    public EchoTask(DatagramSocket datagramSocket, InetAddress IPAddress, int port) {
        this.datagramSocket = datagramSocket;
        this.IPAddress = IPAddress;
        this.port = port;
    }

    @Override
    public void run() {
        String adderPort = "{ type : STUN, ip : " + IPAddress.getHostAddress().toString() + ", port : " + port + "}";
        System.out.printf("[%s] %s : Accepted!\n", new Date(), adderPort);
        try {
            datagramSocket.send(new DatagramPacket(adderPort.getBytes(),
                    adderPort.getBytes().length, IPAddress, port));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.printf("[%s] %s : Task succeeded.\n", new Date(), adderPort);
    }
}
