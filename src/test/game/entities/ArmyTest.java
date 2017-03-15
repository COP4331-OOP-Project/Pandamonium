package game.entities;

import game.Player;
import game.entities.factories.EntityTypeDoesNotExistException;
import game.entities.factories.exceptions.*;
import game.entities.managers.exceptions.WorkerLimitExceededException;
import game.entities.managers.exceptions.WorkerTypeDoesNotExist;
import game.entities.units.Unit;
import game.gameboard.Gameboard;
import game.gameboard.Location;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class ArmyTest {
    private Player human;
    private Player panda;
    private Gameboard gBoard;

    private Location unit1Location;
    private Location unit2Location;
    private Location unit3Location;
    private Location unit4Location;
    private Location unit5Location;
    private Location unit6Location;
    private Location armyLocation;
    

    @Before
    public void setUp(){
        gBoard = new Gameboard();
        human = new Player(1, unit1Location, gBoard);

        unit1Location = new Location(13,13);
        unit2Location = new Location(30,30);
        unit3Location = new Location(11,11);
        unit4Location = new Location(20,20);
        unit5Location = new Location(30,30);
        unit6Location = new Location(32,32);
        try {
            human.addEntity(EntityTypeEnum.UNIT, EntitySubtypeEnum.MELEE, unit1Location);
            human.addEntity(EntityTypeEnum.UNIT, EntitySubtypeEnum.MELEE, unit2Location);
            human.addEntity(EntityTypeEnum.UNIT, EntitySubtypeEnum.MELEE, unit1Location);
            human.addEntity(EntityTypeEnum.UNIT, EntitySubtypeEnum.RANGE, unit1Location);
            human.addEntity(EntityTypeEnum.UNIT, EntitySubtypeEnum.RANGE, unit2Location);
        }catch( EntityTypeDoesNotExistException | UnitTypeLimitExceededException |StructureTypeLimitExceededException |
                TotalUnitLimitExceededException | TotalStructureLimitExceededException |
                UnitTypeDoesNotExistException | StructureTypeDoesNotExist e){
            Assert.fail();
        }
    }

    @Test //Test create army
    public void testCreateArmy(){
        ArrayList<Unit> armyUnits = new ArrayList<Unit>();
        armyUnits.add(human.getUnits().get(0));
        armyUnits.add(human.getUnits().get(3));
        armyUnits.add(human.getUnits().get(4));
        try {
            human.addArmy(EntityTypeEnum.ARMY, armyUnits, unit1Location);
        }catch (EntityTypeDoesNotExistException e){
            Assert.fail();
        }
        Assert.assertEquals(2, human.getArmies().get(0).getBattleGroup().getBattleGroup().size());
        Assert.assertEquals(EntitySubtypeEnum.RANGE, human.getArmies().get(0).getReinforcements().getReinforcements().get(0).getType());
    }

    @Test //Test ArmyId for Unit
    public void testArmyIdForUnit(){
        ArrayList<Unit> armyUnits = new ArrayList<Unit>();
        armyUnits.add(human.getUnits().get(0));
        try {
            human.addArmy(EntityTypeEnum.ARMY, armyUnits, unit1Location);
        }catch (EntityTypeDoesNotExistException e){
            Assert.fail();
        }
        Assert.assertEquals((Integer)1, human.getUnits().get(0).getArmyId());

        human.getArmies().get(0).disband();
        Assert.assertEquals(null, human.getUnits().get(0).getArmyId());
    }
}
