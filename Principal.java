public class Principal {

    public static void main(String[] args) {

     Configurador c= Configurador.getConfigurador("miurl", "mibaseDatos");

     System.out.println(c.getUrl());

     System.out.println(c.getBaseDatos());

    }

