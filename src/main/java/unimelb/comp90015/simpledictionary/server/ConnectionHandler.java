package unimelb.comp90015.simpledictionary.server;

import org.json.JSONException;
import org.json.JSONObject;
import unimelb.comp90015.simpledictionary.dictionary.Dictionary;
import unimelb.comp90015.simpledictionary.util.ClientSocket;

import java.io.IOException;
import java.net.SocketException;

public class ConnectionHandler implements Runnable {

    private final ClientSocket client;
    private final Dictionary dictionary;

    public ConnectionHandler(ClientSocket client, Dictionary dictionary) {
        this.client = client;
        this.dictionary = dictionary;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String request;
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
                String response;
                try {
                    response = handleRequest(request);
                } catch (JSONException e) {
                    System.out.println("Invalid message format");
                    break;
                }
                client.send(response);
            }
            client.close();
        } catch (IOException e) {
            System.out.println("IOException " + e.getMessage());
        }
    }

    private String handleRequest(String request) {
        System.out.println("handle request: " + request + " from " + client.getRemoteHost());

        JSONObject requestJson = new JSONObject(request);
        String content = "";
        String action = requestJson.optString("type");
        String word = requestJson.optString("word");
        String description = requestJson.optString("description");

        JSONObject responseJson = new JSONObject();
        boolean success = false;
        switch (action) {
            case "query":
                content = dictionary.query(word);
                if (content.isEmpty()) {
                    System.out.println("didn't find the word: " + word);
                    content = "the word is not in the dictionary";
                } else {
                    success = true;
                }
                break;
            case "remove":
                success = dictionary.remove(word);
                if (!success) {
                    System.out.println("didn't find the word: " + word);
                    content = "the word is not in the dictionary";
                }
                break;
            case "add":
                success = dictionary.add(word, description);
                if (!success) {
                    System.out.println("The word " + word + " has been added to dictionary");
                    content = "the word is already in the dictionary";
                }
                break;
            case "update":
                success = dictionary.update(word, description);
                if (!success) {
                    System.out.println("didn't find the word: " + word);
                    content = "the word is not in the dictionary";
                }
                break;
            default:
                System.out.println("Invalid action type");
                content = "Invalid action type";
                break;
        }
        responseJson.put("success", success);
        responseJson.put("content", content);
        System.out.println(responseJson);
        return responseJson.toString();
    }
}
