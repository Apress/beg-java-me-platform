/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.apress.rischpater.multimediamidlet;

import java.io.*; 
import javax.microedition.lcdui.*; 
import javax.microedition.m2g.*;


/**
 *
 * @author kf6gpe
 */
public class SVGImageItem extends CustomItem  {
    private ScalableGraphics sg = ScalableGraphics.createInstance();
    private SVGImage image;
    private int prefWidth, prefHeight;

    public SVGImageItem( String l, InputStream in, ExternalResourceHandler h ) {
        super(l);
        if (in != null) {
            try {
                image = (SVGImage)ScalableImage.createImage(in, h);
            } catch( IOException e) { e.printStackTrace(); };
        } else {
            image = (SVGImage)SVGImage.createEmptyImage(h);
        }
        SVGAnimator animator = SVGAnimator.createAnimator(image);
        Canvas svgCanvas = (Canvas)animator.getTargetComponent();
        prefWidth = svgCanvas.getWidth();
        prefHeight = svgCanvas.getHeight();
        image.setViewportWidth(prefWidth);
        image.setViewportHeight(prefHeight);
        animator.play();

    }

    protected int getPrefContentHeight(int w) {
        return getMinContentHeight();
    }

    protected int getPrefContentWidth(int h) {
        return getMinContentWidth();
    }

    protected int getMinContentHeight() {
        return prefHeight;
    }

    protected int getMinContentWidth() {
        return prefWidth;
    }

    public void paint(Graphics g, int w, int h) {
        g.setColor(255, 255, 255);
        g.fillRect(0, 0, w, h);
        sg.bindTarget(g);
        image.setViewportWidth(w);
        image.setViewportHeight(h);
        sg.render(0, 0, image);
        sg.releaseTarget();
    }
}
