package unimelb.comp90015.simpledictionary.util;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientSocket {

    private DataInputStream is;
    private DataOutputStream os;
    private Socket socket;
    private String host;
    private int port;

    /**
     * Socket for client
     * @param host
     * @param port
     * @throws IOException
     */
    public ClientSocket(String host, int port) throws IOException {
        this.socket = new Socket(host, port);
        this.port = port;
        this.host = host;
        System.out.println("Connect to server " + host + ":" + port);
        setStream(this.socket);
    }

    /**
     * Socket for server
     * @param socket
     * @throws IOException
     */
    public ClientSocket(Socket socket) throws IOException {
        this.socket = socket;
        setStream(this.socket);
    }

    public String receive() throws IOException {
        return is.readUTF();
    }

    public void send(String message) throws IOException {
        os.writeUTF(message);
    }

    public boolean connectAndSend(String message) {
        int retryTimes = 0;
        boolean doReconnect = false;
        boolean successConnect = false;
        while (retryTimes++ < 3) {
            // reconnect when client could not connect to server
            if (doReconnect) {
                try {
                    reconnect();
                } catch (IOException ex) {
                    System.out.println("Try to connect to " + getRemoteHost() + " the " + retryTimes + " times");
                }
            }
            try {
                send(message);
            } catch (IOException e) {
                doReconnect = true;
                continue;
            }
            successConnect = true;
            break;
        }
        return successConnect;
    }

    public String getRemoteHost() {
        return socket.getRemoteSocketAddress().toString();
    }

    public void close() throws IOException {
        socket.close();
        is.close();
        os.close();
    }

    public void reconnect() throws IOException {
        this.socket = new Socket(host, port);
        System.out.println("Connect to server " + host + ":" + port);
        setStream(this.socket);
    }

    private void setStream(Socket socket) throws IOException {
        is = new DataInputStream(socket.getInputStream());
        os = new DataOutputStream(socket.getOutputStream());
    }
}
