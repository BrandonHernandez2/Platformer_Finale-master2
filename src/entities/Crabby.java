package entities;

import static utilz.Constants.EnemyConstants.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

import static utilz.Constants.Directions.*;

import main.Game;
import utilz.Constants;

public class Crabby extends Enemy {

    private int attackBoxOffsetX;

    public Crabby(float x, float y) {
        super(x, y, CRABBY_WIDTH, CRABBY_HEIGHT, CRABBY);
        // TODO: call initHitbox() passing in 22, and 19
        initHitbox(22, 19);
        // TODO: call initAttackBox()
        initAttackBox();
    }

    private void initAttackBox() {
        // TODO: assign a new Rectangle2D.Float() with arguments x, y, (int)(82 * Game.SCALE), (int)(19 * Game.SCALE) to attackBox
        attackBox = new Rectangle2D.Float(x, y, (int) (82 * Constants.Game.SCALE), (int)(19 * Constants.Game.SCALE));
        // TODO: assign (int) (Game.SCALE * 30) to attackBoxOffsetX
        attackBoxOffsetX = (int) (Constants.Game.SCALE * 30);
    }

    public void update(int[][] lvlData, Player player) {
        // TODO: call updateBehavior() passing in lvlData and player
        updateBehavior(lvlData, player);
        // TODO: call updateAnimationTick()
        updateAnimationTick();
        // TODO: call updateAttackBox()
        updateAttackBox();
    }

    private void updateAttackBox() {
        // TODO: assign hitbox.x - attackBoxOffsetX to attackBox.x
        attackBox.x = hitbox.x - attackBoxOffsetX;
        // TODO: assign hitbox.y to attackBox.y
        attackBox.y = hitbox.y;
    }

    private void updateBehavior(int[][] lvlData, Player player) {
        // TODO: check if firstUpdate
        if (firstUpdate) {
            // if block begin
            // TODO: call firstUpdateCheck() passing in lvlData
            firstUpdateCheck(lvlData);
            // end of if block
        }
        // TODO: check if inAir
        if (inAir) {
            // if block begin
            // TODO: call updateInAir() passing in lvlData
            updateInAir(lvlData);
            // end of if block
        }
        // else block begin
        else {
            switch (state) {
                case IDLE:
                    newState(RUNNING);
                    break;
                case RUNNING:
                    if (canSeePlayer(lvlData, player)) {
                        turnTowardsPlayer(player);
                        if (isPlayerCloseForAttack(player))
                            newState(ATTACK);
                    }

                    move(lvlData);
                    break;
                case ATTACK:
                    if (aniIndex == 0)
                        attackChecked = false;
                    if (aniIndex == 3 && !attackChecked)
                        checkPlayerHit(attackBox, player);
                    break;
                case HIT:
                    break;
            }
            // else block end
        }
    }

    public int flipX() {
        // TODO: check if walkDir is equal to RIGHT
        if (walkDir == RIGHT) {
            // if block begin
            // TODO: return width;
            return width;
            // end of if block
        }
        // else block begin
        else {
            // TODO: return 0
            return 0;
            // else block end
        }
    }

    public int flipW() {
        // TODO: check if walkDir is equal to RIGHT
        if (walkDir == RIGHT) {
            // if block begin
            // TODO: return -1;
            return -1;
            // end of if block
        }
        // else block begin
        else {
            // TODO: return 1
            return 1;
            // else block end
        }
    }
}