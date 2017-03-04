package game;

import game.Player;
import game.entities.EntityId;
import game.entities.EntitySubtypeEnum;
import game.entities.EntityTypeEnum;
import game.entities.stats.UnitStats;
import game.entities.units.Colonist;
import game.entities.units.Melee;
import game.entities.units.Ranged;
import game.entities.units.UnitType;
import game.entities.units.exceptions.UnitNotFoundException;
import game.entities.workers.workerTypes.Worker;
import game.gameboard.Location;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class PlayerTest {
    private Location loc;
    private int playerId = 1;
    private Player player;

    private Melee melee1;
    private EntityId melee1Id;
    private Melee melee2;
    private EntityId melee2Id;
    private Ranged range1;
    private EntityId range1Id;
    private Ranged range2;
    private EntityId range2Id;
    private Colonist colonist1;
    private EntityId colonistId;

    private Location unitLocation;

    //Check with Gavin on worker
//    private Worker worker1;
//    private Worker worker2;
//    private Worker worker3;

    private UnitStats meleeStats;
    private UnitStats rangedStats;
    private UnitStats colonistStats;

    @Before
    public void setUp(){
        try {
            meleeStats = new UnitStats(UnitType.MELEE);
            rangedStats = new UnitStats(UnitType.RANGED);
            colonistStats = new UnitStats((UnitType.COLONIST));

            melee1Id = new EntityId(this.playerId, EntityTypeEnum.UNIT, EntitySubtypeEnum.MELEE, 1);
            melee2Id = new EntityId(this.playerId, EntityTypeEnum.UNIT, EntitySubtypeEnum.MELEE, 2);
            range1Id = new EntityId(this.playerId, EntityTypeEnum.UNIT, EntitySubtypeEnum.RANGE, 1);
            range2Id = new EntityId(this.playerId, EntityTypeEnum.UNIT, EntitySubtypeEnum.RANGE, 2);
            colonistId = new EntityId(this.playerId,EntityTypeEnum.UNIT, EntitySubtypeEnum.COLONIST, 1);

            unitLocation = new Location(3,3);

            melee1 = new Melee(meleeStats,unitLocation, melee1Id);
            melee2 = new Melee(meleeStats,unitLocation, melee2Id);
            range1 = new Ranged(rangedStats, unitLocation, range1Id);
            range2 = new Ranged(rangedStats, unitLocation, range2Id);
            colonist1 = new Colonist(colonistStats, unitLocation, colonistId);

            this.loc = new Location(5, 5);
            this.player = new Player(playerId, loc);
        } catch (IllegalArgumentException e){
            Assert.fail();
        } catch (UnitNotFoundException e) {
            Assert.fail();
        }
    }

    @Test //check if adding melee is true
    public void getNewMeleeCount(){
        this.player.addMelee(melee1);
        this.player.addMelee(melee2);

        Assert.assertEquals(this.player.getMelees().size(), 2);
    }

}
