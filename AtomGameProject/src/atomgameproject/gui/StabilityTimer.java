/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package atomgameproject.gui;

import atomgameproject.game.PlayerAtom;
import atomgameproject.states.GameOverState;
import atomgameproject.world.components.Stability;
import atomgameproject.world.components.StabilityTable;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import simplegames.animation.GameDrawingPanel;

/**
 *
 * @author Jonathon
 */
public class StabilityTimer extends GuiClock {
    
    private PlayerAtom player;
    private GameDrawingPanel panel;
    private boolean counting;
    
    public StabilityTimer(PlayerAtom player, GameDrawingPanel panel) {
        super();
        this.player = player;
        this.panel = panel;
        secondsPassed = 20;
        counting = false;
        setY(130);
    }
    
    @Override
    public BufferedImage draw(BufferedImage bi, Graphics2D gd) {
        gd = (Graphics2D) bi.getGraphics();
        if (counting) {
            gd.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
            gd.setColor(Color.black);
            gd.drawString("You are unstable! Decay in " + minutesPassed + ":" + secondsPassed + "", getX()-247, getY());
        }
        return bi;
    }
    
    @Override
    public void update(float f) {
        if (counting) {
            ticks = (int)ticks - (int)f;
            if (ticks<0) {
                ticks = 1000;
                secondsPassed = secondsPassed - 1;
                if (secondsPassed<0) {
                    secondsPassed = 60;
                    minutesPassed = minutesPassed - 1;
                    if (minutesPassed<0) {
                        panel.changeState(new GameOverState());
                    }
                }
            }
        }
        if (StabilityTable.getTable().checkStability(player.getAtomComponent())!=Stability.STABLE) {
            counting = true;
        } else {
            counting = false;
            secondsPassed = 20;
        }
    }
}
