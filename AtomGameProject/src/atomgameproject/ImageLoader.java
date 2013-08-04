/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package atomgameproject;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/**
 *
 * @author Jonathon
 */
public class ImageLoader {
    
    public static BufferedImage loadImage(String path) {
        try {
            return (BufferedImage)ImageIO.read(AtomGameMain.class.getResourceAsStream(path));
        } catch(Exception e) {
            System.out.println("Could not find " + path);
            return null;
        }
    }
}
