package client;

import frame.ClientFrame;
import frame.ClientPCFrame;
import utils.*;
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

        output = SocketUtils.getWriter(client);
        input = SocketUtils.getReader(client);

        new Thread(new Alarm(this, frame)).start();

        try {
            while ((line = (String) input.readObject()) != null) {
                if (!line.equalsIgnoreCase("")) {
                    switch (line.toUpperCase()) {
                        case "SIGN UP: OK":
                            frame.showSignIn();
                            System.out.println("Sign up: OK");
                            break;
                        case "SIGN IN: OK":
                            user = new User((User) input.readUnshared());
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
                            event = new Event((Event) input.readUnshared());
                            user.putEvent(event);
                            user.addMessage("UPDATE: " + event.getTitle());
                            System.out.println(user);
                            frame.showEvents();
                            frame.showMessages();
                            System.out.println("Guest updating event");
                            break;
                        case "GET USERS: OK":
                            users = new ConcurrentSkipListMap<>((ConcurrentSkipListMap) input.readUnshared());
                            frame.showAdminFrame();
                            System.out.println("Receive users: OK -- " + users);
                            break;
                        case "CREATE EVENT: OK":
                            event = new Event((Event) input.readObject());
                            user.putEvent(event);
                            frame.showMessages();
                            frame.showEvents();
                            System.out.println("Create event: OK -- " + event);
                            break;
                        case "CHECK CONTACT: OK":
                            user.addContact(((String) input.readObject()));
                            System.out.println("Check contact: OK");
                            frame.showMessages();
                            //frame.showEvents();
                            break;
                        case "INVITATION":
                            System.out.println("Invitation");
                            event = new Event((Event) input.readUnshared());
                            user.putEvent(event);
                            frame.showEvents();
                            user.addMessage("INVITATION: " + event.getTitle());
                            frame.showMessages();
                            System.out.println(event);
                            //user = (User) input.readUnshared();
                            //user.putEvent(event);
                            break;
                        case "DELETE YOURSELF":
                            user = null;
                            frame.showSignIn();
                            break;
                        case "FORGOTTEN PASSWORD: OK":
                            user = new User((User) input.readUnshared());
                            frame.showMainPanel();
                            frame.showEvents();
                            frame.showMessages();
                            System.out.println("Forgotten password: OK -- " + user);
                            break;
                        default:
                            System.out.println(line);
                            //throw new RuntimeException("Unknown command!");
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
            output.writeUnshared(new User(user));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void signIn(User user) {
        try {
            output.writeObject("Sign in");
            output.writeUnshared(new User(user));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createEvent(Event event) {
        try {
            output.writeObject("Create event");
            output.writeUnshared(new Event(event));
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
        user.removeContact(contact);
    }

    public void addContact(String contact) {
        try {
            if (!user.getContacts().contains(contact)) {
                output.writeObject("Check Contact");
                output.writeUnshared(new User(user));
                output.writeObject(contact);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void inviteGuests(Event event) {
        try {
            output.writeObject("Invitation");
            output.writeUnshared(new Event(event));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteEvent(Event event) {
        try {
            output.writeObject("Delete event");
            output.writeUnshared(new Event(event));
            user.removeEvent(event.getId());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateEvent(Event event) {
        try {
            output.writeObject("Update event");
            System.out.println("Sending from " + user.getName() + ": " + event);
            output.writeUnshared(new Event(event));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void forgottenPassword(User user) {
        try {
            output.writeObject("Forgotten password");
            output.writeUnshared(new User(user));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logOut() {
        try {
            System.out.println("Log out: " + user);
            output.writeObject("Log out");
            output.writeUnshared(new User(user));
            user = null;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // -----------------------------------------------------------------------------------
    // --------------------------- GETTERS, SETTERS AND CLEARS ---------------------------
    // -----------------------------------------------------------------------------------

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

    public String getName() {
        return name;
    }

}
