/*
 * SpriteSampleMIDlet.java
 *
 * Created on April 4, 2008, 4:49 PM
 */

package com.apress.rischpater;

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

/**
 *
 * @author kf6gpe
 */
public class SpriteSampleMIDlet extends MIDlet {
    private SpriteCanvas canvas;
    /**
     * Creates a new instance of SpriteSampleMIDlet
     */
    public SpriteSampleMIDlet() {
    }
        
    private void initialize() {
        if (canvas==null) {
            try {
                canvas = new SpriteCanvas(getDisplay());
                getDisplay().setCurrent(canvas);
                canvas.start();
            }
        catch(Exception ex) {}
        } else {
            canvas.setPaused(false);
        }
            
    }

    public Display getDisplay() {
        return Display.getDisplay(this);
    }
    
    public void exitMIDlet() {
        canvas=null;
        getDisplay().setCurrent(null);
        destroyApp(true);
        notifyDestroyed();
    }

    public void startApp() {
        initialize();
    }
    
    public void pauseApp() {
        canvas.setPaused(true);
    }
    
    public void destroyApp(boolean unconditional) {
    }
    
}
