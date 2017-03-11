package view.screen;

import java.awt.Point;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
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

public class SplashPanel extends Panel {
	private static final int ASPECT_RATIO_X = 16;
	private static final int ASPECT_RATIO_Y = 9;
	private PanelManager panelManager;
	private Group root;
	private Media splash = getAssets().getSplash();
	private StackPane video = new StackPane();
	private MediaPlayer player = new MediaPlayer(splash);
	private MediaView view = new MediaView(player);
	private boolean splashStarted = false;

	public SplashPanel(GameModelAdapter gameModelAdapter, Group root, PanelManager panelManager,
			AssetManager assets, ViewEnum viewEnum) {
		super(gameModelAdapter, assets, viewEnum);
		this.root = root;
		checkSkipped();
		this.panelManager = panelManager;
		view.fitWidthProperty().bind(Bindings.selectDouble(view.sceneProperty(), "width"));
		view.fitHeightProperty().bind(Bindings.selectDouble(view.sceneProperty(), "height"));
		view.setPreserveRatio(true);
		video.getChildren().add(view);
	}

	private void checkSkipped() {
		video.setOnMouseReleased(new EventHandler<MouseEvent>() { // Click to skip
			public void handle(MouseEvent event) {
				if (player.getStatus() == MediaPlayer.Status.PLAYING) {
					player.stop();
					panelManager.setMode(ViewEnum.MAIN_MENU);
				}
			}
		});

	}

	public void draw(GraphicsContext g, Point screenDimensions, long currentPulse) {
		//Centers Video On Screen
		if ((double)screenDimensions.y/(double)screenDimensions.x < 
					(double)ASPECT_RATIO_Y/(double)ASPECT_RATIO_X) {
			view.setTranslateX((screenDimensions.x - ((ASPECT_RATIO_X * screenDimensions.y)/ASPECT_RATIO_Y))/2);
		}
		else {
			view.setTranslateY((screenDimensions.y - ((ASPECT_RATIO_Y * screenDimensions.x)/ASPECT_RATIO_X))/2); 
		}
		if (!splashStarted) {
			playSplashVideo();
			splashStarted = true;
		} else {
			if (player.getCurrentTime().toMillis() == 5142) {
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
