/*
 * LocationParserHandler.java
 *
 * Created on July 6, 2008, 12:21 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.apress.rischpater.weatherwidget;

import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;


/**
 *
 * @author kf6gpe
 */
public class LocationParserHandler extends DefaultHandler {
    private Location location;
    private boolean setForecast;
    private boolean hasForecast;
    private boolean setTemp;
    private String precipitation, precipType, precipProb;
    private String windSpeed, windDirection;
    private StringBuffer buffer;
    
    public LocationParserHandler( Location l ) {
        location = l;
    }
    
    public Location getLocation() {
        return location;
    }
        
    public void startDocument() {
        if ( location == null )
            location = new Location();
        setForecast = false;
        hasForecast = false;
        setTemp = false;
    }
    
    public void startElement(String uri, String localName, 
        String qName, Attributes attributes )
        throws SAXException {
        buffer = new StringBuffer();
        if ( qName.compareTo(Location.XML_TAG_WEATHER) == 0) {
            String c = attributes.getValue(Location.XML_ATTR_CITY);
            String s = attributes.getValue(Location.XML_ATTR_STATE);
            String co = attributes.getValue(Location.XML_ATTR_COUNTRY);
            location.setLocation(c,s,co);
            hasForecast = false;
        }
        if ( qName.compareTo(Location.XML_TAG_TEMPS) == 0) {
            String u = attributes.getValue(Location.XML_ATTR_UNITS);
            if ( u != null && u.compareTo("F") != 0) throw new SAXException("Invalid temperature units");
            hasForecast = false;
        }        
        if ( qName.compareTo(Location.XML_TAG_TEMP) == 0 ) {
            String t = attributes.getValue(Location.XML_ATTR_TYPE);
            if (t != null && t.compareTo("current") != 0) {
                setTemp = true;
            } else {
                setTemp = false;
            }
            hasForecast = false;
        }
        if ( qName.compareTo(Location.XML_TAG_WIND) == 0) {
            String u = attributes.getValue(Location.XML_ATTR_UNITS);
            if ( u != null && u.compareTo("MPH") != 0) 
                throw new SAXException("Invalid temperature units");
            hasForecast = false;
        }    
        if ( qName.compareTo(Location.XML_TAG_PRECIP) == 0 )
        {
            String u = attributes.getValue(Location.XML_ATTR_UNITS);
            if (u != null && u.compareTo("in") != 0) 
                throw new SAXException("Invalid precipitation units");
            precipProb = attributes.getValue(Location.XML_ATTR_PROB);
            precipType = attributes.getValue(Location.XML_ATTR_TYPE);
            hasForecast = false;
        }
        if (qName.compareTo(Location.XML_TAG_TEXT) == 0) {
            hasForecast = true;
        }
        if (qName.compareTo(Location.XML_TAG_WHEN) == 0 && hasForecast) {
            String w = attributes.getValue(Location.XML_ATTR_TIME);
            if (w != null && w.compareTo("now") == 0) {
                setForecast = true;
            } else {
                setForecast = false;
            }
        }
    }
    
    public void characters( char[] ch, int start, int length) {
        if (buffer != null)
            buffer.append(ch, start, length);
    }
    
    public void endElement( String uri, String localName, String qName ) {
        if (qName.compareTo(Location.XML_TAG_SPEED) == 0) {
            windSpeed = buffer.toString();
        }
        if (qName.compareTo(Location.XML_TAG_DIRECTION) == 0) {
            windDirection = buffer.toString();
        }
        if (qName.compareTo(Location.XML_TAG_WIND) == 0) {
            location.setWind(windSpeed, windDirection);
        }
        if (qName.compareTo(Location.XML_TAG_PRECIP) == 0) {
            precipitation = buffer.toString();
            location.setPrecipitation(precipitation,precipProb,precipType);
        }
        if (qName.compareTo(Location.XML_TAG_TEMP) == 0 && setTemp )
        {
            location.setTemperature(buffer.toString());
        }
        if (qName.compareTo(Location.XML_TAG_WHEN) == 0 && setForecast) {
            location.setForecast( buffer.toString());
        }
    }
    
    public void endDocument() {
    }
}
