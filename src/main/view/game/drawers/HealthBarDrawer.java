package view.game.drawers;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;

import java.awt.Point;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import view.assets.AssetManager;


public class HealthBarDrawer {
	private final static Logger log = LogManager.getLogger(HealthBarDrawer.class);
	private AssetManager assets;
	private ColorAdjust colorAdjust = new ColorAdjust();

	public HealthBarDrawer(AssetManager assets) {
		this.assets = assets;
	}

	public void drawBar(Point position, double health, double scale, GraphicsContext g) {
		scale = 0.8 * scale;
		if (health > 0 && health <= 1) {
			double hue = (1 - health) * (-0.65);
			colorAdjust.setHue(hue);
			g.setEffect(colorAdjust);
			Image healthBarImage = assets.getImage("HEALTH_BAR");
			Image healthBarFill = assets.getImage("HEALTH_BAR_FILL");
			g.drawImage(healthBarImage, position.x + (15 * scale), position.y + (5 * scale), healthBarImage.getWidth() * scale ,healthBarImage.getHeight() * scale);
			g.drawImage(healthBarFill, position.x + (20 * scale), position.y + (10 * scale),
					(healthBarFill.getWidth() * health) * scale,
					healthBarFill.getHeight() * scale);
			g.setEffect(null);
		} else {
			log.error("Can't draw health bar, invalid health amount: " + health);
		}
	}
}
