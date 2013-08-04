
package atomgameproject.states;

import atomgameproject.AtomGameMain;
import atomgameproject.ImageLoader;
import atomgameproject.game.PlayerAtom;
import atomgameproject.launcher.config.KeyBindWrapper;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import simplegames.animation.GameDrawingPanel;
import simplegames.inputlisteners.MouseDownListener;
import simplegames.inputlisteners.click.ClickAreaListener;
import simplegames.inputlisteners.click.HoverImageClickArea;
import simplegames.states.GameState;

/**
 *
 * @author Jonathon
 */
public class StartState extends GameState implements MouseDownListener {
    
    private GameDrawingPanel panel;
    protected KeyBindWrapper bindings;
    private HoverImageClickArea startButton, exitButton, bugButton;
    
    public StartState(KeyBindWrapper bindings) {
        this.bindings = bindings;
    }
    
    @Override
    public void init(GameDrawingPanel panel) {
        this.panel = panel;
        int width = AtomGameMain.frameWidth;
        int height = AtomGameMain.frameHeight;
        startButton = new HoverImageClickArea((width/2)-(275/2), (height/2)-(height/6), ImageLoader.loadImage("img/buttons/purpleButtonDown.png"), ImageLoader.loadImage("img/buttons/purpleButtonUp.png"));
        exitButton = new HoverImageClickArea((width/2)-(275/2), (height/2)+(height/6), ImageLoader.loadImage("img/buttons/orangeButtonDown.png"), ImageLoader.loadImage("img/buttons/orangeButtonUp.png"));
        bugButton = new HoverImageClickArea((width/2)-(275/2), (height/2), ImageLoader.loadImage("img/buttons/blueButtonDown.png"), ImageLoader.loadImage("img/buttons/blueButtonUp.png"));
    }

    @Override
    public void registerListeners(GameDrawingPanel panel) {
        startButton.addListener(new StartGameListener(panel));
        exitButton.addListener(new ExitGameListener(panel));
        bugButton.addListener(new BugGameListener(panel));
        panel.addMouseMovedListener(startButton);
        panel.addMouseMovedListener(exitButton);
        panel.addMouseMovedListener(bugButton);
        panel.addMouseDownListener(this);
    }

    @Override
    public BufferedImage drawToImage(BufferedImage bImage, Graphics2D graphics2d) {
        graphics2d = (Graphics2D) bImage.getGraphics();
        bImage = startButton.draw(bImage, graphics2d);
        bImage = exitButton.draw(bImage, graphics2d);
        bImage = bugButton.draw(bImage, graphics2d);
        graphics2d.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 72));
        graphics2d.setColor(Color.GREEN);
        graphics2d.drawString("Atom Game", (AtomGameMain.frameWidth/2)-190, (AtomGameMain.frameHeight/6));
        graphics2d.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
        graphics2d.drawString("Made for the Arbitrary Game Jam #01", (AtomGameMain.frameWidth/2)-180, (AtomGameMain.frameHeight/6)+30);
        graphics2d.drawString("Created by Jon Prehn and Kevin Prehn", (AtomGameMain.frameWidth/2)-180, (AtomGameMain.frameHeight/6)+50);
        graphics2d.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
        graphics2d.setColor(Color.BLACK);
        graphics2d.drawString("Start", (int)startButton.getBox().getX()+70, (int)startButton.getBox().getY()+50);
        graphics2d.drawString("Exit", (int)exitButton.getBox().getX()+70, (int)exitButton.getBox().getY()+50);
        graphics2d.drawString("Known Bugs", (int)bugButton.getBox().getX()+5, (int)bugButton.getBox().getY()+50);
        return bImage;
    }

    @Override
    public void updateState(float diffTime) {
        
    }  

    @Override
    public void reactToMouseDown(int x, int y, MouseEvent me) {
        startButton.checkSpot(x, y);
        exitButton.checkSpot(x, y);
        bugButton.checkSpot(x, y);
    }
    
    class ExitGameListener implements ClickAreaListener {

        private GameDrawingPanel panel;
        public ExitGameListener(GameDrawingPanel panel) {
            this.panel = panel;
        }
        
        @Override
        public void reactToClick() {
            System.exit(0);
        }
        
    }
    
    class StartGameListener implements ClickAreaListener {

        private GameDrawingPanel panel;
        public StartGameListener(GameDrawingPanel panel) {
            this.panel = panel;
        }
        
        @Override
        public void reactToClick() {
            panel.changeState(new PlayState(bindings));
        }
        
    }
    
    class BugGameListener implements ClickAreaListener {

        private GameDrawingPanel panel;
        public BugGameListener(GameDrawingPanel panel) {
            this.panel = panel;
        }
        
        @Override
        public void reactToClick() {
            panel.changeState(new BugState(bindings));
        }
        
    }
}
