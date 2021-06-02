package pkgUtils;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.concurrent.ConcurrentSkipListMap;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserTest {
	public static User TestUser;
	public static User TestUser2;
	public static ConcurrentSkipListMap<String, Event> TestGuests = new ConcurrentSkipListMap<String, Event>();
	public static Event TestEvent1;
	public static Event TestEvent2;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		TestUser = new User("user2@uma.es", "user2", "pass2", "x123456", false);
		TestUser2 = new User("user3@uma.es", "user3", "", "", false); //Usuario incorrecto
		TestEvent1 = new Event("001", "Evento1", new Date(), "Evento1 descripcion", "admin");
		TestEvent2 = new Event("002", "Evento2", new Date(), "Evento2 descripcion", "user2");
	}

	@AfterEach
	void tearDown() throws Exception {
		TestUser = null;
		TestUser2 = null;
		TestEvent1 = null;
		TestEvent2 = null;
	}

	@Test
	void addEventTest() {
		TestUser.putEvent(TestEvent1);
		TestUser.putEvent(TestEvent2);
		assertAll("events add",
		() -> assertEquals(TestUser.getEvent(TestEvent1.getId()), TestEvent1, "Evento no encontrado"),
		() -> assertEquals(TestUser.getEvent(TestEvent2.getId()), TestEvent2, "Evento no encontrado") 
				);
	}
	@Test
	void removeEventTest() {
		TestUser.putEvent(TestEvent1);
		TestUser.putEvent(TestEvent2);
		TestUser.removeEvent(TestEvent1);
		assertAll("events remove",
		() -> assertEquals(TestUser.getEvent(TestEvent1.getId()), null, "Evento no eliminado"),
		() -> assertEquals(TestUser.getEvent(TestEvent2.getId()), TestEvent2, "Evento no encontrado") 
				);
	}
	@Test
	void correctUserDataTest() {
		assertAll("correct user",
		() -> assertTrue(TestUser.isCorrect(), "Comprobación de usuario correcto fallida"),
		() -> assertFalse(TestUser2.isCorrect(), "Comprobación de usuario incorrecto fallida") 
				);
	}

}
