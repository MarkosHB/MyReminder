package controller;

import client.Client;
import frame.ClientFrame;
import utils.Event;
import utils.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;

/**
 * Clase para el controlador del cliente En ella crearemos el controlador y las
 * respectivas funciones asociadas a este Es decir, se hará el tratamiento de
 * las pulsaciones de los botones de la ventana
 */
public abstract class ClientController implements ActionListener {

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

        control(e);

    }

    // -----------------------------------------------------------------------------------
    // -------------------------------- FUNCIONES CREADAS --------------------------------
    // -----------------------------------------------------------------------------------

    private void control(ActionEvent e) {
        String command, line;

        switch (command = (line = e.getActionCommand()).toUpperCase()) {
            case "SIGN UP PANEL":
                showSignUp();
                break;
            case "GO BACK":
                goBack();
                break;
            case "SIGN UP":
                signUp();
                break;
            case "SIGN IN":
                signIn();
                break;
            case "CREATE EVENT":
                showCreateEvent();
                break;
            case "CONFIRM CREATE EVENT":
                createEvent();
                break;
            case "CANCEL CREATE EVENT":
                cancelCreateEvent();
                break;
            case "LIST CONTACTS":
                frame.showListContacts();
                break;
            case "ADD CONTACT":
                client.addContact(frame.getAddContactText());
                break;
            case "INVITE CONTACT":
                inviteContacts();
                frame.showMessages();
                break;
            case "INVITE USER":
                frame.showInvitationPanel();
                break;
            case "MESSAGES":
                showMessages();
                break;
            case "ADMIN":
                showAdmin();
                break;
            case "CANCEL UPDATE EVENT":
                showMessages();
                break;
            case "LOG OUT":
                logOut();
                break;
            case "FORGOTTEN PASSWORD":
                frame.showForgottenPassword();
                System.out.println("Forgotten password panel");
                break;
            case "CONFIRM FORGOTTEN PASSWORD":
                confirmForgottenPassword();
                break;
            case "GO BACK FORGOTTEN PASSWORD":
                frame.showSignIn();
                System.out.println("Go back forgotten password");
                break;
            case "GO BACK SING IN":
                frame.showSignIn();
                System.out.println("Go back sing in");
                break;
            default:
                System.out.println(command);
                if (command.contains("MORE INFO")) {
                    String id = line.split(": ")[1];
                    System.out.println("More info: Event " + client.getUser().getEvent(id).getTitle());
                    //client.getUser().getEvent(id);
                    frame.showEventDetails(client.getUser().getEvent(id));
                } else if (command.contains("DELETE EVENT")) {
                    String id = line.split(": ")[1];
                    System.out.println("Delete: Event " + client.getUser().getEvent(id).getTitle());
                    Event aux = new Event(client.getUser().getEvent(id));
                    client.getUser().removeEvent(id);
                    client.deleteEvent(aux);
                    // Hay que informar al servidor
                    frame.showEvents();
                } else if (command.contains("DELETE USER")) {
                    String name = line.split(": ")[1];
                    System.out.println("Delete: User " + name);
                    client.deleteUser(name);
                } else if (command.contains("DELETE CONTACT")) {
                    String name = line.split(": ")[1];
                    System.out.println("Delete: Contact " + name);
                    client.removeContact(name);
                    frame.showListContacts();
                } else if (command.contains("UPDATE EVENT")) {
                    String id = line.split(": ")[1];
                    System.out.println("Update: Event " + id);
                    try {
                        client.getUser().getEvent(id).setTitle(frame.getShowEventTitleText());
                        client.getUser().getEvent(id).setDescription(frame.getShowEventDescriptionText());
                        client.getUser().getEvent(id).setDate(frame.getShowEventDate());
                        client.getUser().getEvent(id).setAlarm(frame.getShowEventAlarm());
                        client.updateEvent(client.getUser().getEvent(id));
                        frame.showEvents();
                        frame.showMessages();
                    } catch (ParseException ignore) { }
                } else {
                    throw new RuntimeException("Unknown command!");
                }
        }
    }

    public void showSignUp() {
        frame.showSignUp();
        System.out.println("Sign up panel");
    }

    public void goBack() {
        frame.showSignIn();
        System.out.println("Go back");
    }

    public void signUp() {
        User user = new User(frame.getMailTextSignUp(), frame.getUserTextSignUp(), frame.getPasswordTextSignUp(),
                frame.getDniTextSignUp());
        if (user.isCorrect()) {
            client.signUp(user);
            System.out.println("Sign up: " + user);
        } else {
            System.out.println("Sign up: USER ISN'T CORRECT");
        }
    }

    public void signIn() {
        User user = new User(frame.getUserTextSignIn(), frame.getPasswordTextSignIn());
        System.out.println("Sign in: " + user);
        client.signIn(user);
    }

    public void showCreateEvent() {
        frame.showCreateEventPanel();
        System.out.println("Create event panel");
    }

    public void createEvent() {
        try {
            Event event = new Event(client.getName() + "_" + cont++, frame.getCreateEventTitle(),
                    frame.getCreateEventDate(), frame.getCreateEventAlarm(),
                    frame.getCreateEventDescription(), client.getUser().getName());
            System.out.println("Create event: " + event);
            client.createEvent(event);
        } catch (ParseException ex) {
            System.out.println("Create event: Error. Date format incorrect");
        }
    }

    public void cancelCreateEvent() {
        frame.showMessages();
        System.out.println("Cancel create event");
    }

    public void showMessages() {
        frame.showMessages();
        System.out.println("Messages panel");
    }

    public void showAdmin() {
        client.receiveUsers();
    }

    public void logOut() {
        client.logOut();
        //client.setUser(null);
        frame.showSignIn();
    }

    public void inviteContacts() {
        int i = 0;
        ArrayList<JCheckBox> buttons = frame.getGuestsButtons();
        for (JCheckBox button: buttons) {
            if (button.isSelected()) {
                frame.getActualEvent().putGuest(button.getActionCommand());
                ++i;
            }
        }
        System.out.println("Actual: " + frame.getActualEvent());
        if (i != 0) {
            client.getUser().putEvent(frame.getActualEvent());
            client.inviteGuests(new Event(frame.getActualEvent()));
        }
    }

    public void confirmForgottenPassword() {
        User user = new User(frame.getMailTextForgottenPasswordPanel(),
                frame.getPasswordTextForgottenPasswordPanel(), frame.getDniTextForgottenPasswordPanel());
        client.forgottenPassword(user);
        System.out.println("Confirm forgotten password: " + user);
    }

}
