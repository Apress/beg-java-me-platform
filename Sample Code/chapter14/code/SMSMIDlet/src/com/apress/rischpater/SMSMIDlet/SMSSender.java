package com.apress.rischpater.SMSMIDlet;

import javax.microedition.io.*; 
import javax.wireless.messaging.*; 
import java.io.IOException;
 
public class SMSSender implements Runnable { 
    private static SMSSender me = new SMSSender(); 
    private String receiver = null; 
    private String port = null; 
    private String msgString = null; 
    private boolean sending = false; 

    private SMSSender() { 
    }

    public static SMSSender getInstance() { 
        return me; 
    } 

    public boolean isSending() {
        return sending;
    }
    
    public void sendMsg(String rcvr, String p, String msgText) {
        if (sending) return; 
        receiver = rcvr; 
        port = p;
        msgString = msgText; 
        Thread th = new Thread(this); 
        th.start(); 
    }
 
    public void run() { 
        String address = "sms://" + receiver + ":" + port;
        sending = true; 
        MessageConnection conn = null; 
        try { 
            /** Open the message connection. */ 
            conn = (MessageConnection) Connector.open(address); 
            TextMessage txtmessage = (TextMessage) conn.newMessage( 
            MessageConnection.TEXT_MESSAGE); 
            txtmessage.setAddress(address); 
            txtmessage.setPayloadText(msgString); 
            conn.send(txtmessage); 
        } catch (Throwable t) { 
            System.out.println("Send caught: "); 
            t.printStackTrace(); 
        }
 
        if (conn != null) { 
            try { 
                conn.close(); 
            } catch (IOException ioe) { 
                System.out.println("Closing connection caught: "); 
                ioe.printStackTrace(); 
            } 
        }
        sending = false; 
    } 
} 
