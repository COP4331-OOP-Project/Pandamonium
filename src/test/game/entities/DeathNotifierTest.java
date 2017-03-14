package game.entities;

import game.Player;
import game.entities.managers.exceptions.UnitDoesNotExistException;
import game.entities.structures.Structure;
import game.entities.units.exceptions.UnitNotFoundException;
import game.gameboard.Gameboard;
import game.gameboard.Location;
import org.junit.Before;
import org.junit.Test;

public class DeathNotifierTest {

    private Player player;
    private Gameboard gb;
    private Location location;

    @Before
    public void setUp(){
        this.location = new Location(0, 0);
        this.gb = new Gameboard();
        this.player = new Player(0, location, gb);
    }

    @Test (expected = UnitNotFoundException.class)
    public void testIfEntityNotifiedDeath() throws UnitNotFoundException {

        Entity e;

        try {
            e = player.addEntity(EntityTypeEnum.UNIT, EntitySubtypeEnum.MELEE, new Location(0, 0));
            e.takeDamage(200);
            player.getEntityById(e.getEntityId());
        } catch (UnitNotFoundException ex) {
            throw new UnitNotFoundException();
        } catch (Exception ex) {}

    }

    @Test
    public void testThatSmallDamageDoesNotKillEntity() {

        Entity e;

        try {

            e = player.addEntity(EntityTypeEnum.UNIT, EntitySubtypeEnum.MELEE, new Location(0, 0));
            e.takeDamage(1);
            assert(e.getCurrentHealth() > 0);

        } catch (Exception ex) {}

    }

    @Test
    public void testThatLargeDamageDoesNotKillEntity() {

        Entity e;

        try {

            e = player.addEntity(EntityTypeEnum.UNIT, EntitySubtypeEnum.MELEE, new Location(0, 0));
            e.takeDamage(200);
            assert(e.getCurrentHealth() < 0);

        } catch (Exception ex) {}

    }

    @Test (expected = UnitNotFoundException.class)
    public void testStructureDeath() throws UnitNotFoundException {

        Entity s;

        try {

            s = player.addEntity(EntityTypeEnum.STRUCTURE, EntitySubtypeEnum.CAPITOL, new Location(0, 0));
            s.takeDamage(200);
            s = player.getEntityById(s.getEntityId());

        } catch (UnitNotFoundException ex) {
            throw new UnitNotFoundException();
        }
        catch (Exception ex) {}

    }

    @Test (expected = UnitNotFoundException.class)
    public void testUnitDeath() throws UnitNotFoundException {

        Entity s;

        try {

            s = player.addEntity(EntityTypeEnum.UNIT, EntitySubtypeEnum.RANGE, new Location(0, 0));
            s.takeDamage(200);
            s = player.getEntityById(s.getEntityId());

        } catch (UnitNotFoundException ex) {
            throw new UnitNotFoundException();
        }
        catch (Exception ex) {}

    }


}
