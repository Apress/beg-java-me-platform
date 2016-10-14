import java.rmi.*;
import javax.microedition.xlet.*;
import javax.microedition.xlet.ixc.*;


public class XletLocationConsumer implements Xlet {
	XletContext context;
	Location location;
    private static final String NAME =
                             "XletLocationProducer.location";


    public XletLocationConsumer () {
    }

    public void initXlet( XletContext c )
    	throws XletStateChangeException {
		context = c;
		
		try {
			IxcRegistry registry = IxcRegistry.getRegistry(context);
		
		    location = (Location)registry.lookup(NAME);
		}
		catch( NotBoundException e ){
			throw new XletStateChangeException("Nothing bound");
		}
		catch( StubException e ){
			throw new XletStateChangeException("Stub error");
		}
    }

    public void pauseXlet() {
    }

    public void startXlet() 
		throws XletStateChangeException {
    }

    public void destroyXlet( boolean b )
		throws XletStateChangeException {
    }

}
