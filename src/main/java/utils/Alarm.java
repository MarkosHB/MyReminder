package utils;

import client.Client;
import frame.ClientFrame;
import utils.Event;

import javax.swing.*;
import java.awt.*;
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
        while(true) {
            try {
                Thread.sleep(60000);
                Event[] events = client.getUser().getEvents().values().toArray(new Event[0]);
                for (Event event : events) {
                    try {
                        if ((new Date().getTime() - event.getAlarm().getTime()) < (60 * 1000)) {
                            //client.getUser().addMessage();
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
