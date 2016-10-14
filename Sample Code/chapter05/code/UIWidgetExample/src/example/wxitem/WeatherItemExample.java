package example.wxitem;

import javax.microedition.midlet.*; 
import javax.microedition.lcdui.*; 


public class WeatherItemExample extends MIDlet { 
  public void startApp() { 
    Form f = new Form("WeatherItemTest"); 
    Command exit = new Command("Exit", Command.EXIT, 0); 
    WeatherItem item = new WeatherItem("Today", Display.getDisplay( this ));
    item.setConditions(item.SUNNY);
    f.append(item);
    
    f.addCommand(exit); 
    
    f.setCommandListener(new CommandListener() { 
      public void commandAction(Command c, Displayable s) { 
        notifyDestroyed(); 
      } 
    } );     
    
    Display.getDisplay(this).setCurrent(f); 
  } 
  public void pauseApp() { } 
  public void destroyApp(boolean unconditional) { } 
}