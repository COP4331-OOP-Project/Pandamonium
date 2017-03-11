package controls;

import game.GameModel;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import view.View;

public class MouseEventController {
	GameModel game;
	View view;
	Scene scene;
	double mouseX = 0;
	double mouseY = 0;
	
	public MouseEventController(GameModel game, View view,  Scene scene) {
		this.game = game;
		this.view = view;
		this.scene = scene;
	}

	public void mouseDragged(MouseEvent event) {
		view.continueDragging(event.getX(),
				event.getY());
		if (!event.isShiftDown()) {
			view.tileClicked(event.getX(), event.getY());
		}
	}

	public void mouseClicked(MouseEvent event) {
		view.tileClicked(event.getX(), event.getY());
	}
	
	private void mouseMoved(MouseEvent event) {
		mouseX = event.getX();
		mouseY = event.getY();
	}
	
	protected void mouseReleased(MouseEvent event) {
	}

	protected void mousePressed(MouseEvent event) {
		view.startDragging(event.getSceneX(), event.getSceneY());
	}

	public void mouseScrolled(ScrollEvent event) {
		view.zoom(event.getDeltaY());
	}

	
	public void handleEvents() {
		scene.setOnMouseDragged(event -> mouseDragged(event));
    	scene.setOnMousePressed(event -> mousePressed(event));
    	scene.setOnMouseReleased(event -> mouseReleased(event));
    	scene.setOnMouseMoved(event -> mouseMoved(event));
    	scene.setOnScroll(event -> mouseScrolled(event));
    	scene.setOnMouseClicked(event -> mouseClicked(event));
	}


}
