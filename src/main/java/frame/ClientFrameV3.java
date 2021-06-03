package frame;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDayChooser;
import client.Client;
import controller.ClientController;
import utils.Event;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

/**
 * Clase para la ventana del cliente En ella crearemos la ventana y las
 * respectivas funciones asociadas a esta (El tratamiento de las pulsaciones de
 * los botones se realizará en otra clase)
 */
public class ClientFrameV3 extends JFrame {

    private Client client;
    private ClientController controller;

    // Posición de la ventana
    private final int posX;
    private final int posY;

    // Tamaño de la ventana
    private final int sizeX;
    private final int sizeY;

    // -------------------------- NUEVO --------------------------
    // Sign In Panel
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
    private JLabel forgottenPasswordPanelLabel;

    private JPanel centerforgottenPasswordPanel;
    private JPanel emailforgottenPasswordPanel;
    private JLabel emailLabelforgottenPasswordPanel;
    private JTextField emailtextforgottenPasswordPanel;
    private JPanel dniforgottenPasswordPanel;
    private JLabel dniLabelforgottenPasswordPanel;
    private JTextField dnitextforgottenPasswordPanel;

    private JPanel southforgottenPasswordPanel;
    private JButton acceptforgottenPasswordButton;
    private JButton goBackforgottenPasswordButton;

    // confirm forgotten password
    private JPanel confirmforgottenPasswordPanel;
    private JLabel confirmforgottenPasswordPanelLabel;

    private JPanel centerconfirmforgottenPasswordPanel;
    private JPanel emailconfirmforgottenPasswordPanel;
    private JLabel emailLabelconfirmforgottenPasswordPanel;
    private JTextField emailtextconfirmforgottenPasswordPanel;
    private JPanel dniconfirmforgottenPasswordPanel;
    private JLabel dniLabelconfirmforgottenPasswordPanel;
    private JTextField dnitextconfirmforgottenPasswordPanel;

    private JPanel southconfirmforgottenPasswordPanel;
    private JButton acceptconfirmforgottenPasswordButton;
    private JButton goBackconfirmforgottenPasswordButton;

    // Main Panel
    private JPanel mainPanel;
    private JLabel mainLabel;

    private JPanel centerPanel;
    private JLabel calendarLabel;
    // -------
    private JCalendar calendar;
    // ------
    private JScrollPane calendarPanel;
    private JPanel buttonsMainPanel;
    private JButton createEventButton;
    private JButton messagesButton;
    private JButton changeViewButton;
    private JPanel eastPanel;
    private JPanel infoPanel;
    // ---- prueba - pulsar - mostrar_calendario
    private JPanel auxpanel;
    private JButton showdate;
    // ---
    private JButton profileButton;
    private JButton logOutButton;
    private JScrollPane auxiliarPanel;

    private JPanel eventsPanel;
    private ArrayDeque<JPanel> eventsList;
    private ArrayDeque<JLabel> eventDate;
    private ArrayDeque<JLabel> eventTitle;
    private ArrayDeque<JButton> eventButton;
    private ButtonGroup eventButtons;

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

    // -------------------------- TERMINA NUEVO --------------------------

    /**
     * EJEMPLOS DE DECLARACIÓN DE ALGUNAS VARIABLES QUE USAREMOS Panel se crea con
     * JPanel, ej.: private JPanel panel; Label se crea con JLabel, ej.: private
     * JLabel label; TextField se crea con JTextField, ej.: private JTextField
     * textField; TextArea se crea con JTextArea, ej.: private JTextArea textArea;
     * Button se crea con JButton, ej.: private JButton button; CheckBox se crea con
     * JCheckBox, ej: private JCheckBox checkBox; RadioButton se crea con
     * JRadioButton, ej.: private JRadioButton radioButton; ButtonGroup se crea con
     * ButtonGroup, ej.: private ButtonGroup buttonGroup; ScrollPane se crea con
     * JScrollPane, ej.: private JScrollPane scrollPane;
     *
     * IMPORTANTE PONER NOMBRES IDENTIFICATIVOS SEGÚN FINALIDAD, ej.: Botón para
     * guardar eventos: private JButton saveEventButton;
     *
     * DECLARACIONES PARA UNA CONVERSACIÓN DE WHATSAPP (otro ejemplo más
     * descriptivo) private JPanel chatActualPanel; private JLabel chatLabel;
     * private JTextArea conversationArea; private JPanel messagePanel; private
     * JTextArea messageText; private JButton sendMessageButton;
     */

    // Constructor
    public ClientFrameV3(String title, int posX, int posY, Client client) {

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
        ImageIcon image = new ImageIcon("imagenes/MyReminderLogo.png");
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
        forgottenPasswordButton.setActionCommand("Forgotten password panel");
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

        forgottenPasswordPanel = new JPanel(new BorderLayout());

        centerforgottenPasswordPanel = new JPanel(new GridLayout(2, 1));
        emailforgottenPasswordPanel = new JPanel(new FlowLayout());
        emailLabelforgottenPasswordPanel = new JLabel("Email: ");
        emailtextforgottenPasswordPanel = new JTextField(10);
        emailforgottenPasswordPanel.add(emailLabelforgottenPasswordPanel);
        emailforgottenPasswordPanel.add(emailtextforgottenPasswordPanel);
        dniforgottenPasswordPanel = new JPanel(new FlowLayout());
        dniLabelforgottenPasswordPanel = new JLabel("DNI: ");
        dnitextforgottenPasswordPanel = new JPasswordField(10);
        dniforgottenPasswordPanel.add(dniLabelforgottenPasswordPanel);
        dniforgottenPasswordPanel.add(dnitextforgottenPasswordPanel);

        centerforgottenPasswordPanel.add(emailforgottenPasswordPanel);
        centerforgottenPasswordPanel.add(dniforgottenPasswordPanel);
        forgottenPasswordPanel.add(centerforgottenPasswordPanel, BorderLayout.CENTER);

        southforgottenPasswordPanel = new JPanel(new GridLayout(1, 2));
        acceptforgottenPasswordButton = new JButton("Confirm Password");
        acceptforgottenPasswordButton.setActionCommand("Confirm forgotten password");
        goBackforgottenPasswordButton = new JButton("Go back");
        goBackforgottenPasswordButton.setActionCommand("Go back sing in");

        southforgottenPasswordPanel.add(acceptforgottenPasswordButton);
        southforgottenPasswordPanel.add(goBackforgottenPasswordButton);
        forgottenPasswordPanel.add(southforgottenPasswordPanel, BorderLayout.SOUTH);

        // ------------------- Accept Forgotten Password Panel -------------

        confirmforgottenPasswordPanel = new JPanel(new BorderLayout());

        centerconfirmforgottenPasswordPanel = new JPanel(new GridLayout(2, 1));
        emailconfirmforgottenPasswordPanel = new JPanel(new FlowLayout());
        emailLabelconfirmforgottenPasswordPanel = new JLabel("Email/User: ");
        emailtextconfirmforgottenPasswordPanel = new JTextField(10);
        emailconfirmforgottenPasswordPanel.add(emailLabelconfirmforgottenPasswordPanel);
        emailconfirmforgottenPasswordPanel.add(emailtextconfirmforgottenPasswordPanel);
        dniconfirmforgottenPasswordPanel = new JPanel(new FlowLayout());
        dniLabelconfirmforgottenPasswordPanel = new JLabel("New password: ");
        dnitextconfirmforgottenPasswordPanel = new JPasswordField(10);
        dniconfirmforgottenPasswordPanel.add(dniLabelconfirmforgottenPasswordPanel);
        dniconfirmforgottenPasswordPanel.add(dnitextconfirmforgottenPasswordPanel);

        centerconfirmforgottenPasswordPanel.add(emailconfirmforgottenPasswordPanel);
        centerconfirmforgottenPasswordPanel.add(dniconfirmforgottenPasswordPanel);
        confirmforgottenPasswordPanel.add(centerconfirmforgottenPasswordPanel, BorderLayout.CENTER);

        southconfirmforgottenPasswordPanel = new JPanel(new GridLayout(1, 2));
        acceptconfirmforgottenPasswordButton = new JButton("Confirm new password");
        acceptconfirmforgottenPasswordButton.setActionCommand("new password panel");
        goBackconfirmforgottenPasswordButton = new JButton("Go back");
        goBackconfirmforgottenPasswordButton.setActionCommand("Go back forgotten password");

        southconfirmforgottenPasswordPanel.add(acceptconfirmforgottenPasswordButton);
        southconfirmforgottenPasswordPanel.add(goBackconfirmforgottenPasswordButton);
        confirmforgottenPasswordPanel.add(southconfirmforgottenPasswordPanel, BorderLayout.SOUTH);

        // -------------------------- Main Panel --------------------------
        // PRUEBA DE EVENTOS
        eventsPanel = new JPanel();
        eventsPanel.setLayout(new BoxLayout(eventsPanel, BoxLayout.Y_AXIS));
        eventsList = new ArrayDeque<>();
        eventDate = new ArrayDeque<>();
        eventTitle = new ArrayDeque<>();
        eventButton = new ArrayDeque<>();
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
        calendar = new JCalendar();
        buttonsMainPanel = new JPanel(new GridLayout(1, 3));
        createEventButton = new JButton("Create event");
        changeViewButton = new JButton("Change view");
        messagesButton = new JButton("Messages");
        buttonsMainPanel.add(createEventButton);
        buttonsMainPanel.add(changeViewButton);
        buttonsMainPanel.add(messagesButton);
        centerPanel.add(mainLabel, BorderLayout.NORTH);
        centerPanel.add(calendar, BorderLayout.CENTER); // muestra el calendario
        centerPanel.add(buttonsMainPanel, BorderLayout.SOUTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // --------
        eastPanel = new JPanel(new BorderLayout());
        infoPanel = new JPanel(new FlowLayout());
        profileButton = new JButton("Profile");
        logOutButton = new JButton("Log out");
        infoPanel.add(profileButton);
        infoPanel.add(logOutButton);
        // Prueba -- Añadir
        ArrayList<JPanel> mensajes2 = new ArrayList<>();
        // TERMINA prueba

        // Otra via para mostrar calendario
        auxiliarPanel = new JScrollPane();
        JPanel calendarTest = new JPanel(new BorderLayout());
        JButton auxPruebaText = new JButton("Prueba");

        calendarTest.add(auxPruebaText, BorderLayout.NORTH);
        calendarTest.add(auxiliarPanel, BorderLayout.CENTER);
        ArrayDeque<Button> selectDate = new ArrayDeque<>();

        // Con esto se cambia el tamaño del EAST
        eastPanel.setPreferredSize(new Dimension(240, 800));
        eastPanel.add(infoPanel, BorderLayout.NORTH);
        eastPanel.add(calendarTest, BorderLayout.CENTER);
        mainPanel.add(eastPanel, BorderLayout.EAST);
        // add(mainPanel);

        // Show dateCalendar

        /*
         * 
         * //---------------- PRUEBAS PARA MOSTRAR CALENDARIO ------------
         * //---------------- EJEMPLO PARA ORIENTARSE (Controller)------------- final
         * JCalendar calendar = new JCalendar();
         * calendar.getDayChooser().addPropertyChangeListener("day", new
         * PropertyChangeListener() {
         * 
         * @Override public void propertyChange(PropertyChangeEvent e) {
         * 
         * if(Calendar.AddJob!=1){ JOptionPane.showMessageDialog(null,"No Entry Found");
         * frame.dispose(); String date = EditDate(calendar.getDate().toString());
         * WorkDiary.WorkDiaryGui(date); }
         * 
         * if(Calendar.AddJob==1){ String date =
         * EditDate(calendar.getDate().toString()); Calendar.AddJob=0; frame.dispose();
         * WorkDiaryAddJob.WorkDiaryAddJobGui(CalReg, date); } } }); Calendar calendar =
         * new JCalendar(); JDayChooser dayChooser = calendar.getDayChooser();
         * dayChooser.setAlwaysFireDayProperty(true); // here is the key
         * dayChooser.addPropertyChangeListener("day", ...);
         * 
         * calendar.getDayChooser().addActionListener(new ActionListener(){ };
         * 
         */

        /*
         * //------------------------- PRUEBA 1 ----------- JCalendar calendar = new
         * JCalendar(); JDayChooser dayChooser = calendar.getDayChooser();
         * dayChooser.setAlwaysFireDayProperty(true);
         * dayChooser.addPropertyChangeListener("day", new PropertyChangeListener() {
         * 
         * @Override public void propertyChange(PropertyChangeEvent evt) { if
         * (calendar.getDayChooser() == evt.getSource()) panel2.setVisible(true);
         * 
         * } });
         * 
         */
        /*
         * showdatos = new JTextField("Introducir los datos"); showdatos.setBounds(20,
         * 20, 200, 40); panel2.add(showdatos);
         * 
         * JCalendar calendar = new JCalendar(); JDayChooser dayChooser =
         * calendar.getDayChooser(); dayChooser.setAlwaysFireDayProperty(true);
         * dayChooser.addPropertyChangeListener(new PropertyChangeListener() {
         * 
         * @Override public void propertyChange(PropertyChangeEvent evt) {
         * if(evt.getSource() == dayChooser){ panel2.setVisible(true); String año =
         * Integer.toString(calendario.getCalendar().get(java.util.Calendar.YEAR));
         * String mes =
         * Integer.toString(calendario.getCalendar().get(java.util.Calendar.MONTH) + 1);
         * String dia =
         * Integer.toString(calendario.getCalendar().get(java.util.Calendar.DATE));
         * showdatos.setText(dia + " de " + mes + " del " + año); } } });
         */

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
        createEventAlarmText = new JTextField("DD/MM/YYYY hh:mm", 10);

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

        // -------------------------- TERMINA NUEVO --------------------------

        // Creamos el controlador y activamos los botones
        controller = new ClientController(this, client);
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
     * @param myController controlador que tratará las pulsaciones de los botones
     */
    private void controller(ActionListener myController) {

        signInButton.addActionListener(myController);
        signUpButtonInitialPanel.addActionListener(myController);
        forgottenPasswordButton.addActionListener(myController);

        signUpButton.addActionListener(myController);
        goBackButton.addActionListener(myController);
        // -------------
        acceptforgottenPasswordButton.addActionListener(myController);
        goBackforgottenPasswordButton.addActionListener(myController);

        acceptconfirmforgottenPasswordButton.addActionListener(myController);
        goBackconfirmforgottenPasswordButton.addActionListener(myController);
        // -------------

        createEventButton.addActionListener(myController);
        changeViewButton.addActionListener(myController);
        messagesButton.addActionListener(myController);

        confirmCreateEvent.addActionListener(myController);
        cancelCreateEvent.addActionListener(myController);

        profileButton.addActionListener(myController);
        logOutButton.addActionListener(myController);

    }

    // -----------------------------------------------------------------------------------
    // -------------------------------- FUNCIONES CREADAS
    // --------------------------------
    // -----------------------------------------------------------------------------------

    public void showSignIn() {
        signInPanel.setVisible(false);
        signUpPanel.setVisible(false);
        mainPanel.setVisible(false);
        // -----------------
        forgottenPasswordPanel.setVisible(false);
        confirmforgottenPasswordPanel.setVisible(false);
        // -----------------
        remove(signUpPanel);
        remove(forgottenPasswordPanel);
        remove(confirmforgottenPasswordPanel);
        remove(mainPanel);
        add(signInPanel);
        signInPanel.setVisible(true);
    }

    public void showSignUp() {
        signInPanel.setVisible(false);
        signUpPanel.setVisible(false);
        // -----------------
        forgottenPasswordPanel.setVisible(false);
        confirmforgottenPasswordPanel.setVisible(false);
        // -----------------
        mainPanel.setVisible(false);
        remove(signInPanel);
        remove(forgottenPasswordPanel);
        remove(confirmforgottenPasswordPanel);
        remove(mainPanel);
        add(signUpPanel);
        signUpPanel.setVisible(true);
    }

    public void showForgottenPassword() {
        signInPanel.setVisible(false);
        signUpPanel.setVisible(false);
        // ------------------
        forgottenPasswordPanel.setVisible(false);
        confirmforgottenPasswordPanel.setVisible(false);
        // -------------------
        mainPanel.setVisible(false);
        remove(signInPanel);
        remove(signUpPanel);
        remove(confirmforgottenPasswordPanel);
        remove(mainPanel);
        add(forgottenPasswordPanel);
        forgottenPasswordPanel.setVisible(true);
    };

    public void showConfirmForgottenPassword() {
        signInPanel.setVisible(false);
        signUpPanel.setVisible(false);
        // -----------------
        forgottenPasswordPanel.setVisible(false);
        confirmforgottenPasswordPanel.setVisible(false);
        // -----------------
        mainPanel.setVisible(false);
        remove(signInPanel);
        remove(signUpPanel);
        remove(forgottenPasswordPanel);
        remove(mainPanel);
        add(confirmforgottenPasswordPanel);
        confirmforgottenPasswordPanel.setVisible(true);
    };

    public void showMainPanel() {
        signInPanel.setVisible(false);
        signUpPanel.setVisible(false);
        // ------------
        forgottenPasswordPanel.setVisible(false);
        confirmforgottenPasswordPanel.setVisible(false);
        // -----------
        mainPanel.setVisible(false);
        remove(signInPanel);
        remove(signUpPanel);
        remove(forgottenPasswordPanel);
        remove(confirmforgottenPasswordPanel);
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

    public void showMessagesPanel() {
        signInPanel.setVisible(false);
        signUpPanel.setVisible(false);
        mainPanel.setVisible(false);
        eastPanel.remove(createEventPanel);
        eastPanel.add(auxiliarPanel, BorderLayout.CENTER);
        mainPanel.setVisible(true);
    }

    public void addEvent(Event event) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        mainPanel.setVisible(false);
        eventsPanel.setVisible(false);
        // JPanel panel = new JPanel(new FlowLayout());
        JPanel panel = new JPanel(new GridLayout(1, 3));
        eventDate.addFirst(new JLabel(formatter.format(event.getDate())));
        eventTitle.addFirst(new JLabel(event.getTitle()));
        eventButton.addFirst(new JButton("More info"));
        // eventButton.getFirst().addActionListener(controller);
        panel.add(eventDate.peekFirst());
        panel.add(eventTitle.peekFirst());
        panel.add(eventButton.peekFirst());
        eventsList.addFirst(panel);
        eventsPanel.add(eventsList.peekFirst());
        eventsPanel.setVisible(true);
        mainPanel.setVisible(true);
    }

    public void changeView() {
        mainPanel.setVisible(false);
        mainPanel.setVisible(true);
    }

    // -----------------------------------------------------------------------------------
    // --------------------------- GETTERS, SETTERS AND CLEARS
    // ---------------------------
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
    // Falta añadir algunas Funciones de forgotten password panel y confirm
    // forgotten password panel

    public ClientController getController() { // Usado en las pruebas
        return controller;
    }

}
