/*
 * ChoiceGroupDemo.java
 *
 * Copyright (c) 2006 Sun Microsystems, Inc.  All rights reserved.
 * Use is subject to license terms.
 */
package example.choicegroup;

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

public class ChoiceGroupDemo extends MIDlet implements javax.microedition.lcdui.CommandListener {
    
    /** Creates a new instance of ChoiceGroupDemo */
    public ChoiceGroupDemo() {
    }
    
    private Form mainForm;//GEN-BEGIN:MVDFields
    private StringItem stringItemTitle;
    private ChoiceGroup cgExcl;
    private ChoiceGroup cgMult;
    private ChoiceGroup cgPop;
    private Image icon;
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
            if (command == exitCommand) {//GEN-END:MVDCABody
                // Insert pre-action code here
                exitMIDlet();//GEN-LINE:MVDCAAction13
                // Insert post-action code here
            }//GEN-BEGIN:MVDCACase13
        }//GEN-END:MVDCACase13
    // Insert global post-action code here
}//GEN-LINE:MVDCAEnd

    /** This method returns instance for mainForm component and should be called instead of accessing mainForm field directly.//GEN-BEGIN:MVDGetBegin2
     * @return Instance for mainForm component
     */
    public Form get_mainForm() {
        if (mainForm == null) {//GEN-END:MVDGetBegin2
            String base="Option ";
            for (char c='A';c<'E';c++) {
                get_cgPop().append(base+c,get_icon());
                get_cgMult().append(base+c,get_icon());
            }
            mainForm = new Form("Choice Group", new Item[] {//GEN-BEGIN:MVDGetInit2
                get_stringItemTitle(),
                get_cgExcl(),
                get_cgMult(),
                get_cgPop()
            });
            mainForm.addCommand(get_exitCommand());
            mainForm.setCommandListener(this);//GEN-END:MVDGetInit2
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd2
        return mainForm;
    }//GEN-END:MVDGetEnd2

    /** This method returns instance for stringItemTitle component and should be called instead of accessing stringItemTitle field directly.//GEN-BEGIN:MVDGetBegin3
     * @return Instance for stringItemTitle component
     */
    public StringItem get_stringItemTitle() {
        if (stringItemTitle == null) {//GEN-END:MVDGetBegin3
            // Insert pre-init code here
            stringItemTitle = new StringItem(null, "These are the available choice group types");//GEN-BEGIN:MVDGetInit3
            stringItemTitle.setLayout(Item.LAYOUT_LEFT | Item.LAYOUT_TOP | Item.LAYOUT_NEWLINE_AFTER | Item.LAYOUT_2);//GEN-END:MVDGetInit3
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd3
        return stringItemTitle;
    }//GEN-END:MVDGetEnd3

    /** This method returns instance for cgExcl component and should be called instead of accessing cgExcl field directly.//GEN-BEGIN:MVDGetBegin4
     * @return Instance for cgExcl component
     */
    public ChoiceGroup get_cgExcl() {
        if (cgExcl == null) {//GEN-END:MVDGetBegin4
            // Insert pre-init code here
            cgExcl = new ChoiceGroup("Exclusive", Choice.EXCLUSIVE, new String[] {//GEN-BEGIN:MVDGetInit4
                "Option A",
                "Option B",
                "Option C",
                "Option D"
            }, new Image[] {
                get_icon(),
                get_icon(),
                get_icon(),
                get_icon()
            });
            cgExcl.setSelectedFlags(new boolean[] {
                false,
                false,
                false,
                false
            });//GEN-END:MVDGetInit4
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd4
        return cgExcl;
    }//GEN-END:MVDGetEnd4

    /** This method returns instance for cgMult component and should be called instead of accessing cgMult field directly.//GEN-BEGIN:MVDGetBegin5
     * @return Instance for cgMult component
     */
    public ChoiceGroup get_cgMult() {
        if (cgMult == null) {//GEN-END:MVDGetBegin5
            // Insert pre-init code here
            cgMult = new ChoiceGroup("Multiple", Choice.MULTIPLE, new String[0], new Image[0]);//GEN-BEGIN:MVDGetInit5
            cgMult.setSelectedFlags(new boolean[0]);//GEN-END:MVDGetInit5
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd5
        return cgMult;
    }//GEN-END:MVDGetEnd5

    /** This method returns instance for cgPop component and should be called instead of accessing cgPop field directly.//GEN-BEGIN:MVDGetBegin6
     * @return Instance for cgPop component
     */
    public ChoiceGroup get_cgPop() {
        if (cgPop == null) {//GEN-END:MVDGetBegin6
            // Insert pre-init code here
            cgPop = new ChoiceGroup("Pop-up", Choice.POPUP, new String[0], new Image[0]);//GEN-BEGIN:MVDGetInit6
            cgPop.setSelectedFlags(new boolean[0]);//GEN-END:MVDGetInit6
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd6
        return cgPop;
    }//GEN-END:MVDGetEnd6

    /** This method returns instance for icon component and should be called instead of accessing icon field directly.//GEN-BEGIN:MVDGetBegin7
     * @return Instance for icon component
     */
    public Image get_icon() {
        if (icon == null) {//GEN-END:MVDGetBegin7
            // Insert pre-init code here
            try {//GEN-BEGIN:MVDGetInit7
                icon = Image.createImage("/icons/Icon.png");
            } catch (java.io.IOException exception) {
            }//GEN-END:MVDGetInit7
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd7
        return icon;
    }//GEN-END:MVDGetEnd7

    /** This method returns instance for exitCommand component and should be called instead of accessing exitCommand field directly.//GEN-BEGIN:MVDGetBegin12
     * @return Instance for exitCommand component
     */
    public Command get_exitCommand() {
        if (exitCommand == null) {//GEN-END:MVDGetBegin12
            // Insert pre-init code here
            exitCommand = new Command("Exit", Command.EXIT, 1);//GEN-LINE:MVDGetInit12
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd12
        return exitCommand;
    }//GEN-END:MVDGetEnd12
    
    public void startApp() {
        initialize();
    }
    
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
    }
    
}
