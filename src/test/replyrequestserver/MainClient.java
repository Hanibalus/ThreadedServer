package test.replyrequestserver;

public class MainClient {
    public static void main(String[] args){
        ClientTest clientTest = new ClientTest();
        new Thread(clientTest).start();
    }
}
