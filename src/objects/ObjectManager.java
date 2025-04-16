package objects;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import entities.Player;
import gamestates.Playing;
import levels.Level;
import utilz.LoadSave;
import utilz.Constants;
import static utilz.Constants.ObjectConstants.*;
import static utilz.HelpMethods.CanCannonSeePlayer;
import static utilz.HelpMethods.IsProjectileHittingLevel;
import static utilz.Constants.Projectiles.*;

public class ObjectManager {

    Playing playing;
    Playing player;
    Playing Level;
    // TODO: add these fields.  note Playing, Player, and Level don't exist yet.
    private BufferedImage[][] potionImgs, containerImgs;
    private BufferedImage[] cannonImgs;
    private BufferedImage spikeImg, cannonBallImg;
    private ArrayList<Potion> potions;
    private ArrayList<GameContainer> containers;
    private ArrayList<Spike> spikes;
    private ArrayList<Cannon> cannons;
    private ArrayList<Projectile> projectiles = new ArrayList<>();

    public ObjectManager(Playing playing) {
        this.playing = playing;
        loadImgs();
    }

    public void checkSpikesTouched(Player p) {
        // TODO: loop through all spikes with a for (Spike s : spikes) loop
        for (Spike s : spikes){
        // start of for loop
        // TODO: check if s.getHitbox().intersects(p.getHitbox())
            if (s.getHitbox().intersects(p.getHitbox())) {
                // start of if statement
                // TODO: call p.kill() if so.
                p.kill();
                // end of if statement
            }
        // end of for loop
        }
    }

    public void checkObjectTouched(Rectangle2D.Float hitbox) {
        // TODO: loop through all of the potions like you did for spikes
        for (Potion p : potions) {
            // start of for loop
            // TODO: check if p.isActive()
            if (p.isActive()) {
                // start of if statement
                // TODO: check if the hitbox intersects with p's hitbox: like we did above
                if (p.getHitbox().intersects(p.getHitbox())) {
                    // start of if statement
                    // TODO: call p's setActive method passing in false
                    p.setActive(false);
                    // TODO: call applyEffectToPlayer passing in p
                    applyEffectToPlayer(p);
                    // end of if statement
                }
                // end of if statement
            }
            // end of loop
        }
    }

    public void applyEffectToPlayer(Potion p) {
        // TODO: simple if else statement each block has one line of code
        // TODO: if p.getObjType is the RED_POTION then
        if (p.getObjType() == RED_POTION) {
            // call playing.getPlayer()'s changeHealth method passing in RED_POTION_VALUE
            playing.getPlayer().changeHealth(RED_POTION_VALUE);
        }
        // otherwise the BLUE_POTION_VALUE
        else{
            playing.getPlayer().changeHealth(BLUE_POTION_VALUE);
        }
    }

    public void checkObjectHit(Rectangle2D.Float attackbox) {
        // TODO: loop through all of the GameContainer's :  starting to see a pattern here.
        for (GameContainer gc : containers) {
            // for start
            // TODO: check if gc.isActive and !gc.doAnimation
            if (gc.isActive() || !gc.doAnimation) {
                // if start
                // TODO: check if gc.getHitbox intersects with attackbox
                if (gc.getHitbox().intersects(attackbox)) {
                    // if start
                    // TODO: call gc's setAnimation method passing in true
                    gc.setAnimation(true);
                    // TODO: create an int called type and set to 0
                    int type = 0;
                    // TODO: check if gc.getObjType is BARREL
                    if (gc.getObjType() == BARREL) {
                        // if start
                        // TODO: set type to 1
                        type = 1;
                        // if end
                    }
                    // TODO: add a new Potion() to potions.  You'll pass the following arguments to the new Potion constructor
                    potions.add(new Potion((int) (gc.getHitbox().x + gc.getHitbox().width / 2), (int) (gc.getHitbox().y - gc.getHitbox().height / 2), type));
                    // arguments for new Potion()
                    //(int) (gc.getHitbox().x + gc.getHitbox().width / 2),

                    // (int) (gc.getHitbox().y - gc.getHitbox().height / 2),
                    // type)
                    // TODO: call return
                    return;
                    // end of if
                }
                // end of if
            }
            // end of for
        }
    }

    public void loadObjects(Level newLevel) {
        // TODO: set potions to a new ArrayList of postions passing in newLevel.getPotions()
        potions = new ArrayList<>(newLevel.getPotions());
        // TODO: same idea for contains
        containers = new ArrayList<>(newLevel.getContainers());
        // TODO: set spikes to newLevel.getSpikes()
        spikes = newLevel.getSpikes();
        // TODO: same idea for cannons
        cannons = newLevel.getCannons();
        // TODO: call projectiles clear method
        projectiles.clear();
    }

    private void loadImgs() {
        // TODO: hey look free stuff.
        BufferedImage potionSprite = LoadSave.GetSpriteAtlas(LoadSave.POTION_ATLAS);
        potionImgs = new BufferedImage[2][7];

        for (int j = 0; j < potionImgs.length; j++){
            for (int i = 0; i < potionImgs[j].length; i++){
                potionImgs[j][i] = potionSprite.getSubimage(12 * i, 16 * j, 12, 16);
            }
        }

        BufferedImage containerSprite = LoadSave.GetSpriteAtlas(LoadSave.CONTAINER_ATLAS);
        containerImgs = new BufferedImage[2][8];

        for (int j = 0; j < containerImgs.length; j++){
            for (int i = 0; i < containerImgs[j].length; i++){
                containerImgs[j][i] = containerSprite.getSubimage(40 * i, 30 * j, 40, 30);
            }
        }

        spikeImg = LoadSave.GetSpriteAtlas(LoadSave.TRAP_ATLAS);

        cannonImgs = new BufferedImage[7];
        BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.CANNON_ATLAS);

        for (int i = 0; i < cannonImgs.length; i++){
            cannonImgs[i] = temp.getSubimage(i * 40, 0, 40, 26);
        }

        cannonBallImg = LoadSave.GetSpriteAtlas(LoadSave.CANNON_BALL);

    }

    public void update(int[][] lvlData, Player player) {
        // TODO: loop through all of the potions, like you've done before.  use p as the loop variable.  check if its active.
        for (Potion p : potions) {
            if (p.isActive()) {
                // call p's update method if so.
                p.update();
                // end of if
            }
            // end of for
        }

        // TODO: repeat but use GameContainer gc : containers instead for the loop
        for (GameContainer gc : containers) {
            if (gc.isActive()){
                gc.update();
            // end of if
        }
            // end of for
        }

        // TODO: call updateCannons passing in lvlData, player
        updateCannons(lvlData, player);
        // TODO: same for updateProjectiles
        updateProjectiles(lvlData, player);
    }

    private void updateProjectiles(int[][] lvlData, Player player) {
        // TODO: hey look more free code
        for (Projectile p : projectiles){
            if (p.isActive()) {
                p.updatePos();
                if (p.getHitbox().intersects(player.getHitbox())) {
                    player.changeHealth(-25);
                    p.setActive(false);
                } else if (IsProjectileHittingLevel(p, lvlData)){
                    p.setActive(false);
                }
            }
        }
    }

    private boolean isPlayerInRange(Cannon c, Player player) {
        // TODO: assign (int) Math.abs(player.getHitbox().x - c.getHitbox().x) to an int named absValue
        int absValue = (int) Math.abs(player.getHitbox().x - c.getHitbox().x);
        // TODO: return whether or not absValue is less than or equal to Game.TILES_SIZE * 5
        return absValue <= Constants.Game.TILES_SIZE * 5;
    }

    private boolean isPlayerInfrontOfCannon(Cannon c, Player player) {
        // TODO: simple if, else if, else condition
        // for the if check c.getObjType is equals to CANNON_LEFT
        if (c.getObjType() == CANNON_LEFT){
        // inside this if check if c.getHitbox().x is greater than player's getHitbox x, then return true
            if (c.getHitbox().x > player.getHitbox().x){
                return true;
        // end of nested if
            }
        // for the else if check c.getHitbox.x less than player's getHitbox x, then return true
            else{
                if (c.getHitbox().x < player.getHitbox().x){
                    return true;
                }else {
                    return false;
                }
            }
        // for the else return false
    }

    private void updateCannons(int[][] lvlData, Player player){
            for (Cannon c : cannons) {
                // TODO: a for Cannon c: cannons loop followed by 5 nested ifs each doing nothing but calling
                for (Cannon c : cannons) {
                    // another if statement until the deepest one call's c.setAnimation() passing in true.
                    // TODO: 1st if check !c.doAnimation
                    if (!c.doAnimation) {
                        // TODO: 2nd if check c.getTileY() is equal to player.getTileY
                        if (c.getTileY() == player.getTileY()) {
                            // TODO: 3rd if check isPlayerInRange passing in c and player
                            if (isPlayerInRange(c, player)) {
                                // TODO: 4th if check isPlayerInfrontOfCannon passing in c and player
                                if (isPlayerInfrontOfCannon(c, player)) {
                                    // TODO: last if check CanCannonSeePlayer passing in lvlData, player.getHitbox, c.getHitbox, and c.getTileY
                                    if (CanCannonSeePlayer(lvlData, player.getHitbox(), c.getHitbox(), c.getTileY())) {
                                        // TODO:  sigh finally when you've reached this level of the underworld set call c.setAnimation passing in true.
                                        c.setAnimation(true);
                                        // end of if
                                    }
                                    // end of if
                                }
                                // end of if
                            }
                            // end of if

                            // TODO: gotcha call c.update
                            c.update();
                            if (c.getAniIndex() == 4 && c.getAniTick() == 0) {
                                shootCannon(c);
                            }
                            // end of if
                        }
                        // end of for
                    }
                }
            }
        }

    private void shootCannon(Cannon c) {
        // TODO: make an int called dir and set to 1
            int dir = 1;
        // TODO: check if c.getObjType is the CANNON_LEFT and set dir to -1 if it is.  all done with if
            if (c.getObjType() == CANNON_LEFT){
                dir = -1;
            }
        // add a new Projectile to projectiles.  new Projectile's args are
            projectiles.add(new Projectile((int) c.getHitbox().x, (int) c.getHitbox().y, dir));
        // (int) c.getHitbox().x, (int) c.getHitbox().y, dir
    }

    public void draw(Graphics g, int xLvlOffset) {
        // TODO: drawPotions, drawContainers, drawTraps, drawCannons, drawProjectiles.
            drawPotions(g, xLvlOffset);
            drawContainers(g, xLvlOffset);
            drawTraps(g, xLvlOffset);
            drawCannons(g, xLvlOffset);
            drawProjectiles(g, xLvlOffset);
        // TODO: all of these above calls take g and xLvlOffset.
    }


    // TODO: hey look again with the free code.
    private void drawProjectiles(Graphics g, int xLvlOffset) {
        for (Projectile p : projectiles)
            if (p.isActive())
                g.drawImage(cannonBallImg, (int) (p.getHitbox().x - xLvlOffset), (int) (p.getHitbox().y), CANNON_BALL_WIDTH, CANNON_BALL_HEIGHT, null);

    }

    private void drawCannons(Graphics g, int xLvlOffset) {
        for (Cannon c : cannons) {
            int x = (int) (c.getHitbox().x - xLvlOffset);
            int width = CANNON_WIDTH;

            if (c.getObjType() == CANNON_RIGHT) {
                x += width;
                width *= -1;
            }

            g.drawImage(cannonImgs[c.getAniIndex()], x, (int) (c.getHitbox().y), width, CANNON_HEIGHT, null);
        }

    }

    private void drawTraps(Graphics g, int xLvlOffset) {
        for (Spike s : spikes)
            g.drawImage(spikeImg, (int) (s.getHitbox().x - xLvlOffset), (int) (s.getHitbox().y - s.getyDrawOffset()), SPIKE_WIDTH, SPIKE_HEIGHT, null);

    }

    private void drawContainers(Graphics g, int xLvlOffset) {
        for (GameContainer gc : containers)
            if (gc.isActive()) {
                int type = 0;
                if (gc.getObjType() == BARREL)
                    type = 1;
                g.drawImage(containerImgs[type][gc.getAniIndex()], (int) (gc.getHitbox().x - gc.getxDrawOffset() - xLvlOffset), (int) (gc.getHitbox().y - gc.getyDrawOffset()), CONTAINER_WIDTH,
                        CONTAINER_HEIGHT, null);
            }
    }

    private void drawPotions(Graphics g, int xLvlOffset) {
        for (Potion p : potions)
            if (p.isActive()) {
                int type = 0;
                if (p.getObjType() == RED_POTION)
                    type = 1;
                g.drawImage(potionImgs[type][p.getAniIndex()], (int) (p.getHitbox().x - p.getxDrawOffset() - xLvlOffset), (int) (p.getHitbox().y - p.getyDrawOffset()), POTION_WIDTH, POTION_HEIGHT,
                        null);
            }
    }

    public void resetAllObjects() {
        // TODO: last not least call loadObjects passing in playing.getLevelManager().getCurrentLevel
        loadObjects(playing.getLevelManager().getCurrentLevel());
        // TODO: 3 for loops, with one line each.  for(Potion p : potions) call p.reset
        for (Potion p : potions){
            p.reset();
        }
        // TODO: repeate for containers, gc,
        for (GameContainer gc : containers){
            gc.reset();
        }
        // TODO: repeat for cannons, c
        for (Cannon c : cannons){
            c.reset();
        }

        for (Potion p : potions)
            p.reset();
        for (GameContainer gc : containers)
            gc.reset();
        for (Cannon c : cannons)
            c.reset();
    }
}