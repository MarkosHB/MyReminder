package controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.util.Date;
import java.util.concurrent.PriorityBlockingQueue;
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
	static User TestContact;
	static User TestUser;
	static Database db;
	static ClientController cont;
	static ClientFrame mockframe; //Objeto frame simulado
	static int tmpPropagacion = 1000; //Tiempo a esperar hasta que la acción tenga resultado

	
	
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
        TestContact= new User("user3@uma.es", "user3", "pass3", "3123456", false);
		TestEvent = new Event("CLIENT 1_0", "Evento1", new Date(), new Date(), "Evento descripcion", "user2");
        
        //Creando FRAME SIMULADO para probar        
    	when(mockframe.getMailTextSignUp()).thenReturn(TestUser.getMail());
    	when(mockframe.getUserTextSignUp()).thenReturn(TestUser.getName());
    	when(mockframe.getPasswordTextSignUp()).thenReturn(TestUser.getPassword());
    	when(mockframe.getDniTextSignUp()).thenReturn(TestUser.getDni());
    	
    	when(mockframe.getUserTextSignIn()).thenReturn(TestUser.getName());
    	when(mockframe.getPasswordTextSignIn()).thenReturn(TestUser.getPassword());
		
    	when(mockframe.getCreateEventTitle()).thenReturn(TestEvent.getTitle());
    	when(mockframe.getCreateEventDate()).thenReturn(TestEvent.getDate());
    	when(mockframe.getCreateEventAlarm()).thenReturn(TestEvent.getAlarm());
    	when(mockframe.getCreateEventDescription()).thenReturn(TestEvent.getDescription());
    	
    	when(mockframe.getAddContactText()).thenReturn(TestContact.getName());
    	
    	cont.setFrame(mockframe);
    	
    	db.addUser(TestContact); //Añadiendo usuario de contacto a la base de datos
    	cont.signUp();
    	
    	Thread.sleep(tmpPropagacion);
    	cli.signIn(TestUser);
        
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		cont.logOut();
		serv = null;
		cli = null;
		db = null;
		cont = null;
		mockframe = null;
		TestUser = null;
		TestContact = null;
		TestEvent = null;
		
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
		
	}
	
	
	@Test
	void signUpUserTest() throws InterruptedException { 
		System.out.println("////////Test de registro de usuario");
		//SIMULANDO QUE EL USUARIO INTENTA HACER SING-UP	
		db.removeUser(TestUser);
		ActionEvent act = new ActionEvent(this, 0, "SIGN UP");
		cont.actionPerformed(act);
		
		Thread.sleep(tmpPropagacion);
		
		//Comprobando si se us� los metodos de prueba
		verify(mockframe, atLeast(1)).getMailTextSignUp();
		verify(mockframe, atLeast(1)).getUserTextSignUp();
		verify(mockframe, atLeast(1)).getPasswordTextSignUp();
		verify(mockframe, atLeast(1)).getDniTextSignUp();
		
		assertTrue(db.getUser(TestUser.getName()) != null, "No se a�adi� el usuario");	
	} 
	
	@Test
	void signInTest() throws InterruptedException {
		System.out.println("////////Test de autenticación de usuario");
		User loggedUser;
    	when(mockframe.getUserTextSignIn()).thenReturn(TestContact.getName());
    	when(mockframe.getPasswordTextSignIn()).thenReturn(TestContact.getPassword());
		
		cont.logOut();
		ActionEvent act = new ActionEvent(this, 0, "SIGN IN");
		cont.actionPerformed(act);
		
		Thread.sleep(tmpPropagacion * 2); //doble propagación al hacer camino clientController->client->server->client
		
		loggedUser = cli.getUser();
		
		
		System.out.println("LOGGED: " + loggedUser);
		
		assertEquals(loggedUser.getName(), TestContact.getName(), "Autenticación de usuario fallida");	
    	
		cont.logOut();
		when(mockframe.getUserTextSignIn()).thenReturn(TestUser.getName()); //Volviendo al usuario de prueba inicial
    	when(mockframe.getPasswordTextSignIn()).thenReturn(TestUser.getPassword());
    	cli.signIn(TestUser);
		
		}
	
	@Test
	void createEventTest() throws ParseException, InterruptedException {
		System.out.println("////////Test de creacion de evento");
		Event resultEvent;
		
		//SIMULANDO QUE EL USUARIO CREA UN EVENTO		
		ActionEvent act = new ActionEvent(this, 0, "CONFIRM CREATE EVENT");
		cont.actionPerformed(act);
		
		Thread.sleep(tmpPropagacion);
		
		
		//Comprobando si se us� los metodos de prueba
    	verify(mockframe).getCreateEventTitle();
    	verify(mockframe).getCreateEventDate();
    	verify(mockframe).getCreateEventAlarm();
    	verify(mockframe).getCreateEventDescription();
    	
		resultEvent = db.getUser(TestEvent.getOwner()).getEvent(TestEvent.getId()); //Obtiene evento almacenado
		assertEquals(TestEvent.getId(), resultEvent.getId(), "No se cre� el evento");	
	}
	

	@Test
	void addContactTest() throws InterruptedException {
		System.out.println("////////Test de añadir contacto");
		
		String resultUserName = null;
		//SIMULANDO QUE EL USUARIO AÑADE UN CONTACTO
		ActionEvent act = new ActionEvent(this, 0, "ADD CONTACT");
		cont.actionPerformed(act);
		
		Thread.sleep(tmpPropagacion);
		
		//Comprobando si se us� los metodos de prueba
		verify(mockframe).getAddContactText();	
		
		
		resultUserName = db.getUser(TestUser.getName()).getContacts().remove(); //Sacamos el contacto de la lista de contactos del usuario
		assertEquals(resultUserName, TestContact.getName(), "El contacto no se ha añadido correctamente.");
		
	
	}
	
	@Test
	void removeContactTest() throws InterruptedException {
		System.out.println("////////Test de eliminar contacto");
		PriorityBlockingQueue<String> userContacts;		
		
		//SIMULANDO QUE EL USUARIO ELIMINA UN CONTACTO
		ActionEvent act = new ActionEvent(this, 0, "DELETE CONTACT: " + TestContact.getName());
		cont.actionPerformed(act);	
		
		Thread.sleep(tmpPropagacion);
		
		userContacts = db.getUser(TestUser.getName()).getContacts();
		assertTrue(userContacts.isEmpty(), "El contacto no se ha eliminado correctamente.");		
	}
	
	
	
}
