package unimelb.comp90015.simpledictionary.server;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import unimelb.comp90015.simpledictionary.ClientSocket;
import unimelb.comp90015.simpledictionary.dictionary.Dictionary;
import unimelb.comp90015.simpledictionary.dictionary.WordNotFoundException;

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

                // parse request
                String response = handleRequest(request);
                client.send(response);
            }
            client.close();
        } catch (IOException e) {
            System.out.println("IOException " + e.getMessage());
        }
    }

    private String handleRequest(String request) {
        System.out.println(request);

        JSONParser parser = new JSONParser();
        JSONObject requestJson = null;
        try {
            requestJson = (JSONObject) parser.parse(request);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String response = null;
        if (requestJson != null) {
            String action = (String) requestJson.get("type");

            switch (action) {
                case "query":
                    String word = (String) requestJson.get("data");
                    try {
                        response = dictionary.query(word);
                    } catch (WordNotFoundException e) {
                        System.out.println("didn't find the word: " + word);
                        response = "the word is not in the dictionary";
                    }
                    break;
                case "add":
                    break;
                case "update":
                    break;
                case "remove":
                    break;
                default:
                    System.out.println("Invalid action type");
                    break;
            }
        }
        return response;
    }
}
