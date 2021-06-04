package client;

import frame.ClientFrame;
import utils.Event;

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
            Event[] events = client.getUser().getEvents().values().toArray(new Event[0]);
            for (int i = 0; i < events.length; i++) {
                if ((new Date().getTime() - events[i].getAlarm().getTime()) < (60*1000)) {
                    //client.getUser().addMessage();
                    frame.showMessages();
                }
            }
        }
    }
}
