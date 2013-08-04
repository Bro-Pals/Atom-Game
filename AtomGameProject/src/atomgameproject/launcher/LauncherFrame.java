/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package atomgameproject.launcher;

import atomgameproject.AtomGameMain;
import static atomgameproject.AtomGameMain.frameHeight;
import static atomgameproject.AtomGameMain.frameWidth;
import atomgameproject.ImageLoader;
import atomgameproject.launcher.config.KeyBindWrapper;
import atomgameproject.states.PlayState;
import atomgameproject.states.StartState;
import atomgameproject.world.EnemySpawner;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import simplegames.animation.GameDrawingPanel;
import simplegames.animation.GamePanelSpace;

/**
 *
 * @author Jonathon
 */
public class LauncherFrame extends JFrame {
    
    private GamePanelSpace space;
    private JComboBox worldChooser, frameChooser, spawnChooser, maxEnemyChooser;
    protected int chosenWorldSizeX, chosenWorldSizeY, chosenFrameSizeX, chosenFrameSizeY, maxEnemies, spawnRate;
    private KeyBindWrapper bindings;
    protected GameDrawingPanel picPanel;
    
    public LauncherFrame(GamePanelSpace space) {
        super("Launch Atom-Game");
        this.space = space;
        setIconImage(ImageLoader.loadImage("img/Atom-GameIcon.gif"));
        bindings = new KeyBindWrapper();
        setResizable(false);
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(20, 20));
        JPanel choosers = new JPanel();
        choosers.setLayout(new GridLayout(3, 2, 20, 20));
        maxEnemies = 6;
        spawnRate = 300;
        chosenWorldSizeX = 0;
        chosenWorldSizeY = 0;
        chosenFrameSizeX = 0;
        chosenFrameSizeY = 0;
        
        worldChooser = new JComboBox(new String[]{
            "1000x1000", "2000x2000", "3000x3000", "4000x4000 (default)",
            "5000x5000", "6000x6000", "6500x6500"
        });
        frameChooser = new JComboBox(new String[]{
            "440x330", "540x405",
            "640x480", "720x540", "800x600 (default)", "960x720",
            "1200x720", "1280x960", "1600x1080",
            "1200x720", "1280x960", "1500x1000"
        });
        worldChooser.addActionListener(new WorldChooserListener());
        frameChooser.addActionListener(new FrameChooserListener());
        frameChooser.setSelectedIndex(4);
        worldChooser.setSelectedIndex(3);
        JPanel wChoosePanel = new JPanel(new GridLayout(2, 1, 10, 10));
        JPanel fChoosePanel = new JPanel(new GridLayout(2, 1, 10, 10)); 
        wChoosePanel.setPreferredSize(new Dimension(100, 80));
        fChoosePanel.setPreferredSize(new Dimension(100, 80));
        wChoosePanel.add(new JLabel("World Size"));
        wChoosePanel.add(worldChooser);
        fChoosePanel.add(new JLabel("Frame Size"));
        fChoosePanel.add(frameChooser);
        ///Enemy spawn rate
        JPanel spawnRatePanel = new JPanel(new GridLayout(2, 1, 10, 10));
        spawnChooser = new JComboBox(new String[]{
            "Slow", "Default", "Fast"
        });
        spawnChooser.setSelectedIndex(1);
        spawnChooser.addActionListener(new SpawnRateListener());
        spawnRatePanel.add(new JLabel("Spawn Rate"));
        spawnRatePanel.add(spawnChooser);
        ///Max enemies
        JPanel maxEnemiesPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        maxEnemyChooser = new JComboBox(new String[]{
            "Little", "Default", "Lots"
        });
        maxEnemyChooser.setSelectedIndex(1);
        maxEnemyChooser.addActionListener(new MaxEnemyListener());
        maxEnemiesPanel.add(new JLabel("Max Enemies"));
        maxEnemiesPanel.add(maxEnemyChooser);
        choosers.add(wChoosePanel);
        choosers.add(fChoosePanel);
        choosers.add(maxEnemiesPanel);
        choosers.add(spawnRatePanel);
        add(choosers, BorderLayout.CENTER);
        JButton accept = new JButton("Launch");
        accept.addActionListener(new GoButtonListener(this));
        JPanel acceptPanel = new JPanel(new GridLayout(1, 1, 50, 10));
        acceptPanel.add(accept);
        add(acceptPanel, BorderLayout.SOUTH);
        ///Everything initialized!
        ///Bannnner!
        picPanel = space.createGamePanel(324, 66, new LaunchBannerState());
        picPanel.setPreferredSize(new Dimension(324, 66));
        //Extra spacing
        add(picPanel, BorderLayout.NORTH);
        choosers.setPreferredSize(new Dimension(324, 200));
        //Moar formatting
        worldChooser.setPreferredSize(new Dimension(100, 60));
        frameChooser.setPreferredSize(new Dimension(100, 60));
        worldChooser.setSize(new Dimension(100, 40));
        frameChooser.setSize(new Dimension(100, 40));
        /////MORE mega clump stuff (lets make a launcher maker later bro)
        accept.setPreferredSize(new Dimension(90, 40));
        accept.setSize(new Dimension(90, 40));
        //Make a button that opens the keybinding dialog
        JButton bindButton = new JButton("Key bindings (N/A)");
        bindButton.addActionListener(new OpenKeyBinderListener(this, bindings));
        bindButton.setPreferredSize(new Dimension(150, 40));
        bindButton.setSize(new Dimension(150, 40));
        choosers.add(bindButton);
        pack();
        setLocation((getGraphicsConfiguration().getDevice().getDisplayMode().getWidth()/2)-(getWidth()/2), (getGraphicsConfiguration().getDevice().getDisplayMode().getHeight()/2)-(getHeight()/2));
        setVisible(true);
        space.startTicker();
    }

    public KeyBindWrapper getBindings() {
        return bindings;
    }

    public void setBindings(KeyBindWrapper bindings) {
        this.bindings = bindings;
    }

    public int getMaxEnemies() {
        return maxEnemies;
    }

    public int getSpawnRate() {
        return spawnRate;
    }
    
    class OpenKeyBinderListener implements ActionListener {

        private LauncherFrame frame;
        private KeyBindWrapper wrapper;
        
        public OpenKeyBinderListener(LauncherFrame frame, KeyBindWrapper wrapper) {
            this.frame = frame;
            this.wrapper = wrapper;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton s = (JButton)e.getSource();
            KeyBindingDialog kbd = new KeyBindingDialog(frame, wrapper);
        }  
    }
    
    class MaxEnemyListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JComboBox s = (JComboBox)e.getSource();
            switch((String)s.getSelectedItem()) {
                case "Little":
                    maxEnemies = 3;
                    break;
                case "Default":
                    maxEnemies = 6;
                    break;
                case "Lots":
                    maxEnemies = 11;
                    break;
                default:
                    maxEnemies = 6;
                    break;
            }
        }
        
    }
    
    class SpawnRateListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JComboBox s = (JComboBox)e.getSource();
            switch((String)s.getSelectedItem()) {
                case "Slow":
                    spawnRate = 550;
                    break;
                case "Default":
                    spawnRate = 300;
                    break;
                case "Fast":
                    spawnRate = 130;
                    break;
                default:
                    spawnRate = 300;
                    break;
            }
        }
        
    }
    
    class GoButtonListener implements ActionListener {
        private LauncherFrame parent;
        public GoButtonListener(LauncherFrame parent) {
            this.parent = parent;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            if (chosenFrameSizeX>0 && chosenFrameSizeY>0 && chosenWorldSizeX>0 && chosenWorldSizeY>0) {
                AtomGameMain.setFrameHeight(chosenFrameSizeY);
                AtomGameMain.setFrameWidth(chosenFrameSizeX);
                AtomGameMain.setWorldSizeX(chosenWorldSizeX);
                AtomGameMain.setWorldSizeY(chosenWorldSizeY);
                EnemySpawner.maxEnemies = maxEnemies;
                System.out.println(EnemySpawner.maxEnemies+"="+maxEnemies);
                EnemySpawner.spawnSpeed = spawnRate;
                System.out.println(EnemySpawner.spawnSpeed+"="+spawnRate);
                space.getTicker().removeListener(picPanel);
                JFrame f = space.createContainedGamePanel(frameWidth, frameHeight, "Atom-Game", new StartState(getBindings()));
                f.setLocation((getGraphicsConfiguration().getDevice().getDisplayMode().getWidth()/2)-(f.getWidth()/2), (getGraphicsConfiguration().getDevice().getDisplayMode().getHeight()/2)-(f.getHeight()/2));
                f.setIconImage(ImageLoader.loadImage("img/Atom-GameIcon.gif"));
                space.startTicker();
                this.parent.dispose();
            }
        }     
    }
    
    class WorldChooserListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JComboBox box = (JComboBox)e.getSource();
            switch((String)box.getSelectedItem()) {
                case "1000x1000":
                    chosenWorldSizeX = 1000;
                    chosenWorldSizeY = 1000;
                    break;
                case "2000x2000":
                    chosenWorldSizeX = 2000;
                    chosenWorldSizeY = 2000;
                    break;
                case "3000x3000":
                    chosenWorldSizeX = 3000;
                    chosenWorldSizeY = 3000;
                    break;
                case "4000x4000 (default)":
                    chosenWorldSizeX = 4000;
                    chosenWorldSizeY = 4000;
                    break;
                case "5000x5000":
                    chosenWorldSizeX = 5000;
                    chosenWorldSizeY = 5000;
                    break;
                case "6000x6000":
                    chosenWorldSizeX = 6000;
                    chosenWorldSizeY = 6000;
                    break;
                case "6500x6500":
                    chosenWorldSizeX = 6500;
                    chosenWorldSizeY = 6500;
                    break;
            }
        }    
    }
    
    class FrameChooserListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JComboBox box = (JComboBox)e.getSource();
            switch((String)box.getSelectedItem()) {
                case "440x330":
                    chosenFrameSizeX = 440;
                    chosenFrameSizeY = 330;
                    break;
                case "540x405":
                    chosenFrameSizeX = 540;
                    chosenFrameSizeY = 405;
                    break; 
                case "640x480": 
                    chosenFrameSizeX = 640;
                    chosenFrameSizeY = 480;
                    break;
                case "720x540":
                    chosenFrameSizeX = 720;
                    chosenFrameSizeY = 540;
                    break;
                case "960x720":
                    chosenFrameSizeX = 960;
                    chosenFrameSizeY = 720;
                    break;
                case "1200x720":
                    chosenFrameSizeX = 1200;
                    chosenFrameSizeY = 720;
                    break;
                case "1280x960": 
                    chosenFrameSizeX = 1280;
                    chosenFrameSizeY = 960;
                    break;
                case "1600x1080":
                    chosenFrameSizeX = 1600;
                    chosenFrameSizeY = 1080;
                case "1500x1000":
                    chosenFrameSizeX = 1500;
                    chosenFrameSizeY = 1000;
                    break;
                case "800x600 (default)":
                    chosenFrameSizeX = 800;
                    chosenFrameSizeY = 600;
                    break;
            }
        }    
    }
}
