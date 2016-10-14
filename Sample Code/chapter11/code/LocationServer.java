 
import java.rmi.Naming;

public class LocationServer {
   public LocationServer() {
       try {
           Location c = new LocationImpl();
           Naming.rebind("rmi://localhost:1099/LocationService", c);
       } catch (Exception e) {
           System.out.println("Exception: " + e);
       }
   }

   public static void main(String args[]) {
       new LocationServer();
   }
}
