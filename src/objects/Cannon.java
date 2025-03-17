package objects;

import main.Game;

public class Cannon extends GameObject {

    private int tileY;

    public Cannon(int x, int y, int objType) {
        // TODO: call super passing in x, y, objType
        super(x, y, objType);
        // TODO: set tileY to y / Game.TILES_SIZE
        tileY = y / Game.TILES_SIZE;
        // TODO: call initHitbox() passing in 40, 26
        initHitbox(40, 26);
        // TODO: subtract (int)(4 * Game.SCALE) from hitbox.x
        hitbox - (int)(4 * Game.SCALE);
        // TODO: add (int)(6 * Game.SCALE) to hitbox.y
        hitbox.y + (int)(6 * Game.SCALE);
    }

    public void update(){
        // TODO: check if doAnimation is true and if so updateAnimationTick()
        if (doAnimation = true){
            updateAnimationTick();
        }
    }

    public int getTileY() {
        // TODO: return tileY
        return tileY;
    }
}