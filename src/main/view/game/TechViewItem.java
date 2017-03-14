package view.game;

import java.awt.Point;

import game.research.techTree.nodeTypes.TechNodeImageEnum;
import game.research.techTree.nodeTypes.TechTreeNode;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import view.GameModelAdapter;
import view.assets.AssetManager;

public class TechViewItem {
	private TechViewEnum researched = TechViewEnum.UNRESEARCHED;
	private TechTreeNode techNode;
	private String text;
	private Image icon;
	private String improvement;
	private AssetManager assets;
	private GameModelAdapter adapter;
	private Font font = Font.font(17);
	private DropShadow ds = new DropShadow();
	
	public TechViewItem(AssetManager assets, TechTreeNode techNode, GameModelAdapter adapter) {
		this.assets = assets;
		text = techNode.getName();
		improvement = techNode.getDescription();
		getIcon(techNode.getImageEnum());
    	ds.setOffsetY(2.0);
    	ds.setColor(Color.color(0, 0, 0));
	}
	
	private void getIcon(TechNodeImageEnum imageEnum) {
		switch (imageEnum) {
		case FOOD:
			if (adapter.getPlayer() == 0) {
				icon = assets.getImage("ICON_FOOD_HUMAN");
			} else {
				icon = assets.getImage("ICON_FOOD_PANDA");
			}
			break;
		case WORKER_RADIUS:
			icon = assets.getImage("WORK_RADIUS");
			break;
		case METAL:
			icon = assets.getImage("ICON_METAL");
			break;
		case POWER:
			icon = assets.getImage("ICON_POWER");
			break;
		case BREEDING:
			icon = assets.getImage("BREED_WORKER");
			break;
		case WORKER_DENSITY:
			icon = assets.getImage("WORKER_ON_TILE");
			break;
		case SOLDIER_POINTS:
			if (adapter.getPlayer() == 0) {
				icon = assets.getImage("WORKER_TO_SOLDIER_HUMAN");
			} else {
				icon = assets.getImage("WORKER_TO_SOLDIER_PANDA");
			}
			break;
		}
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
	
	public void onClick() {
		//This functionality below is just a test of clicking on the technology.
		//This needs to be replaced with actual technology function on clicking
		//on the technology.
		if (researched == TechViewEnum.RESEARCHED) {
			researched = TechViewEnum.RESEARCHING;
		} else {
			researched = TechViewEnum.RESEARCHED;
		}
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
