/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package atomgameproject.states;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import simplegames.animation.GameDrawingPanel;
import simplegames.states.GameState;

/**
 *
 * @author Jonathon
 */
public class GameOverState extends GameState {

    @Override
    public void init(GameDrawingPanel pnl) {
    }

    @Override
    public void registerListeners(GameDrawingPanel pnl) {
    }

    @Override
    public BufferedImage drawToImage(BufferedImage bi, Graphics2D gd) {
        gd = (Graphics2D) bi.getGraphics();
        
        
        return bi;
    }

    @Override
    public void updateState(float f) {
    }
    
}
