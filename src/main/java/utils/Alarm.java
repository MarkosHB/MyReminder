package utils;

import client.Client;
import frame.ClientFrame;
import utils.Event;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ConcurrentSkipListMap;

public class Alarm implements Runnable {

    private Client client;
    private ClientFrame frame;

    public Alarm(Client client, ClientFrame frame) {
        this.client = client;
        this.frame = frame;
    }

    @Override
    public void run() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        while(true) {
            try {
                Thread.sleep(1000);
                Date now = new Date();
                for (Event event : client.getUser().getEvents().values()) {
                    try {
                        if (formatter.format(now).equals(formatter.format(event.getAlarm()))) {
                            System.out.println("ALARMA: " + event.getTitle());
                            client.getUser().addMessage("ALARMA: " + event.getTitle());
                            event.setAlarm(null);
                            frame.showMessages();
                        }
                    } catch (NullPointerException ignore) { }
                }
            } catch (Exception ignore) { }
        }
    }

}
