import java.rmi.*;
import javax.microedition.xlet.*;
import javax.microedition.xlet.ixc.*;


public class XletLocationProducer implements Xlet {
	XletContext context;
	Location location;
    private static final String NAME =
                             "XletLocationProducer.location";


    public XletLocationProducer () {
    }

    public void initXlet( XletContext c )
    	throws XletStateChangeException {
		location = new Location();
		context = c;
		
		try {
			IxcRegistry registry = IxcRegistry.getRegistry(context);
		
		    registry.bind(NAME, location);
		}
		catch( AlreadyBoundException e ){
			throw new XletStateChangeException("Something bound");
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

    public void destroyXlet( boolean unconditional )
		throws XletStateChangeException {
        try {
            IxcRegistry registry = IxcRegistry.getRegistry(context);

            registry.unbindAll();
        }
        catch( Access e ){
            System.out.println("Permission failed");
        }
        catch( StubException e ){
           System.out.println("Stub error");
        }
    }

}
