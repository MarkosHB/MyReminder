package utils;

import java.util.Date;

public class Message {

    private String message;
    private Date date;
    private boolean read;

    public Message(String message, Date date, boolean read) {
        this.message = message;
        this.date = date;
        this.read = read;
    }

    public Message(String message, Date date) {
        this.message = message;
        this.date = date;
        this.read = false;
    }

    public Message(String message, boolean read) {
        this.message = message;
        this.date = new Date();
        this.read = read;
    }

    public Message(String message) {
        this.message = message;
        this.date = new Date();
        this.read = false;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

}
