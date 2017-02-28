package view;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

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

	public void draw(GraphicsContext gc, int x, int y, double scaleX, double scaleY, long currentTick) {
		currentTime += currentTick - lastTick;
		lastTick = currentTick;
		if (currentTime >= speed) {
			 cycleImage();
			 currentTime = 0;
		}
		gc.drawImage(animationImages[currentImage], x, y, scaleX, scaleY);
	}
}