/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package atomgameproject.game.bullet;

import atomgameproject.behaviors.ReactsToCollide;
import atomgameproject.game.EnemyAtom;
import atomgameproject.game.GameEntity;
import atomgameproject.game.PlayerAtom;
import atomgameproject.world.GameWorld;
import atomgameproject.world.WorldMember;
import atomgameproject.world.components.BulletPhysicsComponent;
import atomgameproject.world.components.SimplePhysicsComponent;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import simplegames.matrix.Vector2D;

/**
 *
 * @author Jonathon
 */
public class GameBullet extends WorldMember implements ReactsToCollide {
    
    private WorldMember shooter;
    private BulletPhysicsComponent bpc;
    private BulletType type;
    
    public GameBullet(WorldMember shooter, int xTarget, int yTarget, int bulletSpeed, BulletType bullet) {
        super();
        setSize(20, 20);
        setLocation((int)shooter.x+(int)(shooter.getWidth()/2), (int)shooter.y+(int)(shooter.getHeight()/2));
        bpc = new BulletPhysicsComponent(this);
        bpc.setTargetX(xTarget);
        bpc.setTargetY(yTarget);
        bpc.setOrginX((float)(shooter.getX()+(shooter.getWidth()/2)));
        bpc.setOrginY((float)(shooter.getY()+(shooter.getHeight()/2)));
        bpc.setSpeed(bulletSpeed);
        bpc.setLocation(new Vector2D((float)shooter.getX(), (float)shooter.getY()));
        addComponent(bpc);
        this.shooter = shooter;
        this.type = bullet;
        type.setParent(this);
        bpc.calculate();
       // System.out.println("Shooting bullet that wants to go to " + xTarget + ", " + yTarget + "");
    }
    
    @Override
     public void remove() {
        GameWorld.getWorld().removeMember(this);
        if (bpc!=null) {
            bpc.setParent(null);
            bpc.setWorld(null);
            bpc.setLocation(null);
        }
        bpc = null;
        shooter = null; 
        if (type!=null) {
            type.setParent(null);
            type = null;
        }
    }
    
    public BufferedImage drawTest(BufferedImage bImage, Graphics2D graphics2d) {
        graphics2d = (Graphics2D) bImage.getGraphics();
        graphics2d.drawLine((int)bpc.getOrginX()-(int)GameWorld.getWorld().getCamera().getX(), (int)bpc.getOrginY()-(int)GameWorld.getWorld().getCamera().getY(), (int)bpc.getTargetX()-(int)GameWorld.getWorld().getCamera().getX(), (int)bpc.getTargetY()-(int)GameWorld.getWorld().getCamera().getY());
        return bImage;
    }
    
    @Override
    public double getX() {
        return (double)bpc.getX();
    }
    
    @Override
    public double getY() {
        return (double)bpc.getY();
    }
    
    @Override
    public void update(float timeDiff) {
        for (int i=0; i<getComponents().size(); i++) {
            if (getComponents().get(i)!=null) {
                getComponents().get(i).updateComponent(timeDiff);
            }
        }
        if (GameWorld.getWorld().getWorldRectangle().contains(this)==false) {
            this.remove();
        }
    }
    
    @Override
    public void reactToCollide(WorldMember hit) {
        if (hit!=null && !hit.equals(shooter) && !hit.equals(this) && !(hit instanceof GameBullet)) {
            if ((shooter instanceof PlayerAtom) && (hit instanceof EnemyAtom)) {
                ((PlayerAtom)shooter).countShot();
            }
            type.invokeBulletAbility(hit);
            this.remove();
        }
    }

    public Color getBulletColor() {
        return type.getBulletColor();
    }

    public BulletType getType() {
        return type;
    }

    public void setShooter(WorldMember shooter) {
        this.shooter = shooter;
    }
}
