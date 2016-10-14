/*
 * %W% %E%
 *
 * Copyright (c) 2004 Sun Microsystems, Inc.  All rights reserved.
 * Use is subject to license terms
 */
package example.gauge;

import javax.microedition.lcdui.*;


/**
 * This class implements a non-interactive gague control
 * that moves automatically, between the min and max values.
 *
 * @version 2.0
 */
public class NonInteractiveGaugeRunnable extends Gauge implements Runnable {

    private int maxValue = 10;

    /**
     * This member indicates the number of units to move per
     * iteration. It is set to -1 when we reach a 100 and 1
     * when we reach 0.
     */
    private int delta = 1;

    private boolean done = false;
    
    /**
     * The constructor initializes the gauge.
     */
    public NonInteractiveGaugeRunnable() {
        super("Non Interactive", false, 10, 0);
        this.maxValue = maxValue;
        new Thread(this).start();
    }
    

    /**
     * This method moves the gauge left and right.
     */
    public void run() {

        while (!done) {

            // decide whether the gauge should start moving
            // backwards or forwards.
            int newValue = getValue() + delta;

            if (newValue == maxValue) {
                delta = -1;
            } else if (newValue == 0) {
                delta = 1;
            }

            setValue(newValue);
            try {
                Thread.currentThread().sleep(1000);
            } catch (InterruptedException err) {
            }
        }
    }
    
    void setDone() {
        done = true;
    }
}
