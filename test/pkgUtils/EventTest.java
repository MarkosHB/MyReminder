package pkgUtils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.concurrent.ConcurrentSkipListMap;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EventTest {
	public static Event TestEvent;
	public static ConcurrentSkipListMap<String, Boolean> TestGuests;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {

		 
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		
	}

	@BeforeEach
	void setUp() throws Exception {
		TestEvent = new Event("001", "Evento1", new Date(), "Evento descripcion", "admin");
		TestGuests = new ConcurrentSkipListMap<String, Boolean>();
		TestGuests.put("user1", false);
		TestGuests.put("user2", false);
		TestGuests.put("user3", false);
		TestGuests.put("user4", false);
		TestGuests.put("user5", false);
	}

	@AfterEach
	void tearDown() throws Exception {
		TestEvent = null;
		TestGuests = null;
	}

	@Test
	void addGuestsToEventTest() {
		TestEvent.setGuests(TestGuests);
		assertAll("guests",
		() -> assertTrue(TestEvent.containsGuest("user2")),
		() -> assertFalse(TestEvent.containsGuest("user6")) //Invitado inexistente
				);
	}
	
	@Test
	void answerInvitationTest() {
		TestEvent.setGuests(TestGuests);
		TestEvent.answerInvitation("user1", true);
		TestEvent.answerInvitation("user2", false);
		assertAll("invitations",
				() -> assertTrue(TestGuests.get("user1"), "Invitación no aceptada correctamente"),
				() -> assertNull(TestGuests.get("user2"), "Invitación rechazada incorrectamente"),
				() -> assertFalse(TestGuests.get("user5"))
				);
	}

}
