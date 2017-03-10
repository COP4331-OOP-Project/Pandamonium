package view;

import java.awt.Point;
import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import view.assets.AssetManager;
import view.game.Camera;
import view.game.CivilizationPanel;
import view.game.CommandPanel;
import view.game.ControlModePanel;
import view.game.GamePanel;
import view.game.MakeDetailsPanel;
import view.game.MiniMapPanel;
import view.game.SideBarPanel;
import view.game.StructureDetailsPanel;
import view.game.StructureOverviewPanel;
import view.game.TechOverviewPanel;
import view.game.ToggleBarPanel;
import view.game.UnitDetailsPanel;
import view.game.UnitOverviewPanel;
import view.screen.MainMenuPanel;
import view.screen.MapMakerPanel;
import view.screen.SettingsPanel;
import view.screen.SplashPanel;

public class PanelManager {
	private ViewEnum currentViewMode = ViewEnum.MAIN_MENU;
	private MusicManager musicManager;
	private GameModelAdapter gameModelAdapter;
	private AssetManager assets;
	private CivilizationPanel civilizationPanel;
	private SideBarPanel sideBarPanel;
	private CommandPanel commandPanel;
	private ControlModePanel controlModePanel;
	private GamePanel gamePanel;
	private MakeDetailsPanel makeDetailsPanel;
	private MiniMapPanel miniMapPanel;
	private UnitOverviewPanel unitOverviewPanel;
	private StructureOverviewPanel structureOverviewPanel;
	private TechOverviewPanel techOverviewPanel;
	private ToggleBarPanel toggleBarPanel;
	private UnitDetailsPanel unitDetailsPanel;
	private StructureDetailsPanel structureDetailsPanel;
	private MainMenuPanel mainMenuPanel;
	private SplashPanel splashPanel;
	private MapMakerPanel mapMakerPanel;
	private SettingsPanel settingsPanel;
	private GraphicsContext g;
	private ArrayList<Panel> panels;
	
	public PanelManager(GameModelAdapter gameModelAdapter, AssetManager assets, Group group, GraphicsContext g, Camera camera) {
		this.gameModelAdapter = gameModelAdapter;
		this.g = g;
		this.assets = assets;
		musicManager = new MusicManager(assets, group);
		panels = new ArrayList<Panel>();
		gamePanel = new GamePanel(gameModelAdapter, assets, camera, ViewEnum.MAIN_GAME);
		panels.add(gamePanel);
		civilizationPanel = new CivilizationPanel(gameModelAdapter, assets, ViewEnum.MAIN_GAME);
		panels.add(civilizationPanel);
		controlModePanel = new ControlModePanel(gameModelAdapter, group, assets, ViewEnum.MAIN_GAME);
		panels.add(controlModePanel);
		sideBarPanel = new SideBarPanel(gameModelAdapter, assets, ViewEnum.MAIN_GAME, group, this);
		panels.add(sideBarPanel);
		commandPanel = new CommandPanel(gameModelAdapter, group, assets, ViewEnum.MAIN_GAME);
		panels.add(commandPanel);
		makeDetailsPanel = new MakeDetailsPanel(gameModelAdapter, assets, ViewEnum.MAIN_GAME);
		panels.add(makeDetailsPanel);
		miniMapPanel = new MiniMapPanel(gameModelAdapter, assets, ViewEnum.MAIN_GAME);
		panels.add(miniMapPanel);
		structureDetailsPanel = new StructureDetailsPanel(gameModelAdapter, assets, ViewEnum.MAIN_GAME);
		panels.add(structureDetailsPanel);
		structureOverviewPanel = new StructureOverviewPanel(gameModelAdapter, assets, ViewEnum.MAIN_GAME);
		panels.add(structureOverviewPanel);
		unitDetailsPanel = new UnitDetailsPanel(gameModelAdapter, assets, ViewEnum.MAIN_GAME);
		panels.add(unitDetailsPanel);
		unitOverviewPanel = new UnitOverviewPanel(gameModelAdapter, assets, ViewEnum.MAIN_GAME);
		panels.add(unitOverviewPanel);
		techOverviewPanel = new TechOverviewPanel(gameModelAdapter, assets, ViewEnum.MAIN_GAME);
		panels.add(techOverviewPanel);
		toggleBarPanel = new ToggleBarPanel(gameModelAdapter, group, this, assets, ViewEnum.MAIN_GAME);
		panels.add(toggleBarPanel);
		splashPanel = new SplashPanel(gameModelAdapter, group, this, assets, ViewEnum.SPLASH);
		panels.add(splashPanel);
		mainMenuPanel = new MainMenuPanel(gameModelAdapter, group, this, assets, ViewEnum.MAIN_MENU);
		panels.add(mainMenuPanel);
		mapMakerPanel = new MapMakerPanel(gameModelAdapter, group, this, assets, ViewEnum.MAP_MAKER);
		panels.add(mapMakerPanel);
		settingsPanel = new SettingsPanel(gameModelAdapter, group, this, assets, ViewEnum.SETTINGS);
		panels.add(settingsPanel);
	}
	
	public void drawPanels(Point screenDimensions, long currentPulse) {
		musicManager.updateMusic(currentViewMode);
    	commandPanel.setIsVisible(gameModelAdapter.getSelectedEntity() != null);
		for (Panel panel : panels) {
			panel.drawPanel(g, screenDimensions, currentViewMode, currentPulse);
		}
	}

	public void setMode(ViewEnum currentViewMode) {
		this.currentViewMode = currentViewMode;
	}

	public void tileClicked(double x, double y) {
		if (currentViewMode == ViewEnum.MAP_MAKER) {
			mapMakerPanel.tileClicked(new Point((int)x, (int)y));
		}
	}

	public void toggleUnitOverview() {
		if (currentViewMode == ViewEnum.MAIN_GAME) {
	        structureOverviewPanel.hideIfVisible();
	        techOverviewPanel.hideIfVisible();
	        unitOverviewPanel.toggle();
		}
	}

	public void toggleStructureOverview() {
		if (currentViewMode == ViewEnum.MAIN_GAME) {
	    	unitOverviewPanel.hideIfVisible();
	    	techOverviewPanel.hideIfVisible();
	        structureOverviewPanel.toggle();
		}
	}
	
	public void toggleTechOverview() {
		if (currentViewMode == ViewEnum.MAIN_GAME) {
	    	unitOverviewPanel.hideIfVisible();
	    	structureOverviewPanel.hideIfVisible();
	        techOverviewPanel.toggle();
		}
	}

	public void toggleToggler() {
		toggleBarPanel.toggle();
	}

	public void toggleMiniMap() {
		miniMapPanel.toggle();
	}

	public void toggleResources() {
		gamePanel.toggleResources();
	}
}
