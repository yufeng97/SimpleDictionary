package unimelb.comp90015.simpledictionary;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientSocket {

    private InputStream is;
    private OutputStream os;
    private Socket socket;

    public ClientSocket(String host, int port) throws IOException {
        this.socket = new Socket(host, port);
        System.out.println("Connect to server " + host + ":" + port);
        is = socket.getInputStream();
        os = socket.getOutputStream();
    }

    public ClientSocket(Socket socket) throws IOException {
        this.socket = socket;
        is = socket.getInputStream();
        os = socket.getOutputStream();
    }
}
