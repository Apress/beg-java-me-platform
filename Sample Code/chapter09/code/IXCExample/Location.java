import java.rmi.*;

public class Location
	implements java.rmi.Remote {

	private String location;
	private String forecast;

	private Location() {
	}

	public setLocation(String l)
		throws RemoteException {
		location = l;
	}

	public setForecast(String f)
		throws RemoteException {
		forecast = f;
	}

	public getLocation()
		throws RemoteException {
		if (location != null) {
			return location;
		}
		else {
			return "";
		}
	}

	public getForecast()
		throws RemoteException {
		if (forecast != null) {
			return forecast;
		}
		else {
			return "";
		}
	}
}
