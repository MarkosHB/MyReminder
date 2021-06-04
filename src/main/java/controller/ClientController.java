package controller;

import client.Client;
import frame.ClientFrame;
import utils.Event;
import utils.User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.text.ParseException;

/**
 * Clase para el controlador del cliente En ella crearemos el controlador y las
 * respectivas funciones asociadas a este Es decir, se hará el tratamiento de
 * las pulsaciones de los botones de la ventana
 */
public class ClientController implements ActionListener {

    private ClientFrame frame;
    private Client client;

    private int cont = 0;

    // Socket para el servidor, a través de él podemos enviarle mensajes
    // private PrintWriter output;
    // private ObjectOutputStream output;

    // Constructor
    // public ClientController(ClientFrame clientFrameV3, Client client) {
    // this.frame = clientFrameV3;
    // this.client = client;
    // // this.output = client.getWriter();
    // }

    public ClientController(ClientFrame clientFrame, Client client) {
        this.frame = clientFrame;
        this.client = client;
    }

    /**
     * En esta función se hará el tratamiento de los botones creados en ClientFrame.
     * Es decir, si se pulsa un botón se enviará un comando que coincidirá con el
     * nombre asociado a dicho botón (ej.: JButton("Enviar")) a no ser que se
     * indique lo contrario (ej.: setActionCommand("Enviar"))
     *
     * @param e evento que se recibe, es decir, botón pulsado
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        User user;
        Event event;
        String command;

        switch (command = e.getActionCommand().toUpperCase()) {
            case "SIGN UP PANEL":
                frame.showSignUp();
                System.out.println("Sign up panel");
                break;
            case "GO BACK":
                frame.showSignIn();
                System.out.println("Go back");
                break;
            case "SIGN UP":
                user = new User(frame.getMailTextSignUp(), frame.getUserTextSignUp(), frame.getPasswordTextSignUp(),
                        frame.getDniTextSignUp());
                if (user.isCorrect()) {
                    client.signUp(user);
                    System.out.println("Sign up: " + user);
                } else {
                    System.out.println("Sign up: USER ISN'T CORRECT");
                }
                break;
            case "SIGN IN":
                user = new User(frame.getUserTextSignIn(), frame.getPasswordTextSignIn());
                System.out.println("Sign in: " + user);
                client.signIn(user);
                break;
            case "CREATE EVENT":
                frame.showCreateEventPanel();
                System.out.println("Create event panel");
                break;
            case "CONFIRM CREATE EVENT":
                try {
                    event = new Event(client.getUser().getName() + cont++, frame.getCreateEventTitle(),
                            frame.getCreateEventDate(), frame.getCreateEventDescription(), client.getUser().getName());
                    System.out.println("Create event: " + event);
                    client.createEvent(event);
                } catch (ParseException ex) {
                    System.out.println("Create event: Error. Date format incorrect");
                }
                break;
            case "CANCEL CREATE EVENT":
                frame.showMessagesPanel();
                System.out.println("Cancel create event");
                break;
            case "MESSAGES":
                frame.showMessagesPanel();
                System.out.println("Messages panel");
                break;
            case "CHANGE VIEW":
                frame.changeView();
                break;
            case "LOG OUT":
                System.out.println("Log out: " + client.getUser());
                client.setUser(null);
                frame.showSignIn();
                break;
            default:
                System.out.println(command);
                if (command.contains("MORE INFO")) {
                    String id = command.split(": ")[1];
                    System.out.println("More info: Event " + client.getUser().getEvent(id).getTitle());
                    client.getUser().getEvent(id);
                    frame.showEventDetails();
                } else if (command.contains("DELETE")) {
                    String id = command.split(": ")[1];
                    System.out.println("Delete: Event " + client.getUser().getEvent(id).getTitle());
                    client.getUser().removeEvent(id);
                    // Hay que informar al servidor
                    frame.showEvents();
                } else {
                    throw new RuntimeException("Unknown command!");
                }
        }

    }

    // -----------------------------------------------------------------------------------
    // -------------------------------- FUNCIONES CREADAS --------------------------------
    // -----------------------------------------------------------------------------------

}
