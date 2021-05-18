package pkgDemo;

import pkgClient.Client;
import pkgServer.Server;

import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Demo {

    // Introduzco manualmente el algoritmo de cifrado por lo que sé que no dará error
    public static void main(String[] args) throws NoSuchAlgorithmException {

        ScheduledExecutorService service = new ScheduledThreadPoolExecutor(4 + 1);
        service.schedule(new Server("Server"), 0, TimeUnit.SECONDS);
        service.schedule(new Client("Client 1", 0, 10), 2, TimeUnit.SECONDS);
        //service.schedule(new Client("Client 2", 800, 10), 3, TimeUnit.SECONDS);
        //service.schedule(new Client("Client 3", 0, 500), 4, TimeUnit.SECONDS);
        //service.schedule(new Client("Client 4", 800, 500), 5, TimeUnit.SECONDS);

    }

}
