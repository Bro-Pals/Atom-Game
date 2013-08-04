
package atomgameproject;

import atomgameproject.game.EnemyAtom;
import atomgameproject.game.GameEntity;
import atomgameproject.launcher.LauncherFrame;
import atomgameproject.states.PlayState;
import atomgameproject.states.StartState;
import atomgameproject.world.Camera;
import atomgameproject.world.GameWorld;
import simplegames.animation.GamePanelSpace;
import simplegames.world.WorldObject;

/**
 *
 * @author Owner
 */
public class AtomGameMain {
        public static int frameWidth = 1000;
        public static int frameHeight = 800;
        public static int worldSizeX = 2000;
        public static int worldSizeY = 1800;
    /**
     * @param args the command line arguments
     */    
    public static void main(String[] args) {
        System.out.println("Let's start this project!");
        GamePanelSpace space = new GamePanelSpace();
        LauncherFrame frame = new LauncherFrame(space);
    }

    public static void setFrameWidth(int aFrameWidth) {
        frameWidth = aFrameWidth;
    }

    public static void setFrameHeight(int aFrameHeight) {
        frameHeight = aFrameHeight;
    }

    public static void setWorldSizeX(int aWorldSizeX) {
        worldSizeX = aWorldSizeX;
    }

    public static void setWorldSizeY(int aWorldSizeY) {
        worldSizeY = aWorldSizeY;
    }
}
