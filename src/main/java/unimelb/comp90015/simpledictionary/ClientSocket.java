package unimelb.comp90015.simpledictionary;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientSocket {

    private DataInputStream is;
    private DataOutputStream os;
    private Socket socket;

    /**
     * Socket for client
     * @param host
     * @param port
     * @throws IOException
     */
    public ClientSocket(String host, int port) throws IOException {
        this.socket = new Socket(host, port);
        System.out.println("Connect to server " + host + ":" + port);
        is = new DataInputStream(socket.getInputStream());
        os = new DataOutputStream(socket.getOutputStream());
    }

    /**
     * Socket for server
     * @param socket
     * @throws IOException
     */
    public ClientSocket(Socket socket) throws IOException {
        this.socket = socket;
        is = new DataInputStream(socket.getInputStream());
        os = new DataOutputStream(socket.getOutputStream());
    }

    public void receive() throws IOException {
        String requeset = is.readUTF();
        System.out.println(requeset);
    }

    public void sendRequest(String request) throws IOException {
        os.writeUTF(request);
    }

    public void sendSearchRequest(String word) throws IOException {
        String request = "search:" + word;
        sendRequest(request);
    }
}
