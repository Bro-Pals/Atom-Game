/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package atomgameproject.game;

import atomgameproject.behaviors.ReactsToCollide;
import atomgameproject.game.bullet.GameBullet;
import atomgameproject.world.WorldMember;
import atomgameproject.world.components.Stability;

/**
 *
 * @author Jonathon
 */
public class WorldBoundryBox extends GameEntity implements ReactsToCollide {
    
    public WorldBoundryBox(int x, int y, int width, int height) {
        super(x, y, width, height);
    }
    
    @Override
    public void reactToCollide(WorldMember hit) {
        if (hit!=null) {
            if (hit instanceof GameBullet) {
                hit.remove();
            }
            if (hit instanceof EnemyAtom) {
                if (((EnemyAtom)hit).getStability((GameAtom)hit)==Stability.STABLE) {
                    hit.remove();
                }
            }
        }
    }
}
