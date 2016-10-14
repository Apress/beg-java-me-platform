
package hello;

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

public class HelloMidlet extends MIDlet {

    public HelloMidlet() {
    }

    public void startApp() {
        Display.getDisplay(this).setCurrent(
			new TextBox("", "Hello World", 20, 0) );
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }
}


