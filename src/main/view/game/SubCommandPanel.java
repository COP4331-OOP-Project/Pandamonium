package view.game;

import game.commands.SubCommandEnum;
import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import view.GameModelAdapter;
import view.Panel;
import view.ViewEnum;
import view.assets.AssetManager;

import java.awt.*;
import java.util.ArrayList;



public class SubCommandPanel extends Panel {

    private static final int COMMAND_Y_NORMAL = 350;
    private static final int BUTTON_HEIGHT = 18;
    private static final int SPACING = 15;
    private int yDistance = COMMAND_Y_NORMAL;
    private DropShadow ds = new DropShadow();
    private Point screenDimensions;
    private AnchorPane subCommandButtonPane = new AnchorPane();

    private Group root;
    private HoverPanel hoverPanel;
    private TileSelector tileSelector;

    // Command buttons & possible commands
    private ArrayList<SubCommandEnum> possibleSubCommands;
    private ArrayList<SubCommandButton> subCommandButtons = new ArrayList<>();

    // Button Position Points
    private Point c1 = new Point();
    private Point c2 = new Point();
    private Point c3 = new Point();
    private Point c4 = new Point();
    private Point c5 = new Point();
    private Point c6 = new Point();
    private Point c7 = new Point();
    private Point c8 = new Point();
    private Point c9 = new Point();
    private Point c10 = new Point();
    private Point c11 = new Point();
    private Point c12 = new Point();

    // Create button / subcommand pairs
    private SubCommandButton assignBreeder = new SubCommandButton(SubCommandEnum.ASSIGN_BREEDER);
    private SubCommandButton assignFarmer = new SubCommandButton(SubCommandEnum.ASSIGN_FARMER);
    private SubCommandButton assignGenerator = new SubCommandButton(SubCommandEnum.ASSIGN_GENERATOR);
    private SubCommandButton assignMiner = new SubCommandButton(SubCommandEnum.ASSIGN_MINER);
    private SubCommandButton assignInactive = new SubCommandButton(SubCommandEnum.ASSIGN_INACTIVE_WORKER);
    private SubCommandButton assignSoldierGenerator = new SubCommandButton(SubCommandEnum.ASSIGN_SOLDIER_GENERATOR);
    private SubCommandButton assignResearcher = new SubCommandButton(SubCommandEnum.ASSIGN_RESEARCHER);
    private SubCommandButton createMelee = new SubCommandButton(SubCommandEnum.CREATE_MELEE);
    private SubCommandButton createColonist = new SubCommandButton(SubCommandEnum.CREATE_COLONIST);
    private SubCommandButton createRanged = new SubCommandButton(SubCommandEnum.CREATE_RANGED);
    private SubCommandButton createExplorer = new SubCommandButton(SubCommandEnum.CREATE_EXPLORER);
    private SubCommandButton createWorker = new SubCommandButton(SubCommandEnum.CREATE_WORKER);
    private SubCommandButton buildFort = new SubCommandButton(SubCommandEnum.BUILD_FORT);
    private SubCommandButton buildCapitol = new SubCommandButton(SubCommandEnum.BUILD_CAPITOL);
    private SubCommandButton buildObserver = new SubCommandButton(SubCommandEnum.BUILD_OBSERVER);
    private SubCommandButton buildUniversity = new SubCommandButton(SubCommandEnum.BUILD_UNIVERSITY);
    private SubCommandButton buildMine = new SubCommandButton(SubCommandEnum.BUILD_MINE);
    private SubCommandButton buildPowerPlant = new SubCommandButton(SubCommandEnum.BUILD_POWER_PLANT);
    private SubCommandButton buildFarm = new SubCommandButton(SubCommandEnum.BUILD_FARM);

    // Constructor
    public SubCommandPanel(GameModelAdapter gameModelAdapter, Group root, AssetManager assets, ViewEnum view, TileSelector tileSelector) {

        super(gameModelAdapter, assets, view);

        setIsVisible(false);

        this.root = root;
        this.hoverPanel = new HoverPanel(gameModelAdapter, assets, view);
        this.tileSelector = tileSelector;

        // Setup buttons
        setUpSubCommandButton(assignBreeder);
        setUpSubCommandButton(assignFarmer);
        setUpSubCommandButton(assignGenerator);
        setUpSubCommandButton(assignMiner);
        setUpSubCommandButton(assignInactive);
        setUpSubCommandButton(assignSoldierGenerator);
        setUpSubCommandButton(assignResearcher);
        setUpSubCommandButton(createMelee);
        setUpSubCommandButton(createColonist);
        setUpSubCommandButton(createRanged);
        setUpSubCommandButton(createExplorer);
        setUpSubCommandButton(createWorker);
        setUpSubCommandButton(buildFort);
        setUpSubCommandButton(buildCapitol);
        setUpSubCommandButton(buildObserver);
        setUpSubCommandButton(buildUniversity);
        setUpSubCommandButton(buildMine);
        setUpSubCommandButton(buildPowerPlant);
        setUpSubCommandButton(buildFarm);

    }

    private void drawSubCommandPanel(GraphicsContext g) {
        yDistance = COMMAND_Y_NORMAL;

        if (possibleSubCommands.size() >= 1 && possibleSubCommands.size() <= 3) {
            g.drawImage(getImage("GUI_COMMAND_PANEL1"), 0, yDistance);
        } else if (possibleSubCommands.size() >= 4 && possibleSubCommands.size() <= 6) {
            g.drawImage(getImage("GUI_COMMAND_PANEL2"), 0, yDistance);
        } else if (possibleSubCommands.size() >= 7 && possibleSubCommands.size() <= 9) {
            g.drawImage(getImage("GUI_COMMAND_PANEL3"), 0, yDistance);
        } else if (possibleSubCommands.size() >= 10 && possibleSubCommands.size() <= 12) {
            g.drawImage(getImage("GUI_COMMAND_PANEL4"), 0, yDistance);
        }

        updateCommandButtonLocations(g);
        drawCommandButtons(g);

    }

    private void updateCommandButtonLocations(GraphicsContext g) {
        c1.x = 0;
        c1.y = yDistance;
        c2.x = 0;
        c2.y = yDistance + BUTTON_HEIGHT;
        c3.x = 0;
        c3.y = yDistance + BUTTON_HEIGHT * 2;
        c4.x = 0;
        c4.y = yDistance + BUTTON_HEIGHT * 3;
        c5.x = 0;
        c5.y = yDistance + BUTTON_HEIGHT * 4;
        c6.x = 0;
        c6.y = yDistance + BUTTON_HEIGHT * 5;
        c7.x = 0;
        c7.y = yDistance + BUTTON_HEIGHT * 6;
        c8.x = 0;
        c8.y = yDistance + BUTTON_HEIGHT * 7;
        c9.x = 0;
        c9.y = yDistance + BUTTON_HEIGHT * 8;
        c10.x = 0;
        c10.y = yDistance + BUTTON_HEIGHT * 9;
        c11.x = 0;
        c11.y = yDistance + BUTTON_HEIGHT * 10;
        c12.x = 0;
        c12.y = yDistance + BUTTON_HEIGHT * 11;
    }

    private void drawCommandButtons(GraphicsContext g) {

        for (SubCommandButton subCommandButton : subCommandButtons) {
            boolean commandExists = false;
            for (int i = 0; i < possibleSubCommands.size(); i++) {
                if (subCommandButton.getCommand() == possibleSubCommands.get(i)) {
                    if (getAdapter().getSelectedSubCommand() == subCommandButton.getCommand()) {
                        subCommandButton.getStyleClass().setAll("subCommandButtonSelected");
                    } else {
                        subCommandButton.getStyleClass().setAll("subCommandButton");
                    }
                    switch (i) {
                        case 0:
                            subCommandButton.setTranslateX(c1.x + SPACING);
                            subCommandButton.setTranslateY(c1.y + SPACING);
                            break;
                        case 1:
                            subCommandButton.setTranslateX(c2.x + SPACING);
                            subCommandButton.setTranslateY(c2.y + SPACING);
                            break;
                        case 2:
                            subCommandButton.setTranslateX(c3.x + SPACING);
                            subCommandButton.setTranslateY(c3.y + SPACING);
                            break;
                        case 3:
                            subCommandButton.setTranslateX(c4.x + SPACING);
                            subCommandButton.setTranslateY(c4.y + SPACING);
                            break;
                        case 4:
                            subCommandButton.setTranslateX(c5.x + SPACING);
                            subCommandButton.setTranslateY(c5.y + SPACING);
                            break;
                        case 5:
                            subCommandButton.setTranslateX(c6.x + SPACING);
                            subCommandButton.setTranslateY(c6.y + SPACING);
                            break;
                        case 6:
                            subCommandButton.setTranslateX(c7.x + SPACING);
                            subCommandButton.setTranslateY(c7.y + SPACING);
                            break;
                        case 7:
                            subCommandButton.setTranslateX(c8.x + SPACING);
                            subCommandButton.setTranslateY(c8.y + SPACING);
                            break;
                        case 8:
                            subCommandButton.setTranslateX(c9.x + SPACING);
                            subCommandButton.setTranslateY(c9.y + SPACING);
                            break;
                        case 9:
                            subCommandButton.setTranslateX(c10.x + SPACING);
                            subCommandButton.setTranslateY(c10.y + SPACING);
                            break;
                        case 10:
                            subCommandButton.setTranslateX(c11.x + SPACING);
                            subCommandButton.setTranslateY(c11.y + SPACING);
                            break;
                        case 11:
                            subCommandButton.setTranslateX(c12.x + SPACING);
                            subCommandButton.setTranslateY(c12.y + SPACING);
                            break;
                    }
                    commandExists = true;
                }
                if (commandExists) {
                    subCommandButton.setVisible(true);
                } else {
                    subCommandButton.setVisible(false);
                }
            }
        }
    }

    // Draw loop
    public void draw(GraphicsContext g, Point screenDimensions, long currentPulse) {
        this.screenDimensions = screenDimensions;
        possibleSubCommands = getAdapter().getSubCommands();
        if (getAdapter().getSelectedEntity() != null &&  getAdapter().isExpectingSubCommand()) {
            drawSubCommandPanel(g);
        } else {
            this.hideButtons();
        }
    }

    // Set buttons invisible
    private void hideButtons() {
        for (SubCommandButton b : subCommandButtons) {
            b.setVisible(false);
        }
    }

    // Setup the correct selected subCommand
    public void setUpSubCommandButton(SubCommandButton subCommandButton) {
        subCommandButton.setId("button");
        subCommandButton.setOnAction(event -> {});
        subCommandButtonPane.getChildren().removeAll();
        subCommandButtonPane.getChildren().add(subCommandButton);
        subCommandButtons.add(subCommandButton);
        subCommandButton.setOnAction(event -> {
            setSubCommand(subCommandButton.getCommand());
            getAdapter().executeSubCommand();
        });
    }


    public void hideGUIElements() {
        root.getChildren().remove(subCommandButtonPane);
    }

    public void showGUIElements() { root.getChildren().add(subCommandButtonPane); }
}
