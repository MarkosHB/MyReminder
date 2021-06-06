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
                Thread.sleep(1000);
                for (Event event : client.getUser().getEvents().values()) {
                    try {
                        Date now = new Date();
                        if ((now.getMinutes() == event.getAlarm().getMinutes()) &&
                                (now.getHours() == event.getAlarm().getHours()) &&
                                (now.getDay() == event.getAlarm().getDate()) &&
                                (now.getMonth() == event.getAlarm().getMonth()) &&
                                (now.getYear() == event.getAlarm().getYear())) {
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
