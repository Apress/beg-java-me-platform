package com.apress.rischpater.MMSMIDlet;

import javax.microedition.io.*; 
import javax.wireless.messaging.*; 
import java.io.*; 
public class MMSSender implements Runnable { 
    private static MMSSender me = new MMSSender(); 
    private MMSSender() { 
    }
 
    public static MMSSender getInstance() { 
        return me; 
    }

    private String receiver = null; 
    private String appId = null;
    private String image = null;
    private String msg = null;
    private String encoding = null;
    private boolean sending = false;

    public void sendMsg(String r, String id, String m, String i) {
        if (sending) return; 
        receiver = r;
        appId = id;
        msg = m;
        image = i;
        encoding = System.getProperty("microedition.encoding");
        Thread t = new Thread(this); 
        t.start(); 
    }
 
    public boolean isSending() { 
        return sending; 
    }
 
    public void run() { 
        sending = true; 
        try { 
            sendMMS(); 
        } catch (Exception e) {
            System.out.println("run caught: ");
            e.printStackTrace();
        }
        sending = false; 
    }
 
    public void sendMMS() { 
        String address = "mms://" + receiver + ":" + appId;
        System.out.println(address);
        MessageConnection c = null;
        try { 
            c = (MessageConnection) Connector.open(address);
            MultipartMessage mpm = (MultipartMessage) c.newMessage(
                MessageConnection.MULTIPART_MESSAGE); 
            mpm.setSubject("MMSMIDlet Image");
            if (image!=null) {
                InputStream is = getClass().getResourceAsStream(image);
                byte[] bImage = new byte[is.available()];
                is.read(bImage);
                mpm.addMessagePart(
                    new MessagePart(bImage, 0, bImage.length,
                        "image/png", "id1", null, null));
            }
            if (msg!=null) {
                byte[] bMsg = msg.getBytes();
                mpm.addMessagePart(
                    new MessagePart(bMsg, 0, bMsg.length,
                        "text/plain", "txt1", null, encoding));
            }
            c.send(mpm);
        } catch (Exception e) { 
            System.out.println("Send caught: "); 
            e.printStackTrace(); 
        } finally { 
            if (c != null) {
                try { 
                    c.close();
                } catch (IOException e) { 
                    System.out.println("Closing connection caught: "); 
                    e.printStackTrace(); 
                } 
            } 
        } 
    } 
} 
