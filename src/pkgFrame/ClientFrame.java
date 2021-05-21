package pkgFrame;

import pkgClient.Client;
import pkgController.ClientController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Arrays;

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

    // -------------------------- NUEVO --------------------------
    //  Sign In Panel
    private JPanel signInPanel;
    private JLabel signInLabel;

    private JPanel centerSignInPanel;
    private JPanel userSignInPanel;
    private JLabel userLabelSignInPanel;
    private JTextField userTextSignInPanel;
    private JPanel passwordSignInPanel;
    private JLabel passwordLabelSignInPanel;
    private JPasswordField passwordTextSignInPanel;

    private JPanel southSignInPanel;
    private JButton forgottenPasswordButton;
    private JButton signInButton;
    private JButton signUpButtonInitialPanel;


    // Sign Up Panel
    private JPanel signUpPanel;
    private JLabel signUpLabel;

    private JPanel centerSignUpPanel;
    private JPanel userSignUpPanel;
    private JLabel userLabelSignUpPanel;
    private JTextField userTextSignUpPanel;
    private JPanel mailSignUpPanel;
    private JLabel mailLabelSignUpPanel;
    private JTextField mailTextSignUpPanel;
    private JPanel dniSignUpPanel;
    private JLabel dniLabelSignUpPanel;
    private JTextField dniTextSignUpPanel;
    private JPanel passwordSignUpPanel;
    private JLabel passwordLabelSignUpPanel;
    private JPasswordField passwordTextSignUpPanel;

    private JPanel southSignUpPanel;
    private JButton signUpButton;
    private JButton goBackButton;

    // Forgotten Password Panel
    private JPanel forgottenPasswordPanel;
    private JLabel etiquetaemailpanel3;
    private JLabel etiquetadnipanel3;
    private JTextField textoemailpanel3;
    private JTextField textodnipanel3;
    private JButton botonconfirmarpanel3;
    private JButton botonvolveratraspanel3;

    // panel 4
    private JPanel panel4;
    private JTextField textousuariopanel4;
    private JTextField textocontraseñapanel4;
    private JLabel etiquetausuariopanel4;
    private JLabel etiquetacontraseñapanel4;
    private JButton botoncontinuarpanel4;
    private JButton botonvolveratraspanel4;

    // Main Panel
    private JPanel mainPanel;
    private JLabel mainLabel;

    private JPanel centerPanel;
    private JLabel calendarLabel;
    private JScrollPane calendarPanel;
    private JPanel buttonsMainPanel;
    private JButton createEventButton;
    private JButton messagesButton;
    private JButton contactsButton;
    private JPanel eastPanel;
    private JPanel infoPanel;
    private JButton profileButton;
    private JButton logOutButton;
    private JScrollPane auxiliarPanel;
    // -------------------------- TERMINA NUEVO --------------------------

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
        this.sizeX = 800;
        this.sizeY = 400;
        this.client = client;

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createGUI();
            }
        });

    }

    // Crea la ventana del usuario
    private void createGUI() {

        // Hay distintos tipos de Layout, como BorderLayout, BoxLayout o FlowLayout (buscar información)
        setLayout(new BorderLayout());

        // -------------------------- NUEVO --------------------------

        // -------------------------- Sign In Panel --------------------------
        signInPanel = new JPanel(new BorderLayout());

        // myReminderLabel = new JLabel("MyReminder");
        ImageIcon image = new ImageIcon("images/sendImage.png");
        Icon icon = new ImageIcon(image.getImage().getScaledInstance(260, 260, Image.SCALE_DEFAULT));
        signInLabel = new JLabel(icon);
        signInPanel.add(signInLabel, BorderLayout.NORTH);

        centerSignInPanel = new JPanel(new GridLayout(2, 1));
        // centerInitialPanel.setLayout(new BoxLayout(centerInitialPanel, BoxLayout.Y_AXIS));
        userSignInPanel = new JPanel(new FlowLayout());
        userLabelSignInPanel = new JLabel("User: ");
        userTextSignInPanel = new JTextField(10);
        userSignInPanel.add(userLabelSignInPanel);
        userSignInPanel.add(userTextSignInPanel);
        passwordSignInPanel = new JPanel(new FlowLayout());
        passwordLabelSignInPanel = new JLabel("Password: ");
        passwordTextSignInPanel = new JPasswordField(10);
        passwordSignInPanel.add(passwordLabelSignInPanel);
        passwordSignInPanel.add(passwordTextSignInPanel);

        //centerInitialPanel.add(userLabelInitialPanel);
        //centerInitialPanel.add(userTextInitialPanel);
        //centerInitialPanel.add(passwordLabelInitialPanel);
        //centerInitialPanel.add(passwordTextInitialPanel);
        centerSignInPanel.add(userSignInPanel);
        centerSignInPanel.add(passwordSignInPanel);
        signInPanel.add(centerSignInPanel, BorderLayout.CENTER);

        southSignInPanel = new JPanel(new GridLayout(1, 3));
        signInButton = new JButton("Sign in");
        signUpButtonInitialPanel = new JButton("Sign up");
        signUpButtonInitialPanel.setActionCommand("Sign up panel");
        forgottenPasswordButton = new JButton("Forgotten password");
        southSignInPanel.add(signUpButtonInitialPanel);
        southSignInPanel.add(signInButton);
        southSignInPanel.add(forgottenPasswordButton);
        signInPanel.add(southSignInPanel, BorderLayout.SOUTH);

        add(signInPanel);


        // -------------------------- Sign Up Panel --------------------------
        signUpPanel = new JPanel(new BorderLayout());

        Icon icon2 = new ImageIcon(image.getImage().getScaledInstance(160, 160, Image.SCALE_DEFAULT));
        signUpLabel = new JLabel(icon2);
        signUpPanel.add(signUpLabel, BorderLayout.NORTH);

        centerSignUpPanel = new JPanel(new GridLayout(4, 1));
        mailSignUpPanel = new JPanel(new FlowLayout());
        mailLabelSignUpPanel = new JLabel("Mail: ");
        mailTextSignUpPanel = new JTextField(10);
        mailSignUpPanel.add(mailLabelSignUpPanel);
        mailSignUpPanel.add(mailTextSignUpPanel);
        userSignUpPanel = new JPanel(new FlowLayout());
        userLabelSignUpPanel = new JLabel("User: ");
        userTextSignUpPanel = new JTextField(10);
        userSignUpPanel.add(userLabelSignUpPanel);
        userSignUpPanel.add(userTextSignUpPanel);
        passwordSignUpPanel = new JPanel(new FlowLayout());
        passwordLabelSignUpPanel = new JLabel("Password: ");
        passwordTextSignUpPanel = new JPasswordField(10);
        passwordSignUpPanel.add(passwordLabelSignUpPanel);
        passwordSignUpPanel.add(passwordTextSignUpPanel);
        dniSignUpPanel = new JPanel(new FlowLayout());
        dniLabelSignUpPanel = new JLabel("DNI: ");
        dniTextSignUpPanel = new JTextField(10);
        dniSignUpPanel.add(dniLabelSignUpPanel);
        dniSignUpPanel.add(dniTextSignUpPanel);
        centerSignUpPanel.add(mailSignUpPanel);
        centerSignUpPanel.add(userSignUpPanel);
        centerSignUpPanel.add(passwordSignUpPanel);
        centerSignUpPanel.add(dniSignUpPanel);
        signUpPanel.add(centerSignUpPanel, BorderLayout.CENTER);

        southSignUpPanel = new JPanel(new GridLayout(1, 2));
        signUpButton = new JButton("Sign up");
        goBackButton = new JButton("Go back");
        southSignUpPanel.add(signUpButton);
        southSignUpPanel.add(goBackButton);
        signUpPanel.add(southSignUpPanel, BorderLayout.SOUTH);

        //add(signUpPanel);

        // -------------------------- Forgotten Password Panel --------------------------

        // -------------------------- Main Panel --------------------------
        mainPanel = new JPanel(new BorderLayout());

        centerPanel = new JPanel(new BorderLayout());
        // centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        Icon icon3 = new ImageIcon(image.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));
        mainLabel = new JLabel(icon3);
        calendarPanel = new JScrollPane();
        buttonsMainPanel = new JPanel(new GridLayout(1, 3));
        createEventButton = new JButton("Create event");
        contactsButton = new JButton("Contacts");
        messagesButton = new JButton("Messages");
        buttonsMainPanel.add(createEventButton);
        buttonsMainPanel.add(contactsButton);
        buttonsMainPanel.add(messagesButton);
        centerPanel.add(mainLabel, BorderLayout.NORTH);
        centerPanel.add(calendarPanel, BorderLayout.CENTER);
        centerPanel.add(buttonsMainPanel, BorderLayout.SOUTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        eastPanel = new JPanel(new BorderLayout());
        infoPanel = new JPanel(new FlowLayout());
        profileButton = new JButton("Profile");
        logOutButton = new JButton("Log out");
        infoPanel.add(profileButton);
        infoPanel.add(logOutButton);
        auxiliarPanel = new JScrollPane();
        eastPanel.add(infoPanel, BorderLayout.NORTH);
        eastPanel.add(auxiliarPanel, BorderLayout.CENTER);
        mainPanel.add(eastPanel, BorderLayout.EAST);

        //add(mainPanel);

        //////////////////////////  PANEL 3 ///////////////////////////////

        /*
        panel3 = new JPanel();
        panel3.setBackground(Color.orange);
        panel3.setBounds(0, 0, 960, 540);
        panel3.setLayout(null);
        panel3.setVisible(false);

        //  etiqueta --> Email
        etiquetaemailpanel3 = new JLabel("Email");
        etiquetaemailpanel3.setBounds(300, 70, 50, 50);
        panel3.add(etiquetaemailpanel3);
        // campo de texto --> Email
        textoemailpanel3 = new JTextField();
        textoemailpanel3.setBounds(300, 110, 300, 30);
        panel3.add(textoemailpanel3);
        //  etiqueta --> DNI
        etiquetadnipanel3 = new JLabel("DNI");
        etiquetadnipanel3.setBounds(300, 140, 100, 50);
        panel3.add(etiquetadnipanel3);
        // campo de texto --> DNI
        textodnipanel3 = new JTextField();
        textodnipanel3.setBounds(300, 180, 300, 30);
        panel3.add(textodnipanel3);

        //boton confirmar
        botonconfirmarpanel3 = new JButton("Confirmar");
        botonconfirmarpanel3.setBounds(380, 230, 160, 40);
        panel3.add(botonconfirmarpanel3);
        //botonconfirmarpanel3.addActionListener(this);

        //boton volver atras
        botonvolveratraspanel3 = new JButton("volver atras");
        botonvolveratraspanel3.setBounds(20, 440, 150, 40);
        panel3.add(botonvolveratraspanel3);
        //botonvolveratraspanel3.addActionListener(this);
        add(panel3);

        ////////////////////////// PANEL 4  ///////////////////

        panel4 = new JPanel();
        panel4.setBackground(Color.orange);
        panel4.setBounds(0, 0, 960, 540);
        panel4.setLayout(null);
        panel4.setVisible(false);

        //  etiqueta --> Usuario
        etiquetausuariopanel4 = new JLabel("Usuario (No modificar el campo, ERROR!!)");
        etiquetausuariopanel4.setBounds(300, 70, 250, 50);
        panel4.add(etiquetausuariopanel4);
        // campo de texto --> Usuario
        textousuariopanel4 = new JTextField();
        textousuariopanel4.setBounds(300, 110, 300, 30);
        panel4.add(textousuariopanel4);
        //  etiqueta --> Nueva contraseña
        etiquetacontraseñapanel4 = new JLabel("Nueva contraseña");
        etiquetacontraseñapanel4.setBounds(300, 140, 150, 50);
        panel4.add(etiquetacontraseñapanel4);
        // campo de texto --> Nueva contraseña
        textocontraseñapanel4 = new JTextField();
        textocontraseñapanel4.setBounds(300, 180, 300, 30);
        panel4.add(textocontraseñapanel4);

        //boton continuar
        botoncontinuarpanel4 = new JButton("Continuar");
        botoncontinuarpanel4.setBounds(380, 230, 160, 40);
        panel4.add(botoncontinuarpanel4);
        //botoncontinuarpanel4.addActionListener(this);


        //boton volver atras
        botonvolveratraspanel4 = new JButton("volver atras");
        botonvolveratraspanel4.setBounds(20, 440, 150, 40);
        panel4.add(botonvolveratraspanel4);

        add(panel4);

        //////////////////////////////// Panel Cliente /////////////////////////////

        panel5 = new JPanel();
        panel5.setBackground(Color.orange);
        panel5.setBounds(0,0,960,540);
        panel5.setLayout(null);
        panel5.setVisible(false);

        panel5_1 = new JPanel();
        panel5_1.setBackground(Color.lightGray);
        panel5_1.setBounds(40,100, 550,320);
        panel5_1.setLayout(null);
        panel5_1.setVisible(true);

        panel5_2 = new JPanel();
        panel5_2.setBackground(Color.lightGray);
        panel5_2.setBounds(630,100, 300,370);
        panel5_2.setLayout(null);
        panel5_2.setVisible(true);

        panel5.add(panel5_1);
        panel5.add(panel5_2);


        // etiqueta calendario
        etiquetacalendario = new JLabel("Calendario");
        etiquetacalendario.setBounds(240, 40, 100, 50);
        panel5_1.add(etiquetacalendario);

        // etiqueta myreminder
        etiquetamyreminder = new JLabel("Myreminder");
        etiquetamyreminder.setBounds(280, 40, 100, 50);
        panel5.add(etiquetamyreminder);

        //boton Crear evento
        botoncrearevento = new JButton("Bandeja de entrada");
        botoncrearevento.setBounds(40, 430, 130, 40);
        panel5.add(botoncrearevento);
        ///////cambiar por bandeja de entrada

        //boton cambiar vista
        botoncambiarvista = new JButton("Cambiar Vista");
        botoncambiarvista.setBounds(240, 430, 130, 40);
        panel5.add(botoncambiarvista);


        //boton lista de contactos
        botonlistadecontactos= new JButton("Lista de conctactos");
        botonlistadecontactos.setBounds(430, 430, 160, 40);
        panel5.add(botonlistadecontactos);

        //boton perfil
        botonperfil= new JButton("Perfil");
        botonperfil.setBounds(730, 40, 70, 40);
        panel5.add(botonperfil);

        //boton cerrar sesion
        botoncerrarsesion = new JButton("Cerrar Sesión");
        botoncerrarsesion.setBounds(810, 40, 120, 40);
        panel5.add(botoncerrarsesion);
        //botoncerrarsesion.addActionListener(this);


        // Creamos el controlador y activamos los botones
        //ControllerClient controller = new ControllerClient(this);
        //this.controller(controller);

        add(panel5);
        */
        // -------------------------- TERMINA NUEVO --------------------------

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
        ClientController controller = new ClientController(this, client);
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
     * ControllerClient myController = new ControllerClient(this, client);
     * this.controller(myController);
     *
     * En esta función activaremos los botones deseados de la siguiente forma:
     * button.addActionListener(myController);
     *
     * Repetimos la línea anterior para cada botón que deseamos activar
     *
     * @param myController controlador que tratará las pulsaciones de los botones
     */
    private void controller(ActionListener myController) {

        signInButton.addActionListener(myController);
        signUpButtonInitialPanel.addActionListener(myController);
        forgottenPasswordButton.addActionListener(myController);

        signUpButton.addActionListener(myController);
        goBackButton.addActionListener(myController);

        createEventButton.addActionListener(myController);
        contactsButton.addActionListener(myController);
        messagesButton.addActionListener(myController);

        profileButton.addActionListener(myController);
        logOutButton.addActionListener(myController);

    }

    // -----------------------------------------------------------------------------------
    // -------------------------------- FUNCIONES CREADAS --------------------------------
    // -----------------------------------------------------------------------------------

    public void showSignIn() {
        signUpPanel.setVisible(false);
        mainPanel.setVisible(false);
        remove(signUpPanel);
        remove(mainPanel);
        add(signInPanel);
        signInPanel.setVisible(true);
    }

    public void showSignUp() {
        signInPanel.setVisible(false);
        mainPanel.setVisible(false);
        remove(signInPanel);
        remove(mainPanel);
        add(signUpPanel);
        signUpPanel.setVisible(true);
    }

    public void showMainPanel() {
        signInPanel.setVisible(false);
        signUpPanel.setVisible(false);
        remove(signInPanel);
        remove(signUpPanel);
        add(mainPanel);
        mainPanel.setVisible(true);
    }

    // -----------------------------------------------------------------------------------
    // --------------------------- GETTERS, SETTERS AND CLEARS ---------------------------
    // -----------------------------------------------------------------------------------

    public String getUserTextSignIn() {
        return userTextSignInPanel.getText();
    }

    public String getPasswordTextSignIn() {
        return new String(passwordTextSignInPanel.getPassword());
    }

    public String getUserTextSignUp() {
        return userTextSignUpPanel.getText();
    }

    public String getPasswordTextSignUp() {
        return new String(passwordTextSignUpPanel.getPassword());
    }

    public String getMailTextSignUp() {
        return mailTextSignUpPanel.getText();
    }

    public String getDniTextSignUp() {
        return dniTextSignUpPanel.getText();
    }

}