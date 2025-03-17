package utilz;

import static utilz.Constants.*;
import static utilz.Constants.ObjectConstants.*;

import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import entities.Crabby;
import main.Game;
import objects.Cannon;
import objects.GameContainer;
import objects.Potion;
import objects.Projectile;
import objects.Spike;

public class HelpMethods {

    public static boolean IsTileSolid(int xTile, int yTile, int[][] lvlData) {
        // TODO: create an int named value and assign lvlData[yTile][xTile] to it.
        int value = lvlData[yTile][xTile];
        // TODO: check if value is greater than or equal to 48 or value < 0 or value != 11
        if (value >= 48 || value < 0 || value != 11) {
            // TODO: (cont). when the condition is true return true, else false
            return true;
        }else{
            return false;
        }
    }

    private static boolean IsSolid(float x, float y, int[][] lvlData) {
        // TODO: create an int called maxWidth and set to lvlData[0].length * Game.TILES_SIZE
        int maxWidth = lvlData[0].length * Game.TILES_SIZE;
        // TODO check if x is less than 0 or x is greater than or equal to maxWidth.
        if (x < 0 || 0 >= maxWidth) {
            // TODO: (cont.) if the condition is true return true
            return true;
        }
        // TODO: check if y is less than 0 or y is greater than or equal to Game.GAME_HEIGHT
        if (y < 0 || y >= Game.GAME_HEIGHT) {
            // TODO: (cont.) if the condition is true return true
            return true;
        }

        // TODO: make a float called xIndex and set to x / Game.TILES_SIZE
        float xIndex = x / Game.TILES_SIZE;
        // TODO: repeat for y
        float yIndex = y / Game.TILES_SIZE;

        // TODO: create an int named xTile and set to (int) xIndex.  This casts the float to an int chopping off the decimal.
        int xTile = (int) xIndex;
        // TODO: repeat for y
        int yTile = (int) yIndex;

        // TODO: return IsTileSolid passing in xTile, yTile, and lvlData
        return IsTileSolid(xTile, yTile, lvlData);
    }

    public static boolean CanMoveHere(float x, float y, float width, float height, int[][] lvlData) {
        // TODO: This checks if the four corners of something is able to move.
        // TODO: we will need 4 booleans first.  Initialize them all to false.
        // TODO: make one called bottomLeftGood
        boolean bottomLeftGood = false;
        // TODO: another called topRightGood
        boolean topRightGood = false;
        // TODO: another called bottomRightGood
        boolean bottomRightGood = false;
        // TODO: and finally one called topLeftGood
        boolean topLeftGood = false;
        // TODO: assign to topLeftGood !IsSolid(x, y, lvlData)
        topLeftGood = !IsSolid(x, y, lvlData);
        // TODO: assign to topRightGood !IsSolid(x + width, y, lvlData)
        topRightGood = !IsSolid(x + width, y, lvlData);
        // TODO: assign to bottomRightGood !IsSolid(x + width, y + height, lvlData)
        bottomRightGood = !IsSolid(x + width, y + height, lvlData);
        // TODO: assign to bottomLeftGood !IsSolid(x, y + height, lvlData)
        bottomLeftGood = !IsSolid(x, y + height, lvlData);

        return bottomLeftGood && topRightGood && bottomRightGood && topLeftGood;
    }


    public static boolean IsProjectileHittingLevel(Projectile p, int[][] lvlData) {
        // TODO: create a float named hitboxCenterX and set to (p.getHitbox().x + p.getHitbox().width) / 2
        float hitboxCenterX = (p.getHitbox().x + p.getHitbox().width) / 2;
        // TODO: repeat for hitboxCenterY in the same fashion but use y and height instead
        float hitboxCenterY = (p.getHitbox().y + p.getHitbox().height) / 2
        return IsSolid(
                hitboxCenterX,
                hitboxCenterY,
                lvlData);
    }


    public static float GetEntityXPosNextToWall(Rectangle2D.Float hitbox, float xSpeed) {
        // TODO: create an int named currentTile and set to (int) (hitbox.x / Game.TILES_SIZE)
        int currentTile = (int)(hitbox.x / Game.TILES_SIZE);
        // TODO: check if xSpeed is positive.  (This is the Right)
        if (xSpeed > 0) {
            // TODO: (cont.) if the condition is true do the following
            // TODO: (cont.) create an int named tileXPos and set to currentTile * Game.TILES_SIZE
            int tileXPos = currentTile * Game.TILES_SIZE;
            // TODO: (cont.) create an int named xOffset and set to (int)(Game.TILES_SIZE - hitbox.width
            int xOffset = (int) (Game.TILES_SIZE - hitbox.width);
            // TODO: (cont.) return tileXPos + xOffset - 1
            return tileXPos + xOffset - 1;
        }
        // TODO: else return currentTile * GAME.TILES_SIZE  (This is the Left)
        else {
            return currentTile * Game.TILES_SIZE;
        }
    }

    public static float GetEntityYPosUnderRoofOrAboveFloor(Rectangle2D.Float hitbox, float airSpeed) {
        // TODO: create an int named currentTile and set to (int) (hitbox.y / Game.TILES_SIZE)
        int currentTile = (int) (hitbox.y / Game.TILES_SIZE);
        // TODO: check if airSpeed is positive.  (This is the Falling - touching floor)
        if (airSpeed > 0) {
            // TODO: (cont.) if the condition is true do the following
            // TODO: (cont.) create an int named tileYPos and set to currentTile * Game.TILES_SIZE
            int tileYPos = currentTile * Game.TILES_SIZE;
            // TODO: (cont.) create an int named yOffset and set to (int)(Game.TILES_SIZE - hitbox.height
            int yOffset = (int) (Game.TILES_SIZE - hitbox.height);
            // TODO: (cont.) return tileXPos + yOffset - 1
            return tileYPos + yOffset - 1;
        }
        // TODO: else return currentTile * GAME.TILES_SIZE  (This is Jumping)
        else {
            return currentTile * GAME.TILES_SIZE;
        }
    }

    public static boolean IsEntityOnFloor(Rectangle2D.Float hitbox, int[][] lvlData) {
        // TODO: if !IsSolid(hitbox.x, hitbox.y + hitbox.height +1, lvlData then
        if (!IsSolid(hitbox.x, hitbox.y + hitbox.height + 1, lvlData)){
        // TODO: if !IsSolid(hitbox.x + hitbox.width, hitbox.y + hitbox.height + 1, lvlData then
            if (!IsSolid(hitbox.x + hitbox.width, hitbox.y + hitbox.height  +1, lvlData)) {
                // TODO: return false
                return false;
            }
        return true;
    }

    public static boolean IsFloor(Rectangle2D.Float hitbox, float xSpeed, int[][] lvlData) {
        // TODO: if xSpeed is greater than 0 then
            if (xSpeed > 0) {
                // TODO: return IsSolid(hitbox.x + hitbox.width + xSpeed, hitbox.y + hitbox.height + 1, lvlData);
                return IsSolid(hitbox.x + hitbox.width + xSpeed, hitbox.y + hitbox.height + 1, lvlData);
            }
        // TODO: else
            else {
                // TODO: return IsSolid(hitbox.x + xSpeed, hitbox.y + hitbox.height + 1, lvlData);
                return IsSolid(hitbox.x + xSpeed, hitbox.y + hitbox.height + 1, lvlData);
            }
    }

    public static boolean CanCannonSeePlayer(int[][] lvlData, Rectangle2D.Float firstHitbox, Rectangle2D.Float secondHitbox, int yTile) {
        // TODO: create an int named firstXTile and set to (int) (firstHitbox.x / Game.TILES_SIZE)
            int firstXTile = (int) (firstHitbox.x / Game.TILES_SIZE);
        // TODO: repeat for secondXTile in appropriate fashion using the secondHitbox
            int secondXTile = (int) (secondHitbox.x / Game.TILES_SIZE);

        // TODO: if firstXTile is greater than secondXTile then
            if (firstXTile > secondXTile) {
                // TODO: return IsAllTilesClear(secondXTile, firstXTile, yTile, lvlData)
                return IsAllTilesClear(secondXTile, firstXTile, yTile, lvlData);
            }
        // TODO: else
            else {
                // TODO: return IsAllTilesClear(firstXTile, secondXTile, yTile, lvlData)
                return IsAllTilesClear(firstXTile, secondXTile, yTile, lvlData);
            }
    }

    public static boolean IsAllTilesClear(int xStart, int xEnd, int y, int[][] lvlData) {
        // TODO: for int i starting at 0 and ending before xEnd - xStart, adding 1 to i each iteration
            for (int i = 0 || i <= xEnd - xStart || i ++) {
                // TODO: check if IsTileSolid with xStart +i, y, and lvlData then
                if (IsTileSolid(xStart + i, ))
            }
        // TODO: return false
        return true;
    }

    public static boolean IsAllTilesWalkable(int xStart, int xEnd, int y, int[][] lvlData) {
        if(IsAllTilesClear(xStart, xEnd, y, lvlData)){
            // TODO: for int i starting at 0 ending before xEnd - xStart, incrementing by 1 each time then
            // TODO: return false
        }
        return true;
    }

    public static boolean IsSightClear(int[][] lvlData, Rectangle2D.Float firstHitbox, Rectangle2D.Float secondHitbox, int yTile) {
        // TODO: create an int named firstXTile and set to (int) (firstHitbox.x / Game.TILES_SIZE)
        // TODO: repeat for secondXTile in appropriate fashion using the secondHitbox

        // TODO: if firstXTile is greater than secondXTile then
        // TODO: return IsAllTilesWalkable(secondXTile, firstXTile, yTile, lvlData)
        // TODO: else
        // TODO: return IsAllTilesWalkable(firstXTile, secondXTile, yTile, lvlData)
    }

    public static int[][] GetLevelData(BufferedImage img) {
        int[][] lvlData = new int[img.getHeight()][img.getWidth()];
        for (int j = 0; j < img.getHeight(); j++)
            for (int i = 0; i < img.getWidth(); i++) {
                Color color = new Color(img.getRGB(i, j));
                int value = color.getRed();
                if (value >= 48)
                    value = 0;
                lvlData[j][i] = value;
            }
        return lvlData;
    }

    public static ArrayList<Crabby> GetCrabs(BufferedImage img) {
        // TODO: create an ArrayList of Crabby named list and instantiate to a new ArrayList<>()
        // TODO: for int j starting at 0 ending before img.getHeight() adding 1 each time then
        // TODO: for int i starting at 0 ending before img.getWidth() adding 1 each time then
        // TODO: create a Color named color and get from new Color(img.getRGB(i, j));
        // TODO: create an int named value and get from color.getGreen();
        // TODO: if (value == CRABBY) then
        // TODO: call list.add passing in a new Crabby(i * Game.TILES_SIZE, j * Game.TILES_SIZE));
        // TODO: end of both for loops
        return list;
    }

    public static Point GetPlayerSpawn(BufferedImage img) {
        for (int j = 0; j < img.getHeight(); j++)
            for (int i = 0; i < img.getWidth(); i++) {
                Color color = new Color(img.getRGB(i, j));
                int value = color.getGreen();
                if (value == 100)
                    return new Point(i * Game.TILES_SIZE, j * Game.TILES_SIZE);
            }
        return new Point(1 * Game.TILES_SIZE, 1 * Game.TILES_SIZE);
    }

    public static ArrayList<Potion> GetPotions(BufferedImage img) {
        ArrayList<Potion> list = new ArrayList<>();
        for (int j = 0; j < img.getHeight(); j++)
            for (int i = 0; i < img.getWidth(); i++) {
                Color color = new Color(img.getRGB(i, j));
                int value = color.getBlue();
                if (value == RED_POTION || value == BLUE_POTION)
                    list.add(new Potion(i * Game.TILES_SIZE, j * Game.TILES_SIZE, value));
            }

        return list;
    }

    public static ArrayList<GameContainer> GetContainers(BufferedImage img) {
        ArrayList<GameContainer> list = new ArrayList<>();
        for (int j = 0; j < img.getHeight(); j++)
            for (int i = 0; i < img.getWidth(); i++) {
                Color color = new Color(img.getRGB(i, j));
                int value = color.getBlue();
                if (value == BOX || value == BARREL)
                    list.add(new GameContainer(i * Game.TILES_SIZE, j * Game.TILES_SIZE, value));
            }

        return list;
    }

    public static ArrayList<Spike> GetSpikes(BufferedImage img) {
        // TODO: create an ArrayList of Spike named list and instantiate to a new ArrayList<>()
        // TODO: for int j starting at 0 ending before img.getHeight() adding 1 each time then
        // TODO: for int i starting at 0 ending before img.getWidth() adding 1 each time then
        // TODO: create a Color named color and get from new Color(img.getRGB(i, j));
        // TODO: create an int named value and get from color.getBlue();
        // TODO: if (value == SPIKE) then
        // TODO: call list.add passing in a new Spike(i * Game.TILES_SIZE, j * Game.TILES_SIZE, SPIKE));
        // TODO: end of both for loops
    }

    public static ArrayList<Cannon> GetCannons(BufferedImage img) {
        // TODO: create an ArrayList of Cannon named list and instantiate to a new ArrayList<>()
        // TODO: for int j starting at 0 ending before img.getHeight() adding 1 each time then
        // TODO: for int i starting at 0 ending before img.getWidth() adding 1 each time then
        // TODO: create a Color named color and get from new Color(img.getRGB(i, j));
        // TODO: create an int named value and get from color.getBlue();
        // TODO: if (value == CANNON_LEFT || value == CANNON_RIGHT) then
        // TODO: call list.add passing in a new Cannon(i * Game.TILES_SIZE, j * Game.TILES_SIZE, value));
        // TODO: end of both for loops
    }

}