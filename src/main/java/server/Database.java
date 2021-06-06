package server;

import utils.User;

import java.util.concurrent.ConcurrentSkipListMap;

public class Database {

    private static volatile Database instance;

    private ConcurrentSkipListMap<String, User> users; // Name -> User
    private ConcurrentSkipListMap<String, String> mails; // Mail -> Name

    private Database() {
        this.users = new ConcurrentSkipListMap<>();
        this.mails = new ConcurrentSkipListMap<>();
    }

    public static Database getInstance() {
        Database result = instance;
        if (result != null) {
            return result;
        }
        synchronized (Database.class) {
            if (instance == null) {
                instance = new Database();
            }
            return instance;
        }
    }

    public ConcurrentSkipListMap<String, User> getUsers() {
        return users;
    }

    public void setUsers(ConcurrentSkipListMap<String, User> users) {
        this.users = users;
    }

    public ConcurrentSkipListMap<String, String> getMails() {
        return mails;
    }

    public void setMails(ConcurrentSkipListMap<String, String> mails) {
        this.mails = mails;
    }

    public void addUser(String key, User value) {
        users.put(key, value);
    }

    public void removeUser(String key) {
        users.remove(key);
    }

    public User getUser(String key) {
        return users.get(key);
    }

    public boolean containsUserName(String key) {
        return users.containsKey(key);
    }

    public void addMail(String key, String value) {
        mails.put(key, value);
    }

    public String getUserName(String key) {
        return mails.get(key);
    }

    public boolean containsMail(String key) {
        return mails.containsKey(key);
    }

}
