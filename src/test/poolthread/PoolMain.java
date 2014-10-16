package test.poolthread;

import static java.lang.Thread.sleep;

public class PoolMain {

        public static void main(String[] args) throws InterruptedException {
            ThreadPooledServer server = new ThreadPooledServer(9000);
            new Thread(server).start();

            /*
            try {
                Thread.sleep(20 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            */

            while(true){
                sleep(1000);
            }
            //System.out.println("Stopping Server");
            //server.stop();
        }
}
