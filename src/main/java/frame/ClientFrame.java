package frame;

import client.Client;
import controller.ClientController;
import utils.Event;
import utils.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * Clase para la ventana del cliente En ella crearemos la ventana y las
 * respectivas funciones asociadas a esta (El tratamiento de las pulsaciones de
 * los botones se realizará en otra clase)
 */
public abstract class ClientFrame extends JFrame {

    Client client;
    ClientController controller;

    // Posición de la ventana
    private final int posX;
    private final int posY;

    // Tamaño de la ventana
    private final int sizeX;
    private final int sizeY;

    // -------------------------- NUEVO --------------------------
    // Sign In Panel
    JPanel signInPanel;
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
    JPanel signUpPanel;
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
    JPanel mainPanel;
    private JLabel mainLabel;

    private JPanel centerPanel;
    private JLabel calendarLabel;
    private JScrollPane calendarPanel;
    private JPanel buttonsMainPanel;
    private JButton createEventButton;
    private JButton messagesButton;
    private JButton listContactsButton;
    private JPanel eastPanel;
    private JPanel infoPanel;
    private JButton adminButton;
    private JButton logOutButton;
    //private JPanel auxiliarPanel;
    private JScrollPane auxiliarPanel;

    private JLabel messagesLabel;
    private JPanel messagesPanel;
    private JScrollPane inboxPanel;

    private JPanel eventsPanel;
    /*
    private ArrayDeque<JPanel> eventsList;
    private ArrayDeque<JLabel> eventDate;
    private ArrayDeque<JLabel> eventTitle;
    private ArrayDeque<JButton> eventButton;
    private ArrayDeque<JButton> deleteEventButton;
    private ButtonGroup eventButtons;
    */

    // Create Event Panel
    private JPanel createEventPanel;

    private JLabel createEventTitleLabel;
    private JTextField createEventTitleText;
    private JLabel createEventDescriptionLabel;
    private JTextArea createEventDescriptionText;
    private JScrollPane createEventDescriptionScroll;
    private JLabel createEventDateLabel;
    private JTextField createEventDateText;
    private JLabel createEventAlarmLabel;
    private JTextField createEventAlarmText;

    private JPanel createEventButtons;
    private JButton confirmCreateEvent;
    private JButton cancelCreateEvent;

    private JPanel adminPanel;
    private JPanel usersPanel;
    private JScrollPane usersScrollPanel;

    // -------------------------- TERMINA NUEVO --------------------------

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

        // Hay distintos tipos de Layout, como BorderLayout, BoxLayout o FlowLayout
        // (buscar información)
        setLayout(new BorderLayout());

        // -------------------------- NUEVO --------------------------

        // -------------------------- Sign In Panel --------------------------
        signInPanel = new JPanel(new BorderLayout());

        // myReminderLabel = new JLabel("MyReminder");
        ImageIcon image = new ImageIcon("images/myReminder.png");
        Icon icon = new ImageIcon(image.getImage().getScaledInstance(260, 260, Image.SCALE_DEFAULT));
        signInLabel = new JLabel(icon);
        signInPanel.add(signInLabel, BorderLayout.NORTH);

        centerSignInPanel = new JPanel(new GridLayout(2, 1));
        // centerInitialPanel.setLayout(new BoxLayout(centerInitialPanel,
        // BoxLayout.Y_AXIS));
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

        // centerInitialPanel.add(userLabelInitialPanel);
        // centerInitialPanel.add(userTextInitialPanel);
        // centerInitialPanel.add(passwordLabelInitialPanel);
        // centerInitialPanel.add(passwordTextInitialPanel);
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

        // add(signUpPanel);

        // -------------------------- Forgotten Password Panel
        // --------------------------

        // -------------------------- Main Panel --------------------------
        // PRUEBA DE EVENTOS
        eventsPanel = new JPanel();
        eventsPanel.setLayout(new BoxLayout(eventsPanel, BoxLayout.Y_AXIS));
        /*
        eventsList = new ArrayDeque<>();
        eventDate = new ArrayDeque<>();
        eventTitle = new ArrayDeque<>();
        eventButton = new ArrayDeque<>();
        deleteEventButton = new ArrayDeque<>();
        */
        // TERMINA PRUEBA

        mainPanel = new JPanel(new BorderLayout());

        centerPanel = new JPanel(new BorderLayout());
        // centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        Icon icon3 = new ImageIcon(image.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));
        mainLabel = new JLabel(icon3);
        /*
         * JPanel eventsPanel = new JPanel(); eventsPanel.setLayout(new
         * BoxLayout(eventsPanel, BoxLayout.Y_AXIS)); // Prueba ArrayList<JPanel>
         * mensajes = new ArrayList<>(); JPanel fila = new JPanel(new FlowLayout());
         * JLabel labelPrueba = new JLabel("Texto de prueba"); fila.add(labelPrueba);
         * JButton botonPrueba = new JButton("Aceptar"); fila.add(botonPrueba); JButton
         * botonPrueba2 = new JButton("Denegar"); fila.add(botonPrueba2); JPanel fila2 =
         * new JPanel(new FlowLayout()); JLabel labelPrueba2 = new
         * JLabel("Texto de prueba 2"); fila2.add(labelPrueba2); JButton botonPrueba3 =
         * new JButton("Aceptar"); fila2.add(botonPrueba3); JButton botonPrueba4 = new
         * JButton("Denegar"); fila2.add(botonPrueba4); mensajes.add(fila);
         * mensajes.add(fila2); for (JPanel mensaje: mensajes) {
         * eventsPanel.add(mensaje); } eventsPanel.add(fila); calendarPanel = new
         * JScrollPane(eventsPanel); // TERMINA prueba
         */
        calendarPanel = new JScrollPane(eventsPanel);
        buttonsMainPanel = new JPanel(new GridLayout(1, 3));
        createEventButton = new JButton("Create event");
        listContactsButton = new JButton("List contacts");
        messagesButton = new JButton("Messages");
        buttonsMainPanel.add(createEventButton);
        buttonsMainPanel.add(listContactsButton);
        buttonsMainPanel.add(messagesButton);
        centerPanel.add(mainLabel, BorderLayout.NORTH);
        centerPanel.add(calendarPanel, BorderLayout.CENTER);
        centerPanel.add(buttonsMainPanel, BorderLayout.SOUTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        eastPanel = new JPanel(new BorderLayout());
        infoPanel = new JPanel(new FlowLayout());
        adminButton = new JButton("Admin");
        logOutButton = new JButton("Log out");
        infoPanel.add(adminButton);
        infoPanel.add(logOutButton);
        // Prueba -- Añadir
        ArrayList<JPanel> mensajes2 = new ArrayList<>();
        // TERMINA prueba
        auxiliarPanel = new JScrollPane();
        // Con esto se cambia el tamaño del EAST
        eastPanel.setPreferredSize(new Dimension(240, 800));
        eastPanel.add(infoPanel, BorderLayout.NORTH);
        eastPanel.add(auxiliarPanel, BorderLayout.CENTER);
        mainPanel.add(eastPanel, BorderLayout.EAST);

        // add(mainPanel);

        // Create Event Panel
        createEventPanel = new JPanel();
        createEventPanel.setLayout(new BoxLayout(createEventPanel, BoxLayout.Y_AXIS));

        createEventTitleLabel = new JLabel("Title");
        createEventTitleText = new JTextField(10);
        createEventDescriptionLabel = new JLabel("Description");
        createEventDescriptionText = new JTextArea("", 30, 10);
        createEventDescriptionScroll = new JScrollPane(createEventDescriptionText);
        createEventDateLabel = new JLabel("Date");
        // createEventDateText = new JTextField("DD/MM/YYYY hh:mm", 10);
        createEventDateText = new JTextField("30/06/2021 10:00", 10); // PRUEBAS
        createEventAlarmLabel = new JLabel("Alarm");
        createEventAlarmText = new JTextField("05/06/2021 21:30", 10);

        createEventButtons = new JPanel(new GridLayout(1, 2));
        confirmCreateEvent = new JButton("Confirm");
        confirmCreateEvent.setActionCommand("Confirm create event");
        cancelCreateEvent = new JButton("Cancel");
        cancelCreateEvent.setActionCommand("Cancel create event");
        createEventButtons.add(confirmCreateEvent);
        createEventButtons.add(cancelCreateEvent);

        createEventPanel.add(createEventTitleLabel);
        createEventPanel.add(createEventTitleText);
        createEventPanel.add(createEventDescriptionLabel);
        createEventPanel.add(createEventDescriptionScroll);
        createEventPanel.add(createEventDateLabel);
        createEventPanel.add(createEventDateText);
        createEventPanel.add(createEventAlarmLabel);
        createEventPanel.add(createEventAlarmText);
        createEventPanel.add(createEventButtons);

        adminPanel = new JPanel(new BorderLayout());
        adminPanel.add(new JLabel("Users:"), BorderLayout.NORTH);

        usersPanel = new JPanel();
        usersPanel.setLayout(new BoxLayout(usersPanel, BoxLayout.Y_AXIS));
        usersScrollPanel = new JScrollPane(usersPanel);
        adminPanel.add(usersScrollPanel, BorderLayout.CENTER);

        ////////////////////////// PANEL 3 ///////////////////////////////

        /*
         * panel3 = new JPanel(); panel3.setBackground(Color.orange);
         * panel3.setBounds(0, 0, 960, 540); panel3.setLayout(null);
         * panel3.setVisible(false);
         * 
         * // etiqueta --> Email etiquetaemailpanel3 = new JLabel("Email");
         * etiquetaemailpanel3.setBounds(300, 70, 50, 50);
         * panel3.add(etiquetaemailpanel3); // campo de texto --> Email textoemailpanel3
         * = new JTextField(); textoemailpanel3.setBounds(300, 110, 300, 30);
         * panel3.add(textoemailpanel3); // etiqueta --> DNI etiquetadnipanel3 = new
         * JLabel("DNI"); etiquetadnipanel3.setBounds(300, 140, 100, 50);
         * panel3.add(etiquetadnipanel3); // campo de texto --> DNI textodnipanel3 = new
         * JTextField(); textodnipanel3.setBounds(300, 180, 300, 30);
         * panel3.add(textodnipanel3);
         * 
         * //boton confirmar botonconfirmarpanel3 = new JButton("Confirmar");
         * botonconfirmarpanel3.setBounds(380, 230, 160, 40);
         * panel3.add(botonconfirmarpanel3);
         * //botonconfirmarpanel3.addActionListener(this);
         * 
         * //boton volver atras botonvolveratraspanel3 = new JButton("volver atras");
         * botonvolveratraspanel3.setBounds(20, 440, 150, 40);
         * panel3.add(botonvolveratraspanel3);
         * //botonvolveratraspanel3.addActionListener(this); add(panel3);
         * 
         * ////////////////////////// PANEL 4 ///////////////////
         * 
         * panel4 = new JPanel(); panel4.setBackground(Color.orange);
         * panel4.setBounds(0, 0, 960, 540); panel4.setLayout(null);
         * panel4.setVisible(false);
         * 
         * // etiqueta --> Usuario etiquetausuariopanel4 = new
         * JLabel("Usuario (No modificar el campo, ERROR!!)");
         * etiquetausuariopanel4.setBounds(300, 70, 250, 50);
         * panel4.add(etiquetausuariopanel4); // campo de texto --> Usuario
         * textousuariopanel4 = new JTextField(); textousuariopanel4.setBounds(300, 110,
         * 300, 30); panel4.add(textousuariopanel4); // etiqueta --> Nueva contraseña
         * etiquetacontraseñapanel4 = new JLabel("Nueva contraseña");
         * etiquetacontraseñapanel4.setBounds(300, 140, 150, 50);
         * panel4.add(etiquetacontraseñapanel4); // campo de texto --> Nueva contraseña
         * textocontraseñapanel4 = new JTextField();
         * textocontraseñapanel4.setBounds(300, 180, 300, 30);
         * panel4.add(textocontraseñapanel4);
         * 
         * //boton continuar botoncontinuarpanel4 = new JButton("Continuar");
         * botoncontinuarpanel4.setBounds(380, 230, 160, 40);
         * panel4.add(botoncontinuarpanel4);
         * //botoncontinuarpanel4.addActionListener(this);
         * 
         * 
         * //boton volver atras botonvolveratraspanel4 = new JButton("volver atras");
         * botonvolveratraspanel4.setBounds(20, 440, 150, 40);
         * panel4.add(botonvolveratraspanel4);
         * 
         * add(panel4);
         * 
         * //////////////////////////////// Panel Cliente /////////////////////////////
         * 
         * panel5 = new JPanel(); panel5.setBackground(Color.orange);
         * panel5.setBounds(0,0,960,540); panel5.setLayout(null);
         * panel5.setVisible(false);
         * 
         * panel5_1 = new JPanel(); panel5_1.setBackground(Color.lightGray);
         * panel5_1.setBounds(40,100, 550,320); panel5_1.setLayout(null);
         * panel5_1.setVisible(true);
         * 
         * panel5_2 = new JPanel(); panel5_2.setBackground(Color.lightGray);
         * panel5_2.setBounds(630,100, 300,370); panel5_2.setLayout(null);
         * panel5_2.setVisible(true);
         * 
         * panel5.add(panel5_1); panel5.add(panel5_2);
         * 
         * 
         * // etiqueta calendario etiquetacalendario = new JLabel("Calendario");
         * etiquetacalendario.setBounds(240, 40, 100, 50);
         * panel5_1.add(etiquetacalendario);
         * 
         * // etiqueta myreminder etiquetamyreminder = new JLabel("Myreminder");
         * etiquetamyreminder.setBounds(280, 40, 100, 50);
         * panel5.add(etiquetamyreminder);
         * 
         * //boton Crear evento botoncrearevento = new JButton("Bandeja de entrada");
         * botoncrearevento.setBounds(40, 430, 130, 40); panel5.add(botoncrearevento);
         * ///////cambiar por bandeja de entrada
         * 
         * //boton cambiar vista botoncambiarvista = new JButton("Cambiar Vista");
         * botoncambiarvista.setBounds(240, 430, 130, 40);
         * panel5.add(botoncambiarvista);
         * 
         * 
         * //boton lista de contactos botonlistadecontactos= new
         * JButton("Lista de conctactos"); botonlistadecontactos.setBounds(430, 430,
         * 160, 40); panel5.add(botonlistadecontactos);
         * 
         * //boton perfil botonperfil= new JButton("Perfil"); botonperfil.setBounds(730,
         * 40, 70, 40); panel5.add(botonperfil);
         * 
         * //boton cerrar sesion botoncerrarsesion = new JButton("Cerrar Sesión");
         * botoncerrarsesion.setBounds(810, 40, 120, 40); panel5.add(botoncerrarsesion);
         * //botoncerrarsesion.addActionListener(this);
         * 
         * 
         * // Creamos el controlador y activamos los botones //ControllerClient
         * controller = new ControllerClient(this); //this.controller(controller);
         * 
         * add(panel5);
         */
        // -------------------------- TERMINA NUEVO --------------------------

        // Creamos el controlador y activamos los botones
        //controller = new ClientController(this, client);
        //this.controller(controller);
        assignController();

        // Mostramos la ventana
        setSize(this.sizeX, this.sizeY);
        setLocation(this.posX, this.posY);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

    abstract void assignController();

    /**
     * Activa los botones y permite que sean tratados por el controlador
     * 
     * @param myController controlador que tratará las pulsaciones de los botones
     */
    void controller(ActionListener myController) {

        signInButton.addActionListener(myController);
        signUpButtonInitialPanel.addActionListener(myController);
        forgottenPasswordButton.addActionListener(myController);

        signUpButton.addActionListener(myController);
        goBackButton.addActionListener(myController);

        createEventButton.addActionListener(myController);
        listContactsButton.addActionListener(myController);
        messagesButton.addActionListener(myController);

        confirmCreateEvent.addActionListener(myController);
        cancelCreateEvent.addActionListener(myController);

        adminButton.addActionListener(myController);
        logOutButton.addActionListener(myController);

    }

    // -----------------------------------------------------------------------------------
    // -------------------------------- FUNCIONES CREADAS --------------------------------
    // -----------------------------------------------------------------------------------

    public void showSignIn() {
        signInPanel.setVisible(false);
        signUpPanel.setVisible(false);
        mainPanel.setVisible(false);
        remove(signUpPanel);
        remove(mainPanel);
        add(signInPanel);
        signInPanel.setVisible(true);
    }

    public void showSignUp() {
        signInPanel.setVisible(false);
        signUpPanel.setVisible(false);
        mainPanel.setVisible(false);
        remove(signInPanel);
        remove(mainPanel);
        add(signUpPanel);
        signUpPanel.setVisible(true);
    }

    public void showMainPanel() {
        signInPanel.setVisible(false);
        signUpPanel.setVisible(false);
        mainPanel.setVisible(false);
        infoPanel.removeAll();
        if (client.getUser().isAdmin()) {
            infoPanel.add(adminButton);
        }
        infoPanel.add(logOutButton);
        remove(signInPanel);
        remove(signUpPanel);
        add(mainPanel);
        mainPanel.setVisible(true);
    }

    public void showCreateEventPanel() {
        signInPanel.setVisible(false);
        signUpPanel.setVisible(false);
        mainPanel.setVisible(false);
        eastPanel.remove(auxiliarPanel);
        eastPanel.add(createEventPanel, BorderLayout.CENTER);
        mainPanel.setVisible(true);
    }

    public void showEventDetails() {
        signInPanel.setVisible(false);
        signUpPanel.setVisible(false);
        mainPanel.setVisible(false);
        eastPanel.remove(auxiliarPanel);
        // Poner que se muestre otro panel que debo crear
        eastPanel.add(createEventPanel, BorderLayout.CENTER);
        mainPanel.setVisible(true);
    }

    public void showMessagesPanel() {
        signInPanel.setVisible(false);
        signUpPanel.setVisible(false);
        mainPanel.setVisible(false);
        eastPanel.remove(createEventPanel);
        eastPanel.add(auxiliarPanel, BorderLayout.CENTER);
        mainPanel.setVisible(true);
    }

    public void showMessages() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        mainPanel.setVisible(false);
        eventsPanel.setVisible(false);
    }

    public void showEvents() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        mainPanel.setVisible(false);
        eventsPanel.setVisible(false);
        eventsPanel.removeAll();
        JPanel panel;
        //eventDate.clear();
        //eventTitle.clear();
        //eventButton.clear();
        Event[] events = client.getUser().getEvents().values().toArray(new Event[0]);
        for (int i = 0; i < client.getUser().getEvents().size(); i++) {
            panel = new JPanel(new FlowLayout(FlowLayout.TRAILING, 20, 5));
            panel.add(new JLabel(formatter.format(events[i].getDate())));
            panel.add(new JLabel(events[i].getTitle()));
            JButton button = new JButton("More info");
            button.setActionCommand("More info: " + events[i].getId());
            button.addActionListener(controller);
            panel.add(button);
            button = new JButton("Delete");
            button.setActionCommand("Delete: " + events[i].getId());
            button.addActionListener(controller);
            panel.add(button);
            //panel.add(new JButton("More info"));
            //panel.add(new JButton("Delete"));
            eventsPanel.add(panel);
        }

        eventsPanel.setVisible(true);
        mainPanel.setVisible(true);
    }

    public void showAdminFrame() {
        mainPanel.setVisible(false);
        auxiliarPanel.setVisible(false);
        eastPanel.remove(auxiliarPanel);
        JPanel panel;
        //eventDate.clear();
        //eventTitle.clear();
        //eventButton.clear();
        ArrayList<String> users = new ArrayList<>(client.getUsers().keySet());
        for (String user : users) {
            panel = new JPanel(new FlowLayout(FlowLayout.TRAILING, 5, 5));
            panel.add(new JLabel(user));
            JButton button = new JButton("Delete");
            button.setActionCommand("Delete: " + client.getUsers());
            button.addActionListener(controller);
            panel.add(button);
            usersPanel.add(panel);
        }

        eastPanel.add(adminPanel, BorderLayout.CENTER);
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

    public String getCreateEventTitle() {
        return createEventTitleText.getText();
    }

    public String getCreateEventDescription() {
        return createEventDescriptionText.getText();
    }

    public Date getCreateEventDate() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return formatter.parse(createEventDateText.getText());
    }

    public Date getCreateEventAlarm() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return formatter.parse(createEventAlarmText.getText());
    }

    public void showConfirmForgottenPassword() {
    }

    public void showForgottenPassword() {
    }

}
