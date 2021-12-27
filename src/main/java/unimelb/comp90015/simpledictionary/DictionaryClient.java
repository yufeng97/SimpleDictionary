package unimelb.comp90015.simpledictionary;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.ConnectException;
import java.util.List;

public class DictionaryClient extends Application {
    private static String serverHost;
    private static int port;
    private static ClientSocket client;

    public static void main(String[] args) {
        launch(args);
    }

    private void checkArgs(List<String> args) {
        if (args.size() < 2) {
            Utils.showErrorMsg(Error.INVALID_CLIENT_ARGUMENT);
            System.exit(1);
        }
        serverHost = args.get(0);
        try {
            port = Integer.parseInt(args.get(1));
        } catch (NumberFormatException e) {
            Utils.showErrorMsg(Error.INVALID_PORT);
            System.exit(1);
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parameters p = this.getParameters();
        List<String> unnamedParams = p.getUnnamed();
        checkArgs(unnamedParams);

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("client-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 615, 400);
        stage.setScene(scene);
        ClientController controller = fxmlLoader.<ClientController>getController();
        try {
            client = new ClientSocket(serverHost, port);
        } catch (ConnectException e) {
            Utils.showErrorMsg(Error.ERROR_CONNECTION);
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        controller.setClient(client);
        stage.show();
    }
}
