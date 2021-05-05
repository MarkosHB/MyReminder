package pkgFrame;

import pkgClient.Client;
import pkgController.ControllerClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Clase para la ventana del cliente
 * En ella crearemos la ventana y las respectivas funciones asociadas a esta
 * (El tratamiento de las pulsaciones de los botones se realizará en otra clase)
 */
public class ClientFrame extends JFrame {

    private Client client;

    // Posición de la ventana
    private final int posX;
    private final int posY;

    // Tamaño de la ventana
    private final int sizeX;
    private final int sizeY;

    /**
     * EJEMPLOS DE DECLARACIÓN DE ALGUNAS VARIABLES QUE USAREMOS
     * Panel se crea con JPanel, ej.: private JPanel panel;
     * Label se crea con JLabel, ej.: private JLabel label;
     * TextField se crea con JTextField, ej.: private JTextField textField;
     * TextArea se crea con JTextArea, ej.: private JTextArea textArea;
     * Button se crea con JButton, ej.: private JButton button;
     * CheckBox se crea con JCheckBox, ej: private JCheckBox checkBox;
     * RadioButton se crea con JRadioButton, ej.: private JRadioButton radioButton;
     * ButtonGroup se crea con ButtonGroup, ej.: private ButtonGroup buttonGroup;
     * ScrollPane se crea con JScrollPane, ej.: private JScrollPane scrollPane;
     *
     * IMPORTANTE PONER NOMBRES IDENTIFICATIVOS SEGÚN FINALIDAD, ej.:
     * Botón para guardar eventos: private JButton saveEventButton;
     *
     * DECLARACIONES PARA UNA CONVERSACIÓN DE WHATSAPP (otro ejemplo más descriptivo)
     * private JPanel chatActualPanel;
     * private JLabel chatLabel;
     * private JTextArea conversationArea;
     * private JPanel messagePanel;
     * private JTextArea messageText;
     * private JButton sendMessageButton;
     */

    // Constructor
    public ClientFrame(String title, int posX, int posY, Client client) {

        super(title);
        this.posX = posX;
        this.posY = posY;
        this.sizeX = 700;
        this.sizeY = 400;
        this.client = client;

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createGUI();
            }
        });

    }

    // Crea el la ventana del usuario
    private void createGUI() {

        // Hay distintos tipos de Layout, como BorderLayout, BoxLayout o FlowLayout (buscar información)
        setLayout(new BorderLayout());

        /**
         * Los paneles pueden y deben agruparse, ej.: (Importante añadir finalmente al Frame)
         *
         * CREACIÓN DE PANEL PARA UNA CONVERSACIÓN DE WHATSAPP (antes deben haberse declarado las variables)
         *
         * // Se crea el panel para la conversación
         * chatActualPanel = new JPanel();
         * chatActualPanel.setLayout(new BoxLayout(chatActualPanel, BoxLayout.Y_AXIS));
         *
         * // Se crea la etiqueta que por defecto mostrará que no hay conversaciones
         * chatLabel = new JLabel("No hay conversaciones");
         *
         * // Se crea el área que mostrará la conversación
         * conversationArea = new JTextArea("", 30, 10);
         * conversationArea.setEditable(false);
         * // Quiero que sea de tipo Scroll
         * scrollConversation = new JScrollPane(conversationArea);
         *
         * // Se crea un sub-panel para enviar el mensaje del usuario
         * messagePanel = new JPanel();
         * messagePanel.setLayout(new BorderLayout());
         *
         * // Se crea el área donde irá el texto a enviar
         * messageText = new JTextArea("", 3, 10);
         * // Quiero que sea de tipo Scroll
         * scrollMessage = new JScrollPane(messageText);
         * // Se crea un botón para enviar el mensaje
         * sendMessageButton = new JButton("Enviar");
         * // Se le puede añadir una imagen al botón de la siguiente forma:
         * // (Importante en ese caso añadir setActionCommand("Enviar") para el controller)
         * // (Si se crea mediante JButton("Enviar"), el setActionCommand no es necesario)
         * //ImageIcon image = new ImageIcon("images/sendImage.png");
         * //Icon icon = new ImageIcon(image.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
         * //sendMessageButton = new JButton(icon);
         * //sendMessageButton.setActionCommand("Enviar");
         *
         * // Se añade en el sub-panel el mensaje a enviar en el centro (BorderLayout.CENTER)
         * messagePanel.add(scrollMessage, BorderLayout.CENTER);
         * // Se añade en el sub-panel el botón de enviar a la derecha (BorderLayout.EAST)
         * messagePanel.add(sendMessageButton, BorderLayout.EAST);
         *
         * // Se añade en el panel principal la etiqueta
         * chatActualPanel.add(chatLabel);
         * // Se añade en el panel principal la conversación
         * chatActualPanel.add(scrollConversation);
         * // Se añade en el panel principal el sub-panel para enviar el mensaje del usuario
         * chatActualPanel.add(messagePanel);
         *
         * // Se añade el panel principal en el centro del Frame (GUI)
         * add(chatActualPanel, BorderLayout.CENTER);
         */

        // Creamos el controlador y activamos los botones
        ControllerClient controller = new ControllerClient(this, client);
        this.controller(controller);

        // Mostramos la ventana
        setSize(this.sizeX, this.sizeY);
        setLocation(this.posX, this.posY);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

    /**
     * Activa los botones y permite que sean tratados por el controlador
     *
     * Se invocará en createGUI de la siguiente forma:
     * ControllerClient controller = new ControllerClient(this, client);
     * this.controller(controller);
     *
     * En esta función activaremos los botones deseados de la siguiente forma:
     * button.addActionListener(myController);
     *
     * Repetimos la línea anterior para cada botón que deseamos activar
     *
     * @param myController controlador que tratará las pulsaciones de los botones
     */
    private void controller(ActionListener myController) {

    }

    // -----------------------------------------------------------------------------------
    // -------------------------------- FUNCIONES CREADAS --------------------------------
    // -----------------------------------------------------------------------------------



    // -----------------------------------------------------------------------------------
    // --------------------------- GETTERS, SETTERS AND CLEARS ---------------------------
    // -----------------------------------------------------------------------------------



}
