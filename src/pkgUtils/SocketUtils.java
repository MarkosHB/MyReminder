package pkgUtils;

import java.io.*;
import java.net.Socket;

public class SocketUtils {

    /*
    public static BufferedReader getReader(Socket s) throws IOException {
        return(new BufferedReader(
                new InputStreamReader(s.getInputStream())));
    }

    public static PrintWriter getWriter(Socket s) throws IOException {
        // Second argument of true means autoflush
        return(new PrintWriter(s.getOutputStream(), true));
    }
    */

    public static ObjectInputStream getReader(Socket s) throws IOException {
        return(new ObjectInputStream(s.getInputStream()));
    }

    public static ObjectOutputStream getWriter(Socket s) throws IOException {
        return(new ObjectOutputStream(s.getOutputStream()));
    }

    private SocketUtils() {} // Uninstantiatable class

}
