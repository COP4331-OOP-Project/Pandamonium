package view.game.drawers;

import java.awt.Point;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.ColorAdjust;
import view.assets.AssetManager;
import view.game.ControlModePanel;

public class HealthBarDrawer{
	private final static Logger log = LogManager.getLogger(HealthBarDrawer.class);
	private AssetManager assets;
	ColorAdjust colorAdjust = new ColorAdjust();
	
	public HealthBarDrawer(AssetManager assets) {
		this.assets = assets;
	}
	
	public void drawBar(Point position, double health, GraphicsContext g) {
		if (health > 0 && health <= 1) {
			double hue = (1 - health) * (-0.65);
			colorAdjust.setHue(hue);
			g.setEffect(colorAdjust);
			g.drawImage(assets.getImage("HEALTH_BAR"), position.x, position.y);
			g.drawImage(assets.getImage("HEALTH_BAR_FILL"), position.x + 5, position.y + 5,
					assets.getImageWidth("HEALTH_BAR_FILL") * health , assets.getImageHeight("HEALTH_BAR_FILL"));
			g.setEffect(null);
		} else {
			log.error("Can't draw health bar, invalid health amount: " + health);
		}
	}

}
