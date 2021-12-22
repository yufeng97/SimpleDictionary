package unimelb.comp90015.simpledictionary;

import javafx.scene.control.Alert;
import javafx.scene.control.Dialog;

public class Utils {

    public static void showErrorMsg(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(msg);
        alert.showAndWait();
    }
}
