package pkgController;

import pkgClient.Client;
import pkgFrame.ClientFrame;
import pkgUtils.Event;
import pkgUtils.User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

/**
 * Clase para el controlador del cliente
 * En ella crearemos el controlador y las respectivas funciones asociadas a este
 * Es decir, se hará el tratamiento de las pulsaciones de los botones de la ventana
 */
public class ClientController implements ActionListener {

    private ClientFrame frame;
    private Client client;

    // Socket para el servidor, a través de él podemos enviarle mensajes
    // private PrintWriter output;
    // private ObjectOutputStream output;

    // Constructor
    public ClientController(ClientFrame frame, Client client) {
        this.frame = frame;
        this.client = client;
        // this.output = client.getWriter();
    }

    /**
     * En esta función se hará el tratamiento de los botones creados en ClientFrame. Es decir, si se pulsa un
     * botón se enviará un comando que coincidirá con el nombre asociado a dicho botón (ej.: JButton("Enviar"))
     * a no ser que se indique lo contrario (ej.: setActionCommand("Enviar"))
     *
     * @param e evento que se recibe, es decir, botón pulsado
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        User user;

        switch (e.getActionCommand().toUpperCase()) {
            case "SIGN UP PANEL":
                frame.showSignUp();
                System.out.println("Sign up panel");
                break;
            case "GO BACK":
                frame.showSignIn();
                System.out.println("Go back");
                break;
            case "SIGN UP":
                user = new User(frame.getMailTextSignUp(), frame.getUserTextSignUp(), frame.getPasswordTextSignUp(), frame.getDniTextSignUp());
                client.signUp(user);
                //frame.showSignIn();
                System.out.println("Sign up: " + user);
                break;
            case "SIGN IN":
                user = new User(frame.getUserTextSignIn(), frame.getPasswordTextSignIn());
                client.signIn(user);
                //frame.showMainPanel();
                System.out.println("Sign in: " + user);
                break;
            case "CREATE EVENT":

                break;
            case "LOG OUT":
                client.setUser(null);
                frame.showSignIn();
                System.out.println("Log out");
                break;
            default:
                throw new RuntimeException("Unknown command!");
        }

    }

    // -----------------------------------------------------------------------------------
    // -------------------------------- FUNCIONES CREADAS --------------------------------
    // -----------------------------------------------------------------------------------



}
