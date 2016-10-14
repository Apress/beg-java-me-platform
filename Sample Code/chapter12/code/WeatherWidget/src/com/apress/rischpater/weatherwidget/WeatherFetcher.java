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
import javax.microedition.amms.control.MIDIChannelControl;
import javax.microedition.io.*;
/**
 *
 * @author kf6gpe
 */
public class WeatherFetcher implements Runnable {
    private String url = "http://www.noplace.com/weather.php";
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

    public void run() {
        String vars;
        String forecast = "";
        HttpConnection hc  =null;
        InputStream in = null;
        OutputStream out = null;
        
        vars = "location=" + WeatherFetcher.urlEncode(location.getLocation());
        
        try {
            hc = (HttpConnection)Connector.open(url);
            hc.setRequestMethod(HttpConnection.POST);
            hc.setRequestProperty("Content-Type",
                "application/x-www-formrlencoded");
            hc.setRequestProperty("Content-Length",
                Integer.toString(vars.length()));
            out=hc.openOutputStream();
            out.write(vars.getBytes());
            in=hc.openInputStream();
            int length=(int)hc.getLength();
            byte[] data = new byte[length];
            in.read(data);
            forecast = new String(data);
        }
        catch(Exception e){ forecast = e.getMessage(); }
        finally {
            try {
                if (in!=null) hc.close();
                if (out!=null) hc.close();
                if (hc!=null) hc.close();
            }
            catch(Exception e) {}
        }
        if (!cancelled) {
            location.setForecast(forecast);
            app.update();
        }
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


}