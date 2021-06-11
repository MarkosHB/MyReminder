package server;

import utils.Event;
import utils.SocketUtils;
import utils.User;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.ConcurrentSkipListMap;

public class Server extends MultiThreadServer implements Runnable {

    private String name;

    private Database db; // Esto sustituye a users y mails

    // Socket -> Write (One per user)
    private ConcurrentSkipListMap<String, ObjectOutputStream> socketWriter;

    // Constructor
    public Server(String name) {

        super(8080);
        this.name = name;
        socketWriter = new ConcurrentSkipListMap<>();

        db = Database.getInstance(); // Esto sustituye a users y mails
        db.addUser(new User("admin@uma.es", "admin", "1234", "0", true));
        db.addUser(new User("pablo@uma.es", "pablo", "1234", "1", false));
        db.addUser(new User("miguel@uma.es", "miguel", "1234", "2", false));
        db.addUser(new User("marcos@uma.es", "marcos", "1234", "3", false));

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        try {
            db.getUser("admin").putEvent(new Event("ADMIN_0", "Examen de SI",
                    formatter.parse("11/06/2021 09:30"), formatter.parse("11/06/2021 08:00"),
                    "Examen final de la asignatura Sistemas Inteligentes", "admin"));
            db.getUser("admin").putEvent(new Event("ADMIN_1", "Examen de Software",
                    formatter.parse("11/06/2021 23:55"), formatter.parse("11/06/2021 23:00"),
                    "Entrega del proyecto de Introduccion a la Ingenieria Software", "admin"));
            db.getUser("admin").putEvent(new Event("ADMIN_2", "Reunion grupal",
                    formatter.parse("09/06/2021 11:00"), formatter.parse("09/06/2021 10:30"),
                    "Reunion grupal para el proyecto de Software", "admin"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void handleConnection(Socket connection) throws IOException {

        ObjectOutputStream output = SocketUtils.getWriter(connection);
        ObjectInputStream input = SocketUtils.getReader(connection);

        System.out.println("Connection Local Port: " + connection.getLocalPort());
        System.out.println("Connection Remote Port: " + connection.getPort());
        System.out.println("Host: " + connection.getInetAddress().getHostName());

        String line;
        User user;
        Event event;

        try {
            while ((line = (String) input.readObject()) != null) {
                if (!line.equalsIgnoreCase("")) {
                    switch (line.toUpperCase()) {
                        case "SIGN UP":
                            user = new User((User) input.readObject());
                            signUp(user, output);
                            break;
                        case "SIGN IN":
                            user = new User((User) input.readObject());
                            signIn(user, output);
                            break;
                        case "LOG OUT":
                            user = new User((User) input.readObject());
                            System.out.println("Server log out: " + user);
                            db.removeUser(user);
                            db.addUser(user);
                            socketWriter.remove(user.getName());
                            break;
                        case "GET USERS":
                            sendUsers(output);
                            break;
                        case "CREATE EVENT":
                            event = new Event((Event) input.readObject());
                            createEvent(event, output);
                            break;
                        case "CHECK CONTACT":
                            user = new User((User) input.readUnshared());
                            String contact = (String) input.readObject();
                            if (db.containsUserName(contact)) {
                                db.getUser(user.getName()).addContact(contact);
                                output.writeObject("CHECK CONTACT: OK");
                                output.writeObject(contact);
                            }
                            break;
                        case "INVITATION":
                            event = new Event((Event) input.readUnshared());
                            sendInvitation(event);
                            break;
                            /*
                        case "ANSWER INVITATION":
                            event = (Event) input.readObject();
                            answerInvitation(event, output);
                            break;
                            */
                        case "DELETE USER":
                            System.out.println("Deleting user");
                            user = db.getUser(((String) input.readObject()));
                            for (Event e: user.getEvents().values()) {
                                deleteEvent(e);
                            }
                            if (socketWriter.containsKey(user.getName())) {
                                socketWriter.get(user.getName()).writeObject("Delete yourself");
                            }
                            db.removeUser(user);
                            output.writeObject("Delete user: OK");
                            output.writeUnshared(db.getUsers());
                            break;
                        case "DELETE EVENT":
                            System.out.println("Deleting event");
                            event = new Event((Event) input.readUnshared());
                            deleteEvent(event);
                            break;
                        case "UPDATE EVENT":
                            System.out.println("Updating event");
                            event = new Event((Event) input.readUnshared());
                            System.out.println("Received: " + event);
                            updateEvent(event);
                            break;
                        case "FORGOTTEN PASSWORD":
                            User aux;
                            user = new User((User) input.readObject());
                            String name;
                            // if (mails.containsKey(user.getMail())) {
                            if (db.containsMail(user.getMail())) {
                                // name = mails.get(user.getMail());
                                name = db.getUserName(user.getMail());
                                // aux = users.get(name);
                                aux = db.getUser(name);
                                if (user.getDni().equals(aux.getDni())) {
                                    aux.setPassword(user.getPassword());
                                    output.writeObject("Forgotten password: OK");
                                    output.writeUnshared(new User(aux));
                                    socketWriter.put(name, output);
                                }
                            }
                            break;
                        default:
                            throw new RuntimeException("Unknown command!");
                    }
                }
            }
        } catch (SocketException | ClassNotFoundException e) {
            e.printStackTrace();
        }

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

    private void signUp(User user, ObjectOutputStream output) throws IOException {
        // Compruebo que el mail y el nombre de usuario utilizado no existan
        // if (mails.containsKey(user.getMail()) || users.containsKey(user.getName())) {
        if (db.containsMail(user.getMail()) || db.containsUserName(user.getName())) {
            output.writeObject("Sign up: Error. User already exists");
        } else {
            // Introduzco el usuario y el mail
            // users.put(user.getName(), user);
            db.addUser(user);
            // mails.put(user.getMail(), user.getName());
            db.addMail(user.getMail(), user.getName());
            output.writeObject("Sign up: OK");
        }
    }

    private void signIn(User user, ObjectOutputStream output) throws IOException {
        // Compruebo que el nombre de usuario existe
        // if (!users.containsKey(user.getName())) {
        if (!db.containsUserName(user.getName())) {
            output.writeObject("Sign in: Error. User doesn't exist");
        } else {
            // User userAux = users.get(user.getName());
            User userAux = db.getUser(user.getName());
            // Compruebo que la contraseña coincide
            if (user.correctPassword(userAux.getPassword())) {
                // Introduzco el Writer y le envío la información completa del usuario
                socketWriter.put(user.getName(), output);
                output.writeObject("Sign in: OK");
                output.writeUnshared(new User(userAux));
            } else {
                output.writeObject("Sign in: Error. Incorrect password");
            }
        }
    }

    private void sendUsers(ObjectOutputStream output) throws IOException {
        System.out.println("Server sending users: " + db.getUsers());
        output.writeObject("Get users: OK");
        output.writeUnshared(new ConcurrentSkipListMap<>(db.getUsers()));
    }

    private void createEvent(Event event, ObjectOutputStream output) throws IOException {
        // Introduzco el evento
        // User user = users.get(event.getOwner());
        User user = db.getUser(event.getOwner());
        user.putEvent(event);
        output.writeObject("Create event: OK");
        output.writeUnshared(new Event(event));
        // Compruebo los invitados, actualizo sus eventos y les envío un mensaje si
        // están activos
        for (String guestName : event.getGuests().keySet()) {
            // if (users.containsKey(guestName)) {
            if (db.containsUserName(guestName)) {
                // user = users.get(guestName);
                user = db.getUser(guestName);
                user.putEvent(event);
                if (socketWriter.containsKey(guestName)) {
                    // Se puede enviar el evento y que el cliente lo actualice
                    socketWriter.get(guestName).writeObject("Invitation");
                    socketWriter.get(guestName).writeUnshared(new Event(event));
                }
            }
        }
    }

    private void sendInvitation(Event event) {
        // Comparo los nuevos invitados con los que había anteriormente
        // Event eventAux = users.get(event.getOwner()).getEvent(event.getId());
        Event eventAux = db.getUser(event.getOwner()).getEvent(event.getId());
        for (String guestName : event.getGuests().keySet()) {
            try {
                db.getUser(guestName).putEvent(event);
                db.getUser(guestName).addMessage("INVITATION: " + event.getTitle());
                // Si hay algun invitado nuevo le envio un mensaje si está activo
                if (!eventAux.containsGuest(guestName)) {
                    if (socketWriter.containsKey(guestName)) {
                        // Se puede enviar el evento y que el cliente lo actualice
                        socketWriter.get(guestName).writeObject("Invitation");
                        socketWriter.get(guestName).writeUnshared(new Event(event));
                        //socketWriter.get(guestName).writeUnshared(db.getUser(guestName));
                    }
                }
            } catch (Exception ignore) { }
        }
        // Actualizo los invitados
        eventAux.setGuests(event.getGuests());
    }

    public void deleteEvent(Event event) throws IOException {
        db.getUser(event.getOwner()).removeEvent(event.getId());
        System.out.println("Removing event: " + event);
        // Comparo los nuevos invitados con los que había anteriormente
        for (String guestName : event.getGuests().keySet()) {
            db.getUser(guestName).removeEvent(event.getId());
            // Si hay algun invitado le envio un mensaje si está activo
            if (socketWriter.containsKey(guestName)) {
                // Se puede enviar el evento y que el cliente lo actualice
                socketWriter.get(guestName).writeObject("Delete event");
                socketWriter.get(guestName).writeUnshared(new Event(event));
                //socketWriter.get(guestName).writeUnshared(db.getUser(guestName));
            }
        }
        // Actualizo los invitados
    }

    public void updateEvent(Event event) throws IOException {
        db.getUser(event.getOwner()).putEvent(event);
        System.out.println("Updating: " + event);
        for (String guestName : event.getGuests().keySet()) {
            System.out.println("Sending to: " + guestName);
            db.getUser(guestName).putEvent(event);
            db.getUser(guestName).addMessage("UPDATE: " + event.getTitle());
            // Si hay algun invitado le envio un mensaje si está activo
            if (socketWriter.containsKey(guestName)) {
                // Se puede enviar el evento y que el cliente lo actualice
                socketWriter.get(guestName).writeObject("Update event");
                socketWriter.get(guestName).writeUnshared(new Event(event));
                //socketWriter.get(guestName).writeUnshared(db.getUser(guestName));
            }
        }
        // Actualizo los invitados
    }

    /*
    private void answerInvitation(Event event, ObjectOutputStream output) throws IOException {
        // Actualizo el evento del propietario y de los invitados
        // users.get(event.getOwner()).putEvent(event);
        db.getUser(event.getOwner()).putEvent(event);
        for (String guestName : event.getGuests().keySet()) {
            // users.get(guestName).putEvent(event);
            db.getUser(guestName).putEvent(event);
            // Si hay algun invitado activo le envio un mensaje
            if (socketWriter.containsKey(guestName)) {
                // Se puede enviar el evento y que el cliente lo actualice
                socketWriter.get(guestName).writeObject("Invitation");
                socketWriter.get(guestName).writeObject(event);
            }
        }
    }
    */

    // -----------------------------------------------------------------------------------
    // --------------------------- GETTERS, SETTERS AND CLEARS ---------------------------
    // -----------------------------------------------------------------------------------

}
