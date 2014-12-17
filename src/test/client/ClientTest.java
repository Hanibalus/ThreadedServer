package test.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientTest implements Runnable {

    public static final int BUFFER_SIZE = 1024;
    public static final int SERVER_PORT = 9000;
    public static final String SERVER_IP = "127.0.0.1";
    InputStream input;
    OutputStream output ;
    private int iterations;

    public ClientTest(int iter){
        this.iterations = iter;
    }

    @Override
    public void run() {
        String serverAddress = SERVER_IP;
        try {
            Socket socket = new Socket(serverAddress, SERVER_PORT);
            InputStream input  = socket.getInputStream();
            OutputStream output = socket.getOutputStream();

            long startDate = System.currentTimeMillis()/1000L;
            for(int i = 0 ; i < iterations ; i++){
                output.write(("1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890\n").getBytes());

                byte[] receivedBytes = new byte[BUFFER_SIZE];
                //System.out.println("Waiting read\n");
                int received = input.read(receivedBytes, 0, receivedBytes.length);
                //System.out.println(new String(receivedBytes, "UTF-8"));

            }

            long endDate = System.currentTimeMillis()/1000L;
            long rate = iterations/(endDate - startDate);

            System.out.println("Rate " + rate + "\n");

            Thread.sleep(3000);
            input.close();
            output.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
