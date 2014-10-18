package test.replyrequestserver;

import java.io.*;
import java.net.Socket;

public class ClientTest implements Runnable {

    public static final int SERVER_PORT = 9000;
    public static final String SERVER_IP = "127.0.0.1";
    public static final long ITERATIONS = 100000;
    InputStream input;
    OutputStream output ;


    @Override
    public void run() {
        String serverAddress = SERVER_IP;
        try {
            Socket socket = new Socket(serverAddress, SERVER_PORT);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

//            while( true ) {

                //System.out.println("Sending");


            long start = System.currentTimeMillis();
            for (int i = 0; i < ITERATIONS; i++) {
                out.println("test");
                in.readLine();
                if ( i%10000 == 0){
                    System.out.println(i);
                }
            }

            long end = System.currentTimeMillis();
            System.out.println( "Performance : " +  ( ITERATIONS / (end - start) *  1000  ) );

                //Thread.sleep(1000);
//            }

            socket.close();
        } catch (IOException e) {
            System.out.println("Herror");
            e.printStackTrace();
        }


    }
}
