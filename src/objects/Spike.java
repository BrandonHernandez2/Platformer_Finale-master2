package objects;

import utilz.Constants;

public class Spike extends GameObject{
    public Spike(int x, int y, int objType) {
        // TODO: call super passing in x, y, and objType
        super(x, y, objType);

        // TODO: call initHitbox passing in 32, and 16
        initHitbox(32, 16);
        // TODO: set xDrawOffset to 0
        xDrawOffset = 0;
        // TODO: set yDrawOffset to (int) (Game.SCALE * 16)
        yDrawOffset = (int) (Constants.Game.SCALE * 16);
        // TODO: add yDrawOffset to hitbox.y
        hitbox.y += yDrawOffset;
    }
}
