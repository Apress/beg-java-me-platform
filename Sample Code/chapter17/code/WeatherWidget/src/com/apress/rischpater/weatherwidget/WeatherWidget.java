/*
 * WeatherWidget.java
 *
 * Created on March 1, 2008, 10:08 AM
 */

package com.apress.rischpater.weatherwidget;

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import javax.microedition.location.*;
/**
 *
 * @author  kf6gpe
 * @version
 */
public class WeatherWidget extends MIDlet 
    implements CommandListener, Runnable {
    private Form wxForm;
    private Alert locatingAlert;
    private StringItem wxItem;
    private Command exitCommand;
    private Command screenCommand;
    private Command settingCommand;
    private Command okCommand;
    private Command backCommand;
    private Command locateCommand;
    private List locationList;
    private TextBox locationTextBox;
    private Alert cannotAddLocationAlert;
    private WeatherFetcher fetcher;
    private WeatherLocation wxlocation;
    private WeatherLocationStore locationStore;

    /** This method initializes UI of the application.
     */
    private void initialize() {
        locationStore = new WeatherLocationStore();
        String[] locations = locationStore.getLocationStrings();
        try {
            if (locations.length > 0 ) 
                wxlocation = locationStore.getLocation(locations[0]);
        }
        catch(Exception e){}
        fetcher = new WeatherFetcher(wxlocation, this);
        getDisplay().setCurrent(get_wxForm());
    }

    public void startApp() {
        initialize();
    }
    
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
    }

    public void update() {
        get_wxItem().setText(get_forecast());
        try
        {
            locationStore.updateLocation(wxlocation);
        }
        catch(Exception e){}
    }
    
    public void commandAction(Command command, Displayable displayable) {
        // Insert global pre-action code here
        if (displayable == wxForm) {
            if (command == exitCommand) {
                exitMIDlet();
            } else if (command == settingCommand) {
                getDisplay().setCurrent(get_locationList());
            }
	} else if (displayable == locationList) {
            if (command == locateCommand) {
                getDisplay().setCurrent(get_locatingAlert());
                Thread t = new Thread(this);
                t.start();
            } else if (command == screenCommand) {
                getDisplay().setCurrent(get_locationTextBox());
	    } else if (command == List.SELECT_COMMAND) {
                int index = get_locationList().getSelectedIndex();
                set_location(get_locationList().getString(index));
                fetcher.cancel();
                fetcher = new WeatherFetcher(wxlocation, this);
                getDisplay().setCurrent(get_wxForm());
	    } else if (command == backCommand) {
                getDisplay().setCurrent(get_wxForm());
            }
        } else if (displayable == locationTextBox) {
            if (command == locateCommand) {
                getDisplay().setCurrent(get_locatingAlert());
                Thread t = new Thread(this);
                t.start();
            } else if (command == backCommand) {
                getDisplay().setCurrent(get_locationList());
            } else if (command == okCommand) {
                add_location(locationTextBox.getString());
                getDisplay().setCurrent(get_locationList());
            }
        } else if (displayable == cannotAddLocationAlert) {
            if (command == backCommand) {
                getDisplay().setCurrent(get_locationList());                
            }
        }
    }

    public String get_forecast() {
        if (wxlocation == null) {
            return "unknown forecast";
        } else {
            return wxlocation.getForecast();
        }
    }
        
    
    public String get_location() {
        if (wxlocation == null) {
            return "unknown";
        } else {
            return wxlocation.getLocation();
        }
    }
    
    public void set_location( String l ) {
        try {
            wxlocation = locationStore.getLocation(l);
        }
        catch(Exception e) {e.printStackTrace();}
        get_locationTextBox().setString(l);
        get_wxForm().setTitle(l);
    }

    public void add_location( String l ) {
        wxlocation = new WeatherLocation(l);
        try {
            locationStore.addLocation( wxlocation );
        } catch (Exception e) {
            getDisplay().setCurrent(get_cannotAddLocationAlert());
        }
        // Refresh the wxlocation list lazily.
        locationList = null;
    }


    /**
     * This method should return an instance of the display.
     */
    public Display getDisplay() {
        return Display.getDisplay(this);
    }
    
    /**
     * This method should exit the midlet.
     */
    public void exitMIDlet() {
        getDisplay().setCurrent(null);
        destroyApp(true);
        notifyDestroyed();
    }
    

    /** This method returns instance for wxItem component and should be called instead of accessing wxItem field directly.
     * @return Instance for wxItem component
     */
    public StringItem get_wxItem() {
        if (wxItem == null) {
            wxItem = new StringItem("Forecast", get_forecast());
        }
        return wxItem;
    }

    /** This method returns instance for wxForm component and should be called instead of accessing wxForm field directly.
     * @return Instance for wxForm component
     */
    public Form get_wxForm() {
        if (wxForm == null) {
            wxForm = new Form(get_location(), new Item[] {
                get_wxItem()
            });
            wxForm.addCommand(get_exitCommand());
            wxForm.addCommand(get_settingCommand());
            wxForm.setCommandListener(this);
        }
        return wxForm;
    }

    public Alert get_locatingAlert() {
        if (locatingAlert == null) {
            locatingAlert = new Alert("Locating", "Finding your location",
                    null, null);
            locatingAlert.setTimeout(Alert.FOREVER);
        }
        return locatingAlert;
    }

    /** This method returns instance for locationTextBox component and should be called instead of accessing locationTextBox field directly.
     * @return Instance for locationTextBox component
     */
    public TextBox get_locationTextBox() {
        if (locationTextBox == null) {
            locationTextBox = new TextBox("Add Location", "", 80, 0);
            locationTextBox.addCommand(get_locateCommand());
            locationTextBox.addCommand(get_backCommand());
            locationTextBox.addCommand(get_okCommand());
            locationTextBox.setCommandListener(this);
        }
        return locationTextBox;
    }

    /** This method returns instance for locationList component and should be called instead of accessing locationList field directly.
     * @return Instance for locationList component
     */
    public List get_locationList() {
        if (locationList == null) {
            String[] locations;
            locations = locationStore.getLocationStrings();
            
            locationList = new List("Where", List.IMPLICIT, locations, null);
            locationList.addCommand(get_screenCommand());
            locationList.addCommand(get_backCommand());
            locationList.addCommand(get_locateCommand());
            locationList.setCommandListener(this);
        }
        return locationList;
    }

    public Alert get_cannotAddLocationAlert() {
        if (cannotAddLocationAlert == null)
        {
           cannotAddLocationAlert = new Alert("Cannot Add Location");
           cannotAddLocationAlert.setString("An error occurred adding ~CCC
the location you entered. It has not been added.");
           cannotAddLocationAlert.addCommand(get_backCommand());
        }
        return cannotAddLocationAlert;
    }
    
    /** This method returns instance for settingCommand component and should be called instead of accessing settingCommand field directly.
     * @return Instance for settingCommand component
     */
    public Command get_settingCommand() {
        if (settingCommand == null) {
            settingCommand = new Command("Settings", Command.OK, 1);
        }
        return settingCommand;
    }

    /** This method returns instance for settingCommand component and should be called instead of accessing settingCommand field directly.
     * @return Instance for settingCommand component
     */
    public Command get_okCommand() {
        if (okCommand == null) {
            okCommand = new Command("OK", Command.OK, 1);
        }
        return okCommand;
    }

    public Command get_locateCommand() {
        if (locateCommand == null) {
            locateCommand = new Command("Use this location", Command.ITEM, 1);
        }
        return locateCommand;
    }


    /** This method returns instance for exitCommand component and should be called instead of accessing exitCommand field directly.
     * @return Instance for exitCommand component
     */
    public Command get_exitCommand() {
        if (exitCommand == null) {
            exitCommand = new Command("Exit", Command.EXIT, 1);
        }
        return exitCommand;
    }

    /** This method returns instance for screenCommand component and should be called instead of accessing screenCommand field directly.
     * @return Instance for itemCommand component
     */
    public Command get_screenCommand() {
        if (screenCommand == null) {
            screenCommand = new Command("Add Location", Command.SCREEN, 1);
        }
        return screenCommand;
    }


    /** This method returns instance for backCommand component and should be called instead of accessing backCommand field directly.
     * @return Instance for backCommand component
     */
    public Command get_backCommand() {
        if (backCommand == null) {
            backCommand = new Command("Back", Command.BACK, 1);
        }
        return backCommand;
    }

    private class LocationUpdater implements Runnable {
        String location;
        public LocationUpdater(String l) {
            location = l;
        }
        public void run() {
            add_location(location);
            set_location(location);
            getDisplay().setCurrent(get_locationTextBox());
        }
    }

    public void run() {
        Criteria criteria = new Criteria();
        LocationProvider lp;
        StringBuffer cl = new StringBuffer();
        String location;

        criteria.setCostAllowed(true);
        criteria.setAddressInfoRequired(true);
        criteria.setHorizontalAccuracy(100);

        try {
            lp = LocationProvider.getInstance(criteria);
            Location l = lp.getLocation(60);
            if ( l != null && l.isValid() ) {
                AddressInfo ai = l.getAddressInfo();
                QualifiedCoordinates c = l.getQualifiedCoordinates();
                if ( ai != null ) {
                    cl.append(ai.getField(AddressInfo.CITY));
                    cl.append(", ");
                    cl.append(ai.getField(AddressInfo.STATE));
                    cl.append(", ");
                    cl.append(ai.getField(AddressInfo.COUNTRY));
                } else {
                    int r = QualifiedCoordinates.DD_MM;
                    String s;
                    cl.append("GPS Coordinates, ");
                    s = QualifiedCoordinates.convert(c.getLatitude(), r);
                    cl.append(s);
                    cl.append(", ");
                    s = QualifiedCoordinates.convert(c.getLongitude(), r);
                    cl.append(s);
                }
            }
        } catch(Exception e) {e.printStackTrace();};
        if ( cl.length() > 0 ) {
            location = cl.toString();
        } else {
            location = "Could not determine location.";
        }
        // Update later
        Display.getDisplay(this).callSerially(new LocationUpdater(location));
    }
}
