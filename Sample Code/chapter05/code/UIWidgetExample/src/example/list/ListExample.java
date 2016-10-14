import javax.microedition.midlet.*; 
import javax.microedition.lcdui.*; 


public class ListExample extends MIDlet  { 
  public void startApp() { 
    final List l = new List("Pizza Toppings", Choice.MULTIPLE);
    l.append( "Anchovies", null );
    l.append( "Cheese", null );
    l.append( "Olives", null );
    l.append( "Onions", null );
    l.append( "Pepperoni", null );
    l.append( "Sausage", null );

    l.addCommand( new Command( "Order", "Order Pizza", Command.ITEM, 0));
    l.setCommandListener( new CommandListener() { 
	      public void commandAction(Command c, Displayable s) { 
	        boolean isSelected[] = new boolean[ l.size() ];
                int i;
                l.getSelectedFlags( isSelected );
                
                for ( i = 0; i < l.size(); i++ ) {
                    if ( isSelected[ i ] ) {
                        System.out.println( l.getString( i ));
                    }
                }
	      } 
	    } ); 

    
    Display.getDisplay(this).setCurrent(l);    
  } 
  public void pauseApp() { } 
  public void destroyApp(boolean unconditional) { } 
}