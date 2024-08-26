package mas;

public class SimpleMetaAgent extends MetaAgent {

    public SimpleMetaAgent(String userName, Portal portal, Printer out) {
        super(userName, portal, out);
    }

    public SimpleMetaAgent(String userName, Printer out) {
        super(userName, out);
    }
}
