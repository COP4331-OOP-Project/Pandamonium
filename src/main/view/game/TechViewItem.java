package view.game;

import java.awt.Point;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import view.assets.AssetManager;

public class TechViewItem {
	private TechViewEnum researched = TechViewEnum.UNRESEARCED;
	private String text;
	private Image icon;
	private String improvement;
	private AssetManager assets;
	private Font font = Font.font(17);
	private DropShadow ds = new DropShadow();
	
	public TechViewItem(AssetManager assets, String text, Image icon, String improvement) {
		this.assets = assets;
		this.text = text;
		this.icon = icon;
		this.improvement = improvement;
    	ds.setOffsetY(2.0);
    	ds.setColor(Color.color(0, 0, 0));
	}
	
	public void draw(GraphicsContext g, Point p) {
		g.drawImage(assets.getImage("TECHNOLOGY"), p.x, p.y);
		if (researched == TechViewEnum.RESEARCHING) {
			g.drawImage(assets.getImage("RESEARCHING"), p.x, p.y);
		} else if (researched == TechViewEnum.RESEARCHED) {
			g.drawImage(assets.getImage("RESEARCHED"), p.x, p.y);
		}
		g.drawImage(icon, p.x + 25, p.y + 47, 0.8 * icon.getWidth(), 0.8 * icon.getHeight());
		g.setEffect(ds);
		g.setFont(assets.getFont(1));
		g.fillText(text, p.x + 25, p.y + 40);
		g.setFont(font);
		g.fillText(improvement, p.x + 60, p.y + 68);
		g.setEffect(null);
	}
	
	public void setIcon(Image icon) {
		this.icon = icon;
	}
	
	public TechViewEnum getResearched() {
		return researched;
	}
	
	public void setTechViewEnum(TechViewEnum researched) {
		this.researched = researched;
	}
}
