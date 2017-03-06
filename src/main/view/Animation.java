package view;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;

public class Animation {
	 private Image[] animationImages;
	 private int speed;
	 private int currentTime = 0;
	 private long lastTick = 0;
	 private int currentImage = 0;
	 public Animation(Image[] animationImages, int speed) {
		 this.animationImages = animationImages;
		 this.speed = speed;
	 }
	 
	 private void cycleImage() {
		 currentImage = (currentImage + 1) % animationImages.length; 
	 }

	public void draw(GraphicsContext g, int x, int y, double scaleX, double scaleY, long currentTick) {
		currentTime += currentTick - lastTick;
		lastTick = currentTick;
		if (currentTime >= speed) {
			 cycleImage();
			 currentTime = 0;
		}
		drawImage(g, animationImages[currentImage], x, y, scaleX, scaleY, currentTick);
	}
	
	public void drawImage(GraphicsContext g, Image image, int x, int y, double scaleX, double scaleY, long currentTick) {
		g.drawImage(image, x, y, image.getWidth() * scaleX, image.getHeight() * scaleY);
	}
}