package view;

import javafx.scene.Group;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import view.assets.AssetManager;

public class MusicManager {
	AssetManager assets;
	private Media menuMusic;
	private Media gameMusic;
	private boolean menuMusicStarted = false;
	private boolean gameMusicStarted = false;
	private boolean menuMusicPlaying = false;
	private boolean gameMusicPlaying = false;
	MediaPlayer menuMusicPlayer;
	MediaPlayer gameMusicPlayer;
	Group root;
	
	public MusicManager(AssetManager assets, Group root) {
		this.assets = assets;
		this.root = root;
		menuMusic = assets.getMenuMusic();
		gameMusic = assets.getGameMusic();
		menuMusicPlayer = new MediaPlayer(menuMusic);
		gameMusicPlayer = new MediaPlayer(gameMusic);
		menuMusicPlayer.setOnEndOfMedia(new Runnable() {
		       public void run() {
		    	   menuMusicPlayer.seek(Duration.ZERO);
		         }
		});
		gameMusicPlayer.setOnEndOfMedia(new Runnable() {
		       public void run() {
		    	   gameMusicPlayer.seek(Duration.ZERO);
		         }
		});
	}
	
	public void updateMusic(ViewEnum currentViewMode) {
		if (currentViewMode == ViewEnum.INTRO) {
			gameMusicPlayer.pause();
			menuMusicPlayer.pause();
		}
		if (currentViewMode == ViewEnum.SETTINGS ||
			currentViewMode == ViewEnum.MAIN_MENU ||
			currentViewMode == ViewEnum.MAP_MAKER) {
			if (!menuMusicPlaying) {
				menuMusicPlaying = true;
				gameMusicPlaying = false;
				menuMusicStarted = true;
			}
		} else {
			menuMusicPlaying = false;
		}
		if (currentViewMode == ViewEnum.MAIN_GAME) {
			if (!gameMusicPlaying) {
				gameMusicPlaying = true;
				menuMusicPlaying = false;
				gameMusicStarted = true;
			}
		} else {
			gameMusicPlaying = false;
		}
		playMusic();
	}

	private void playMusic() {
		if (menuMusicPlaying && menuMusicStarted) {
			gameMusicPlayer.stop();
			menuMusicPlayer.seek(Duration.ZERO);
			menuMusicPlayer.play();
			menuMusicStarted = false;
		}
		
		if (gameMusicPlaying && gameMusicStarted) {
			menuMusicPlayer.stop();
			gameMusicPlayer.seek(Duration.ZERO);
			gameMusicPlayer.play();
			gameMusicStarted = false;
		}
	}

}
