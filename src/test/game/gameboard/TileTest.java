package game.gameboard;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TileTest {
    private Tile tileWater;
    private Tile tileSand;
    private Tile tileGrass;
    private Tile tileMountain;

    private Location tileWaterLoc;
    private Location tileSandLoc;
    private Location tileGrassLoc;
    private Location tileMountainLoc;

    public void setUp(){
        tileWaterLoc = new Location(20,20);
        tileSandLoc = new Location(20,21);
        tileGrassLoc = new Location(20,22);
        tileMountainLoc = new Location(19,20);

        tileWater = new Tile(TerrainEnum.WATER, tileWaterLoc);
        tileSand = new Tile(TerrainEnum.SAND, tileSandLoc);
        tileGrass = new Tile(TerrainEnum.GRASS, tileGrassLoc);
        tileMountain = new Tile(TerrainEnum.MOUNTAIN, tileMountainLoc);
    }
}
