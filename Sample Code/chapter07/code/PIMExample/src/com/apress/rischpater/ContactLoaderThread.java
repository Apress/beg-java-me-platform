/*
 * ContactLoaderThread.java
 *
 * Created on March 29, 2008, 8:42 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
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
public class ContactLoaderThread extends Thread {
    List list;

    public ContactLoaderThread(List l) {
        list = l;
    }
    
    public void run() {
        try {
            PIM pim = PIM.getInstance(); 
            ContactList contList = (ContactList)
                pim.openPIMList(PIM.CONTACT_LIST, PIM.READ_ONLY); 
            Enumeration contacts = contList.items();
            while(contacts.hasMoreElements()) {
                Contact c = (Contact) contacts.nextElement(); 
                String [] nameValues = c.getStringArray( Contact.NAME, 0); 
                String firstName = nameValues[Contact.NAME_GIVEN]; 
                String lastName = nameValues[Contact.NAME_FAMILY]; 
                list.append(lastName + ", " + firstName, null); 
            }
        }
        catch(Exception ex) {}
    }   
}

