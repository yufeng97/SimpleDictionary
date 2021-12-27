package unimelb.comp90015.simpledictionary;

import javafx.scene.control.Alert;

public class Utils {

    private static void showErrorMsg(String code, String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(code);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    public static void showErrorMsg(String msg) {
        showErrorMsg("Error", msg);
    }

    public static void showErrorMsg(Error error) {
        showErrorMsg(error.getTitle(), error.getMessage());
    }
}
