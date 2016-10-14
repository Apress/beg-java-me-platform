/*
 * WeatherFetcher.java
 *
 * Created on June 24, 2008, 2:48 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.apress.rischpater.weatherwidget;

import java.io.*;
import javax.microedition.io.*;
/**
 *
 * @author kf6gpe
 */
public class WeatherFetcher implements Runnable {
    private static String url = "http://www.noplace.com/wx/";

    
    private Thread thread;
    private Location location;
    private boolean cancelled;
    WeatherWidget app;
    
    /** Creates a new instance of WeatherFetcher */
    public WeatherFetcher(Location l, WeatherWidget a) {
        location = l;
        app = a;
        cancelled = false;
        if (l!=null && a!=null) {
            thread = new Thread(this);
            thread.start();
        }
    }
    
    public void cancel() {
        cancelled = true;
    }
    
    private static String urlEncode(String s)
    {
        if (s!=null) {
            StringBuffer tmp = new StringBuffer();
            int i=0;
            try {
                while (true) {
                    int b = (int)s.charAt(i++);
                    if ((b>=0x30 && b<=0x39) || 
                        (b>=0x41 && b<=0x5A) || 
                        (b>=0x61 && b<=0x7A)) {
                        tmp.append((char)b);
                    } else {
                        tmp.append("%");
                        if (b <= 0xf) tmp.append("0");
                            tmp.append(Integer.toHexString(b));
                    }
                }
            }
            catch (Exception e) {}
            return tmp.toString();
        }
        return null;
    }
    
    private String requestEncode() {
        StringBuffer result = new StringBuffer();
        result.append(url);
        result.append(urlEncode(location.getLocation()));
        return result.toString();
    }
    
    public void run() {
        String requestUrl;
        String response = "";
        HttpConnection hc = null;
        InputStream in = null;
        
        requestUrl = requestEncode();
        
        try {
            hc = (HttpConnection)Connector.open(requestUrl);
            hc.setRequestMethod(HttpConnection.GET);
            in=hc.openInputStream();
            int length=(int)hc.getLength();
            byte[] data = new byte[length];
            in.read(data);
            response = new String(data);
        }
        catch(Exception e){}
        finally {
            try {
                if (in!=null) hc.close();
                if (hc!=null) hc.close();
            }
            catch(Exception e) {}
        }
        if (!cancelled) {
            System.out.println(response);
            location.fromXml(response);
            System.out.print(location.toXml());
            app.update();
        }
    }   
}