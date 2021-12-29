package unimelb.comp90015.simpledictionary.server;

import unimelb.comp90015.simpledictionary.ClientSocket;
import unimelb.comp90015.simpledictionary.dictionary.Dictionary;

public class ConnectionHandler implements Runnable {

    private ClientSocket client;
    private Dictionary dictionary;

    public ConnectionHandler(ClientSocket client, Dictionary dictionary) {
        this.client = client;
        this.dictionary = dictionary;
    }

    @Override
    public void run() {
        System.out.println();
    }
}
