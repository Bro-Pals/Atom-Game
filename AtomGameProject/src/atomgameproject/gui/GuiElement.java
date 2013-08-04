/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package atomgameproject.gui;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import simplegames.behaviors.Renderable;
import simplegames.behaviors.Updatable;

/**
 *
 * @author Jonathon
 */
public abstract class GuiElement implements Renderable, Updatable {

    private int x, y, width, height;
    
    public GuiElement(int x, int y, int width, int height) {
        this.x = x; this.y = y; this.width = width; this.height = height;
    }
    
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    
}
