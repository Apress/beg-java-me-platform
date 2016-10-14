/*
 * WeatherLocationStore.java
 *
 * Created on March 2, 2008, 9:29 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.apress.rischpater.weatherwidget;

import javax.microedition.rms.*;

/**
 *
 * @author kf6gpe
 */
public class WeatherLocationStore {
    private static final String storeName = "wx";
    private RecordStore store = null;
    
    public WeatherLocationStore() {
    }

    private void openStore() throws RecordStoreException {
        if (store == null) {
            store = RecordStore.openRecordStore(storeName, true);
        }
    }
    
    private void closeStore() {
        if (store != null) {
            try {
                store.closeRecordStore();
            } catch( Exception ex ) {}
            store = null;
        }
    }
    
    public void addLocation(WeatherLocation l) throws RecordStoreException {
        WeatherLocation existing = getLocation(l.getLocation());
        if (existing!=null) {
            existing.setForecast(l.getForecast());
            updateLocation(existing);
        } else {
            byte b[] = l.toBytes();
            openStore();
            l.setId(store.getNextRecordID());
            store.addRecord(b, 0, b.length);
            closeStore();
        }
    }
    
    public String[] getLocationStrings() {
        String[] result = null;
        try {
            openStore();
            result = new String[store.getNumRecords()];
            RecordEnumeration e = store.enumerateRecords( 
                null, // No filter
                new RecordComparator () {
                    public int compare( byte[] b1, byte[] b2 ) {
                        WeatherLocation r1 = new WeatherLocation(b1);
                        WeatherLocation r2 = new WeatherLocation(b2);
                        if (r1.getLocation().compareTo(r2.getLocation()) == 0) {
                            return RecordComparator.EQUIVALENT;
                        } else if (r1.getLocation().compareTo(r2.getLocation()) < 0) {
                            return RecordComparator.PRECEDES;
                        } else {
                            return RecordComparator.FOLLOWS;
                        }
                    }
                },
                false);
            int i;

            for (i=0; i<store.getNumRecords(); i++) {
                WeatherLocation l = new WeatherLocation(e.nextRecord());
                result[i] = l.getLocation();
                l = null;
            }
            closeStore();
        }
        catch(Exception ex) { closeStore(); }
        return result;
    }
    
    public WeatherLocation getLocation( final String location ) throws RecordStoreException {
        openStore();
        WeatherLocation l = null;
        RecordEnumeration e = store.enumerateRecords( 
            new RecordFilter () {
                public boolean matches( byte[] b) {
                    WeatherLocation r = new WeatherLocation(b);
                    if (r.getLocation().equalsIgnoreCase(location)) return true;
                    else return false;
                }
            },
            null,       
            false);

        if (e.hasNextElement()) {
            int id = e.nextRecordId();
            byte[] b = store.getRecord(id);
            l = new WeatherLocation(b, id);
        } 
        closeStore();
        return l;        
    }
    
    public void updateLocation(WeatherLocation l) throws RecordStoreException {
        int id = l.getId();
        openStore();
        // If it has an id, do an update on that id.
        if (id != WeatherLocation.NO_ID) {
            byte b[]=l.toBytes();
            store.setRecord(id,b,0,b.length);
        } else {
            // If it doesn't have an id, find it.
            WeatherLocation target = getLocation(l.getLocation());
            // If there is a record matching this one, update it
            if (target!=null) {
                target.setForecast(l.getForecast());
                updateLocation(target);
            } else {
                // otherwise add this one.
                addLocation(l);
            }
        }
        closeStore();
    }
}
