import javax.microedition.midlet.*; 
import javax.microedition.lcdui.*; 


public class CommandExample extends MIDlet { 
  public void startApp() { 
    Displayable d = new TextBox("Demo", "", 20, TextField.ANY); 
    Command exit = new Command("Exit", Command.EXIT, 0); 
    Command help = new Command("Help", Command.HELP, 1); 
    Command stop = new Command("Stop", Command.STOP, 2); 

    d.addCommand(exit); 
    d.addCommand(help); 
    d.addCommand(stop); 
    
    d.setCommandListener(new CommandListener() { 
      public void commandAction(Command c, Displayable s) { 
        notifyDestroyed(); 
      } 
    } );     
    
    Display.getDisplay(this).setCurrent(d); 
  } 
  public void pauseApp() { } 
  public void destroyApp(boolean unconditional) { } 
}