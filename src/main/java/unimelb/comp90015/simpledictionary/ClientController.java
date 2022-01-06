package unimelb.comp90015.simpledictionary;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.json.JSONObject;
import unimelb.comp90015.simpledictionary.client.AddWordController;
import unimelb.comp90015.simpledictionary.client.WordDialog;

import java.io.IOException;
import java.net.SocketException;
import java.net.URL;
import java.util.ResourceBundle;

public class ClientController implements Initializable {
    @FXML
    private TextField searchBox;

    @FXML
    private TextArea resultBox;

    private ClientSocket client;

    private WordDialog addWordDialog;

    @FXML
    protected void onSearchBtnClick() {
        String word = searchBox.getText();
        System.out.println(word);
        if (!checkWord(word)) {
            return;
        }

        int retryTimes = 0;
        boolean doReconnect = false;
        boolean successConnect = false;
        while (retryTimes++ < 3) {
            // reconnect when client could not connect to server
            if (doReconnect) {
                try {
                    client.reconnect();
                } catch (IOException ex) {
                    System.out.println("Try to connect to " + client.getRemoteHost() + " the " + retryTimes + " times");
                }
            }
            try {
                client.send(makeQueryRequest(word));
            } catch (IOException e) {
                doReconnect = true;
                continue;
            }
            successConnect = true;
            break;
        }
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
//        URL resource = getClass().getResource("add-view.fxml");
//        System.out.println(resource);
//
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("add-view.fxml"));
//        Parent parent = fxmlLoader.load();
//        AddWordController dialogController = fxmlLoader.<AddWordController>getController();
//
//        Scene scene = new Scene(parent, 300, 200);
//        Stage stage = new Stage();
//        stage.initModality(Modality.APPLICATION_MODAL);
//        stage.setScene(scene);
//        stage.showAndWait();
        if (addWordDialog == null) {
            addWordDialog = new WordDialog();
        }
        addWordDialog.showAndWait();


    }

    @FXML
    protected void onUpdateBtnClick() {}

    @FXML
    protected void onDeleteBtnClick() {}

    public void setClient(ClientSocket socket) {
        client = socket;
    }

    private boolean checkWord(String word) {
        if (word.trim().isEmpty()) {
            Utils.showWarning("word could not be empty");
            return false;
        }
        return true;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}