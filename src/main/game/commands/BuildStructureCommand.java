package game.commands;

import game.entities.Entity;
import game.entities.EntitySubtypeEnum;
import game.gameboard.Tile;

// Initiate making an entity with an actor
public class BuildStructureCommand extends Command {

    private Entity actor;                 // Actor to create an entity
    private Tile target; 	              // Target tile to create on
    private EntitySubtypeEnum structure;  //Type of structure to create

    // Constructor
    public BuildStructureCommand(Entity actor, Tile target, EntitySubtypeEnum structure, int duration) {
    	super(duration);
        this.actor = actor;             // Set actor
        this.target = target;           // Set target tile
    }

    // Execute command
    public void exec() {}

}
