package Myreminderv2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class FrameInicial extends JFrame {

    //private Client client;

    // Posición de la ventana
    private final int posX;
    private final int posY;

    // Tamaño de la ventana
    private final int sizeX;
    private final int sizeY;


    //  panel 1
    //private JPanel panel1;

    private JLabel etiquetausuario;
    private JLabel etiquetacontraseña;
    private JTextField usuariopanel1;
    private JTextField contraseñapanel1;
    private JButton botonrecuperarcontraseñapanel1;
    private JButton botonconfirmarpanel1;
    private JButton botonregistrarsepanel1;


    // panel 2
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
    private JLabel etiquetaemailpanel3;
    private JLabel etiquetadnipanel3;
    private JTextField textoemailpanel3;
    private JTextField textodnipanel3;
    private JButton botonconfirmarpanel3;
    private JButton botonvolveratraspanel3;


    // panel 4
    private JTextField textousuariopanel4;
    private JTextField textocontraseñapanel4;
    private JLabel etiquetausuariopanel4;
    private JLabel etiquetacontraseñapanel4;
    private JButton botoncontinuarpanel4;
    private JButton botonvolveratraspanel4;


    // Constructor
    public FrameInicial(String title, int posX, int posY/*, Client client*/) {

        super(title);
        this.posX = posX;
        this.posY = posY;
        this.sizeX = 960;
        this.sizeY = 540;
        //this.client = client;


        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                pagina1();

            }
        });

    }
    // panel 1

    private void pagina1(){

        Container v = this.getContentPane();
        this.getRootPane().setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));
        v.setBackground(Color.gray);

        //  etiqueta 1 usuario
        etiquetausuario = new JLabel("usuario");
        etiquetausuario.setBounds(300,70,50,50);
        add(etiquetausuario);
        // campo de texto
        usuariopanel1 = new JTextField();
        usuariopanel1.setBounds(300,110,300,30);
        add(usuariopanel1);
        //  etiqueta2 contraseña
        etiquetacontraseña = new JLabel("contraseña");
        etiquetacontraseña.setBounds(300,140,100,50);
        add(etiquetacontraseña);
        // campo de texto 2
        contraseñapanel1 = new JTextField();
        contraseñapanel1.setBounds(300,180,300,30);
        add(contraseñapanel1);
        //boton 1
        botonrecuperarcontraseñapanel1 = new JButton("registrarse");
        botonrecuperarcontraseñapanel1.setBounds(300,290,100,40);
        add(botonrecuperarcontraseñapanel1);
        //boton 2
        botonconfirmarpanel1 = new JButton("recuperar contraseña");
        botonconfirmarpanel1.setBounds(300,230,160,40);
        add(botonconfirmarpanel1);
        //boton 3
        botonregistrarsepanel1 = new JButton("confirmar");
        botonregistrarsepanel1.setBounds(500,230,100,40);
        add(botonregistrarsepanel1);

        ControllerClient controller = new ControllerClient(this/*, client*/);
        this.controller(controller);

        v.setLayout(new BorderLayout());
        setSize(this.sizeX, this.sizeY);
        setLocation(this.posX, this.posY);
        setVisible(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }
    private void pagina2(){


        Container v = this.getContentPane();
        this.getRootPane().setBorder(BorderFactory.createMatteBorder(4,4,4,4, Color.BLACK));
        v.setBackground(Color.orange);

        //  etiqueta 1 --> Nombre
        nombrepanel2 = new JLabel("Nombre");
        nombrepanel2.setBounds(300,70,50,50);
        add(nombrepanel2);
        // campo de texto --> nombre
        textonombrepanel2 = new JTextField();
        textonombrepanel2.setBounds(300,110,300,30);
        add(textonombrepanel2);
        //  etiqueta2 --> email
        emailpanel2 = new JLabel("email");
        emailpanel2.setBounds(300,140,100,50);
        add(emailpanel2);
        // campo de texto  --> email
        textoemailpanel2 = new JTextField();
        textoemailpanel2.setBounds(300,180,300,30);
        add(textoemailpanel2);
        //  etiqueta3 --> dni
        dnipanel2 = new JLabel("dni");
        dnipanel2.setBounds(300,210,100,50);
        add(dnipanel2);
        // campo de texto --> dni
        textodnipanel2 = new JTextField();
        textodnipanel2.setBounds(300,250,300,30);
        add(textodnipanel2);
        //  etiqueta4 --> contraseña
        constraseñapanel2 = new JLabel("contraseña");
        constraseñapanel2.setBounds(300,280,100,50);
        add(constraseñapanel2);
        // campo de texto --> contraseña
        textocontraseñapanel2 = new JTextField();
        textocontraseñapanel2.setBounds(300,320,300,30);
        add(textocontraseñapanel2);
        //boton 3
        botonvolveratraspanel2 = new JButton("volver atras");
        botonvolveratraspanel2.setBounds(20,440,120,40);
        add(botonvolveratraspanel2);

        //boton 4
        botonconfirmarregistropanel2 = new JButton("confirmar registro");
        botonconfirmarregistropanel2.setBounds(300,380,160,40);
        add(botonconfirmarregistropanel2);

        ControllerClient controller = new ControllerClient(this/*, client*/);
        this.controller(controller);

        v.setLayout(new BorderLayout());

        setSize(this.sizeX, this.sizeY);
        setLocation(this.posX, this.posY);
        setVisible(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


    }

    private void pagina3(){


        Container v = this.getContentPane();
        this.getRootPane().setBorder(BorderFactory.createMatteBorder(4,4,4,4, Color.BLACK));
        v.setBackground(Color.green);

        //  etiqueta --> Email
        etiquetaemailpanel3 = new JLabel("Email");
        etiquetaemailpanel3.setBounds(300,70,50,50);
        add(etiquetaemailpanel3);
        // campo de texto --> Email
        textoemailpanel3 = new JTextField();
        textoemailpanel3.setBounds(300,110,300,30);
        add(textoemailpanel2);
        //  etiqueta --> DNI
        etiquetadnipanel3 = new JLabel("DNI");
        etiquetadnipanel3.setBounds(300,140,100,50);
        add(etiquetadnipanel3);
        // campo de texto --> DNI
        textodnipanel3 = new JTextField();
        textodnipanel3.setBounds(300,180,300,30);
        add(textodnipanel3);

        //boton 5
        botonconfirmarpanel3 = new JButton("Confirmar");
        botonconfirmarpanel3.setBounds(380,230,160,40);
        add(botonconfirmarpanel3);
        //boton 6
        botonvolveratraspanel3 = new JButton("volver atras");
        botonvolveratraspanel3.setBounds(20,440,150,40);
        add(botonvolveratraspanel3);

        ControllerClient controller = new ControllerClient(this/*, client*/);
        this.controller(controller);

        v.setLayout(new BorderLayout());
        setSize(this.sizeX, this.sizeY);
        setLocation(this.posX, this.posY);
        setVisible(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


    }

    private void pagina4(){


        Container v = this.getContentPane();
        this.getRootPane().setBorder(BorderFactory.createMatteBorder(4,4,4,4, Color.BLACK));
        v.setBackground(Color.gray);

        //  etiqueta --> Usuario
        etiquetausuariopanel4 = new JLabel("Usuario (No modificar el campo, ERROR!!)");
        etiquetausuariopanel4.setBounds(300,70,250,50);
        add(etiquetausuariopanel4);
        // campo de texto --> Usuario
        textousuariopanel4 = new JTextField();
        textousuariopanel4.setBounds(300,110,300,30);
        add(textousuariopanel4);
        //  etiqueta --> Nueva contraseña
        etiquetacontraseñapanel4 = new JLabel("Nueva contraseña");
        etiquetacontraseñapanel4.setBounds(300,140,150,50);
        add(etiquetacontraseñapanel4);
        // campo de texto --> Nueva contraseña
        textocontraseñapanel4 = new JTextField();
        textocontraseñapanel4.setBounds(300,180,300,30);
        add(textocontraseñapanel4);

        //boton 7
        botoncontinuarpanel4 = new JButton("Continuar");
        botoncontinuarpanel4.setBounds(380,230,160,40);
        add(botoncontinuarpanel4);
        //boton 8
        botonvolveratraspanel4 = new JButton("volver atras");
        botonvolveratraspanel4.setBounds(20,440,150,40);
        add(botonvolveratraspanel4);

        ControllerClient controller = new ControllerClient(this/*, client*/);
        this.controller(controller);

        v.setLayout(new BorderLayout());
        setSize(this.sizeX, this.sizeY);
        setLocation(this.posX, this.posY);
        setVisible(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

    // un solo frame
    //////////////////////////////////////////

    private void controller(ActionListener myController){
        // controlador panel 1
        botonconfirmarpanel1.addActionListener(myController);
        botonregistrarsepanel1.addActionListener(myController);
        botonrecuperarcontraseñapanel1.addActionListener(myController);
        // controlador panel 2
        botonconfirmarregistropanel2.addActionListener(myController);
        botonvolveratraspanel2.addActionListener(myController);
        // controlador panel 3
        botonconfirmarpanel3.addActionListener(myController);
        botonvolveratraspanel3.addActionListener(myController);
        // controlador panel 4
        botoncontinuarpanel4.addActionListener(myController);
        botonvolveratraspanel4.addActionListener(myController);


    }


}
