package frame;

import client.Client;
import controller.ClientController;
import utils.Event;
import utils.Message;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.PriorityBlockingQueue;

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

    private JPanel eventsPanel;

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

    private JPanel inviteUserPanel;
    private JButton inviteUserButton;

    private JPanel createEventButtons;
    private JButton confirmCreateEvent;
    private JButton cancelCreateEvent;

    private JPanel showEventPanel;

    private JLabel showEventTitleLabel;
    private JTextField showEventTitleText;
    private JLabel showEventDescriptionLabel;
    private JTextArea showEventDescriptionText;
    private JScrollPane showEventDescriptionScroll;
    private JLabel showEventDateLabel;
    private JTextField showEventDateText;
    private JLabel showEventAlarmLabel;
    private JTextField showEventAlarmText;

    private JPanel showEventButtons;
    private JButton confirmUpdateEvent;
    private JButton cancelUpdateEvent;

    private JPanel adminPanel;
    private JPanel usersPanel;
    private JScrollPane usersScrollPanel;

    private JPanel listContactsPanel;
    private JPanel contactsPanel;
    private JScrollPane contactsScrollPanel;
    private JPanel addContactPanel;
    private JTextField addContactText;
    private JButton addContactButton;

    private JPanel invitePanel;
    private JPanel inviteContactsPanel;
    private JScrollPane inviteContactsScrollPanel;
    private JButton inviteContactButton;

    private JPanel inboxPanel;
    private JPanel messagesPanel;
    private JScrollPane messagesScrollPanel;

    ArrayList<JCheckBox> guestsButtons = new ArrayList<>();
    private Event actualEvent;

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

        setLayout(new BorderLayout());

        // -------------------------- Sign In Panel --------------------------
        signInPanel = new JPanel(new BorderLayout());

        ImageIcon image = new ImageIcon("images/myReminder.png");
        Icon icon = new ImageIcon(image.getImage().getScaledInstance(260, 260, Image.SCALE_DEFAULT));
        signInLabel = new JLabel(icon);
        signInPanel.add(signInLabel, BorderLayout.NORTH);

        centerSignInPanel = new JPanel(new GridLayout(2, 1));
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

        /* --------------------------- UNIFICAR EN UNO --------------------------- */
        // -------------------------- Forgotten Password Panel --------------------------
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
        /* --------------------------- UNIFICAR EN UNO --------------------------- */

        // -------------------------- Main Panel --------------------------
        eventsPanel = new JPanel();
        eventsPanel.setLayout(new BoxLayout(eventsPanel, BoxLayout.Y_AXIS));

        mainPanel = new JPanel(new BorderLayout());

        centerPanel = new JPanel(new BorderLayout());
        Icon icon3 = new ImageIcon(image.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));
        mainLabel = new JLabel(icon3);
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
        auxiliarPanel = new JScrollPane();
        eastPanel.setPreferredSize(new Dimension(240, 800));
        eastPanel.add(infoPanel, BorderLayout.NORTH);
        eastPanel.add(auxiliarPanel, BorderLayout.CENTER);
        mainPanel.add(eastPanel, BorderLayout.EAST);

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
        createEventDateText = new JTextField("30/06/2021 10:00", 10);
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

        // Show Event Details Panel
        showEventPanel = new JPanel();
        showEventPanel.setLayout(new BoxLayout(showEventPanel, BoxLayout.Y_AXIS));

        showEventTitleLabel = new JLabel("Title");
        showEventTitleText = new JTextField(10);
        showEventDescriptionLabel = new JLabel("Description");
        showEventDescriptionText = new JTextArea("", 30, 10);
        showEventDescriptionScroll = new JScrollPane(showEventDescriptionText);
        showEventDateLabel = new JLabel("Date");
        showEventDateText = new JTextField("DD/MM/YYYY hh:mm", 10);
        showEventAlarmLabel = new JLabel("Alarm");
        showEventAlarmText = new JTextField("DD/MM/YYYY hh:mm", 10);

        inviteUserPanel = new JPanel(new GridLayout(1, 1));
        inviteUserButton = new JButton("Invite user");
        //inviteUserPanel.add(inviteUserButton);

        showEventButtons = new JPanel(new GridLayout(1, 2));
        confirmUpdateEvent = new JButton("Update");
        confirmUpdateEvent.setActionCommand("Update event");
        cancelUpdateEvent = new JButton("Cancel");
        cancelUpdateEvent.setActionCommand("Cancel update event");
        showEventButtons.add(confirmUpdateEvent);
        showEventButtons.add(cancelUpdateEvent);

        showEventPanel.add(showEventTitleLabel);
        showEventPanel.add(showEventTitleText);
        showEventPanel.add(showEventDescriptionLabel);
        showEventPanel.add(showEventDescriptionScroll);
        showEventPanel.add(showEventDateLabel);
        showEventPanel.add(showEventDateText);
        showEventPanel.add(showEventAlarmLabel);
        showEventPanel.add(showEventAlarmText);
        showEventPanel.add(inviteUserPanel);
        showEventPanel.add(showEventButtons);

        adminPanel = new JPanel(new BorderLayout());
        adminPanel.add(new JLabel("Users:"), BorderLayout.NORTH);

        usersPanel = new JPanel();
        usersPanel.setLayout(new BoxLayout(usersPanel, BoxLayout.Y_AXIS));
        usersScrollPanel = new JScrollPane(usersPanel);
        adminPanel.add(usersScrollPanel, BorderLayout.CENTER);

        listContactsPanel = new JPanel(new BorderLayout());
        listContactsPanel.add(new JLabel("Contacts:"), BorderLayout.NORTH);

        contactsPanel = new JPanel();
        contactsPanel.setLayout(new BoxLayout(contactsPanel, BoxLayout.Y_AXIS));
        contactsScrollPanel = new JScrollPane(contactsPanel);
        listContactsPanel.add(contactsScrollPanel, BorderLayout.CENTER);

        addContactPanel = new JPanel(new GridLayout(2, 1));
        addContactText = new JTextField("");
        addContactButton = new JButton("Add contact");

        addContactPanel.add(addContactText);
        addContactPanel.add(addContactButton);
        listContactsPanel.add(addContactPanel, BorderLayout.SOUTH);

        invitePanel = new JPanel(new BorderLayout());
        invitePanel.add(new JLabel("Contacts:"), BorderLayout.NORTH);

        inviteContactsPanel = new JPanel();
        inviteContactsPanel.setLayout(new BoxLayout(inviteContactsPanel, BoxLayout.Y_AXIS));
        inviteContactsScrollPanel = new JScrollPane(inviteContactsPanel);
        invitePanel.add(inviteContactsScrollPanel, BorderLayout.CENTER);

        inviteContactButton = new JButton("Invite contact");
        invitePanel.add(inviteContactButton, BorderLayout.SOUTH);

        inboxPanel = new JPanel(new BorderLayout());
        inboxPanel.add(new JLabel("Invitations and alerts:"), BorderLayout.NORTH);

        messagesPanel = new JPanel();
        messagesPanel.setLayout(new BoxLayout(messagesPanel, BoxLayout.Y_AXIS));
        messagesScrollPanel = new JScrollPane(messagesPanel);
        inboxPanel.add(messagesScrollPanel, BorderLayout.CENTER);

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

        acceptforgottenPasswordButton.addActionListener(myController);
        goBackforgottenPasswordButton.addActionListener(myController);

        acceptconfirmforgottenPasswordButton.addActionListener(myController);
        goBackconfirmforgottenPasswordButton.addActionListener(myController);

        confirmUpdateEvent.addActionListener(myController);
        cancelUpdateEvent.addActionListener(myController);

        inviteUserButton.addActionListener(myController);
        inviteContactButton.addActionListener(myController);

        addContactButton.addActionListener(myController);

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
        userTextSignInPanel.setText("");
        passwordTextSignInPanel.setText("");
        add(signInPanel);
        signInPanel.setVisible(true);
    }

    public void showSignUp() {
        signInPanel.setVisible(false);
        signUpPanel.setVisible(false);
        mainPanel.setVisible(false);
        remove(signInPanel);
        remove(mainPanel);
        mailTextSignUpPanel.setText("");
        userTextSignUpPanel.setText("");
        passwordTextSignUpPanel.setText("");
        dniTextSignUpPanel.setText("");
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
        } else {
            infoPanel.add(new JLabel(client.getUser().getName()));
        }
        infoPanel.add(logOutButton);
        remove(signInPanel);
        remove(signUpPanel);
        add(mainPanel);
        mainPanel.setVisible(true);
    }

    public void showCreateEventPanel() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        mainPanel.setVisible(false);
        auxiliarPanel.setVisible(false);
        adminPanel.setVisible(false);
        inboxPanel.setVisible(false);
        showEventPanel.setVisible(false);
        listContactsPanel.setVisible(false);
        eastPanel.remove(auxiliarPanel);
        eastPanel.remove(adminPanel);
        eastPanel.remove(inboxPanel);
        eastPanel.remove(showEventPanel);
        eastPanel.remove(listContactsPanel);
        createEventTitleText.setText("");
        createEventDescriptionText.setText("");
        createEventDateText.setText(formatter.format(new Date()));
        createEventAlarmText.setText(formatter.format(new Date()));
        eastPanel.add(createEventPanel, BorderLayout.CENTER);
        createEventPanel.setVisible(true);
        mainPanel.setVisible(true);
    }

    public void showEventDetails(Event event) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        actualEvent = event;
        mainPanel.setVisible(false);
        auxiliarPanel.setVisible(false);
        adminPanel.setVisible(false);
        inboxPanel.setVisible(false);
        createEventPanel.setVisible(false);
        listContactsPanel.setVisible(false);
        eastPanel.remove(auxiliarPanel);
        eastPanel.remove(adminPanel);
        eastPanel.remove(inboxPanel);
        eastPanel.remove(createEventPanel);
        eastPanel.remove(listContactsPanel);
        confirmUpdateEvent.setActionCommand("Update event: " + event.getId());
        showEventTitleText.setText(event.getTitle());
        showEventDescriptionText.setText(event.getDescription());
        try {
            showEventDateText.setText(formatter.format(event.getDate()));
        } catch (NullPointerException e) {
            showEventDateText.setText("");
        }
        try {
            showEventAlarmText.setText(formatter.format(event.getAlarm()));
        } catch (NullPointerException e) {
            showEventAlarmText.setText("");
        }
        eastPanel.add(showEventPanel, BorderLayout.CENTER);
        inviteUserPanel.remove(inviteUserButton);
        showEventButtons.remove(confirmUpdateEvent);
        showEventButtons.remove(cancelUpdateEvent);
        if (event.getOwner().equals(client.getUser().getName())) {
            inviteUserPanel.add(inviteUserButton);
            showEventTitleText.setEditable(true);
            showEventDescriptionText.setEditable(true);
            showEventDateText.setEditable(true);
            showEventAlarmText.setEditable(true);
            showEventButtons.setLayout(new GridLayout(1, 2));
            showEventButtons.add(confirmUpdateEvent);
            cancelUpdateEvent.setText("Cancel");
            showEventButtons.add(cancelUpdateEvent);
        } else {
            showEventTitleText.setEditable(false);
            showEventDescriptionText.setEditable(false);
            showEventDateText.setEditable(false);
            showEventAlarmText.setEditable(false);
            showEventButtons.setLayout(new GridLayout(1, 1));
            cancelUpdateEvent.setText("Continue");
            showEventButtons.add(cancelUpdateEvent);
        }
        showEventPanel.setVisible(true);
        mainPanel.setVisible(true);
    }

    public void showMessages() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        mainPanel.setVisible(false);
        auxiliarPanel.setVisible(false);
        createEventPanel.setVisible(false);
        adminPanel.setVisible(false);
        showEventPanel.setVisible(false);
        listContactsPanel.setVisible(false);
        invitePanel.setVisible(false);
        eastPanel.remove(auxiliarPanel);
        eastPanel.remove(createEventPanel);
        eastPanel.remove(adminPanel);
        eastPanel.remove(showEventPanel);
        eastPanel.remove(listContactsPanel);
        eastPanel.remove(invitePanel);
        messagesPanel.removeAll();
        JPanel panel;
        PriorityBlockingQueue<Message> messages = new PriorityBlockingQueue<>(client.getUser().getMessages());
        while (messages.size() > 0) {
            Message message = messages.poll();
            panel = new JPanel(new FlowLayout(FlowLayout.TRAILING, 5, 5));
            panel.add(new JLabel(formatter.format(message.getDate())));
            panel.add(new JLabel(message.getMessage()));
            messagesPanel.add(panel);
        }

        eastPanel.add(inboxPanel, BorderLayout.CENTER);
        inboxPanel.setVisible(true);
        mainPanel.setVisible(true);
    }

    public void showAdminFrame() {
        mainPanel.setVisible(false);
        auxiliarPanel.setVisible(false);
        createEventPanel.setVisible(false);
        inboxPanel.setVisible(false);
        showEventPanel.setVisible(false);
        listContactsPanel.setVisible(false);
        eastPanel.remove(auxiliarPanel);
        eastPanel.remove(createEventPanel);
        eastPanel.remove(inboxPanel);
        eastPanel.remove(showEventPanel);
        eastPanel.remove(listContactsPanel);
        usersPanel.removeAll();
        JPanel panel;
        PriorityBlockingQueue<String> users = new PriorityBlockingQueue<>(client.getUsers().keySet());
        while (users.size() > 0) {
            String user = users.poll();
            panel = new JPanel(new FlowLayout(FlowLayout.TRAILING, 5, 5));
            panel.add(new JLabel(user));
            JButton button = new JButton("Delete");
            button.setActionCommand("Delete user: " + user);
            button.addActionListener(controller);
            panel.add(button);
            usersPanel.add(panel);
        }

        eastPanel.add(adminPanel, BorderLayout.CENTER);
        adminPanel.setVisible(true);
        mainPanel.setVisible(true);
    }

    public void showEvents() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        mainPanel.setVisible(false);
        eventsPanel.setVisible(false);
        eventsPanel.removeAll();
        JPanel panel;
        PriorityBlockingQueue<Event> events = new PriorityBlockingQueue<>(client.getUser().getEvents().values());
        while (events.size() > 0) {
            Event event = events.poll();
            panel = new JPanel(new FlowLayout(FlowLayout.TRAILING, 20, 5));
            panel.add(new JLabel(formatter.format(event.getDate())));
            panel.add(new JLabel(event.getTitle()));
            JButton button = new JButton("More info");
            button.setActionCommand("More info: " + event.getId());
            button.addActionListener(controller);
            panel.add(button);
            if (event.getOwner().equals(client.getUser().getName())) {
                button = new JButton("Delete");
                button.setActionCommand("Delete event: " + event.getId());
                button.addActionListener(controller);
                panel.add(button);
            }
            eventsPanel.add(panel);
        }

        eventsPanel.setVisible(true);
        mainPanel.setVisible(true);
    }

    /*
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
    }
    */

    public void showForgottenPassword() {
        signInPanel.setVisible(false);
        remove(signInPanel);
        add(forgottenPasswordPanel);
        forgottenPasswordPanel.setVisible(true);
    }

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
    }

    public void showListContacts() {
        mainPanel.setVisible(false);
        auxiliarPanel.setVisible(false);
        createEventPanel.setVisible(false);
        inboxPanel.setVisible(false);
        showEventPanel.setVisible(false);
        adminPanel.setVisible(false);
        invitePanel.setVisible(false);
        eastPanel.remove(auxiliarPanel);
        eastPanel.remove(createEventPanel);
        eastPanel.remove(inboxPanel);
        eastPanel.remove(showEventPanel);
        eastPanel.remove(adminPanel);
        eastPanel.remove(invitePanel);
        contactsPanel.removeAll();
        JPanel panel;
        PriorityBlockingQueue<String> contacts = new PriorityBlockingQueue<>(client.getUser().getContacts());
        while (contacts.size() > 0) {
            String contact = contacts.poll();
            panel = new JPanel(new FlowLayout(FlowLayout.TRAILING, 5, 5));
            panel.add(new JLabel(contact));
            JButton button = new JButton("Delete");
            button.setActionCommand("Delete contact: " + contact);
            button.addActionListener(controller);
            panel.add(button);
            contactsPanel.add(panel);
        }

        eastPanel.add(listContactsPanel, BorderLayout.CENTER);
        addContactText.setText("");
        listContactsPanel.setVisible(true);
        mainPanel.setVisible(true);
    }

    public void showInvitationPanel() {
        mainPanel.setVisible(false);
        auxiliarPanel.setVisible(false);
        createEventPanel.setVisible(false);
        inboxPanel.setVisible(false);
        showEventPanel.setVisible(false);
        adminPanel.setVisible(false);
        invitePanel.setVisible(true);
        eastPanel.remove(auxiliarPanel);
        eastPanel.remove(createEventPanel);
        eastPanel.remove(inboxPanel);
        eastPanel.remove(showEventPanel);
        eastPanel.remove(adminPanel);
        eastPanel.remove(listContactsPanel);
        inviteContactsPanel.removeAll();
        guestsButtons.clear();
        JPanel panel;
        PriorityBlockingQueue<String> contacts = new PriorityBlockingQueue<>(client.getUser().getContacts());
        while (contacts.size() > 0) {
            String contact = contacts.poll();
            panel = new JPanel(new FlowLayout(FlowLayout.TRAILING, 5, 5));
            // panel.add(new JLabel(contact));
            JCheckBox button = new JCheckBox(contact);
            panel.add(button);
            guestsButtons.add(button);
            inviteContactsPanel.add(panel);
        }

        eastPanel.add(invitePanel, BorderLayout.CENTER);
        addContactText.setText("");
        invitePanel.setVisible(true);
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

    public String getAddContactText() {
        return addContactText.getText();
    }

    public Date getCreateEventDate() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return formatter.parse(createEventDateText.getText());
    }

    public Date getCreateEventAlarm() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return formatter.parse(createEventAlarmText.getText());
    }

    public Event getActualEvent() {
        return actualEvent;
    }

    public ArrayList<JCheckBox> getGuestsButtons() {
        return guestsButtons;
    }

    public String getShowEventTitleText() {
        return showEventTitleText.getText();
    }

    public String getShowEventDescriptionText() {
        return showEventTitleText.getText();
    }

    public Date getShowEventDate() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return formatter.parse(showEventDateText.getText());
    }

    public Date getShowEventAlarm() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return formatter.parse(showEventAlarmText.getText());
    }

}
