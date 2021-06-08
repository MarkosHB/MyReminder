package server;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.ConcurrentSkipListMap;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import utils.User;

class DatabaseTest {
	static Database db;
    static ConcurrentSkipListMap<String, User> users;     
    static ConcurrentSkipListMap<String, String> mails;  
    static User TestUser1, TestUser2, TestUser3, TestUser4, TestUser5;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		db = Database.getInstance();
		TestUser1 = new User("user1@uma.es", "user1", "pass2", "x123456", false);
		TestUser2 = new User("user2@uma.es", "user2", "pass3", "u21203", false); 
		TestUser3 = new User("user3@uma.es", "user3", "pass3", "u21204", false); 
		TestUser4 = new User("user4@uma.es", "user4", "pass3", "u21205", false);
		TestUser5 = new User("user5@uma.es", "user5", "pass3", "u21206", false); 
		users = new ConcurrentSkipListMap<String, User>();
		users.put(TestUser1.getName(), TestUser1);
		users.put(TestUser2.getName(), TestUser2); 
		users.put(TestUser3.getName(), TestUser3); 
		users.put(TestUser4.getName(), TestUser4);
		mails = new ConcurrentSkipListMap<String, String>();
		mails.put(TestUser1.getMail(), TestUser1.getName());
		mails.put(TestUser2.getMail(), TestUser2.getName());
		mails.put(TestUser3.getMail(), TestUser3.getName());
		mails.put(TestUser4.getMail(), TestUser4.getName());
		
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		db = null;
		TestUser1 = TestUser2 = TestUser3 = TestUser4 = TestUser5 = null;
		users = null;
	}

	@BeforeEach
	void setUp() throws Exception {
		db.setUsers(users);
		db.setMails(mails);
	}

	@AfterEach
	void tearDown() throws Exception {
		db.setUsers(new ConcurrentSkipListMap<String, User>());
		db.setMails(new ConcurrentSkipListMap<String, String>());
	}

	@Test
	void addUserTest() {
		db.addUser(TestUser5);
		assertAll("users add",
		() -> assertTrue(db.containsUserName("user1") , "Usuario no encontrado"),
		() -> assertTrue(db.containsUserName("user5"), "No se a�adi� el usuario correctamente"),
		() -> assertFalse(db.containsUserName("user6"), "Usuario inexistente")
				);
	}
	
	@Test
	void addMailTest() {
		db.addMail(TestUser5.getMail(), TestUser5.getName());
		assertAll("mail add",
		() -> assertTrue(db.containsMail(TestUser1.getMail()) , "Correo no encontrado"),
		() -> assertTrue(db.containsMail(TestUser5.getMail()), "No se a�adi� el correo correctamente"),
		() -> assertFalse(db.containsMail("user6@uma.es"), "Correo inexistente")
				);
	}

}
