package pkgUtils;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.concurrent.ConcurrentSkipListMap;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EventTest {
	public static Event TestEvent = null;
	public static ConcurrentSkipListMap<String, Boolean> TestGuests = new ConcurrentSkipListMap<String, Boolean>();

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		TestGuests.put("user1", false);
		TestGuests.put("user2", true);
		TestGuests.put("user3", false);
		TestGuests.put("user4", true);
		TestGuests.put("user5", false);
		 
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		TestEvent = new Event("001", "Evento1", new Date(), "Evento descripcion", "admin");
	}

	@AfterEach
	void tearDown() throws Exception {
		TestEvent = null;
	}

	@Test
	void addGuestsToEventTest() {
		TestEvent.setGuests(TestGuests);
		assertAll("guests",
		() -> assertTrue(TestEvent.containsGuest("user2")),
		() -> assertFalse(TestEvent.containsGuest("user6")) //Invitado inexistente
				);
	}

}
