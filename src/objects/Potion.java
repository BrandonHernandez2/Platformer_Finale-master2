package objects;

import utilz.Constants;

public class Potion extends GameObject {

    private float hoverOffset;
    private int maxHoverOffset, hoverDir = 1;

    public Potion(int x, int y, int objType) {
        super(x, y, objType);
        // TODO: set doAnimation to true
        doAnimation = true;
        // TODO: call initHitbox passing in 7 and 14
        initHitbox(7, 14);
        // TODO: assign (int) (3 * Game.SCALE) to xDrawOffset
        xDrawOffset = (int) (3 * Constants.Game.SCALE);
        // TODO: assign (int) (2 * Game.SCALE) to yDrawOffset
        yDrawOffset = (int) (2 * Constants.Game.SCALE);
        // TODO: assign (int) (10 * Game.SCALE) to maxHoverOffset
        maxHoverOffset = (int) (10 * Constants.Game.SCALE);
    }

    public void update(){
        // TODO: call updateAnimationTick()
        updateAnimationTick();
        // TODO: call updateHover()
        updateHover();
    }

    private void updateHover(){
        // TODO: add (0.075f * Game.SCALE * hoverDir) to hoverOffset
        hoverOffset += (0.075f * Constants.Game.SCALE * hoverDir);
        // if else block
        // TODO: check if hoverOffset is greater than or equal to maxHoverOffset
        if (hoverOffset >= maxHoverOffset) {
            // if so set hoverDir to -1
            hoverDir = -1;
        }else {
            // else set it to 1
            hoverDir = 1;
            // end of if else block
        }

        // TODO: set hitbox.y to y + hoverOffset
        hitbox.y = y + hoverOffset;
    }


}