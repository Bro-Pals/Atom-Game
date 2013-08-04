package atomgameproject;

import atomgameproject.game.EnemyAtom;
import atomgameproject.game.GameAtom;
import atomgameproject.game.GameEntity;
import atomgameproject.game.PlayerAtom;
import atomgameproject.game.WorldBoundryBox;
import atomgameproject.game.bullet.BulletType;
import atomgameproject.game.bullet.GameBullet;
import atomgameproject.world.Camera;
import atomgameproject.world.GameWorld;
import atomgameproject.world.Sprite;
import atomgameproject.world.WorldMember;
import atomgameproject.world.components.Stability;
import atomgameproject.world.components.StabilityTable;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import simplegames.behaviors.Renderable;

/**
 *
 * @author Owner
 */
public class GameRenderer implements Renderable {

    private ArrayList<WorldMember> membersInView;
    private Camera camera;
    private BufferedImage background;
    private ArrayList<Sprite> sprites;

    public GameRenderer() {
        //background = ImageLoader.loadImage("img/largeBackground.gif");
    }

    @Override
    public BufferedImage draw(BufferedImage bi, Graphics2D gd) {
        camera = GameWorld.getWorld().getCamera();
        //membersInView = camera.getMembersInView();
        membersInView = GameWorld.getWorld().getEverything();
        sprites = GameWorld.getWorld().getSprites();
        gd = (Graphics2D) bi.getGraphics();
        //gd.drawImage(background, -((AtomGameMain.worldSizeX+background.getWidth())/2), -AtomGameMain.worldSizeY-background.getHeight(), null);
        for (int i = 0; i < membersInView.size(); i++) {
            if (membersInView.get(i) != null) {
                WorldMember wm = membersInView.get(i);
                if (membersInView.get(i) instanceof GameEntity) {
                    gd.setColor(Color.black);
                    gd.fillOval((int) wm.getX() - (int) camera.getX() + 3, (int) wm.getY() - (int) camera.getY() + 3, (int) wm.getWidth(), (int) wm.getHeight());
                }
                if (membersInView.get(i) instanceof WorldBoundryBox) {
                    gd.setColor(Color.black);
                    gd.fillRect((int) wm.getX() - (int) camera.getX() + 3, (int) wm.getY() - (int) camera.getY() + 3, (int) wm.getWidth(), (int) wm.getHeight());
                }
                if (membersInView.get(i) instanceof PlayerAtom) {
                    gd.setColor(Color.ORANGE);
                    gd.fillOval(wm.x - camera.x, wm.y - camera.y, wm.width, wm.height);
                    gd.fillOval(wm.x - camera.x, wm.y - camera.y, wm.width, wm.height);
                    gd.setColor(Color.WHITE);
                    gd.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 9));
                    gd.drawString("" + (((GameAtom) wm).getAtomComponent()).getProtons() + "", (int) (wm.x - camera.x + (int) ((wm.getWidth() / 2) - 5)), (int) (wm.y - camera.y + (int) ((wm.getHeight() / 2) + 20)));
                    gd.drawString("" + ((((GameAtom) wm).getAtomComponent()).getNeutrons() + (((GameAtom) wm).getAtomComponent()).getProtons()) + "", (int) (wm.x - camera.x + (int) ((wm.getWidth() / 2) - 4)), (int) (wm.y - camera.y + (int) ((wm.getHeight() / 2) - 12)));
                    gd.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
                    gd.drawString("" + StabilityTable.getTable().getAtomicSymbol(((GameAtom) wm).getAtomComponent()) + "", (int) (wm.x - camera.x + (int) ((wm.getWidth() / 2)) - 5), (int) (wm.y - camera.y + (int) ((wm.getHeight() / 2))) + 4);
                }
                if (membersInView.get(i) instanceof EnemyAtom) {
                    if (((EnemyAtom) membersInView.get(i)).getStability(((EnemyAtom) membersInView.get(i))) == Stability.STABLE) {
                        gd.setColor(Color.green);
                    } else if (((EnemyAtom) membersInView.get(i)).getStability(((EnemyAtom) membersInView.get(i))) == Stability.UNDER) {
                        gd.setColor(Color.blue);
                    } else if (((EnemyAtom) membersInView.get(i)).getStability(((EnemyAtom) membersInView.get(i))) == Stability.ABOVE) {
                        gd.setColor(Color.red);
                    }
                    gd.fillOval(wm.x - camera.x, wm.y - camera.y, wm.width, wm.height);
                    gd.setColor(Color.WHITE);
                    gd.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
                    gd.drawString("" + (((GameAtom) wm).getAtomComponent()).getProtons() + "", (int) (wm.x - camera.x + (int) ((wm.getWidth() / 2) - 5)), (int) (wm.y - camera.y + (int) ((wm.getHeight() / 2) + 20)));
                    gd.drawString("" + ((((GameAtom) wm).getAtomComponent()).getNeutrons() + (((GameAtom) wm).getAtomComponent()).getProtons()) + "", (int) (wm.x - camera.x + (int) ((wm.getWidth() / 2) - 4)), (int) (wm.y - camera.y + (int) ((wm.getHeight() / 2) - 12)));

                    gd.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
                    gd.drawString("" + StabilityTable.getTable().getAtomicSymbol(((GameAtom) wm).getAtomComponent()) + "", (int) (wm.x - camera.x + (int) ((wm.getWidth() / 2)) - 10), (int) (wm.y - camera.y + (int) ((wm.getHeight() / 2))) + 4);
                }
                if (membersInView.get(i) instanceof GameBullet) {
                    if (((GameBullet) membersInView.get(i)).getType() != null) {
                        if (((GameBullet) membersInView.get(i)).getType().getUsed() == false) {
                            gd.setColor(((GameBullet) membersInView.get(i)).getBulletColor());
                            gd.fillOval((int) wm.getX() - (int) camera.getX(), (int) wm.getY() - (int) camera.getY(), (int) wm.getWidth(), (int) wm.getHeight());
                        }
                    }
                }
            }
        }
        for (int s = 0; s < sprites.size(); s++) {
            if (sprites.get(s) != null) {
                sprites.get(s).move();
                sprites.get(s).decreaseLifeSpan();
                if (sprites.get(s).isDead()) {
                    sprites.remove(s);
                    GameWorld.getWorld().getSprites().remove(s);
                    System.out.println("Removed a sprite");
                } else {
                    gd.drawImage(sprites.get(s).getImage(), sprites.get(s).getPosition().x - (int) camera.getX(), sprites.get(s).getPosition().y - (int) camera.getY(), null);
                }
            }
        }
         return bi;
    }
}
