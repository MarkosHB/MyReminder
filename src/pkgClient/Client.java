package pkgClient;

import pkgFrame.ClientFrame;
import pkgUtils.SocketUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Clase para el cliente
 * En ella crearemos el cliente y las respectivas funciones asociadas a este
 * Más concretamente, se realizará la comunicación con el servidor y se tratarán los mensajes recibidos por este
 */
public class Client extends NetworkClient implements Runnable {

    private final int posX;
    private final int posY;
    private ClientFrame frame;

    /**
     * EJEMPLO DE VARIABLES PARA WHATSAPP:
     * private final Long number;
     * private String name;
     * private ConcurrentSkipListMap<Long, String> contacts;
     * private ConcurrentLinkedDeque<Chat> chats;
     * private Chat chosenChat;
     */
    private String name;

    // Socket -> Read / Write
    private BufferedReader input;
    private PrintWriter output;

    // Contacts starts empty
    public Client(String name, int posX, int posY) {

        super("localhost", 8080);

        /** EJEMPLO DE CONTRUCTOR PARA WHATSAPP:
         * this.number = number;
         * this.name = name;
         * contacts = new ConcurrentSkipListMap<>();
         * chats = new ConcurrentLinkedDeque<>();
         * chosenChat = null;
         */
        this.name = name;

        this.posX = posX;
        this.posY = posY;
        frame = null;

        input = null;
        output = null;

    }

    @Override
    protected void handleConnection(Socket client) throws IOException {

        String line;

        // Number and name are send to server (ejemplo)
        input = SocketUtils.getReader(client);
        output = SocketUtils.getWriter(client);
        // output.println(number + " " + name);

        // IMPORTANTE! Usar este esqueleto para el proyecto
        try {
            while ((line = input.readLine()) != null) {
                if (!line.equalsIgnoreCase("")) {
                    switch (line.toUpperCase()) {
                        // EJEMPLO DE USO (los 'case' deben ser implementados por nosotros)
                        case "NUEVO CONTACTO":
                            line = input.readLine();
                            /*
                            if (line.equalsIgnoreCase("OK")) {
                                line = input.readLine();
                                System.out.println("Client: " + name + " -> New contact OK\n");
                                if (frame.getNameNewContact().equalsIgnoreCase("")) {
                                    addContact(frame.getNumberNewContact(), line);
                                } else {
                                    addContact(frame.getNumberNewContact(), frame.getNameNewContact());
                                }
                                synchronizeContacts();
                                synchronizeChats();
                            } else {
                                System.out.println("Client: " + name + " -> New contact NOT FOUND\n");
                            }
                            frame.clearNewContact();
                            */
                            break;
                        default:
                            throw new RuntimeException("Unknown command!");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        frame = new ClientFrame(name, posX, posY, this);
        connect();
    }

    // -----------------------------------------------------------------------------------
    // -------------------------------- FUNCIONES CREADAS --------------------------------
    // -----------------------------------------------------------------------------------



    // -----------------------------------------------------------------------------------
    // --------------------------- GETTERS, SETTERS AND CLEARS ---------------------------
    // -----------------------------------------------------------------------------------

    public PrintWriter getWriter() {
        return output;
    }

}
