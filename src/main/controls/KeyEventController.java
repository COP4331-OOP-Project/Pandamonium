package controls;

import game.commands.CommandEnum;
import game.mode.ModeController;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import view.View;

import java.io.File;

public class KeyEventController {
    private final static Logger log = LogManager.getLogger(KeyEventController.class);
	private static final String HUMAN_CONFIG_FILE = "assets/data/hctrl.dat";
	private static final String PANDA_CONFIG_FILE = "assets/data/pctrl.dat";
	private File humanControlsFile = new File(HUMAN_CONFIG_FILE);
	private File pandaControlsFile = new File(PANDA_CONFIG_FILE);
    private ModeController controlMode;
    private View view;
    private Scene scene;
    private ControlFileReader controlReader;
    private boolean gettingMoves = false;
    private boolean gettingMakeList = false;
    private int currentPlayerControls = 1;
	private static final String COMMAND_UP = "COMMAND_UP";
	private static final String COMMAND_DOWN = "COMMAND_DOWN";
	private static final String COMMAND_LEFT = "COMMAND_LEFT";
	private static final String COMMAND_RIGHT = "COMMAND_RIGHT";
	private static final String SELECT_ITEM = "SELECT_ITEM";
	private static final String END_TURN = "END_TURN";
	private static final String UNIT_OVERVIEW = "UNIT_OVERVIEW";
	private static final String STRUCTURE_OVERVIEW = "STRUCTURE_OVERVIEW";
	private static final String CENTER = "CENTER";
	private static final String MOVE_0 = "MOVE_0";
	private static final String MOVE_45 = "MOVE_45";
	private static final String MOVE_135 = "MOVE_135";
	private static final String MOVE_180 = "MOVE_180";
	private static final String MOVE_225 = "MOVE_225";
	private static final String MOVE_315 = "MOVE_315";
	private static final String CHANGE_CYCLING = "CHANGE_CYCLING";
	
    public KeyEventController(ModeController controlMode, View view, Scene scene) {
    	controlReader = new ControlFileReader();
    	controlReader.loadControls(humanControlsFile);
    	this.view = view;
    	this.scene = scene;
        this.controlMode = controlMode;
    }
    
    private void controlDownActions(KeyEvent e) {
        KeyCode key = e.getCode();
        if (key == controlReader.getControl(COMMAND_UP)) {
                log.debug("CTRL + Up key pressed");
                controlMode.cycleModeBackward();
                
        } else if (key == controlReader.getControl(COMMAND_DOWN)) {
            	log.debug("CTRL + Down key pressed");
            	controlMode.cycleModeForward();
            	
        } else if (key == controlReader.getControl(COMMAND_LEFT)) {
                log.debug("CTRL + Right key pressed");
                controlMode.cycleSubmodeForward();
                
        } else if (key == controlReader.getControl(COMMAND_RIGHT)) {
                log.debug("CTRL + Left key pressed");
                controlMode.cycleSubmodeBackward();
                
        } else {
                log.info("Invalid command");
        }
    }

    private void normalKeyPressActions(KeyEvent e) {
        KeyCode key = e.getCode();
        if (key == controlReader.getControl(COMMAND_UP)) {
        	log.debug("Up key pressed");
        	controlMode.cycleCommandForward();
            
        } else if (key == controlReader.getControl(COMMAND_DOWN)) {
        	log.debug("Down key pressed");
        	controlMode.cycleCommandBackward();
        	
        } else if (key == controlReader.getControl(COMMAND_LEFT)) {
        	log.debug("Right key pressed");
        	controlMode.cycleEntityBackward();
            
        } else if (key == controlReader.getControl(COMMAND_RIGHT)) {
        	log.debug("Left key pressed");
        	controlMode.cycleEntityForward();
            
        } else if (key == controlReader.getControl(SELECT_ITEM)) {
            log.debug("Select key pressed");
            CommandEnum command = controlMode.executeCommand();
            if (command == CommandEnum.MOVE) {
                gettingMoves = true;
            } else if (command == CommandEnum.MAKE) {
                gettingMakeList = true;
            }
        } else if (key == controlReader.getControl(CENTER)) {
        	log.debug("Center key pressed");
        	view.centerOnCurrentTypeInstance();

        } else if (key == controlReader.getControl(END_TURN)) {
            log.debug("End turn key pressed");
            controlMode.endTurn();
            view.endTurn();
            
        } else if (key == controlReader.getControl(UNIT_OVERVIEW)) {
            log.debug("Unit Overview Pressed");
            view.toggleUnitOverview();
            
        } else if (key == controlReader.getControl(STRUCTURE_OVERVIEW)) {
            log.debug("Structure Overview Pressed");
            view.toggleStructureOverview();
            
        } else {
            log.info("Invalid command");
        }
    }

    public void getMoves(KeyEvent e) {
        KeyCode key = e.getCode();
        if (key == controlReader.getControl(MOVE_0)) {
            log.debug("Move 0 Key Pressed");
            controlMode.addMoveToList(0);
            
        } else if (key == controlReader.getControl(MOVE_45)) {
        	log.debug("Move 45 Key Pressed");
        	controlMode.addMoveToList(45);
        	
        } else if (key == controlReader.getControl(MOVE_135)) {
        	log.debug("Move 135 Key Pressed");
        	controlMode.addMoveToList(135);
        	
        } else if (key == controlReader.getControl(MOVE_180)) {
        	log.debug("Move 180 Key Pressed");
        	controlMode.addMoveToList(180);
        	
        } else if (key == controlReader.getControl(MOVE_225)) {
        	log.debug("Move 225 Key Pressed");
        	controlMode.addMoveToList(225);
        	
        } else if (key == controlReader.getControl(MOVE_315)) {
        	log.debug("Move 315 Key Pressed");
        	controlMode.addMoveToList(315);
        } else if (key == controlReader.getControl(SELECT_ITEM)) {
            log.debug("Select Item Pressed");
            controlMode.executeMoveCommand();
            gettingMoves = false;
        } else {
            log.info("Invalid command");
        }
    }

    // Handle cycle through make list
    public void getMakeList(KeyEvent e) {
        KeyCode key = e.getCode();

        // Cycle key mode choices for
        if (key == controlReader.getControl(COMMAND_UP)) {
        	log.debug("UP Key pressed (Make)");
        	controlMode.cycleMakeOptionUp();
            
        } else if (key == controlReader.getControl(COMMAND_DOWN)) {
        	log.debug("DOWN Key pressed (Make)");
        	controlMode.cycleMakeOptionDown();
        	
        } else if (key == controlReader.getControl(SELECT_ITEM)) {
            log.debug("Enter pressed");
            }
    }
    
    public void keyReleased(KeyEvent e) {
    	if (gettingMoves) {
            getMoves(e);
        } else if (gettingMakeList) {
            getMakeList(e);
        } else if ((e.isControlDown() && (controlReader.getControl(CHANGE_CYCLING) == KeyCode.CONTROL)) ||
        			(e.isAltDown() && (controlReader.getControl(CHANGE_CYCLING) == KeyCode.ALT)) ||
        			(e.isShiftDown() && (controlReader.getControl(CHANGE_CYCLING) == KeyCode.SHIFT))){
            controlDownActions(e);
            return;
        } else normalKeyPressActions(e);
    }
    
    public void handleEvents() {
        scene.setOnKeyReleased(event -> keyReleased(event));
    }

	public void togglePlayer() {
		currentPlayerControls = (currentPlayerControls + 1) % 2;
		if (currentPlayerControls == 0) {
			controlReader.loadControls(humanControlsFile);
		} else {
			controlReader.loadControls(pandaControlsFile);
		}
	}
}