package view.game.drawers;

import java.awt.Point;

import javafx.scene.canvas.GraphicsContext;
import view.assets.AssetManager;

public class TechnologyViewItem {
	private TechViewEnum researched = TechViewEnum.UNRESEARCED;
	private String text;
	private AssetManager assets;
	
	public TechnologyViewItem(AssetManager assets, String text) {
		this.assets = assets;
		this.text = text;
	}
	
	public void draw(GraphicsContext g, Point p) {
		g.drawImage(assets.getImage("TECHNOLOGY"), p.x, p.y);
		if (researched == TechViewEnum.RESEARCHING) {
			g.drawImage(assets.getImage("RESEARCHING"), p.x, p.y);
		} else if (researched == TechViewEnum.RESEARCHED) {
			g.drawImage(assets.getImage("RESEARCHED"), p.x, p.y);
		}
		g.setFont(assets.getFont(0));
		g.fillText(text, p.x, p.y);
	}
	
	public TechViewEnum getResearched() {
		return researched;
	}
	
	public void setTechViewEnum(TechViewEnum researched) {
		this.researched = researched;
	}
}
