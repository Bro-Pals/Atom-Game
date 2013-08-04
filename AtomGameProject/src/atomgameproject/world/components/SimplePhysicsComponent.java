/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package atomgameproject.world.components;

import atomgameproject.behaviors.ReactsToCollide;
import atomgameproject.world.GameWorld;
import atomgameproject.world.WorldMember;
import java.awt.Rectangle;
import java.util.ArrayList;

/**
 *
 * @author Jonathon
 */
public class SimplePhysicsComponent implements WorldComponent {
    ///Handles velocity and collision
    
    private WorldMember parent;
    private double xVel, yVel;
    private float xSpeed, ySpeed;
    private boolean collidable, stopsOnHit;
    private GameWorld world;
    
    public SimplePhysicsComponent(WorldMember parent) {
        this.parent = parent;
        collidable = true;
        stopsOnHit = true;
        world = GameWorld.getWorld();
        xSpeed = 0;
        ySpeed = 0;
        xVel = 0;
        ySpeed = 0;
    }
    
    public Rectangle getCurrentBox() {
        return parent.getBounds();
    }
    
    public Rectangle getPredictedBox(float timeDiff) {
        return new Rectangle((int)(((xSpeed*xVel))+parent.getX()), (int)(((ySpeed*yVel))+parent.getY()), (int)parent.getWidth(), (int)parent.getHeight());
    }

    @Override
    public void updateComponent(float timeDiff) {
        if (isCollidable()) {
            WorldMember hit = collidesWith(world.getEverything(), timeDiff);
            if (hit!=null) {
                if (parent instanceof ReactsToCollide) {
                    ((ReactsToCollide)parent).reactToCollide(hit);
                }
                if (!isStopsOnHit()) {
                   // System.out.println("Relentless; the object moves");
                    parent.setLocation((int)Math.floor(parent.getX()+((getxVel()*xSpeed))), (int)Math.floor(parent.getY()+((getyVel()*ySpeed))));
                }
            } else {
                 parent.setLocation((int)Math.floor(parent.getX()+((getxVel()*xSpeed))), (int)Math.floor(parent.getY()+((getyVel()*ySpeed))));
            }
        } else {
            parent.setLocation((int)Math.floor(parent.getX()+((getxVel()*xSpeed))), (int)Math.floor(parent.getY()+((getyVel()*ySpeed))));
        }
    }

    public WorldMember collidesWith(ArrayList<WorldMember> members, float timeDiff) {
        WorldMember possibleHit = null;
        for (int i=0; i<members.size(); i++) {
            SimplePhysicsComponent spc = (SimplePhysicsComponent) members.get(i).getComponent(SimplePhysicsComponent.class);
            if (spc!=null) {
                if (this.getPredictedBox(timeDiff).intersects(spc.getPredictedBox(timeDiff)) && members.get(i)!=parent) {
                    possibleHit = members.get(i);
                    return possibleHit;
                }
            }
        }   
        return null;
    }
    
    public boolean isCollidable() {
        return collidable;
    }

    public void setCollidable(boolean collidable) {
        this.collidable = collidable;
    }

    public double getxVel() {
        return xVel;
    }

    public double getyVel() {
        return yVel;
    }
    
    public void addXVel(double amount) {
        this.xVel = xVel + amount;
    }
    
    public void adsdYVel(double amount) {
        this.yVel = yVel + amount;
    }

    public void setxVel(double xVel) {
        this.xVel = xVel;
    }

    public void setyVel(double yVel) {
        this.yVel = yVel;
    }

    public float getxSpeed() {
        return xSpeed;
    }

    public void setxSpeed(float xSpeed) {
        this.xSpeed = xSpeed;
    }

    public float getySpeed() {
        return ySpeed;
    }

    public void setySpeed(float ySpeed) {
        this.ySpeed = ySpeed;
    }
    
    public void setBothSpeed(float speed) {
        this.ySpeed = speed;
        this.xSpeed = speed;
    }

    public boolean isStopsOnHit() {
        return stopsOnHit;
    }

    public void setStopsOnHit(boolean stopsOnHit) {
        this.stopsOnHit = stopsOnHit;
    }

    public void setParent(WorldMember parent) {
        this.parent = parent;
    }

    public void setWorld(GameWorld world) {
        this.world = world;
    }
}
