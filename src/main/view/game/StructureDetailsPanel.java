package view.game;

import game.entities.EntitySubtypeEnum;
import game.entities.managers.exceptions.StructureDoesNotExistException;
import game.entities.stats.StructureStats;
import game.entities.stats.UnitStats;
import game.entities.structures.Structure;
import game.entities.units.Unit;
import game.mode.Mode;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import view.GameModelAdapter;
import view.ViewEnum;
import view.assets.AssetManager;

import java.awt.*;

public class StructureDetailsPanel extends DetailsPanel {
    Font detailsFont = getAssets().getFont(0);
    Font bigFont = getAssets().getFont(3);
    private DropShadow ds = new DropShadow();
    private static final int X_DISTANCE = 20;
    private static final int OFFSET = 80;
	private static final int SPACING = 230;

    public StructureDetailsPanel(GameModelAdapter gameModelAdapter, AssetManager assets, ViewEnum viewEnum) {
    	super(gameModelAdapter, assets, viewEnum);
    	ds.setOffsetY(2.0f);
    	ds.setColor(Color.color(0, 0, 0));
    }

    public void draw(GraphicsContext g, Point screenDimensions, long currentPulse) {
    	try {
			if (getAdapter().getCurrentMode() == Mode.STRUCTURE && getAdapter().getSelectedStructure() != null) {
				drawBar(g, screenDimensions);
			    g.setEffect(ds);
			    drawText(g, screenDimensions.y);
			    g.setEffect(null);
			}
		} catch (StructureDoesNotExistException e) {
			e.printStackTrace();
		}
    }

    private void drawText(GraphicsContext g, int height) {
        Font old = g.getFont();
        g.setFont(detailsFont);
        Structure structure = null;
        StructureStats stats = null;
		try {
			structure = getAdapter().getSelectedStructure();
			stats = structure.getStats();
		} catch (StructureDoesNotExistException e) {}
        g.fillText("Structure Details", 10, height - 65);
        g.fillText("Type: ", X_DISTANCE , height - 35);
        g.fillText("Health: ", X_DISTANCE, height - 10);
        g.fillText("Attack: ", X_DISTANCE + SPACING , height - 35);
        g.fillText("Defense: ", X_DISTANCE + SPACING, height - 10);
        g.fillText("Armor: ", X_DISTANCE + SPACING * 2, height - 35);
        g.fillText("Upkeep: ", X_DISTANCE + SPACING * 2, height - 10);
        if (structure.getType() == EntitySubtypeEnum.CAPITOL) {
            g.fillText("Capitol", X_DISTANCE + OFFSET, height - 35);
        }
        if (structure.getType() == EntitySubtypeEnum.FARM) {
            g.fillText("Farm", X_DISTANCE + OFFSET, height - 35);
        }
        if (structure.getType() == EntitySubtypeEnum.MINE) {
            g.fillText("Mine", X_DISTANCE + OFFSET, height - 35);
        }
        if (structure.getType() == EntitySubtypeEnum.PLANT) {
            g.fillText("Plant", X_DISTANCE + OFFSET, height - 35);
        }
        if (structure.getType() == EntitySubtypeEnum.OBSERVE) {
            g.fillText("Observatory", X_DISTANCE + OFFSET, height - 35);
        }
        if (structure.getType() == EntitySubtypeEnum.FORT) {
            g.fillText("Fort", X_DISTANCE + OFFSET, height - 35);
        }
        if (structure.getType() == EntitySubtypeEnum.UNIVERSITY) {
            g.fillText("University", X_DISTANCE + OFFSET, height - 35);
        }
        g.fillText(structure.getCurrentHealth() + "", X_DISTANCE + OFFSET, height - 10);
        g.fillText(stats.getAttackStrength() + "", X_DISTANCE + OFFSET + SPACING, height - 35);
        g.fillText(stats.getDefensiveStrength() + "", X_DISTANCE + OFFSET + SPACING, height - 10);
        g.fillText(stats.getArmor() + "", X_DISTANCE + OFFSET + SPACING * 2, height - 35);
        g.fillText(stats.getUpkeep() + "", X_DISTANCE + OFFSET + SPACING * 2, height - 10);
    }

	public void hideGUIElements() {
	}

	public void showGUIElements() {
	}

}
