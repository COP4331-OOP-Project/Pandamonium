package view.game.drawers;

import game.gameboard.SimpleTile;
import game.resources.Resource;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import view.GameModelAdapter;
import view.assets.AssetManager;
import view.game.Camera;

import java.awt.*;

public class ResourceDrawer{
    private GameModelAdapter gameModelAdapter;
    private AssetManager assetManager;
    private Camera camera;
    
    public ResourceDrawer(GameModelAdapter gameModelAdapter, AssetManager assetManager, 
    		Camera camera) {
    	this.gameModelAdapter = gameModelAdapter;
    	this.camera = camera;
    	this.assetManager = assetManager;
	}
    
    public void drawResources(SimpleTile tile, Point p, GraphicsContext g) {
    	if (tile.getResourcesProspected()) {
    		drawAllResources(tile, p, g);
    	}
    }

	private void drawAllResources(SimpleTile tile, Point p, GraphicsContext g) {
		String foodIconString = "";
    	if (gameModelAdapter.getPlayerId() == 0) {
    		foodIconString = "ICON_FOOD_HUMAN";
    	} else {
    		foodIconString = "ICON_FOOD_PANDA";
    	}

    	Resource food = tile.getFood();
    	Resource ore = tile.getOre();
    	Resource peat = tile.getPeat();
    	Image foodImage = assetManager.getImage(foodIconString);
    	Image peatImage = assetManager.getImage("ICON_PEAT");
    	Image oreImage = assetManager.getImage("ICON_ORE");
		g.setFont(Font.font(22 * camera.getScale()));
    	int x = camera.offset(p).x;
    	int y = camera.offset(p).y;
    	double scale = camera.getScale();
    	g.fillText((int)food.getAmount() + "", x + (60 * scale), y + (41 * scale));
    	g.fillText((int)ore.getAmount() + "", x + (60 * scale), y + (71 * scale));
    	g.fillText((int)peat.getAmount() + "", x + (60 * scale), y + (101 * scale));
    	g.drawImage(foodImage, x + (20 * scale), y + (15 * scale), scale * foodImage.getWidth(), 
        														   scale * foodImage.getHeight());
    	g.drawImage(peatImage, x + (20 * scale), y + (48 * scale), scale * peatImage.getWidth(), 
    															   scale * peatImage.getHeight());
    	g.drawImage(oreImage, x + (20 * scale), y + (75 * scale), scale * oreImage.getWidth(), 
													 scale * oreImage.getHeight());
	}
}
