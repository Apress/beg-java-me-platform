/*
 * LocationStore.java
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
public class LocationStore {
    private static final String storeName = "wx";
    private RecordStore store = null;
    
    public LocationStore() {
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
    
    public void addLocation(Location l) throws RecordStoreException {
        Location existing = getLocation(l.getLocation());
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
                        Location r1 = new Location(b1);
                        Location r2 = new Location(b2);
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
                Location l = new Location(e.nextRecord());
                result[i] = l.getLocation();
                l = null;
            }
            closeStore();
        }
        catch(Exception ex) { closeStore(); }
        return result;
    }
    
    public Location getLocation( final String location ) throws RecordStoreException {
        openStore();
        Location l = null;
        RecordEnumeration e = store.enumerateRecords( 
            new RecordFilter () {
                public boolean matches( byte[] b) {
                    Location r = new Location(b);
                    if (r.getLocation().equalsIgnoreCase(location)) return true;
                    else return false;
                }
            },
            null,       
            false);

        if (e.hasNextElement()) {
            int id = e.nextRecordId();
            l = new Location(store.getRecord(id), id);
        } 
        closeStore();
        return l;        
    }
    
    public void updateLocation(Location l) throws RecordStoreException {
        int id = l.getId();
        openStore();
        // If it has an id, do an update on that id.
        if (id != Location.NO_ID) {
            byte b[]=l.toBytes();
            store.setRecord(id,b,0,b.length);
        } else {
            // If it doesn't have an id, find it.
            Location target = getLocation(l.getLocation());
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
