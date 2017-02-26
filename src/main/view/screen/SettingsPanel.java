package view.screen;

import java.awt.Point;
import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import view.Assets;
import view.Panel;
import view.View;
import view.ViewEnum;

public class SettingsPanel extends Panel{
	View view;
	
	public SettingsPanel(Group root, View view, Assets assets, ViewEnum viewEnum) {
		super(assets, viewEnum);
		this.view = view;
	}

	@Override
	public void draw(GraphicsContext gc, Point screenDimensions) {
	}

	@Override
	public void hideGUIElements() {
	}

	@Override
	public void showGUIElements() {
	}

}
