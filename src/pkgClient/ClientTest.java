package pkgClient;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pkgServer.Database;
import pkgServer.Server;
import pkgUtils.Event;
import pkgUtils.User;

class ClientTest {
	static Client cli;
	static Server serv;
	static User TestUser;
	static Event TestEvent;
	static Database db;

    
	@BeforeAll
	static void setUpBeforeClass() throws Exception {    	
        ScheduledExecutorService service = new ScheduledThreadPoolExecutor(4 + 1);
        service.schedule(serv = new Server("Server"), 0, TimeUnit.SECONDS);
        service.schedule(cli = new Client("Client 1", 0, 10), 2, TimeUnit.SECONDS);
        Thread.sleep(3000);      
        db = Database.getInstance();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		serv = null;
		cli = null;
	}

	@BeforeEach
	void setUp() throws Exception {
		TestUser = new User("user2@uma.es", "user2", "pass2", "x123456", false);
		TestEvent = new Event("001", "Evento1", new Date(), "Evento descripcion", "admin");
	}

	@AfterEach
	void tearDown() throws Exception {
		TestUser = null;
		TestEvent = null;
	}

	@Test
	void signUpUserTest() throws IOException, Exception {
		cli.signUp(TestUser);
		Thread.sleep(100); //Esperando a la propagacion la accion
		assertTrue(db.getUser(TestUser.getName()) != null, "No se añadió el usuario");		
	}
	
	@Test
	void createEventTest() throws Exception {
		Event resultEvent = null;
		cli.createEvent(TestEvent);
		Thread.sleep(100); //Esperando a la propagacion la accion
		resultEvent = db.getUser(TestEvent.getOwner()).getEvent(TestEvent.getId()); //Obtiene evento almacenado
		assertEquals(TestEvent.getId(), resultEvent.getId(), "No se creó el evento");	
	}
}
