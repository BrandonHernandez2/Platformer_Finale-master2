package entities;

import utilz.Constants.Game;

import java.awt.geom.Rectangle2D;

import static utilz.Constants.ANI_SPEED;
import static utilz.Constants.Directions.LEFT;
import static utilz.Constants.Directions.RIGHT;
import static utilz.Constants.EnemyConstants.*;
import static utilz.Constants.GRAVITY;
import static utilz.HelpMethods.*;

public abstract class Enemy extends Entity {
    protected int enemyType;
    protected boolean firstUpdate = true;
    protected int walkDir = LEFT;
    protected int tileY;
    protected float attackDistance = Game.TILES_SIZE;
    protected boolean active = true;
    protected boolean attackChecked;

    public Enemy(float x, float y, int width, int height, int enemyType) {
        super(x, y, width, height);
        // TODO: assign enemyType to this.enemyType
        enemyType = this.enemyType;
        // TODO: assign to maxHealth the value from GetMaxHealth(enemyType)
        maxHealth =GetMaxHealth(enemyType);
        // TODO: assign to currentHealth the value of maxHealth
        currentHealth = maxHealth;
        // TODO: assign to walkSpeed the value of Game.SCALE * 0.35f;
        walkSpeed = (float) (Game.SCALE * 0.35);

    }

    protected void firstUpdateCheck(int[][] lvlData) {
        // TODO: check if !EntityOnFloor() passing in hitbox and lvlData
        if (!IsEntityOnFloor(hitbox, lvlData)) {
            // if block start
            // TODO: set inAir to true
            inAir = true;
            // end of if block
            // TODO: set firstUpdate to false
            firstUpdate = false;
        }
    }


    protected void updateInAir(int[][] lvlData) {
        // TODO: check if CanMoveHere() passing in hitbox.x, hitbox.y + airSpeed, hitbox.width, hitbox.height, and lvlData
        if (CanMoveHere(hitbox.x, hitbox.y + airSpeed, hitbox.width, hitbox.height, lvlData)) {
            // if block start
            // TODO: add airSpeed to the value of hitbox.y
            hitbox.y += airSpeed;
            // TODO: add GRAVITY to the value of airSpeed
            airSpeed += GRAVITY;
            // if block end
        }else {
            // else block start
            // TODO: set inAir to false
            inAir = false;
            // TODO: set hitbox.y to GetEntityYPosUnderRoofOrAboveFloor() passing in hitbox and airSpeed
            hitbox.y = GetEntityYPosUnderRoofOrAboveFloor(hitbox, airSpeed);
            // TODO: set tileY to (int) (hitbox.y / Game.TILES_SIZE)
            tileY = (int) (hitbox.y / Game.TILES_SIZE);
            // end of else block
        }
    }

    protected void move(int[][] lvlData) {
        // TODO: make a float named xSpeed and set to 0
        float xSpeed = 0;
        // TODO: check if walkDir is equal to LEFT
        if (walkDir == LEFT) {
            // if block start
            // TODO: set xSpeed to -walkSpeed
            xSpeed = -walkSpeed;
            // end of if block
        }
        // else block start
        else {
            // TODO: set xSpeed to walkSpeed
            xSpeed = walkSpeed;
            // else block end
        }

        // TODO: check if CanMoveHere() passing in hitbox.x + xSpeed, hitbox.y, hitbox.width, hitbox.height, and lvlData
        if (CanMoveHere(hitbox.x + xSpeed, hitbox.y, hitbox.width, hitbox.height,lvlData)) {
            // if block start
            // TODO: check if IsFloor() passing in hitbox, xSpeed, and lvlData
            if (IsFloor(hitbox, xSpeed, lvlData)) {
                // if block start
                // TODO: add xSpeed to hitbox.x
                hitbox.x += xSpeed;
                // TODO: return
                return;
                // end of if block
            }
            // end of if block
        // TODO: call changeWalkDir()
            changeWalkDir();
        }
    }

    protected void turnTowardsPlayer(Player player) {
        // TODO: check if player.hitbox.x is greater than hitbox.x
        if (player.hitbox.x > hitbox.x) {
            // if block start
            // TODO: set walkDir to RIGHT
            walkDir = RIGHT;
            // end of if block
        }else {
            // else block start
            // TODO: set walkDir to LEFT
            walkDir = LEFT;
            // end of else block
        }
    }

    protected boolean canSeePlayer(int[][] lvlData, Player player) {
        // TODO: make an int called playerTileY and set to (int) (player.getHitbox().y / Game.TILES_SIZE
        int playerTileY = (int) (player.getHitbox().y / Game.TILES_SIZE);
        // TODO: check if playerTileY is equal to tileY
        if (playerTileY == tileY) {
            // if block begin
            // TODO: check if isPlayerInRange() passing in player
            if (isPlayerInRange(player)) {
                // if block begin
                // TODO: check if IsSightClear() passing in lvlData, hitbox, player.hitbox, and tileY
                if (IsSightClear(lvlData, hitbox, player.hitbox, tileY)) {
                    // if block begin
                    // TODO: return true
                    return true;
                    // if block end
                }
                // if block end
            }
            // if block end
        }
        return false;
    }

    protected boolean isPlayerInRange(Player player) {
        // TODO: make an int called absValue and set to (int) Math.abs(player.hitbox.x - hitbox.x)
        int absValue = (int) Math.abs(player.hitbox.x - hitbox.x);
        // TODO: return whether then absValue is less than or equal to attackDistance * 5
        return absValue <= attackDistance * 5;
    }

    protected boolean isPlayerCloseForAttack(Player player) {
        // TODO: make an int called absValue and set to (int) Math.abs(player.hitbox.x - hitbox.x)
        int absValue = (int) Math.abs(player.hitbox.x - hitbox.x);
        // TODO: return whether absValue is less than or equal to attackDistance
        return absValue <= attackDistance;
    }

    protected void newState(int enemyState) {
        // TODO: set this.state to enemyState
        this.state = enemyState;
        // TODO: set aniTick to 0
        aniTick = 0;
        // TODO: set aniIndex to 0
        aniIndex = 0;
    }

    public void hurt(int amount) {
        // TODO: subtract amount from currentHealth
        currentHealth -= amount;
        // TODO: check if currentHealth is less than or equal to 0
        if (currentHealth <= 0) {
            // if block begin
            // TODO: call newState() passing in DEAD
            newState(DEAD);
            // if block end
        }
        // else block begin
        else {
            // TODO: call newState() passing in HIT
            newState(HIT);
            // else block end
        }
    }

    protected void checkPlayerHit(Rectangle2D.Float attackBox, Player player) {
        // TODO: check if attackBox.intersects(player.hitbox)
        if (attackBox.intersects(player.hitbox)) {
            // if block begin
            // TODO: call player.changeHealth() passing in -GetEnemyDmg(enemyType)
            player.changeHealth(-GetEnemyDmg(enemyType));
            // if block end
            // TODO: set attackChecked to true
            attackChecked = true;
        }
    }

    protected void updateAnimationTick() {
        // TODO: add 1 to aniTick
        aniTick++;
        // TODO: check if aniTick is greater than or equal to ANI_SPEED
        if (aniTick >= ANI_SPEED) {
            // if block begin
            // TODO: set aniTick to 0
            aniTick = 0;
            // TODO: add 1 to aniIndex
            aniIndex++;
            // TODO: check if aniIndex is greater than or equal to GetSpriteAmount(enemyType, state)
            if (aniIndex >= GetSpriteAmount(enemyType, state)) {
                // if block begin
                // TODO: set aniIndex to 0
                aniIndex = 0;
                switch (state) {
                    case ATTACK, HIT -> state = IDLE;
                    case DEAD -> active = false;
                }
                // end of if block
            }
            // end of if block
        }
    }

    protected void changeWalkDir() {
        // TODO: check if walkDir is equal to LEFT
        if (walkDir == LEFT){
        // if block begin
        // TODO: set walkDir to RIGHT
            walkDir = RIGHT;
        // end of if block
            }else {
            // else block begin
            // TODO: set walkDir to LEFT
            walkDir = LEFT;
            // end of else block
        }
    }

    public void resetEnemy() {
        // TODO: set hitbox.x to x
        hitbox.x = x;
        // TODO: set hitbox.y to y
        hitbox.y = y;
        // TODO: set firstUpdate to true
        firstUpdate = true;
        // TODO: set currentHealth to maxHealth
        currentHealth = maxHealth;
        // TODO: call newState() passing in IDLE
        newState(IDLE);
        // TODO: set active to true
        active = true;
        // TODO: set airSpeed to 0
        airSpeed = 0;
    }


    public boolean isActive() {
        return active;
    }

}