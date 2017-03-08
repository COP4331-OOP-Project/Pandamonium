package view.assets;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.text.Font;

public class Assets {
	private final static Logger log = LogManager.getLogger(Assets.class);
	private static final Assets INSTANCE = new Assets(); // This is the one
															// instance of
															// resources
															// (singleton)
	private static final String FONT = "assets/fonts/nuku.ttf";
	private static final String SPLASH_SCREEN = "assets/video/Intro.mp4";
	private File splashFile;
	private Media splashScreen;
	private static final String MENU_MUSIC = "assets/music/MainMenu.mp3";
	private File menuMusicFile;
	private Media menuMusic;
	private static final String GAME_MUSIC = "assets/music/InGame.mp3";
	private File gameMusicFile;
	private Media gameMusic;
	private HashMap<String, Image> assets = new HashMap<String, Image>();
	private Font defaultFont;
	private Font smallFont;
	private Font mediumFont;
	private Font largeFont;
	private Font hugeFont;
	private Font veryHugeFont;

	private Assets() {
	} // Constructor is Private, only one instance of Resources can be created

	static Assets getInstance() { // Only available to AssetManager, which is in
									// package
		return INSTANCE;
	}

	public void loadResources() {
		splashFile = new File(SPLASH_SCREEN);
		splashScreen = new Media(splashFile.toURI().toString());
		menuMusicFile = new File(MENU_MUSIC);
		menuMusic = new Media(menuMusicFile.toURI().toString());
		gameMusicFile = new File(GAME_MUSIC);
		gameMusic = new Media(gameMusicFile.toURI().toString());
		loadImages();
		loadFonts();
	}

	private void loadFonts() {
		try {
			defaultFont = Font.loadFont(new FileInputStream(new File(FONT)), 22);
			smallFont = Font.loadFont(new FileInputStream(new File(FONT)), 20);
			mediumFont = Font.loadFont(new FileInputStream(new File(FONT)), 25);
			largeFont = Font.loadFont(new FileInputStream(new File(FONT)), 37);
			hugeFont = Font.loadFont(new FileInputStream(new File(FONT)), 53);
			veryHugeFont = Font.loadFont(new FileInputStream(new File(FONT)), 100);
		} catch (IOException e) {
			defaultFont = new Font("Lucida Sans", 20);
			smallFont = new Font("Lucida Sans", 18);
			mediumFont = new Font("Lucida Sans", 21);
			largeFont = new Font("Lucida Sans", 35);
			hugeFont = new Font("Lucida Sans", 55);
			veryHugeFont = new Font("Lucida Sans", 100);
			e.printStackTrace();
		}
	}

	private void loadImages() {
		loadItem("MENU_BACKGROUND", "assets/images/menu/menuBackground.jpg");
		loadItem("GAME_BACKGROUND", "assets/images/menu/gameBackground.jpg");
		loadItem("GUI_TOP", "assets/images/gui/topBar.png");
		loadItem("GUI_TOGGLE", "assets/images/gui/toggleBar.png");
		loadItem("GUI_SIDE", "assets/images/gui/sideBar.png");
		loadItem("GUI_HOVER", "assets/images/gui/hover.png");
		loadItem("GUI_MAP_BAR", "assets/images/gui/mapMakerBar.png");
		loadItem("GUI_BOTTOM", "assets/images/gui/bottomBar.png");
		loadItem("GUI_COMMAND_PANEL", "assets/images/gui/mode/commandPanel.png");
		loadItem("GUI_MINI_MAP_BORDER", "assets/images/gui/miniMap/miniBorder.png");
		loadItem("GUI_MINI_MAP_BACKGROUND", "assets/images/gui/miniMap/minimapBackground.png");
		loadItem("GUI_MODE_PANEL", "assets/images/gui/mode/modePanel.png");
		loadItem("GUI_SUBMODE_PANEL", "assets/images/gui/mode/subModePanel.png");
		loadItem("GUI_MODE_SELECTED1", "assets/images/gui/mode/selectedMode1.png");
		loadItem("GUI_MODE_SELECTED2", "assets/images/gui/mode/selectedMode2.png");
		loadItem("GUI_MODE_SELECTED3", "assets/images/gui/mode/selectedMode3.png");
		loadItem("GUI_MODE_SELECTED4", "assets/images/gui/mode/selectedMode4.png");
		loadItem("TERRAIN_SAND", "assets/images/terrain/sand/sand.png");
		loadItem("TERRAIN_GRASS1", "assets/images/terrain/grass/grass1.png");
		loadItem("TERRAIN_GRASS2", "assets/images/terrain/grass/grass2.png");
		loadItem("TERRAIN_GRASS3", "assets/images/terrain/grass/grass3.png");
		loadItem("TERRAIN_MOUNTAIN1", "assets/images/terrain/mountain/mountain1.png");
		loadItem("TERRAIN_MOUNTAIN2", "assets/images/terrain/mountain/mountain2.png");
		loadItem("TERRAIN_MOUNTAIN3", "assets/images/terrain/mountain/mountain3.png");
		loadItem("TERRAIN_WATER1", "assets/images/terrain/water/water1.png");
		loadItem("TERRAIN_WATER2", "assets/images/terrain/water/water2.png");
		loadItem("TERRAIN_WATER3", "assets/images/terrain/water/water3.png");
		loadItem("TERRAIN_WATER4", "assets/images/terrain/water/water4.png");
		loadItem("TERRAIN_WATER5", "assets/images/terrain/water/water5.png");
		loadItem("TERRAIN_WATER6", "assets/images/terrain/water/water6.png");
		loadItem("CLOUDS_MINI", "assets/images/gui/miniMap/clouds.png");
		loadItem("GRASS_MINI", "assets/images/gui/miniMap/grassmini.png");
		loadItem("SAND_MINI", "assets/images/gui/miniMap/sandmini.png");
		loadItem("WATER_MINI", "assets/images/gui/miniMap/watermini.png");
		loadItem("MOUNTAIN_MINI", "assets/images/gui/miniMap/mountainmini.png");
		loadItem("UNIT_SELECTED", "assets/images/units/selectedUnit.png");
		loadItem("UNIT_LARGE_HUMAN", "assets/images/units/largeHuman.png");
		loadItem("UNIT_GIANT_HUMAN", "assets/images/units/giantHuman.png");
		loadItem("UNIT_LARGE_HUMAN_HAT", "assets/images/units/largeHumanHat.png");
		loadItem("UNIT_SMALL_HUMAN", "assets/images/units/smallHuman.png");
		loadItem("UNIT_SMALL_HUMAN_HAT", "assets/images/units/smallHumanHat.png");
		loadItem("UNIT_LARGE_PANDA", "assets/images/units/largePanda.png");
		loadItem("UNIT_SMALL_PANDA", "assets/images/units/smallPanda.png");
		loadItem("UNIT_GIANT_PANDA", "assets/images/units/giantPanda.png");
		loadItem("UNIT_HUMAN_MM", "assets/images/gui/miniMap/human.png");
		loadItem("UNIT_PANDA_MM", "assets/images/gui/miniMap/panda.png");
		loadItem("UNIT_MELEE", "assets/images/units/decal/Melee.png");
		loadItem("UNIT_RANGED", "assets/images/units/decal/Ranged.png");
		loadItem("UNIT_EXPLORER", "assets/images/units/decal/Explorer.png");
		loadItem("UNIT_COLONIST", "assets/images/units/decal/Colonist.png");
		loadItem("BASE_SELECTED", "assets/images/structure/baseSelected.png");
		loadItem("BASE_HUMAN", "assets/images/structure/baseHuman.png");
		loadItem("BASE_PANDA", "assets/images/structure/basePanda.png");
		loadItem("POWER_PLANT_HUMAN1", "assets/images/structure/powerPlantHuman1.png");
		loadItem("POWER_PLANT_HUMAN2", "assets/images/structure/powerPlantHuman2.png");
		loadItem("POWER_PLANT_HUMAN3", "assets/images/structure/powerPlantHuman3.png");
		loadItem("POWER_PLANT_PANDA1", "assets/images/structure/powerPlantPanda1.png");
		loadItem("POWER_PLANT_PANDA2", "assets/images/structure/powerPlantPanda2.png");
		loadItem("POWER_PLANT_PANDA3", "assets/images/structure/powerPlantPanda3.png");
		loadItem("OBSERVATION_TOWER_HUMAN", "assets/images/structure/observationTowerHuman.png");
		loadItem("OBSERVATION_TOWER_PANDA", "assets/images/structure/observationTowerPanda.png");
		loadItem("FARM_HUMAN", "assets/images/structure/farmHuman.png");
		loadItem("FARM_PANDA", "assets/images/structure/farmPanda.png");
		loadItem("FORT_HUMAN", "assets/images/structure/fortHuman.png");
		loadItem("FORT_PANDA", "assets/images/structure/fortPanda.png");
		loadItem("UNIVERSITY_HUMAN", "assets/images/structure/universityHuman.png");
		loadItem("UNIVERSITY_PANDA", "assets/images/structure/universityPanda.png");
		loadItem("MINE_HUMAN1", "assets/images/structure/mineHuman1.png");
		loadItem("MINE_HUMAN2", "assets/images/structure/mineHuman2.png");
		loadItem("MINE_HUMAN3", "assets/images/structure/mineHuman3.png");
		loadItem("MINE_HUMAN4", "assets/images/structure/mineHuman4.png");
		loadItem("MINE_PANDA1", "assets/images/structure/minePanda1.png");
		loadItem("MINE_PANDA2", "assets/images/structure/minePanda2.png");
		loadItem("MINE_PANDA3", "assets/images/structure/minePanda3.png");
		loadItem("MINE_PANDA4", "assets/images/structure/minePanda4.png");
		loadItem("BASE_HUMAN_MM", "assets/images/gui/miniMap/baseHuman.png");
		loadItem("BASE_PANDA_MM", "assets/images/gui/miniMap/basePanda.png");
		loadItem("ICON_HUMAN", "assets/images/icon/human.png");
		loadItem("ICON_PANDA", "assets/images/icon/panda.png");
		loadItem("RALLY_POINT_SELECTED", "assets/images/rallyPoint/selectedRallyPoint.png");
		loadItem("DETAILS_PANEL", "assets/images/detailsPanel/detailsPanel.png");
		loadItem("MOVE_SELECTED", "assets/images/tileCovering/moveSelected.png");
		loadItem("TILE_FOG1", "assets/images/tileCovering/fog1.png");
		loadItem("TILE_FOG2", "assets/images/tileCovering/fog2.png");
		loadItem("TILE_FOG3", "assets/images/tileCovering/fog3.png");
		loadItem("TILE_SEMI_VISIBLE", "assets/images/tileCovering/semiVisible.png");
		loadItem("AOE_DIE", "assets/images/areaEffect/loseHealth.png");
		loadItem("AOE_LOSE", "assets/images/areaEffect/redCross.png");
		loadItem("AOE_HEAL", "assets/images/areaEffect/skullDecal.png");
		loadItem("COMMAND_ASSIGN_WORKER", "assets/images/icon/assignWorker.png");
		loadItem("COMMAND_ATTACK", "assets/images/icon/attack.png");
		loadItem("COMMAND_BUILD", "assets/images/icon/build.png");
		loadItem("COMMAND_CANCEL_QUEUE", "assets/images/icon/cancelQueue.png");
		loadItem("COMMAND_DECOMMISSION", "assets/images/icon/decommission.png");
		loadItem("COMMAND_DEFEND", "assets/images/icon/defend.png");
		loadItem("COMMAND_DROP_OFF_WORKER", "assets/images/icon/dropOffWorker.png");
		loadItem("COMMAND_FOUND_CAPITOL", "assets/images/icon/foundCapitol.png");
		loadItem("COMMAND_GOTO_RALLY_POINT", "assets/images/icon/gotoRallyPoint.png");
		loadItem("COMMAND_HEAL", "assets/images/icon/heal.png");
		loadItem("COMMAND_MOVE", "assets/images/icon/move.png");
		loadItem("COMMAND_PICKUP_WORKER", "assets/images/icon/pickupWorker.png");
		loadItem("COMMAND_POWER_DOWN", "assets/images/icon/powerDown.png");
		loadItem("COMMAND_POWER_UP", "assets/images/icon/powerUp.png");
		loadItem("COMMAND_UNASSIGN_ALL_WORKERS", "assets/images/icon/unassignAllWorkers.png");
		loadItem("TEXT_PATTERN", "assets/images/textTexture.jpg");
		loadItem("ICON_FOOD_HUMAN", "assets/images/icon/foodHuman.png");
		loadItem("ICON_FOOD_PANDA", "assets/images/icon/foodPanda.png");
		loadItem("ICON_ORE", "assets/images/icon/ore.png");
		loadItem("ICON_METAL", "assets/images/icon/metal.png");
		loadItem("ICON_PEAT", "assets/images/icon/peat.png");
		loadItem("ICON_POWER", "assets/images/icon/power.png");
		loadItem("ICON_VISIBILITY", "assets/images/icon/visibility.png");
		loadItem("ICON_HUMAN_HEAD", "assets/images/icon/unitHuman.png");
		loadItem("ICON_PANDA_HEAD", "assets/images/icon/unitPanda.png");
		loadItem("ICON_STRUCTURE", "assets/images/icon/structure.png");
		loadItem("ICON_RESEARCH", "assets/images/icon/research.png");
		loadItem("ICON_SETTINGS", "assets/images/icon/settings.png");
		loadItem("ICON_END_TURN", "assets/images/icon/endTurn.png");
		loadItem("CHECKED", "assets/images/icon/checked.png");
		loadItem("UNCHECKED", "assets/images/icon/unChecked.png");
		loadItem("HEALTH_BAR", "assets/images/health/healthBar.png");
		loadItem("HEALTH_BAR_FILL", "assets/images/health/healthBarFilling.png");
	}

	private void loadItem(String name, String path) {
		File file = new File(path);
		String localUrl = "";
		try {
			localUrl = file.toURI().toURL().toString();
		} catch (MalformedURLException e) {
			log.error("Invalid Image File Specified: " + name + " " + path);
			e.printStackTrace();
		}
		assets.put(name, new Image(localUrl));
		log.info("Loaded Item: " + name);
	}

	public Image getImage(String image) {
		return assets.get(image);
	}

	public Font getFont(int size) {
		switch (size) {
			case 0 :
				return smallFont;
			case 1 :
				return mediumFont;
			case 2 :
				return largeFont;
			case 3 :
				return hugeFont;
			case 4 :
				return veryHugeFont;
			default :
				return defaultFont;
		}
	}

	public Media getSplash() {
		return splashScreen;
	}

	public Media getMenuMusic() {
		return menuMusic;
	}

	public Media getGameMusic() {
		return gameMusic;
	}
}