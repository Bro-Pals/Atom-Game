/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package atomgameproject.gui;

import atomgameproject.AtomGameMain;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author Jonathon
 */
public class GuiClock extends GuiElement {

    protected int secondsPassed, minutesPassed, ticks;
    
    public GuiClock() {
        super(AtomGameMain.frameWidth-50, 100, 0, 0);
        ticks = 0;
        secondsPassed = 0;
        minutesPassed = 0;
    }
    
    @Override
    public BufferedImage draw(BufferedImage bi, Graphics2D gd) {
        gd = (Graphics2D) bi.getGraphics();
        gd.setColor(Color.black);
        gd.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        gd.drawString("Time alive " + minutesPassed + ":" + secondsPassed + "", getX()-94, getY());
        return bi;
    }

    @Override
    public void update(float f) {
        ticks = (int)ticks + (int)f;
        if (ticks>1000) {
            ticks = 0;
            secondsPassed = secondsPassed + 1;
            if (secondsPassed>60) {
                secondsPassed = 0;
                minutesPassed = minutesPassed + 1;
            }
        }
    }

    public int getSecondsPassed() {
        return secondsPassed;
    }

    public int getMinutesPassed() {
        return minutesPassed;
    }
    
}
