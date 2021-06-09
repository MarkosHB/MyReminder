public class Configurador {

    private String url;
     private String baseDatos;
     private static final Configurador miconfigurador;

     public  static Configurador getConfigurador(String url,String baseDatos) {

     if (miconfigurador==null) {

     miconfigurador=new Configurador(url,baseDatos);
     }
     return miconfigurador;
     }

     private Configurador(String url,String baseDatos){

     this.url=url;
     this.baseDatos=baseDatos;

     }

    public String getUrl() {
     return url;
     }

    public void setUrl(String url) {
     this.url = url;
     }

    public String getBaseDatos() {
     return baseDatos;
     }