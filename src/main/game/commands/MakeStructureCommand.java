package game.commands;

import game.entities.Entity;
import game.entities.EntitySubtypeEnum;
import game.entities.EntityTypeEnum;
import game.entities.managers.StructureManager;
import game.gameboard.Tile;

public class MakeStructureCommand extends Command {

    private Entity actor;
    private StructureManager structureManager;
    private EntityTypeEnum type;
    private EntitySubtypeEnum subtype;

    // Constructor
    public MakeStructureCommand(Entity actor, Tile target, int duration, EntityTypeEnum type, EntitySubtypeEnum subtype, StructureManager structureManager) {
        super(duration);
        this.actor = actor;
        this.structureManager = structureManager;
        this.type = type;
        this.subtype = subtype;
    }

    // Executre structure creation
    public void exec() {
    }
}
