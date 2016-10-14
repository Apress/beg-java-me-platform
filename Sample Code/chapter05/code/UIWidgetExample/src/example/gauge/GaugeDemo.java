/*
 * GaugeDemo.java
 *
 * Copyright (c) 2006 Sun Microsystems, Inc.  All rights reserved.
 * Use is subject to license terms.
 */
package example.gauge;

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

public class GaugeDemo extends MIDlet implements javax.microedition.lcdui.CommandListener {
    
    /** Creates a new instance of GaugeDemo */
    public GaugeDemo() {
        System.out.println(Gauge.INDEFINITE);
        System.out.println(Gauge.CONTINUOUS_RUNNING);
        System.out.println(Gauge.INCREMENTAL_IDLE);
    }
    
    private javax.microedition.lcdui.Form mainForm;//GEN-BEGIN:MVDFields
    private javax.microedition.lcdui.Gauge indefiniteGauge;
    private javax.microedition.lcdui.Gauge interactiveGauge;
    private javax.microedition.lcdui.Command exitCommand;
    private example.gauge.IncrementalIndefiniteGaugeRunnable gauge1;
    private example.gauge.NonInteractiveGaugeRunnable gauge2;//GEN-END:MVDFields

    /** This method initializes UI of the application.//GEN-BEGIN:MVDMethods
     */
    private void initialize() {
        getDisplay().setCurrent(get_mainForm());
    }//GEN-END:MVDMethods

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
    public void commandAction(javax.microedition.lcdui.Command command, javax.microedition.lcdui.Displayable displayable) {//GEN-END:MVDCABegin
    // Insert global pre-action code here
        if (displayable == mainForm) {//GEN-BEGIN:MVDCABody
            if (command == exitCommand) {//GEN-END:MVDCABody
                // Insert pre-action code here
                exitMIDlet();//GEN-LINE:MVDCAAction6
                // Insert post-action code here
            }//GEN-BEGIN:MVDCACase6
        }//GEN-END:MVDCACase6
    // Insert global post-action code here
    }//GEN-LINE:MVDCAEnd

    /** This method returns instance for mainForm component and should be called instead of accessing mainForm field directly.//GEN-BEGIN:MVDGetBegin2
     * @return Instance for mainForm component
     */
    public javax.microedition.lcdui.Form get_mainForm() {
        if (mainForm == null) {//GEN-END:MVDGetBegin2
            // Insert pre-init code here
            mainForm = new javax.microedition.lcdui.Form("Gauge Demo", new javax.microedition.lcdui.Item[] {//GEN-BEGIN:MVDGetInit2
                get_interactiveGauge(),
                        get_indefiniteGauge(),
                        get_gauge1(),
                        get_gauge2()
            });
            mainForm.addCommand(get_exitCommand());
            mainForm.setCommandListener(this);//GEN-END:MVDGetInit2
            new Thread(get_gauge1()).start();
            new Thread(get_gauge2()).start();
        }//GEN-BEGIN:MVDGetEnd2
        return mainForm;
    }//GEN-END:MVDGetEnd2

    /** This method returns instance for indefiniteGauge component and should be called instead of accessing indefiniteGauge field directly.//GEN-BEGIN:MVDGetBegin3
     * @return Instance for indefiniteGauge component
     */
    public javax.microedition.lcdui.Gauge get_indefiniteGauge() {
        if (indefiniteGauge == null) {//GEN-END:MVDGetBegin3
            // Insert pre-init code here
            indefiniteGauge = new javax.microedition.lcdui.Gauge("Indefinite - Running", false, -1, 2);//GEN-BEGIN:MVDGetInit3
            indefiniteGauge.setLayout(0x4211);//GEN-END:MVDGetInit3
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd3
        return indefiniteGauge;
    }//GEN-END:MVDGetEnd3

    /** This method returns instance for interactiveGauge component and should be called instead of accessing interactiveGauge field directly.//GEN-BEGIN:MVDGetBegin4
     * @return Instance for interactiveGauge component
     */
    public javax.microedition.lcdui.Gauge get_interactiveGauge() {
        if (interactiveGauge == null) {//GEN-END:MVDGetBegin4
            // Insert pre-init code here
            interactiveGauge = new javax.microedition.lcdui.Gauge("Interactive", true, 10, 0);//GEN-BEGIN:MVDGetInit4
            interactiveGauge.setLayout(0x4211);//GEN-END:MVDGetInit4
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd4
        return interactiveGauge;
    }//GEN-END:MVDGetEnd4

    /** This method returns instance for exitCommand component and should be called instead of accessing exitCommand field directly.//GEN-BEGIN:MVDGetBegin5
     * @return Instance for exitCommand component
     */
    public javax.microedition.lcdui.Command get_exitCommand() {
        if (exitCommand == null) {//GEN-END:MVDGetBegin5
            // Insert pre-init code here
            exitCommand = new javax.microedition.lcdui.Command("Exit", javax.microedition.lcdui.Command.EXIT, 1);//GEN-LINE:MVDGetInit5
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd5
        return exitCommand;
    }//GEN-END:MVDGetEnd5

    /** This method returns instance for gauge1 component and should be called instead of accessing gauge1 field directly.//GEN-BEGIN:MVDGetBegin7
     * @return Instance for gauge1 component
     */
    public example.gauge.IncrementalIndefiniteGaugeRunnable get_gauge1() {
        if (gauge1 == null) {//GEN-END:MVDGetBegin7
            // Insert pre-init code here
            gauge1 = new example.gauge.IncrementalIndefiniteGaugeRunnable();//GEN-BEGIN:MVDGetInit7
            gauge1.setLabel("Non Interactive");
            gauge1.setLayout(0x4201);
            gauge1.setValue(1);
            gauge1.setMaxValue(-1);//GEN-END:MVDGetInit7
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd7
        return gauge1;
    }//GEN-END:MVDGetEnd7

    /** This method returns instance for gauge2 component and should be called instead of accessing gauge2 field directly.//GEN-BEGIN:MVDGetBegin8
     * @return Instance for gauge2 component
     */
    public example.gauge.NonInteractiveGaugeRunnable get_gauge2() {
        if (gauge2 == null) {//GEN-END:MVDGetBegin8
            // Insert pre-init code here
            gauge2 = new example.gauge.NonInteractiveGaugeRunnable();//GEN-BEGIN:MVDGetInit8
            gauge2.setLabel("Indefinite - Incremental");
            gauge2.setValue(0);
            gauge2.setMaxValue(10);//GEN-END:MVDGetInit8
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd8
        return gauge2;
    }//GEN-END:MVDGetEnd8
    
    private void addCustomComponents() {
        NonInteractiveGaugeRunnable nonInteractive = new NonInteractiveGaugeRunnable();
        new Thread(nonInteractive).start();
        get_mainForm().append(nonInteractive);
        
        IncrementalIndefiniteGaugeRunnable indefinite = new IncrementalIndefiniteGaugeRunnable();
        new Thread(indefinite).start();
        get_mainForm().append(indefinite);
    }
    
    public void startApp() {
        initialize();
    }
    
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
    }
    
}
