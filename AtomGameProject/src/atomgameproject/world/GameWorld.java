/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package atomgameproject.world;

import atomgameproject.AtomGameMain;
import atomgameproject.game.EnemyAtom;
import atomgameproject.game.PlayerAtom;
import atomgameproject.game.bullet.GameBullet;
import atomgameproject.world.components.StabilityTable;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import simplegames.behaviors.Renderable;
import simplegames.behaviors.Updatable;

/**
 *
 * @author Jonathon
 */
public class GameWorld implements Updatable {
    
    private static GameWorld world = new GameWorld();
    private Camera camera;
    private ArrayList<WorldMember> members;
    private int boundryX, boundryY;
    private PlayerAtom player;
    private EnemySpawner spawner;
    
    public GameWorld() {
        members = new ArrayList<>();
    }
    
    public void addCamera(int sizeX, int sizeY) {
        camera = new Camera(sizeX, sizeY);
    }
    
    public Camera getCamera() {
        return camera;
    }
    
    public PlayerAtom getPlayer() {
        return player;
    }
    
    public void setPlayer(PlayerAtom p) {
        player = p;
    }
    
    public void addMember(WorldMember member) {
        members.add(member);
    }
    
    public void removeMember(WorldMember member) {
        members.remove(member);
    }
    
    public ArrayList<WorldMember> getEverything() {
        return members;
    }
    
    public static GameWorld getWorld() {
        return world;
    }
    
    public Rectangle getWorldRectangle() {
        return new Rectangle(0,0, AtomGameMain.worldSizeX, AtomGameMain.worldSizeY);
    }
    
    public int getBoundryX() {
        return boundryX;
    }

    public void setBoundryX(int boundryX) {
        this.boundryX = boundryX;
    }

    public int getBoundryY() {
        return boundryY;
    }

    public void setBoundryY(int boundryY) {
        this.boundryY = boundryY;
    }

    @Override
    public void update(float timeDiff) {
        for (int i=0; i<getEverything().size(); i++) {
            WorldMember current = getEverything().get(i);
            current.update(timeDiff);
        }
        spawner.update(timeDiff);
    //    recountBulletsInWorld();
    }
    
    public void recountBulletsInWorld() {
        int bulletCount = 0;
        ArrayList<WorldMember> tbl = GameWorld.getWorld().getEverything();
        for (int i=0; i<tbl.size(); i++) {
            if (tbl.get(i) instanceof GameBullet) {
                bulletCount++;
            }
        } // end for 
        System.out.println("We have " + bulletCount + " bullets");
    }
    
    public void makeSpawner() {
        spawner = new EnemySpawner();
    }
    
    public EnemySpawner getEnemySpawner() {
        if (spawner!=null) {
             return spawner;
        }
        return null;
    }
}
