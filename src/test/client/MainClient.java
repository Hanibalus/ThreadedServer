package test.client;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainClient {
    public static void main(String[] args){

        int  noThreads = 100;
        int iter = 1000;

        ExecutorService threadPool =
                Executors.newFixedThreadPool(noThreads);

        for(int i=0;i<noThreads;i++) {
            ClientTest clientTest = new ClientTest(iter);
            threadPool.execute(clientTest);
        }
        threadPool.shutdown();


    }
}
