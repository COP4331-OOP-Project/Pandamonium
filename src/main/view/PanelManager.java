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
import view.game.StructureDetailsPanel;
import view.game.StructureOverviewPanel;
import view.game.UnitDetailsPanel;
import view.game.UnitOverviewPanel;
import view.screen.MainMenuPanel;
import view.screen.MapMakerPanel;
import view.screen.SettingsPanel;

public class PanelManager {
	private ViewEnum currentGameMode = ViewEnum.MAIN_MENU;
	private GameModelAdapter gameModelAdapter;
	private AssetManager assets;
	private CivilizationPanel civilizationPanel;
	private CommandPanel commandPanel;
	private ControlModePanel controlModePanel;
	private GamePanel gamePanel;
	private MakeDetailsPanel makeDetailsPanel;
	private MiniMapPanel miniMapPanel;
	private StructureDetailsPanel structureDetailsPanel;
	private StructureOverviewPanel structureOverviewPanel;
	private UnitDetailsPanel unitDetailsPanel;
	private UnitOverviewPanel unitOverviewPanel;
	private MainMenuPanel mainMenuPanel;
	private MapMakerPanel mapMakerPanel;
	private SettingsPanel settingsPanel;
	private GraphicsContext g;
	private ArrayList<Panel> panels;
	
	public PanelManager(GameModelAdapter gameModelAdapter, AssetManager assets, Group group, GraphicsContext g, Camera camera) {
		this.gameModelAdapter = gameModelAdapter;
		this.g = g;
		this.assets = assets;
		panels = new ArrayList<Panel>();
		civilizationPanel = new CivilizationPanel(gameModelAdapter, assets, ViewEnum.MAIN_GAME);
		panels.add(civilizationPanel);
		commandPanel = new CommandPanel(gameModelAdapter, group, assets, ViewEnum.MAIN_GAME);
		panels.add(commandPanel);
		controlModePanel = new ControlModePanel(gameModelAdapter, group, assets, ViewEnum.MAIN_GAME);
		panels.add(controlModePanel);
		gamePanel = new GamePanel(gameModelAdapter, assets, camera, ViewEnum.MAIN_GAME);
		panels.add(gamePanel);
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
		mainMenuPanel = new MainMenuPanel(gameModelAdapter, group, this, assets, ViewEnum.MAIN_MENU);
		panels.add(mainMenuPanel);
		mapMakerPanel = new MapMakerPanel(gameModelAdapter, group, this, assets, ViewEnum.MAP_MAKER);
		panels.add(mapMakerPanel);
		settingsPanel = new SettingsPanel(gameModelAdapter, group, this, assets, ViewEnum.SETTINGS);
		panels.add(settingsPanel);
	}
	
	public void drawPanels(Point screenDimensions, long currentPulse) {
		for (Panel panel : panels) {
			panel.drawPanel(g, screenDimensions, currentGameMode, currentPulse);
		}
	}

	public void setMode(ViewEnum currentGameMode) {
		this.currentGameMode = currentGameMode;
	}

	public void tileClicked(double x, double y) {
		if (currentGameMode == ViewEnum.MAP_MAKER) {
			mapMakerPanel.tileClicked(new Point((int)x, (int)y));
		}
	}
}
