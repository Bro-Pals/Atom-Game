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
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import simplegames.animation.GameDrawingPanel;
import simplegames.inputlisteners.KeyDownListener;
import simplegames.states.GameState;

/**
 *
 * @author Jonathon
 */
public class PlayState extends GameState implements KeyDownListener {

    private GameRenderer renderer;
    private GameWorld world;
    private PlayerAtom player;
    private GuiManager guis;
    private KeyBindWrapper wrapper;
    private boolean paused;

    public PlayState(KeyBindWrapper wrapper) {
        this.wrapper = wrapper;
    }
    private EnemySpawner spawner;
    
    @Override
    public void init(GameDrawingPanel panel) {
        paused = false;
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
        panel.addKeyDownListener(this);
    }

    @Override
    public BufferedImage drawToImage(BufferedImage bImage, Graphics2D graphics2d) {
        graphics2d = (Graphics2D) bImage.getGraphics();
        bImage = renderer.draw(bImage, graphics2d);
        bImage = guis.draw(bImage, graphics2d);
        if (paused) {
            graphics2d.setFont(new Font(Font.DIALOG, Font.BOLD, 28));
            graphics2d.setColor(Color.RED);
            graphics2d.drawString("Paused - Press p to unpause", (AtomGameMain.frameWidth/2)-220, (AtomGameMain.frameHeight/2)-10);
        }
        return bImage;
    }

    @Override
    public void updateState(float diffTime) {
        if (!paused) {
            world.update(diffTime);
            world.getCamera().update(diffTime);
            guis.update(diffTime);
        }
    }

    public void togglePause() {
        if (paused) {
            paused = false;
        } else {
            paused = true;
        }
    }
    
    @Override
    public void reactToKeyDown(KeyEvent ke) {
        if (ke.getKeyCode()==KeyEvent.VK_P) {
            togglePause();
        }
    }
}
