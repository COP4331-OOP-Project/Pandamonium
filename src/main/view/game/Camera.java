package view.game;

import java.awt.Point;
import view.Assets;

public class Camera {
    private static final int HEX_W = 130;
    private static final int HEX_H = (int)(HEX_W * 0.86);
    private static final int TILE_SIZE = HEX_W;
	private static final double SCALE_AMOUNT = 0.05; //The amount change per zoom
	private static final double MIN_SCALE = 0.4; //Min amount to be zoomed in
	private static final double MAX_SCALE = 1.0; //Max amount to be zoomed in
	
    private CameraCenterer panelCenterer;
    private Point screenDimensions;
    private Point offset = new Point(180, -2350);
    
    //These values are used when dragging the Camera.
    private double dragX = -999;
    private double dragY = -999;
    
    //These values are used when zooming the Camera.
    private double scale = 0.8;
	private boolean zooming = false;
	private int zoomCounter = -1;
	Point mouseZoomStart = new Point(0,0);

	
    public Camera(Point screenDimensions) {
    	this.screenDimensions = screenDimensions;
        this.panelCenterer = new CameraCenterer(this);
    }
    
    /**
     * This function handles the changes to the camera that
     * must be handled every tick, such as checking the centering
     * and zooming.
     */
	public void reAlign(Point selected) {
        checkZooming();
        if (!zooming) {
        	panelCenterer.recenter(screenDimensions.x, screenDimensions.y);
        }

        if (selected.x != 0 && selected.y != 0) {
            panelCenterer.centerOnTile(selected);
        }
	}
    
    /**
     * This functions takes in a <b>Point</b> in pixels from the
     * top left corner of the screen and returns a
     * <b>Point</b> that corresponds to the coordinates of a
     * tile.
     * 
     * @param pixel point in pixels from corner of screen
     * @return corresponding tile location of the pixels
     */
    public Point getTileLocation(Point pixel) {
    	Point p = new Point(0,0);
		p.x = (int)((pixel.x - offset.x) / (0.75f * scale * HEX_W ));
		p.y = (int)(((pixel.y - offset.y)-((0.5 * HEX_H * scale) * 
				((pixel.x - offset.x)/(0.75 * scale * HEX_W))))/(HEX_H * scale));
		Point r = new Point(getPixelLocation(p).x - pixel.x + offset.x, getPixelLocation(p).y - pixel.y + offset.y);
		if (r.y < -117 * scale) {
			p.y++;
		}
		r.x = getPixelLocation(p).x - pixel.x + offset.x;
		r.y = getPixelLocation(p).y - pixel.y + offset.y;
		if (r.y <= -7 * scale && r.y >= -63 * scale) {
			if (r.x > ((r.y + 63 * scale)/-1.75)) { //Top Left Triangle
				p.x--;
			}
		} else if (r.y < -63 * scale && r.y >= -117 * scale) {
			if (r.x > ((r.y + 63 * scale)/1.75)) { //Bottom Left Triangle
				p.x--;
				p.y++;
			}
		}
    	return p;
    }
    
    public Point getTileCenter(Point tile) {
    	return new Point ((getPixelLocation(tile).x + TILE_SIZE / 2),
    					  (getPixelLocation(tile).y + TILE_SIZE / 2));
    }
    
	public void zoom(double deltaY) {
		Point p = new Point((int)screenDimensions.x/2, (int)screenDimensions.y/2);
		zoomCounter = 0;
		if (!zooming) {
			mouseZoomStart = getTileLocation(p);
		}
		zooming = true;
		if (deltaY > 0) {
			if (scale < MAX_SCALE) {
				scale += SCALE_AMOUNT;
				panelCenterer.quickCenter(mouseZoomStart);
			}
		} else {
			if (scale > MIN_SCALE) {
				scale -= SCALE_AMOUNT;
				panelCenterer.quickCenter(mouseZoomStart);
			}
		}
	}
	
	public void startDragging(double x, double y) {
		dragX = x;
		dragY = y;
	}
	
	public void continueDragging(double x, double y) {
		double diffX = dragX - x;
		double diffY = dragY - y;
		setOffset(new Point(getOffset().x - (int)diffX,
				(getOffset().y - (int)diffY)));
		dragX = x;
		dragY = y;
	}

    protected Point getOffset() {
    	return offset;
    }
    
    public void setOffset(Point point) {
        offset = point;
    }
    
    public void setScale(double scale) {
    	this.scale = scale;
    }
    
    public double getScale() {
    	return scale;
    }

    public Point offset(Point p) {
        return new Point(getPixelLocation(p).x + offset.x,
        		getPixelLocation(p).y + offset.y);
    }
    
	private void checkZooming() {
    	if (zoomCounter != -1) {
    		zoomCounter++;
    		if (zoomCounter > 40) {
    			zoomCounter = -1;
    			zooming = false;
    			panelCenterer.stopCentering();
    		}
    	}
	}
    
    private Point getPixelLocation(Point tile) {
    	return new Point((int)(0.75f * scale * HEX_W * tile.x),
        				 (int)(HEX_H * scale * (tile.x * 0.5f + tile.y)));
    }
}
