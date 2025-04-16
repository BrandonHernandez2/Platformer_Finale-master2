package objects;

import utilz.Constants;

import java.awt.*;
import java.awt.geom.Rectangle2D;

import static utilz.Constants.ANI_SPEED;
import static utilz.Constants.ObjectConstants.*;

public class GameObject {

    protected int x, y, objType;
    protected Rectangle2D.Float hitbox;
    protected boolean doAnimation, active = true;
    protected int aniTick, aniIndex;
    protected int xDrawOffset, yDrawOffset;

    public GameObject(int x, int y, int objType) {
        // TODO: assign this x to x
        this.x = x;
        // TODO: repeat for the other parameters
        this.y = y;
        this.objType = objType;
    }

    protected void updateAnimationTick(){
        // TODO: add 1 to aniTick
        aniTick ++;
        // TODO: check if aniTick is greater than or equal to ANI_SPEED
        if (aniTick >= ANI_SPEED) {
            // if block beginning
            // TODO: set aniTick to 0
            aniTick = 0;
            // TODO: add 1 to aniIndex
            aniIndex ++;
            // TODO: check if aniIndex is greater than or equal to GetSpriteAmount(objType)
            if (aniIndex >= GetSpriteAmount(objType)) {
                // if block beginning
                // TODO: set aniIndex to 0
                aniIndex = 0;
                // TODO: check if objType is BARRELL or BOX
                if (objType == BARREL || objType == BOX) {
                    // if block beginning
                    // TODO: set doAnimation to false
                    // TODO: set active to false
                    active = false;
                    // end of if
                }
                    // beginning of else if block for objType equal to CANNON_LEFT or CANNON_RIGHT
                else {
                    // TODO: set doAnimation to false
                    // end of else if
                }
                // end of if
            }
                // end of if
        }
    }

    public void reset(){
        // TODO: set aniIndex, aniTick to 0
        aniTick = 0;
        aniIndex = 0;
        // TODO: set active to true
        active = true;

        // TODO: check if objType is a BARRELL or BOX, or CANNON_LEFT or CANNON_RIGHT
        if (objType == BARREL || objType == BOX || objType == CANNON_LEFT || objType == CANNON_RIGHT) {
            // if block beginning
            // TODO: set doAnimation to false
            doAnimation = false;
            // end of if block
        }
        // else block beginning
        else {
            // TODO: set doAnimation to true
            doAnimation = true;
            // end of else block
        }
    }

    protected void initHitbox(int width, int height){
        // TODO: set hitbox to new Rectangle2D.Float()
        hitbox = new Rectangle2D.Float(x, y, (int) (width * Constants.Game.SCALE), (int) (height * Constants.Game.SCALE));
        // cont.  passing in x, y, (int) (width * Game.SCALE), (int) (height * Game.SCALE)

    }

    public void drawHitbox(Graphics g, int xLvlOffset){
        // TODO: call g's setColor method and pass in Color.PINK
        g.setColor(Color.PINK);
        // TODO: call g's drawRect method passing in
        g.drawRect((int) hitbox.x - xLvlOffset, (int) hitbox.y, (int) hitbox.width, (int) hitbox.height);
        // cont. (int) hitbox.x - xLvlOffset, (int) hitbox.y, (int) hitbox.width, (int) hitbox.height
    }

    public int getObjType(){
        // TODO: return objType
        return objType;
    }

    public Rectangle2D.Float getHitbox(){
        // TODO: return hitbox
        return hitbox;
    }

    public boolean isActive(){
        // TODO: return active
        return active;
    }

    public void setActive(boolean active){
        // TODO: set this.active to active
        this.active = active;
    }

    public void setAnimation(boolean doAnimation){
        // TODO: set this.doAnimation to doAnimation
        this.doAnimation = doAnimation;
    }

    public int getxDrawOffset(){
        // TODO: return xDrawOffset
        return xDrawOffset;
    }

    public int getyDrawOffset(){
        // TODO: return yDrawOffset
        return yDrawOffset;
    }

    public int getAniIndex(){
        // TODO: return aniIndex
        return aniIndex;
    }

    public int getAniTick(){
        // TODO: return aniTick
        return aniTick;
    }
}