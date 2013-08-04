/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package atomgameproject.world.components;

import atomgameproject.world.WorldMember;

/**
 *
 * @author Jonathon
 */
public class AtomComponent implements WorldComponent {

    private int protons, neutrons, electrons;
    
    public WorldMember parent;
    
    public AtomComponent(WorldMember parent) {
        this.parent = parent;

    }
    
    @Override
    public void updateComponent(float timeDiff) {
        
    }
    
    public void setParent(WorldMember wm) {
        parent = wm;
    }
    
    public void setNeutrons(int n) {
         this.neutrons = n;
    }
    
    public void setProtons(int p) {
         this.protons = p;
    }
    
    public void setElectrons(int e) {
         this.electrons = e;
    }
    
    
    public void addNeutron() {
        this.neutrons = this.neutrons + 1;
    }
    
    public void removeNeutron() {
        this.neutrons = this.neutrons - 1;
    }

    public void addProton() {
        this.protons++;
    }
    
    public void removeProton() {
        this.protons--;
    }
    
    public void addElectron() {
        this.electrons++;
    }
    
    public void removeElectron() {
        this.electrons--;
    }
    
    public int getProtons() {
        return protons;
    }

    public int getNeutrons() {
        return neutrons;
    }

    public int getElectrons() {
        return electrons;
    }
}
