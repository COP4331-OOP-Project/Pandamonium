package controls;

import game.GameModel;
import javafx.event.EventHandler;
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
		scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mouseDragged(event);
            }
        });
    	
    	scene.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mousePressed(event);
            }
        });
    	
    	scene.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mouseReleased(event);
            }
        });
    	
    	scene.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mouseMoved(event);
            }
        });
    	
    	scene.setOnScroll(new EventHandler<ScrollEvent>() {
            @Override
            public void handle(ScrollEvent event) {
               mouseScrolled(event);
            }
        });
    	
    	scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mouseClicked(event);
            }
        });
	}


}
