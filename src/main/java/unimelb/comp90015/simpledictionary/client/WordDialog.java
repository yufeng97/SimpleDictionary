package unimelb.comp90015.simpledictionary.client;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import unimelb.comp90015.simpledictionary.util.ClientSocket;

import java.io.IOException;
import java.net.URL;

public class WordDialog {

    private Scene scene;
    private Stage stage;

    public WordDialog(ClientSocket client, WordDialogType type) throws IOException {
        URL resource;
        if (type == WordDialogType.ADD) {
            resource = ClientController.class.getResource("add-view.fxml");
        } else if (type == WordDialogType.UPDATE) {
            resource = ClientController.class.getResource("update-view.fxml");
        } else if (type == WordDialogType.REMOVE) {
            resource = ClientController.class.getResource("remove-view.fxml");
        } else {
            resource = ClientController.class.getResource("add-view.fxml");
        }
        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        Parent parent = fxmlLoader.load();
        AbstractController dialogController = fxmlLoader.<AddWordController>getController();
        dialogController.setClient(client);
        scene = new Scene(parent, 300, 250);
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
    }

    public void showAndWait() {
        stage.showAndWait();
    }
}
