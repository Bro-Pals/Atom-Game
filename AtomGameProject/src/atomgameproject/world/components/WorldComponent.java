/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package atomgameproject.world.components;

/**
 *
 * @author Jonathon
 */
public interface WorldComponent {
    ///Common interface among world components
    public void updateComponent(float timeDiff);
}
