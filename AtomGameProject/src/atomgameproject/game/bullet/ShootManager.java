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
    private int time, endTime, x, y;
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
    
    public void shootRed() {
        if (canShootRed) {
            checkReady();
            if (readyToShoot) {
                GameBullet go = new GameBullet(this.shooter, x, y, 6, new NeutronBullet());
                readyToShoot = false;
            }
        }
    }
    
    public void shootBlue() {
        if (canShootBlue) {
            checkReady();
            if (readyToShoot) {
                GameBullet go = new GameBullet(this.shooter, x, y, 6, new AntiNeutronBullet());
                readyToShoot = false;
            }
        }
    }
    
    public void tryToFire() {
        shootBlue();
        shootRed();
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isCanShootRed() {
        return canShootRed;
    }

    public void setCanShootRed(boolean canShootRed) {
        this.canShootRed = canShootRed;
        if (canShootBlue && canShootRed) {
            stopShooting();
        }
    }

    public boolean isCanShootBlue() {
        return canShootBlue;
    }

    public void setCanShootBlue(boolean canShootBlue) {
        this.canShootBlue = canShootBlue;
        if (canShootBlue && canShootRed) {
            stopShooting();
        }
    }
    
    public void stopShooting() {
        canShootBlue = false;
        canShootRed = false;
    }
}
