/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package atomgameproject.world.components;

import atomgameproject.game.bullet.GameBullet;
import atomgameproject.world.GameWorld;
import atomgameproject.world.WorldMember;
import java.awt.Rectangle;
import java.util.ArrayList;
import simplegames.matrix.Vector2D;

/**
 *
 * @author Jonathon
 */
public class BulletPhysicsComponent implements WorldComponent {
    
    private GameBullet parent;

    public void setParent(GameBullet parent) {
        this.parent = parent;
    }

    public void setWorld(GameWorld world) {
        this.world = world;
    }
    private float xVel, yVel, speed, targetX, targetY, differenceX, differenceY, orginX, orginY;
    private GameWorld world;
    private Vector2D location;
    
    public BulletPhysicsComponent(GameBullet parent) {
        world = GameWorld.getWorld();
        location = new Vector2D((float)parent.x, (float)parent.y);
        xVel = 0;
        yVel = 0;
        orginX = 0;
        orginY = 0;
        targetX = 0;
        targetY = 0;
        this.parent = parent;
        speed = 0;
    }

    @Override
    public void updateComponent(float timeDiff) {
        if (location!=null) {
            location.add(new Vector2D(xVel, yVel));
            WorldMember mem = collidesWith(world.getEverything(), timeDiff);
            if (mem!=null) {
                parent.reactToCollide(mem);
            }
        }
    }
    
    public void calculate() {
        differenceX = ((float)targetX-(orginX));
        differenceY = ((float)targetY-(orginY));
        float diff = (float)(Math.sqrt((differenceX*differenceX)+(differenceY*differenceY)));
        float k = speed/diff; 
        xVel = differenceX*k;
        yVel = differenceY*k;
       // System.out.println("The velocity is " + xVel + ", " + yVel + "");
    }

    public WorldMember collidesWith(ArrayList<WorldMember> members, float timeDiff) {
        WorldMember possibleHit = null;
        for (int i=0; i<members.size(); i++) {
            SimplePhysicsComponent spc = (SimplePhysicsComponent) members.get(i).getComponent(SimplePhysicsComponent.class);
            if (spc!=null) {
                if (this.getCurrentBox().intersects(spc.getPredictedBox(timeDiff)) && members.get(i)!=parent) {
                    possibleHit = members.get(i);
                    return possibleHit;
                }
            }
        }   
        return null;
    }
    
    public Rectangle getCurrentBox() {
        return new Rectangle((int)parent.getX(), (int)parent.getY(), (int)parent.getWidth(), (int)parent.getHeight());
    }
    
    public Rectangle getPredictedBox(float timeDiff) {
        return new Rectangle((int)Math.floor(((getSpeed()*xVel))+parent.getX()), (int)Math.floor(((getSpeed()*yVel))+parent.getY()), (int)parent.getWidth(), (int)parent.getHeight());
    }
    
    public float getxVel() {
        return xVel;
    }

    public void setxVel(float xVel) {
        this.xVel = xVel;
        calculate();
    }

    public float getyVel() {
        return yVel;
    }

    public void setyVel(float yVel) {
        this.yVel = yVel;
        calculate();
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
        calculate();
    }

    public float getTargetX() {
        return targetX;
    }

    public void setTargetX(float targetX) {
        this.targetX = targetX;
    }

    public float getTargetY() {
        return targetY;
    }

    public void setTargetY(float targetY) {
        this.targetY = targetY;
    }
    
    public float getX() {
        return location.getX();
    }
    
    public float getY() {
        return location.getY();
    }

    public void setLocation(Vector2D location) {
        this.location = location;
    }

    public float getOrginX() {
        return orginX;
    }

    public void setOrginX(float orginX) {
        this.orginX = orginX;
    }

    public float getOrginY() {
        return orginY;
    }

    public void setOrginY(float orginY) {
        this.orginY = orginY;
    }
}
