package view.game.drawers;

import java.awt.Point;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import game.entities.EntityId;
import game.entities.EntitySubtypeEnum;
import game.entities.units.Unit;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import view.assets.AssetManager;
import view.game.Camera;

public class UnitDrawer {
    private static final Logger log = LogManager.getLogger(UnitDrawer.class);
    private static final int TILE_SIZE = 130;
    private AssetManager assets;
    private Camera camera;
    
    public UnitDrawer(AssetManager assets, Camera camera) {
        this.assets = assets;
        this.camera = camera;
    }
    

	public void drawUnits(Point p, ArrayList<Unit> units, GraphicsContext g) {
		switch (units.size()) {
			case 1 :
				drawUnit(p, new Point(36, 32), 1, units.get(0), g);
				break;
			case 2 :
				drawUnit(p, new Point(60, 32), 1, units.get(1), g);
				drawUnit(p, new Point(15, 32), 1, units.get(0), g);
				break;
			case 3 :
				drawUnit(p, new Point(58, 42), 1, units.get(2), g);
				drawUnit(p, new Point(37, 10), 1, units.get(1), g);
				drawUnit(p, new Point(15, 42), 1, units.get(0), g);
				break;
			case 4 :
				drawUnit(p, new Point(64, 10), 0.9, units.get(3), g);
				drawUnit(p, new Point(64, 54), 0.9, units.get(2), g);
				drawUnit(p, new Point(22, 10), 0.9, units.get(1), g);
				drawUnit(p, new Point(22, 54), 0.9, units.get(0), g);
				break;
			case 5 :
				drawUnit(p, new Point(75, 7), 0.9, units.get(4), g);
				drawUnit(p, new Point(45, 34), 0.9, units.get(2), g);
				drawUnit(p, new Point(75, 60), 0.9, units.get(3), g);
				drawUnit(p, new Point(15, 7), 0.9, units.get(1), g);
				drawUnit(p, new Point(15, 60), 0.9, units.get(0), g);
				break;
			case 6 :
				drawUnit(p, new Point(89, 22), 0.75, units.get(5), g);
				drawUnit(p, new Point(57, 22), 0.75, units.get(4), g);
				drawUnit(p, new Point(25, 22), 0.75, units.get(3), g);
				drawUnit(p, new Point(89, 78), 0.75, units.get(2), g);
				drawUnit(p, new Point(57, 78), 0.75, units.get(1), g);
				drawUnit(p, new Point(25, 78), 0.75, units.get(0), g);
				break;
			case 7 :
				drawUnit(p, new Point(118, 50), 0.70, units.get(6), g);
				drawUnit(p, new Point(86, 22), 0.70, units.get(5), g);
				drawUnit(p, new Point(54, 22), 0.70, units.get(4), g);
				drawUnit(p, new Point(22, 22), 0.70, units.get(3), g);
				drawUnit(p, new Point(86, 78), 0.70, units.get(2), g);
				drawUnit(p, new Point(54, 78), 0.70, units.get(1), g);
				drawUnit(p, new Point(22, 78), 0.70, units.get(0), g);
				break;
			case 8 :
				drawUnit(p, new Point(90, 20), 0.65, units.get(7), g);
				drawUnit(p, new Point(55, 20), 0.65, units.get(6), g);
				drawUnit(p, new Point(125, 60), 0.65, units.get(5), g);
				drawUnit(p, new Point(90, 60), 0.65, units.get(4), g);
				drawUnit(p, new Point(55, 60), 0.65, units.get(3), g);
				drawUnit(p, new Point(20, 60), 0.65, units.get(2), g);
				drawUnit(p, new Point(90, 100), 0.65, units.get(1), g);
				drawUnit(p, new Point(55, 100), 0.65, units.get(0), g);
				break;
			case 9 :
				drawUnit(p, new Point(108, 20), 0.65, units.get(8), g);
				drawUnit(p, new Point(73, 20), 0.65, units.get(7), g);
				drawUnit(p, new Point(38, 20), 0.65, units.get(6), g);
				drawUnit(p, new Point(125, 60), 0.65, units.get(5), g);
				drawUnit(p, new Point(90, 60), 0.65, units.get(4), g);
				drawUnit(p, new Point(55, 60), 0.65, units.get(3), g);
				drawUnit(p, new Point(20, 60), 0.65, units.get(2), g);
				drawUnit(p, new Point(90, 100), 0.65, units.get(1), g);
				drawUnit(p, new Point(55, 100), 0.65, units.get(0), g);
				break;
			case 10 :
				drawUnit(p, new Point(108, 20), 0.65, units.get(9), g);
				drawUnit(p, new Point(73, 20), 0.65, units.get(8), g);
				drawUnit(p, new Point(38, 20), 0.65, units.get(7), g);
				drawUnit(p, new Point(125, 60), 0.65, units.get(6), g);
				drawUnit(p, new Point(90, 60), 0.65, units.get(5), g);
				drawUnit(p, new Point(55, 60), 0.65, units.get(4), g);
				drawUnit(p, new Point(20, 60), 0.65, units.get(3), g);
				drawUnit(p, new Point(108, 100), 0.65, units.get(2), g);
				drawUnit(p, new Point(73, 100), 0.65, units.get(1), g);
				drawUnit(p, new Point(38, 100), 0.65, units.get(0), g);
				break;
			case 11 :
				drawUnit(p, new Point(128, 15), 0.60, units.get(10), g);
				drawUnit(p, new Point(98, 15), 0.60, units.get(9), g);
				drawUnit(p, new Point(68, 15), 0.60, units.get(8), g);
				drawUnit(p, new Point(38, 15), 0.60, units.get(7), g);
				drawUnit(p, new Point(140, 65), 0.60, units.get(6), g);
				drawUnit(p, new Point(100, 65), 0.60, units.get(5), g);
				drawUnit(p, new Point(60, 65), 0.60, units.get(4), g);
				drawUnit(p, new Point(20, 65), 0.60, units.get(3), g);
				drawUnit(p, new Point(120, 115), 0.60, units.get(2), g);
				drawUnit(p, new Point(80, 115), 0.60, units.get(1), g);
				drawUnit(p, new Point(40, 115), 0.60, units.get(0), g);
				break;
			case 12 :
				drawUnit(p, new Point(128, 15), 0.60, units.get(11), g);
				drawUnit(p, new Point(98, 15), 0.60, units.get(10), g);
				drawUnit(p, new Point(68, 15), 0.60, units.get(9), g);
				drawUnit(p, new Point(38, 15), 0.60, units.get(8), g);
				drawUnit(p, new Point(140, 65), 0.60, units.get(7), g);
				drawUnit(p, new Point(100, 65), 0.60, units.get(6), g);
				drawUnit(p, new Point(60, 65), 0.60, units.get(5), g);
				drawUnit(p, new Point(20, 65), 0.60, units.get(4), g);
				drawUnit(p, new Point(128, 115), 0.60, units.get(3), g);
				drawUnit(p, new Point(98, 115), 0.60, units.get(2), g);
				drawUnit(p, new Point(68, 115), 0.60, units.get(1), g);
				drawUnit(p, new Point(38, 115), 0.60, units.get(0), g);
				break;
				//If you have more than 13 units on a tile, it just draws the first 13
			default: //You can obviously still cycle through the others through
				drawUnit(p, new Point(128, 15), 0.60, units.get(12), g);
				drawUnit(p, new Point(98, 15), 0.60, units.get(11), g);
				drawUnit(p, new Point(68, 15), 0.60, units.get(10), g);
				drawUnit(p, new Point(38, 15), 0.60, units.get(9), g);
				drawUnit(p, new Point(140, 65), 0.60, units.get(8), g);
				drawUnit(p, new Point(110, 65), 0.60, units.get(7), g);
				drawUnit(p, new Point(80, 65), 0.60, units.get(6), g);
				drawUnit(p, new Point(50, 65), 0.60, units.get(5), g);
				drawUnit(p, new Point(20, 65), 0.60, units.get(4), g);
				drawUnit(p, new Point(128, 115), 0.60, units.get(3), g);
				drawUnit(p, new Point(98, 115), 0.60, units.get(2), g);
				drawUnit(p, new Point(68, 115), 0.60, units.get(1), g);
				drawUnit(p, new Point(38, 115), 0.60, units.get(0), g);
				break;
		}
	}

	public void drawUnit(Point p, Point offset, double scale, Unit unit, GraphicsContext g) {
		EntityId entityId = unit.getEntityId();
		EntitySubtypeEnum type = unit.getType();
		int player = entityId.getPlayerId();
		switch (player) {
			case 0 :
				drawItem(p, offset, "UNIT_SMALL_HUMAN", scale, g);
				break;
			case 1 :
				drawItem(p, offset, "UNIT_SMALL_PANDA", scale, g);
				break;
		}
		switch (type) {
			case COLONIST:
				if (player == 0)
					drawItem(p, offset, "DECAL_COLONIST_HUMAN", scale, g);
				else
					drawItem(p, offset, "DECAL_COLONIST_PANDA", scale, g);
				break;
			case EXPLORER:
				drawItem(p, offset, "DECAL_EXPLORER", scale, g);
				break;
			case MELEE:
				drawItem(p, offset, "DECAL_MELEE", scale, g);
				break;
			case RANGE:
				drawItem(p, offset, "DECAL_RANGED",scale, g);
				break;
			default :
				break;
		}	
	}
	
    public void drawItem(Point p, Point offset, String image, double scale, GraphicsContext g) {
    	Image img = assets.getImage(image);
    	g.drawImage(img, camera.offset(p).x + (offset.x * camera.getScale() * scale), camera.offset(p).y + (offset.y * camera.getScale() * scale), 
    			camera.getScale() * img.getWidth() * scale, camera.getScale() * img.getHeight() * scale);
    }
}
