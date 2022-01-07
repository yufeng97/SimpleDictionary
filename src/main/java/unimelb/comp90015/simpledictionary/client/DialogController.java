package unimelb.comp90015.simpledictionary.client;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class DialogController extends AbstractController {

    @FXML
    protected Button cancelBtn;

    @FXML
    protected void onCancelBtnClick() {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }
}
