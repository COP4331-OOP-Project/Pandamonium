package view;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class RotationAnimation extends Animation{
	private int degrees;
	private boolean backAndForth;
	public RotationAnimation(Image[] animationImages, int speed, boolean backAndForth, int degrees) {
		super(animationImages, speed);
		this.backAndForth = backAndForth;
		this.degrees = degrees;
	}
	
	@Override
	public void drawImage(GraphicsContext gc, Image image, int x, int y, double scaleX, double scaleY) {
		gc.drawImage(image, x, y, image.getWidth() * scaleX, image.getHeight() * scaleY);
	}

}
