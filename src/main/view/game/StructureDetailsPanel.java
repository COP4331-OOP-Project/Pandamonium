package view.game;

import java.awt.Point;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import view.Assets;
import view.ViewEnum;

public class StructureDetailsPanel extends DetailsPanel {
    Font detailsFont = getAssets().getFont(0);
    Font bigFont = getAssets().getFont(3);
    private DropShadow ds = new DropShadow();
    private static final int X_DISTANCE = 20;
    private static final int OFFSET = 80;
	private static final int SPACING = 230;

    public StructureDetailsPanel(Assets assets, ViewEnum viewEnum) {
    	super(assets, viewEnum);
    	ds.setOffsetY(2.0f);
    	ds.setColor(Color.color(0, 0, 0));
    }

    public void draw(GraphicsContext gc, Point screenDimensions) {
        drawBar(gc, screenDimensions);
        gc.setEffect(ds);
        drawText(gc, screenDimensions.y);
        gc.setEffect(null);
    }

    private void drawText(GraphicsContext g, int height) {
        Font old = g.getFont();
        g.setFont(detailsFont);
        g.fillText("Structure Details:", 10, height - 65);
        /*
        if (game.getCurrentPlayer().getBases().size() > 0) {
            g.fillText("Type: ", X_DISTANCE , height - 35);
            g.fillText("Health: ", X_DISTANCE, height - 10);
            g.fillText("Attack: ", X_DISTANCE + SPACING , height - 35);
            g.fillText("Defense: ", X_DISTANCE + SPACING, height - 10);
            g.fillText("Armor: ", X_DISTANCE + SPACING * 2, height - 35);
            g.fillText("Upkeep: ", X_DISTANCE + SPACING * 2, height - 10);

            if (game.getCurrentType() == StructureEnum.BASE) {
                g.fillText("Base", X_DISTANCE + OFFSET, height - 35);
            }
            g.fillText(game.getCurrentPlayer().getBases().get(0).getHealth() + "", X_DISTANCE + OFFSET, height - 10);
            g.fillText(game.getCurrentPlayer().getBases().get(0).getAttackDamage() + "", X_DISTANCE + OFFSET + SPACING, height - 35);
            g.fillText(game.getCurrentPlayer().getBases().get(0).getDefenseDamage() + "", X_DISTANCE + OFFSET + SPACING, height - 10);
            g.fillText(game.getCurrentPlayer().getBases().get(0).getArmor() + "", X_DISTANCE + OFFSET + SPACING * 2, height - 35);
            g.fillText(game.getCurrentPlayer().getBases().get(0).getUpkeep() + "", X_DISTANCE + OFFSET + SPACING * 2, height - 10);
        } else {
            g.setFont(bigFont);
            g.fillText("You Have No Structures", X_DISTANCE, height - 17);
        }
        */
        g.setFont(old);
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
