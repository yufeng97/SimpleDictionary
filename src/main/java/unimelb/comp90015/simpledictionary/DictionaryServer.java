package unimelb.comp90015.simpledictionary;

import unimelb.comp90015.simpledictionary.dictionary.DictionaryFactory;
import unimelb.comp90015.simpledictionary.dictionary.SimpleDictionary;

import javax.net.ServerSocketFactory;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;


public class DictionaryServer {
    private static int port;
    private static String dictionaryFilePath;

    public static void main(String[] args) {
        checkArgs(args);
        DictionaryFactory dictionaryFactory = DictionaryFactory.getInstance();
        SimpleDictionary simpleDictionary = dictionaryFactory.createSimpleDictionary(dictionaryFilePath);
        ServerSocketFactory socketFactory = ServerSocketFactory.getDefault();
        try {
            ServerSocket serverSocket = socketFactory.createServerSocket(port);
            System.out.println("Server Start with address: " + serverSocket.getLocalSocketAddress());
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Connected from: " + socket.getRemoteSocketAddress());

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void checkArgs(String[] args) {
        if (args.length < 2) {
            System.out.println("command line should be in format:\n" +
                    "java –jar DictionaryServer.jar <port> <dictionary-file>");
            System.exit(1);
        }
        System.out.println(Arrays.toString(args));
        try {
            port = Integer.parseInt(args[0]);

        } catch (NumberFormatException e) {
            System.out.println("Fail to parse port number");
        }
        dictionaryFilePath = args[1];
    }
}
