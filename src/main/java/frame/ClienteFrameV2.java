package frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClienteFrameV2 extends JFrame implements ActionListener {

    // private Client client;

    // Posición de la ventana
    private final int posX;
    private final int posY;

    // Tamaño de la ventana
    private final int sizeX;
    private final int sizeY;

    // panel 1
    private JPanel panel1;
    private JLabel etiquetausuario;
    private JLabel etiquetacontraseña;
    private JLabel imagen;
    private JTextField usuariopanel1;
    private JTextField contraseñapanel1;
    private JButton botonrecuperarcontraseñapanel1;
    private JButton botonconfirmarpanel1;
    private JButton botonregistrarsepanel1;

    // panel 2
    private JPanel panel2;
    private JLabel nombrepanel2;
    private JLabel emailpanel2;
    private JLabel dnipanel2;
    private JLabel constraseñapanel2;
    private JTextField textonombrepanel2;
    private JTextField textoemailpanel2;
    private JTextField textodnipanel2;
    private JTextField textocontraseñapanel2;
    private JButton botonconfirmarregistropanel2;
    private JButton botonvolveratraspanel2;

    // panel 3
    private JPanel panel3;
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

    // panel 5
    private JPanel panel5;
    private JPanel panel5_1; // calendario
    private JPanel panel5_2; // textaArea
    private JLabel etiquetamyreminder;
    private JLabel etiquetacalendario;
    private JButton botoncrearevento;
    private JButton botoncambiarvista;
    private JButton botonlistadecontactos;
    private JButton botonperfil;
    private JButton botoncerrarsesion;

    // Constructor
    public ClienteFrameV2(String title, int posX, int posY/* , Client client */) {

        super(title);
        this.posX = posX;
        this.posY = posY;
        this.sizeX = 960;
        this.sizeY = 540;
        // this.client = client;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(this.sizeX, this.sizeY);
        setLayout(null);
        setLocation(this.posX, this.posY);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                CreateGUI();

            }

        });

    }

    // panel 1
    private void CreateGUI() {

        ////////////////////////////// Panel 1 /////////////////////////////////////
        panel1 = new JPanel();
        // panel1.setBackground(Color.orange);
        panel1.setBounds(0, 0, 960, 540);
        panel1.setLayout(null);
        panel1.setVisible(true);

        /*
         * // insertamos la imagen imagen = new JLabel(new ImageIcon("oso.jpg"));
         * imagen.setBounds(500,10,300,300); panel1.add(imagen);
         */

        // etiqueta 1 usuario
        etiquetausuario = new JLabel("usuario");
        etiquetausuario.setBounds(100, 70, 50, 50);
        panel1.add(etiquetausuario);

        // campo de texto
        usuariopanel1 = new JTextField();
        usuariopanel1.setBounds(100, 110, 300, 30);
        panel1.add(usuariopanel1);

        // etiqueta2 contraseña
        etiquetacontraseña = new JLabel("contraseña");
        etiquetacontraseña.setBounds(100, 140, 100, 50);
        panel1.add(etiquetacontraseña);

        // campo de texto 2
        contraseñapanel1 = new JTextField();
        contraseñapanel1.setBounds(100, 180, 300, 30);
        panel1.add(contraseñapanel1);

        // boton registrarse
        botonregistrarsepanel1 = new JButton("registrarse");
        botonregistrarsepanel1.setBounds(100, 290, 100, 40);
        panel1.add(botonregistrarsepanel1);
        botonregistrarsepanel1.addActionListener(this);

        // boton recuperar contraseña
        botonrecuperarcontraseñapanel1 = new JButton("recuperar contraseña");
        botonrecuperarcontraseñapanel1.setBounds(100, 230, 160, 40);
        panel1.add(botonrecuperarcontraseñapanel1);
        botonrecuperarcontraseñapanel1.addActionListener(this);

        // boton confirmar
        botonconfirmarpanel1 = new JButton("confirmar");
        botonconfirmarpanel1.setBounds(300, 230, 100, 40);
        panel1.add(botonconfirmarpanel1);
        botonconfirmarpanel1.addActionListener(this);

        add(panel1);

        //////////////////////////////////// Panel 2 //////////////////////////////

        panel2 = new JPanel();
        panel2.setBackground(Color.orange);
        panel2.setBounds(0, 0, 960, 540);
        panel2.setLayout(null);
        panel2.setVisible(false);

        // etiqueta 1 --> Nombre
        nombrepanel2 = new JLabel("Nombre");
        nombrepanel2.setBounds(300, 70, 50, 50);
        panel2.add(nombrepanel2);

        // campo de texto --> nombre
        textonombrepanel2 = new JTextField();
        textonombrepanel2.setBounds(300, 110, 300, 30);
        panel2.add(textonombrepanel2);

        // etiqueta2 --> email
        emailpanel2 = new JLabel("email");
        emailpanel2.setBounds(300, 140, 100, 50);
        panel2.add(emailpanel2);

        // campo de texto --> email
        textoemailpanel2 = new JTextField();
        textoemailpanel2.setBounds(300, 180, 300, 30);
        panel2.add(textoemailpanel2);

        // etiqueta3 --> dni
        dnipanel2 = new JLabel("dni");
        dnipanel2.setBounds(300, 210, 100, 50);
        panel2.add(dnipanel2);

        // campo de texto --> dni
        textodnipanel2 = new JTextField();
        textodnipanel2.setBounds(300, 250, 300, 30);
        panel2.add(textodnipanel2);

        // etiqueta4 --> contraseña
        constraseñapanel2 = new JLabel("contraseña");
        constraseñapanel2.setBounds(300, 280, 100, 50);
        panel2.add(constraseñapanel2);

        // campo de texto --> contraseña
        textocontraseñapanel2 = new JTextField();
        textocontraseñapanel2.setBounds(300, 320, 300, 30);
        panel2.add(textocontraseñapanel2);

        // boton confirmar registro
        botonconfirmarregistropanel2 = new JButton("confirmar registro");
        botonconfirmarregistropanel2.setBounds(300, 380, 160, 40);
        panel2.add(botonconfirmarregistropanel2);
        botonconfirmarregistropanel2.addActionListener(this);

        // boton volver atras
        botonvolveratraspanel2 = new JButton("volver atras");
        botonvolveratraspanel2.setBounds(20, 440, 120, 40);
        panel2.add(botonvolveratraspanel2);
        botonvolveratraspanel2.addActionListener(this);

        add(panel2);

        ////////////////////////// PANEL 3 ///////////////////////////////

        panel3 = new JPanel();
        panel3.setBackground(Color.orange);
        panel3.setBounds(0, 0, 960, 540);
        panel3.setLayout(null);
        panel3.setVisible(false);

        // etiqueta --> Email
        etiquetaemailpanel3 = new JLabel("Email");
        etiquetaemailpanel3.setBounds(300, 70, 50, 50);
        panel3.add(etiquetaemailpanel3);
        // campo de texto --> Email
        textoemailpanel3 = new JTextField();
        textoemailpanel3.setBounds(300, 110, 300, 30);
        panel3.add(textoemailpanel3);
        // etiqueta --> DNI
        etiquetadnipanel3 = new JLabel("DNI");
        etiquetadnipanel3.setBounds(300, 140, 100, 50);
        panel3.add(etiquetadnipanel3);
        // campo de texto --> DNI
        textodnipanel3 = new JTextField();
        textodnipanel3.setBounds(300, 180, 300, 30);
        panel3.add(textodnipanel3);

        // boton confirmar
        botonconfirmarpanel3 = new JButton("Confirmar");
        botonconfirmarpanel3.setBounds(380, 230, 160, 40);
        panel3.add(botonconfirmarpanel3);
        botonconfirmarpanel3.addActionListener(this);

        // boton volver atras
        botonvolveratraspanel3 = new JButton("volver atras");
        botonvolveratraspanel3.setBounds(20, 440, 150, 40);
        panel3.add(botonvolveratraspanel3);
        botonvolveratraspanel3.addActionListener(this);
        add(panel3);

        ////////////////////////// PANEL 4 ///////////////////

        panel4 = new JPanel();
        panel4.setBackground(Color.orange);
        panel4.setBounds(0, 0, 960, 540);
        panel4.setLayout(null);
        panel4.setVisible(false);

        // etiqueta --> Usuario
        etiquetausuariopanel4 = new JLabel("Usuario (No modificar el campo, ERROR!!)");
        etiquetausuariopanel4.setBounds(300, 70, 250, 50);
        panel4.add(etiquetausuariopanel4);
        // campo de texto --> Usuario
        textousuariopanel4 = new JTextField();
        textousuariopanel4.setBounds(300, 110, 300, 30);
        panel4.add(textousuariopanel4);
        // etiqueta --> Nueva contraseña
        etiquetacontraseñapanel4 = new JLabel("Nueva contraseña");
        etiquetacontraseñapanel4.setBounds(300, 140, 150, 50);
        panel4.add(etiquetacontraseñapanel4);
        // campo de texto --> Nueva contraseña
        textocontraseñapanel4 = new JTextField();
        textocontraseñapanel4.setBounds(300, 180, 300, 30);
        panel4.add(textocontraseñapanel4);

        // boton continuar
        botoncontinuarpanel4 = new JButton("Continuar");
        botoncontinuarpanel4.setBounds(380, 230, 160, 40);
        panel4.add(botoncontinuarpanel4);
        botoncontinuarpanel4.addActionListener(this);

        /*
         * //boton volver atras botonvolveratraspanel4 = new JButton("volver atras");
         * botonvolveratraspanel4.setBounds(20, 440, 150, 40);
         * panel4.add(botonvolveratraspanel4);
         */
        add(panel4);

        //////////////////////////////// Panel Cliente /////////////////////////////

        panel5 = new JPanel();
        panel5.setBackground(Color.orange);
        panel5.setBounds(0, 0, 960, 540);
        panel5.setLayout(null);
        panel5.setVisible(false);

        panel5_1 = new JPanel();
        panel5_1.setBackground(Color.lightGray);
        panel5_1.setBounds(40, 100, 550, 320);
        panel5_1.setLayout(null);
        panel5_1.setVisible(true);

        panel5_2 = new JPanel();
        panel5_2.setBackground(Color.lightGray);
        panel5_2.setBounds(630, 100, 300, 370);
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

        // boton Crear evento
        botoncrearevento = new JButton("Bandeja de entrada");
        botoncrearevento.setBounds(40, 430, 130, 40);
        panel5.add(botoncrearevento);
        /////// cambiar por bandeja de entrada

        // boton cambiar vista
        botoncambiarvista = new JButton("Cambiar Vista");
        botoncambiarvista.setBounds(240, 430, 130, 40);
        panel5.add(botoncambiarvista);

        // boton lista de contactos
        botonlistadecontactos = new JButton("Lista de conctactos");
        botonlistadecontactos.setBounds(430, 430, 160, 40);
        panel5.add(botonlistadecontactos);

        // boton perfil
        botonperfil = new JButton("Perfil");
        botonperfil.setBounds(730, 40, 70, 40);
        panel5.add(botonperfil);

        // boton cerrar sesion
        botoncerrarsesion = new JButton("Cerrar Sesión");
        botoncerrarsesion.setBounds(810, 40, 120, 40);
        panel5.add(botoncerrarsesion);
        botoncerrarsesion.addActionListener(this);

        // Creamos el controlador y activamos los botones
        // ControllerClient controller = new ControllerClient(this);
        // this.controller(controller);

        add(panel5);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botonregistrarsepanel1) {
            panel1.setVisible(false);
            panel2.setVisible(true);
        }

        if (e.getSource() == botonrecuperarcontraseñapanel1) {
            panel1.setVisible(false);
            panel3.setVisible(true);
        }
        if (e.getSource() == botonconfirmarpanel1) {
            panel5.setVisible(true);
            panel1.setVisible(false);
        }
        ////////// PANEL 2////////////
        if (e.getSource() == botonconfirmarregistropanel2) {
            panel1.setVisible(true);
            panel2.setVisible(false);
        }
        if (e.getSource() == botonvolveratraspanel2) {
            panel1.setVisible(true);
            panel2.setVisible(false);
        }
        //////// PANEL 3 //////////
        if (e.getSource() == botonconfirmarpanel3) {
            panel4.setVisible(true);
            panel3.setVisible(false);
        }
        if (e.getSource() == botonvolveratraspanel3) {
            panel1.setVisible(true);
            panel3.setVisible(false);
        }

        //////// PANEL 4 /////////
        if (e.getSource() == botoncontinuarpanel4) {
            panel1.setVisible(true);
            panel4.setVisible(false);
        }
        /////// PANEL 5 ////////
        if (e.getSource() == botoncerrarsesion) {
            panel1.setVisible(true);
            panel5.setVisible(false);
        }

    }

    /**
     * Activa los botones y permite que sean tratados por el controlador
     *
     * Se invocará en createGUI de la siguiente forma: ControllerClient controller =
     * new ControllerClient(this, client); this.controller(controller);
     *
     * En esta función activaremos los botones deseados de la siguiente forma:
     * button.addActionListener(myController);
     *
     * Repetimos la línea anterior para cada botón que deseamos activar
     *
     **/
    /**
     * // Activamos los botones private void controller(ActionListener myController)
     * { /////////////////////// PANEL 1 ///////////////////
     * botonregistrarsepanel1.addActionListener(myController);
     * botonrecuperarcontraseñapanel1.addActionListener(myController);
     * botonconfirmarpanel1.addActionListener(myController);
     * 
     * ///////////////////// PANEL 2 ///////////////////
     * botonconfirmarregistropanel2.addActionListener(myController);
     * botonvolveratraspanel2.addActionListener(myController);
     * 
     * ///////////////////// PANEL 3 ///////////////////
     * botonconfirmarpanel3.addActionListener(myController);
     * botonvolveratraspanel3.addActionListener(myController);
     * 
     * ///////////////////// PANEL 4 ///////////////////
     * botoncontinuarpanel4.addActionListener(myController); }
     */

    // -----------------------------------------------------------------------------------
    // -------------------------------- FUNCIONES CREADAS
    // --------------------------------
    // -----------------------------------------------------------------------------------

    /***
     * ///////////////////////////////////// PANEL 1 //////////////// public
     * JTextField getUsuariopanel1() { return usuariopanel1; }
     * 
     * public void setUsuariopanel1(JTextField usuariopanel1) { this.usuariopanel1 =
     * usuariopanel1; }
     * 
     * public JTextField getContraseñapanel1() { return contraseñapanel1; }
     * 
     * public void setContraseñapanel1(JTextField contraseñapanel1) {
     * this.contraseñapanel1 = contraseñapanel1; } /////////////////////////////////
     * PANEL 2 //////////////////
     * 
     * 
     * public JLabel getConstraseñapanel2() { return constraseñapanel2; }
     * 
     * public void setConstraseñapanel2(JLabel constraseñapanel2) {
     * this.constraseñapanel2 = constraseñapanel2; }
     * 
     * public JTextField getTextonombrepanel2() { return textonombrepanel2; }
     * 
     * public void setTextonombrepanel2(JTextField textonombrepanel2) {
     * this.textonombrepanel2 = textonombrepanel2; }
     * 
     * public JTextField getTextoemailpanel2() { return textoemailpanel2; }
     * 
     * public void setTextoemailpanel2(JTextField textoemailpanel2) {
     * this.textoemailpanel2 = textoemailpanel2; }
     * 
     * public JTextField getTextodnipanel2() { return textodnipanel2; }
     * 
     * public void setTextodnipanel2(JTextField textodnipanel2) {
     * this.textodnipanel2 = textodnipanel2; }
     * 
     * public JTextField getTextocontraseñapanel2() { return textocontraseñapanel2;
     * }
     * 
     * public void setTextocontraseñapanel2(JTextField textocontraseñapanel2) {
     * this.textocontraseñapanel2 = textocontraseñapanel2; } //////////////////////
     * PANEL 3 ///////////////
     * 
     * 
     * public JTextField getTextoemailpanel3() { return textoemailpanel3; }
     * 
     * public void setTextoemailpanel3(JTextField textoemailpanel3) {
     * this.textoemailpanel3 = textoemailpanel3; }
     * 
     * public JTextField getTextodnipanel3() { return textodnipanel3; }
     * 
     * public void setTextodnipanel3(JTextField textodnipanel3) {
     * this.textodnipanel3 = textodnipanel3; } ///////////////////// PANEL 4
     * //////////////
     * 
     * 
     * public JTextField getTextousuariopanel4() { return textousuariopanel4; }
     * 
     * public void setTextousuariopanel4(JTextField textousuariopanel4) {
     * this.textousuariopanel4 = textousuariopanel4; }
     * 
     * public JTextField getTextocontraseñapanel4() { return textocontraseñapanel4;
     * }
     * 
     * public void setTextocontraseñapanel4(JTextField textocontraseñapanel4) {
     * this.textocontraseñapanel4 = textocontraseñapanel4; }
     * ////////////////////////////////////////////////////////////////////////////////////->>>>>>>>>>>>>//////////////
     * 
     * 
     * /** ////////// PANEL 1 ///////////// public void actionPerformed(ActionEvent
     * e) { if(e.getSource() == botonregistrarsepanel1){ panel1.setVisible(false);
     * panel2.setVisible(true); }
     * 
     * if(e.getSource() == botonrecuperarcontraseñapanel1){
     * panel1.setVisible(false); panel3.setVisible(true); } if(e.getSource() ==
     * botonconfirmarpanel1){ panel5.setVisible(true); panel1.setVisible(false); }
     * ////////// PANEL 2//////////// if(e.getSource() ==
     * botonconfirmarregistropanel2){ panel1.setVisible(true);
     * panel2.setVisible(false); } if(e.getSource() == botonvolveratraspanel2) {
     * panel1.setVisible(true); panel2.setVisible(false); } //////// PANEL 3
     * ////////// if(e.getSource() == botonconfirmarpanel3){
     * panel4.setVisible(true); panel3.setVisible(false); } if(e.getSource() ==
     * botonvolveratraspanel3){ panel1.setVisible(true); panel3.setVisible(false); }
     * 
     * //////// PANEL 4 ///////// if(e.getSource() == botoncontinuarpanel4){
     * panel1.setVisible(true); panel4.setVisible(false); } /////// PANEL 5 ////////
     * if(e.getSource() == botoncerrarsesion){ panel1.setVisible(true);
     * panel5.setVisible(false); } }
     **/
}
