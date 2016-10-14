/*
 * ContactStuffer.java
 *
 * Created on March 28, 2008, 9:03 PM
 */

package com.apress.rischpater;

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import java.io.*; 
import java.util.*;
import javax.microedition.pim.*;
/**
 *
 * @author kf6gpe
 */
public class ContactStuffer extends MIDlet implements CommandListener {
    
    /**
     * Creates a new instance of ContactStuffer
     */
    public ContactStuffer() {
        try { 
            verifyPIMSupport(); 
            seed(); 
        } 
        catch (Exception ex) { 
            Form mForm = new Form("Exception"); 
            mForm.append(new StringItem(null, ex.toString())); 
            Command mExitCommand = new Command("Exit", Command.EXIT, 0); 
            mForm.addCommand(mExitCommand); 
            mForm.setCommandListener(this); 
            return; 
        }      
    }
    
    private Form helloForm;//GEN-BEGIN:MVDFields
    private StringItem helloStringItem;
    private Command exitCommand;
    private List listContacts;
    private Command okCommand1;//GEN-END:MVDFields
    
//GEN-LINE:MVDMethods

    /** This method initializes UI of the application.//GEN-BEGIN:MVDInitBegin
     */
    private void initialize() {//GEN-END:MVDInitBegin
        // Insert pre-init code here
        getDisplay().setCurrent(get_helloForm());//GEN-LINE:MVDInitInit
        // Insert post-init code here
    }//GEN-LINE:MVDInitEnd
    
    /** Called by the system to indicate that a command has been invoked on a particular displayable.//GEN-BEGIN:MVDCABegin
     * @param command the Command that ws invoked
     * @param displayable the Displayable on which the command was invoked
     */
    public void commandAction(Command command, Displayable displayable) {//GEN-END:MVDCABegin
        // Insert global pre-action code here
        if (displayable == helloForm) {//GEN-BEGIN:MVDCABody
            if (command == exitCommand) {//GEN-END:MVDCABody
                // Insert pre-action code here
                exitMIDlet();//GEN-LINE:MVDCAAction3
                // Insert post-action code here
            } else if (command == okCommand1) {//GEN-LINE:MVDCACase3
                // Insert pre-action code here
                getDisplay().setCurrent(get_listContacts());//GEN-LINE:MVDCAAction9
                // Insert post-action code here
            }//GEN-BEGIN:MVDCACase9
        }//GEN-END:MVDCACase9
        // Insert global post-action code here
}//GEN-LINE:MVDCAEnd
    
    /**
     * This method should return an instance of the display.
     */
    public Display getDisplay() {//GEN-FIRST:MVDGetDisplay
        return Display.getDisplay(this);
    }//GEN-LAST:MVDGetDisplay
    
    /**
     * This method should exit the midlet.
     */
    public void exitMIDlet() {//GEN-FIRST:MVDExitMidlet
        getDisplay().setCurrent(null);
        destroyApp(true);
        notifyDestroyed();
    }//GEN-LAST:MVDExitMidlet
    
    /** This method returns instance for helloForm component and should be called instead of accessing helloForm field directly.//GEN-BEGIN:MVDGetBegin2
     * @return Instance for helloForm component
     */
    public Form get_helloForm() {
        if (helloForm == null) {//GEN-END:MVDGetBegin2
            // Insert pre-init code here
            helloForm = new Form(null, new Item[] {get_helloStringItem()});//GEN-BEGIN:MVDGetInit2
            helloForm.addCommand(get_exitCommand());
            helloForm.addCommand(get_okCommand1());
            helloForm.setCommandListener(this);//GEN-END:MVDGetInit2
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd2
        return helloForm;
    }//GEN-END:MVDGetEnd2
    
    /** This method returns instance for helloStringItem component and should be called instead of accessing helloStringItem field directly.//GEN-BEGIN:MVDGetBegin4
     * @return Instance for helloStringItem component
     */
    public StringItem get_helloStringItem() {
        if (helloStringItem == null) {//GEN-END:MVDGetBegin4
            // Insert pre-init code here
            helloStringItem = new StringItem("", "");//GEN-LINE:MVDGetInit4
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd4
        return helloStringItem;
    }//GEN-END:MVDGetEnd4
    
    /** This method returns instance for exitCommand component and should be called instead of accessing exitCommand field directly.//GEN-BEGIN:MVDGetBegin5
     * @return Instance for exitCommand component
     */
    public Command get_exitCommand() {
        if (exitCommand == null) {//GEN-END:MVDGetBegin5
            // Insert pre-init code here
            exitCommand = new Command("Exit", Command.EXIT, 1);//GEN-LINE:MVDGetInit5
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd5
        return exitCommand;
    }//GEN-END:MVDGetEnd5

    /** This method returns instance for listContacts component and should be called instead of accessing listContacts field directly.//GEN-BEGIN:MVDGetBegin6
     * @return Instance for listContacts component
     */
    public List get_listContacts() {
        if (listContacts == null) {//GEN-END:MVDGetBegin6

            listContacts = new List(null, Choice.IMPLICIT, new String[0], new Image[0]);//GEN-BEGIN:MVDGetInit6
            listContacts.setCommandListener(this);
            listContacts.setSelectedFlags(new boolean[0]);//GEN-END:MVDGetInit6
            ContactLoaderThread t = new ContactLoaderThread( listContacts );
            t.start();
        }//GEN-BEGIN:MVDGetEnd6
        return listContacts;
    }//GEN-END:MVDGetEnd6

    /** This method returns instance for okCommand1 component and should be called instead of accessing okCommand1 field directly.//GEN-BEGIN:MVDGetBegin8
     * @return Instance for okCommand1 component
     */
    public Command get_okCommand1() {
        if (okCommand1 == null) {//GEN-END:MVDGetBegin8
            // Insert pre-init code here
            okCommand1 = new Command("Ok", Command.OK, 1);//GEN-LINE:MVDGetInit8
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd8
        return okCommand1;
    }//GEN-END:MVDGetEnd8
    
    public void startApp() {
        initialize();
    }
    
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
    }
    
   public void verifyPIMSupport() throws IOException { 
        String version = ""; 
        version = System.getProperty("microedition.pim.version"); 
        if (version != null) { 
          if (!version.equals("1.0")) 
            throw new IOException("Package is not version 1.0."); 
        } 
        else 
          throw new IOException("PIM optional package is not available."); 
  }
   
  private ContactList list = null; 
  
  private void seed() throws PIMException { 
    try { 
      PIM pimInst = PIM.getInstance(); 
      list = (ContactList) 
      pimInst.openPIMList(PIM.CONTACT_LIST, PIM.READ_WRITE); 
    } 
    catch (PIMException ex) { 
      // Contact list is not supported. 
    } 
    addContact(list, "Ray", "Rischpater", "1234 High Street", 
    "Los Gatos", "USA", "95030"); 
    addContact(list, "John", "Doe", "1111 Bear Road", 
    "Mariposa", "USA", "9????"); 
    if (list != null) 
      list.close(); 
    list = null; 
  } 

    private void addContact( ContactList list, String firstName, 
                            String lastName, String street, String city, 
                            String country, String postalcode) 
    throws PIMException { 
    Contact c = list.createContact(); 
    String [] name = new String[list.stringArraySize(Contact.NAME)]; 
    name[Contact.NAME_GIVEN] = firstName; 
    name[Contact.NAME_FAMILY] = lastName; 
    c.addStringArray(Contact.NAME,Contact.ATTR_NONE , name); 
    String [] addr = new String[list.stringArraySize(Contact.ADDR)]; 
    addr[Contact.ADDR_STREET] = street; 
    addr[Contact.ADDR_LOCALITY] = city; 
    addr[Contact.ADDR_COUNTRY] = country; 
    addr[Contact.ADDR_POSTALCODE] = street; 
    c.addStringArray(Contact.ADDR, Contact.ATTR_NONE , addr); 
    c.commit(); 
  } 
} 

