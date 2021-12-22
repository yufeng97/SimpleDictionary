package unimelb.comp90015.simpledictionary;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

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
        resultBox.setText("Welcome to JavaFX Application!");
        Utils.showErrorMsg("test error");
    }

    public void setData(String string) {
        resultBox.setText(string);
    }

    public void setClient(ClientSocket socket) {
        client = socket;
    }
}