package game;

import game.Player;
import game.entities.EntityId;
import game.entities.EntitySubtypeEnum;
import game.entities.EntityTypeEnum;
import game.entities.stats.StructureStats;
import game.entities.stats.UnitStats;
import game.entities.structures.Capitol;
import game.entities.structures.Farm;
import game.entities.EntitySubtypeEnum;
import game.entities.structures.exceptions.StructureNotFoundException;
import game.entities.units.Colonist;
import game.entities.units.Melee;
import game.entities.units.Ranged;
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

    private UnitStats meleeStats;
    private UnitStats rangedStats;
    private UnitStats colonistStats;
    private StructureStats capitolStat;
    private StructureStats farmStat;

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

    private Capitol capitol;
    private EntityId capitolId;
    private Farm farm;
    private EntityId farmId;

    private Location unitLocation;
    private Location capitolLocation;
    private Location farmLocation;

    //Check with Gavin on worker
//    private Worker worker1;
//    private Worker worker2;
//    private Worker worker3;

    @Before
    public void setUp(){
        try {
            meleeStats = new UnitStats(EntitySubtypeEnum.MELEE);
            rangedStats = new UnitStats(EntitySubtypeEnum.RANGE);
            colonistStats = new UnitStats((EntitySubtypeEnum.COLONIST));
            capitolStat = new StructureStats(EntitySubtypeEnum.CAPITOL);
            farmStat = new StructureStats(EntitySubtypeEnum.FARM);


            melee1Id = new EntityId(this.playerId, EntityTypeEnum.UNIT, EntitySubtypeEnum.MELEE, 1);
            melee2Id = new EntityId(this.playerId, EntityTypeEnum.UNIT, EntitySubtypeEnum.MELEE, 2);
            range1Id = new EntityId(this.playerId, EntityTypeEnum.UNIT, EntitySubtypeEnum.RANGE, 1);
            range2Id = new EntityId(this.playerId, EntityTypeEnum.UNIT, EntitySubtypeEnum.RANGE, 2);
            colonistId = new EntityId(this.playerId,EntityTypeEnum.UNIT, EntitySubtypeEnum.COLONIST, 1);

            capitolId = new EntityId(this.playerId, EntityTypeEnum.STRUCTURE, EntitySubtypeEnum.CAPITOL, 1);
            farmId = new EntityId(this.playerId, EntityTypeEnum.STRUCTURE, EntitySubtypeEnum.FARM, 2);

            unitLocation = new Location(3,3);
            capitolLocation = new Location(12,16);
            farmLocation = new Location(11,16);

            melee1 = new Melee(meleeStats,unitLocation, melee1Id);
            melee2 = new Melee(meleeStats,unitLocation, melee2Id);
            range1 = new Ranged(rangedStats, unitLocation, range1Id);
            range2 = new Ranged(rangedStats, unitLocation, range2Id);
            colonist1 = new Colonist(colonistStats, unitLocation, colonistId);

            capitol = new Capitol(capitolStat, capitolLocation, capitolId);
            farm = new Farm(farmStat, farmLocation, farmId);

            this.loc = new Location(5, 5);
            this.player = new Player(playerId, loc);
        } catch (IllegalArgumentException e){
            Assert.fail();
        } catch (UnitNotFoundException e) {
            Assert.fail();
        } catch (StructureNotFoundException e){
            Assert.fail();
        }
    }

    @Test //check adding Melee Unit
    public void getNewMeleeCount(){
        this.player.addMelee(melee1);
        this.player.addMelee(melee2);

        Assert.assertEquals(this.player.getMelees().size(), 2);
    }

    @Test //Check adding Range unit
    public void getNewRangeCount(){
        this.player.addRanged(range1);
        this.player.addRanged(range2);

        Assert.assertEquals(this.player.getRanges().size(), 2);
    }

    @Test //Check RemoveEntity()
    public void validUnitRemoval(){
        this.player.addMelee(melee1);
        this.player.addMelee(melee2);

        this.player.removeEntity(melee1Id);

        Assert.assertEquals(player.getMelees().get(0).getEntityId(), melee2Id);

        this.player.addColonist(colonist1);
        this.player.removeEntity(colonistId);

        Assert.assertEquals(this.player.getColonists().isEmpty(), true);
    }

    @Test //Check add structure
    public void validAddStructure(){
        this.player.addCapitol(capitol);
        this.player.addFarm(farm);
        Assert.assertEquals(this.player.getStructures().size(), 2);
        Assert.assertEquals(this.player.getStructures().get(0), capitol);
    }

    @Test //Remove Structure
    public void validStructureRemoval(){
        this.player.addCapitol(capitol);
        this.player.addFarm(farm);

        player.removeEntity(capitolId);

        Assert.assertEquals(this.player.getStructures().get(0), farm);

        player.removeEntity(farmId);

        Assert.assertEquals(this.player.getStructures().isEmpty(), true);
    }
}
