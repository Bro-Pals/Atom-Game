/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package atomgameproject.game;

import atomgameproject.world.components.AtomComponent;
import atomgameproject.world.components.SimplePhysicsComponent;
import atomgameproject.world.components.Stability;
import atomgameproject.world.components.StabilityTable;

/**
 *
 * @author Jonathon
 */
public class GameAtom extends GameEntity {
    
    private AtomComponent atomPart;
    
    public GameAtom(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.atomPart = new AtomComponent(this);
        addComponent(atomPart); //Now it has 2 components
    //    System.out.println(getComponents().size()+" <--Number of compoentnets");
    }
    
    public void addNeutron() {
        atomPart.addNeutron();
    }
    
    public AtomComponent getAtomComponent() {
        return atomPart;
    }
    
    public SimplePhysicsComponent getPhysics() {
        return (SimplePhysicsComponent) this.getComponent(SimplePhysicsComponent.class);
    }
    
    public Stability getStability(GameAtom atom) {
        if (atomPart!=null) {
         return StabilityTable.getTable().checkStability(atom.getAtomComponent());
        } else {
      //      System.out.println("No atom component");
            return null;
        }
    }
    
    public void removeNeutron() {
        atomPart.removeNeutron();
    }

    public void addProton() {
        atomPart.addProton();
    }
    
    public void removeProton() {
        atomPart.removeProton();
    }
    
    public void addElectron() {
        atomPart.addElectron();
    }
    
    public void removeElectron() {
        atomPart.removeElectron();
    }
    
    public int getProtons() {
        return atomPart.getProtons();
    }

    public int getNeutrons() {
        return atomPart.getNeutrons();
    }

    public int getElectrons() {
        return atomPart.getNeutrons();
    }
}
