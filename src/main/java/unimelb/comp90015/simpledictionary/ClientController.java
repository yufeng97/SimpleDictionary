package unimelb.comp90015.simpledictionary;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;

public class ClientController {
    @FXML
    private TextField searchBox;

    @FXML
    private Button searchBtn;

    @FXML
    private TextArea resultBox;

    private ClientSocket client;

    @FXML
    protected void onSearchButtonClick() {
        String text = searchBox.getText();
        System.out.println(text);
        try {
            client.sendRequest("test message");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setClient(ClientSocket socket) {
        client = socket;
    }
}