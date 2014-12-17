package test.poolthread;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class WorkerRunnable implements Runnable {
    public static final int BUFFER_SIZE = 1024;
    protected Socket clientSocket = null;
    protected String serverText   = null;

    public WorkerRunnable(Socket clientSocket, String serverText) {
        this.clientSocket = clientSocket;
        this.serverText   = serverText;
    }

    public void run() {
        try {
            InputStream input  = clientSocket.getInputStream();
            OutputStream output = clientSocket.getOutputStream();

            while (true){


                byte[] receivedBytes = new byte[BUFFER_SIZE];
                //System.out.println("Waiting read\n");
                int received = input.read(receivedBytes, 0, receivedBytes.length);
                if ( received == -1 ) break;
                //System.out.println("Bytes received: " + received + "\n");
                //System.out.println(new String(receivedBytes, "UTF-8"));
                Thread.sleep(10);
                output.write("012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789".getBytes());
            }
            //long time = System.currentTimeMillis();
            //output.write(("HTTP/1.1 200 OK\n\nWorkerRunnable: " +
             //       this.serverText + " - " +
             //       time +
            //        "").getBytes());
            System.out.printf("Exiting\n");
            output.close();
            input.close();
            //System.out.println("Request processed: " + time);
        } catch (IOException e) {
            //report exception somewhere.
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
