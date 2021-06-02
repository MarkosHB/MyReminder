package pkgController;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pkgClient.Client;
import pkgServer.Database;
import pkgServer.Server;
import pkgUtils.Event;
import pkgUtils.User;

class ClientControllerTest {
	static Server serv;
	static Client cli;
	static Database db;
	static User TestUser;
	
	
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
	}

	@AfterEach
	void tearDown() throws Exception {
		TestUser = null;
	}

	@Test
	void signUpUserTest() {
		fail("Test por implementar");
	}
}
