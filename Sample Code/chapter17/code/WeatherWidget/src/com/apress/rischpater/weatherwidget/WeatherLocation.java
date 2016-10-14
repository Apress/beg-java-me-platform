/*
 * WeatherLocation.java
 *
 * Created on March 2, 2008, 9:01 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.apress.rischpater.weatherwidget;

import javax.microedition.rms.*;
import java.io.*;
import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
/**
 *
 * @author kf6gpe
 */
public class WeatherLocation {
    private final static int FIELD_VERSION = 1;
    private final static int FIELD_CITY = 2;
    private final static int FIELD_FORECAST = 3;
    private final static int FIELD_TEMP = 4;
    private final static int FIELD_WINDSPEED = 5;
    private final static int FIELD_WINDDIR = 6;
    private final static int FIELD_PRECIP = 7;
    private final static int FIELD_STATE = 8;
    private final static int FIELD_COUNTRY = 9;
    private final static int FIELD_PRECIP_PROB = 10;
    private final static int FIELD_PRECIP_TYPE = 11;
    
    private final static String XML_PREAMBLE = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
    private final static String XML_START_OPEN = "<";
    private final static String XML_START_EMPTYOPEN = "<";
    private final static String XML_START_CLOSE="</";
    private final static String XML_END_OPEN = ">";
    private final static String XML_END_EMPTYOPEN = "/>";
    private final static String XML_END_CLOSE = ">";
    private final static String XML_ATTR_IS = "=";
    private final static String XML_ATTR_QUOTE = "\"";
    public final static String XML_TAG_WEATHER="weather";
    public final static String XML_ATTR_CITY="city";
    public final static String XML_ATTR_STATE="state";
    public final static String XML_ATTR_COUNTRY="country";
    public final static String XML_TAG_TEMPS="temperatures";
    public final static String XML_ATTR_UNITS="units";
    private final static String XML_ATTR_TUNITS=" units=\"F\"";
    public final static String XML_TAG_TEMP="temperature";
    public final static String XML_ATTR_TYPE="type";
    public final static String XML_TAG_WIND="wind";
    private final static String XML_ATTR_WUNITS=" units=\"MPH\"";
    public final static String XML_TAG_DIRECTION="direction";
    public final static String XML_TAG_SPEED="speed";
    public final static String XML_TAG_PRECIP="precipitation";
    public final static String XML_ATTR_PROB="probability";
    private final static String XML_ATTR_PUNITS=" units=\"in\"";
    public final static String XML_TAG_TEXT="text";
    public final static String XML_TAG_WHEN="when";
    public final static String XML_ATTR_TIME="time";
    private final static String XML_ATTR_WTIME=" time=\"today\"";
    private final static String XML_NEWLINE="\n";
    
    public final static int NO_ID = -1;
    
    private final static int version = 1;
    private String city, state, country;
    private String forecast;
    private String temp;
    private String windSpeed;
    private String windDirection;
    private String precipitation, precipitationProb, precipitationType;
    private int recordid;
    
    /** Creates a new instance of WeatherLocation */
    public WeatherLocation() {
        recordid = NO_ID;
    }
    
    public WeatherLocation(String l) {
        setLocation(l);
        recordid = NO_ID;
    }
    
    public WeatherLocation(byte[] b) {
        fromBytes(b);
        recordid = NO_ID;
    }

    public WeatherLocation(byte[] b, int id) {
        fromBytes(b);
        recordid = id;
    }
    
    public String getLocation() {
        StringBuffer result = new StringBuffer();
        if (city!=null) {
            result.append(city);
        }
        result.append(",");
        if (state!=null) {
            result.append(state);
        }
        result.append(",");
        if (country!=null) {
            result.append(country);
        }
       return result.toString();
    }
    
    public String getCity() {
        if(city != null)
            return city;
        else
            return "";
    }

    public String getState() {
        if(state != null)
            return state;
        else
            return "";
    }
    
    public String getCountry() {
        if(country != null)
            return country;
        else
            return "";
    }    
    
    public void setLocation(String l) {
        int cityStart, stateStart, countryStart;
        cityStart = 0;
        stateStart = l.indexOf(",");
        if (stateStart==-1) {
            city = l;
            state="";
            country="";
            return;
        }
        countryStart = l.indexOf(",",stateStart+1);
        city = l.substring(cityStart,stateStart);
        if (countryStart==-1)
        {
            state = l.substring(stateStart-1);
            country = "USA";
            return;
        }
        state = l.substring(stateStart+1,countryStart);
        country = l.substring(countryStart+1);
    }
    
    public void setLocation(String c, String s, String co) {
        city = c;
        state = s;
        country = co;
    }
    
    public String getWind() {
        if (windSpeed != null && windDirection != null)
            return windSpeed + " mph from the " + windDirection;
        else return "";
    }
    
    public void setWind(String s, String d) {
        windSpeed = s;
        windDirection = d;
    }
    
    public String getTemperature() {
        if (temp!=null)
            return temp;
        else
            return "";
    }
    
    public void setTemperature(String t) {
        temp = t;
    }
    
    public String getPrecipitation() {
        StringBuffer result = new StringBuffer();
        if (precipitation != null) {
            result.append(precipitation);
        } else {
            result.append("unknown");
        }
        result.append("in of ");
        if (precipitationType != null) {
            result.append(precipitationType);
        } else {
            result.append("unknown");
        }
        result.append("(");
        if (precipitationProb != null) {
            result.append(precipitationProb);
        } else {
            result.append("unknown");            
        }
        result.append("%)");
        return result.toString();
    }
    
    public void setPrecipitation(String p, String pp, String pt) {
        precipitation = p;
        precipitationProb = pp;
        precipitationType = pt;
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
            if (city != null) {
                dos.writeInt(FIELD_CITY);
                dos.writeUTF(getCity());
            }
            if (state != null) {
                dos.writeInt(FIELD_STATE);
                dos.writeUTF(getState());
            }
            if (country != null) {
                dos.writeInt(FIELD_COUNTRY);
                dos.writeUTF(getCountry());
            }
            if (forecast != null)
            {
                dos.writeInt(FIELD_FORECAST);
                dos.writeUTF(getForecast());
            }
            if (temp != null) {
                dos.writeInt(FIELD_TEMP);
                dos.writeUTF(getTemperature());
            }
            if (precipitation != null && 
                precipitationProb != null && 
                precipitationType != null) {
                dos.writeInt(FIELD_PRECIP);
                dos.writeUTF(precipitation);
                dos.writeInt(FIELD_PRECIP_PROB);
                dos.writeUTF(precipitationProb);
                dos.writeInt(FIELD_PRECIP_TYPE);
                dos.writeUTF(precipitationType);
            }
            if (windDirection != null && windSpeed != null) {
                dos.writeInt(FIELD_WINDDIR);
                dos.writeUTF(windDirection);
                dos.writeInt(FIELD_WINDSPEED);
                dos.writeUTF(windSpeed);
            }
        }
        catch(Exception e) {
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
        String dir = null, speed = null; 
        String c = null, s = null, co = null;
        String p = null, pp = null, pt = null;
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
                    case FIELD_CITY:
                        c = dis.readUTF();
                        break;
                    case FIELD_STATE:
                        s = dis.readUTF();
                        break;
                    case FIELD_COUNTRY:
                        co = dis.readUTF();
                        break;
                    case FIELD_FORECAST:
                        setForecast(dis.readUTF());
                        break;
                    case FIELD_TEMP:
                        setTemperature(dis.readUTF());
                        break;
                    case FIELD_PRECIP:
                        p = dis.readUTF();
                        break;
                    case FIELD_PRECIP_PROB:
                        pp = dis.readUTF();
                        break;
                    case FIELD_PRECIP_TYPE:
                        pt = dis.readUTF();
                        break;
                    case FIELD_WINDDIR:
                        dir = dis.readUTF();
                        break;
                    case FIELD_WINDSPEED:
                        speed = dis.readUTF();
                        break;
                }
            }
        }
        catch (Exception e) {}
        finally {
            setLocation(c, s, co);
            setPrecipitation(p, pp, pt);
            setWind(speed, dir);
            try {
                dis.close();
                bais.close();
            }
            catch (Exception e) {}
        }
        dis = null;
        bais = null;
    }
    
    public String toXml() {
        StringBuffer xmlBuffer = new StringBuffer();
        xmlBuffer.append(XML_PREAMBLE);
        xmlBuffer.append(XML_START_OPEN);
        xmlBuffer.append(XML_TAG_WEATHER);
        if (city != null) {
	    xmlBuffer.append(" ");
	    xmlBuffer.append(XML_ATTR_CITY);
	    xmlBuffer.append(XML_ATTR_IS);
	    xmlBuffer.append(XML_ATTR_QUOTE);
	    xmlBuffer.append(city);
	    xmlBuffer.append(XML_ATTR_QUOTE);
        }
        if (state != null) {
	    xmlBuffer.append(" ");
	    xmlBuffer.append(XML_ATTR_STATE);
	    xmlBuffer.append(XML_ATTR_IS);
	    xmlBuffer.append(XML_ATTR_QUOTE);
	    xmlBuffer.append(state);
	    xmlBuffer.append(XML_ATTR_QUOTE);
        }
        if (country != null) {
	    xmlBuffer.append(" ");
	    xmlBuffer.append(XML_ATTR_COUNTRY);
	    xmlBuffer.append(XML_ATTR_IS);
	    xmlBuffer.append(XML_ATTR_QUOTE);
	    xmlBuffer.append(country);
	    xmlBuffer.append(XML_ATTR_QUOTE);
        }        
        if (forecast != null || 
	    temp != null ||
	    (precipitation != null && 
             precipitationProb != null && 
             precipitationType != null ) ||
	    (windSpeed != null && windDirection != null)) {
	    xmlBuffer.append(XML_END_OPEN);
	    xmlBuffer.append(XML_NEWLINE);
	    if (temp != null) {
		xmlBuffer.append(XML_START_OPEN);
		xmlBuffer.append(XML_TAG_TEMPS);
		xmlBuffer.append(XML_ATTR_TUNITS);
		xmlBuffer.append(XML_END_OPEN);
		xmlBuffer.append(XML_NEWLINE);
		xmlBuffer.append(XML_START_OPEN);
		xmlBuffer.append(XML_TAG_TEMP);
                xmlBuffer.append(" ");
		xmlBuffer.append(XML_ATTR_TYPE);
                xmlBuffer.append(XML_ATTR_IS);
                xmlBuffer.append("\"current\"");
		xmlBuffer.append(XML_END_OPEN);
		xmlBuffer.append(temp);
		xmlBuffer.append(XML_START_CLOSE);
		xmlBuffer.append(XML_TAG_TEMP);
		xmlBuffer.append(XML_END_CLOSE);
		xmlBuffer.append(XML_NEWLINE);
		xmlBuffer.append(XML_START_CLOSE);
		xmlBuffer.append(XML_TAG_TEMPS);
		xmlBuffer.append(XML_END_CLOSE);
		xmlBuffer.append(XML_NEWLINE);
	    }
	    if (windSpeed != null && windDirection != null) {
		xmlBuffer.append(XML_START_OPEN);
		xmlBuffer.append(XML_TAG_WIND);
		xmlBuffer.append(XML_ATTR_WUNITS);
		xmlBuffer.append(XML_END_OPEN);
		xmlBuffer.append(XML_NEWLINE);
		xmlBuffer.append(XML_START_OPEN);
		xmlBuffer.append(XML_TAG_SPEED);
		xmlBuffer.append(XML_END_OPEN);
		xmlBuffer.append(windSpeed);
		xmlBuffer.append(XML_START_CLOSE);
		xmlBuffer.append(XML_TAG_SPEED);
		xmlBuffer.append(XML_END_CLOSE);
		xmlBuffer.append(XML_START_OPEN);
		xmlBuffer.append(XML_TAG_DIRECTION);
		xmlBuffer.append(XML_END_OPEN);
		xmlBuffer.append(windDirection);
		xmlBuffer.append(XML_START_CLOSE);
		xmlBuffer.append(XML_TAG_DIRECTION);
		xmlBuffer.append(XML_END_CLOSE);
		xmlBuffer.append(XML_NEWLINE);
		xmlBuffer.append(XML_START_CLOSE);
		xmlBuffer.append(XML_TAG_WIND);
		xmlBuffer.append(XML_END_CLOSE);
		xmlBuffer.append(XML_NEWLINE);
	    }
	    if (precipitation != null &&
                precipitationProb != null &&
                precipitationType != null) {
		xmlBuffer.append(XML_START_OPEN);
		xmlBuffer.append(XML_TAG_PRECIP);
		xmlBuffer.append(XML_ATTR_PUNITS);
                xmlBuffer.append(" ");
                xmlBuffer.append(XML_ATTR_PROB);
                xmlBuffer.append(XML_ATTR_IS);
                xmlBuffer.append(XML_ATTR_QUOTE);
                xmlBuffer.append(precipitationProb);
                xmlBuffer.append(XML_ATTR_QUOTE);
                xmlBuffer.append(" ");
                xmlBuffer.append(XML_ATTR_TYPE);
                xmlBuffer.append(XML_ATTR_IS);
                xmlBuffer.append(XML_ATTR_QUOTE);
                xmlBuffer.append(precipitationType);
                xmlBuffer.append(XML_ATTR_QUOTE);               
		xmlBuffer.append(XML_END_OPEN);
		xmlBuffer.append(precipitation);
		xmlBuffer.append(XML_START_CLOSE);
		xmlBuffer.append(XML_TAG_PRECIP);
		xmlBuffer.append(XML_END_CLOSE);
		xmlBuffer.append(XML_NEWLINE);
	    }
	    if (forecast != null) {
		xmlBuffer.append(XML_START_OPEN);
		xmlBuffer.append(XML_TAG_TEXT);
		xmlBuffer.append(XML_END_OPEN);
		xmlBuffer.append(XML_NEWLINE);
		xmlBuffer.append(XML_START_OPEN);
		xmlBuffer.append(XML_TAG_WHEN);
		xmlBuffer.append(XML_ATTR_WTIME);
		xmlBuffer.append(XML_END_OPEN);
		xmlBuffer.append(forecast);
		xmlBuffer.append(XML_START_CLOSE);
		xmlBuffer.append(XML_TAG_WHEN);
		xmlBuffer.append(XML_END_CLOSE);
		xmlBuffer.append(XML_NEWLINE);
		xmlBuffer.append(XML_START_CLOSE);
		xmlBuffer.append(XML_TAG_TEXT);
		xmlBuffer.append(XML_END_CLOSE);
		xmlBuffer.append(XML_NEWLINE);
	    }
            xmlBuffer.append(XML_START_CLOSE);
            xmlBuffer.append(XML_TAG_WEATHER);
            xmlBuffer.append(XML_END_CLOSE);
            xmlBuffer.append(XML_NEWLINE);
	} else {
	    xmlBuffer.append(XML_END_EMPTYOPEN);
	}

        return xmlBuffer.toString();
    }
    
    public void fromXml(String xml) {
        WeatherLocationParser parser = new WeatherLocationParser(this);
        byte[] xmlBytes;
        ByteArrayInputStream bis;

        xmlBytes = xml.getBytes();
        bis = new ByteArrayInputStream(xmlBytes);
        try {
            parser.parse(bis);
        }
        catch(Exception e){}
        finally {
            try {
                bis.close();
            }
            catch(Exception e) {}
        }
    }
}
