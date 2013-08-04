/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package atomgameproject.world;

import atomgameproject.world.components.SimplePhysicsComponent;
import atomgameproject.world.components.WorldComponent;
import java.awt.Rectangle;
import java.util.ArrayList;
import simplegames.behaviors.Updatable;

/**
 *
 * @author Jonathon
 */
public abstract class WorldMember extends Rectangle implements Updatable {
    
    private ArrayList<WorldComponent> components;
    
    public WorldMember() {
        GameWorld.getWorld().addMember(this);
        components = new ArrayList<>();
    }
    
    public void remove() {
        GameWorld.getWorld().removeMember(this);
    }
    
    public void addComponent(WorldComponent component) {
        components.add(component);
    }
    
    public void removeComponent(WorldComponent component) {
        components.remove(component);
    }
    
    public WorldComponent getComponent(Class<?> c) {
        WorldComponent comp = null;
        for (int i=0; i<components.size(); i++) {
            if (c.isInstance(components.get(i))) {
                comp = components.get(i);
            }
        }
        return comp;
    }
    
    public boolean hasComponent(Class<?> c) {
        boolean hasIt = false;
        for (int i=0; i<components.size(); i++) {
            if (c.isInstance(components.get(i))) {
                hasIt = true;
            }
        }
        return hasIt;
    }
    
    @Override
    public void update(float timeDiff) {
        ///Update it's components
        for (int i=0; i<components.size(); i++) {
            components.get(i).updateComponent(timeDiff);
        }
    }

    public ArrayList<WorldComponent> getComponents() {
        return components;
    }
}
