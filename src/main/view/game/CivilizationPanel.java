package view.game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Font;
import view.GameModelAdapter;
import view.Panel;
import view.ViewEnum;
import view.assets.AssetManager;

import java.awt.*;

public class CivilizationPanel extends Panel {
	private DropShadow ds = new DropShadow();
	private Font civInfoFont = getAssets().getFont(2);
	private ImagePattern textFill = new ImagePattern(getImage("TEXT_PATTERN"), 0, 0, 1,
			1, true);
	public CivilizationPanel(GameModelAdapter gameModelAdapter, AssetManager assets,
			ViewEnum view) {
		super(gameModelAdapter, assets, view);
		ds.setOffsetY(2.0f);
		ds.setColor(Color.color(0, 0, 0));
	}

	public void draw(GraphicsContext g, Point screenDimensions, long currentPulse) {
		drawBar(g, screenDimensions);
		drawText(g);
		drawPlayerIcon(g);
		drawResources(g);
	}

	private void drawResources(GraphicsContext g) {
		g.setFont(getAssets().getFont(1));
		if (getAdapter().getPlayerId() == 0)
			g.drawImage(getImage("ICON_FOOD_HUMAN"), 330, 4);
		else
			g.drawImage(getImage("ICON_FOOD_PANDA"), 330, 4);
		g.drawImage(getImage("ICON_POWER"), 427, 4);
		g.drawImage(getImage("ICON_METAL"), 520, 4);
		g.setEffect(ds);
		g.fillText("" + getAdapter().getCurrentNutrients(), 366, 30);
		g.fillText("" + getAdapter().getCurrentPower(), 455, 30);
		g.fillText("" + getAdapter().getCurrentMetal(), 558, 30);
		g.setEffect(null);
	}

	private void drawPlayerIcon(GraphicsContext g) {
		if (getAdapter().getPlayerId() == 0)
			g.drawImage(getImage("ICON_HUMAN"), 130, 3);
		if (getAdapter().getPlayerId() == 1)
			g.drawImage(getImage("ICON_PANDA"), 130, 3);
	}

	private void drawText(GraphicsContext g) {
		Font old = g.getFont();
		g.setFont(civInfoFont);
		g.setFill(textFill);
		g.setEffect(ds);
		g.fillText("Player: ", 10, 34);
		g.fillText("Turn: " + getAdapter().getTurnNum(), 180, 34);
		g.setEffect(null);
		g.setFont(old);
	}

	// Draw the panel itself
	private void drawBar(GraphicsContext g, Point screenDimensions) {
		g.drawImage(getImage("GUI_TOP"), 0, 0);
	}

	public void hideGUIElements() {
	}

	public void showGUIElements() {
	}
}
