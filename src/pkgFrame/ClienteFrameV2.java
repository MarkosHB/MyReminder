package FrameV2;

import javax.swing.*;
import java.awt.*;


public class ClienteFrameV2 extends JFrame {

    //private Client client;

    // Posición de la ventana
    private final int posX;
    private final int posY;

    // Tamaño de la ventana
    private final int sizeX;
    private final int sizeY;


    //  panel 1
    //private JPanel panel1;
    private JPanel panel1;
    private JLabel etiquetausuario;
    private JLabel etiquetacontraseña;
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



    // Constructor
    public ClienteFrameV2(String title, int posX, int posY/*, Client client*/) {

        super(title);
        this.posX = posX;
        this.posY = posY;
        this.sizeX = 960;
        this.sizeY = 540;
        //this.client = client;

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
        ////////////////////////////// Panel 1  /////////////////////////////////////
        panel1 = new JPanel();
        panel1.setBackground(Color.orange);
        panel1.setBounds(0,0,960,540);
        panel1.setLayout(null);
        panel1.setVisible(false);


        //  etiqueta 1 usuario
        etiquetausuario = new JLabel("usuario");
        etiquetausuario.setBounds(300, 70, 50, 50);
        panel1.add(etiquetausuario);

        // campo de texto
        usuariopanel1 = new JTextField();
        usuariopanel1.setBounds(300, 110, 300, 30);
        panel1.add(usuariopanel1);

        //  etiqueta2 contraseña
        etiquetacontraseña = new JLabel("contraseña");
        etiquetacontraseña.setBounds(300, 140, 100, 50);
        panel1.add(etiquetacontraseña);

        // campo de texto 2
        contraseñapanel1 = new JTextField();
        contraseñapanel1.setBounds(300, 180, 300, 30);
        panel1.add(contraseñapanel1);

        //boton 1
        botonrecuperarcontraseñapanel1 = new JButton("registrarse");
        botonrecuperarcontraseñapanel1.setBounds(300, 290, 100, 40);
        panel1.add(botonrecuperarcontraseñapanel1);


        //boton 2
        botonconfirmarpanel1 = new JButton("recuperar contraseña");
        botonconfirmarpanel1.setBounds(300, 230, 160, 40);
        panel1.add(botonconfirmarpanel1);

        //boton 3
        botonregistrarsepanel1 = new JButton("confirmar");
        botonregistrarsepanel1.setBounds(500, 230, 100, 40);

        panel1.add(botonregistrarsepanel1);

        add(panel1);
        ////////////////////////////////////    Panel 2     //////////////////////////////

        panel2 = new JPanel();
        panel2.setBackground(Color.orange);
        panel2.setBounds(0,0,960,540);
        panel2.setLayout(null);
        panel2.setVisible(true);


        //  etiqueta 1 --> Nombre
        nombrepanel2 = new JLabel("Nombre");
        nombrepanel2.setBounds(300, 70, 50, 50);
        panel2.add(nombrepanel2);

        // campo de texto --> nombre
        textonombrepanel2 = new JTextField();
        textonombrepanel2.setBounds(300, 110, 300, 30);
        panel2.add(textonombrepanel2);

        //  etiqueta2 --> email
        emailpanel2 = new JLabel("email");
        emailpanel2.setBounds(300, 140, 100, 50);
        panel2.add(emailpanel2);

        // campo de texto  --> email
        textoemailpanel2 = new JTextField();
        textoemailpanel2.setBounds(300, 180, 300, 30);
        panel2.add(textoemailpanel2);

        //  etiqueta3 --> dni
        dnipanel2 = new JLabel("dni");
        dnipanel2.setBounds(300, 210, 100, 50);
        panel2.add(dnipanel2);

        // campo de texto --> dni
        textodnipanel2 = new JTextField();
        textodnipanel2.setBounds(300, 250, 300, 30);
        panel2.add(textodnipanel2);

        //  etiqueta4 --> contraseña
        constraseñapanel2 = new JLabel("contraseña");
        constraseñapanel2.setBounds(300, 280, 100, 50);
        panel2.add(constraseñapanel2);

        // campo de texto --> contraseña
        textocontraseñapanel2 = new JTextField();
        textocontraseñapanel2.setBounds(300, 320, 300, 30);
        panel2.add(textocontraseñapanel2);

        //boton 4
        botonconfirmarregistropanel2 = new JButton("confirmar registro");
        botonconfirmarregistropanel2.setBounds(300, 380, 160, 40);
        panel2.add(botonconfirmarregistropanel2);

        //boton 3
        botonvolveratraspanel2 = new JButton("volver atras");
        botonvolveratraspanel2.setBounds(20, 440, 120, 40);
        panel2.add(botonvolveratraspanel2);

        add(panel2);

        //////////////////////////  PANEL 3 ///////////////////////////////


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

        //boton 5
        botonconfirmarpanel3 = new JButton("Confirmar");
        botonconfirmarpanel3.setBounds(380, 230, 160, 40);
        panel3.add(botonconfirmarpanel3);
        //boton 6
        botonvolveratraspanel3 = new JButton("volver atras");
        botonvolveratraspanel3.setBounds(20, 440, 150, 40);
        panel3.add(botonvolveratraspanel3);
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

        //boton 7
        botoncontinuarpanel4 = new JButton("Continuar");
        botoncontinuarpanel4.setBounds(380, 230, 160, 40);
        panel4.add(botoncontinuarpanel4);
        //boton 8
        botonvolveratraspanel4 = new JButton("volver atras");
        botonvolveratraspanel4.setBounds(20, 440, 150, 40);
        panel4.add(botonvolveratraspanel4);
        add(panel4);



    }
    /**
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == botonregistrarsepanel1){
            panel1.setVisible(true);
            panel2.setVisible(false);
        }
        if(e.getSource() == botonvolveratraspanel2){
            panel1.setVisible(false);
            panel2.setVisible(true);
        }
    }
    **/
}

