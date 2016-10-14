/*
 * AlertDemo.java
 *
 * Copyright (c) 2006 Sun Microsystems, Inc.  All rights reserved.
 * Use is subject to license terms.
 */
package example.alert;

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;


public class AlertDemo extends MIDlet implements javax.microedition.lcdui.CommandListener {
    
    private final String[] typeStrings = {"Alarm", "Confirmation", "Error", "Info", "Warning"};
    private final String[] timeoutStrings = {"2 Seconds", "4 Seconds", "8 Seconds", "Forever"};
    private final AlertType[] alertTypes = {
            AlertType.ALARM, AlertType.CONFIRMATION, AlertType.ERROR, 
            AlertType.INFO, AlertType.WARNING
        };
    private final int[] timeouts = { 2 * SECOND, 4 * SECOND, 8 * SECOND, Alert.FOREVER };
    private final static int SECOND = 1000;
    
    /** Creates a new instance of AlertDemo */
    public AlertDemo() {
    }
    
    private Form mainForm;//GEN-BEGIN:MVDFields
    private ChoiceGroup typeGroup;
    private ChoiceGroup timeoutGroup;
    private ChoiceGroup optionsGroup;
    private Alert alert;
    private Command showCommand;
    private Command exitCommand;//GEN-END:MVDFields

//GEN-LINE:MVDMethods

    /** This method initializes UI of the application.//GEN-BEGIN:MVDInitBegin
     */
    private void initialize() {//GEN-END:MVDInitBegin
        // Insert pre-init code here
        getDisplay().setCurrent(get_mainForm());//GEN-LINE:MVDInitInit
        // Insert post-init code here
    }//GEN-LINE:MVDInitEnd

    /**
     * This method should return an instance of the display.
     */
    public javax.microedition.lcdui.Display getDisplay () {//GEN-FIRST:MVDGetDisplay
        return javax.microedition.lcdui.Display.getDisplay (this);
    }//GEN-LAST:MVDGetDisplay

    /**
     * This method should exit the midlet.
     */
    public void exitMIDlet () {//GEN-FIRST:MVDExitMidlet
        getDisplay ().setCurrent (null);
        destroyApp (true);
        notifyDestroyed ();
    }//GEN-LAST:MVDExitMidlet

    /** Called by the system to indicate that a command has been invoked on a particular displayable.//GEN-BEGIN:MVDCABegin
     * @param command the Command that ws invoked
     * @param displayable the Displayable on which the command was invoked
     */
    public void commandAction(Command command, Displayable displayable) {//GEN-END:MVDCABegin
    // Insert global pre-action code here
        if (displayable == mainForm) {//GEN-BEGIN:MVDCABody
            if (command == showCommand) {//GEN-END:MVDCABody
                setUpAlert();
                getDisplay().setCurrent(get_alert(), get_mainForm());//GEN-LINE:MVDCAAction8
                // Insert post-action code here
            } else if (command == exitCommand) {//GEN-LINE:MVDCACase8
                // Insert pre-action code here
                exitMIDlet();//GEN-LINE:MVDCAAction10
                // Insert post-action code here
            }//GEN-BEGIN:MVDCACase10
        }//GEN-END:MVDCACase10
    // Insert global post-action code here
}//GEN-LINE:MVDCAEnd

    /** This method returns instance for mainForm component and should be called instead of accessing mainForm field directly.//GEN-BEGIN:MVDGetBegin2
     * @return Instance for mainForm component
     */
    public Form get_mainForm() {
        if (mainForm == null) {//GEN-END:MVDGetBegin2
            // Insert pre-init code here
            mainForm = new Form("Alert Options", new Item[] {//GEN-BEGIN:MVDGetInit2
                get_typeGroup(),
                get_timeoutGroup(),
                get_optionsGroup()
            });
            mainForm.addCommand(get_showCommand());
            mainForm.addCommand(get_exitCommand());
            mainForm.setCommandListener(this);//GEN-END:MVDGetInit2
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd2
        return mainForm;
    }//GEN-END:MVDGetEnd2

    /** This method returns instance for typeGroup component and should be called instead of accessing typeGroup field directly.//GEN-BEGIN:MVDGetBegin3
     * @return Instance for typeGroup component
     */
    public ChoiceGroup get_typeGroup() {
        if (typeGroup == null) {//GEN-END:MVDGetBegin3
            // Insert pre-init code here
            typeGroup = new ChoiceGroup("Type", Choice.POPUP, new String[0], new Image[0]);//GEN-BEGIN:MVDGetInit3
            typeGroup.setSelectedFlags(new boolean[0]);//GEN-END:MVDGetInit3
            fillTypeGroup();
        }//GEN-BEGIN:MVDGetEnd3
        return typeGroup;
    }//GEN-END:MVDGetEnd3

    /** This method returns instance for timeoutGroup component and should be called instead of accessing timeoutGroup field directly.//GEN-BEGIN:MVDGetBegin4
     * @return Instance for timeoutGroup component
     */
    public ChoiceGroup get_timeoutGroup() {
        if (timeoutGroup == null) {//GEN-END:MVDGetBegin4
            // Insert pre-init code here
            timeoutGroup = new ChoiceGroup("Timeout", Choice.POPUP, new String[0], new Image[0]);//GEN-BEGIN:MVDGetInit4
            timeoutGroup.setSelectedFlags(new boolean[0]);//GEN-END:MVDGetInit4
            fillTimeoutGroup();
        }//GEN-BEGIN:MVDGetEnd4
        return timeoutGroup;
    }//GEN-END:MVDGetEnd4

    /** This method returns instance for optionsGroup component and should be called instead of accessing optionsGroup field directly.//GEN-BEGIN:MVDGetBegin5
     * @return Instance for optionsGroup component
     */
    public ChoiceGroup get_optionsGroup() {
        if (optionsGroup == null) {//GEN-END:MVDGetBegin5
            // Insert pre-init code here
            optionsGroup = new ChoiceGroup("Options", Choice.MULTIPLE, new String[0], new Image[0]);//GEN-BEGIN:MVDGetInit5
            optionsGroup.setSelectedFlags(new boolean[0]);//GEN-END:MVDGetInit5
            this.get_optionsGroup().append("Show Indicator",null);
        }//GEN-BEGIN:MVDGetEnd5
        return optionsGroup;
    }//GEN-END:MVDGetEnd5

    /** This method returns instance for alert component and should be called instead of accessing alert field directly.//GEN-BEGIN:MVDGetBegin6
     * @return Instance for alert component
     */
    public Alert get_alert() {
        if (alert == null) {//GEN-END:MVDGetBegin6
            // Insert pre-init code here
            alert = new Alert("Alert", null, null, AlertType.INFO);//GEN-BEGIN:MVDGetInit6
            alert.setTimeout(-2);//GEN-END:MVDGetInit6
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd6
        return alert;
    }//GEN-END:MVDGetEnd6

    /** This method returns instance for showCommand component and should be called instead of accessing showCommand field directly.//GEN-BEGIN:MVDGetBegin7
     * @return Instance for showCommand component
     */
    public Command get_showCommand() {
        if (showCommand == null) {//GEN-END:MVDGetBegin7
            // Insert pre-init code here
            showCommand = new Command("Show", Command.OK, 1);//GEN-LINE:MVDGetInit7
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd7
        return showCommand;
    }//GEN-END:MVDGetEnd7

    /** This method returns instance for exitCommand component and should be called instead of accessing exitCommand field directly.//GEN-BEGIN:MVDGetBegin9
     * @return Instance for exitCommand component
     */
    public Command get_exitCommand() {
        if (exitCommand == null) {//GEN-END:MVDGetBegin9
            // Insert pre-init code here
            exitCommand = new Command("Exit", Command.EXIT, 1);//GEN-LINE:MVDGetInit9
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd9
        return exitCommand;
    }//GEN-END:MVDGetEnd9
    
    private void fillTypeGroup() {
        for (int i=0;i<typeStrings.length;i++) {
            this.get_typeGroup().append(typeStrings[i],null);
        }
    }
    
    private void fillTimeoutGroup() {
        for (int i=0;i<timeoutStrings.length;i++) {
            this.get_timeoutGroup().append(timeoutStrings[i],null);
        }
    }
    
    private void setUpAlert() {
        int typeIndex = this.get_typeGroup().getSelectedIndex();
        get_alert().setType(this.alertTypes[typeIndex]);
        
        int timeoutIndex = this.get_timeoutGroup().getSelectedIndex();
        get_alert().setTimeout(timeouts[timeoutIndex]);
        get_alert().setString(
                typeStrings[typeIndex] + " Alert, Running " + timeoutStrings[timeoutIndex]);
        get_alert().setString("Here's an alert with a gauge!");
        boolean[] SelectedFlags = new boolean[1];
        this.get_optionsGroup().getSelectedFlags(SelectedFlags);
        
        if (SelectedFlags[0]) {
            
            Gauge indicator = createIndicator(timeouts[timeoutIndex]);
            get_alert().setIndicator(indicator);
        }
        else
        {
            try {
                get_alert().setImage( Image.createImage("/icons/Icon.png"));
            }
            catch(Exception e){}
        }
    }
    
    /**
     * Creates the alert's indicator.
     * If there is no timeout (maxValue == Alert.FOREVER), the indicator will be
     * an "indefinite-running" gauge.
     * If there is a timeout, the indicator will be a "non-interactive" gauge
     * that is updated by a background thread.
     */
    private Gauge createIndicator(int maxValue) {
        
        if (maxValue == Alert.FOREVER) {
            
            return new Gauge(null, false, Gauge.INDEFINITE,
                    Gauge.CONTINUOUS_RUNNING);
        }
        
        final int max = maxValue / SECOND;
        final Gauge indicator = new Gauge(null, false, max, 0);
        
        new Thread() {
            public void run() {
                
                int value = 0;
                
                while (value < max) {
                    indicator.setValue(value);
                    ++value;
                    
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ie) {
                        // ignore
                    }
                }
            }
        }.start();
        
        return indicator;
    }
    
    public void startApp() {
        initialize();
    }
    
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
    }
    
}
