import javax.ejb.*;
import java.io.Serializable;
import java.util.*;
import java.rmi.*;
  
public class LocationBean 
    extends SessionBean 
    implements Location {
    private String location;
    private String forecast;
    
    public LocationBean()
        throws java.rmi.RemoteException {
	    super();
    }
    
    public String getLocation()
        throws java.rmi.RemoteException {
        if (location != null) {
            return location;
        } else {
            return "";
        }
    }
    
    public void setLocation(String l)
        throws java.rmi.RemoteException {
        location = l;
    }
    
    public String getForecast() 
        throws java.rmi.RemoteException {
        if (forecast != null) {
            return forecast;
        } else {
            return "";
        }
    }
    
    public void setForecast(String f)
        throws java.rmi.RemoteException {
        forecast = f;
    }
  
    private transient SessionContext ctx;
    private transient Properties props;
  
  	public void ejbActivate() {
        System.out.println("ejbActivate called");
    }
  
	public void ejbRemove() {
    	System.out.println("ejbRemove called");
	}
  
	public void ejbPassivate() {
 		System.out.println("ejbPassivate called");
	}
  
	public void setSessionContext(SessionContext ctx) {    
    	System.out.println("setSessionContext called");
		this.ctx = ctx;
		props = ctx.getEnvironment();
	}
    
	public void ejbCreate () {
 		System.out.println("ejbCreate called");
	}
}