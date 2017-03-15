package game.commands;

import game.Player;
import game.entities.Entity;
import game.entities.EntityId;
import game.entities.EntitySubtypeEnum;
import game.entities.EntityTypeEnum;
import game.entities.structures.Structure;
import game.entities.units.Unit;
import game.gameboard.Gameboard;
import game.gameboard.Location;
import game.gameboard.Tile;
import game.gameboard.iTileObserver;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class MakeCommandsTest {

    private Player player;
    private Gameboard gb;
    private CommandManager commandManager;
    private Entity c;

    @Before
    public void setup() {
        this.gb = new Gameboard();
        this.player = new Player(0, new Location(0, 0), gb);
        this.commandManager = new CommandManager(player);
        this.commandManager.setPlayer(player);

        try {
            this.c = this.player.addEntity(EntityTypeEnum.UNIT, EntitySubtypeEnum.COLONIST, new Location(0, 0));
        } catch (Exception e) {
            Assert.fail();
        }

    }


    // Test if units are being created by the make unit command
    @Test
    public void testIfUnitCreated() {

        MakeUnitCommand makeUnitCommand = new MakeUnitCommand(c, new Location(0, 0), 1, EntitySubtypeEnum.MELEE, this.player.getUnitManager());
        c.addCommandToQueue(makeUnitCommand);
        c.doTurn();

        Tile t = gb.getTileWithLocation(new Location(0, 0));
        ArrayList<Unit> units = t.getUnits();

        Entity e1 = units.get(0);
        Entity e2 = units.get(1);

        assert(e1.getEntityId().getSubTypeId() == EntitySubtypeEnum.COLONIST);
        assert(e2.getEntityId().getSubTypeId() == EntitySubtypeEnum.MELEE);

        double max_health = e2.getCurrentHealth();

        e1.takeDamage(e1.getCurrentHealth());
        e2.takeDamage(1);

        units = t.getUnits();

        assert(units.size() == 1);

        e1 = units.get(0);

        assert(e1.getEntityId().getSubTypeId() == EntitySubtypeEnum.MELEE);
        assert((e1.getCurrentHealth() + 1) == max_health);

        e1.instantDeath();

        units = t.getUnits();

        assert(units.size() == 0);

    }

    // Test if structure is being created at target tile
    @Test
    public void testIfStructureCreated() {

        MakeStructureCommand makeStructureCommand = new MakeStructureCommand(c, new Location(0, 0), 1, EntitySubtypeEnum.FORT, this.player.getStructureManager());
        c.addCommandToQueue(makeStructureCommand);
        c.doTurn();

        Tile t = gb.getTileWithLocation(new Location(0, 0));
        Structure f = t.getStructure();

        EntityId e = f.getEntityId();

        assert(e.getSubTypeId() == EntitySubtypeEnum.FORT);

        f.takeDamage(1);

        f = t.getStructure();

        assert(f != null);

        f.instantDeath();

        f = t.getStructure();

        assert(f == null);

        Tile t2 = gb.getTileWithLocation(new Location(0, 1));
        ArrayList<iTileObserver> list = t2.getInfluencedBy();
        iTileObserver o = list.get(0);

        assert(o != null);

    }

}
