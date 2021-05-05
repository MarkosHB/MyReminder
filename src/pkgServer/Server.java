package pkgServer;

import pkgUtils.SocketUtils;
import pkgUtils.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.ConcurrentSkipListMap;

public class Server extends MultiThreadServer implements Runnable {

    // El id sirve para identificar grupos con mismo nombre y mismos componentes
    private String name;
    private ConcurrentSkipListMap<String, User> users;

    // Socket -> Write (One per user)
    private ConcurrentSkipListMap<Long, PrintWriter> socketWriter;

    // Constructor
    public Server(String name) {

        super(8080);
        this.name = name;
        users = new ConcurrentSkipListMap<>();
        socketWriter = new ConcurrentSkipListMap<>();

    }

    @Override
    public void handleConnection(Socket connection) throws IOException {

        BufferedReader input = SocketUtils.getReader(connection);
        PrintWriter output = SocketUtils.getWriter(connection);

        System.out.println("Connection Local Port: " + connection.getLocalPort());
        System.out.println("Connection Remote Port: " + connection.getPort());
        System.out.println("Host: " + connection.getInetAddress().getHostName());

        String line;
        Long number;
        String[] words;

        // Lee número y nombre del usuario conectado
        line = input.readLine();
        words = line.split(" ");
        System.out.println("Number: " + words[0] + " -> " + "Name: " + words[1] + "\n");

        // Guardamos número del usuario del que leemos los datos
        number = Long.parseLong(words[0]);
        // users.put(number, words[1]);
        socketWriter.put(Long.parseLong(words[0]), output);

        try {
            while ((line = input.readLine()) != null) {
                if (!line.equalsIgnoreCase("")) {
                    switch (line.toUpperCase()) {
                        case "CREAR USUARIO":
                            line = input.readLine();
                            System.out.println("Server -> Creating new user");
                            break;
                        case "CREAR CHAT":
                            line = input.readLine();
                            System.out.println("Server -> Creating chat");
                            output = socketWriter.get(Long.parseLong(line));
                            output.println("CREAR CHAT");
                            output.println(number);
                            break;
                        case "CREAR CONTACTO":
                            line = input.readLine();
                            output = socketWriter.get(number);
                            System.out.println("Server -> Querying database");
                            output.println("NUEVO CONTACTO");
                            if (users.containsKey(line)) {
                                output.println("OK");
                                output.println(users.get(line));
                            } else {
                                output.println("NOT FOUND");
                            }
                            break;
                        case "CAMBIAR INFORMACIÓN":
                            line = input.readLine();
                            // users.put(number, line);
                            System.out.println("Server -> Information has changed correctly\n");
                            break;
                        default:
                            throw new RuntimeException("Unknown command!");
                    }
                }
            }
        } catch (SocketException ignore) { }

        // Nunca se cerrará la conexión en principio
        connection.close();

    }

    @Override
    public void run() {
        listen();
    }

    // -----------------------------------------------------------------------------------
    // -------------------------------- FUNCIONES CREADAS --------------------------------
    // -----------------------------------------------------------------------------------



    // -----------------------------------------------------------------------------------
    // --------------------------- GETTERS, SETTERS AND CLEARS ---------------------------
    // -----------------------------------------------------------------------------------



}
