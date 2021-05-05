package pkgServer;

import java.io.IOException;
import java.net.Socket;

public class ConnectionHandlerRunnable implements Runnable {
    private Socket connection;
    private MultiThreadServer server;

    public ConnectionHandlerRunnable(Socket socket, MultiThreadServer server) {
        this.connection = socket;
        this.server = server;
    }

    @Override
    public void run() {
        try {
            server.handleConnection(connection);
        } catch (IOException ioe) {
            System.err.println("IOException: " + ioe);
            ioe.printStackTrace();
        }
    }
}