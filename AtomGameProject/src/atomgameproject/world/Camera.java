/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package atomgameproject.world;

import java.awt.Rectangle;
import java.util.ArrayList;
import simplegames.behaviors.Updatable;

/**
 *
 * @author Jonathon and Kevin
 */
public class Camera extends Rectangle implements Updatable {
    private ArrayList<WorldMember> membersInView;
    private GameWorld world;
    private ArrayList<WorldMember> worldMembers;
            
    public Camera(int sizeX, int sizeY) {
        width = sizeX;
        height = sizeY;
        x = 0;
        y = 0; // default position
        membersInView = new ArrayList<WorldMember>();
        world = GameWorld.getWorld();
        worldMembers = world.getEverything();
    }
    
    public void findMembersInView() {
        membersInView.clear();
        for (int i=0; i<worldMembers.size(); i++) {
            if (worldMembers.get(i).intersects(this)) {
                membersInView.add(worldMembers.get(i));
            }
        }
    }
    
    public ArrayList<WorldMember> getMembersInView() {
        if (world!=null) {
           findMembersInView();
           return membersInView;
        } else {
            return null;
        }
    }
    
    @Override
    public void update(float f) {
        if (world!=null) {
           worldMembers = world.getEverything();
           findMembersInView();
        }
    }
}
