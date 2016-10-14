/*
 * AlarmTimerMidlet.java
 *
 * Created on December 18, 2007, 3:23 AM
 */

package com.apress.rischpater.alarmtimer;

import java.io.*;
import java.util.*;
import java.lang.*;
import javax.microedition.io.*;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import javax.microedition.rms.*;

/**
 *
 * @author Ray Rischpater
 */
public class AlarmTimerMidlet extends MIDlet implements CommandListener {
    
    /**
     * Creates a new instance of AlarmTimerMidlet
     */
    public AlarmTimerMidlet() {
    }
    
    class MyTask extends TimerTask {
        private AlarmTimerMidlet owner;
        void setOwner( AlarmTimerMidlet o ) {
            owner = o;
        }
        public void run() {
            owner.alarmFired();
        }
    } 
    
    private Form infoForm;
    private StringItem helloStringItem;
    private Command exitCommand;
    private Alert alarmAlert;
    private long DELAY = 15 * 1000;
    private Timer timer;
    private MyTask task;
    private long whenLaunched = new Date().getTime();
    private String storeName = "AlarmTimerStore";
    private RecordStore store;

    /** This method initializes UI of the application.                        
     */
    private void initialize() {
        try {
            Date d = new Date();
            long whenToFire = d.getTime() + DELAY;
            store = RecordStore.openRecordStore(storeName, true);

            if (store.getNumRecords()>0){
                byte b[] = store.getRecord(1);
                ByteArrayInputStream bais = new ByteArrayInputStream(b);
                DataInputStream dis = new DataInputStream(bais);
                whenToFire = dis.readLong();
                if (whenToFire < whenLaunched) {
                    getDisplay().setCurrent(get_alarmAlert());
                } 
                store.deleteRecord(1);
                store.closeRecordStore();
                return;
            } 
            store.closeRecordStore();
            
            String me = this.getClass().getName();
            PushRegistry.registerAlarm(me, 0);            
            timer = new Timer();
            task = new MyTask();
            task.setOwner(this);
            timer.schedule(task, whenToFire - d.getTime() );
            
            getDisplay().setCurrent(get_infoForm());
        }
        catch( Exception e) {};
    }
    
    /** Called by the system to indicate that a command has been invoked on a particular displayable.                      
     * @param command the Command that ws invoked
     * @param displayable the Displayable on which the command was invoked
     */
    public void commandAction(Command command, Displayable displayable) {
        if (displayable == infoForm) {
            if (command == exitCommand) {
                exitMIDlet();
            }
        }
    }
    
    private void scheduleMIDlet( ) {
        try {
            String me = this.getClass().getName();
            Date when = new Date();
            if (when.getTime() < whenLaunched + DELAY) {
                PushRegistry.registerAlarm(me, whenLaunched + DELAY);
            }

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            DataOutputStream dos = new DataOutputStream( baos );
            dos.writeLong(whenLaunched + DELAY);
            byte b[] = baos.toByteArray();
            store = RecordStore.openRecordStore(storeName, true);
            store.addRecord(b, 0, b.length);
            store.closeRecordStore();
        }
        catch (Exception e) {}
    }
    
    private void alarmFired() {
        getDisplay().setCurrent(get_alarmAlert(), get_infoForm());
    }
    
    /**
     * This method should return an instance of the display.
     */
    public Display getDisplay() {
        return Display.getDisplay(this);
    }
    
    /**
     * This method should exit the midlet.
     */
    public void exitMIDlet() {
        getDisplay().setCurrent(null);
        try {
            if ( getDisplay().getCurrent() != get_infoForm() )
                scheduleMIDlet();            
        }
        catch(Exception e) {};
        destroyApp(true);
        notifyDestroyed();
    }
    
    /** This method returns instance for infoForm component and should be called instead of accessing infoForm field directly.                        
     * @return Instance for infoForm component
     */
    public Form get_infoForm() {
        if (infoForm == null) {
            infoForm = new Form(null, new Item[] {get_helloStringItem()});
            infoForm.addCommand(get_exitCommand());
            infoForm.setCommandListener(this);
        }
        return infoForm;
    }
    
    /** This method returns instance for helloStringItem component and should be called instead of accessing helloStringItem field directly.
     * @return Instance for helloStringItem component
     */
    public StringItem get_helloStringItem() {
        if (helloStringItem == null) {
            // Insert pre-init code here
            helloStringItem = new StringItem("", "An alarm has been set for fifteen seconds from now.");
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd4
        return helloStringItem;
    }//GEN-END:MVDGetEnd4
    
    /** This method returns instance for exitCommand component and should be called instead of accessing exitCommand field directly.
     * @return Instance for exitCommand component
     */
    public Command get_exitCommand() {
        if (exitCommand == null) {
            exitCommand = new Command("Exit", Command.EXIT, 1);
        }
        return exitCommand;
    }
    /** This method returns instance for alarmAlert component and should be called instead of accessing alarmAlert field directly.                        
     * @return Instance for alarmAlert component
     */
    public Alert get_alarmAlert() {
        if (alarmAlert == null) {
            alarmAlert = new Alert(null, "The alarm has fired.\n", null, null);
            alarmAlert.setTimeout(-2);
        }
        return alarmAlert;
    }
 
    
    public void startApp() {
        initialize();
    }
    
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
	    timer = null;
	    task = null;
    }
    
}
