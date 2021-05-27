package pkgUtils;

import java.io.Serializable;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.PriorityBlockingQueue;

public class User implements Serializable {

    private String mail;
    private String name;
    private String password;
    private String dni;
    private boolean admin;
    private ConcurrentSkipListMap<String, Event> events;
    private PriorityBlockingQueue<Event> events2;

    public User(String mail, String name, String password, String dni, boolean admin) {
        this.mail = mail;
        this.name = name;
        this.password = password;
        this.dni = dni;
        this.admin = admin;
        this.events = new ConcurrentSkipListMap<>();
    }

    public User(String mail, String name, String password, String dni) {
        this.mail = mail;
        this.name = name;
        this.password = password;
        this.dni = dni;
        this.admin = false;
        this.events = new ConcurrentSkipListMap<>();
    }

    public User(String name, String password) {
        this.mail = null;
        this.name = name;
        this.password = password;
        this.dni = null;
        this.admin = false;
        this.events = new ConcurrentSkipListMap<>();
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public ConcurrentSkipListMap<String, Event> getEvents() {
        return events;
    }

    public void setEvents(ConcurrentSkipListMap<String, Event> events) {
        this.events = events;
    }

    public Event getEvent(String id) {
        return this.events.get(id);
    }

    public void putEvent(Event event) {
        this.events.put(event.getId(), event);
    }

    public void removeEvent(Event event) {
        this.events.remove(event.getId());
    }

    @Override
    public String toString() {
        return "USER: " + name + " -> e-mail = " + mail + ", password = " + password;
    }

    public boolean correctPassword(String password) {
        return password.equals(this.password);
    }

    public boolean isCorrect() {
        return (!mail.equals("") && !name.equals("") && !password.equals("") && !dni.equals(""));
    }

}
