package view.game;

import game.entities.EntityId;
import game.entities.EntitySubtypeEnum;
import game.entities.stats.UnitStats;
import game.entities.units.Unit;
import game.mode.Mode;
import game.mode.Submode;
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
	private AssetManager assets;
	private ViewEnum viewEnum;
    
    public UnitDetailsPanel(GameModelAdapter gameModelAdapter, AssetManager assets, ViewEnum viewEnum) {
    	super(gameModelAdapter, assets, viewEnum);
    	this.assets = assets;
    	this.viewEnum = viewEnum;
    	ds.setOffsetY(2.0);
    	ds.setColor(Color.color(0, 0, 0));
    }


    public void draw(GraphicsContext g, Point screenDimensions, long currentPulse) {
        if (getAdapter().getCurrentMode() == Mode.UNIT && getAdapter().getSelectedUnit() != null) {
        	drawBar(g, screenDimensions);
            g.setEffect(ds);
            drawText(g, screenDimensions.y);
            g.setEffect(null);
        }
    }

    private void drawText(GraphicsContext g, int height) {
        g.setFont(detailsFont);
        g.fillText("Unit Details", 10, height - 65);
        Unit unit = getAdapter().getSelectedUnit();
        UnitStats stats = unit.getStats();
        g.fillText("Type: ", X_DISTANCE , height - 35);
        g.fillText("Health: ", X_DISTANCE, height - 10);
        g.fillText("Attack: ", X_DISTANCE + SPACING , height - 35);
        g.fillText("Defense: ", X_DISTANCE + SPACING, height - 10);
        g.fillText("Armor: ", X_DISTANCE + SPACING * 2, height - 35);
        g.fillText("Upkeep: ", X_DISTANCE + SPACING * 2, height - 10);
        if (unit.getType() == EntitySubtypeEnum.EXPLORER) {
            g.fillText("Explorer", X_DISTANCE + OFFSET, height - 35);
        }
        if (unit.getType() == EntitySubtypeEnum.COLONIST) {
            g.fillText("Colonist", X_DISTANCE + OFFSET, height - 35);
        }
        if (unit.getType() == EntitySubtypeEnum.MELEE) {
            g.fillText("Melee", X_DISTANCE + OFFSET, height - 35);
        }
        if (unit.getType() == EntitySubtypeEnum.RANGE) {
            g.fillText("Ranged", X_DISTANCE + OFFSET, height - 35);
        }
        g.fillText(unit.getCurrentHealth() + "", X_DISTANCE + OFFSET, height - 10);
        g.fillText(stats.getOffPow() + "", X_DISTANCE + OFFSET + SPACING, height - 35);
        g.fillText(stats.getDefPow() + "", X_DISTANCE + OFFSET + SPACING, height - 10);
        g.fillText(stats.getArmor() + "", X_DISTANCE + OFFSET + SPACING * 2, height - 35);
        g.fillText(stats.getUpkeep() + "", X_DISTANCE + OFFSET + SPACING * 2, height - 10);
    }

	public void hideGUIElements() {
	}

	public void showGUIElements() {
	}
}