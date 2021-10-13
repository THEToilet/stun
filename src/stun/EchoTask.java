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
        String addrPort = IPAddress.getHostAddress().toString() + "-" + port;
        System.out.printf("[%s] %s : Accepted!\n", new Date(), addrPort);
        try {
            datagramSocket.send(new DatagramPacket(addrPort.getBytes(),
                    addrPort.getBytes().length, IPAddress, port));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.printf("[%s] %s : Task succeeded.\n", new Date(), addrPort);
    }
}
