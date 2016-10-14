/*
 * CustomItemDemo.java
 *
 * Copyright (c) 2006 Sun Microsystems, Inc.  All rights reserved.
 * Use is subject to license terms.
 */
package example.customitem;

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

public class CustomItemDemo extends MIDlet implements javax.microedition.lcdui.CommandListener {
    
    /** Creates a new instance of CustomItemDemo */
    public CustomItemDemo() {
    }
    
    private Form mainForm;//GEN-BEGIN:MVDFields
    private example.customitem.Table customItem1;
    private Command exitCommand;
    private TextField textField1;
    private TextField textField2;//GEN-END:MVDFields

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
            if (command == exitCommand) {//GEN-END:MVDCABody
                // Insert pre-action code here
                exitMIDlet();//GEN-LINE:MVDCAAction5
                // Insert post-action code here
            }//GEN-BEGIN:MVDCACase5
        }//GEN-END:MVDCACase5
    // Insert global post-action code here
}//GEN-LINE:MVDCAEnd

    /** This method returns instance for mainForm component and should be called instead of accessing mainForm field directly.//GEN-BEGIN:MVDGetBegin2
     * @return Instance for mainForm component
     */
    public Form get_mainForm() {
        if (mainForm == null) {//GEN-END:MVDGetBegin2
            // Insert pre-init code here
            mainForm = new Form("Custom Item Demo", new Item[] {//GEN-BEGIN:MVDGetInit2
                get_textField1(),
                get_customItem1(),
                get_textField2()
            });
            mainForm.addCommand(get_exitCommand());
            mainForm.setCommandListener(this);//GEN-END:MVDGetInit2
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd2
        return mainForm;
    }//GEN-END:MVDGetEnd2

    /** This method returns instance for customItem1 component and should be called instead of accessing customItem1 field directly.//GEN-BEGIN:MVDGetBegin3
     * @return Instance for customItem1 component
     */
    public example.customitem.Table get_customItem1() {
        if (customItem1 == null) {//GEN-END:MVDGetBegin3
            // Insert pre-init code here
            customItem1 = new example.customitem.Table("Table", Display.getDisplay(this));//GEN-BEGIN:MVDGetInit3
            customItem1.setLabel("Table");//GEN-END:MVDGetInit3
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd3
        return customItem1;
    }//GEN-END:MVDGetEnd3

    /** This method returns instance for exitCommand component and should be called instead of accessing exitCommand field directly.//GEN-BEGIN:MVDGetBegin4
     * @return Instance for exitCommand component
     */
    public Command get_exitCommand() {
        if (exitCommand == null) {//GEN-END:MVDGetBegin4
            // Insert pre-init code here
            exitCommand = new Command("Exit", Command.EXIT, 1);//GEN-LINE:MVDGetInit4
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd4
        return exitCommand;
    }//GEN-END:MVDGetEnd4

    /** This method returns instance for textField1 component and should be called instead of accessing textField1 field directly.//GEN-BEGIN:MVDGetBegin6
     * @return Instance for textField1 component
     */
    public TextField get_textField1() {
        if (textField1 == null) {//GEN-END:MVDGetBegin6
            // Insert pre-init code here
            textField1 = new TextField("Upper Item", null, 10, TextField.ANY);//GEN-LINE:MVDGetInit6
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd6
        return textField1;
    }//GEN-END:MVDGetEnd6

    /** This method returns instance for textField2 component and should be called instead of accessing textField2 field directly.//GEN-BEGIN:MVDGetBegin7
     * @return Instance for textField2 component
     */
    public TextField get_textField2() {
        if (textField2 == null) {//GEN-END:MVDGetBegin7
            // Insert pre-init code here
            textField2 = new TextField("Lower Item", null, 10, TextField.ANY);//GEN-LINE:MVDGetInit7
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd7
        return textField2;
    }//GEN-END:MVDGetEnd7
    
    public void startApp() {
        initialize();
    }
    
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
    }
    
}
