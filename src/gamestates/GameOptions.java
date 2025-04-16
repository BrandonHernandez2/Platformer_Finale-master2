package gamestates;

import main.Game;
import ui.AudioOptions;
import ui.PauseButton;
import ui.UrmButton;
import utilz.Constants;
import utilz.LoadSave;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import static utilz.Constants.UI.URMButtons.URM_SIZE;

public class GameOptions extends State implements Statemethods {

	private AudioOptions audioOptions;
	private BufferedImage backgroundImg, optionsBackgroundImg;
	private int bgX, bgY, bgW, bgH;
	private UrmButton menuB;

	public GameOptions(Game game) {
		super(game);
		// TODO: call loadImgs()
		loadImgs();
		// TODO: call loadButton()
		loadButton();
		// TODO: set audioOptions to game.getAudioOptions();
		audioOptions = game.getAudioOptions();
	}

	private void loadButton() {
		// TODO: create an int named menuX and set to (int)(387 * Game.SCALE)
		int menuX = ((int)(387 * Constants.Game.SCALE));
		// TODO: repeate for menuY and set to (int)(325 * Game.SCALE)
		int menuY = (int)(325 * Constants.Game.SCALE);
		// TODO: set menuB to a new UrmButton() passing in menuX, menuY, URM_SIZE, URM_SIZE, 2)
		menuB = new UrmButton(menuX, menuY, URM_SIZE, URM_SIZE, 2);
	}

	private void loadImgs() {
		// TODO: set backgroundImg to LoadSave.GetSpriteAtlas(LoadSave.MENU_BACKGROUND_IMG)
		backgroundImg = LoadSave.GetSpriteAtlas(LoadSave.MENU_BACKGROUND_IMG);
		// TODO: set optionsBackgroundImg to LoadSave.GetSpriteAtlas(LoadSave.OPTIONS_MENU)
		optionsBackgroundImg = LoadSave.GetSpriteAtlas(LoadSave.OPTIONS_MENU);
		// TODO: set bgW to (int) (optionsBackgroundImg.getWidth() * Game.SCALE)
		bgW = (int) (optionsBackgroundImg.getWidth() * Constants.Game.SCALE);
		// TODO: set bgH to (int) (optionsBackgroundImg.getHeight() * Game.SCALE)
		bgH = (int) (optionsBackgroundImg.getHeight() * Constants.Game.SCALE);
		// TODO: set bgX to Game.GAME_WIDTH / 2 - bgW / 2
		bgX = Constants.Game.GAME_WIDTH / 2 - bgW / 2;
		// TODO: set bgY to (int) (33 * Game.SCALE)
		bgY = (int) (33 * Constants.Game.SCALE);
	}

	@Override
	public void update() {
		// TODO: call menuB.update()
		menuB.update();
		// TODO: call audioOptions.update()
		audioOptions.update();

	}

	@Override
	public void draw(Graphics g) {
		// TODO: call g.drawImage() passing in backgroundImg, 0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT, null)
		g.drawImage(backgroundImg, 0, 0, Constants.Game.GAME_WIDTH, Constants.Game.GAME_HEIGHT, null);
		// TODO: call g.drawImage() passing in optionsBackgroundImg, bgX, bgY, bgW, bgH, null
		g.drawImage(optionsBackgroundImg, bgX, bgY, bgW, bgH, null);
		// TODO: call menuB.draw() passing in g
		menuB.draw(g);
		// TODO: call audioOptions.draw() passing in g.
		audioOptions.draw(g);

	}

	public void mouseDragged(MouseEvent e) {
		// TODO: call audioOptions.mouseDragged() passing in e
		audioOptions.mouseDragged(e);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO: check if isIn(e, menuB)
		if (isIn(e, menuB)) {
			// if statement block start
			// TODO: call menu.setMousePressed() passing in true
			menuB.setMousePressed(true);
			// end of if block
		}
		// else statement block start
		else {
			// TODO: call audioOptions.mousePressed() passing in e
			audioOptions.mousePressed(e);
			// end of else block
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO: check if IsIn(e, menuB)
		if (isIn(e, menuB)) {
			// if statement block start
			// TODO: check if menuB.isMousePressed()
			if (menuB.isMousePressed()) {
				// if statement block start
				// TODO: set Gamestate.state to Gamestate.MENU
				Gamestate.state = Gamestate.MENU;
				// end of if block
			}
			// end of if block
		}
		// else statement block start
		else {
			// TODO: call audioOptions.mouseReleased() passing in e
			audioOptions.mouseReleased(e);
			// end of else block
		}
		// TODO: call menuB.resetBools()
		menuB.resetBools();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO: call menuB.setMouseOver() passing in false
		menuB.setMouseOver(false);
		// TODO: check if IsIn(e, menuB)
		if (isIn(e, menuB)) {
			// if statement block start
			// TODO: call menuB.setMouseOver() passing true
			menuB.setMouseOver(true);
			// end of if block
		}
		// else statement block start
		else {
			// TODO: call audioOptions.mouseMoved() passing in e
			audioOptions.mouseMoved(e);
			// end of else block
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO: check if e.getKeyCode() is equal to KeyEvent.VK_ESCAPE
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			// if statement block start
			// TODO: set Gamestate.state to Gamestate.MENU
			Gamestate.state = Gamestate.MENU;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO: nothing in this method.  It must exist, but it does nothing

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO: nothing in this method.  It must exist, but it does nothing
	}

	private boolean isIn(MouseEvent e, PauseButton b) {
		// TODO: return b.getBounds().contains() passing in e.getX(), e.getY()
		return b.getBounds().contains(e.getX(), e.getY());
	}

}