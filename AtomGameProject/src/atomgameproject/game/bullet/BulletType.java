/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package atomgameproject.game.bullet;

import atomgameproject.world.WorldMember;
import java.awt.Color;

/**
 *
 * @author Jonathon
 */
public abstract class BulletType {
    
    protected GameBullet parent;
    private Color bulletColor;
    private boolean used;
    
    public abstract void invokeBulletAbility(WorldMember affectee);
    protected abstract Color initBulletColor();
    
    public void setParent(GameBullet parent) {
        this.parent = parent;
        bulletColor = initBulletColor();
    }

    public Color getBulletColor() {
        return bulletColor;
    }
    
    public void setUsed(boolean u) {
        used = u;
    }
    
    public boolean getUsed() {
        return used;
    } 
}
