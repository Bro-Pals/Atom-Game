/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package atomgameproject.states;

import atomgameproject.AtomGameMain;
import atomgameproject.ImageLoader;
import atomgameproject.launcher.config.KeyBindWrapper;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import simplegames.animation.GameDrawingPanel;
import simplegames.inputlisteners.MouseDownListener;
import simplegames.inputlisteners.MouseMovedListener;
import simplegames.inputlisteners.click.ClickAreaListener;
import simplegames.inputlisteners.click.HoverImageClickArea;
import simplegames.states.GameState;

/**
 *
 * @author Jonathon
 */
public class BugState extends GameState implements MouseDownListener {

    private KeyBindWrapper wrapper;
    private HoverImageClickArea exitButton;
    
    public BugState(KeyBindWrapper wrapper) {
        this.wrapper = wrapper;
    }
    
    @Override
    public void init(GameDrawingPanel pnl) {
        int width = AtomGameMain.frameWidth;
        int height = AtomGameMain.frameHeight;
        exitButton = new HoverImageClickArea((width/2)-(275/2), (height/2)+(height/6), ImageLoader.loadImage("img/buttons/orangeButtonDown.png"), ImageLoader.loadImage("img/buttons/orangeButtonUp.png"));
    }

    @Override
    public void registerListeners(GameDrawingPanel pnl) {
        exitButton.addListener(new BackToStartListener(pnl));
        pnl.addMouseMovedListener(exitButton);
        pnl.addMouseDownListener(this);
    }

    @Override
    public BufferedImage drawToImage(BufferedImage bi, Graphics2D gd) {
        gd = (Graphics2D) bi.createGraphics();
        bi = exitButton.draw(bi, gd);
        gd.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        gd.setColor(Color.BLUE);
        gd.drawString("Sometimes the bullets stop working", (AtomGameMain.frameWidth/2)-220, 50);
        gd.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
        gd.setColor(Color.BLACK);
        gd.drawString("To Main", (int)exitButton.getBox().getX()+50, (int)exitButton.getBox().getY()+50);
        return bi;
    }

    @Override
    public void updateState(float f) {
        
    }

    @Override
    public void reactToMouseDown(int x, int y, MouseEvent me) {
        exitButton.checkSpot(x, y);
    }
    
    class BackToStartListener implements ClickAreaListener {
        private GameDrawingPanel parent;
        public BackToStartListener(GameDrawingPanel parent) {
            this.parent = parent;
        }
        
        @Override
        public void reactToClick() {
            parent.changeState(new StartState(wrapper));
        }
        
    }
    
}
