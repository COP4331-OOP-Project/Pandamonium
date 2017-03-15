package game.Mode;

import game.GameModel;
import game.mode.Mode;
import game.mode.ModeController;
import game.mode.Submode;
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

    @Test //Test submode forward
    public void testCycleSubmodeForward(){
        modeController.setMode(Mode.UNIT);
        Assert.assertEquals(Mode.UNIT, modeController.getGameMode());
        Assert.assertEquals(Submode.EXPLORER, modeController.getGameSubmode());

        modeController.cycleSubmodeForward();
        Assert.assertEquals(Submode.COLONIST, modeController.getGameSubmode());
        modeController.cycleSubmodeForward();
        Assert.assertEquals(Submode.MELEE, modeController.getGameSubmode());
        modeController.cycleSubmodeForward();
        Assert.assertEquals(Submode.RANGED, modeController.getGameSubmode());
        modeController.cycleSubmodeForward();
        Assert.assertEquals(Submode.EXPLORER, modeController.getGameSubmode());

        modeController.setMode(Mode.STRUCTURE);
        Assert.assertEquals(Mode.STRUCTURE, modeController.getGameMode());
        Assert.assertEquals(Submode.CAPITOL, modeController.getGameSubmode());

        modeController.cycleSubmodeForward();
        Assert.assertEquals(Submode.FARM, modeController.getGameSubmode());
        modeController.cycleSubmodeForward();
        Assert.assertEquals(Submode.FORT, modeController.getGameSubmode());
        modeController.cycleSubmodeForward();
        Assert.assertEquals(Submode.MINE, modeController.getGameSubmode());
        modeController.cycleSubmodeForward();
        Assert.assertEquals(Submode.OBSERVE, modeController.getGameSubmode());
        modeController.cycleSubmodeForward();
        Assert.assertEquals(Submode.PLANT, modeController.getGameSubmode());
        modeController.cycleSubmodeForward();
        Assert.assertEquals(Submode.UNIVERSITY, modeController.getGameSubmode());

    }

    @Test //Test submode backward
    public void testCycleSubModeBackward(){
        modeController.setMode(Mode.UNIT);
        Assert.assertEquals(Mode.UNIT, modeController.getGameMode());
        Assert.assertEquals(Submode.EXPLORER, modeController.getGameSubmode());

        modeController.cycleSubmodeBackward();
        Assert.assertEquals(Submode.RANGED, modeController.getGameSubmode());
        modeController.cycleSubmodeBackward();
        Assert.assertEquals(Submode.MELEE, modeController.getGameSubmode());
        modeController.cycleSubmodeBackward();
        Assert.assertEquals(Submode.COLONIST, modeController.getGameSubmode());
        modeController.cycleSubmodeBackward();
        Assert.assertEquals(Submode.EXPLORER, modeController.getGameSubmode());

        modeController.setMode(Mode.ARMY);
        Assert.assertEquals(Submode.ENTIRE_ARMY, modeController.getGameSubmode());
        modeController.cycleSubmodeBackward();
        Assert.assertEquals(Submode.REINFORCEMENTS, modeController.getGameSubmode());
        modeController.cycleSubmodeBackward();
        Assert.assertEquals(Submode.BATTLE_GROUP, modeController.getGameSubmode());
        modeController.cycleSubmodeBackward();
        Assert.assertEquals(Submode.ENTIRE_ARMY, modeController.getGameSubmode());
    }
}
