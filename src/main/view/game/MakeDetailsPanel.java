package view.game;

import java.awt.Point;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;
import view.GameModelAdapter;
import view.Panel;
import view.ViewEnum;
import view.assets.AssetManager;

public class MakeDetailsPanel extends Panel{
    private final static Logger log = LogManager.getLogger(ControlModePanel.class);
    private static final int PANEL_HEIGHT = 114;
    private static final int PANEL_DISTANCE = 78;
    private static final int PANEL_DISTANCE_FROM_LEFT = 210;
    private static final int TEXT_SPACING = 48;
    private static final int TEXT_LOCATION = 58;
    private static final int TEXT_OFFSET = 28;
    private Point screenDimensions;

    private Font modeFont = getAssets().getFont(1);
    private String[][] makeList = {{"Base", "", "", ""},
            {"Melee", "Ranged", "Colonist", "Explorer"}};

    private int type = 0;
    private int mode = 0;

    // Constructor
    public MakeDetailsPanel(GameModelAdapter gameModelAdapter, AssetManager assets, ViewEnum viewEnum) {
    	super(gameModelAdapter, assets, viewEnum);
    	this.setIsVisible(false);
    }

    private void updateType() {
       // if (game.getCurrentType() == UnitEnum.COLONIST) type = COLONIST_LIST;
       // else type = BASE_LIST;

       // this.mode = game.getCurrentMakeOption();
    }

    // Draw the panel
    public void draw(GraphicsContext gc, Point screenDimensions, long currentPulse) {
        this.screenDimensions = screenDimensions;
        updateType();
        gc.drawImage(getAssets().getImage("GUI_MODE_PANEL"), PANEL_DISTANCE_FROM_LEFT
                , screenDimensions.y / 2 - PANEL_DISTANCE - PANEL_HEIGHT);
        switch (mode) {
            case 0:
                gc.drawImage(getAssets().getImage("GUI_MODE_SELECTED1"), PANEL_DISTANCE_FROM_LEFT
                        , screenDimensions.y / 2 - PANEL_DISTANCE - PANEL_HEIGHT);
                break;
            case 1:
                gc.drawImage(getAssets().getImage("GUI_MODE_SELECTED2"), PANEL_DISTANCE_FROM_LEFT
                        , screenDimensions.y / 2 - PANEL_DISTANCE - PANEL_HEIGHT);
                break;
            case 2:
                gc.drawImage(getAssets().getImage("GUI_MODE_SELECTED3"), PANEL_DISTANCE_FROM_LEFT
                        , screenDimensions.y / 2 - PANEL_DISTANCE - PANEL_HEIGHT);
                break;
            case 3:
                gc.drawImage(getAssets().getImage("GUI_MODE_SELECTED4"), PANEL_DISTANCE_FROM_LEFT
                        , screenDimensions.y / 2 - PANEL_DISTANCE - PANEL_HEIGHT);
                break;
            default:
                log.warn("Invalid Mode to display");
        }

        gc.setFont(modeFont);
        drawTypeStrings(gc);

    }

    // Draw make list strings
    private void drawTypeStrings(GraphicsContext g) {
        g.fillText(makeList[type][0], PANEL_DISTANCE_FROM_LEFT + TEXT_OFFSET, screenDimensions.y / 2 - PANEL_DISTANCE - TEXT_LOCATION);
        g.fillText(makeList[type][1], PANEL_DISTANCE_FROM_LEFT + TEXT_OFFSET, screenDimensions.y / 2 - PANEL_DISTANCE - TEXT_LOCATION + TEXT_SPACING);
        g.fillText(makeList[type][2], PANEL_DISTANCE_FROM_LEFT + TEXT_OFFSET, screenDimensions.y / 2 - PANEL_DISTANCE - TEXT_LOCATION + 2 * TEXT_SPACING);
        g.fillText(makeList[type][3], PANEL_DISTANCE_FROM_LEFT + TEXT_OFFSET, screenDimensions.y / 2 - PANEL_DISTANCE - TEXT_LOCATION + 3 * TEXT_SPACING);
    }

	@Override
	public void hideGUIElements() {
		// TODO Auto-generated method stub
	}

	@Override
	public void showGUIElements() {
		// TODO Auto-generated method stub
	}

}

