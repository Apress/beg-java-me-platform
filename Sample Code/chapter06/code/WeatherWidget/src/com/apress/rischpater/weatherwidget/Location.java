/*
 * Location.java
 *
 * Created on March 2, 2008, 9:01 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.apress.rischpater.weatherwidget;

import javax.microedition.rms.*;
import java.io.*;

/**
 *
 * @author kf6gpe
 */
public class Location {
    private final static int FIELD_VERSION = 1;
    private final static int FIELD_LOCATION = 2;
    private final static int FIELD_FORECAST = 3;
    
    public final static int NO_ID = -1;
    
    private final static int version = 1;
    private String location;
    private String forecast;
    private int recordid;
    
    /** Creates a new instance of Location */
    public Location(String l, String f) {
        location = l;
        forecast = f;
        recordid = NO_ID;
    }
    
    public Location(byte[] b) {
        fromBytes(b);
        recordid = NO_ID;
    }

    
    public Location(byte[] b, int id) {
        fromBytes(b);
        recordid = id;
    }
    
    public String getLocation() {
        if (location != null) {
            return location;
        } else {
            return "";
        }
    }
    
    public void setLocation(String l) {
        location = l;
    }
    
    public String getForecast() {
        if (forecast != null) {
            return forecast;
        } else {
            return "";
        }
    }
    
    public void setForecast(String f) {
        forecast = f;
    }
    
    public int getId() {
        return recordid;
    }
    
    public void setId(int id) {
        recordid = id;
    }
    public byte[] toBytes() {
        byte[] b;
        ByteArrayOutputStream baos = new ByteArrayOutputStream(); 
        DataOutputStream dos = new DataOutputStream(baos); 
        
        
        // Record format is field-tag, then field for each
        try {
            dos.writeInt(FIELD_VERSION);
            dos.writeInt(version);
            if (location != null) {
                dos.writeInt(FIELD_LOCATION);
                dos.writeUTF(getLocation());
            }
            if (forecast != null)
            {
                dos.writeInt(FIELD_FORECAST);
                dos.writeUTF(getForecast());
            }
        }
        catch( Exception e) {
            return null;
        }        
        
        // Get the bytes for this item.
        b = baos.toByteArray(); 
        dos = null;
        baos = null;
        
        return b;
    }
    
    public void fromBytes(byte[] b) {
        ByteArrayInputStream bais = new ByteArrayInputStream(b);
        DataInputStream dis = new DataInputStream(bais);
        
        // Read each tag, then each field
        try
        {
            while(true) {
                int tag = dis.readInt();
                switch(tag) {
                    case FIELD_VERSION:
                        // Don't check version; there's only one
                        dis.readInt();
                        break;
                    case FIELD_LOCATION:
                        setLocation(dis.readUTF());
                        break;
                    case FIELD_FORECAST:
                        setForecast(dis.readUTF());
                        break;
                }
            }
        }
        catch (Exception e) {}
                
        dis = null;
        bais = null;
    }
}
