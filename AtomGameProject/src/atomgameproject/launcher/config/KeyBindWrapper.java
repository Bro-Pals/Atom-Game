/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package atomgameproject.launcher.config;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 *
 * @author Jonathon
 */
public class KeyBindWrapper {
    
    private int[] movementBinding;
    private int[] shootBinding;

    public KeyBindWrapper() {
        movementBinding  = new int[4];
        shootBinding = new int[2];
        setUpKey(KeyEvent.VK_W);
        setDownKey(KeyEvent.VK_S);
        setRightKey(KeyEvent.VK_D);
        setLeftKey(KeyEvent.VK_A);
        setBlueBulletMouseButton(MouseEvent.BUTTON1);
        setRedBulletMouseButton(MouseEvent.BUTTON3);
    }
     
    public void setUpKey(int key) {
        movementBinding[0] = key;
    }
    
    public void setDownKey(int key) {
        movementBinding[2] = key;
    }
    
    public void setLeftKey(int key) {
        movementBinding[3] = key;
    }
    
    public void setRightKey(int key) {
        movementBinding[1] = key;
    }
    
    public void setRedBulletMouseButton(int mouse) {
        shootBinding[1] = mouse;
    }
    
    public void setBlueBulletMouseButton(int mouse) {
        shootBinding[0] = mouse;
    }
    
    public int[] getMovementBinding() {
        return movementBinding;
    }

    public int[] getShootBinding() {
        return shootBinding;
    }
}
