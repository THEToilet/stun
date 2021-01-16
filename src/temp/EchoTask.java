package temp;

import java.io.IOException;
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
        System.out.printf("%s [%s] :Accepted!\n", new Date(), Thread.currentThread().getName());
    }

    @Override
    public void run() {
        String addrPort = IPAddress.getHostAddress().toString() + "-" + String.valueOf(port);
        try {
            Thread.sleep(1000);
            datagramSocket.send(new DatagramPacket(addrPort.getBytes(),
                    addrPort.getBytes().length, IPAddress, port));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.printf("%s [%s] :Process finished.\n", new Date(), Thread.currentThread().getName());
        System.out.printf("%s [%s] :%s closed.\n", new Date(), Thread.currentThread().getName(), datagramSocket.toString());
    }
}
