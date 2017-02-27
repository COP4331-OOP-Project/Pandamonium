package view.screen;

import java.awt.Point;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import view.Panel;
import view.PanelManager;
import view.ViewEnum;
import view.assets.AssetManager;

public class SettingsPanel extends Panel {
		private static final int SPACING = 33;
	    private DropShadow ds = new DropShadow();
	    Point screenDimensions = new Point(0,0);
		PanelManager panelManager;
		SettingsEnum currentMode = SettingsEnum.GENERAL;
		Group root; //Any GUI Elements Must Be Added and Removed From Here
		AnchorPane settings = new AnchorPane();
		Button loadButton = new Button("Load");
		Button saveButton = new Button("Save");
		Button exitToMenuButton = new Button("Main Menu");
		ToggleButton general = new ToggleButton("General");
	    ToggleButton humanControlsButton = new ToggleButton("Human");
	    ToggleButton pandaControlsButton = new ToggleButton("Panda");
	    String[][] humanControls = SettingsLoader.getControls(new File("assets/data/hctrl.dat"));
	    String[][] pandaControls = SettingsLoader.getControls(new File("assets/data/hctrl.dat"));
	    String[][] currentControls;
	    ArrayList<Button> controlButtons = new ArrayList<Button>();
		
		public SettingsPanel(Group root, PanelManager panelManager, AssetManager assets, ViewEnum viewEnum) {
			super(assets, viewEnum);
			this.panelManager = panelManager;
			this.root = root;
	    	ds.setOffsetY(2.0f);
			setUpButtons();
			setUpGeneralSettings();
			setUpControlsSettings();
			for (int i = 0; i < pandaControls.length; i++) {
				Button controlButton = new Button();
				controlButton.setText(pandaControls[i][1]);
				controlButton.setTranslateX(350);
				controlButton.setTranslateY(87 + SPACING * (i + 1));
				controlButton.setId("controlButton");
				settings.getChildren().add(controlButton);
				controlButtons.add(controlButton);
			}
		}
		
		private void setUpControlsSettings() {
			// TODO Auto-generated method stub
			
		}

		private void setUpGeneralSettings() {
			// TODO Auto-generated method stub
			
		}

		private void setUpButtons() {
			loadButton.setTranslateX(555);
			loadButton.setTranslateY(7);
			loadButton.setId("button");
			loadButton.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	                loadControls();
	            }
	        });
			saveButton.setTranslateX(630);
			saveButton.setTranslateY(7);
			saveButton.setId("button");
			saveButton.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	                saveControls();
	            }
	        });

			exitToMenuButton.setTranslateX(705);
			exitToMenuButton.setTranslateY(7);
			exitToMenuButton.setId("button");
			exitToMenuButton.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	                panelManager.setMode(ViewEnum.MAIN_MENU);
	            }
	        });
	        general.getStyleClass().setAll("buttonSelected");
			general.setTranslateX(165);
	        general.setTranslateY(7);
	        general.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	            	currentMode = SettingsEnum.GENERAL;
	                general.getStyleClass().setAll("buttonSelected");
	                humanControlsButton.getStyleClass().setAll("button");
	                pandaControlsButton.getStyleClass().setAll("button");
	                humanControlsButton.setSelected(false);
	                pandaControlsButton.setSelected(false);
	            }
	        });
	        humanControlsButton.getStyleClass().setAll("button");
	        humanControlsButton.setTranslateX(270);
	        humanControlsButton.setTranslateY(7);
	        humanControlsButton.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	            	currentMode = SettingsEnum.HUMAN;
	            	currentControls = humanControls;
	                humanControlsButton.getStyleClass().setAll("buttonSelected");
	                general.getStyleClass().setAll("button");
	                pandaControlsButton.getStyleClass().setAll("button");
	                general.setSelected(false);
	                pandaControlsButton.setSelected(false);
	            }
	        });
	        pandaControlsButton.getStyleClass().setAll("button");
	        pandaControlsButton.setTranslateX(357);
	        pandaControlsButton.setTranslateY(7);
	        pandaControlsButton.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	            	currentMode = SettingsEnum.PANDA;
	            	currentControls = pandaControls;
	                pandaControlsButton.getStyleClass().setAll("buttonSelected");
	                general.getStyleClass().setAll("button");
	                humanControlsButton.getStyleClass().setAll("button");
	                general.setSelected(false);
	                humanControlsButton.setSelected(false);
	            }
	        });

			settings.getChildren().add(loadButton);
			settings.getChildren().add(saveButton);
			settings.getChildren().add(exitToMenuButton);
			settings.getChildren().add(general);
			settings.getChildren().add(humanControlsButton);
			settings.getChildren().add(pandaControlsButton);
			loadButton.setVisible(true);
			saveButton.setVisible(true);
			exitToMenuButton.setVisible(true);
		}

		@Override
		public void draw(GraphicsContext g, Point screenDimensions) {
			g.drawImage(getAssets().getImage("GAME_BACKGROUND"), 0, 0, screenDimensions.x, screenDimensions.y);
			this.screenDimensions.x = screenDimensions.x;
			this.screenDimensions.y = screenDimensions.y;
			drawTopBar(g);
			switch (currentMode) {
				case GENERAL:
					displayGeneralSettings(g);
					break;
				default:
					displayControlSettings(g);
			}
		}

		private void displayControlSettings(GraphicsContext g) {
			loadButton.setVisible(true);
			saveButton.setVisible(true);
			setCommandButtonVisibility(true);
			g.setFont(getAssets().getFont(3));
		    g.setFill(Color.WHITE);
		    g.setEffect(ds);
		    if (currentMode == SettingsEnum.HUMAN)
		    	g.fillText("Human Controls", 15, 100);
		    else
		    	g.fillText("Panda Controls", 15, 100);
		    g.setFont(getAssets().getFont(1));
		    int multiplier = 0;
		    g.fillText("Mode Modifier", 15, 140 + SPACING  * multiplier++);
		    g.fillText("Mode/Command Forward", 15, 140 + SPACING * multiplier++);
		    g.fillText("Mode/Command Backward", 15, 140 + SPACING * multiplier++);
		    g.fillText("Type/Instance Forward", 15, 140 + SPACING * multiplier++);
		    g.fillText("Type/Instace Backward", 15, 140 + SPACING * multiplier++);
		    g.fillText("Show Unit Overview", 15, 140 + SPACING * multiplier++);
		    g.fillText("Show Structure Overview", 15, 140 + SPACING * multiplier++);
		    g.fillText("Select Item", 15, 140 + SPACING * multiplier++);
		    g.fillText("End Turn", 15, 140 + SPACING * multiplier++);
		    g.fillText("Move North", 15, 140 + SPACING * multiplier++);
		    g.fillText("Move North-East", 15, 140 + SPACING * multiplier++);
		    g.fillText("Move South-East", 15, 140 + SPACING * multiplier++);
		    g.fillText("Move South", 15, 140 + SPACING * multiplier++);
		    g.fillText("Move South-West", 15, 140 + SPACING * multiplier++);
		    g.fillText("Move North-West", 15, 140 + SPACING * multiplier++);
		    g.fillText("Center Camera", 15, 140 + SPACING * multiplier++);
		    g.setEffect(null);
		}
		
		private void setCommandButtonVisibility(boolean visibility) {
			for (Button button : controlButtons) {
				button.setVisible(visibility);
			}
		}

		private void displayGeneralSettings(GraphicsContext g) {
			loadButton.setVisible(false);
			saveButton.setVisible(false);
			setCommandButtonVisibility(false);
			g.setFont(getAssets().getFont(3));
		      g.setFill(Color.WHITE);
		      g.setEffect(ds);
		      g.fillText("General Settings", 15, 100);
		      g.setEffect(null);
			
		}

		private void drawTopBar(GraphicsContext g) {
		      g.drawImage(getAssets().getImage("GUI_MAP_BAR"), 0, 0);
		      g.setFont(getAssets().getFont(2));
		      g.setFill(Color.WHITE);
		      g.setEffect(ds);
		      g.fillText("Settings", 6, 35);
		      g.setEffect(null);
		}

		private void saveControls() {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Save Map");
			fileChooser.setInitialDirectory(new File("assets/controls"));
			fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Control Files", "*.ctr"));
			File saveMap = fileChooser.showSaveDialog(null);
			if (saveMap != null) {
				saveFile(saveMap);
			}
		}

		private void saveFile(File saveControls) {  
			BufferedWriter writeMap;
			try {
				writeMap = new BufferedWriter(new PrintWriter(saveControls));
				for (int i = 0; i < currentControls.length; i++) {
					   String s = "";
					   for (int j = 0; j < currentControls[i].length; j++) {
						   s += (currentControls[i][j] + "\t");
					   }
					   writeMap.write(s);
					   writeMap.newLine();
				   }
				writeMap.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			   
		}

		private void loadControls() {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Open Control File");
			fileChooser.setInitialDirectory(new File("assets/controls"));
			fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Control Files", "*.ctr"));
			File newControls = fileChooser.showOpenDialog(null);
			if (newControls != null) {
					currentControls = SettingsLoader.getControls(newControls);
			}
		}

		@Override
		public void hideGUIElements() {
			root.getChildren().remove(settings);
		}

		@Override
		public void showGUIElements() {
			root.getChildren().add(settings);
		}
}
