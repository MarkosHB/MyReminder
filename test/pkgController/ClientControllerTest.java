package pkgController;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.event.ActionEvent;
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
import pkgFrame.ClientFrame;
import pkgServer.Database;
import pkgServer.Server;
import pkgUtils.Event;
import pkgUtils.User;

class ClientControllerTest {
	static Server serv;
	static Client cli;
	static Database db;
	static User TestUser;
	static ClientController cont;
	static ClientFrame mockframe; //Objeto frame simulado

	
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {    	
        ScheduledExecutorService service = new ScheduledThreadPoolExecutor(4 + 1);
        service.schedule(serv = new Server("Server"), 0, TimeUnit.SECONDS);
        service.schedule(cli = new Client("Client 1", 0, 10), 2, TimeUnit.SECONDS);
        Thread.sleep(3000);    
        
        //Componentes que van a interactuar en la prueba
        db = Database.getInstance(); 
        cont = cli.getController(); 
        mockframe = mock(ClientFrame.class);
        TestUser = new User("user2@uma.es", "user2", "pass2", "x123456", false);
        
        //Creando FRAME SIMULADO para probar        
    	when(mockframe.getMailTextSignUp()).thenReturn(TestUser.getMail());
    	when(mockframe.getUserTextSignUp()).thenReturn(TestUser.getName());
    	when(mockframe.getPasswordTextSignUp()).thenReturn(TestUser.getPassword());
    	when(mockframe.getDniTextSignUp()).thenReturn(TestUser.getDni());
    	cont.setFrame(mockframe);
        
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		serv = null;
		cli = null;
		db = null;
		cont = null;
		mockframe = null;
		TestUser = null;
	}

	@BeforeEach
	void setUp() throws Exception {
		
	}

	@AfterEach
	void tearDown() throws Exception {
		
	}

	@Test
	void signUpUserTest() { 
		//SIMULANDO QUE EL USUARIO INTENTA HACER SING-UP		
		ActionEvent act = new ActionEvent(this, 0, "SIGN UP");
		cont.actionPerformed(act);
		
		//Comprobando si se usó los metodos de prueba
		verify(mockframe).getMailTextSignUp();
		verify(mockframe).getUserTextSignUp();
		verify(mockframe).getUserTextSignUp();
		verify(mockframe).getUserTextSignUp();
		
		assertTrue(db.getUser(TestUser.getName()) != null, "No se añadió el usuario");		
	}
	
}
