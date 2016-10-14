package example.canvas;

import javax.microedition.midlet.*; 
import javax.microedition.lcdui.*; 


public class MyCanvas extends Canvas  { 
    protected void paint( Graphics g) {
      System.out.println( "Paint!"); 
    }
    protected void keyPressed( int code ) {
      System.out.println( "pressed!");
      System.out.println( KEY_NUM9 );
      System.out.println( code );
        
    }
    protected void keyReleased( int code ) {
      System.out.println( "released!");
        
    }
    protected void keyRepeated( int code ) {
      System.out.println( "repeated!");   
    }

    protected void pointerPressed(int x, int y) {
        System.out.println("Press at " + Integer.toString(x) + "," + Integer.toString(y));
    }
    
    protected void pointerDragged( int x, int y) {
        System.out.println("Drag to  " + Integer.toString(x) + "," + Integer.toString(y));        
    }
    
    protected void pointerReleased( int x, int y ) {
        System.out.println("Release at " + Integer.toString(x) + "," + Integer.toString(y));
        
    }
}