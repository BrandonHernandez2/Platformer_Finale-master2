
package objects;

import utilz.Constants;

import javax.swing.*;

import static utilz.Constants.ObjectConstants.BOX;

public class GameContainer extends GameObject{

    public GameContainer(int x, int y, int objType) {
        super(x, y, objType);
        // TODO: call super passing in x, y, objType
        // TODO: call createHitbox()
        createHitbox();
    }

    private void createHitbox(){
        // TODO: if else statement here
        // check if objType is a box
        if (objType == BOX) {
            // TODO: if so call initHitbox(25, 18) then
            initHitbox(25, 18);
            // TODO: set xDrawOffset to (int) (7 * Game.SCALE)
            xDrawOffset = (int) (7 * Constants.Game.SCALE);
            // TODO: set yDrawOffset to (int) (12 * Game.SCALE)
            yDrawOffset = (int) (12 * Constants.Game.SCALE);
        }else {
            // else
            // TODO: call initHitbox(23, 25) then
            initHitbox(23, 25);
            // TODO: set xDrawOffset to (int) (8 * Game.SCALE)
            xDrawOffset = (int) (8 * Constants.Game.SCALE);
            // TODO: set yDrawOffset to (int) (5 * Game.SCALE)
            yDrawOffset = (int) (5 * Constants.Game.SCALE);
        }
        // end of if else block
        // TODO: add yDrawOffset + (int) (Game.SCALE * 2) to hitbox.y
        hitbox.y += yDrawOffset + (int) (Constants.Game.SCALE * 2);
        // TODO: add xDrawOffset / 2 to hitbox.x
        hitbox.x += xDrawOffset / 2;
    }

    public void update(){
        // TODO: check if doAnimation is true and if so updateAnimationTick()
        if (doAnimation == true){
            updateAnimationTick();
        }
    }

}
