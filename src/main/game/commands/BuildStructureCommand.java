package game.commands;

import game.entities.Entity;
import game.entities.EntitySubtypeEnum;
import game.gameboard.Tile;

// Initiate making an entity with an actor
public class BuildStructureCommand extends Command {

    private Entity actor;                  // Actor to create an entity
    private Tile target; 	              // Direction to create in
    private EntitySubtypeEnum structure;  //Type of structure to create

    // Constructor
    public BuildStructureCommand(Entity actor, Tile target, EntitySubtypeEnum structure) {
        this.actor = actor;             // Set actor
        this.target = target;           // Set target tile
        this.structure = structure;
        super.duration = duration;      //
    }

    // Execute make from Gameboard function
    public void exec() {}

}