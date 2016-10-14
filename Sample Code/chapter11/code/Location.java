public interface Location 
    extends java.rmi.Remote {
    public String getLocation() 
        throws java.rmi.RemoteException;
    
    public void setLocation(String l)
        throws java.rmi.RemoteException;
    
    public String getForecast()
        throws java.rmi.RemoteException;
    
    public void setForecast(String f) 
        throws java.rmi.RemoteException;
}
