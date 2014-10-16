package test.multithreaded;

public class Multithreaded {

    public static void main(String[] args) {
        MultithreadedServer server = new MultithreadedServer(9000);
        new Thread(server).start();

        try {
            Thread.sleep(20 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Stopping Server");
        server.stop();
    }
}
