package view;

import javafx.scene.image.Image;
import javafx.scene.text.Font;

public class AssetManager {
	Assets assets;
	
	public AssetManager() {
		this.assets = Assets.getInstance();
	}
	
	public Image getImage(String image) {
		return assets.getImage(image);
	}
	
	public int getImageWidth(String image) {
		return (int) assets.getImage(image).getWidth();
	}
	
	public int getImageHeight(String image) {
		return (int) assets.getImage(image).getHeight();
	}
	
	public Font getFont(int size) {
		return assets.getFont(size);
	}
}
