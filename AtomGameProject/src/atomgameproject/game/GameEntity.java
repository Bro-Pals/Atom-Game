/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package atomgameproject.game;

import atomgameproject.behaviors.ReactsToCollide;
import atomgameproject.world.WorldMember;
import atomgameproject.world.components.SimplePhysicsComponent;

/**
 *
 * @author Jonathon
 */
public class GameEntity extends WorldMember implements ReactsToCollide {
    
    public GameEntity(int xPos, int yPos, int sizeX, int sizeY) {
        super();
        x=xPos; y=yPos; width=sizeX; height=sizeY;
        addComponent(new SimplePhysicsComponent(this));
    }

    @Override
    public void reactToCollide(WorldMember hit) {
        
    }
}
