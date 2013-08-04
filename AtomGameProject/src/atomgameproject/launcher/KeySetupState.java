/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package atomgameproject.launcher;

import atomgameproject.launcher.config.KeyBindWrapper;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import javax.swing.JDialog;
import javax.swing.JFrame;
import simplegames.animation.GameDrawingPanel;
import simplegames.inputlisteners.KeyDownListener;
import simplegames.states.GameState;

/**
 *
 * @author Jonathon
 */
public class KeySetupState extends GameState implements KeyDownListener {

    private KeyBindWrapper wrapper;
    private KeyBindingDialog dia;
    private String message;
    private int index;
    private JDialog containedFrame;
    private GameDrawingPanel panel;
    
    public KeySetupState(KeyBindWrapper wrapper, String message, int index, JDialog containedFrame, KeyBindingDialog dia) {
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
        pnl.addKeyDownListener(this);
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
    public void reactToKeyDown(KeyEvent ke) {
        switch(index) {
            case 0:
                wrapper.setUpKey(ke.getKeyCode());
                break;
            case 1:
                wrapper.setRightKey(ke.getKeyCode());
                break;
            case 2:
                wrapper.setDownKey(ke.getKeyCode());
                break;
            case 3:
                wrapper.setLeftKey(ke.getKeyCode());
                break;
        }
        containedFrame.dispose();
        dia.refreshDisplay();
    }   
}
