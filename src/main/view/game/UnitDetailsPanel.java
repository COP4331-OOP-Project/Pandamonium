package view.game;

import game.mode.Mode;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import view.GameModelAdapter;
import view.ViewEnum;
import view.assets.AssetManager;

import java.awt.*;

public class UnitDetailsPanel extends DetailsPanel {
    Font detailsFont = getAssets().getFont(0);
    Font bigFont = getAssets().getFont(3);
    private DropShadow ds = new DropShadow();
    private static final int X_DISTANCE = 20;
    private static final int OFFSET = 80;
	private static final int SPACING = 230;
	private GameModelAdapter gameModelAdapter;
	private AssetManager assets;
	private ViewEnum viewEnum;
    
    public UnitDetailsPanel(GameModelAdapter gameModelAdapter, AssetManager assets, ViewEnum viewEnum) {
    	super(gameModelAdapter, assets, viewEnum);
    	this.gameModelAdapter = gameModelAdapter;
    	this.assets = assets;
    	this.viewEnum = viewEnum;
    	ds.setOffsetY(2.0);
    	ds.setColor(Color.color(0, 0, 0));
    }


    public void draw(GraphicsContext g, Point screenDimensions, long currentPulse) {
        if (getAdapter().getCurrentMode() == Mode.UNIT) {
        	drawBar(g, screenDimensions);
            g.setEffect(ds);
            drawText(g, screenDimensions.y);
            g.setEffect(null);
        }
    }

    private void drawText(GraphicsContext g, int height) {
        Font old = g.getFont();
        g.setFont(detailsFont);
        g.fillText("Unit Details", 10, height - 65);
        /*
        if (game.getSelectedUnit() != -1) {
            g.fillText("Type: ", X_DISTANCE , height - 35);
            g.fillText("Health: ", X_DISTANCE, height - 10);
            g.fillText("Attack: ", X_DISTANCE + SPACING , height - 35);
            g.fillText("Defense: ", X_DISTANCE + SPACING, height - 10);
            g.fillText("Armor: ", X_DISTANCE + SPACING * 2, height - 35);
            g.fillText("Upkeep: ", X_DISTANCE + SPACING * 2, height - 10);
            if (game.getCurrentType() == UnitEnum.EXPLORER) {
                g.fillText("Explorer", X_DISTANCE + OFFSET, height - 35);
            }
            if (game.getCurrentType() == UnitEnum.COLONIST) {
                g.fillText("Colonist", X_DISTANCE + OFFSET, height - 35);
            }
            if (game.getCurrentType() == UnitEnum.MELEE) {
                g.fillText("Melee", X_DISTANCE + OFFSET, height - 35);
            }
            if (game.getCurrentType() == UnitEnum.RANGED) {
                g.fillText("Ranged", X_DISTANCE + OFFSET, height - 35);
            }
            g.fillText(game.getCurrentPlayer().getAllUnit().get(game.getSelectedUnit()).getHealth() + "", X_DISTANCE + OFFSET, height - 10);
            g.fillText(game.getCurrentPlayer().getAllUnit().get(game.getSelectedUnit()).getAttackDamage() + "", X_DISTANCE + OFFSET + SPACING, height - 35);
            g.fillText(game.getCurrentPlayer().getAllUnit().get(game.getSelectedUnit()).getDefenseDamage() + "", X_DISTANCE + OFFSET + SPACING, height - 10);
            g.fillText(game.getCurrentPlayer().getAllUnit().get(game.getSelectedUnit()).getArmor() + "", X_DISTANCE + OFFSET + SPACING * 2, height - 35);
            g.fillText(game.getCurrentPlayer().getAllUnit().get(game.getSelectedUnit()).getUpkeep() + "", X_DISTANCE + OFFSET + SPACING * 2, height - 10);
        } else {
            g.setFont(bigFont);
            if (game.getCurrentType() == UnitEnum.EXPLORER) {
                g.fillText("You Have No Explorer Units", X_DISTANCE, height - 17);
            }
            if (game.getCurrentType() == UnitEnum.COLONIST) {
                g.fillText("You Have No Colonist Units", X_DISTANCE, height - 17);
            }
            if (game.getCurrentType() == UnitEnum.MELEE) {
                g.fillText("You Have No Melee Units", X_DISTANCE, height - 17);
            }
            if (game.getCurrentType() == UnitEnum.RANGED) {
                g.fillText("You Have No Ranged Units", X_DISTANCE, height - 17);
            }
        }
        g.setFont(old);
        */
    }

	public void hideGUIElements() {
	}

	public void showGUIElements() {
	}
}