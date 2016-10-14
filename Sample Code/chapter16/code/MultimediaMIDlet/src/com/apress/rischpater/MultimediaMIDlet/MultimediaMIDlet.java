package com.apress.rischpater.multimediamidlet;

import java.io.*;
import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;
import javax.microedition.media.*; 
import javax.microedition.media.control.*; 
import javax.microedition.m2g.*;



public class MultimediaMIDlet
    extends MIDlet 
    implements CommandListener, Runnable { 
    private Display display; 
    private List choiceScreen;
    private Form mediaScreen;
    Displayable viewerDisplayable;
    private Item videoItem; 
    private ImageItem capturedImage;
    private VideoControl videoControl; 
    private Command captureCommand;
    private Command selectCommand;
    String mediaName = null;
    String mediaType = null;
    private Player player = null;
    SVGAnimator svgAnimator;
    boolean endNow;
    private static final String SVG_IMAGE_PATH = "/res/image.svg";
    private static final String SVG_IMAGE_TYPE = "image/svg+xml";
    private static final String WAV_SOUND_PATH = "/res/sound.wav";
    private static final String WAV_SOUND_TYPE = "audio/x-wav";
    private static final String MPG_MOVIE_PATH = "/res/movie.mpg";
    private static final String MPG_MOVIE_TYPE = "video/mpeg";
    private static final String CAP_VIDEO_PATH = "capture://video";
    private static final String CAP_VIDEO_TYPE = "video/x-capture";


    public void startApp() {
        showSupportedMedia();
        display = Display.getDisplay(this); 
        if (choiceScreen == null) {
            choiceScreen = new List("MultimediaMIDlet", List.IMPLICIT);
            choiceScreen.addCommand(new Command("Exit", Command.EXIT, 0)); 
            selectCommand = new Command("Play", Command.ITEM,1);
            choiceScreen.setSelectCommand(selectCommand);
            choiceScreen.addCommand(selectCommand); 
            choiceScreen.setCommandListener(this);
            choiceScreen.append("Sound", null);
            choiceScreen.append("Video", null);
            choiceScreen.append("SVG", null);
            choiceScreen.append("Camera Capture",null);
        }
        display.setCurrent(choiceScreen); 
    } 

    private void showSupportedMedia() {
        String[] contentTypes = Manager.getSupportedContentTypes(null);
        for (int i=0; i<contentTypes.length; i++) {
            String protocols[] = Manager.getSupportedProtocols(contentTypes[i]);
            for (int j=0; j<protocols.length; j++) {
                String s = contentTypes[i] + ":" + protocols[j];
                System.out.println(s);
            }
        }
    }

    public void pauseApp() {
        endNow = true;
        if (svgAnimator != null) {
            svgAnimator.stop();
        }
        if (player!=null) {
            player.close();
        }
    }

    public void destroyApp(boolean unconditional) {
        endNow = true;
        if (player != null)  { 
                player.close(); 
        } 
    } 

    public void commandAction(Command c, Displayable s) { 
        if (c.getCommandType() == Command.EXIT) { 
            destroyApp(true); 
            notifyDestroyed(); 
        } else
        if (display.getCurrent() == viewerDisplayable) {
            if (c == captureCommand) {
                synchronized(this) {
                    this.notify();
                }
            }
        } else
        if (display.getCurrent() == choiceScreen ) {
            if (c == selectCommand ) {
                Form waitForm = new Form("Loading..."); 
                display.setCurrent(waitForm); 
                int itemIndex = choiceScreen.getSelectedIndex();
                switch(itemIndex) {
                    case 0:
                        mediaName = WAV_SOUND_PATH;
                        mediaType = WAV_SOUND_TYPE;
                        break;    
                    case 1:
                        mediaName = MPG_MOVIE_PATH;
                        mediaType = MPG_MOVIE_TYPE;
                        break;    
                    case 2:
                        // TODO Set up for SVG playback
                        mediaName = SVG_IMAGE_PATH;
                        mediaType = SVG_IMAGE_TYPE;
                        break;    
                    case 3: 
                        mediaName = CAP_VIDEO_PATH;
                        mediaType = CAP_VIDEO_TYPE;
                        break;
                }

                Thread t = new Thread(this); 
                t.start();                 
            }
        }
    } 

    public void run() { 
        playFromResource();
        while(!endNow) {
            synchronized(this) {
                try {
                    this.wait();
                } catch (Exception e) {}
                if (!endNow) {
                    try {
                        byte[] raw = videoControl.getSnapshot(null);
                        Image image = Image.createImage(raw, 0, raw.length);
                        capturedImage.setImage(image);
                    } catch (MediaException e) {continue;}
                }
            }
        }
    } 

    private void initSvgPlayer()
        throws IOException {
        InputStream in = getClass().getResourceAsStream(mediaName);
        SVGImage svgImage = (SVGImage)ScalableImage.createImage(in, null);
        svgAnimator = SVGAnimator.createAnimator(svgImage);
        Canvas svgCanvas = (Canvas)svgAnimator.getTargetComponent();
        svgImage.setViewportWidth(svgCanvas.getWidth());
        svgImage.setViewportHeight(svgCanvas.getHeight());
        viewerDisplayable = (Displayable)svgCanvas;
        svgAnimator.play();
    }

    private void initMediaPlayer()
        throws IOException, MediaException {
        if (mediaScreen == null) {
            mediaScreen = new Form("MultimediaMIDlet");
            mediaScreen.addCommand(new Command("Exit", Command.EXIT, 0));
            mediaScreen.setCommandListener(this);
        }
        if ( mediaType.equals(CAP_VIDEO_TYPE))
            player = Manager.createPlayer(mediaName);
        else {
            InputStream in = getClass().getResourceAsStream(mediaName);
            player = Manager.createPlayer(in, mediaType);
        }
        player.realize();
        player.prefetch();
        viewerDisplayable = (Displayable)mediaScreen;
    }

    private void configViewSound() {
        // No-op
    }

    private void configViewSvg() {
        // No-op
    }

    private void configViewVideo() {
	    captureCommand = new Command("Capture", Command.ITEM, 0);
        mediaScreen.addCommand(captureCommand);
        videoControl = (VideoControl) player.getControl("VideoControl");
        if (videoControl != null) {
            videoItem = (Item) videoControl.initDisplayMode(
                    VideoControl.USE_GUI_PRIMITIVE, null);
            mediaScreen.append(videoItem);
        }
        capturedImage = new ImageItem(null, null, ImageItem.LAYOUT_DEFAULT, null);
        mediaScreen.append(capturedImage);
    }

    private void playFromResource() { 
        try {
            if ( mediaType.equals("image/svg+xml")) {
                initSvgPlayer();
                configViewSvg();
            }
            else
            {
                initMediaPlayer();
                if (mediaType.startsWith("audio")) {
                    configViewSound();
                }
                if (mediaType.startsWith("video")) {
                    configViewVideo();
                }

                player.start();
            }
            display.setCurrent(viewerDisplayable);
        }  catch (Exception e) {
            e.printStackTrace();
            showException(e);
            return;
        }
    }
    
    private void showException(Exception e) { 
        Alert a = new Alert("Exception", e.toString(), null, null); 
        a.setTimeout(Alert.FOREVER); 
        display.setCurrent(a, viewerDisplayable);
   } 
} 
