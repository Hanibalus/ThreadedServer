package test.replyrequestserver;

import java.io.IOException;

public class ReplyRequestSimpleServer {

    public static final int SERVER_PORT = 9000;

    public static void main(String[] args) throws InterruptedException, IOException {

        final ThreadReplyRequestServer serverImpl = new ThreadReplyRequestServer(SERVER_PORT);
        Thread server = new Thread(serverImpl);

        Runtime.getRuntime().addShutdownHook( new Thread(){
            @Override
            public void run()
            {
                System.out.println("Shutting down");
                serverImpl.stopThreadSever();
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } );


        server.start();
        server.join();

    }
}
