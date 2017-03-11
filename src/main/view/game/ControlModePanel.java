package view.game;

import java.awt.Point;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import game.mode.Mode;
import game.mode.Submode;
import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import view.GameModelAdapter;
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
    private Mode mode;
    private Group root;
    private String submodeString = "";
    
    public ControlModePanel(GameModelAdapter gameModelAdapter, Group root, AssetManager assets, ViewEnum view) {
    	super(gameModelAdapter, assets, view);
    	this.root = root;
        ds.setOffsetY(2.0f);
    	ds.setColor(Color.color(0, 0, 0));
    }

    public void draw(GraphicsContext g, Point screenDimensions, long currentPulse) {
    	checkModeClicked();
    	this.screenDimensions = screenDimensions;
    	updateModes();
        drawModePanel(g);
        drawSubmodePanel(g);
        g.setFont(modeFont);
        g.setEffect(ds);
        drawModeStrings(g);
        g.setFont(subModeFont);
        drawSubmodeStrings(g);
        g.setEffect(null);
    }
    
    private void checkModeClicked() {
		root.setOnMouseReleased(event -> {
		   double x = event.getX();
		   double y = event.getY();
		   if (x > (screenDimensions.x - 503) && y < 45) { //Clicked in Mode Box
			   if (x > screenDimensions.x - 503 && x <= screenDimensions.x - 377) { //Click on RP
				   getAdapter().setMode(Mode.RALLY_POINT);
			   } else if (x > screenDimensions.x - 377 && x <= screenDimensions.x - 251) {//Click on Structure
				   getAdapter().setMode(Mode.STRUCTURE);
			   } else if (x > screenDimensions.x - 251 && x <= screenDimensions.x - 126) {//Click on Unit
				   getAdapter().setMode(Mode.UNIT);
			   } else { //Click on Army
				   getAdapter().setMode(Mode.ARMY);
			   }
		   }
		});
    }

	private void updateModes() {
    	mode = getAdapter().getCurrentMode();
    	Submode submode = getAdapter().getCurrentSubmode();
    	submodeString = submode.getText();
    }
    
    private void drawModeStrings(GraphicsContext g) {
        g.fillText(modeString[0], screenDimensions.x - MODE_TEXT_X, MODE_TEXT_Y);
        g.fillText(modeString[1], screenDimensions.x - MODE_TEXT_X + MODE_TEXT_SPACING, MODE_TEXT_Y);
        g.fillText(modeString[2], screenDimensions.x - MODE_TEXT_X + 2 * MODE_TEXT_SPACING, MODE_TEXT_Y);
        g.fillText(modeString[3], screenDimensions.x - MODE_TEXT_X + 3 * MODE_TEXT_SPACING, MODE_TEXT_Y);
    }

    private void drawSubmodeStrings(GraphicsContext g) {
    	if (getAdapter().getCurrentMode() != Mode.RALLY_POINT)
    		g.fillText(submodeString, SUBMODE_TEXT_X, SUBMODE_TEXT_Y);
    }

    private void drawModePanel(GraphicsContext g) {
    	Image img = getImage("GUI_MODE_PANEL");
        g.drawImage(img, screenDimensions.x - img.getWidth() , MODE_Y);
        switch (mode) {
            case RALLY_POINT:
                g.drawImage(getImage("GUI_MODE_SELECTED1"), screenDimensions.x - img.getWidth() , MODE_Y);
                break;
            case STRUCTURE:
                g.drawImage(getImage("GUI_MODE_SELECTED2"), screenDimensions.x - img.getWidth() , MODE_Y);
                break;
            case UNIT:
                g.drawImage(getImage("GUI_MODE_SELECTED3"), screenDimensions.x - img.getWidth() , MODE_Y);
                break;
            case ARMY:
                g.drawImage(getImage("GUI_MODE_SELECTED4"), screenDimensions.x - img.getWidth() , MODE_Y);
                break;
            default:
                log.warn("Invalid Mode to display");
        }
    }

    private void drawSubmodePanel(GraphicsContext g) {
    	if (getAdapter().getCurrentMode() != Mode.RALLY_POINT)
    		g.drawImage(getImage("GUI_SUBMODE_PANEL"), SUBMODE_X, SUBMODE_Y);
    }

	public void hideGUIElements() {
	}

	public void showGUIElements() {
	}
}
