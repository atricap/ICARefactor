/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mas;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.Normalizer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Teddy
 */
public class MetaAgentTest {
    
    public MetaAgentTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of run method, of class MetaAgent.
     */
    @Test
    public void testRun() {
        
        System.out.println("run");
        Router router = new Router("R1");
        Portal portal1 = new Portal("P1",router); 
        MetaAgent instance = new UserAgent("123", portal1);
        
        
    }

    /**
     * Test of stop method, of class MetaAgent.
     */
    @Test
    public void testStop() {
        System.out.println("stop");
        Router router = new Router("R1");
        Portal portal1 = new Portal("P1",router); 
        MetaAgent instance = new UserAgent("123", portal1);
        
        
    }

    /**
     * Test of messageHandler method, of class MetaAgent.
     */
    @Test
    public void testMessageHandler() throws InterruptedException
    {
        System.out.println("messageHandler");
        Router router = new Router("R1");
        Portal portal1 = new Portal("P1", router);
        Portal portal2 = new Portal("P2", router);
        UserAgent user1 = new UserAgent("A1", portal1);
        UserAgent user2 = new UserAgent("A2", portal1);
        UserAgent user3 = new UserAgent("A3", portal2);
        Message message = new Message("A3", "Hello A1!", "A1", MessageType.USERMESSAGE);
        portal1.addAgent(user1);
        portal1.addAgent(user2);
        portal2.addAgent(user3);
        String result = "";
        
        
        PrintStream originalOut = System.out;
        try {
        ByteArrayOutputStream os = new ByteArrayOutputStream(100);
        PrintStream capture = new PrintStream(os);
        // From this point on, everything printed to System.out will get captured
        
        System.setOut(capture);
        user3.sendMessage(message);
        Thread.sleep(500);
        capture.flush();
        result = os.toString();
        } 
        finally 
        {
        System.setOut(originalOut);
        }
        String expResult = "Message from: A1\nMessage: Hello A1!\nTo: A3";
        expResult = (Normalizer.normalize(expResult, Normalizer.Form.NFD));
        result = (Normalizer.normalize(result, Normalizer.Form.NFD));
        assertEquals(expResult, result);
        System.out.println(message.toString());
        System.out.println(result);
        
    }

    public class MetaAgentImpl extends MetaAgent {

        public MetaAgentImpl() {
            super("", (Portal) null);
        }
    }
    
}
