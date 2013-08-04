/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package atomgameproject.gui;

import atomgameproject.AtomGameMain;
import atomgameproject.game.PlayerAtom;
import atomgameproject.world.components.AtomComponent;
import atomgameproject.world.components.Stability;
import atomgameproject.world.components.StabilityTable;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author Jonathon
 */
public class StabilityGui extends GuiElement {

    private PlayerAtom player;
    private int stabilityPercentage, distanceFromStable, markPercent, pixelsPerPercent, currentNeutrons, centerOfNeutrons;
    private int heightPercent;
    
    public StabilityGui(PlayerAtom tracking) {
        super(0, 0, AtomGameMain.frameWidth, 0);
        this.player = tracking;
        stabilityPercentage = 20;
        distanceFromStable = 0;
        markPercent = 1;
        heightPercent = 7;
        pixelsPerPercent = (int)(AtomGameMain.frameWidth/100);
        centerOfNeutrons = (int)((StabilityTable.getTable().getMinStablity(player.getProtons())+StabilityTable.getTable().getMaxStablity(player.getProtons()))/2);
        setHeight(heightPercent*pixelsPerPercent);
    }
     
    @Override
    public BufferedImage draw(BufferedImage bi, Graphics2D gd) {
        int min = StabilityTable.getTable().getMinStablity(player.getProtons());
        int max = StabilityTable.getTable().getMaxStablity(player.getProtons());
        int centerScreen = (int)(AtomGameMain.frameWidth/2);
        int minTickSpot, offsetForMax, markSpot;
        int stableOffset = (player.getNeutrons()-centerOfNeutrons);
        minTickSpot = (centerScreen)-((centerOfNeutrons-(StabilityTable.getTable().getMinStablity(player.getProtons())))*pixelsPerPercent);
        offsetForMax = (max-min)*pixelsPerPercent;
        
        gd = (Graphics2D) bi.getGraphics();
        
        gd.setColor(Color.PINK);
        gd.fillRect(getX(), getY(), getWidth(), getHeight());
       // System.out.println("The data for the Gui is " + getX() + ", " + getY() + ", " + getWidth() + ", " + getHeight() + "");

        gd.setColor(Color.GREEN);
        gd.fillRect(minTickSpot, 0, offsetForMax, getHeight()); //Stability band
        gd.setColor(Color.BLUE);
        gd.fillRect((minTickSpot+offsetForMax), 0, AtomGameMain.frameWidth-(minTickSpot+offsetForMax), getHeight());
        gd.setColor(Color.RED);
        gd.fillRect(0, 0, minTickSpot, getHeight());
        gd.setColor(Color.WHITE); ///Neutron marker
        gd.fillRect(centerScreen+(stableOffset*pixelsPerPercent)-(markPercent*pixelsPerPercent/2), 0, markPercent*pixelsPerPercent, getHeight());
        gd.setColor(Color.BLACK);
        gd.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        gd.drawString("" + player.getNeutrons() + "", centerScreen+(stableOffset*pixelsPerPercent)-(markPercent*pixelsPerPercent/2)-5, getHeight()-(getHeight()/2)+5);
        return bi;
    }

    @Override
    public void update(float f) {
        currentNeutrons = player.getAtomComponent().getNeutrons();
        Stability s = StabilityTable.getTable().checkStability((AtomComponent)player.getAtomComponent());
        if (s!=Stability.STABLE) {
            
            if (player.getAtomComponent().getNeutrons()>StabilityTable.getTable().getMaxStablity(player.getProtons())) {
                distanceFromStable = player.getAtomComponent().getNeutrons()-StabilityTable.getTable().getMaxStablity(player.getProtons());
            }
            else if (player.getAtomComponent().getNeutrons()<StabilityTable.getTable().getMinStablity(player.getProtons())) {
                distanceFromStable = player.getAtomComponent().getNeutrons()-StabilityTable.getTable().getMinStablity(player.getProtons());
            }
        } else {
            stabilityPercentage = 30;
            distanceFromStable = 0;
        }
         ////TO DO orange thing that grows and shrinks depending on how unbalanced the stability is
        
    }  
}
