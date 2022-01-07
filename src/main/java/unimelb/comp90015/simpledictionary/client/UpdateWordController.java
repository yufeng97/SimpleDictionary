package unimelb.comp90015.simpledictionary.client;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.json.JSONObject;
import unimelb.comp90015.simpledictionary.util.Error;
import unimelb.comp90015.simpledictionary.util.Utils;

import java.io.IOException;
import java.net.SocketException;

public class UpdateWordController extends DialogController {

    @FXML
    TextField updateWordText;

    @FXML
    TextArea updateWordDefinition;

    @FXML
    protected void onUpdateBtnClick() {
        String word = updateWordText.getText();
        String definition = updateWordDefinition.getText();
        if (!checkInput(word, definition)) {
            return;
        }
        boolean successConnect = client.connectAndSend(makeUpdateRequest(word, definition));
        // fail to connect, then return
        if (!successConnect) {
            Utils.showError(Error.ERROR_CONNECTION);
            return;
        }

        try {
            String response = client.receive();
            System.out.println("client receive message: " + response);
            JSONObject responseJson = new JSONObject(response);
            boolean success = responseJson.optBoolean("success");
            if (success) {
                Utils.showInfo("Successfully update a word");
                updateWordText.clear();
                updateWordDefinition.clear();
            } else {
                Utils.showError(responseJson.optString("content"));
            }
        } catch (SocketException e) {
            System.out.println(client);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String makeUpdateRequest(String word, String definition) {
        return makeRequest("update", word, definition);
    }
}
