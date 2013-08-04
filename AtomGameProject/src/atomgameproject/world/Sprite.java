/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package atomgameproject.world;

import atomgameproject.ImageLoader;
import java.awt.Point;
import java.awt.image.BufferedImage;

/**
 *
 * @author Owner
 */
public class Sprite {
    
    private int xVel, yVel, lifeSpan, initSpan;
    private Point position;
    private BufferedImage img;
    
    public Sprite(Point pos, int lspan, String imgUrl, int xV, int yV) {
        position = pos;
        img = ImageLoader.loadImage(imgUrl);
        xVel = xV;
        yVel = yV;
        lifeSpan = lspan;
        initSpan = lspan;
    }
    
    public Sprite(Point pos,  int lspan, String imgUrl) {
        position = pos;
        img = ImageLoader.loadImage(imgUrl);
        xVel = 0;
        yVel = 0;
        lifeSpan = lspan;
        initSpan = lspan;
    }
    
    public void decreaseLifeSpan() {
        lifeSpan--;
    }
    
    public void move() {
        position = new Point(position.x+xVel, position.y+yVel);
    }
    
    public boolean isDead() {
        return (lifeSpan>initSpan);
    }
    
    public BufferedImage getImage() {
        return img;
    }

    public Point getPosition() {
        return new Point(position.x-(img.getWidth()/2), position.y-(img.getHeight()/2));
    }
}
