/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package atomgameproject.launcher;

import atomgameproject.launcher.config.KeyBindWrapper;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.JDialog;
import simplegames.animation.GameDrawingPanel;
import simplegames.inputlisteners.MouseDownListener;
import simplegames.states.GameState;

/**
 *
 * @author Jonathon
 */
public class MouseSetupState extends GameState implements MouseDownListener  {
    private KeyBindWrapper wrapper;
    private KeyBindingDialog dia;
    private String message;
    private int index;
    private JDialog containedFrame;
    private GameDrawingPanel panel;
    
    public MouseSetupState(KeyBindWrapper wrapper, String message, int index, JDialog containedFrame, KeyBindingDialog dia) {
        this.wrapper = wrapper;
        this.message = message;
        this.index = index;
        this.containedFrame = containedFrame;
        this.dia = dia;
    }
    
    @Override
    public void init(GameDrawingPanel pnl) {
        this.panel = pnl;
    }

    @Override
    public void registerListeners(GameDrawingPanel pnl) {
        pnl.addMouseDownListener(this);
    }

    @Override
    public BufferedImage drawToImage(BufferedImage bi, Graphics2D gd) {
        gd = (Graphics2D) bi.getGraphics();
        gd.drawString(message, (int)((containedFrame.getSize().getWidth()/2)-80), (int)((containedFrame.getSize().getHeight()/2)-10));
        return bi;
    }

    @Override
    public void updateState(float f) {
    }

    @Override
    public void reactToMouseDown(int x, int y, MouseEvent ke) {
        switch(index) {
            case 0:
                wrapper.setBlueBulletMouseButton(ke.getButton());
                break;
            case 1:
                wrapper.setRedBulletMouseButton(ke.getButton());
                break;
        }
        containedFrame.dispose();
        dia.refreshDisplay();
    }   
}
