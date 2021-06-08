package controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.util.Date;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.junit.Ignore;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import client.Client;
import frame.ClientFrame;
import server.Database;
import server.Server;
import utils.Event;
import utils.User;

class ClientControllerTest {
	static Server serv;
	static Client cli;
	static Event TestEvent;
	static User TestUser;
	static Database db;
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
		TestEvent = new Event("CLIENT 1_0", "Evento1", new Date(), new Date(), "Evento descripcion", "user2");
        
        //Creando FRAME SIMULADO para probar        
    	when(mockframe.getMailTextSignUp()).thenReturn(TestUser.getMail());
    	when(mockframe.getUserTextSignUp()).thenReturn(TestUser.getName());
    	when(mockframe.getPasswordTextSignUp()).thenReturn(TestUser.getPassword());
    	when(mockframe.getDniTextSignUp()).thenReturn(TestUser.getDni());
		
    	when(mockframe.getCreateEventTitle()).thenReturn(TestEvent.getTitle());
    	when(mockframe.getCreateEventDate()).thenReturn(TestEvent.getDate());
    	when(mockframe.getCreateEventAlarm()).thenReturn(TestEvent.getAlarm());
    	when(mockframe.getCreateEventDescription()).thenReturn(TestEvent.getDescription());
    	
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
		TestEvent = null;
		
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
		
		//Comprobando si se us� los metodos de prueba
		verify(mockframe, atLeast(1)).getMailTextSignUp();
		verify(mockframe, atLeast(1)).getUserTextSignUp();
		verify(mockframe, atLeast(1)).getPasswordTextSignUp();
		verify(mockframe, atLeast(1)).getDniTextSignUp();
		
		assertTrue(db.getUser(TestUser.getName()) != null, "No se a�adi� el usuario");		
	} 
	
	@Test
	void createEventTest() throws ParseException, InterruptedException {
		Event resultEvent;
		cont.signUp();
		//SIMULANDO QUE EL USUARIO CREA UN EVENTO		
		ActionEvent act = new ActionEvent(this, 0, "CONFIRM CREATE EVENT");
		cont.actionPerformed(act);
		
		Thread.sleep(2000);
		
		
		//Comprobando si se us� los metodos de prueba
    	verify(mockframe).getCreateEventTitle();
    	verify(mockframe).getCreateEventDate();
    	verify(mockframe).getCreateEventAlarm();
    	verify(mockframe).getCreateEventDescription();
    	
		resultEvent = db.getUser(TestEvent.getOwner()).getEvent(TestEvent.getId()); //Obtiene evento almacenado
		assertEquals(TestEvent.getId(), resultEvent.getId(), "No se cre� el evento");	
		
		db.removeUser(TestUser);

	}
	
	
/*	@Test //Prueba en clase Client mientras se implementa en ClientController
	void createEventTest() throws Exception {
		Event resultEvent;
		cli.createEvent(TestEvent);
		Thread.sleep(500); //Esperando a la propagacion la accion
		resultEvent = db.getUser(TestEvent.getOwner()).getEvent(TestEvent.getId()); //Obtiene evento almacenado
		assertEquals(TestEvent.getId(), resultEvent.getId(), "No se cre� el evento");	
	} */
	
}
