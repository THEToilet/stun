package temp;

import java.io.IOException;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    static final int PORT_NUMBER = 55554;
    static final int MAX_THREADS = 20;

    public static void main(String args[]) throws IOException {
        ExecutorService executor = Executors.newFixedThreadPool(MAX_THREADS);

        try (
                DatagramSocket datagramSocket = new DatagramSocket(PORT_NUMBER);
        ) {
            System.out.printf("Server running. port->%d\n", PORT_NUMBER);
            DatagramPacket receivePacket = new DatagramPacket(new byte[1024], 1024);
            while (true) {
                try {
                    datagramSocket.receive(receivePacket);
                    InetAddress IPAddress = receivePacket.getAddress();
                    int port = receivePacket.getPort();
                    executor.submit(new EchoTask(datagramSocket, IPAddress, port));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
