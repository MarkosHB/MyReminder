package client;

import frame.ClientFrame;
import utils.Event;
import utils.SocketUtils;
import utils.User;

import java.io.*;
import java.net.Socket;

import controller.ClientController;

/**
 * Clase para el cliente En ella crearemos el cliente y las respectivas
 * funciones asociadas a este Más concretamente, se realizará la comunicación
 * con el servidor y se tratarán los mensajes recibidos por este
 */
public class Client extends NetworkClient implements Runnable {

    private final int posX;
    private final int posY;
    private ClientFrame frame;

    /**
     * EJEMPLO DE VARIABLES PARA WHATSAPP: private final Long number; private String
     * name; private ConcurrentSkipListMap<Long, String> contacts; private
     * ConcurrentLinkedDeque<Chat> chats; private Chat chosenChat;
     */
    private String name;
    private User user;

    // Socket -> Read / Write
    // private BufferedReader input;
    // private PrintWriter output;
    private ObjectInputStream input;
    private ObjectOutputStream output;

    // Contacts starts empty
    public Client(String name, int posX, int posY) {

        super("localhost", 8080);

        /**
         * EJEMPLO DE CONTRUCTOR PARA WHATSAPP: this.number = number; this.name = name;
         * contacts = new ConcurrentSkipListMap<>(); chats = new
         * ConcurrentLinkedDeque<>(); chosenChat = null;
         */
        this.name = name;
        this.user = null;

        this.posX = posX;
        this.posY = posY;
        frame = null;

        input = null;
        output = null;

    }

    @Override
    protected void handleConnection(Socket client) throws IOException {

        String line;
        Event event;

        // Number and name are send to server (ejemplo)
        output = SocketUtils.getWriter(client);
        input = SocketUtils.getReader(client);
        // output.println(number + " " + name);

        // PRUEBA (Parece que va guay, cambiar los input.readLine() por
        // input.readObject())
        // signUp(new User("my email", name, "my password", "my dni"));

        try {
            while ((line = (String) input.readObject()) != null) {
                if (!line.equalsIgnoreCase("")) {
                    switch (line.toUpperCase()) {
                        case "SIGN UP: OK":
                            frame.showSignIn();
                            System.out.println("Sign up: OK");
                            break;
                        case "SIGN IN: OK":
                            user = (User) input.readObject();
                            frame.showMainPanel();
                            System.out.println("Sign in: OK -- " + user);
                            break;
                        case "CREATE EVENT: OK":
                            event = (Event) input.readObject();
                            user.putEvent(event);
                            frame.showMessagesPanel();
                            frame.showEvents();
                            System.out.println("Create event: OK -- " + event);
                            break;
                        case "INVITATION":
                            System.out.println("Invitation");
                            event = (Event) input.readObject();
                            user.putEvent(event);
                            System.out.println(event);
                            break;
                        default:
                            System.out.println(line);
                            // throw new RuntimeException("Unknown command!");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        // COMENTADO SOLO PARA PRUEBAS
        frame = new ClientFrame(name, posX, posY, this);
        connect();
    }

    // -----------------------------------------------------------------------------------
    // -------------------------------- FUNCIONES CREADAS --------------------------------
    // -----------------------------------------------------------------------------------

    public void signUp(User user) {
        try {
            this.user = user;
            output.writeObject("Sign up");
            output.writeObject(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void signIn(User user) {
        try {
            output.writeObject("Sign in");
            output.writeObject(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createEvent(Event event) {
        try {
            output.writeObject("Create event");
            output.writeObject(event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // -----------------------------------------------------------------------------------
    // --------------------------- GETTERS, SETTERS AND CLEARS ---------------------------
    // -----------------------------------------------------------------------------------

    /*
     * public PrintWriter getWriter() { return output; }
     */

    public ObjectOutputStream getWriter() {
        return output;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // public ClientController getController() { //Usado en tests
    // return this.frame.getContainer();
    // }

}
