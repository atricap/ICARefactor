package mas;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MetaAgent2Test {

    @Test
    public void characterize() throws InterruptedException {
        TestingPrinter out = new TestingPrinter();
        Message m = new Message("someone", "This is a user message to someone!", MessageType.USERMESSAGE);

        SimpleMetaAgent a1 = new SimpleMetaAgent("A1", out);
        a1.put(m);

        assertEquals("", out.sb.toString());
    }
}
