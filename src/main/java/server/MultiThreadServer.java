package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/** Build a server on specified port.
 * The server will accept a connection and
 * then pass the connection to a connection handler,
 * then put the connection handler in a queue of tasks
 * to be executed in separate threads.
 * The class will continue to run until
 * the server is killed (e.g., Control-C in the startup window)
 * or System.exit() from elsewhere in the Java code).
 */
public abstract class MultiThreadServer implements IServer {
    private int port;

    public MultiThreadServer(int port) {
        this.port = port;
    }

    public int getPort() {
        return port;
    }

    /** Monitor a port for connections.
     * Each time one is established,
     * pass resulting Socket to connection handler,
     * which will execute it in background thread.
     */
    public void listen() {
        int poolsize = 10;  // * Runtime.getRuntime().availableProcessors();
        ExecutorService tasks = Executors.newFixedThreadPool(poolsize);
        try (ServerSocket listener = new ServerSocket(port)) {
            Socket socket;
            while (true) {  // Run until killed
                socket = listener.accept();
                // Class whose run handleConnection method
                tasks.execute(new ConnectionHandlerRunnable(socket, this));
            }
        } catch (IOException ioe) {
            System.err.println("IOException: " + ioe);
            ioe.printStackTrace();
        }
    }
    /** This is the method that provides the behavior to the server,
     *  since it determines what is done with the resulting socket.
     *  Override this method in servers you write.
     */
    public abstract void handleConnection(Socket connection)
            throws IOException;
}
