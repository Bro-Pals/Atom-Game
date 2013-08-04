/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package atomgameproject.game.bullet;

import atomgameproject.world.WorldMember;
import simplegames.behaviors.Updatable;

/**
 *
 * @author Jonathon
 */
public class ShootManager implements Updatable {
    
    private boolean canShootRed, canShootBlue, readyToShoot;
    private int time, endTime;
    private WorldMember shooter;
    
    public ShootManager(WorldMember shooter, int endTime) {
        this.shooter = shooter;
        canShootRed = false;
        canShootBlue = false;
        readyToShoot = false;
        time = 0;
        this.endTime = endTime;
        System.out.println("Created ShootManager with endTime of " + endTime + "");
    }

    @Override
    public void update(float f) {
        time++;
    }
    
    public void checkReady() {
        if (time>=endTime) {
            time = 0;
            readyToShoot = true;
        }
    }
    
    public void shootRed(int x, int y) {
        checkReady();
        if (readyToShoot) {
            GameBullet go = new GameBullet(this.shooter, x, y, 6, new NeutronBullet());
        }
    }
    
    public void shootBlue(int x, int y) {
        checkReady();
        if (readyToShoot) {
            GameBullet go = new GameBullet(this.shooter, x, y, 6, new AntiNeutronBullet());
        }
    }
}
