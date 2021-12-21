package unimelb.comp90015.simpledictionary;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.Socket;

public class DictionaryClient extends Application {
    private static String serverAddress;
    private static int port;

    public static void main(String[] args) {
        checkArgs(args);
        launch();
        try {
            Socket socket = new Socket(serverAddress, port);
            System.out.println("Client " + socket.getLocalPort() + " connect to " + socket.getRemoteSocketAddress());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void checkArgs(String[] args) {
        if (args.length < 2) {
            System.out.println("");
            System.exit(1);
        }
        serverAddress = args[0];
        try {
            port = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.out.println("Fail to parse port number");
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("client-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 615, 400);
        stage.setScene(scene);
        stage.show();
    }
}
