package unimelb.comp90015.simpledictionary;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.ConnectException;
import java.util.List;
import java.util.Map;

public class DictionaryClient extends Application {
    private static String serverHost;
    private static int port;
    private static ClientSocket client;

    public static void main(String[] args) {
        checkArgs(args);
        launch(args);
    }

    private static void checkArgs(String[] args) {
        if (args.length < 2) {
            System.out.println("command line should be in format:\n" +
            "java –jar DictionaryClient.jar <server-address> <server-port>");
            System.exit(1);
        }
        serverHost = args[0];
        try {
            port = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.out.println("Fail to parse port number");
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parameters p = this.getParameters();
        Map<String, String> namedParams = p.getNamed();
        List<String> unnamedParams = p.getUnnamed();
        List<String> rawParams = p.getRaw();

        String paramStr = "Named Parameters: " + namedParams + "\n" +
                "Unnamed Parameters: " + unnamedParams + "\n" +
                "Raw Parameters: " + rawParams;
        System.out.println(paramStr);
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("client-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 615, 400);
        stage.setScene(scene);
        ClientController controller = fxmlLoader.<ClientController>getController();
        try {
            client = new ClientSocket(serverHost, port);
        } catch (ConnectException e) {
            System.out.println("Could not connect to server " + serverHost + ":" + port);
            Utils.showErrorMsg("Could not connect to server " + serverHost + ":" + port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        controller.setClient(client);
        stage.show();
    }
}
