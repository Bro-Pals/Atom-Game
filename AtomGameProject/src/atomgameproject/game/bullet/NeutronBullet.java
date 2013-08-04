/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package atomgameproject.game.bullet;

import atomgameproject.game.GameAtom;
import atomgameproject.world.GameWorld;
import atomgameproject.world.WorldMember;
import atomgameproject.world.components.AtomComponent;
import java.awt.Color;

/**
 *
 * @author Jonathon
 */
public class NeutronBullet extends BulletType {
    
    @Override
    public void invokeBulletAbility(WorldMember affectee) {
        if (affectee!=null) {
            if (affectee.hasComponent(AtomComponent.class) && getUsed()==false) {
                setUsed(true);
                if (affectee instanceof GameAtom) {
                    ((GameAtom)affectee).addNeutron();
                }
                parent.setShooter(null);
                parent.remove();
            }
        } else if (GameWorld.getWorld().getWorldRectangle().contains(parent)==false || GameWorld.getWorld().getWorldRectangle().intersects(parent)==false) {
            parent.setShooter(null);
            parent.remove();
        }
    }
    
    @Override
    protected Color initBulletColor() {
        return Color.blue;
    }   
    
}
