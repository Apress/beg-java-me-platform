package example.canvas;

import javax.microedition.midlet.*; 
import javax.microedition.lcdui.*; 

public class CanvasExample extends MIDlet  { 
  public void startApp() { 
    MyCanvas c = new MyCanvas();

    Display.getDisplay(this).setCurrent(c);    
  } 
  public void pauseApp() { } 
  public void destroyApp(boolean unconditional) { } 
}