package unimelb.comp90015.simpledictionary.client;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.json.JSONObject;
import unimelb.comp90015.simpledictionary.util.Error;
import unimelb.comp90015.simpledictionary.util.Utils;

import java.io.IOException;
import java.net.SocketException;

public class RemoveWordController extends DialogController {

    @FXML
    TextField removeWordText;

    @FXML
    protected void onRemoveBtnClick() {
        String word = removeWordText.getText();
        if (!checkWord(word)) {
            return;
        }
        boolean successConnect = client.connectAndSend(makeRemoveRequest(word));
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
                Utils.showInfo("Successfully remove a word");
                removeWordText.clear();
            } else {
                Utils.showError(responseJson.optString("content"));
            }
        } catch (SocketException e) {
            System.out.println(client);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String makeRemoveRequest(String word) {
        return makeRequest("remove", word);
    }
}
