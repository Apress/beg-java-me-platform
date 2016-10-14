/*
 * StringItemDemo.java
 *
 * Copyright (c) 2006 Sun Microsystems, Inc.  All rights reserved.
 * Use is subject to license terms.
 */
package example.stringitem;

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

public class StringItemDemo extends MIDlet implements javax.microedition.lcdui.CommandListener, javax.microedition.lcdui.ItemCommandListener {
    
    /** Creates a new instance of StringItemDemo */
    public StringItemDemo() {
    }
    
    private Form mainForm;//GEN-BEGIN:MVDFields
    private Command exitCommand;
    private StringItem stringItem1;
    private StringItem stringItem2;
    private StringItem stringItem4;
    private StringItem stringItem5;
    private Command goCommand;
    private Command pressCommand;
    private Command okCommand1;
    private Command okCommand2;
    private Command helpCommand1;
    private Command stopCommand1;//GEN-END:MVDFields

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

    /** Called by the system to indicate that a command has been invoked on a particular item.//GEN-BEGIN:MVDICABegin
     * @param command the Command that ws invoked
     * @param item the Item on which the command was invoked
     */
    public void commandAction(Command command, Item item) {//GEN-END:MVDICABegin
    // Insert global pre-action code here
        if (item == stringItem5) {//GEN-BEGIN:MVDICABody
            if (command == pressCommand) {//GEN-END:MVDICABody
                Display.getDisplay(this).setCurrent(new Alert("Action", "Do an action...", null, AlertType.INFO), get_mainForm());
                // Do nothing//GEN-LINE:MVDICAAction13
                // Insert post-action code here
            }//GEN-BEGIN:MVDICACase13
        } else if (item == stringItem4) {
            if (command == goCommand) {//GEN-END:MVDICACase13
                Display.getDisplay(this).setCurrent(new Alert("URL", "Go to the URL...", null, AlertType.INFO), get_mainForm());
                // Do nothing//GEN-LINE:MVDICAAction11
                // Insert post-action code here
            }//GEN-BEGIN:MVDICACase11
        }//GEN-END:MVDICACase11
    // Insert global post-action code here
}//GEN-LINE:MVDICAEnd

    /** Called by the system to indicate that a command has been invoked on a particular displayable.//GEN-BEGIN:MVDCABegin
     * @param command the Command that ws invoked
     * @param displayable the Displayable on which the command was invoked
     */
    public void commandAction(Command command, Displayable displayable) {//GEN-END:MVDCABegin
    // Insert global pre-action code here
        if (displayable == mainForm) {//GEN-BEGIN:MVDCABody
            if (command == exitCommand) {//GEN-END:MVDCABody
                // Insert pre-action code here
                exitMIDlet();//GEN-LINE:MVDCAAction4
                // Insert post-action code here
            } else if (command == helpCommand1) {//GEN-LINE:MVDCACase4
                // Insert pre-action code here
                // Do nothing//GEN-LINE:MVDCAAction20
                // Insert post-action code here
            } else if (command == stopCommand1) {//GEN-LINE:MVDCACase20
                // Insert pre-action code here
                // Do nothing//GEN-LINE:MVDCAAction22
                // Insert post-action code here
            }//GEN-BEGIN:MVDCACase22
        }//GEN-END:MVDCACase22
    // Insert global post-action code here
}//GEN-LINE:MVDCAEnd

    /** This method returns instance for mainForm component and should be called instead of accessing mainForm field directly.//GEN-BEGIN:MVDGetBegin2
     * @return Instance for mainForm component
     */
    public Form get_mainForm() {
        if (mainForm == null) {//GEN-END:MVDGetBegin2
            // Insert pre-init code here
            mainForm = new Form("StringItems", new Item[] {//GEN-BEGIN:MVDGetInit2
                get_stringItem1(),
                get_stringItem2(),
                get_stringItem4(),
                get_stringItem5()
            });
            mainForm.addCommand(get_exitCommand());
            mainForm.addCommand(get_helpCommand1());
            mainForm.addCommand(get_stopCommand1());
            mainForm.setCommandListener(this);//GEN-END:MVDGetInit2
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd2
        return mainForm;
    }//GEN-END:MVDGetEnd2

    /** This method returns instance for exitCommand component and should be called instead of accessing exitCommand field directly.//GEN-BEGIN:MVDGetBegin3
     * @return Instance for exitCommand component
     */
    public Command get_exitCommand() {
        if (exitCommand == null) {//GEN-END:MVDGetBegin3
            // Insert pre-init code here
            exitCommand = new Command("Exit", Command.EXIT, 0);//GEN-LINE:MVDGetInit3
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd3
        return exitCommand;
    }//GEN-END:MVDGetEnd3

    /** This method returns instance for stringItem1 component and should be called instead of accessing stringItem1 field directly.//GEN-BEGIN:MVDGetBegin5
     * @return Instance for stringItem1 component
     */
    public StringItem get_stringItem1() {
        if (stringItem1 == null) {//GEN-END:MVDGetBegin5
            // Insert pre-init code here
            stringItem1 = new StringItem(null, "This is a simple StringItem");//GEN-LINE:MVDGetInit5
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd5
        return stringItem1;
    }//GEN-END:MVDGetEnd5

    /** This method returns instance for stringItem2 component and should be called instead of accessing stringItem2 field directly.//GEN-BEGIN:MVDGetBegin6
     * @return Instance for stringItem2 component
     */
    public StringItem get_stringItem2() {
        if (stringItem2 == null) {//GEN-END:MVDGetBegin6
            // Insert pre-init code here
            stringItem2 = new StringItem("This is the StringItem label:", "This is the StringItem text");//GEN-BEGIN:MVDGetInit6
            stringItem2.setLayout(Item.LAYOUT_NEWLINE_AFTER | Item.LAYOUT_2);//GEN-END:MVDGetInit6
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd6
        return stringItem2;
    }//GEN-END:MVDGetEnd6
    /** This method returns instance for stringItem4 component and should be called instead of accessing stringItem4 field directly.//GEN-BEGIN:MVDGetBegin8
     * @return Instance for stringItem4 component
     */
    public StringItem get_stringItem4() {
        if (stringItem4 == null) {//GEN-END:MVDGetBegin8
            // Insert pre-init code here
            stringItem4 = new StringItem("Hyper-Link", "hyperlink", javax.microedition.lcdui.Item.HYPERLINK);//GEN-BEGIN:MVDGetInit8
            stringItem4.addCommand(get_goCommand());
            stringItem4.setItemCommandListener(this);
            stringItem4.setLayout(Item.LAYOUT_NEWLINE_AFTER | Item.LAYOUT_2);//GEN-END:MVDGetInit8
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd8
        return stringItem4;
    }//GEN-END:MVDGetEnd8

    /** This method returns instance for stringItem5 component and should be called instead of accessing stringItem5 field directly.//GEN-BEGIN:MVDGetBegin9
     * @return Instance for stringItem5 component
     */
    public StringItem get_stringItem5() {
        if (stringItem5 == null) {//GEN-END:MVDGetBegin9
            // Insert pre-init code here
            stringItem5 = new StringItem("Button", "Button", javax.microedition.lcdui.Item.BUTTON);//GEN-BEGIN:MVDGetInit9
            stringItem5.addCommand(get_pressCommand());
            stringItem5.setItemCommandListener(this);
            stringItem5.setLayout(Item.LAYOUT_NEWLINE_AFTER | Item.LAYOUT_2);//GEN-END:MVDGetInit9
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd9
        return stringItem5;
    }//GEN-END:MVDGetEnd9

    /** This method returns instance for goCommand component and should be called instead of accessing goCommand field directly.//GEN-BEGIN:MVDGetBegin10
     * @return Instance for goCommand component
     */
    public Command get_goCommand() {
        if (goCommand == null) {//GEN-END:MVDGetBegin10
            // Insert pre-init code here
            goCommand = new Command("Go", Command.OK, 1);//GEN-LINE:MVDGetInit10
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd10
        return goCommand;
    }//GEN-END:MVDGetEnd10

    /** This method returns instance for pressCommand component and should be called instead of accessing pressCommand field directly.//GEN-BEGIN:MVDGetBegin12
     * @return Instance for pressCommand component
     */
    public Command get_pressCommand() {
        if (pressCommand == null) {//GEN-END:MVDGetBegin12
            // Insert pre-init code here
            pressCommand = new Command("Press", Command.SCREEN, 1);//GEN-LINE:MVDGetInit12
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd12
        return pressCommand;
    }//GEN-END:MVDGetEnd12

    /** This method returns instance for okCommand1 component and should be called instead of accessing okCommand1 field directly.//GEN-BEGIN:MVDGetBegin15
     * @return Instance for okCommand1 component
     */
    public Command get_okCommand1() {
        if (okCommand1 == null) {//GEN-END:MVDGetBegin15
            // Insert pre-init code here
            okCommand1 = new Command("Okay", Command.OK, 1);//GEN-LINE:MVDGetInit15
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd15
        return okCommand1;
    }//GEN-END:MVDGetEnd15

    /** This method returns instance for okCommand2 component and should be called instead of accessing okCommand2 field directly.//GEN-BEGIN:MVDGetBegin17
     * @return Instance for okCommand2 component
     */
    public Command get_okCommand2() {
        if (okCommand2 == null) {//GEN-END:MVDGetBegin17
            // Insert pre-init code here
            okCommand2 = new Command("Do Nothing", Command.SCREEN, 2);//GEN-LINE:MVDGetInit17
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd17
        return okCommand2;
    }//GEN-END:MVDGetEnd17

    /** This method returns instance for helpCommand1 component and should be called instead of accessing helpCommand1 field directly.//GEN-BEGIN:MVDGetBegin19
     * @return Instance for helpCommand1 component
     */
    public Command get_helpCommand1() {
        if (helpCommand1 == null) {//GEN-END:MVDGetBegin19
            // Insert pre-init code here
            helpCommand1 = new Command("Help", Command.HELP, 1);//GEN-LINE:MVDGetInit19
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd19
        return helpCommand1;
    }//GEN-END:MVDGetEnd19

    /** This method returns instance for stopCommand1 component and should be called instead of accessing stopCommand1 field directly.//GEN-BEGIN:MVDGetBegin21
     * @return Instance for stopCommand1 component
     */
    public Command get_stopCommand1() {
        if (stopCommand1 == null) {//GEN-END:MVDGetBegin21
            // Insert pre-init code here
            stopCommand1 = new Command("Stop", Command.STOP, 1);//GEN-LINE:MVDGetInit21
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd21
        return stopCommand1;
    }//GEN-END:MVDGetEnd21
    
    public void startApp() {
        initialize();
    }
    
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
    }
    
}
