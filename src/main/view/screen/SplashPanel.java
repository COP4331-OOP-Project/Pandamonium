package view.screen;

import java.awt.Point;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import view.GameModelAdapter;
import view.Panel;
import view.PanelManager;
import view.ViewEnum;
import view.assets.AssetManager;

public class SplashPanel extends Panel{
	PanelManager panelManager;
	Group root;
	Media splash = getAssets().getSplash();
	StackPane video = new StackPane();
	final MediaPlayer player = new MediaPlayer(splash);
	final MediaView view = new MediaView(player);
	private boolean splashStarted = false;
	
	public SplashPanel(GameModelAdapter gameModelAdapter, Group root, PanelManager panelManager, AssetManager assets,
			ViewEnum viewEnum) {
		super(gameModelAdapter, assets, viewEnum);
		this.root = root;
		checkSkipped();
		this.panelManager = panelManager;
		DoubleProperty viewWidth = view.fitWidthProperty();
		DoubleProperty viewHeight = view.fitHeightProperty();
		viewWidth.bind(Bindings.selectDouble(view.sceneProperty(), "width"));
		viewHeight.bind(Bindings.selectDouble(view.sceneProperty(), "height"));
	    view.setPreserveRatio(true);
	    video.getChildren().add(view);
	}

	private void checkSkipped() {
		root.setOnMouseReleased(new EventHandler<MouseEvent>() { //Click to Skip
			public void handle(MouseEvent event) {
				if (player.getStatus() == MediaPlayer.Status.PLAYING) {
					player.stop();
	                panelManager.setMode(ViewEnum.MAIN_MENU); //Skip if key pressed
				}
            }
        });

	}

	public void draw(GraphicsContext gc, Point screenDimensions, long currentPulse) {
		if (!splashStarted) {
			playSplashVideo();
			splashStarted = true;
		} else {
			if (player.getCurrentTime().toMillis() == 4907) {
				player.stop();
				panelManager.setMode(ViewEnum.MAIN_MENU);
			}
		}
	}

	private void playSplashVideo() {
		player.play();
	}

	public void hideGUIElements() {
		root.getChildren().remove(video);
	}

	public void showGUIElements() {
		root.getChildren().add(video);
	}

}
