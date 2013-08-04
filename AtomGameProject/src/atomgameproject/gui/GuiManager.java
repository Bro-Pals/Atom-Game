/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package atomgameproject.gui;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import simplegames.behaviors.Renderable;
import simplegames.behaviors.Updatable;

/**
 *
 * @author Jonathon
 */
public class GuiManager implements Updatable, Renderable {

    private ArrayList<GuiElement> elements;
    
    public GuiManager() {
        elements = new ArrayList<>();
    }
    
    public void addElement(GuiElement element) {
        elements.add(element);
    }
    
    public void removeElement(GuiElement element) {
        elements.remove(element);
    }
    
    @Override
    public void update(float f) {
        for (int i=0; i<elements.size(); i++) {
            elements.get(i).update(f);
        }
    }

    @Override
    public BufferedImage draw(BufferedImage bi, Graphics2D gd) {
        gd = (Graphics2D) bi.getGraphics();
        for (int i=0; i<elements.size(); i++) {
            bi = elements.get(i).draw(bi, gd);
        }
        return bi;
    }
    
}
