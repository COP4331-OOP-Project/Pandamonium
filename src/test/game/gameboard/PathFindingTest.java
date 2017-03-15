package game.gameboard;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PathFindingTest {
    private Gameboard gBoard;
    private PathFinding pathFinder;

    private Location startLocation;
    private Location endLocation;
    private int direction;
    private int playerId;

    @Before
    public void setUp(){
        gBoard = new Gameboard();
        pathFinder = new PathFinding(gBoard);
        startLocation = new Location(5,28);
        playerId = 0;
    }

    @Test //Test for moving
    /*
                    ---     --x
                    -x- --->---
                    ---     ---
     */
    public void TestMoving45(){
        endLocation = new Location(7,26);
        direction = pathFinder.pathAlgorithm(startLocation, endLocation, playerId);

        Assert.assertEquals(45, direction);
    }

    @Test
    public void TestMoving2Step(){
        endLocation = new Location(32,11);
        direction = pathFinder.pathAlgorithm(startLocation, endLocation, playerId);

        Assert.assertEquals(135, direction);
    }

    @Test //Test moving270
    public void TestMoving270(){
        endLocation = new Location(5,29);
        direction = pathFinder.pathAlgorithm(startLocation, endLocation, playerId);

        Assert.assertEquals(180, direction);
    }

    @Test //Test moving 225
    public void TestMoving225(){
        endLocation = new Location(4,29);
        direction = pathFinder.pathAlgorithm(startLocation, endLocation, playerId);

        Assert.assertEquals(225, direction);
    }
}
