package game.commands;

import game.Player;
import game.entities.EntitySubtypeEnum;
import game.entities.EntityTypeEnum;
import game.entities.factories.EntityTypeDoesNotExistException;
import game.entities.stats.UnitStats;
import game.entities.units.Unit;
import game.gameboard.Gameboard;
import game.gameboard.Location;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

// Test the attack command
public class AttackCommandTest {
    private static final Location HUMAN_STARTING_LOCATION = new Location(5, 28);
    private static final Location PANDA_STARTING_LOCATION = new Location(32, 11);

    private Player human;
    private Player panda;

    private Gameboard gBoard;
    private CommandManager commandManager;
    private ArrayList<Unit> armyUnits;

    Location humanMeleeLocation;
    Location pandaMeleeLocation;

    @Before
    public void setUp(){
        gBoard = new Gameboard();
        human = new Player(0, HUMAN_STARTING_LOCATION,gBoard);
        panda = new Player(1, PANDA_STARTING_LOCATION, gBoard);

        humanMeleeLocation = new Location(28,28);
        pandaMeleeLocation = new Location (28,29);

        commandManager = new CommandManager(human);
        commandManager.setPlayer(human);

        try{
            human.addEntity(EntityTypeEnum.UNIT, EntitySubtypeEnum.MELEE, humanMeleeLocation);
            armyUnits = new ArrayList<Unit>();
            armyUnits.add(human.getUnits().get(0));
            panda.addEntity(EntityTypeEnum.UNIT, EntitySubtypeEnum.MELEE, pandaMeleeLocation);
        } catch (Exception e){
            Assert.fail();
        }

        try {
            human.addArmy(EntityTypeEnum.ARMY, armyUnits, humanMeleeLocation);
        }catch (EntityTypeDoesNotExistException e){
            Assert.fail();
        }
    }

    @Test //Test melee attack against unit
    public void testMeleeAttackUnit(){
        Assert.assertEquals(30, panda.getUnits().get(0).getCurrentHealth(),0);
        AttackCommand attackCommand = new AttackCommand(human.getUnits().get(0), gBoard.getTiles()[28][29], 1);
        human.getArmies().get(0).addCommandToQueue(attackCommand);
        human.getArmies().get(0).doTurn();
        Assert.assertEquals(25, panda.getUnits().get(0).getCurrentHealth(),0);
    }

    @Test //Test melee attack against army
    public void testMeleeAttackArmy(){
        ArrayList<Unit> pandaUnits = new ArrayList<Unit>();
        try {
            panda.addArmy(EntityTypeEnum.ARMY, pandaUnits, pandaMeleeLocation);
        }catch (EntityTypeDoesNotExistException e){
            Assert.fail();
        }

        Assert.assertEquals(30, panda.getArmies().get(0).getBattleGroup().getBattleGroup().get(0),0);
        AttackCommand attackCommand = new AttackCommand(human.getUnits().get(0), gBoard.getTiles()[28][29], 1);
        human.getArmies().get(0).addCommandToQueue(attackCommand);
        human.getArmies().get(0).doTurn();
        Assert.assertEquals(25, panda.getArmies().get(0).getCurrentHealth(),0);
    }
}
