package view.game;

import java.awt.Point;

public class CameraCenterer {
    private static final int TIME_TO_CENTER = 32;
    //GamePanel gamePanel;
    private int timeCentering = 0;
    private boolean isCentering = false;
    private Point centerStart = new Point(-1,-1);
    private Point centerTo = new Point(-1,-1);
    private int width = 0;
    private int height = 0;
    private Camera camera;

    public CameraCenterer(Camera camera) {
        this.camera = camera;
    }

    public void recenter(int width, int height) {
        this.width = width;
        this.height = height;
        if (isCentering)
            continueCentering();
    }

    public void recenterOnTile(Point p) {
        if (centeringOffset(p).x != centerTo.x ||
                centeringOffset(p).y != centerTo.y) {
            centerStart = camera.getOffset();
            centerTo = centeringOffset(p);
            isCentering = true;
        }
    }
    
    public void centerOnTile(Point p) {
            centerStart = camera.getOffset();
            centerTo = centeringOffset(p);
            isCentering = true;
    }
    
	public void quickCenter(Point tile) {
		camera.setOffset(centeringOffset(tile));
	}
    
	
    private Point centeringOffset(Point p) {
        return new Point((width / 2) - camera.getTileCenter(p).x,
        				((height / 2) - camera.getTileCenter(p).y));
    }


    private void continueCentering() {
        if (timeCentering >= TIME_TO_CENTER - 1) {
            camera.setOffset(new Point(centerTo.x, centerTo.y));
            timeCentering = 0;
            isCentering = false;
        } else {
           Point offset = new Point();
           offset.x = (int)((percentDoneCentering() * (centerTo.x - centerStart.x)) + centerStart.x);
           offset.y = (int)(percentDoneCentering() * (centerTo.y - centerStart.y) + centerStart.y);
           camera.setOffset(offset);
           timeCentering++;
        }
    }

    //How close to the destination the view is
    private double percentDoneCentering() {
        return (Math.log(((double) timeCentering / (double) TIME_TO_CENTER * (19.1)) + 1)) / 3;
    }
    
   	public void stopCentering() {
    	isCentering = false;
    	timeCentering = 0;
    }

}
