/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package atomgameproject.launcher;

import atomgameproject.ImageLoader;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import simplegames.animation.GameDrawingPanel;
import simplegames.states.GameState;

/**
 *
 * @author Jonathon
 */
public class LaunchBannerState extends GameState {

    private BufferedImage banner;
    
    @Override
    public void init(GameDrawingPanel pnl) {
        banner = ImageLoader.loadImage("img/banner.png");
    }

    @Override
    public void registerListeners(GameDrawingPanel pnl) {
    }

    @Override
    public BufferedImage drawToImage(BufferedImage bi, Graphics2D gd) {
        gd = (Graphics2D) bi.getGraphics();
        gd.drawImage(banner, 0, 0, null);
        return bi;
    }

    @Override
    public void updateState(float f) {
    } 
}
