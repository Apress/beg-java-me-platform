/*
 * %W% %E%
 *
 * Copyright (c) 2004 Sun Microsystems, Inc.  All rights reserved.
 * Use is subject to license terms
 */
package example.customitem;

import javax.microedition.lcdui.*;
import javax.microedition.midlet.MIDlet;


/**
 *
 * @version 2.0
 */
public class TextInput extends TextBox implements CommandListener {

    private final static Command CMD_OK = new Command("OK", Command.OK,    
                                                        1);        
    private final static Command CMD_CANCEL = new Command("Cancel", Command.CANCEL, 
                                                        1);
    
    private Table parent;
    private Display display;

    public TextInput(String text, Table parent, Display display) {
        super("Enter Text", text, 50, TextField.ANY);
        this.parent = parent;
        this.display = display;
        addCommand(CMD_OK);
        addCommand(CMD_CANCEL);
        setCommandListener(this);
    }

    public void commandAction(Command c, Displayable d) {
        if (c == CMD_OK) {
            // update the table's cell and return
            parent.setText(getString());
            display.setCurrentItem(parent);
        } else if (c == CMD_CANCEL) {
            // return without updating the table's cell
            display.setCurrentItem(parent);
        }
    }
    
}