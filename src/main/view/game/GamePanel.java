package view.game;

import java.awt.Point;

import game.entities.structures.Structure;
import game.entities.units.Unit;
import game.gameboard.SimpleTile;
import game.gameboard.TerrainEnum;
import game.gameboard.TileVisibilityEnum;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;
import view.Animation;
import view.GameModelAdapter;
import view.Panel;
import view.ViewEnum;
import view.assets.AssetManager;
import view.game.drawers.ArmyDrawer;
import view.game.drawers.CoveringDrawer;
import view.game.drawers.ResourceDrawer;
import view.game.drawers.SelectedDrawer;
import view.game.drawers.StructureDrawer;
import view.game.drawers.TileDrawer;
import view.game.drawers.UnitDrawer;

public class GamePanel extends Panel {
    private static final int TILE_PIXEL_SIZE = 130;
    private Camera camera;
    private long currentPulse;
    private TileDrawer tileDrawer;
    private CoveringDrawer coveringDrawer;
    private UnitDrawer unitDrawer;
    private ArmyDrawer armyDrawer;
    private StructureDrawer structureDrawer;
    private SelectedDrawer selectedDrawer;
    private ResourceDrawer resourceDrawer;
    private GraphicsContext g;
	private Point screenDimensions;
	private AssetManager assets;
	private ViewEnum view;
	private boolean resourcesVisible = false;
	private boolean unitsVisible = true;
	private boolean structuresVisible = true;

    public GamePanel(GameModelAdapter gameModelAdapter, AssetManager assets, Camera camera, ViewEnum view) {
    	super(gameModelAdapter, assets, view);
    	this.camera = camera;
    	this.assets = assets;
    	this.view = view;
        screenDimensions = new Point();
        tileDrawer = new TileDrawer(this, assets);
        unitDrawer = new UnitDrawer(assets, camera);
        armyDrawer = new ArmyDrawer(this, gameModelAdapter, assets);
        structureDrawer = new StructureDrawer(this, gameModelAdapter, assets);
        selectedDrawer = new SelectedDrawer(this, gameModelAdapter, assets);
        resourceDrawer = new ResourceDrawer(gameModelAdapter, assets, camera);
        coveringDrawer = new CoveringDrawer(this, assets);
    }

    public void draw(GraphicsContext g, Point screenDimensions, long currentPulse) {
    	this.currentPulse = currentPulse;
		g.drawImage(assets.getImage("GAME_BACKGROUND"), 0, 0, screenDimensions.x, screenDimensions.y);
    	this.screenDimensions = screenDimensions;
        this.g = g;
        /*
        Point selected = new Point(game.getCenterCoordinates().getX(),
				   game.getCenterCoordinates().getY());
		*/
        camera.adjustZoom(screenDimensions);
        //camera.centerToSelected(selected, screenDimensions);
        drawAllItems();
        //selectedDrawer.drawSelectedItemOutline();
        //tileDrawer.drawMovingTiles();
    }

	private void drawAllItems() {
		SimpleTile[][] currentTiles = getAdapter().getCurrentTiles();
        for (int i = 0; i < currentTiles.length; i++) {
            for (int j = 0; j < currentTiles[i].length; j++) {
                SimpleTile tile = currentTiles[i][j];
                Point p = new Point(i, j);
                if (tile.getTileType() != TerrainEnum.NON_TILE && tile.getVisibility() != TileVisibilityEnum.INVISIBLE) {
	                //Draw Tiles
	                tileDrawer.drawTile(p, tile.getTileType());
	                if (unitsVisible && tile.getUnitCount() > 0) {
	                    unitDrawer.drawUnits(p, tile.getUnits(), g);
	                }
	                if (structuresVisible && tile.getStructure() != null) {
	                    Structure structure = tile.getStructure();
	                    structureDrawer.drawStructure(p, structure.getOwnerID(), structure.getType());
	                }
	                if (resourcesVisible) {
	                	resourceDrawer.drawResources(tile, p, g);
	                }
                } 
                coveringDrawer.drawCovering(p, tile.getTileType(), tile.getVisibility());
            }
         }   
    }

    public void drawStaticTileElement(Point p, String image) {
    	Image img = getImage(image);
    	g.drawImage(img, camera.offset(p).x, camera.offset(p).y, camera.getScale() * img.getWidth(), 
        		camera.getScale() * img.getHeight());
    }

    public void drawStaticTileElement(Point p, int rotation, String image) {
        Image img = getImage(image);
    	Affine currentRotation = g.getTransform();
        rotateOnTile(p, rotation);
        g.drawImage(img, camera.offset(p).x, camera.offset(p).y, camera.getScale() * img.getWidth(), 
        		camera.getScale() * img.getHeight());
        g.setTransform(currentRotation);
    }

    private void rotateOnTile(Point p, int degrees) {
        Rotate rotate = new Rotate(degrees,
                (double) (camera.getTileCenter(p).x),
                (double) (camera.getTileCenter(p).y));
        g.setTransform(rotate.getMxx(), rotate.getMyx(), rotate.getMxy(), rotate.getMyy(), 
        		rotate.getTx(), rotate.getTy());
    }

    public void drawAnimatedTileElement(Point p, Animation a) {
    	a.draw(g, camera.offset(p).x, camera.offset(p).y, camera.getScale(), 
        		camera.getScale(), currentPulse);
     }

    public Camera getCamera() {
        return camera;
    }

    public GraphicsContext getGC() {
        return g;
    }

    public int getTileSize() {
        return TILE_PIXEL_SIZE;
    }

    public void toggleResources() {
    	resourcesVisible = !resourcesVisible;
    }
    
    public void toggleUnits() {
    	unitsVisible = !unitsVisible;
    }
    
    public void toggleStructures() {
    	structuresVisible = !structuresVisible;
    }
    
	public void hideGUIElements() {
	}

	public void showGUIElements() {
	}
}