package view.screen;

import java.awt.Point;

import javafx.beans.binding.Bindings;
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

public class IntroPanel extends Panel {
	private static final int ASPECT_RATIO_X = 16;
	private static final int ASPECT_RATIO_Y = 9;
	private PanelManager panelManager;
	private Group root;
	private Media intro = getAssets().getIntro();
	private StackPane video = new StackPane();
	private MediaPlayer videoPlayer = new MediaPlayer(intro);
	private MediaView view = new MediaView(videoPlayer);
	private boolean introStarted = false;

	public IntroPanel(GameModelAdapter gameModelAdapter, Group root, PanelManager panelManager,
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
		video.setOnMouseClicked(new EventHandler<MouseEvent>() { // Click to skip
			public void handle(MouseEvent event) {
				if (videoPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
					videoPlayer.stop();
					panelManager.setMode(ViewEnum.MAIN_GAME);
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
		if (!introStarted) {
			playIntroVideo();
			introStarted = true;
		} else {
			if (videoPlayer.getCurrentTime().toMillis() == 39894) { //Last Milli of Video
				videoPlayer.stop();
				panelManager.setMode(ViewEnum.MAIN_GAME);
			}
		}
	}

	private void playIntroVideo() {
		videoPlayer.play();
	}

	public void hideGUIElements() {
		root.getChildren().remove(video);
	}

	public void showGUIElements() {
		root.getChildren().add(video);
	}

}
