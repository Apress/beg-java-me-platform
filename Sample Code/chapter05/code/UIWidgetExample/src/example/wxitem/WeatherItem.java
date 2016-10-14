package example.wxitem;

import javax.microedition.midlet.*; 
import javax.microedition.lcdui.*; 


public class WeatherItem
       extends CustomItem { 
    private String title;
    private Display display;
    private int width, height;
    private boolean hasFocus = false;
    private int conditions;
    
    // States (not mutatable by caller!)
    public final int    SUNNY = 1;
    public final int    PARTLY_CLOUDY = 2;
    public final int    CLOUDY = 3;
    public final int    SHOWERS = 4;
    public final int    RAIN = 5;
    public final int    FLURRIES = 6;
    public final int    SNOW = 7;
    public final int    SLEET = 8;
    
    // Colors
    final private int YELLOW = 0xFFFF00;
    
    public WeatherItem( String t, Display d ) {
        super(t);
        title = t;
        display = d;
        hasFocus = false;
        width = 64;
        height = 64;
        conditions = 0;
    }
    
    public void setConditions( int c ) {
        conditions = c;
    }
    
    public int getConditions( ) {
        return conditions;
    }
    
    protected int getMinContentHeight() {
        return height;
    }

    protected int getMinContentWidth() {
        return width;
    }

    protected int getPrefContentHeight(int w) {
        if ( w < 0 )
            return height;
        else
            return height * w / width;
    }

    protected int getPrefContentWidth(int h) {
        if ( h < 0 )
            return width;
        else
            return width * h / height;
    }

    protected void paint(Graphics g, int w, int h) {
        // Always paint SOMETHING
        g.fillRect(0, 0, w, h);
        switch( conditions )
        {
            case SUNNY:
                drawSun(g, w, h);
                break;
            case PARTLY_CLOUDY:
                drawSun(g, w, h);
                // FALL-THRU
            case CLOUDY:
                drawCloud(g, w, h);
                break;
            case SHOWERS:
                drawSun(g, w, h);
                // FALL-THRU
            case RAIN:
                drawCloud(g, w, h);
                drawRain(g, w, h);
                break;
            case FLURRIES:
                drawSun(g, w, h);
                // FALL-THRU
            case SNOW:
                drawCloud(g, w, h);
                drawSnow(g, w, h);
                break;
            case SLEET:
                drawCloud(g, w, h);
                drawRain(g, w, h);
                drawSnow(g, w, h);
                break;
            default:
                drawUnknown(g, w, h);
                break;
        }
    }

    protected boolean traverse(int dir, int viewportWidth, int viewportHeight, 
                               int[] visRect) {
        hasFocus = !hasFocus;
        if ( hasFocus )
        {
            visRect[ 0 ] = 0;
            visRect[ 1 ] = 0;
            visRect[ 2 ] = width;
            visRect[ 3 ] = height;
        }
        return hasFocus;
    }

    private void drawSun(Graphics g, int w, int h) {
        int x, y, min, r;
        min = Math.min(w, h);
        r = 3 * min / 4;
        x = ( w - r ) / 4;
        y = ( h - r ) / 4;

        g.setColor( YELLOW );
        g.fillArc(x, y, x + r, y + r, 0, 360);   
    }
    
    private void drawCloud(Graphics g, int w, int h) {
        
    }
    
    private void drawRain(Graphics g, int w, int h) {
        
    }
    
    private void drawSnow(Graphics g, int w, int h) {
        
    }

    private void drawUnknown(Graphics g, int w, int h) {
        
    }
}