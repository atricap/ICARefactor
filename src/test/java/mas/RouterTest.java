/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mas;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Souchys
 */
public class RouterTest {
    
    public RouterTest() {
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
     * Test of getRouterRoutingTable method, of class Router.
     */
    @Test
    public void testGetRouterRoutingTable() {
        System.out.println("getRouterRoutingTable");
        Router router = new Router("R1");
        Portal portal1 = new Portal("P1", router);
        Portal portal2 = new Portal("P2", router);
        UserAgent user1 = new UserAgent("A1", portal1);
        UserAgent user2 = new UserAgent("A2", portal1);
        UserAgent user3 = new UserAgent("A3", portal2);
        Router instance = router;
        TreeMap expResult = router.getRouterRoutingTable();
        TreeMap result = instance.getRouterRoutingTable();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of waitForConnection method, of class Router.
     */
    @Test
    public void testWaitForConnection() {
        System.out.println("waitForConnection");
        Router instance = null;
        //instance.waitForConnection();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of messageHandler method, of class Router.
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
        String expResult = "Message from: A1\r\nMessage: Hello A1!\r\nTo: A3";
        assertEquals(expResult, result);
        System.out.println(message.toString());
        System.out.println(result);
        
    }

    /**
     * Test of getRouterRouting method, of class Router.
     */
    @Test
    public void testGetRouterRouting() {
        
        
        System.out.println("getRouterRouting");
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
        Router instance = router;
        TreeMap expResult = router.getRouterRouting();
        TreeMap result = instance.getRouterRouting();
        assertEquals(result ,expResult );
        
    }

    /**
     * Test of getNetworkPortals method, of class Router.
     */
    @Test
    public void testGetNetworkPortals() {
        System.out.println("getNetworkPortals");
        System.out.println("getRouterRouting");
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
        Router instance = router;
        TreeMap expResult = router.getNetworkPortals();
        TreeMap result = instance.getNetworkPortals();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getLocalPortals method, of class Router.
     */
    @Test
    public void testGetLocalPortals() 
    {
        System.out.println("getRouterRouting");
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
        Router instance = router;
        ArrayList<Portal> expResult = router.getLocalPortals();
        ArrayList<Portal> result = instance.getLocalPortals();
        assertEquals(result ,expResult );
    }
    @Test
    public void testMessageHandlerDeleteMessage() throws InterruptedException 
    {
        System.out.println("messageHandler");
        Router router = new Router("R1");
        Portal portal1 = new Portal("P1", router);
        Portal portal2 = new Portal("P2", router);
        UserAgent user1 = new UserAgent("A1", portal1);
        UserAgent user2 = new UserAgent("A2", portal1);
        UserAgent user3 = new UserAgent("A3", portal2);
        String prevNodeHandle = ""; // FIXME: is that right?
        Message message = new Message("R1", "A1",user1.portal, prevNodeHandle, MessageType.DELETEUSERMESSAGE);
        portal1.addAgent(user1);
        portal1.addAgent(user2);
        portal2.addAgent(user3);
        
        router.messageHandler(message);
        boolean result = false;
        
        if(!router.getRouterRouting().containsKey("A1"))
        {
            result = true;
        }
        
        
        boolean expResult = true;
        assertEquals(expResult, result);
        
        
    }
}
