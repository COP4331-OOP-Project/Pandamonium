package view;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;

public class RotationAnimation extends Animation{
	private int degrees;
	private double rotSpeed;
	private int currentDegree = 0;
	private long lastPulse = 0;
	private boolean backAndForth;
	private boolean goingBackward = false;
	private boolean rotationStarted = false;
	public RotationAnimation(Image[] animationImages, int speed, double rotSpeed, boolean backAndForth, int degrees) {
		super(animationImages, speed);
		this.backAndForth = backAndForth;
		this.degrees = degrees;
		this.rotSpeed = rotSpeed;
	}
	
	@Override
	public void drawImage(GraphicsContext gc, Image image, int x, int y, double scaleX, double scaleY, long currentPulse) {
		updateRotation(currentPulse);
		Affine currentRotation = gc.getTransform();
		Rotate rotate = new Rotate(currentDegree, (double) (x + image.getWidth()/2),(double) (y + image.getHeight()/2));
        gc.setTransform(rotate.getMxx(), rotate.getMyx(), rotate.getMxy(), rotate.getMyy(), 
        		rotate.getTx(), rotate.getTy());
		gc.drawImage(image, x, y, image.getWidth() * scaleX, image.getHeight() * scaleY);
		gc.setTransform(currentRotation);
	}

	private void updateRotation(long currentPulse) {
		if (!rotationStarted) {
			lastPulse = currentPulse;
			rotationStarted = true;
		}
		double rotationChange = (currentPulse - lastPulse)/15000000 * rotSpeed;
		if (backAndForth) {
			if (currentDegree < -degrees || currentDegree > degrees) {
				currentDegree = 0;
			}
			if (goingBackward) {
				currentDegree -= rotationChange;
				lastPulse = currentPulse;
				//System.out.println(currentDegree);
				if (currentDegree <= -degrees) {
					goingBackward = false;
				}
			} else {
				currentDegree += rotationChange;
				lastPulse = currentPulse;
				if (currentDegree >= degrees) {
					goingBackward = true;
				}
			}
		} else {
			currentDegree -= rotationChange;
			lastPulse = currentPulse;
			currentDegree = currentDegree % 360;
		}
	}
}
