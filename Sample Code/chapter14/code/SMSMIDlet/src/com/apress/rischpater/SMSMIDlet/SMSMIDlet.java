package com.apress.rischpater.SMSMIDlet;

import javax.microedition.midlet.*; 
import javax.microedition.io.*; 
import javax.microedition.lcdui.*; 
import javax.wireless.messaging.*; 
import java.io.IOException; 

public class SMSMIDlet 
    extends MIDlet 
    implements CommandListener, MessageListener, Runnable { 
    private SMSSender sender = null; 
    private Command exitCommand = new Command("Exit", Command.EXIT, 2); 
    private Command sendCommand = new Command("Send", Command.SCREEN, 1); 
    private Display display = null; 
    private String port = "1234"; 
    private TextField numberEntry= null; 
    private TextField msgEntry = null;
    private Form form = null; 
    private String senderAddress = null; 
    private boolean endNow = false; 
    private MessageConnection c = null; 
    String msgReceived = null; 


    public SMSMIDlet() { 
        sender = SMSSender.getInstance(); 
    } 
  
    public void commandAction(javax.microedition.lcdui.Command c, 
                              javax.microedition.lcdui.Displayable d) { 
        if (c == exitCommand) { 
            if (!sender.isSending()) { 
                destroyApp(true); 
                notifyDestroyed(); 
            }
        } else if (c == sendCommand) { 
            String dest = numberEntry.getString(); 
			String msg = msgEntry.getString();
                if (dest.length() > 0) 
                    sender.sendMsg(dest, port, msg ); 
        }
    }

    protected void destroyApp(boolean param) { 
        try { 
            endNow = true; 
            c.close(); 
        } catch (IOException ex) { 
            System.out.println("destroyApp caught: "); 
            ex.printStackTrace(); 
        } 
    }
 
    protected void pauseApp() { 
        try { 
            endNow = true; 
            c.close(); 
        } catch (IOException ex) {} 
    }
 
    protected void startApp() { 
        if (form == null) { 
            form = new Form("SMSMIDlet"); 
            numberEntry = new TextField("Connect to:", 
                                         null, 256, 
                                         TextField.PHONENUMBER); 
            msgEntry = new TextField("Message:", 
                                     null, 160,
                                     TextField.ANY);
            form.append(numberEntry); 
            form.append(msgEntry);

            form.addCommand(exitCommand); 
            form.addCommand(sendCommand); 
            form.setCommandListener(this); 
        } 
        Display.getDisplay(this).setCurrent(form); 
        startReceive(); 
    }
 
    private void startReceive() {
	    Thread t; 
        t = new Thread(this); 
        t.start(); 
    }

    public void run() { 
        Message msg = null; 
        c = null; 
        endNow = false; 
      
        /** Check for sms connection. */ 
        try { 
            c = (MessageConnection) Connector.open("sms://:" + port);
            msg = c.receive(); 
            while ((msg != null) && (!endNow)) { 
                if (msg instanceof TextMessage) { 
                    msgReceived = ((TextMessage)msg).getPayloadText(); 
                    Display.getDisplay(this).callSerially(new SetMessage()); 
                } 
                msg = c.receive(); 
            } 
        } catch (IOException e) { 
         // Normal exit when connection is closed 
        } 
    } 

    class SetMessage implements Runnable { 
        public void run() { 
            msgEntry.setString( msgReceived ); 
        } 
    } 
} 
