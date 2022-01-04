package unimelb.comp90015.simpledictionary;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


public class Utils {

    private static void showMessage(AlertType alertType, String code, String msg) {
        Alert alert = new Alert(alertType);
        alert.setTitle(code);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    public static void showError(String msg) {
        showMessage(AlertType.ERROR, "Error", msg);
    }

    public static void showError(Error error) {
        showMessage(AlertType.ERROR, error.getTitle(), error.getMessage());
    }

    public static void showWarning(String msg) {
        showMessage(AlertType.WARNING, "Warning", msg);
    }
}
