package view.game;

import game.entities.EntityId;
import game.entities.structures.Structure;
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
import view.game.drawers.*;

import java.awt.*;

import view.game.drawers.CoveringDrawer;
import view.game.drawers.ResourceDrawer;
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
    private StructureDrawer structureDrawer;
    private AreaEffectDrawer areaEffectDrawer;
    private OneShotItemDrawer oneShotItemDrawer;
    private ResourceDrawer resourceDrawer;
    private GraphicsContext g;
	private Point screenDimensions;
	private Point currentTile;
	private AssetManager assets;
	private ViewEnum view;
	private EntityId selectedEntity;
	private Point selectedPoint;
	private boolean resourcesVisible = false;
	private boolean unitsVisible = true;
	private boolean structuresVisible = true;
    private HealthBarDrawer healthBar;

    public GamePanel(GameModelAdapter gameModelAdapter, AssetManager assets, Camera camera, ViewEnum view) {
    	super(gameModelAdapter, assets, view);
    	this.camera = camera;
    	this.assets = assets;
    	this.view = view;
        screenDimensions = new Point();
        currentTile = new Point();
        tileDrawer = new TileDrawer(this, assets);
        unitDrawer = new UnitDrawer(assets, camera);
        structureDrawer = new StructureDrawer(this, gameModelAdapter, assets);
        resourceDrawer = new ResourceDrawer(gameModelAdapter, assets, camera);
        coveringDrawer = new CoveringDrawer(this, assets);
        areaEffectDrawer = new AreaEffectDrawer(this, assets);
        oneShotItemDrawer = new OneShotItemDrawer(this, assets);
        healthBar = new HealthBarDrawer(assets);
    }

    public void draw(GraphicsContext g, Point screenDimensions, long currentPulse) {
    	this.currentPulse = currentPulse;
    	selectedEntity = getAdapter().getSelectedEntity();
    	selectedPoint = getAdapter().getSelectedPoint();
		g.drawImage(assets.getImage("GAME_BACKGROUND"), 0, 0, screenDimensions.x, screenDimensions.y);
    	this.screenDimensions = screenDimensions;
        this.g = g;
        camera.adjustZoom(screenDimensions);
        camera.centerToSelected(selectedPoint, screenDimensions);
        drawAllItems();
    }


	public void endTurn() {
		camera.centerOnTile(getAdapter().getTurnStartPoint(), screenDimensions);
	}
    
	private void drawAllItems() {
		SimpleTile[][] currentTiles = getAdapter().getCurrentTiles();
        for (int i = 0; i < currentTiles.length; i++) {
            for (int j = 0; j < currentTiles[i].length; j++) {
                SimpleTile tile = currentTiles[i][j];
                currentTile.x = i;
                currentTile.y = j;
                if (tile.getTileType() != TerrainEnum.NON_TILE && tile.getVisibility() != TileVisibilityEnum.INVISIBLE) {
	                //Draw Tiles
	                tileDrawer.drawTile(currentTile, tile.getTileType());
	                if (structuresVisible && tile.getStructure() != null) {
	                    Structure structure = tile.getStructure();
	                    structureDrawer.drawStructure(currentTile, structure, selectedEntity);
	                }
	                if (unitsVisible && tile.getUnitCount() > 0) {
	                    unitDrawer.drawUnits(currentTile, tile.getUnits(), g, selectedEntity);
	                }
	                if (resourcesVisible) {
	                	resourceDrawer.drawResources(tile, currentTile, g);
	                }
	                if (tile.getAreaEffect() != null) {
	                    areaEffectDrawer.drawAreaEffect(currentTile, tile.getAreaEffect().getDecal());
                    }
                    if (tile.getOneShotItem() != null) {
	                    oneShotItemDrawer.drawOneShotItem(currentTile, tile.getOneShotItem().getDecal());
                    }
                }
                coveringDrawer.drawCovering(currentTile, tile.getTileType(), tile.getVisibility());
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
    
    public void drawHealthBar(double health, Point p) {
    	 Point position = new Point(camera.offset(p).x, camera.offset(p).y);
    	 healthBar.drawBar(position, health, camera.getScale(), g);
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
    

	public void centerOnSelected() {
		camera.centerOnTile(selectedPoint, screenDimensions);
	}
    
	public void hideGUIElements() {
	}

	public void showGUIElements() {
	}
}