package game;

import game.entities.EntitySubtypeEnum;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GameModelTest {
    private GameModel gameModel;

    @Before
    public void setUp(){
        gameModel = new GameModel();
        gameModel.initializeGame();
    }

    @Test //Check if unit is added to the player and tile
    public void testInitialUnit(){
        Assert.assertEquals(gameModel.getPlayer(0).getUnits().get(0).getType(), EntitySubtypeEnum.COLONIST);
    }
}
