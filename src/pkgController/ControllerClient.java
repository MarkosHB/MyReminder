package pkgController;

import pkgClient.Client;
import pkgFrame.ClientFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;

/**
 * Clase para el controlador del cliente
 * En ella crearemos el controlador y las respectivas funciones asociadas a este
 * Es decir, se hará el tratamiento de las pulsaciones de los botones de la ventana
 */
public class ControllerClient implements ActionListener {

    private ClientFrame frame;
    private Client client;

    // Socket para el servidor, a través de él podemos enviarle mensajes
    private PrintWriter output;

    // Constructor
    public ControllerClient(ClientFrame frame, Client client) {
        this.frame = frame;
        this.client = client;
        output = client.getWriter();
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

        // IMPORTANTE! Usar este esqueleto para el proyecto
        switch (e.getActionCommand().toUpperCase()) {
            // EJEMPLO DE USO (los 'case' deben ser implementados por nosotros)
            case "MODO OSCURO":
                /*
                System.out.println("Client: " + client.getName() + " -> Dark mode\n");
                // Puedo llamar a una función que haya definido en ClientFrame y así modificarlo
                frame.darkMode();
                */
                break;
            case "ENVIAR":
                /*
                try {
                    System.out.println("Client: " + client.getName() + " -> Send message");
                    // Puedo obtener el valor de una variable mediante su GETTER
                    if (!frame.getMessageText().equalsIgnoreCase("")) {
                        // Puedo y debo llamar a funciones definidas abajo (más ordenado)
                        sendMessage();
                    }
                } catch (Exception ignore) {
                    System.out.println("Client: " + client.getName() + " -> Something went wrong\n");
                }
                */
                break;
            case "CAMBIAR INFORMACIÓN":
                /*
                // Puedo obtener el valor de una variable mediante su GETTER
                if (!frame.getNameText().equalsIgnoreCase("")) {
                    // IMPORTANTE! 'output' se usa para enviar la información al servidor
                    output.println("CAMBIAR INFORMACIÓN");
                    output.println(frame.getNameText());
                    System.out.println("Client: " + client.getName() + " -> Change information");
                }
                */
                break;
            default:
                throw new RuntimeException("Unknown command!");
        }

    }

    // -----------------------------------------------------------------------------------
    // -------------------------------- FUNCIONES CREADAS --------------------------------
    // -----------------------------------------------------------------------------------



}
