package game.Mode;

import game.GameModel;
import game.mode.Mode;
import game.mode.ModeController;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CyclingTest {
    private GameModel gameModel;
    private ModeController modeController;


    @Before
    public void setUp(){
        gameModel = new GameModel();
        gameModel.initializeGame();
        modeController = new ModeController(gameModel);
    }

    @Test //Test cycle mode forward
    public void testCycleModeForward(){
            Assert.assertEquals(Mode.RALLY_POINT, modeController.getGameMode());
            modeController.cycleModeForward();
            Assert.assertEquals(Mode.STRUCTURE, modeController.getGameMode());
            modeController.cycleModeForward();
            Assert.assertEquals(Mode.UNIT, modeController.getGameMode());
            modeController.cycleModeForward();
            Assert.assertEquals(Mode.ARMY, modeController.getGameMode());
            modeController.cycleModeForward();
            Assert.assertEquals(Mode.RALLY_POINT, modeController.getGameMode());
    }

    @Test //Test cycle mode backward
    public void testCycleModeBackward(){
        Assert.assertEquals(Mode.RALLY_POINT, modeController.getGameMode());
        modeController.cycleModeBackward();
        Assert.assertEquals(Mode.ARMY, modeController.getGameMode());
        modeController.cycleModeBackward();
        Assert.assertEquals(Mode.UNIT, modeController.getGameMode());
        modeController.cycleModeBackward();
        Assert.assertEquals(Mode.STRUCTURE, modeController.getGameMode());
        modeController.cycleModeBackward();
        Assert.assertEquals(Mode.RALLY_POINT, modeController.getGameMode());
    }

    @Test //Test set mode
    public void testSetMode(){
        modeController.setMode(Mode.ARMY);
        Assert.assertEquals(Mode.ARMY, modeController.getGameMode());
        modeController.setMode(Mode.UNIT);
        Assert.assertEquals(Mode.UNIT, modeController.getGameMode());
    }

    @Test //Test submode
    public void testCycleSubmodeForward(){
        modeController.setMode(Mode.UNIT);
        
    }
}
