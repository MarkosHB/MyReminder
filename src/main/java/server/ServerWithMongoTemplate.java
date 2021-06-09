package server;

import com.google.gson.Gson;
import com.mongodb.*;
import com.mongodb.client.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.model.Filters;

import org.bson.*;
import org.bson.conversions.Bson;

//import org.slf4j.Logger;

//import org.slf4j.LoggerFactory;

import utils.Event;
import utils.SocketUtils;
import utils.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentSkipListMap;

public class ServerWithMongoTemplate extends MultiThreadServer implements Runnable {

    private String name;
    private MongoCollection<Document> users;
    private MongoCollection<Document> events;

    private ConcurrentSkipListMap<String, ObjectOutputStream> socketWriter;

    public ServerWithMongoTemplate(String name) {

        super(8080);
        this.name = name;

        db = Database.getInstance(); // Esto sustituye a users y mails
        db.addUser(new User("admin@uma.es", "admin", "1234", "0", true));
        socketWriter = new ConcurrentSkipListMap<>();

        ConnectionString connString = new ConnectionString(
                "mongodb+srv://software:MyReminder@cluster0.cb2w0.mongodb.net/Software?w=majority");
        MongoClientSettings settings = MongoClientSettings.builder().applyConnectionString(connString).retryWrites(true)
                .build();
        MongoClient mongoClient = MongoClients.create(settings);
        MongoDatabase database = mongoClient.getDatabase("Software");
        users = database.getCollection("Users");
        users = database.getCollection("Events");

        // Caching process

        // Document prueba = users.find(new Document("_id", "pblprz")).first();
        // System.out.println(prueba);
    }

    @Override
    public void handleConnection(Socket connection) throws IOException {

        // BufferedReader input = SocketUtils.getReader(connection);
        // PrintWriter output = SocketUtils.getWriter(connection);
        ObjectOutputStream output = SocketUtils.getWriter(connection);
        ObjectInputStream input = SocketUtils.getReader(connection);

        /*
         * // EJEMPLO DE COMO ENVIAR OBJETOS OutputStream outputStream =
         * socket.getOutputStream(); ObjectOutputStream objectOutputStream = new
         * ObjectOutputStream(outputStream);
         * objectOutputStream.writeObject(List<Message> messages);
         * 
         * // EJEMPLO DE COMO RECIBIR OBJETOS InputStream inputStream =
         * socket.getInputStream(); ObjectInputStream objectInputStream = new
         * ObjectInputStream(inputStream); List<Message> listOfMessages =
         * (List<Message>) objectInputStream.readObject();
         */

        System.out.println("Connection Local Port: " + connection.getLocalPort());
        System.out.println("Connection Remote Port: " + connection.getPort());
        System.out.println("Host: " + connection.getInetAddress().getHostName());

        String line;
        // String[] words;
        User user;
        Event event;

        /*
         * // Leemos mensaje de presentación del cliente line = input.readLine(); words
         * = line.split(" "); System.out.println("Number: " + words[0] + " -> " +
         * "Name: " + words[1] + "\n");
         * 
         * // Guardamos PrintWriter para el cliente socketWriter.put(words[0], output);
         */

        try {
            while ((line = (String) input.readObject()) != null) {
                if (!line.equalsIgnoreCase("")) {
                    switch (line.toUpperCase()) {
                        case "SIGN UP":
                            user = (User) input.readObject();
                            signUp(user, output);
                            break;
                        case "SIGN IN":
                            user = (User) input.readObject();
                            signIn(user, output);
                            break;
                        case "CREATE EVENT":
                            event = (Event) input.readObject();
                            createEvent(event, output);
                            break;
                        case "INVITATION":
                            event = (Event) input.readObject();
                            sendInvitation(event, output);
                            break;
                        case "ANSWER INVITATION":
                            event = (Event) input.readObject();
                            answerInvitation(event, output);
                            break;
                        case "FORGOTTEN PASSWORD":
                            throw new Error("How to recover password?");
                        // User aux;
                        // user = (User) input.readObject();
                        // String name;
                        // // if (mails.containsKey(user.getMail())) {
                        // if (db.containsMail(user.getMail())) {
                        // // name = mails.get(user.getMail());
                        // name = db.getUserName(user.getMail());
                        // // aux = users.get(name);
                        // aux = db.getUser(name);
                        // if (user.getDni().equals(aux.getDni())) {
                        // aux.setPassword(user.getPassword());
                        // }
                        // }
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
    // -------------------------------- FUNCIONES CREADAS
    // --------------------------------
    // -----------------------------------------------------------------------------------

    private void signUp(User user, ObjectOutputStream output) throws IOException {
        // Compruebo que el mail y el nombre de usuario utilizado no existan
        Document existingUser = users
                .find(Filters.or(Filters.eq("_id", user.getMail()), Filters.eq("name", user.getName()))).first();
        final Gson gson = new Gson();
        final User userInstance = gson.fromJson(existingUser.toJson(), User.class);
        if (userInstance != null) {
            output.writeObject("Sign up: Error. User already exists");
        } else {
            // _id = email
            Document userDocument = Document.parse(gson.toJson(user));
            userDocument.remove("email");
            userDocument.append("_id", user.getMail());
            // Guardar eventos como Array asi la db sabe que hay un campo events tipo array
            Event events[] = {};
            userDocument.append("events", events);
            users.insertOne(userDocument);
            output.writeObject("Sign up: OK");
        }
    }

    private void signIn(User user, ObjectOutputStream output) throws IOException {
        Document existingUser = users.find(Filters.eq("name", user.getName())).first();
        final Gson gson = new Gson();
        final User userInstance = gson.fromJson(existingUser.toJson(), User.class);
        if (userInstance == null) {
            output.writeObject("Sign in: Error. User doesn't exist");
        } else {
            if (user.correctPassword(userInstance.getPassword())) {
                socketWriter.put(user.getName(), output);
                output.writeObject("Sign in: OK");
                output.writeObject(userInstance);
            } else {
                output.writeObject("Sign in: Error. Incorrect password");
            }
        }
    }

    private void createEvent(Event event, ObjectOutputStream output) throws IOException {
        // Introduzco el evento
        // User user = users.get(event.getOwner());
        Document existingUser = users.find(Filters.eq("_id", event.getOwner())).first();
        final Gson gson = new Gson();
        final User userInstance = gson.fromJson(existingUser.toJson(), User.class);
        userInstance.putEvent(event);

        events.insertOne(Document.parse(gson.toJson(event)));

        output.writeObject("Create event: OK");
        output.writeObject(event);
        // Compruebo los invitados, actualizo sus eventos y les envío un mensaje si
        // están activos
        for (String guestName : event.getGuests().keySet()) {
            Document guestDocument = users.find(Filters.eq("name", guestName)).first();
            final User guestInstance = gson.fromJson(guestDocument.toJson(), User.class);
            if (guestInstance != null) {
                // user = users.get(guestName);
                guestInstance.putEvent(event);
                if (socketWriter.containsKey(guestName)) {
                    // Se puede enviar el evento y que el cliente lo actualice
                    socketWriter.get(guestName).writeObject("Invitation");
                    socketWriter.get(guestName).writeObject(event);
                    /*
                     * // Tambien se puede enviar el usuario completo actualizado y el cliente
                     * simplemente lo copia socketWriter.get(name).writeObject("Invitation");
                     * socketWriter.get(name).writeObject(user);
                     */
                }
            }
        }
    }

    private void sendInvitation(Event event, ObjectOutputStream output) throws IOException {
        // Comparo los nuevos invitados con los que había anteriormente
        // Event eventAux = users.get(event.getOwner()).getEvent(event.getId());
        Document existingUser = users.find(Filters.eq("_id", event.getOwner())).first();
        final Gson gson = new Gson();
        final User userInstance = gson.fromJson(existingUser.toJson(), User.class);
        Event eventAux = userInstance.getEvent(event.getId());
        for (String guestName : event.getGuests().keySet()) {
            // Si hay algun invitado nuevo le envio un mensaje si está activo
            if (!eventAux.containsGuest(guestName)) {
                if (socketWriter.containsKey(guestName)) {
                    // Se puede enviar el evento y que el cliente lo actualice
                    socketWriter.get(guestName).writeObject("Invitation");
                    socketWriter.get(guestName).writeObject(event);
                    /*
                     * // Tambien se puede enviar el usuario completo actualizado y el cliente
                     * simplemente lo copia socketWriter.get(name).writeObject("Invitation");
                     * socketWriter.get(name).writeObject(user);
                     */
                }
            }
        }
        // Actualizo los invitados
        eventAux.setGuests(event.getGuests());
    }

    private void answerInvitation(Event event, ObjectOutputStream output) throws IOException {
        // Actualizo el evento del propietario y de los invitados
        // users.get(event.getOwner()).putEvent(event);
        Document existingUser = users.find(Filters.eq("_id", event.getOwner())).first();
        final Gson gson = new Gson();
        final User userInstance = gson.fromJson(existingUser.toJson(), User.class);
        userInstance.putEvent(event);
        for (String guestName : event.getGuests().keySet()) {
            Document guestDocument = users.find(Filters.eq("name", guestName)).first();
            final User guestInstance = gson.fromJson(guestDocument.toJson(), User.class);
            if (guestInstance != null) {
                // user = users.get(guestName);
                guestInstance.putEvent(event);
                // Si hay algun invitado activo le envio un mensaje
                if (socketWriter.containsKey(guestName)) {
                    // Se puede enviar el evento y que el cliente lo actualice
                    socketWriter.get(guestName).writeObject("Invitation");
                    socketWriter.get(guestName).writeObject(event);
                    /*
                     * // Tambien se puede enviar el usuario completo actualizado y el cliente
                     * simplemente lo copia socketWriter.get(name).writeObject("Invitation");
                     * socketWriter.get(name).writeObject(user);
                     */
                }
            }
        }
    }

    // -----------------------------------------------------------------------------------
    // --------------------------- GETTERS, SETTERS AND CLEARS
    // ---------------------------
    // -----------------------------------------------------------------------------------

}
