package view.game;

import java.awt.Point;

import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;
import view.GameModelAdapter;
import view.Panel;
import view.ViewEnum;
import view.assets.AssetManager;

public class HoverPanel extends Panel {
	String text;
    Group root;
	
    public HoverPanel(GameModelAdapter gameModelAdapter, AssetManager assets, ViewEnum view) {
		super(gameModelAdapter, assets, view);
	}

    public void drawText(GraphicsContext g, Point location, String text) {
        Font oldFont = g.getFont();
        g.drawImage(getAssets().getImage("GUI_HOVER"), location.x, location.y);
        g.setFont(getAssets().getFont(0));
        g.fillText(text, location.x + 10, location.y + 25);
        g.setFont(oldFont);
    }

    @Override
    public void draw(GraphicsContext g, Point screenDimensions, long currentPulse) {
    }

    @Override
    public void hideGUIElements() {
    }

    @Override
    public void showGUIElements() {
    }
}
