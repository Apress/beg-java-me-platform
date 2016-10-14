/*
 * SpriteCanvas.java
 *
 * Created on April 8, 2008, 1:19 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.apress.rischpater;

import javax.microedition.lcdui.*;
import javax.microedition.lcdui.game.*;
import java.io.IOException;
import java.util.Random;

/**
 *
 * @author kf6gpe
 */
public class SpriteCanvas 
    extends GameCanvas 
    implements Runnable {
    
    private final int DELAYMS=75;
    private boolean paused;
    
    private Random random;
    private Display display;
    
    private LayerManager layers;
    private TiledLayer board;
    private Sprite[] butterfly;
    private Sprite cat;

    int[] map = {
        1, 1, 1, 1, 1, 1, 1, 1, 
        1, 1, 2, 1, 1, 1, 1, 1,
        1, 1, 1, 1, 3, 1, 1, 1,
        1, 2, 1, 1, 1, 1, 1, 1,
        1, 1, 1, 1, 1, 1, 2, 1,
        1, 1, 1, 3, 1, 1, 1, 1,
        1, 1, 1, 1, 1, 3, 1, 1,
        1, 1, 1, 1, 1, 1, 1, 1 };
    private static final int[] flightSequence = { 
        0, 1, 2, 2, 1, 0 };
    private final int tileWidth = 16, tileHeight = 16;
    private final int boardWidth = 8, boardHeight = 8;
    
    /** Creates a new instance of SpriteCanvas */
    public SpriteCanvas(Display d) 
        throws IOException {
        super(true);
        
        display = d;
        random = new Random();
        
        layers = new LayerManager();
        layers.setViewWindow(-(getWidth()-128)/2, -(getHeight()-128)/2, 256, 256);
        
        createButterflies("/res/butterfly-sprite.png");
        createCat("/res/cat.png");
        createBoard("/res/ground-tiles.png"); 
    }
    
    private void createBoard( String imageName )
        throws IOException {
        Image boardImage = Image.createImage(imageName);

        board = new TiledLayer( boardWidth, boardHeight, 
                    boardImage, tileWidth, tileHeight);

        for( int i = 0; i < map.length; i++ ) {
            int x = i % 8;
            int y = i / 8;
            board.setCell(x, y, map[i]);
        }
        layers.append(board);
    }
    
    private void createButterflies( String imageName )
        throws IOException {
        Image image = Image.createImage(imageName);
        int x, y;
        int i;
        butterfly = new Sprite[2];
        
        for ( i=0; i<butterfly.length; i++ ) {
            x = random.nextInt(8)*tileWidth;
            y = random.nextInt(8)*tileHeight;
            butterfly[i] = new Sprite(image, tileWidth, tileHeight);
            butterfly[i].setPosition(x,y);
            butterfly[i].defineReferencePixel(0,0);
            butterfly[i].setTransform(Sprite.TRANS_NONE);
            butterfly[i].setFrameSequence(flightSequence);
            butterfly[i].setFrame(i % 3);
            layers.append(butterfly[i]);
        }
    }
    
    private void createCat( String imageName )
        throws IOException {
        Image image = Image.createImage(imageName);
        int x, y;
        int i;
        
        x = random.nextInt(8)*tileWidth;
        y = random.nextInt(8)*tileHeight;
        cat = new Sprite(image, tileWidth, tileHeight);
        cat.setPosition(x,y);
        cat.defineReferencePixel(0,0);
        cat.setTransform(Sprite.TRANS_NONE);
        cat.setFrame(0);
        layers.append(cat);
    }    
    
    private void moveCat() {
        int keyStates = getKeyStates();
        int x, y;
        x = cat.getX();
        y = cat.getY();
        if ((keyStates & LEFT_PRESSED) != 0) {
            x -= cat.getWidth()/4;
        }
        if ((keyStates & RIGHT_PRESSED) != 0) {
            x += cat.getWidth()/4;
        } 
        if ((keyStates & UP_PRESSED) != 0) {
            y -= cat.getHeight()/4;
        }
        if ((keyStates & DOWN_PRESSED) != 0) {
            y += cat.getHeight()/4;
        }
        if ( x < 0 ) x = 0;
        if ( y < 0 ) y = 0;
        if ( x > board.getWidth()-cat.getWidth() ) 
            x = board.getWidth()-cat.getWidth();
        if ( y > board.getHeight()-cat.getHeight() ) 
            y = board.getHeight()-cat.getHeight();
        cat.setPosition(x,y);
    }
    
    private void moveButterflies() {
        int dir;
        int i;
        int x, y, width, height;
        for ( i=0; i<butterfly.length; i++ ) {
            dir = random.nextInt(9) + 1;
            x = butterfly[i].getX();
            y = butterfly[i].getY();
            width = butterfly[i].getWidth();
            height = butterfly[i].getHeight();
            switch(dir)
            {
            /*  7 8 9
                4 5 6
                1 2 3   */
                case 1:
                    x -= width/2;
                    y += height/2;
                    break;
                case 2:
                    y += height/2;
                    break;
                case 3:
                    x += width/2;
                    y += height/2;
                    break;
                case 4:
                    x -= width/2;
                    break;
                case 5:
                    break;
                case 6:
                    x += width/2;
                    break;
                case 7:
                    x -= width/2;
                    y -= height/2;
                    break;
                case 8:
                    y -= height/2;
                    break;
                case 9:
                    x += width/2;
                    y -= height/2;
                    break;
            }
            // Clip coordinates
            if ( x < 0 ) x = 0;
            if ( y < 0 ) y = 0;
            if ( x > board.getWidth() - width ) 
                x = board.getWidth() - width;
            if ( y > board.getHeight() - height ) 
                y = board.getHeight() - height;
            butterfly[i].setPosition(x,y);
            butterfly[i].nextFrame();
        }
    }
    
    public void start() {
        Thread thread = new Thread(this);
        thread.start();
        paused=false;
    }
    
    private void detectCollisions() {
        int i;
        for (i=0; i<butterfly.length; i++) {
            if (cat.collidesWith(butterfly[i],true)) {
                display.flashBacklight(100);
                display.vibrate(100);
            }
        }
    }
    
    public void run() {
        Graphics g = getGraphics();
        while(true) {
            try {
                synchronized(this) {
                    while (paused) {
                        wait();
                    }
                }

                moveCat();
                moveButterflies();
                detectCollisions();

                layers.paint(g, 0, 0);
                flushGraphics();

                Thread.sleep(DELAYMS);
            }
            catch(InterruptedException ex) {}
        }
    }
    
    public void setPaused(boolean b)
    {
        synchronized(this) {
            paused = b;
            notify();
        }
    }
}
