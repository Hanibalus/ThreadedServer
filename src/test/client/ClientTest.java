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


    @Override
    public void run() {
        String serverAddress = SERVER_IP;
        try {
            Socket socket = new Socket(serverAddress, SERVER_PORT);
            InputStream input  = socket.getInputStream();
            OutputStream output = socket.getOutputStream();

            while(true){
                if ( input.available() > 0 ) {
                    byte[] receivedBytes = new byte[BUFFER_SIZE];
                    int received = input.read(receivedBytes, 0, receivedBytes.length);
                    System.out.println(new String(receivedBytes, "UTF-8"));
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
