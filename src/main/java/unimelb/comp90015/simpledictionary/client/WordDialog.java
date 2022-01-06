package unimelb.comp90015.simpledictionary.client;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import unimelb.comp90015.simpledictionary.ClientController;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class WordDialog {

    private Scene scene;
    private Stage stage;

    public WordDialog() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ClientController.class.getResource("add-view.fxml"));
        Parent parent = fxmlLoader.load();
        AddWordController dialogController = fxmlLoader.<AddWordController>getController();

//        Scene scene = new Scene(parent, 300, 200);
//        Stage stage = new Stage();
        scene = new Scene(parent, 300, 200);
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
    }

    public void showAndWait() {
        stage.showAndWait();
    }
}