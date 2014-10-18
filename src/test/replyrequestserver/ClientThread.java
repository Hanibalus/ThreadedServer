package test.replyrequestserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

public class ClientThread extends Thread {
    private Socket socket;
    private long threadId;
    private boolean isRunning = true;

    public ClientThread(Socket socket) {
        this.socket = socket;
        threadId = Thread.currentThread().getId();
        System.out.println(threadId + " Connection received from : " + socket);
    }

    public void run() {

        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            while (isRunning) {
                String input = in.readLine();
                if ( input == null ) {
                    System.out.println("Client closed connection : " + socket);
                    break;
                }
                out.println("BACK " + input);
            }

        } catch (IOException e) {
            System.out.println("Exception catch");
            e.printStackTrace();
        }

        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void stopThreadClient(){
        isRunning = false;

        System.out.println(threadId + " Stop received");
    }
}
