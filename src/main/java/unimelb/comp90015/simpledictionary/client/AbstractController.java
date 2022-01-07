package unimelb.comp90015.simpledictionary.client;

import org.json.JSONObject;
import unimelb.comp90015.simpledictionary.util.ClientSocket;
import unimelb.comp90015.simpledictionary.util.Utils;

public abstract class AbstractController {

    protected ClientSocket client;

    protected String makeRequest(String action, String word) {
        JSONObject object = new JSONObject();
        object.put("type", action);
        object.put("word", word);
        return object.toString();
    }

    protected String makeRequest(String action, String word, String description) {
        JSONObject object = new JSONObject();
        object.put("type", action);
        object.put("word", word);
        object.put("description", description);
        return object.toString();
    }

    protected boolean checkWord(String word) {
        if (word.trim().isEmpty()) {
            Utils.showWarning("word could not be empty");
            return false;
        }
        return true;
    }

    protected boolean checkInput(String word, String definition) {
        if (word.trim().isEmpty()) {
            Utils.showWarning("word could not be empty");
            return false;
        }
        if (definition.trim().isEmpty()) {
            Utils.showWarning("definition could not be empty");
            return false;
        }
        return true;
    }

    public void setClient(ClientSocket client) {
        this.client = client;
    }
}
