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
import java.awt.Point;

/**
 *
 * @author Jonathon
 */
public class AntiNeutronBullet extends BulletType {

    @Override
    public void invokeBulletAbility(WorldMember affectee) {
        if (affectee!=null) {
            if (affectee.hasComponent(AtomComponent.class) && getUsed()==false) {
                setUsed(true);
                if (affectee instanceof GameAtom) {
                    ((GameAtom)affectee).removeNeutron();
                }
                /// make bang sprite
                GameWorld.getWorld().createSprite(new Point((int)parent.getX(), (int)parent.getY()), "img/sprites/collideRed.png", 10);
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
        return Color.red;
    }   
    
}
