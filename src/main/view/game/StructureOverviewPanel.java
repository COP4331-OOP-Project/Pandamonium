package view.game;

import java.awt.Point;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;
import view.GameModelAdapter;
import view.ViewEnum;
import view.assets.AssetManager;

public class StructureOverviewPanel extends OverviewPanel {
    public StructureOverviewPanel(GameModelAdapter gameModelAdapter, AssetManager assets, ViewEnum view) {
		super(gameModelAdapter, assets, view);
	}

    @Override
    public void draw(GraphicsContext gc, Point screenDimensions) {
        drawPanelBox(gc, screenDimensions);
        Font oldFont = gc.getFont();
        gc.setFont(getAssets().getFont(2));
        gc.fillText("Structure Overview", screenDimensions.x / 2 - 370, screenDimensions.y / 2 - 245);
        gc.setFont(getAssets().getFont(1));
        //for (int i = 0; i < game.getCurrentPlayer().getBaseCount(); i++) {
        //    gc.fillText("Base", screenDimensions.x / 2 - 370, screenDimensions.y / 2 + (i * 30) - 200);
        //}
        gc.setFont(oldFont);
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
