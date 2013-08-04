/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package atomgameproject.world;

import atomgameproject.AtomGameMain;
import atomgameproject.game.EnemyAtom;
import atomgameproject.game.PlayerAtom;
import atomgameproject.world.components.StabilityTable;
import java.awt.Rectangle;
import java.util.ArrayList;
import simplegames.behaviors.Updatable;

/**
 *
 * @author Owner
 */
public class EnemySpawner implements Updatable {
    
    private GameWorld world;
    private PlayerAtom player;
    private int spawnTime;
    public static int spawnSpeed = 300;
    private int enemyCount = 0;
    public static int maxEnemies = 6;
    
    public EnemySpawner() {
        world = GameWorld.getWorld();
        player = world.getPlayer();
    }

    public void addEnemyCount() {
        enemyCount++;
    }
    
    public void removeEnemyCount() {
        enemyCount--;
    }
    
    public void spawnEnemy(int locX, int locY, int size, int protons, int neutrons) {
        if (canSpawn(locX, locY, size)) {
             EnemyAtom ea = new EnemyAtom(locX, locY, size, size, protons, neutrons);
             addEnemyCount();
        } else {
        //    System.out.println("Was unable to spawn there!");
        }
    }
    
    public boolean canSpawn(int locX, int locY, int size) {
        if (GameWorld.getWorld().getWorldRectangle().contains(new Rectangle(locX, locY, size, size))) {
            float diffx = (float)(player.getX()-locX);
            float diffy = (float)(player.getY()-locY);
            float distance = (float)(Math.sqrt((diffy*diffy)+(diffx*diffx)));
            System.out.println(distance+">"+(((AtomGameMain.frameHeight+AtomGameMain.frameWidth)/2)-Math.abs(AtomGameMain.frameHeight-AtomGameMain.frameWidth)));
            if (distance > (((AtomGameMain.frameHeight+AtomGameMain.frameWidth)/2)-Math.abs(AtomGameMain.frameHeight-AtomGameMain.frameWidth)-100) ){
                if (enemyCount<=maxEnemies) {
                   return true;
                } else {
           //         System.out.println("Too many enemies "+enemyCount+"<="+maxEnemies);
                    return false;
                }
            }else{
            ///    System.out.println("That would spawn too close to the player!");
                return false;
            }
        } else {
          //  System.out.println("That would spawn out of the world!");
            return false;
        }
    }
    
    public int getSpawnTime() {
        return spawnTime;
    }
    
    public int getSpawnSpeed() {
        return spawnSpeed;
    }

    public void setSpawnSpeed(int spawnTime) {
        this.spawnSpeed = spawnTime;
    }
    
    
    public void increaseSpawnTime() {
        spawnTime++;
    }
    
    public void resetSpawnTime() {
        spawnTime = 0;
    }
    
    public void recountEnemiesInWorld() {
        enemyCount = 0;
        ArrayList<WorldMember> tbl = GameWorld.getWorld().getEverything();
        for (int i=0; i<tbl.size(); i++) {
            if (tbl.get(i) instanceof EnemyAtom) {
                addEnemyCount();
            }
        } // end for   
    }
    
    @Override
    public void update(float f) {
        Camera camera = GameWorld.getWorld().getCamera();
        recountEnemiesInWorld();
        if (getSpawnTime()>getSpawnSpeed())  {
          //  System.out.println("Attempting to spawn an enemy ");
            int numP = 1+(int)(Math.random()*(StabilityTable.getTable().getTableLength()-1));
            int numN;
            if (((int)Math.random()*10)>5) {
                 numN = (int)(numP-(4+(Math.random()*6)));
            } else {
                numN = (int)(numP+(4+(Math.random()*6)));
            }
            int spawnX, spawnY;
            int distMod = 350;
            switch(((int)Math.random()*3)) {
                case 0:
                    spawnY = (int)camera.getY()-distMod;
                    spawnX = (int)(camera.getX()+(Math.random()*camera.getWidth()));
                    break;
                case 1:
                    spawnY = (int)(camera.getY()+camera.getHeight()+distMod);
                    spawnX = (int)(camera.getX()+(Math.random()*camera.getWidth()));
                    break;
                case 2:
                    spawnX = (int)(camera.getX()+camera.getWidth()+distMod);
                    spawnY = (int)(camera.getY()+(Math.random()*camera.getHeight()));
                    break;
                case 3:
                    spawnX = (int)(camera.getX()-distMod);
                    spawnY = (int)(camera.getY()+(Math.random()*camera.getHeight()));
                    break;
                default:
                    spawnY = (int)camera.getY()-distMod;
                    spawnX = (int)(camera.getX()+(Math.random()*camera.getWidth()));
                    break;
            }
            spawnEnemy(spawnX, spawnY, (int)(35+(Math.random()*50)), numP, numN);
            resetSpawnTime();
        }
        increaseSpawnTime();
    }

    public int getMaxEnemies() {
        return maxEnemies;
    }
    
    public int getEnemyCount() {
        return enemyCount;
    }
    
    public void setMaxEnemies(int maxEnemies) {
        this.maxEnemies = maxEnemies;
    }
}
