/*
 * WeatherWidget.java
 *
 * Created on March 1, 2008, 10:08 AM
 */

package com.apress.rischpater.weatherwidget;

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import java.util.*;

/**
 *
 * @author  kf6gpe
 * @version
 */
public class WeatherWidget extends MIDlet implements CommandListener {
    private Form wxForm;
    private StringItem locationItem;
    private Command exitCommand;
    private Command screenCommand;
    private List locationList;
    private TextBox locationTextBox;
    private Command settingCommand;
    private Command okCommand;
    private Command backCommand;
    private StringItem wxItem;
  
    String location;
    Vector locationVector;

    /** This method initializes UI of the application.
     */
    private void initialize() {
        locationVector = new Vector();
	
        getDisplay().setCurrent(get_wxForm());
    }

    public void startApp() {
        initialize();
    }
    
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
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
	    if (command == screenCommand) {
                getDisplay().setCurrent(get_locationTextBox());
	    } else if (command == List.SELECT_COMMAND) {
                int index = get_locationList().getSelectedIndex();
                set_location(get_locationList().getString(index));
                getDisplay().setCurrent(get_wxForm());
	    } else if (command == backCommand) {
                getDisplay().setCurrent(get_wxForm());
            }
        } else if (displayable == locationTextBox) {
            if (command == backCommand) {
                getDisplay().setCurrent(get_locationList());
            } else if (command == okCommand) {
                add_location(locationTextBox.getString());
                getDisplay().setCurrent(get_locationList());
            }
        }
    }
    
    public String get_location() {
        if (location == null) {
            location = "Berkeley, CA";
        }
        return location;
    }
    
    public void set_location( String l ) {
	location = l;
        get_wxForm().setTitle(l);
    }

    public void add_location( String l ) {
        locationVector.addElement(l); 
        get_locationList().append(l,null);
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
            wxItem = new StringItem("Forecast", "Sunny.");
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
            // Insert post-init code here
        }
        return wxForm;
    }
    
    /** This method returns instance for locationTextBox component and should be called instead of accessing locationTextBox field directly.
     * @return Instance for locationTextBox component
     */
    public TextBox get_locationTextBox() {
        if (locationTextBox == null) {
            locationTextBox = new TextBox("Add Location", "", 80, 0);
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
            locations = new String[locationVector.size()];
            
            locationVector.copyInto((Object[])locations);
            locationList = new List("Where", List.IMPLICIT, locations, null);
            locationList.addCommand(get_screenCommand());
            locationList.addCommand(get_backCommand());
            locationList.setCommandListener(this);
        }
        return locationList;
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
}
