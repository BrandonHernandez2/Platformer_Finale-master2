package utilz;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;

import javax.imageio.ImageIO;

public class LoadSave {

    public static final String PLAYER_ATLAS = "player_sprites.png";
    public static final String LEVEL_ATLAS = "outside_sprites.png";
    public static final String MENU_BUTTONS = "button_atlas.png";
    public static final String MENU_BACKGROUND = "menu_background.png";
    public static final String PAUSE_BACKGROUND = "pause_menu.png";
    public static final String SOUND_BUTTONS = "sound_button.png";
    public static final String URM_BUTTONS = "urm_buttons.png";
    public static final String VOLUME_BUTTONS = "volume_buttons.png";
    public static final String MENU_BACKGROUND_IMG = "background_menu.png";
    public static final String PLAYING_BG_IMG = "playing_bg_img.png";
    public static final String BIG_CLOUDS = "big_clouds.png";
    public static final String SMALL_CLOUDS = "small_clouds.png";
    public static final String CRABBY_SPRITE = "crabby_sprite.png";
    public static final String STATUS_BAR = "health_power_bar.png";
    public static final String COMPLETED_IMG = "completed_sprite.png";
    public static final String POTION_ATLAS = "potions_sprites.png";
    public static final String CONTAINER_ATLAS = "objects_sprites.png";
    public static final String TRAP_ATLAS = "trap_atlas.png";
    public static final String CANNON_ATLAS = "cannon_atlas.png";
    public static final String CANNON_BALL = "ball.png";
    public static final String DEATH_SCREEN = "death_screen.png";
    public static final String OPTIONS_MENU = "options_background.png";

    public static BufferedImage GetSpriteAtlas(String fileName) {
        // TODO: create a BufferedImage variable named img and set to null
        // TODO: create an InputStream variable named is and set to LoadSave.class.getResourceAsStream("/" + filename)
        try {
            // TODO: set img to ImageIO.read(is)
        } catch (IOException e) {
            // TODO: call e.printStackTrace()
        } finally {
            try {
                // TODO: call is.close()
            } catch (IOException e) {
                // TODO: call e.printStackTrace()
            }
        }

        return img;
    }

    public static BufferedImage[] GetAllLevels() {
        // TODO: create a URL named url and assign LoadSave.class.getResource("/lvls") to it.
        // TODO: create a File named file and assign null to it.

        try{
            // TODO: assign newFile(url.toURI()) to file
        } catch (URISyntaxException e) {
            // TODO: call e.printStackTrace()
        }

        // TODO: make a File array named files and assign files.listFiles() to it
        // TOOD: repeat for filesSorted and assign new File[files.length] to it.

        for (int i = 0; i < filesSorted.length; i++)
            for (int j = 0; j < files.length; j++) {
                // TODO: if files[j].getName().equals((i + 1) + ".png") then set 
                // TODO: (cont.) filesSorted[i] to files[j]
            }
        
        // TODO: create a BufferedImage array named imgs and initialize to a new BufferedImage passing in the length of filesSorted

        for (int i = 0; i < imgs.length; i++)
            try {
                // TODO: set imgs[i] to ImageIO.read(filesSorted[i])
            } catch (IOException e) {
                // TODO: call e.printStackTrace();
            }

        return imgs;
    }

}