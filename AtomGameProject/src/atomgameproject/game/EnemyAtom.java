/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package atomgameproject.game;

import static atomgameproject.game.EnemyBehavior.CIRCLE;
import static atomgameproject.game.EnemyBehavior.SLOWSHOOT;
import atomgameproject.game.bullet.AntiNeutronBullet;
import atomgameproject.game.bullet.BulletType;
import atomgameproject.game.bullet.GameBullet;
import atomgameproject.game.bullet.NeutronBullet;
import atomgameproject.world.GameWorld;
import atomgameproject.world.WorldMember;
import atomgameproject.world.components.AtomComponent;
import atomgameproject.world.components.SimplePhysicsComponent;
import atomgameproject.world.components.Stability;
import atomgameproject.world.components.StabilityTable;
import java.awt.Point;
import simplegames.behaviors.Updatable;

/**
 *
 * @author Owner
 */
public class EnemyAtom extends GameAtom implements Updatable {
    
    private Stability stability;
    private AtomComponent ac;
    private SimplePhysicsComponent spc = (SimplePhysicsComponent) getPhysics();
    private Point[] goalPoints;
    private int currentGoal = 0;
    private int shootCoolDown = 0;
    private int shootSpeed = 1000;
    private EnemyBehavior behavior;
    private EnemyBehavior[] allBehaviors;
    private int bulletSpeed = 3;
    private int spread = 20;
    private Point hangOutPoint;
    private int radius;
    
     public EnemyAtom(int x, int y, int width, int height, int p, int n) {
        super(x, y, width, height);
        ac = super.getAtomComponent();
        stability = getStability(this);
        ac.setParent(this);
     //   System.out.println("Made something wtih protons "+p);
        ac.setProtons(p);
        ac.setNeutrons(n);
        ac.setElectrons(p); // protons = electrons
        /// randomly chooose an initial behavior
        allBehaviors = new EnemyBehavior[]{EnemyBehavior.CIRCLE, EnemyBehavior.SLOWSHOOT};
        behavior = allBehaviors[(int)(Math.random()*(allBehaviors.length))];
        switch(behavior) {
            case CIRCLE:
                radius = 250+(int)(Math.random()*300);
                break;
            case SLOWSHOOT:
                radius = 120+(int)Math.random()*130;
                break;
        }
        
        int distanceAway = 200+((int)Math.random()*200);
        PlayerAtom pl = GameWorld.getWorld().getPlayer();
        Point[] allRegions = new Point[]{new Point((int)pl.getX()+distanceAway,(int)pl.getY()+distanceAway),
            new Point((int)pl.getX()-distanceAway,(int)pl.getY()+distanceAway),
            new Point((int)pl.getX()+distanceAway,(int)pl.getY()-distanceAway),
            new Point((int)pl.getX()-distanceAway,(int)pl.getY()-distanceAway)
        };
        hangOutPoint = allRegions[(int)(Math.random()*(allRegions.length))];
        
        //System.out.println(getComponents().size()+" <--Number of compoentnets");
     }
     
     
    @Override
     public void remove() {
        GameWorld.getWorld().removeMember(this);
        getAtomComponent().setParent(null);
        getPhysics().setParent(null);
        getPhysics().setWorld(null);
        stability = null;
        goalPoints = null;
        hangOutPoint = null;
        behavior = null;
        allBehaviors = null;
        ac = null;
        spc = null;           
    }
     
      public void updateCirclePlayer(EnemyAtom en, int radius) {
         PlayerAtom p = GameWorld.getWorld().getPlayer();
         int targetX = (int)(p.getX()-(spread/2)+(Math.random()*spread));
         int targetY = (int)(p.getY()-(spread/2)+(Math.random()*spread));
         en.getPhysics().setBothSpeed(3.5f);
         spread = 8;
         bulletSpeed = 3;
         shootSpeed = 210;
         shootAt(targetX, targetY);
         en.setGoalPoints(new Point[]{
             new Point((int)p.getX(),(int)p.getY()+radius),
             new Point((int)p.getX()+radius,(int)p.getY()),
             new Point((int)p.getX(),(int)p.getY()-radius),
             new Point((int)p.getX()-radius,(int)p.getY())});
     }
     
     public void updateStable() {
         currentGoal = 0;
         PlayerAtom p = GameWorld.getWorld().getPlayer();
         float diffx = (float)(this.getX()-p.getX());
         float diffy = (float)(this.getY()-p.getY());
         this.getPhysics().setBothSpeed(2);
         this.setGoalPoints(new Point[]{new Point((int)(diffx*5)+100, (int)(diffy*5)+100)});
         if (GameWorld.getWorld().getWorldRectangle().contains(this)==false) {
             this.remove();
         }
     }
      
     public void slowShootingAtPlayer(EnemyAtom en, int radius) {
         PlayerAtom p = GameWorld.getWorld().getPlayer();
         int targetX = (int)(p.getX()-(spread/2)+(Math.random()*spread));
         int targetY = (int)(p.getY()-(spread/2)+(Math.random()*spread));
         en.getPhysics().setBothSpeed(2);
         spread = 3;
         bulletSpeed = 5;
         shootSpeed = 110;
         shootAt(targetX, targetY);
         en.setGoalPoints(new Point[]{
             new Point((int)hangOutPoint.getX(),(int)hangOutPoint.getY()+radius),
             new Point((int)hangOutPoint.getX()+radius,(int)hangOutPoint.getY()),
             new Point((int)hangOutPoint.getX(),(int)hangOutPoint.getY()-radius),
             new Point((int)hangOutPoint.getX()-radius,(int)hangOutPoint.getY())});
     }
      
     public void shootAt(int targetX, int targetY) {
         if (getShootCoolDown()<0) {
      //     System.out.println("Shot a bullet!");
            setShootCoolDown(shootSpeed);
            BulletType bulletType = null;
            if (stability==Stability.UNDER) {
                bulletType = new NeutronBullet();
            }
            if (stability==Stability.ABOVE) {
                bulletType = new AntiNeutronBullet();
            }
            if (bulletType!=null && bulletType instanceof BulletType) {
                GameBullet go = new GameBullet(this, targetX,targetY, bulletSpeed, bulletType);
            }
        }
     }
     
     public boolean headTowards(Point p) {
         int x = p.x;
         int y = p.y;
         double diffX =  x-getX();
         double diffY =  y-getY();
         double diffDistance = Math.sqrt((diffX*diffX)+(diffY*diffY));
         double reduce = spc.getxSpeed()/diffDistance;
         spc.setxVel((float)(reduce*diffX));
         spc.setyVel((float)(reduce*diffY));
         //System.out.println("diffX:"+diffX+" diffY:"+diffY);
        // System.out.println("diffX:"+diffX+" diffY:"+diffY);
        // System.out.println("xVel:"+spc.getxVel()+" yVel:"+spc.getyVel());
         return (diffDistance<30);
     }
     
     int goalX, goalY = 800;
     
     @Override
     public void update(float f) {
         stability = getStability(this);
         spc.updateComponent(f);
         ac.updateComponent(f);
         shootCoolDown--;
         if (StabilityTable.getTable().checkStability(this.getAtomComponent())==Stability.STABLE) {
             behavior = EnemyBehavior.STABLE;
         } else {
             if (behavior==EnemyBehavior.STABLE) {
                 behavior = allBehaviors[(int)(Math.random()*(allBehaviors.length))];
        switch(behavior) {
            case CIRCLE:
                radius = 250+(int)(Math.random()*300);
                break;
            case SLOWSHOOT:
                radius = 120+(int)Math.random()*130;
                break;
        }
             }
         }
         switch(behavior) {
             case CIRCLE:
                 updateCirclePlayer(this, getRadius());
                 break;
             case SLOWSHOOT:
                 slowShootingAtPlayer(this, getRadius());
                 break;
             case STABLE:
                 updateStable();
             break;
             default:
                 behavior = allBehaviors[(int)(Math.random()*(allBehaviors.length-1))];
         }

         if (goalPoints!=null) {
            if (goalPoints.length > 0) {
                if (currentGoal>=goalPoints.length) {
                            currentGoal = 0;
                     }
               if (headTowards(goalPoints[currentGoal]) || spc.collidesWith(GameWorld.getWorld().getEverything(), f)!=null) { /// if reached the goal
                    currentGoal++;
                    //System.out.println("Moved to the next goal position");
                    if (StabilityTable.getTable().checkStability(this.getAtomComponent())==Stability.STABLE) {
                        if (GameWorld.getWorld().getWorldRectangle().contains(this)==false) {
                          //  System.out.println("Removed this thing");
                            GameWorld.getWorld().getEnemySpawner().removeEnemyCount();
                          //  System.out.println("After removing... Current count: "+GameWorld.getWorld().getEnemySpawner().getEnemyCount());
                            this.remove();
                        }
                    }
                }
            }
         }
     }
     
      public int getShootCoolDown() {
        return shootCoolDown;
    }

    public void setShootCoolDown(int shootCoolDown) {
        this.shootCoolDown = shootCoolDown;
    }
    
    public void setGoalPoints(Point[] ps) {
        goalPoints = ps;
    }
    
    public int getRadius() {
        return this.radius;
    }
}
