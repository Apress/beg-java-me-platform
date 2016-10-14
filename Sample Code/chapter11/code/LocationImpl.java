public class LocationImpl
    extends 
        java.rmi.server.UnicastRemoteObject
    implements Location {
    private String location;
    private String forecast;
    
    /** Creates a new instance of Location */
    public LocationImpl()
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
}
