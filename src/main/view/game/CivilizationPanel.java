package view.game;

import java.awt.Point;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Font;
import view.Panel;
import view.ViewEnum;
import view.assets.AssetManager;

public class CivilizationPanel extends Panel {
	private DropShadow ds = new DropShadow();
    private Font civInfoFont = getAssets().getFont(2);
    private ImagePattern textFill = new ImagePattern(getAssets().getImage("TEXT_PATTERN"),
            0, 0, 1, 1, true);
    public CivilizationPanel(AssetManager assets, ViewEnum view) {
    	super(assets, view);
    	ds.setOffsetY(2.0f);
    	ds.setColor(Color.color(0, 0, 0));
    }

    @Override
    public void draw(GraphicsContext gc, Point screenDimensions) {
            drawBar(gc, screenDimensions);
            drawText(gc);
            drawPlayerIcon(gc);
    }

    private void drawPlayerIcon(GraphicsContext g) {
        //if player 0
            //g.drawImage(Assets.getInstance().getImage("ICON_O"), 130, 3);
        //if player 1
            //g.drawImage(getAssets().getImage("ICON_B"), 130, 3);
    }

    private void drawText(GraphicsContext g) {
        Font old = g.getFont();
        g.setFont(civInfoFont);
        g.setFill(textFill);
        g.setEffect(ds);
        g.fillText("Player: ", 10, 34);
        //g.fillText("Turn: " + game.getTurnNum() + "   O: 1-1   E: 2-2   F: 3-5", 180, 34);
        g.setEffect(null);
        g.setFont(old);
    }

    //Draw the panel itself
    private void drawBar(GraphicsContext g, Point screenDimensions) {
        g.drawImage(getAssets().getImage("GUI_TOP"), 0, 0);
    }

	@Override
	public void hideGUIElements() {
	}

	@Override
	public void showGUIElements() {
	}
}
