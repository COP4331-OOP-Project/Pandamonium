package view.game;

import java.awt.Point;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;
import view.GameModelAdapter;
import view.ViewEnum;
import view.assets.AssetManager;

public class UnitOverviewPanel extends OverviewPanel {
	
    public UnitOverviewPanel(GameModelAdapter gameModelAdapter, AssetManager assets, ViewEnum viewEnum) {
    	super(gameModelAdapter, assets, viewEnum);
    	setIsVisible(false);
    }

    public void draw(GraphicsContext g, Point screenDimensions, long currentPulse) {
        drawPanelBox(g, screenDimensions);
        Font oldFont = g.getFont();
        
        g.setFont(getAssets().getFont(2));
        g.fillText("Unit Overview", screenDimensions.x / 2 - 370, screenDimensions.y / 2 - 245);
        g.setFont(getAssets().getFont(1));
        /*
        g.fillText("Unit Overview", screenDimensions.x / 2 - 370, screenDimensions.y / 2 - 245);
        for (int i = 0; i < game.getCurrentPlayer().getAllUnit().size(); i++) {
            String unitString = "";
            UnitEnum unit = game.getCurrentPlayer().getAllUnit().get(i).getUnitType();
            if (unit == UnitEnum.MELEE) {
                unitString = "Melee";
            }
            if (unit == UnitEnum.RANGED) {
                unitString = "Ranged";
            }
            if (unit == UnitEnum.EXPLORER) {
                unitString = "Explorer";
            }
            if (unit == UnitEnum.COLONIST) {
                unitString = "Colonist";
            }
            if (game.getSelectedUnit() == i) {
            	g.setFont(Assets.getInstance().getFont(1).deriveFont(Font.BOLD));
            } else {
            g.setFont(getAssets().getFont(1));
            }
            g.fillText(unitString, screenDimensions.x / 2 - 370, screenDimensions.y / 2 + (i * 30) - 200);
           
        }
        
        g.setFont(oldFont);
         */
    }

	public void hideGUIElements() {
	}

	public void showGUIElements() {
	}
}
