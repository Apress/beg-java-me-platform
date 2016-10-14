/*
 * %W% %E%
 *
 * Copyright (c) 2004 Sun Microsystems, Inc.  All rights reserved.
 * Use is subject to license terms
 */
package example.customitem;

import javax.microedition.lcdui.*;

/**
 *
 * @version 2.0
 */
public class Table
    extends CustomItem
    implements ItemCommandListener {

    private final static Command CMD_EDIT = new Command("Edit", Command.ITEM, 
                                                        1);
    private Display display;
    private int rows = 5;
    private int cols = 3;
    private int dx = 50;
    private int dy = 20;
    private final static int UPPER = 0;
    private final static int IN = 1;
    private final static int LOWER = 2;
    private int location = UPPER;
    private int currentX = 0;
    private int currentY = 0;
    private String[][] data = new String[rows][cols];

    public Table(String title, Display d) {
        super(title);
        display = d;
        setDefaultCommand(CMD_EDIT);
        setItemCommandListener(this);
    }

    protected int getMinContentHeight() {

        return (rows * dy) + 1;
    }

    protected int getMinContentWidth() {

        return (cols * dx) + 1;
    }

    protected int getPrefContentHeight(int width) {

        return (rows * dy) + 1;
    }

    protected int getPrefContentWidth(int height) {

        return (cols * dx) + 1;
    }

    protected void paint(Graphics g, int w, int h) {

        for (int i = 0; i <= rows; i++) {
            g.drawLine(0, i * dy, cols * dx, i * dy);
        }

        for (int i = 0; i <= cols; i++) {
            g.drawLine(i * dx, 0, i * dx, rows * dy);
        }

        int oldColor = g.getColor();
        g.setColor(0x00D0D0D0);
        g.fillRect((currentX * dx) + 1, (currentY * dy) + 1, dx - 1, dy - 1);
        g.setColor(oldColor);

        for (int i = 0; i < rows; i++) {

            for (int j = 0; j < cols; j++) {

                if (data[i][j] != null) {

                    // store clipping properties
                    int oldClipX = g.getClipX();
                    int oldClipY = g.getClipY();
                    int oldClipWidth = g.getClipWidth();
                    int oldClipHeight = g.getClipHeight();
                    g.setClip((j * dx) + 1, i * dy, dx - 1, dy - 1);
                    g.drawString(data[i][j], (j * dx) + 2, ((i + 1) * dy) - 2, 
                                 Graphics.BOTTOM | Graphics.LEFT);

                    // restore clipping properties
                    g.setClip(oldClipX, oldClipY, oldClipWidth, oldClipHeight);
                }
            }
        }
    }

    protected boolean traverse(int dir, int viewportWidth, int viewportHeight, 
                               int[] visRect_inout) {

        switch (dir) {

            case Canvas.DOWN:

                if (location == UPPER) {
                    location = IN;
                } else {

                    if (currentY < (rows - 1)) {
                        currentY++;
                        repaint(currentX * dx, (currentY - 1) * dy, dx, dy);
                        repaint(currentX * dx, currentY * dy, dx, dy);
                    } else {
                        location = LOWER;

                        return false;
                    }
                }

                break;

            case Canvas.UP:

                if (location == LOWER) {
                    location = IN;
                } else {

                    if (currentY > 0) {
                        currentY--;
                        repaint(currentX * dx, (currentY + 1) * dy, dx, dy);
                        repaint(currentX * dx, currentY * dy, dx, dy);
                    } else {
                        location = UPPER;

                        return false;
                    }
                }

                break;

            case Canvas.LEFT:

                if (currentX > 0) {
                    currentX--;
                    repaint((currentX + 1) * dx, currentY * dy, dx, dy);
                    repaint(currentX * dx, currentY * dy, dx, dy);
                }

                break;

            case Canvas.RIGHT:

                if (currentX < (cols - 1)) {
                    currentX++;
                    repaint((currentX - 1) * dx, currentY * dy, dx, dy);
                    repaint(currentX * dx, currentY * dy, dx, dy);
                }
        }

        visRect_inout[0] = currentX;
        visRect_inout[1] = currentY;
        visRect_inout[2] = dx;
        visRect_inout[3] = dy;

        return true;
    }

    public void setText(String text) {
        data[currentY][currentX] = text;
        repaint(currentY * dx, currentX * dy, dx, dy);
    }

    public void commandAction(Command c, Item i) {

        if (c == CMD_EDIT) {

            TextInput textInput = new TextInput(data[currentY][currentX], this, 
                                                display);
            display.setCurrent(textInput);
        }
    }
}
