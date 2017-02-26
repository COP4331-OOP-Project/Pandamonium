package view.game;

import java.awt.Point;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import view.Panel;
import view.ViewEnum;
import view.assets.AssetManager;

public class ControlModePanel extends Panel {
	
    private final static Logger log = LogManager.getLogger(ControlModePanel.class);
    private static final int MODE_Y = 0;
    private static final int MODE_TEXT_X = 495;
	private static final int MODE_TEXT_Y = 30;
    private static final int MODE_TEXT_SPACING = 126;
    private static final int SUBMODE_X = 0;
    private static final int SUBMODE_Y = 50;
    private static final int SUBMODE_TEXT_X = 10;
    private static final int SUBMODE_TEXT_Y = 80;
	private DropShadow ds = new DropShadow();
	private Point screenDimensions;
    private Font modeFont = getAssets().getFont(0);
    private Font subModeFont = getAssets().getFont(1);
    private String[] modeString = {"Rally Point", "Structure", "Unit", "Army"};
    private String submodeString = "";
    private int mode = 0;

    public ControlModePanel(AssetManager assets, ViewEnum view) {
    	super(assets, view);
        ds.setOffsetY(2.0f);
    	ds.setColor(Color.color(0, 0, 0));
    }

    @Override
    public void draw(GraphicsContext gc, Point screenDimensions) {
    	this.screenDimensions = screenDimensions;
        //updateModes();
        drawModePanel(gc);
        drawSubmodePanel(gc);
        gc.setFont(modeFont);
        gc.setEffect(ds);
        drawModeStrings(gc);
        gc.setFont(subModeFont);
        drawSubmodeStrings(gc);
        gc.setEffect(null);
    }
/*
    private void updateModes() {
        if (game.getCurrentMode() == ModeEnum.RALLY_POINT) {
            mode = 0;
            submodeString = "";
        } else if (game.getCurrentMode() == ModeEnum.STRUCTURE) {
            mode = 1;
            if (game.getCurrentType() == StructureEnum.BASE) {
            	submodeString = "Base";
            }
        } else if (game.getCurrentMode() == ModeEnum.UNIT) {
            mode = 2;
            if (game.getCurrentType() == UnitEnum.EXPLORER)
            	submodeString = "Explorer";
            if (game.getCurrentType() == UnitEnum.COLONIST)
            	submodeString = "Colonist";
            if (game.getCurrentType() == UnitEnum.MELEE)
            	submodeString = "Melee";
            if (game.getCurrentType() == UnitEnum.RANGED)
            	submodeString = "Ranged";
        } else if (game.getCurrentMode() == ModeEnum.ARMY) {
            mode = 3;
            if (game.getCurrentType() == ArmyEnum.ENTIRE_ARMY)
            	submodeString = "Entire Army";
            if (game.getCurrentType() == ArmyEnum.BATTLE_GROUP)
            	submodeString = "Battle Group";
            if (game.getCurrentType() == ArmyEnum.REINFORCEMENTS)
            	submodeString = "Reinforcements";
        }
    }
*/
    private void drawModeStrings(GraphicsContext g) {
        g.fillText(modeString[0], screenDimensions.x - MODE_TEXT_X, MODE_TEXT_Y);
        g.fillText(modeString[1], screenDimensions.x - MODE_TEXT_X + MODE_TEXT_SPACING, MODE_TEXT_Y);
        g.fillText(modeString[2], screenDimensions.x - MODE_TEXT_X + 2 * MODE_TEXT_SPACING, MODE_TEXT_Y);
        g.fillText(modeString[3], screenDimensions.x - MODE_TEXT_X + 3 * MODE_TEXT_SPACING, MODE_TEXT_Y);
    }

    private void drawSubmodeStrings(GraphicsContext g) {
        g.fillText(submodeString, SUBMODE_TEXT_X, SUBMODE_TEXT_Y);
    }

    private void drawModePanel(GraphicsContext g) {
    	Image img = getAssets().getImage("GUI_MODE_PANEL");
        g.drawImage(img, screenDimensions.x - img.getWidth() , MODE_Y);
        switch (mode) {
            case 0:
                g.drawImage(getAssets().getImage("GUI_MODE_SELECTED1"), screenDimensions.x - img.getWidth() , MODE_Y);
                break;
            case 1:
                g.drawImage(getAssets().getImage("GUI_MODE_SELECTED2"), screenDimensions.x - img.getWidth() , MODE_Y);
                break;
            case 2:
                g.drawImage(getAssets().getImage("GUI_MODE_SELECTED3"), screenDimensions.x - img.getWidth() , MODE_Y);
                break;
            case 3:
                g.drawImage(getAssets().getImage("GUI_MODE_SELECTED4"), screenDimensions.x - img.getWidth() , MODE_Y);
                break;
            default:
                log.warn("Invalid Mode to display");
        }
    }

    private void drawSubmodePanel(GraphicsContext g) {
    	//if (game.getCurrentMode() != ModeEnum.RALLY_POINT)
    		g.drawImage(getAssets().getImage("GUI_SUBMODE_PANEL"), SUBMODE_X, SUBMODE_Y);
    }

	@Override
	public void hideGUIElements() {
	}

	@Override
	public void showGUIElements() {
	}
}
