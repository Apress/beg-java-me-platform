/*
 * HelloXlet.java
 *
 * Created on November 24, 2007, 6:57 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.apress.rischpater.HelloXlet;

import java.awt.*;
import javax.microedition.xlet.*;

/**
 *
 * @author Ray Rischpater
 */
public class HelloXlet extends Component implements javax.microedition.xlet.Xlet {
    private XletContext context;            // our Xlet application context.
    private Container rootContainer;        // the root container of our screen.
    
    public HelloXlet() {

    }
    
    public void initXlet(final XletContext xletContext) throws XletStateChangeException {
        context = xletContext;
        if(rootContainer == null) {
            try {
                //This call to getContainer() tells the OS we want to be a graphical app.
                rootContainer = context.getContainer();				
				rootContainer.add(this);
            } catch (UnavailableContainerException e) {
                System.out.println("Ouch ! could not get our container!");
                // If we can't get the root container,
                // abort the initialization
                throw new XletStateChangeException( "Start aborted -- no container: "
                        + e.getMessage() );
            }
        }
    }
    
    public void startXlet() throws XletStateChangeException {
        rootContainer.setVisible(true);
    }
    
    public void pauseXlet() {
        System.out.println("HelloXet.pauseXlet()");
    }
    
    public void destroyXlet(boolean b) throws XletStateChangeException {
        System.out.println("HelloXet.destroyXlet() - goodbye");
    }
    
    public void exit(){
        rootContainer.setVisible( false );
        context.notifyDestroyed();
    }

    public void paint(Graphics g) {	
        g.drawString("Hello Xlet", 0, 100);
    }

}
