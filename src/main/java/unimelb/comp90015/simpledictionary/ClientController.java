package unimelb.comp90015.simpledictionary;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.json.JSONObject;

import java.io.IOException;

public class ClientController {
    @FXML
    private TextField searchBox;

    @FXML
    private TextArea resultBox;

    private ClientSocket client;

    @FXML
    protected void onSearchBtnClick() {
        String word = searchBox.getText();
        System.out.println(word);
        try {
            client.send(makeQueryRequest(word));
        } catch (IOException e) {
            System.out.println("IOException " + e.getMessage() + " when send request");
        }
        try {
            String response = client.receive();
            System.out.println("client receive message: " + response);
            JSONObject responseJson = new JSONObject(response);
            resultBox.setText(responseJson.optString("content"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onAddBtnClick() {

    }

    @FXML
    protected void onUpdateBtnClick() {}

    @FXML
    protected void onDeleteBtnClick() {}

    public void setClient(ClientSocket socket) {
        client = socket;
    }

    private String makeRequest(String action, String word) {
        JSONObject object = new JSONObject();
        object.put("type", action);
        object.put("word", word);
        return object.toString();
    }

    private String makeRequest(String action, String word, String description) {
        JSONObject object = new JSONObject();
        object.put("type", action);
        object.put("word", word);
        object.put("description", description);
        return object.toString();
    }

    private String makeQueryRequest(String word) {
        return makeRequest("query", word);
    }

    private String makeAddRequest(String word, String description) {
        return makeRequest("add", word, description);
    }

    private String makeRemoveRequest(String word) {
        return makeRequest("remove", word);
    }

    private String makeUpdateRequest(String word, String description) {
        return makeRequest("update", word, description);
    }
}