package game.entities;

import game.Player;
import game.entities.factories.EntityTypeDoesNotExistException;
import game.entities.factories.exceptions.*;
import game.entities.managers.exceptions.WorkerLimitExceededException;
import game.entities.managers.exceptions.WorkerTypeDoesNotExist;
import game.gameboard.Location;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ArmyTest {
    private Army humanArmy1;
    private Player human;
    private Player panda;

    private Location unit1Location;
    private Location unit2Location;
    private Location unit3Location;
    private Location unit4Location;
    private Location unit5Location;
    private Location unit6Location;
    

    @Before
    public void setUp(){
        unit1Location = new Location(13,13);
        unit2Location = new Location(10,13);
        unit3Location = new Location(11,11);
        unit4Location = new Location(20,20);
        unit5Location = new Location(30,30);
        unit6Location = new Location(32,32);
        try {
            human.addEntity(EntityTypeEnum.UNIT, EntitySubtypeEnum.MELEE, unit1Location);
            human.addEntity(EntityTypeEnum.UNIT, EntitySubtypeEnum.RANGE, unit2Location);
            human.addEntity(EntityTypeEnum.UNIT, EntitySubtypeEnum.MELEE, unit3Location);
            human.addEntity(EntityTypeEnum.UNIT, EntitySubtypeEnum.MELEE, unit1Location);
            human.addEntity(EntityTypeEnum.UNIT, EntitySubtypeEnum.RANGE, unit1Location);
        }catch( EntityTypeDoesNotExistException | UnitTypeLimitExceededException |StructureTypeLimitExceededException |
                TotalUnitLimitExceededException | TotalStructureLimitExceededException |
                UnitTypeDoesNotExistException | StructureTypeDoesNotExist e){
            Assert.fail();
        }
    }

    @Test //Test create army
    public void testCreateArmy(){
        human.addArmy(EntityTypeEnum.ARMY, )
    }
}
