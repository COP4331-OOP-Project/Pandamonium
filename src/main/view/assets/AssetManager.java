package view.assets;

import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.text.Font;

public class AssetManager {
	Assets assets;
	
	public AssetManager() {
		this.assets = Assets.getInstance();
		assets.loadResources();
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

	public Media getSplash() {
		return assets.getSplash();
	}
	
	public Media getIntro() {
		return assets.getIntro();
 	}

	public Media getGameMusic() {
		return assets.getGameMusic();
	}
	
	public Media getMenuMusic() {
		return assets.getMenuMusic();
	}
}
