package game.gameboard;

import game.entities.EntityId;
import game.entities.EntitySubtypeEnum;
import game.entities.EntityTypeEnum;
import game.entities.managers.MovementManager;
import game.entities.stats.StructureStats;
import game.entities.stats.UnitStats;
import game.entities.structures.Capitol;
import game.entities.structures.Farm;
import game.entities.structures.exceptions.StructureNotFoundException;
import game.entities.units.Colonist;
import game.entities.units.Melee;
import game.entities.units.Ranged;
import game.entities.units.*;
import game.entities.units.exceptions.UnitNotFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class TileTest {
    int playerId;

    Gameboard gameboard;
    MovementManager movementManager;

    private Tile tileWater;
    private Tile tileSand;
    private Tile tileGrass;
    private Tile tileMountain;

    private Location tileWaterLoc;
    private Location tileSandLoc;
    private Location tileGrassLoc;
    private Location tileMountainLoc;

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

    @Before
    public void setUp() {
        try {
            gameboard = new Gameboard();
            movementManager = new MovementManager(gameboard);

            playerId = 1;
            meleeStats = new UnitStats(EntitySubtypeEnum.MELEE);
            rangedStats = new UnitStats(EntitySubtypeEnum.RANGE);
            colonistStats = new UnitStats((EntitySubtypeEnum.COLONIST));
            capitolStat = new StructureStats(EntitySubtypeEnum.CAPITOL);
            farmStat = new StructureStats(EntitySubtypeEnum.FARM);


            melee1Id = new EntityId(this.playerId, EntityTypeEnum.UNIT, EntitySubtypeEnum.MELEE, 1, 1);
            melee2Id = new EntityId(this.playerId, EntityTypeEnum.UNIT, EntitySubtypeEnum.MELEE, 2, 2);
            range1Id = new EntityId(this.playerId, EntityTypeEnum.UNIT, EntitySubtypeEnum.RANGE, 1, 3);
            range2Id = new EntityId(this.playerId, EntityTypeEnum.UNIT, EntitySubtypeEnum.RANGE, 2, 4);
            colonistId = new EntityId(this.playerId, EntityTypeEnum.UNIT, EntitySubtypeEnum.COLONIST, 1, 5);

            capitolId = new EntityId(this.playerId, EntityTypeEnum.STRUCTURE, EntitySubtypeEnum.CAPITOL, 1, 1);
            farmId = new EntityId(this.playerId, EntityTypeEnum.STRUCTURE, EntitySubtypeEnum.FARM, 2, 2);

            unitLocation = new Location(3, 3);
            capitolLocation = new Location(12, 16);
            farmLocation = new Location(11, 16);

            melee1 = new Melee(meleeStats, unitLocation, melee1Id, movementManager);
            melee2 = new Melee(meleeStats, unitLocation, melee2Id, movementManager);
            range1 = new Ranged(rangedStats, unitLocation, range1Id, movementManager);
            range2 = new Ranged(rangedStats, unitLocation, range2Id, movementManager);
            colonist1 = new Colonist(colonistStats, unitLocation, colonistId, movementManager);

            capitol = new Capitol(capitolStat, capitolLocation, capitolId, movementManager);
            farm = new Farm(farmStat, farmLocation, farmId, movementManager);

            tileWaterLoc = new Location(20, 20);
            tileSandLoc = new Location(20, 21);
            tileGrassLoc = new Location(20, 22);
            tileMountainLoc = new Location(19, 20);

            tileWater = new Tile(TerrainEnum.WATER, tileWaterLoc);
            tileSand = new Tile(TerrainEnum.SAND, tileSandLoc);
            tileGrass = new Tile(TerrainEnum.GRASS, tileGrassLoc);
            tileMountain = new Tile(TerrainEnum.MOUNTAIN, tileMountainLoc);
        } catch (UnitNotFoundException e) {
            Assert.fail();
        } catch (StructureNotFoundException e) {
            Assert.fail();
        }
    }

    @Test
    public void TestCorrectLocations(){
        ArrayList<Unit> unit=gameboard.getTiles()[unitLocation.getX()][unitLocation.getY()].getUnits();
        Assert.assertEquals(unit.size(), 5);
    }

    @Test //Test ownership of the tile
    public void testOwnerShipOfTile(){
        //If no unit, return -1
        Assert.assertEquals(tileGrass.getOwner(), -1);

        tileGrass.addUnit(melee1);
        tileGrass.addUnit(melee2);

        Assert.assertEquals(tileGrass.getOwner(), playerId);
    }

    @Test //Test the enemy blocker
    public void invalidAddUnitOnTile(){
        EntityId enemyEntityMeleeId = new EntityId(2, EntityTypeEnum.UNIT, EntitySubtypeEnum.MELEE, 1, 1);
        Melee melee3 = new Melee(meleeStats, unitLocation, enemyEntityMeleeId, movementManager);

        tileGrass.addUnit(melee3);
        tileGrass.addUnit(melee1);

        Assert.assertEquals(this.tileGrass.getUnits().size(), 2);
    }

    @Test //Test contains unit
    public void testContainsUnit(){
        tileGrass.addUnit(colonist1);
        Assert.assertEquals(tileGrass.containsUnit(), true);
    }

    @Test //Test unit arrayList
    public void testReturnUnits(){
        ArrayList<Unit> units = new ArrayList<Unit>();
        units.add(melee1);
        units.add(colonist1);
        units.add(range1);
        units.add(range2);

        tileGrass.addUnit(melee1);
        tileGrass.addUnit(colonist1);
        tileGrass.addUnit(range1);
        tileGrass.addUnit(range2);

        Assert.assertEquals(tileGrass.getUnits(), units);
    }

    @Test //Test on impassible tiles
    public void testImpassableTile(){
        Assert.assertEquals(this.tileWater.isImpassable(), true);
    }

    @Test //Check contains Structure()
    public void testContainsStructure(){
        tileGrass.addStructure(this.capitol);
        Assert.assertEquals(this.tileGrass.containsStructure(), true);
        Assert.assertEquals(this.tileGrass.getStructure(), capitol);
    }

    @Test //Check unit and structure removal
    public void testTileRemove(){
        tileGrass.addUnit(melee1);
        tileGrass.addUnit(melee2);

        tileGrass.removeEntity(melee1Id);
        Assert.assertEquals(tileGrass.getUnits().get(0), melee2);

        tileGrass.addStructure(capitol);
        tileGrass.removeEntity(capitolId);
        Assert.assertEquals(tileGrass.getStructure(), null);
    }

}