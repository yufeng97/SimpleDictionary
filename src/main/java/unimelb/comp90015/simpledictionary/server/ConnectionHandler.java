package unimelb.comp90015.simpledictionary.server;

import unimelb.comp90015.simpledictionary.ClientSocket;
import unimelb.comp90015.simpledictionary.dictionary.Dictionary;

import java.io.IOException;
import java.net.SocketException;

public class ConnectionHandler implements Runnable {

    private ClientSocket client;
    private Dictionary dictionary;

    public ConnectionHandler(ClientSocket client, Dictionary dictionary) {
        this.client = client;
        this.dictionary = dictionary;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String request = null;
                try {
                    request = client.receive();
                } catch (SocketException e) {
                    System.out.println("client disconnect");
                    break;
                } catch (IOException e) {
                    System.out.println("disconnect by " + e.getMessage());
                    break;
                }

            }
            client.close();
        } catch (IOException e) {
            System.out.println("IOException " + e.getMessage());
        }
    }

    private void parse(String request) {

    }
}
