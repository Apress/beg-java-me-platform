/*
 * LocationStore.java
 *
 * Created on March 2, 2008, 9:29 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.apress.rischpater.weatherwidget;

import javax.microedition.io.*;
import javax.microedition.io.file.*; 
import java.io.*;
import java.util.*;
/**
 *
 * @author kf6gpe
 */
public class LocationStore {
    private static final String storeName = "wx";
    private static final String fileURLRoot = "file:///";
    private static String fileUrl;
    Vector locations;
    FileConnection fc;
    
    public LocationStore() {
        locations = new Vector();
        Enumeration em = FileSystemRegistry.listRoots();
        if (em.hasMoreElements()) {
                fileUrl = fileURLRoot + em.nextElement() + storeName;
        }
        load();
    }

    private void open() {
        if (fc==null) {
            try {
                fc = (FileConnection)Connector.open(fileUrl, Connector.READ_WRITE);
            }
            catch(Exception ex) {}
        }
    }
    
    private void close() {
        if (fc!=null) {
            try {
                fc.close();
            } catch( Exception ex ) {}
            fc = null;
        }
    }
    
    public void addLocation(Location l)  {
        locations.addElement( l );
        save();
    }
    
    public String[] locationStrings() {
        String result[] = new String[locations.size()];
        int i;
        
        for( i = 0; i < locations.size(); i++ ) {
            result[i] = ((Location)locations.elementAt(i)).getLocation();
        }
        return result;
    }
    
    public Location location( final String location ) {
        int i;
        
        for( i = 0; i < locations.size(); i++ ) {
            Location l = (Location)locations.elementAt(i);
            if (location.equals(l.getLocation())) {
                return l;
            }
        }
        return null;
    }
    
    public void updateLocation(Location location) {
        int i;
        
        for( i = 0; i < locations.size(); i++ ) {
            Location l = (Location)locations.elementAt(i);
            if (location.getLocation().equals(l.getLocation())) {
                l.setForecast(location.getForecast());
                l.setId(location.getId());
            }
        } 
        save();
    }
    
    public void load() {
        try {
            int v;
            byte b[];
            int length;
            Location l;
            DataInputStream dis;
        
            locations.removeAllElements();
            open();
            dis = fc.openDataInputStream();
            
            // Read version
            v = dis.readInt();
            // While there are more elements, read them.
            while(true) {
                length = dis.readInt();
                b = new byte[length];
                dis.read( b, 0, length);
                l = new Location(b);
                locations.addElement( l );
            }
        }
        catch(Exception ex) {};
        close();
    }
    
    public void save() {
        try {
            int i;
            byte[] b;
            DataOutputStream dos;
            open();
            dos = fc.openDataOutputStream();
            
            // Write version
            dos.writeInt( 1 );
            
            for( i = 0; i < locations.size(); i++ ) {
                Location l = (Location)locations.elementAt(i);
                b = l.toBytes();
                dos.writeInt(b.length);
                dos.write(b);
            }
            dos.close();
            close();
        }
        catch(Exception ex) {}
    }
}
