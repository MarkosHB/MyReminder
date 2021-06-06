package client;

import frame.ClientFrame;
import frame.ClientPCFrame;
import utils.*;
/*
import utils.Event;
import utils.SocketUtils;
import utils.User;
*/
import java.io.*;
import java.net.Socket;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * Clase para el cliente
 * En ella crearemos el cliente y las respectivas
 * funciones asociadas a este Más concretamente, se realizará la comunicación
 * con el servidor y se tratarán los mensajes recibidos por este
 */
public class Client extends NetworkClient implements Runnable {

    private final int posX;
    private final int posY;
    private ClientFrame frame;

    private String name;
    private User user;
    private ConcurrentSkipListMap<String, User> users;

    // Socket -> Read / Write
    // private BufferedReader input;
    // private PrintWriter output;
    private ObjectInputStream input;
    private ObjectOutputStream output;

    // Contacts starts empty
    public Client(String name, int posX, int posY) {

        super("localhost", 8080);

        this.name = name;
        this.user = null;
        this.users = null;

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

        new Thread(new Alarm(this, frame)).start();

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
                            user = (User) input.readUnshared();
                            frame.showMainPanel();
                            frame.showEvents();
                            frame.showMessages();
                            System.out.println("Sign in: OK -- " + user);
                            break;
                        case "DELETE USER: OK":
                            users = (ConcurrentSkipListMap) input.readUnshared();
                            frame.showAdminFrame();
                            System.out.println("Delete user: OK");
                            break;
                        case "DELETE EVENT":
                            System.out.println(user);
                            event = new Event((Event) input.readUnshared());
                            user.addMessage("DELETE: " + event.getTitle());
                            user.removeEvent(event.getId());
                            System.out.println(user);
                            frame.showEvents();
                            frame.showMessages();
                            System.out.println("Guest deleting event");
                            break;
                        case "UPDATE EVENT":
                            System.out.println(user);
                            event = new Event((Event) input.readUnshared());
                            user.addMessage("UPDATE: " + event.getTitle());
                            user.putEvent(event);
                            System.out.println(user);
                            frame.showEvents();
                            frame.showMessages();
                            System.out.println("Guest updating event");
                            break;
                        case "GET USERS: OK":
                            users = (ConcurrentSkipListMap) input.readUnshared();
                            frame.showAdminFrame();
                            System.out.println("Receive users: OK -- " + users);
                            break;
                        case "CREATE EVENT: OK":
                            event = (Event) input.readObject();
                            user.putEvent(event);
                            frame.showMessages();
                            frame.showEvents();
                            System.out.println("Create event: OK -- " + event);
                            break;
                        case "CHECK CONTACT: OK":
                            user.addContact(((String) input.readObject()).toLowerCase());
                            frame.showListContacts();
                            System.out.println("Check contact: OK");
                            break;
                        case "INVITATION":
                            System.out.println("Invitation");
                            event = (Event) input.readUnshared();
                            user.putEvent(event);
                            frame.showEvents();
                            user.addMessage("INVITATION: " + event.getTitle());
                            frame.showMessages();
                            System.out.println(event);
                            //user = (User) input.readUnshared();
                            //user.putEvent(event);
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
        frame = new ClientPCFrame(name, posX, posY, this);
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

    public void receiveUsers() {
        try {
            output.writeObject("Get users");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(String name) {
        try {
            output.writeObject("Delete user");
            output.writeObject(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeContact(String contact) {
        user.removeContact(contact.toLowerCase());
    }

    public void addContact(String contact) {
        try {
            output.writeObject("Check Contact");
            output.writeObject(contact);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void inviteGuests(Event event) {
        try {
            output.writeObject("Invitation");
            output.writeUnshared(event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteEvent(Event event) {
        try {
            output.writeObject("Delete event");
            output.writeUnshared(event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateEvent(Event event) {
        try {
            output.writeObject("Update event");
            output.writeUnshared(event);
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

    public ConcurrentSkipListMap getUsers() {
        return users;
    }

    // public ClientController getController() { //Usado en tests
    // return this.frame.getContainer();
    // }

}
