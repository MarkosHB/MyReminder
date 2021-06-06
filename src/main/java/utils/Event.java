package utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ConcurrentSkipListMap;

public class Event implements Serializable, Comparable {

    private String id;
    private String title;
    private Date date;
    private Date alarm;
    private String description;
    private String owner;
    private ConcurrentSkipListMap<String, Boolean> guests; // Name and: true if accepted, false if pending

    public Event (Event event) {
        this.id = event.id;
        this.title = event.title;
        this.date = event.date;
        this.alarm = event.alarm;
        this.description = event.description;
        this.owner = event.owner;
        this.guests = event.guests.clone();
    }

    public Event(String id, String title, Date date, Date alarm, String description, String owner, ArrayList<String> guests) {
        this.id = id.toUpperCase();
        this.title = title;
        this.date = date;
        this.alarm = alarm;
        this.description = description;
        this.owner = owner;
        this.guests = new ConcurrentSkipListMap<>();
        for (String guest: guests) {
            this.guests.put(guest, false);
        }
    }

    public Event(String id, String title, Date date, Date alarm, String description, String owner) {
        this.id = id.toUpperCase();
        this.title = title;
        this.date = date;
        this.alarm = alarm;
        this.description = description;
        this.owner = owner;
        this.guests = new ConcurrentSkipListMap<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getAlarm() {
        return alarm;
    }

    public void setAlarm(Date date) {
        this.alarm = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public ConcurrentSkipListMap<String, Boolean> getGuests() {
        return guests;
    }

    public void setGuests(ConcurrentSkipListMap<String, Boolean> guests) {
        this.guests = guests;
    }

    public void putGuest(String name) {
        this.guests.put(name, false);
    }

    public boolean containsGuest(String name) {
        return this.guests.containsKey(name);
    }

    public void answerInvitation(String userName, boolean accept) {
        if (accept) {
            guests.put(userName, true);
        } else {
            guests.remove(userName);
        }
    }

    @Override
    public String toString() {
        return "EVENT: " + title + " -> date = " + date + ", owner = " + owner + ", guests = " + guests;
    }

    @Override
    public int compareTo(Object o) {
        Event e = (Event) o;
        return (this.date.compareTo(e.date));
    }

}
