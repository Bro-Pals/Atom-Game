/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package atomgameproject.states;

import atomgameproject.AtomGameMain;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import simplegames.animation.GameDrawingPanel;
import simplegames.states.GameState;

/**
 *
 * @author Jonathon
 */
public class BugState extends GameState {

    @Override
    public void init(GameDrawingPanel pnl) {
    }

    @Override
    public void registerListeners(GameDrawingPanel pnl) {
    }

    @Override
    public BufferedImage drawToImage(BufferedImage bi, Graphics2D gd) {
        gd = (Graphics2D) bi.createGraphics();
        gd.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        gd.setColor(Color.BLUE);
        gd.drawString("Sometimes the bullets stop working", (AtomGameMain.frameWidth/2)-220, 50);
        return bi;
    }

    @Override
    public void updateState(float f) {
    }
    
}
