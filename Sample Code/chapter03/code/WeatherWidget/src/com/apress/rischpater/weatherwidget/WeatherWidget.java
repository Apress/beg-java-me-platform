/*
 * WeatherWidget.java
 *
 * Created on November 19, 2007, 7:58 PM
 */

package com.apress.rischpater.weatherwidget;

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

/**
 *
 * @author kf6gpe
 */
public class WeatherWidget extends MIDlet implements CommandListener {
    
    /**
     * Creates a new instance of WeatherWidget
     */
    public WeatherWidget() {
    }
    
    private Form wxForm;//GEN-BEGIN:MVDFields
    private StringItem locationItem;
    private Command exitCommand;
    private Command okCommand;
    private Command backCommand;
    private Spacer spacer1;
    private StringItem wxItem;
    private List locationList;
    private Command okCommand1;
    private Command backCommand1;
    private TextBox locationEntry;
    private Command itemCommand1;
    private Command okCommand2;
    private Command backCommand2;//GEN-END:MVDFields
    
//GEN-LINE:MVDMethods

    /** This method initializes UI of the application.//GEN-BEGIN:MVDInitBegin
     */
    private void initialize() {//GEN-END:MVDInitBegin
        // Insert pre-init code here
        getDisplay().setCurrent(get_wxForm());//GEN-LINE:MVDInitInit
        // Insert post-init code here
    }//GEN-LINE:MVDInitEnd
    
    /** Called by the system to indicate that a command has been invoked on a particular displayable.//GEN-BEGIN:MVDCABegin
     * @param command the Command that ws invoked
     * @param displayable the Displayable on which the command was invoked
     */
    public void commandAction(Command command, Displayable displayable) {//GEN-END:MVDCABegin
        // Insert global pre-action code here
        if (displayable == wxForm) {//GEN-BEGIN:MVDCABody
            if (command == exitCommand) {//GEN-END:MVDCABody
                // Insert pre-action code here
                exitMIDlet();//GEN-LINE:MVDCAAction3
                // Insert post-action code here
            } else if (command == okCommand) {//GEN-LINE:MVDCACase3
                // Insert pre-action code here
                getDisplay().setCurrent(get_locationList());//GEN-LINE:MVDCAAction30
                // Insert post-action code here
            }//GEN-BEGIN:MVDCACase30
        } else if (displayable == locationList) {
            if (command == backCommand1) {//GEN-END:MVDCACase30
                // Insert pre-action code here
                getDisplay().setCurrent(get_wxForm());//GEN-LINE:MVDCAAction32
                // Insert post-action code here
            } else if (command == itemCommand1) {//GEN-LINE:MVDCACase32
                // Insert pre-action code here
                getDisplay().setCurrent(get_wxForm());//GEN-LINE:MVDCAAction35
                // Insert post-action code here
            } else if (command == okCommand2) {//GEN-LINE:MVDCACase35
                // Insert pre-action code here
                getDisplay().setCurrent(get_locationEntry());//GEN-LINE:MVDCAAction37
                // Insert post-action code here
            }//GEN-BEGIN:MVDCACase37
        } else if (displayable == locationEntry) {
            if (command == backCommand2) {//GEN-END:MVDCACase37
                // Insert pre-action code here
                getDisplay().setCurrent(get_locationList());//GEN-LINE:MVDCAAction39
                // Insert post-action code here
            }//GEN-BEGIN:MVDCACase39
        }//GEN-END:MVDCACase39
        // Insert global post-action code here
}//GEN-LINE:MVDCAEnd
    
    /**
     * This method should return an instance of the display.
     */
    public Display getDisplay() {//GEN-FIRST:MVDGetDisplay
        return Display.getDisplay(this);
    }//GEN-LAST:MVDGetDisplay
    
    /**
     * This method should exit the midlet.
     */
    public void exitMIDlet() {//GEN-FIRST:MVDExitMidlet
        getDisplay().setCurrent(null);
        destroyApp(true);
        notifyDestroyed();
    }//GEN-LAST:MVDExitMidlet
    
    /** This method returns instance for wxForm component and should be called instead of accessing wxForm field directly.//GEN-BEGIN:MVDGetBegin2
     * @return Instance for wxForm component
     */
    public Form get_wxForm() {
        if (wxForm == null) {//GEN-END:MVDGetBegin2
            // Insert pre-init code here
            wxForm = new Form(null, new Item[] {//GEN-BEGIN:MVDGetInit2
                get_locationItem(),
                get_spacer1(),
                get_wxItem()
            });
            wxForm.addCommand(get_exitCommand());
            wxForm.addCommand(get_okCommand());
            wxForm.setCommandListener(this);//GEN-END:MVDGetInit2
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd2
        return wxForm;
    }//GEN-END:MVDGetEnd2
    
    /** This method returns instance for locationItem component and should be called instead of accessing locationItem field directly.//GEN-BEGIN:MVDGetBegin4
     * @return Instance for locationItem component
     */
    public StringItem get_locationItem() {
        if (locationItem == null) {//GEN-END:MVDGetBegin4
            // Insert pre-init code here
            locationItem = new StringItem("Location", "Berkeley, CA");//GEN-LINE:MVDGetInit4
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd4
        return locationItem;
    }//GEN-END:MVDGetEnd4
    
    /** This method returns instance for exitCommand component and should be called instead of accessing exitCommand field directly.//GEN-BEGIN:MVDGetBegin5
     * @return Instance for exitCommand component
     */
    public Command get_exitCommand() {
        if (exitCommand == null) {//GEN-END:MVDGetBegin5
            // Insert pre-init code here
            exitCommand = new Command("Exit", Command.EXIT, 1);//GEN-LINE:MVDGetInit5
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd5
        return exitCommand;
    }//GEN-END:MVDGetEnd5
    /** This method returns instance for okCommand component and should be called instead of accessing okCommand field directly.//GEN-BEGIN:MVDGetBegin11
     * @return Instance for okCommand component
     */
    public Command get_okCommand() {
        if (okCommand == null) {//GEN-END:MVDGetBegin11
            // Insert pre-init code here
            okCommand = new Command("Settings", Command.OK, 1);//GEN-LINE:MVDGetInit11
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd11
        return okCommand;
    }//GEN-END:MVDGetEnd11

    /** This method returns instance for backCommand component and should be called instead of accessing backCommand field directly.//GEN-BEGIN:MVDGetBegin13
     * @return Instance for backCommand component
     */
    public Command get_backCommand() {
        if (backCommand == null) {//GEN-END:MVDGetBegin13
            // Insert pre-init code here
            backCommand = new Command("Back", Command.BACK, 1);//GEN-LINE:MVDGetInit13
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd13
        return backCommand;
    }//GEN-END:MVDGetEnd13

    /** This method returns instance for spacer1 component and should be called instead of accessing spacer1 field directly.//GEN-BEGIN:MVDGetBegin15
     * @return Instance for spacer1 component
     */
    public Spacer get_spacer1() {
        if (spacer1 == null) {//GEN-END:MVDGetBegin15
            // Insert pre-init code here
            spacer1 = new Spacer(1000, 10);//GEN-LINE:MVDGetInit15
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd15
        return spacer1;
    }//GEN-END:MVDGetEnd15

    /** This method returns instance for wxItem component and should be called instead of accessing wxItem field directly.//GEN-BEGIN:MVDGetBegin16
     * @return Instance for wxItem component
     */
    public StringItem get_wxItem() {
        if (wxItem == null) {//GEN-END:MVDGetBegin16
            // Insert pre-init code here
            wxItem = new StringItem("Forecast", "Sunny.");//GEN-LINE:MVDGetInit16
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd16
        return wxItem;
    }//GEN-END:MVDGetEnd16

    /** This method returns instance for locationList component and should be called instead of accessing locationList field directly.//GEN-BEGIN:MVDGetBegin27
     * @return Instance for locationList component
     */
    public List get_locationList() {
        if (locationList == null) {//GEN-END:MVDGetBegin27
            // Insert pre-init code here
            locationList = new List("Weather", Choice.EXCLUSIVE, new String[0], new Image[0]);//GEN-BEGIN:MVDGetInit27
            locationList.addCommand(get_backCommand1());
            locationList.addCommand(get_itemCommand1());
            locationList.addCommand(get_okCommand2());
            locationList.setCommandListener(this);
            locationList.setSelectedFlags(new boolean[0]);
            locationList.setSelectCommand(get_itemCommand1());
            locationList.setFitPolicy(Choice.TEXT_WRAP_OFF);//GEN-END:MVDGetInit27
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd27
        return locationList;
    }//GEN-END:MVDGetEnd27

    /** This method returns instance for okCommand1 component and should be called instead of accessing okCommand1 field directly.//GEN-BEGIN:MVDGetBegin29
     * @return Instance for okCommand1 component
     */
    public Command get_okCommand1() {
        if (okCommand1 == null) {//GEN-END:MVDGetBegin29
            // Insert pre-init code here
            okCommand1 = new Command("Ok", Command.OK, 1);//GEN-LINE:MVDGetInit29
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd29
        return okCommand1;
    }//GEN-END:MVDGetEnd29

    /** This method returns instance for backCommand1 component and should be called instead of accessing backCommand1 field directly.//GEN-BEGIN:MVDGetBegin31
     * @return Instance for backCommand1 component
     */
    public Command get_backCommand1() {
        if (backCommand1 == null) {//GEN-END:MVDGetBegin31
            // Insert pre-init code here
            backCommand1 = new Command("Back", Command.BACK, 1);//GEN-LINE:MVDGetInit31
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd31
        return backCommand1;
    }//GEN-END:MVDGetEnd31

    /** This method returns instance for locationEntry component and should be called instead of accessing locationEntry field directly.//GEN-BEGIN:MVDGetBegin33
     * @return Instance for locationEntry component
     */
    public TextBox get_locationEntry() {
        if (locationEntry == null) {//GEN-END:MVDGetBegin33
            // Insert pre-init code here
            locationEntry = new TextBox("Add Location", "", 120, TextField.ANY);//GEN-BEGIN:MVDGetInit33
            locationEntry.addCommand(get_backCommand2());
            locationEntry.setCommandListener(this);//GEN-END:MVDGetInit33
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd33
        return locationEntry;
    }//GEN-END:MVDGetEnd33

    /** This method returns instance for itemCommand1 component and should be called instead of accessing itemCommand1 field directly.//GEN-BEGIN:MVDGetBegin34
     * @return Instance for itemCommand1 component
     */
    public Command get_itemCommand1() {
        if (itemCommand1 == null) {//GEN-END:MVDGetBegin34
            // Insert pre-init code here
            itemCommand1 = new Command("Item", Command.ITEM, 1);//GEN-LINE:MVDGetInit34
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd34
        return itemCommand1;
    }//GEN-END:MVDGetEnd34

    /** This method returns instance for okCommand2 component and should be called instead of accessing okCommand2 field directly.//GEN-BEGIN:MVDGetBegin36
     * @return Instance for okCommand2 component
     */
    public Command get_okCommand2() {
        if (okCommand2 == null) {//GEN-END:MVDGetBegin36
            // Insert pre-init code here
            okCommand2 = new Command("Ok", Command.OK, 1);//GEN-LINE:MVDGetInit36
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd36
        return okCommand2;
    }//GEN-END:MVDGetEnd36

    /** This method returns instance for backCommand2 component and should be called instead of accessing backCommand2 field directly.//GEN-BEGIN:MVDGetBegin38
     * @return Instance for backCommand2 component
     */
    public Command get_backCommand2() {
        if (backCommand2 == null) {//GEN-END:MVDGetBegin38
            // Insert pre-init code here
            backCommand2 = new Command("Back", Command.BACK, 1);//GEN-LINE:MVDGetInit38
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd38
        return backCommand2;
    }//GEN-END:MVDGetEnd38
  
      
    public void startApp() {
        initialize();
    }
    
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
    }
    
}
