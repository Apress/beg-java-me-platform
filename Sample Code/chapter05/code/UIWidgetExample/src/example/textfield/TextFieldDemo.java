/*
 * TextFieldDemo.java
 *
 * Copyright (c) 2006 Sun Microsystems, Inc.  All rights reserved.
 * Use is subject to license terms.
 */
package example.textfield;

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

public class TextFieldDemo extends MIDlet implements javax.microedition.lcdui.CommandListener {
    
    /** Creates a new instance of TextFieldDemo */
    public TextFieldDemo() {
    }
    
    private Form mainForm;//GEN-BEGIN:MVDFields
    private StringItem stringItem1;
    private TextField textField2;
    private TextField textField3;
    private TextField textField4;
    private TextField textField5;
    private TextField textField6;
    private TextField textField7;
    private Command exitCommand;
    private DateField dateField1;//GEN-END:MVDFields

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
                exitMIDlet();//GEN-LINE:MVDCAAction12
                // Insert post-action code here
            }//GEN-BEGIN:MVDCACase12
        }//GEN-END:MVDCACase12
    // Insert global post-action code here
}//GEN-LINE:MVDCAEnd

    /** This method returns instance for mainForm component and should be called instead of accessing mainForm field directly.//GEN-BEGIN:MVDGetBegin2
     * @return Instance for mainForm component
     */
    public Form get_mainForm() {
        if (mainForm == null) {//GEN-END:MVDGetBegin2
            // Insert pre-init code here
            mainForm = new Form("DateField Demo", new Item[] {//GEN-BEGIN:MVDGetInit2
                get_stringItem1(),
                get_dateField1(),
                get_textField2(),
                get_textField3(),
                get_textField4(),
                get_textField5(),
                get_textField6(),
                get_textField7()
            });
            mainForm.addCommand(get_exitCommand());
            mainForm.setCommandListener(this);//GEN-END:MVDGetInit2
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd2
        return mainForm;
    }//GEN-END:MVDGetEnd2

    /** This method returns instance for stringItem1 component and should be called instead of accessing stringItem1 field directly.//GEN-BEGIN:MVDGetBegin3
     * @return Instance for stringItem1 component
     */
    public StringItem get_stringItem1() {
        if (stringItem1 == null) {//GEN-END:MVDGetBegin3
            // Insert pre-init code here
            stringItem1 = new StringItem(null, "Here\'s a DateField");//GEN-LINE:MVDGetInit3
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd3
        return stringItem1;
    }//GEN-END:MVDGetEnd3
     /** This method returns instance for textField2 component and should be called instead of accessing textField2 field directly.//GEN-BEGIN:MVDGetBegin5
     * @return Instance for textField2 component
     */
    public TextField get_textField2() {
        if (textField2 == null) {//GEN-END:MVDGetBegin5
            // Insert pre-init code here
            textField2 = new TextField("E-mail", " ", 60, TextField.EMAILADDR);//GEN-BEGIN:MVDGetInit5
            textField2.setLayout(Item.LAYOUT_LEFT | Item.LAYOUT_TOP | Item.LAYOUT_NEWLINE_AFTER | Item.LAYOUT_2);//GEN-END:MVDGetInit5
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd5
        return textField2;
    }//GEN-END:MVDGetEnd5

    /** This method returns instance for textField3 component and should be called instead of accessing textField3 field directly.//GEN-BEGIN:MVDGetBegin6
     * @return Instance for textField3 component
     */
    public TextField get_textField3() {
        if (textField3 == null) {//GEN-END:MVDGetBegin6
            // Insert pre-init code here
            textField3 = new TextField("Number", null, 60, TextField.NUMERIC);//GEN-BEGIN:MVDGetInit6
            textField3.setLayout(Item.LAYOUT_NEWLINE_AFTER | Item.LAYOUT_2);//GEN-END:MVDGetInit6
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd6
        return textField3;
    }//GEN-END:MVDGetEnd6

    /** This method returns instance for textField4 component and should be called instead of accessing textField4 field directly.//GEN-BEGIN:MVDGetBegin7
     * @return Instance for textField4 component
     */
    public TextField get_textField4() {
        if (textField4 == null) {//GEN-END:MVDGetBegin7
            // Insert pre-init code here
            textField4 = new TextField("Decimal", null, 60, TextField.DECIMAL);//GEN-BEGIN:MVDGetInit7
            textField4.setLayout(Item.LAYOUT_NEWLINE_AFTER | Item.LAYOUT_2);//GEN-END:MVDGetInit7
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd7
        return textField4;
    }//GEN-END:MVDGetEnd7

    /** This method returns instance for textField5 component and should be called instead of accessing textField5 field directly.//GEN-BEGIN:MVDGetBegin8
     * @return Instance for textField5 component
     */
    public TextField get_textField5() {
        if (textField5 == null) {//GEN-END:MVDGetBegin8
            // Insert pre-init code here
            textField5 = new TextField("Phone", null, 60, TextField.PHONENUMBER);//GEN-LINE:MVDGetInit8
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd8
        return textField5;
    }//GEN-END:MVDGetEnd8

    /** This method returns instance for textField6 component and should be called instead of accessing textField6 field directly.//GEN-BEGIN:MVDGetBegin9
     * @return Instance for textField6 component
     */
    public TextField get_textField6() {
        if (textField6 == null) {//GEN-END:MVDGetBegin9
            // Insert pre-init code here
            textField6 = new TextField("Password", null, 120, TextField.ANY | TextField.PASSWORD);//GEN-BEGIN:MVDGetInit9
            textField6.setLayout(Item.LAYOUT_NEWLINE_AFTER | Item.LAYOUT_2);//GEN-END:MVDGetInit9
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd9
        return textField6;
    }//GEN-END:MVDGetEnd9

    /** This method returns instance for textField7 component and should be called instead of accessing textField7 field directly.//GEN-BEGIN:MVDGetBegin10
     * @return Instance for textField7 component
     */
    public TextField get_textField7() {
        if (textField7 == null) {//GEN-END:MVDGetBegin10
            // Insert pre-init code here
            textField7 = new TextField("Url", null, 120, TextField.URL);//GEN-BEGIN:MVDGetInit10
            textField7.setLayout(Item.LAYOUT_NEWLINE_AFTER | Item.LAYOUT_2);//GEN-END:MVDGetInit10
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd10
        return textField7;
    }//GEN-END:MVDGetEnd10

    /** This method returns instance for exitCommand component and should be called instead of accessing exitCommand field directly.//GEN-BEGIN:MVDGetBegin11
     * @return Instance for exitCommand component
     */
    public Command get_exitCommand() {
        if (exitCommand == null) {//GEN-END:MVDGetBegin11
            // Insert pre-init code here
            exitCommand = new Command("Exit", Command.EXIT, 1);//GEN-LINE:MVDGetInit11
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd11
        return exitCommand;
    }//GEN-END:MVDGetEnd11

    /** This method returns instance for dateField1 component and should be called instead of accessing dateField1 field directly.//GEN-BEGIN:MVDGetBegin13
     * @return Instance for dateField1 component
     */
    public DateField get_dateField1() {
        if (dateField1 == null) {//GEN-END:MVDGetBegin13
            // Insert pre-init code here
            dateField1 = new DateField("Input a Date", DateField.DATE_TIME);//GEN-LINE:MVDGetInit13
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd13
        return dateField1;
    }//GEN-END:MVDGetEnd13
     
    public void startApp() {
        initialize();
    }
    
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
    }
    
}
