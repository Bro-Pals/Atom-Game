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
    private int time, time0,time1, time2, time3, endTime, x, y;
    private WorldMember shooter;
    
    public ShootManager(WorldMember shooter, int endTime) {
        this.shooter = shooter;
        canShootRed = false;
        canShootBlue = false;
        readyToShoot = false;
        time = 0;
        time0 = 0;
        time1 = 0;
        time2 = 0;
        time3 = 0; // pentuple buffereing!
        this.endTime = endTime;
        //System.out.println("Created ShootManager with endTime of " + endTime + "");
    }

    @Override
    public void update(float f) {
        time++;
        time0++;
        time1++;
        time2++;
        time3++;
       // System.out.println("CanShootRed: " + canShootRed + ", CanShootBlue: " + canShootBlue + ", Time:" + time + ", EndTime:" + endTime + ", ReadyToShoot: " + readyToShoot + "");
    }
    
    public void checkReady() {
        if (time>=endTime || time!=time0 || time!=time1 || time!=time2 || time!=time3 || time0!=time1 || time0!=time2  || time0!=time3) {
            time = 0;
            time0 = 0;
            time1 = 0;
            time2 = 0;
            time3 = 0;
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
        if (canShootBlue==false) {
            this.canShootRed = canShootRed;
        }
    }

    public boolean isCanShootBlue() {
        return canShootBlue;
    }

    public void setCanShootBlue(boolean canShootBlue) {
        if (canShootRed==false) {
            this.canShootBlue = canShootBlue;
        }
    }
    
    public void stopShooting() {
        canShootBlue = false;
        canShootRed = false;
    }
}
