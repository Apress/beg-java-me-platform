/*
 * TextBoxDemo.java
 *
 * Copyright (c) 2006 Sun Microsystems, Inc.  All rights reserved.
 * Use is subject to license terms.
 */
package example.textbox;

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

public class TextBoxDemo extends MIDlet implements javax.microedition.lcdui.CommandListener {
    
    /**
     * The labels for the supported textboxs.
     */
    static final String[] textBoxLabels = {
        "Any Character", "E-Mail", "Number", "Decimal", "Phone", "Url"
    };
    
    /**
     * The supported example.textbox types.
     */
    static final int[] textBoxTypes = {
        TextField.ANY, TextField.EMAILADDR, TextField.NUMERIC,
                TextField.DECIMAL, TextField.PHONENUMBER, TextField.URL
    };
    
    private void setUpTypeList() {
        for (int i=0;textBoxLabels.length>i;i++) {
            this.get_typeCG().append(textBoxLabels[i], null);
        }
    }
    
    private void setUpTextBox() {
        this.textBox=null;
        int i=get_typeCG().getSelectedIndex();
        String title=textBoxLabels[i];
        int t=textBoxTypes[i];
        boolean[] flags = new boolean[2];
        this.get_optionsCG().getSelectedFlags(flags);
        if (flags[0]) {
            t |= TextField.PASSWORD;
        } 
        if (flags[1]) {
            this.get_textBox().setTicker(new Ticker("TextBox: " + title)); 
        }        
        this.get_textBox().setTitle(title);
        this.get_textBox().setConstraints(t);
    }
    
    /** Creates a new instance of TextBoxDemo */
    public TextBoxDemo() {
    }
    
    private Form mainForm;//GEN-BEGIN:MVDFields
    private StringItem stringItem1;
    private ChoiceGroup typeCG;
    private ChoiceGroup optionsCG;
    private Command showCommand;
    private Command exitCommand;
    private TextBox textBox;
    private Command backCommand;//GEN-END:MVDFields

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
                setUpTextBox();
                getDisplay().setCurrent(get_textBox());//GEN-LINE:MVDCAAction7
                // Insert post-action code here
            } else if (command == exitCommand) {//GEN-LINE:MVDCACase7
                // Insert pre-action code here
                exitMIDlet();//GEN-LINE:MVDCAAction11
                // Insert post-action code here
            }//GEN-BEGIN:MVDCACase11
        } else if (displayable == textBox) {
            if (command == backCommand) {//GEN-END:MVDCACase11
                // Insert pre-action code here
                getDisplay().setCurrent(get_mainForm());//GEN-LINE:MVDCAAction14
                // Insert post-action code here
            }//GEN-BEGIN:MVDCACase14
        }//GEN-END:MVDCACase14
    // Insert global post-action code here
}//GEN-LINE:MVDCAEnd

    /** This method returns instance for mainForm component and should be called instead of accessing mainForm field directly.//GEN-BEGIN:MVDGetBegin2
     * @return Instance for mainForm component
     */
    public Form get_mainForm() {
        if (mainForm == null) {//GEN-END:MVDGetBegin2
            setUpTypeList();
            mainForm = new Form("TextBox Demo", new Item[] {//GEN-BEGIN:MVDGetInit2
                get_stringItem1(),
                get_typeCG(),
                get_optionsCG()
            });
            mainForm.addCommand(get_showCommand());
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
            stringItem1 = new StringItem(null, "Select a TextBox type");//GEN-LINE:MVDGetInit3
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd3
        return stringItem1;
    }//GEN-END:MVDGetEnd3

    /** This method returns instance for typeCG component and should be called instead of accessing typeCG field directly.//GEN-BEGIN:MVDGetBegin4
     * @return Instance for typeCG component
     */
    public ChoiceGroup get_typeCG() {
        if (typeCG == null) {//GEN-END:MVDGetBegin4
            // Insert pre-init code here
            typeCG = new ChoiceGroup("Choose Type", Choice.EXCLUSIVE, new String[0], new Image[0]);//GEN-BEGIN:MVDGetInit4
            typeCG.setSelectedFlags(new boolean[0]);//GEN-END:MVDGetInit4
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd4
        return typeCG;
    }//GEN-END:MVDGetEnd4

    /** This method returns instance for optionsCG component and should be called instead of accessing optionsCG field directly.//GEN-BEGIN:MVDGetBegin5
     * @return Instance for optionsCG component
     */
    public ChoiceGroup get_optionsCG() {
        if (optionsCG == null) {//GEN-END:MVDGetBegin5
            // Insert pre-init code here
            optionsCG = new ChoiceGroup("Options", Choice.MULTIPLE, new String[] {//GEN-BEGIN:MVDGetInit5
                "As Password",
                "Show Ticker"
            }, new Image[] {
                null,
                null
            });
            optionsCG.setSelectedFlags(new boolean[] {
                false,
                false
            });//GEN-END:MVDGetInit5
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd5
        return optionsCG;
    }//GEN-END:MVDGetEnd5

    /** This method returns instance for showCommand component and should be called instead of accessing showCommand field directly.//GEN-BEGIN:MVDGetBegin6
     * @return Instance for showCommand component
     */
    public Command get_showCommand() {
        if (showCommand == null) {//GEN-END:MVDGetBegin6
            // Insert pre-init code here
            showCommand = new Command("Show", Command.SCREEN, 1);//GEN-LINE:MVDGetInit6
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd6
        return showCommand;
    }//GEN-END:MVDGetEnd6

    /** This method returns instance for exitCommand component and should be called instead of accessing exitCommand field directly.//GEN-BEGIN:MVDGetBegin10
     * @return Instance for exitCommand component
     */
    public Command get_exitCommand() {
        if (exitCommand == null) {//GEN-END:MVDGetBegin10
            // Insert pre-init code here
            exitCommand = new Command("Exit", Command.EXIT, 1);//GEN-LINE:MVDGetInit10
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd10
        return exitCommand;
    }//GEN-END:MVDGetEnd10

    /** This method returns instance for textBox component and should be called instead of accessing textBox field directly.//GEN-BEGIN:MVDGetBegin12
     * @return Instance for textBox component
     */
    public TextBox get_textBox() {
        if (textBox == null) {//GEN-END:MVDGetBegin12
            // Insert pre-init code here
            textBox = new TextBox(null, null, 10240, TextField.ANY);//GEN-BEGIN:MVDGetInit12
            textBox.addCommand(get_backCommand());
            textBox.setCommandListener(this);//GEN-END:MVDGetInit12
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd12
        return textBox;
    }//GEN-END:MVDGetEnd12

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
    
    public void startApp() {
        initialize();
    }
    
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
    }
    
}
