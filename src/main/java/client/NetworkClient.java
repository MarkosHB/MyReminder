package client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public abstract class NetworkClient implements IClient {

    private String host;
    private int port;

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    /*
     * Register host and port.
     * Connection won't actually be established until call connect()
     */
    public NetworkClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    /*
     * The easy part. Connecting to server is almost always same
     * Call Socket constructor,
     * use try/catch blocks for UnknownHostException and IOException,
     * close Socket
     */
    public void connect() {
        try {
            Socket client = new Socket(host, port);
            handleConnection(client);
        } catch (UnknownHostException e) {
            System.out.println("Unknown host: " + host);
            e.printStackTrace();
        } catch (IOException ioe) {
            System.out.println("IOException: " + ioe);
            ioe.printStackTrace();
        }
    }

    /*
     * The method to override
     * when making a network client for a task
     */
    protected abstract void handleConnection(Socket client) throws IOException;

}
