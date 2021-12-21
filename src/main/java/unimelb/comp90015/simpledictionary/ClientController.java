package unimelb.comp90015.simpledictionary;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ClientController {
    @FXML
    private TextField searchBox;

    @FXML
    private Button searchButton;

    @FXML
    private TextArea resultBox;

    @FXML
    protected void onSearchButtonClick() {
        resultBox.setText("Welcome to JavaFX Application!");
    }

    public void setData(String string) {
        resultBox.setText(string);
    }
}