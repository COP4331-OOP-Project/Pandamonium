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
    public void TestMoving1Step(){
        endLocation = new Location(6,29);
        //direction = pathFinder.pathAlgorithm(startLocation, endLocation, playerId);

        //Assert.assertEquals(45, direction);
    }

}
