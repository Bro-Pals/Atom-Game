/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package atomgameproject.states;

import atomgameproject.AtomGameMain;
import atomgameproject.launcher.config.KeyBindWrapper;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import simplegames.animation.GameDrawingPanel;
import simplegames.inputlisteners.KeyDownListener;
import simplegames.inputlisteners.MouseDownListener;
import simplegames.states.GameState;

/**
 *
 * @author Jonathon
 */
public class GameOverState extends GameState implements KeyDownListener, MouseDownListener {

    private int shotCount, minutesSurvived, secondsSurvived;
    private GameDrawingPanel panel;
    private KeyBindWrapper w;
    
    public GameOverState(int shotCount, int minutesSurvived, int secondsSurvived, KeyBindWrapper w) {
        this.shotCount = shotCount;
        this.minutesSurvived = minutesSurvived;
        this.secondsSurvived = secondsSurvived;
        this.w = w;
    }
    
    @Override
    public void init(GameDrawingPanel pnl) {
        this.panel = pnl;
    }

    @Override
    public void registerListeners(GameDrawingPanel pnl) {
        panel.addKeyDownListener(this);
        panel.addMouseDownListener(this);
    }

    @Override
    public BufferedImage drawToImage(BufferedImage bi, Graphics2D gd) {
        gd = (Graphics2D) bi.getGraphics();
        gd.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
        gd.setColor(Color.RED);
        gd.drawString("Game over!", (AtomGameMain.frameWidth/2)-130, (AtomGameMain.frameHeight/2)-40);
        gd.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 35));
        gd.drawString("Survived for " + minutesSurvived + ":" + secondsSurvived + "", (AtomGameMain.frameWidth/2)-220, (AtomGameMain.frameHeight/2)+20);
        gd.drawString("Shot " + shotCount + " enemies", (AtomGameMain.frameWidth/2)-220, (AtomGameMain.frameHeight/2)+75);
        return bi;
    }

    @Override
    public void updateState(float f) {
        
    }

    @Override
    public void reactToKeyDown(KeyEvent ke) {
        panel.changeState(new StartState(w));
    }

    @Override
    public void reactToMouseDown(int i, int i1, MouseEvent me) {
        panel.changeState(new StartState(w));
    }
    
}
