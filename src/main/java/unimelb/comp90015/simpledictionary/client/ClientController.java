package unimelb.comp90015.simpledictionary.client;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.json.JSONObject;
import unimelb.comp90015.simpledictionary.util.Error;
import unimelb.comp90015.simpledictionary.util.Utils;

import java.io.IOException;
import java.net.SocketException;
import java.net.URL;
import java.util.ResourceBundle;

public class ClientController extends AbstractController implements Initializable {
    @FXML
    private TextField searchBox;

    @FXML
    private TextArea resultBox;

    private WordDialog addWordDialog;
    private WordDialog updateWordDialog;
    private WordDialog removeWordDialog;

    @FXML
    protected void onSearchBtnClick() {
        String word = searchBox.getText();
        System.out.println(word);
        if (!checkWord(word)) {
            return;
        }

        boolean successConnect = client.connectAndSend(makeQueryRequest(word));
        // fail to connect, then return
        if (!successConnect) {
            Utils.showError(Error.ERROR_CONNECTION);
            return;
        }

        try {
            String response = client.receive();
            System.out.println("client receive message: " + response);
            JSONObject responseJson = new JSONObject(response);
            resultBox.setText(responseJson.optString("content"));
        } catch (SocketException e) {
            System.out.println(client);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onAddBtnClick() throws IOException {
        if (addWordDialog == null) {
            addWordDialog = new WordDialog(client, WordDialogType.ADD);
        }
        addWordDialog.showAndWait();
    }

    @FXML
    protected void onUpdateBtnClick() throws IOException {
        if (updateWordDialog == null) {
            updateWordDialog = new WordDialog(client, WordDialogType.UPDATE);
        }
        updateWordDialog.showAndWait();
    }

    @FXML
    protected void onRemoveBtnClick() throws IOException {
        if (removeWordDialog == null) {
            removeWordDialog = new WordDialog(client, WordDialogType.REMOVE);
        }
        removeWordDialog.showAndWait();
    }

    private String makeQueryRequest(String word) {
        return makeRequest("query", word);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}