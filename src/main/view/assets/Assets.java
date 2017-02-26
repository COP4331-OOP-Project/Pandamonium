package view.assets;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.scene.image.Image;
import javafx.scene.text.Font;

public class Assets {
    private final static Logger log = LogManager.getLogger(Assets.class);
    private static final Assets INSTANCE = new Assets(); //This is the one instance of resources (singleton)
    private static final String FONT = "assets/fonts/nuku.ttf";
    private HashMap<String, Image> assets = new HashMap<String, Image>();
    private Font defaultFont;
    private Font smallFont;
    private Font mediumFont;
    private Font largeFont;
    private Font hugeFont;
    private Font veryHugeFont;

    private Assets() {
    } //Constructor is Private, only one instance of Resources can be created

    static Assets getInstance() { //Only available to AssetManager, which is in package
        return INSTANCE;
    }

    public void loadResources() {
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
        loadItem("GUI_HOVER", "assets/images/gui/hover.png");
        loadItem("GUI_MAP_BAR", "assets/images/gui/mapMakerBar.png");
        loadItem("GUI_BOTTOM", "assets/images/gui/bottomBar.png");
        loadItem("GUI_COMMAND_PANEL", "assets/images/gui/mode/commandPanel.png");
        loadItem("GUI_MINI_MAP_BORDER", "assets/images/gui/miniBorder.png");
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
        loadItem("GRASS_MINI", "assets/images/small/grassmini.png");
        loadItem("SAND_MINI", "assets/images/small/sandmini.png");
        loadItem("WATER_MINI", "assets/images/small/watermini.png");
        loadItem("MOUNTAIN_MINI", "assets/images/small/mountainmini.png");
        loadItem("UNIT_SELECTED", "assets/images/units/selectedUnit.png");
        loadItem("UNIT_G", "assets/images/units/green.png");
        loadItem("UNIT_B", "assets/images/units/blue.png");
        loadItem("UNIT_Y", "assets/images/units/yellow.png");
        loadItem("UNIT_O", "assets/images/units/orange.png");
        loadItem("UNIT_O_SMALL", "assets/images/small/orange.png");
        loadItem("UNIT_B_SMALL", "assets/images/small/blue.png");
        loadItem("UNIT_MELEE", "assets/images/units/decal/Melee.png");
        loadItem("UNIT_RANGED", "assets/images/units/decal/Ranged.png");
        loadItem("UNIT_EXPLORER", "assets/images/units/decal/Explorer.png");
        loadItem("UNIT_COLONIST", "assets/images/units/decal/Colonist.png");
        loadItem("BASE_SELECTED", "assets/images/structure/baseSelected.png");
        loadItem("BASE_ARROW", "assets/images/structure/baseArrow.png");
        loadItem("BASE_G", "assets/images/structure/baseGreen.png");
        loadItem("BASE_B", "assets/images/structure/baseBlue.png");
        loadItem("BASE_Y", "assets/images/structure/baseYellow.png");
        loadItem("BASE_O", "assets/images/structure/baseOrange.png");
        loadItem("BASE_B_SMALL", "assets/images/small/baseblue.png");
        loadItem("BASE_O_SMALL", "assets/images/small/baseorange.png");
        loadItem("ARMY_SELECTED", "assets/images/army/selectedArmy.png");
        loadItem("ARMY_G", "assets/images/army/GreenArmy.png");
        loadItem("ARMY_B", "assets/images/army/BlueArmy.png");
        loadItem("ARMY_Y", "assets/images/army/YellowArmy.png");
        loadItem("ARMY_O", "assets/images/army/OrangeArmy.png");
        loadItem("ICON_O", "assets/images/icon/orange.png");
        loadItem("ICON_B", "assets/images/icon/blue.png");
        loadItem("RALLY_POINT_SELECTED", "assets/images/rallyPoint/selectedRallyPoint.png");
        loadItem("DETAILS_PANEL", "assets/images/detailsPanel/detailsPanel.png");
        loadItem("MOVE_SELECTED", "assets/images/tileCovering/moveSelected.png");
        loadItem("AOE_DIE", "assets/images/areaEffect/loseHealth.png");
        loadItem("AOE_LOSE", "assets/images/areaEffect/redCross.png");
        loadItem("AOE_HEAL", "assets/images/areaEffect/skullDecal.png");
        loadItem("COMMAND_ASSIGN_WORKER", "assets/images/gui/commandIcons/icon/assignWorker.png");
        loadItem("COMMAND_ATTACK", "assets/images/gui/commandIcons/icons/attack.png");
        loadItem("COMMAND_BUILD", "assets/images/gui/commandIcons/icons/build.png");
        loadItem("COMMAND_CANCEL_QUEUE", "assets/images/gui/commandIcons/icons/cancelQueue.png");
        loadItem("COMMAND_DECOMMISSION", "assets/images/gui/commandIcons/icons/decommission.png");
        loadItem("COMMAND_DEFEND", "assets/images/gui/commandIcons/icons/defend.png");
        loadItem("COMMAND_DROP_OFF_WORKER", "assets/images/gui/commandIcons/icons/dropOffWorker.png");
        loadItem("COMMAND_FOUND_CAPITOL", "assets/images/gui/commandIcons/icons/foundCapitol.png");
        loadItem("COMMAND_GOTO_RALLY_POINT", "assets/images/gui/commandIcons/icons/gotoRallyPoint.png");
        loadItem("COMMAND_HEAL", "assets/images/gui/commandIcons/icons/heal.png");
        loadItem("COMMAND_MOVE", "assets/images/gui/commandIcons/icons/move.png");
        loadItem("COMMAND_PICKUP_WORKER", "assets/images/gui/commandIcons/icons/pickupWorker.png");
        loadItem("COMMAND_POWER_DOWN", "assets/images/gui/commandIcons/icons/powerDown.png");
        loadItem("COMMAND_POWER_UP", "assets/images/gui/commandIcons/icons/powerUp.png");
        loadItem("COMMAND_UNASSIGN_ALL_WORKERS", "assets/images/gui/commandIcons/icons/unassignAllWorkers.png");
        loadItem("TEXT_PATTERN", "assets/images/textTexture.jpg");
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
            case 0:
                return smallFont;
            case 1:
                return mediumFont;
            case 2:
                return largeFont;
            case 3:
                return hugeFont;
            case 4:
            	return veryHugeFont;
            default:
                return defaultFont;
        }
    }
}