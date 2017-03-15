package game.commands;

import game.entities.Entity;
import game.entities.EntitySubtypeEnum;
import game.entities.EntityTypeEnum;
import game.entities.managers.StructureManager;
import game.entities.managers.UnitManager;
import game.gameboard.Tile;

// Command to make an entity
public class MakeUnitCommand extends Command {

    // Constructor
    public MakeUnitCommand(Entity actor, Tile target, int duration, EntityTypeEnum type, EntitySubtypeEnum subtype, UnitManager unitManager) {
        super(duration);
    }

    public void exec() {}

}
