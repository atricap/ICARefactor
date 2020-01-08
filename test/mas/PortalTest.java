/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mas;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

/**
 *
 * @author Souchys
 */
public class PortalTest {
    
    public PortalTest()
    {
    
    }

    @Rule public ExpectedException thrown = ExpectedException.none();
    
    /**
     * Testing the IllegalArgumentException thrown when creating a new Portal and the Portal userName variable is empty.
     * @since #1.0
     * @exception  IllegalArgumentException
     * @see IllegalArgumentException
     */
    @Test
    public void testLocalPortalConstArgsUserNameEmpty()
    {
        System.out.println("Testing Local Portal Constructor");
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Please ensure your Portal/Router username is not null or empty");
        Router router = new Router("R1");
        Portal portal = new Portal("", router);
    }
    
    /**
     * Testing the IllegalArgumentException thrown when creating a new Portal and the Portal userName variable is null.
     * @since #1.0
     * @exception  IllegalArgumentException
     * @see IllegalArgumentException
     */
    @Test
    public void testLocalPortalConstArgsUserNameNull()
    {
        System.out.println("Testing Local Portal Constructor");
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Please ensure your Portal/Router username is not null or empty");
        Router router = new Router("R1");
        Portal portal = new Portal(null, router);
    }
    @Test
    public void testSetPortal()
    {
        System.out.println("Testing Set Portal");
        Router router = new Router("R1");
        Portal portal = new Portal("p1", router);
        Portal portal2 = new Portal("p2", router);
        boolean result = portal.setPortal(portal2);
        boolean expResult = true;
        assertEquals(expResult, result);
        
    }
    @Test
    public void testSetRouter()
    {
        System.out.println("Testing SetRouter");
        Router router = new Router("R1");
        Router router2 = new Router("R2");
        Portal portal = new Portal("p1", router);
        
        boolean result = portal.setRouter(router2);
        boolean expResult = true;
        assertEquals(expResult, result);
        
    }

//    public void testGetSocket() throws IOException
//    {
//        System.out.println("Testing Set Portal Socket");
//        System.out.println("Testing External Portal Constructor");
//        Portal portal = new Portal("P1", "123.123.123", 8500);
//        Socket socket;
//        int i = 8500;
//        socket = new Socket("123.123.123",i);
//        
//        assertEquals(portal.getSocket(), socket);
//        
//        
//        
//        
//        
//    }
    @Test
    public void testSetRouterNull()
    {
        System.out.println("Testing Setting Router Null");
        Router router = new Router("R1");
        Router router2 = null;
        Portal portal = new Portal("p1", router);
        
        boolean result = portal.setRouter(router2);
        boolean expResult = false;
        assertEquals(expResult, result);
        
    }
    @Test
    public void testSetPortalNull()
    {
        System.out.println("Testing Set Portal Null");
        Router router = new Router("R1");
        Portal portal = new Portal("p1", router);
        Portal portal2 = null;
        boolean result = portal.setPortal(portal2);
        boolean expResult = false;
        assertEquals(expResult, result);
        
    }
    
    /**
     * Testing the IllegalArgumentException thrown when creating a new Portal and the Router variable is null.
     * @since #1.0
     * @exception  IllegalArgumentException
     * @see IllegalArgumentException
     */
    @Test
    public void testLocalPortalConstArgsRouterNull()
    {
        System.out.println("Testing Local Portal Constructor");
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Please ensure your Portals local Router is not null");
        Portal portal = new Portal("P1", null);
    }
    
    /**
     * Testing the IllegalArgumentException thrown when creating a new Portal and the Portal ipAddress is empty.
     * @since #1.0
     * @exception  IllegalArgumentException
     * @see IllegalArgumentException
     */
    @Test
    public void testExternalPortalConstArgsIpEmpty()
    {
        System.out.println("Testing External Portal Constructor");
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Please ensure that your IP Address is appropriate and your Port is not less than 8000");
        Portal portal = new Portal("P1", "", 8500);
    }
    
    /**
     * Testing the IllegalArgumentException thrown when creating a new Portal and the Portal ipAddress is empty.
     * @since #1.0
     * @exception  IllegalArgumentException
     * @see IllegalArgumentException
     */
    @Test
    public void testExternalPortalConstArgsIpNull()
    {
        System.out.println("Testing External Portal Constructor");
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Please ensure that your IP Address is appropriate and your Port is not less than 8000");
        Portal portal = new Portal("P1", null, 8500);
    }
    
    /**
     * Testing the IllegalArgumentException thrown when creating a new Portal and the Portal ipAddress is empty.
     * @since #1.0
     * @exception  IllegalArgumentException
     * @see IllegalArgumentException
     */
    @Test
    public void testExternalPortalConstArgsIPIncorrect()
    {
        System.out.println("Testing External Portal Constructor");
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Please ensure that your IP Address is appropriate and your Port is not less than 8000");
        Portal portal = new Portal("P1", "1789562", 8500);
    }
    
    /**
     * Testing the IllegalArgumentException thrown when creating a new Portal and the Portal ipAddress is empty.
     * @since #1.0
     * @exception  IllegalArgumentException
     * @see IllegalArgumentException
     */
    @Test
    public void testExternalPortalConstArgsPortIncorrect()
    {
        System.out.println("Testing External Portal Constructor");
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Please ensure that your IP Address is appropriate and your Port is not less than 8000");
        Portal portal = new Portal("P1", "152.0.0.0", -1425);
    }
    @Test
    public void testMessageHandler() throws InterruptedException 
    {
        System.out.println("messageHandler");
        Router router = new Router("R1");
        Portal portal1 = new Portal("P1", router);
        Portal portal2 = new Portal("P2", router);
        UserAgent user1 = new UserAgent("A1", portal1);
        Message message = new Message("P1", "Hello P2!", "A1", MessageType.USERMESSAGE);
        portal1.addAgent(user1);
        String result = "";
        
        Thread.sleep(500);
        PrintStream originalOut = System.out;
        try {
        ByteArrayOutputStream os = new ByteArrayOutputStream(100);
        PrintStream capture = new PrintStream(os);
        // From this point on, everything printed to System.out will get captured
        
        
        System.setOut(capture);
        user1.SendMessage(message);
        Thread.sleep(500);
        capture.flush();
        result = os.toString();
        } 
        finally 
        {
        System.setOut(originalOut);
        }
        String expResult = "Message direct to portal: " + result;
        result = result.trim();
        assertEquals(expResult, result);
        System.out.println(message.toString());
        System.out.println(result);
        
    }
}
