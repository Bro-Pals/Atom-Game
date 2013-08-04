/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package atomgameproject.states;

import atomgameproject.AtomGameMain;
import atomgameproject.GameRenderer;
import atomgameproject.game.EnemyAtom;
import atomgameproject.game.GameEntity;
import atomgameproject.game.PlayerAtom;
import atomgameproject.game.WorldBoundryBox;
import atomgameproject.gui.GuiClock;
import atomgameproject.gui.GuiManager;
import atomgameproject.gui.StabilityGui;
import atomgameproject.gui.StabilityTimer;
import atomgameproject.launcher.config.KeyBindWrapper;
import atomgameproject.world.EnemySpawner;
import atomgameproject.world.GameWorld;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import simplegames.animation.GameDrawingPanel;
import simplegames.states.GameState;

/**
 *
 * @author Jonathon
 */
public class PlayState extends GameState {

    private GameRenderer renderer;
    private GameWorld world;
    private PlayerAtom player;
    private GuiManager guis;
    private KeyBindWrapper wrapper;

    public PlayState(KeyBindWrapper wrapper) {
        this.wrapper = wrapper;
    }
    private EnemySpawner spawner;
    
    @Override
    public void init(GameDrawingPanel panel) {
        renderer = new GameRenderer();
        guis = new GuiManager();
        world = GameWorld.getWorld(); 
        player = new PlayerAtom(((AtomGameMain.worldSizeX/2)-(25)), ((AtomGameMain.worldSizeY/2)-(25)), 50, 50, panel, wrapper);

        world.setPlayer(player);
        world.makeSpawner();
        spawner = world.getEnemySpawner();
        EnemyAtom enemy = new EnemyAtom(100, 300, 60, 60, 6, 12);
        //Make huge walls for boundry lines
        WorldBoundryBox[] boxes = new WorldBoundryBox[4];
        boxes[0] = new WorldBoundryBox(-AtomGameMain.worldSizeX, -(AtomGameMain.worldSizeY), (AtomGameMain.worldSizeX*3), (AtomGameMain.worldSizeY));
        boxes[1] = new WorldBoundryBox(-(AtomGameMain.worldSizeX), AtomGameMain.worldSizeY, (AtomGameMain.worldSizeX*3), (AtomGameMain.worldSizeY));
        boxes[2] = new WorldBoundryBox(-AtomGameMain.worldSizeX, -AtomGameMain.worldSizeY, (AtomGameMain.worldSizeX), (AtomGameMain.worldSizeY*3));
        boxes[3] = new WorldBoundryBox(AtomGameMain.worldSizeX*2, -AtomGameMain.worldSizeY, (AtomGameMain.worldSizeX), (AtomGameMain.worldSizeY*3));

        initGuis(panel);
        world.addCamera(AtomGameMain.frameWidth, AtomGameMain.frameHeight);
    }

    private void initGuis(GameDrawingPanel panel) {
        guis.addElement(new StabilityGui(player));
        guis.addElement(new GuiClock());
        guis.addElement(new StabilityTimer(player, panel));
    }
    
    @Override
    public void registerListeners(GameDrawingPanel panel) {
        panel.addKeyDownListener(player);
        panel.addKeyUpListener(player);
        panel.addMouseDownListener(player);
        panel.addMouseUpListener(player);
    }

    @Override
    public BufferedImage drawToImage(BufferedImage bImage, Graphics2D graphics2d) {
        graphics2d = (Graphics2D) bImage.getGraphics();
        bImage = renderer.draw(bImage, graphics2d);
        bImage = guis.draw(bImage, graphics2d);
        return bImage;
    }

    @Override
    public void updateState(float diffTime) {
        world.update(diffTime);
        world.getCamera().update(diffTime);
        guis.update(diffTime);
    }
}
