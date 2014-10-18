package test.replyrequestserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ThreadReplyRequestServer implements Runnable{

    public static final int SLEEP_TIME = 1000;
    int serverPort;
    long threadId;
    boolean isRunning = true;
    ServerSocket listener;
    List<ClientThread> clientList;


    public ThreadReplyRequestServer(int serverPort) throws IOException {
        this.serverPort = serverPort;
        threadId = Thread.currentThread().getId();
        listener = new ServerSocket(this.serverPort);
        clientList = new ArrayList<ClientThread>();
    }

    @Override
    public void run() {
        System.out.println( threadId + " Start server thread");

        while ( isRunning ) {

            try {
                Socket socket = listener.accept();

                ClientThread clientThread = new ClientThread(socket);
                clientList.add(clientThread);
                clientThread.start();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        for ( ClientThread client : clientList){
            client.stopThreadClient();
            try {
                client.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try {
            listener.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(threadId + " Stopped server thread");
    }

    public void stopThreadSever(){
        isRunning = false;
        System.out.println(threadId + " Stop received");
    }
}
