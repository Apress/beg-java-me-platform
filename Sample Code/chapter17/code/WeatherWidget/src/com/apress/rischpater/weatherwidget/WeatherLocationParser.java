/*
 * WeatherLocationParser.java
 *
 * Created on July 6, 2008, 12:21 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.apress.rischpater.weatherwidget;

import org.kxml2.io.*;
import java.io.*;
import javax.microedition.io.*;
import java.util.Vector;

/**
 *
 * @author kf6gpe
 */
public class WeatherLocationParser {
    private WeatherLocation location;
    private boolean setForecast;
    private boolean hasForecast;
    private boolean setTemp;
    private String precipitation, precipType, precipProb;
    private String windSpeed, windDirection;
    private StringBuffer buffer;
    
    public WeatherLocationParser() {
        location = new WeatherLocation();
    }
    
    public WeatherLocationParser(WeatherLocation l) {
        location = l;
    }
    
    public void parse(ByteArrayInputStream in) {
        try {
            InputStreamReader reader = new InputStreamReader(in);
            KXmlParser parser = new KXmlParser();
            boolean keepParsing = true;
            parser.setInput(reader);
            while(keepParsing) {
                int type = parser.next();
                
                switch(type) {
                    case KXmlParser.START_DOCUMENT:
                        startDocument();
                        break;
                    case KXmlParser.START_TAG:
                        startElement(parser);
                        break;
                    case KXmlParser.END_TAG:
                        endElement(parser);
                        break;
                    case KXmlParser.TEXT:
                        characters(parser);
                        break;
                    case KXmlParser.END_DOCUMENT:
                        endDocument();
                        keepParsing = false;
                        break;
                }
                
            }
        }
        catch(Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }
    }
  
    public WeatherLocation getLocation() {
        return location;
    }
    
    public void startDocument() {
        if ( location == null )
            location = new WeatherLocation();
        setForecast = false;
        hasForecast = false;
        setTemp = false;
    }
    
    public void startElement(KXmlParser parser)
        throws Exception {
        String qName = parser.getName();
        buffer = new StringBuffer();
        if ( qName.compareTo(WeatherLocation.XML_TAG_WEATHER) == 0) {
            String c = parser.getAttributeValue(null,WeatherLocation.XML_ATTR_CITY);
            String s = parser.getAttributeValue(null,WeatherLocation.XML_ATTR_STATE);
            String co = parser.getAttributeValue(null,WeatherLocation.XML_ATTR_COUNTRY);
            location.setLocation(c,s,co);
            hasForecast = false;
        }
        if ( qName.compareTo(WeatherLocation.XML_TAG_TEMPS) == 0) {
            String u = parser.getAttributeValue(null,WeatherLocation.XML_ATTR_UNITS);
            if ( u != null && u.compareTo("F") != 0) throw new Exception("Invalid temperature units");
            hasForecast = false;
        }        
        if ( qName.compareTo(WeatherLocation.XML_TAG_TEMP) == 0 ) {
            String t = parser.getAttributeValue(null,WeatherLocation.XML_ATTR_TYPE);
            if (t != null && t.compareTo("current") == 0) {
                setTemp = true;
            } else {
                setTemp = false;
            }
            hasForecast = false;
        }
        if ( qName.compareTo(WeatherLocation.XML_TAG_WIND) == 0) {
            String u = parser.getAttributeValue(null,WeatherLocation.XML_ATTR_UNITS);
            if ( u != null && u.compareTo("MPH") != 0) 
                throw new Exception("Invalid temperature units");
            hasForecast = false;
        }    
        if ( qName.compareTo(WeatherLocation.XML_TAG_PRECIP) == 0 )
        {
            String u = parser.getAttributeValue(null,WeatherLocation.XML_ATTR_UNITS);
            if (u != null && u.compareTo("in") != 0) 
                throw new Exception("Invalid precipitation units");
            precipProb = parser.getAttributeValue(null,WeatherLocation.XML_ATTR_PROB);
            precipType = parser.getAttributeValue(null,WeatherLocation.XML_ATTR_TYPE);
            hasForecast = false;
        }
        if (qName.compareTo(WeatherLocation.XML_TAG_TEXT) == 0) {
            hasForecast = true;
        }
        if (qName.compareTo(WeatherLocation.XML_TAG_WHEN) == 0 && hasForecast) {
            String w = parser.getAttributeValue(null,WeatherLocation.XML_ATTR_TIME);
            if (w != null && w.compareTo("now") == 0) {
                setForecast = true;
            } else {
                setForecast = false;
            }
        }
    }
    
    public void characters(KXmlParser parser) {
        if (buffer != null)
            buffer.append(parser.getText());
    }
    
    public void endElement(KXmlParser parser) {
        String qName = parser.getName();
        if (qName.compareTo(WeatherLocation.XML_TAG_SPEED) == 0) {
            windSpeed = buffer.toString();
        }
        if (qName.compareTo(WeatherLocation.XML_TAG_DIRECTION) == 0) {
            windDirection = buffer.toString();
        }
        if (qName.compareTo(WeatherLocation.XML_TAG_WIND) == 0) {
            location.setWind(windSpeed, windDirection);
        }
        if (qName.compareTo(WeatherLocation.XML_TAG_PRECIP) == 0) {
            precipitation = buffer.toString();
            location.setPrecipitation(precipitation,precipProb,precipType);
        }
        if (qName.compareTo(WeatherLocation.XML_TAG_TEMP) == 0 && setTemp )
        {
            location.setTemperature(buffer.toString());
        }
        if (qName.compareTo(WeatherLocation.XML_TAG_WHEN) == 0 && setForecast) {
            location.setForecast( buffer.toString());
        }
    }
    
    public void endDocument() {
    }
}
