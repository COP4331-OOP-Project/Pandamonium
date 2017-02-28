package view.screen;

import java.awt.Point;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ToggleButton;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
		private static final String HUMAN_DAT_LOCATION = "assets/data/hctrl.dat";
		private static final String PANDA_DAT_LOCATION = "assets/data/pctrl.dat";
	    private DropShadow ds = new DropShadow();
	    private Point screenDimensions = new Point(0,0);
	    private PanelManager panelManager;
	    private SettingsEnum currentMode = SettingsEnum.GENERAL;
	    private Group root; //Any GUI Elements Must Be Added and Removed From Here
	    private AnchorPane settings = new AnchorPane();
	    private Button loadButton = new Button("Load");
	    private Button saveButton = new Button("Save");
	    private Button exitToMenuButton = new Button("Main Menu");
	    private ToggleButton general = new ToggleButton("General");
	    private ToggleButton humanControlsButton = new ToggleButton("Human");
	    private ToggleButton pandaControlsButton = new ToggleButton("Panda");
	    private String[][] humanControls = SettingsLoader.getControls(new File(HUMAN_DAT_LOCATION));
	    private String[][] pandaControls = SettingsLoader.getControls(new File(PANDA_DAT_LOCATION));
	    private ComboBox<String> modeModifier;
	    private ArrayList<Button> controlButtons = new ArrayList<Button>();
	    private int controlWaiting = 0;
	    private boolean waitingForPress = false;
	    
		public SettingsPanel(Group root, PanelManager panelManager, AssetManager assets, ViewEnum viewEnum) {
			super(assets, viewEnum);
			this.panelManager = panelManager;
			this.root = root;
	    	ds.setOffsetY(2.0f);
			setUpButtons();
			setUpModeModifier();
		}

		private void setUpButtons() {
			setupButton(loadButton, 555, 7);
			loadButton.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	                loadControls();
	            }
	        });
			setupButton(saveButton, 630, 7);
			saveButton.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	                saveControlsFileChoose();
	            }
	        });
			setupButton(exitToMenuButton, 705, 7);
			exitToMenuButton.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	            	updateControlDisplay();
	    			saveFile(new File(PANDA_DAT_LOCATION), pandaControls);
	    			saveFile(new File(HUMAN_DAT_LOCATION), humanControls);
	                panelManager.setMode(ViewEnum.MAIN_MENU);
	            }
	        });
			setupToggleButton(general, 165, 7, "buttonSelected");
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
	        setupToggleButton(humanControlsButton, 270, 7, "button");
	        humanControlsButton.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	            	saveCurrentSelections();
	            	currentMode = SettingsEnum.HUMAN;
	            	updateControlDisplay();
	                humanControlsButton.getStyleClass().setAll("buttonSelected");
	                general.getStyleClass().setAll("button");
	                pandaControlsButton.getStyleClass().setAll("button");
	                general.setSelected(false);
	                pandaControlsButton.setSelected(false);
	            }
	        });
	        setupToggleButton(pandaControlsButton, 357, 7, "button");
	        pandaControlsButton.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	            	saveCurrentSelections();
	            	currentMode = SettingsEnum.PANDA;
	            	updateControlDisplay();
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
			setUpControlButtons();
		}

		private void setupButton(Button button, int x, int y) {
			button.setTranslateX(555);
			button.setTranslateY(7);
			button.setId("button");
		}
		
		private void setupToggleButton(ToggleButton button, int x, int y, String style) {
			button.getStyleClass().setAll(style);
			button.setTranslateX(x);
	        button.setTranslateY(y);
		}

		private void setUpControlButtons() {
			for (int i = 1; i < humanControls.length; i++) {
				Button controlButton = new Button();
				controlButton.setText(humanControls[i][1]);
				controlButton.setTranslateX(350);
				controlButton.setTranslateY(87 + SPACING * (i + 1));
				controlButton.setId("controlButton");
				settings.getChildren().add(controlButton);
				controlButton.setOnAction(new EventHandler<ActionEvent>() {
		            @Override
		            public void handle(ActionEvent event) {
		            	if (!waitingForPress) {
			            	for (int i = 0; i < controlButtons.size(); i++) {
			            		if (controlButtons.get(i) == controlButton) {
			            			controlButtons.get(i).getStyleClass().add("buttonRed");
			            			controlButtons.get(i).setText("Press Any Key");
			            			waitingForPress = true;
			            			controlWaiting = i;
			            		}
			            	}
			            }
		            }
		        });
				handleKeyPressWaiting();
				controlButtons.add(controlButton);
			}
		}

		private void handleKeyPressWaiting() {
			root.setOnKeyReleased(new EventHandler<KeyEvent>() {
			    @Override
			    public void handle(KeyEvent event) {
			    	if (waitingForPress) {
			        	KeyCode key = event.getCode();
			            String pressed = key.toString();
			            controlButtons.get(controlWaiting).getStyleClass().remove("buttonRed");
						if (currentMode == SettingsEnum.HUMAN) {
							humanControls[controlWaiting][1] = pressed;
						} else {
							pandaControls[controlWaiting][1] = pressed;
						}
						controlButtons.get(controlWaiting).setText(pressed);
						waitingForPress = false;
			    	} 
			    }
			});
		} 

		//This is the drop down list for modifying the mode
		private void setUpModeModifier() {
			ObservableList<String> options = 
				    FXCollections.observableArrayList(
				        "CONTROL",
				        "SHIFT",
				        "ALT"
				    );
			modeModifier = new ComboBox<String>(options);
			modeModifier.setVisibleRowCount(3);
			modeModifier.setButtonCell(new ListCell<String>(){
		        @Override
		        protected void updateItem(String string, boolean empty) {
		            super.updateItem(string, empty); 
		            if(!(empty || string==null)){
		                setStyle("-fx-text-fill: white");
		                setText(string);
		            }
		        }

		    });
			modeModifier.setTranslateX(350);
			modeModifier.setTranslateY(87 + SPACING);
			settings.getChildren().add(modeModifier);
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
		    drawCommandLabels(g);
		    g.setEffect(null);
		}

		private void drawCommandLabels(GraphicsContext g) {
			int multiplier = 0;
		    g.fillText("Mode Modifier", 15, 140 + SPACING  * multiplier++);
		    g.fillText("Mode/Command Forward", 15, 140 + SPACING * multiplier++);
		    g.fillText("Mode/Command Backward", 15, 140 + SPACING * multiplier++);
		    g.fillText("Type/Instance Forward", 15, 140 + SPACING * multiplier++);
		    g.fillText("Type/Instace Backward", 15, 140 + SPACING * multiplier++);
		    g.fillText("Select Item", 15, 140 + SPACING * multiplier++);
		    g.fillText("Show Unit Overview", 15, 140 + SPACING * multiplier++);
		    g.fillText("Show Structure Overview", 15, 140 + SPACING * multiplier++);
		    g.fillText("End Turn", 15, 140 + SPACING * multiplier++);
		    g.fillText("Move North", 15, 140 + SPACING * multiplier++);
		    g.fillText("Move North-East", 15, 140 + SPACING * multiplier++);
		    g.fillText("Move South-East", 15, 140 + SPACING * multiplier++);
		    g.fillText("Move South", 15, 140 + SPACING * multiplier++);
		    g.fillText("Move South-West", 15, 140 + SPACING * multiplier++);
		    g.fillText("Move North-West", 15, 140 + SPACING * multiplier++);
		    g.fillText("Center Camera", 15, 140 + SPACING * multiplier++);
		}

		private void drawTopBar(GraphicsContext g) {
		      g.drawImage(getAssets().getImage("GUI_MAP_BAR"), 0, 0);
		      g.setFont(getAssets().getFont(2));
		      g.setFill(Color.WHITE);
		      g.setEffect(ds);
		      g.fillText("Settings", 6, 35);
		      g.setEffect(null);
		}
		
		private void setCommandButtonVisibility(boolean visibility) {
			if(visibility) {
				modeModifier.setVisible(true);
			} else {
				modeModifier.setVisible(false);
			}
			for (Button button : controlButtons) {
				button.setVisible(visibility);
			}
		}
		
		//Updates What's Displayed To What's in the Arrays
		private void updateControlDisplay() {
			if (currentMode == SettingsEnum.HUMAN) {
				modeModifier.setValue(humanControls[0][1]);
				for (int i = 1; i < humanControls.length; i++) {
					controlButtons.get(i - 1).setText(humanControls[i][1]);
				}
			} else if (currentMode == SettingsEnum.PANDA) {
				modeModifier.setValue(pandaControls[0][1]);
				for (int i = 1; i < pandaControls.length; i++) {
					controlButtons.get(i - 1).setText(pandaControls[i][1]);
				}
			}
		}
		
		//Saves What is Visible In The Array
        private void saveCurrentSelections() {
			if (currentMode != SettingsEnum.GENERAL) {
				if (currentMode == SettingsEnum.HUMAN) {
					humanControls[0][1] = modeModifier.getValue();
				}
				if (currentMode == SettingsEnum.PANDA) {
					pandaControls[0][1] = modeModifier.getValue();
				}
			}
		}

		//Show Popup Window Of Where to Save File
		private void saveControlsFileChoose() {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Save Controls");
			fileChooser.setInitialDirectory(new File("assets/controls"));
			fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Control Files", "*.ctr"));
			File saveControls = fileChooser.showSaveDialog(null);
			if (saveControls != null) {
				if (currentMode == SettingsEnum.HUMAN)
					saveFile(saveControls, humanControls);
				else
					saveFile(saveControls, pandaControls);
			}
		}

		//Write Controls To a CTR or DAT file
		private void saveFile(File saveControls, String[][] controls) {
			saveCurrentSelections();
			BufferedWriter writeMap;
			try {
				writeMap = new BufferedWriter(new PrintWriter(saveControls));
				for (int i = 0; i < controls.length; i++) {
					   String s = "";
					   for (int j = 0; j < controls[i].length; j++) {
						   s += (controls[i][j] + "\t");
					   }
					   writeMap.write(s);
					   writeMap.newLine();
				   }
				writeMap.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		//Load Controls From CTR File
		private void loadControls() {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Open Control File");
			fileChooser.setInitialDirectory(new File("assets/controls"));
			fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Control Files", "*.ctr"));
			File newControls = fileChooser.showOpenDialog(null);
			if (newControls != null) {
				if (currentMode == SettingsEnum.HUMAN)
					humanControls = SettingsLoader.getControls(newControls);
				if (currentMode == SettingsEnum.PANDA)
					pandaControls = SettingsLoader.getControls(newControls);
			}
			updateControlDisplay();
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
