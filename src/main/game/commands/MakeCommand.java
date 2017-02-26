package game.commands;


import game.entities.Entity;
import game.gameboard.Tile;

// Initiate making an entity with an actor
public class MakeCommand extends Command {

    private Entity actor;                    // Actor to create an entity
    private Tile target;              // Direction to create in
    private String entityCode;          // Unique entity code to create

    // Constructor
    public MakeCommand(Entity actor, Tile target, String entityCode) {
        this.actor = actor;             // Set actor
        this.target = target;           // Set target tile
        this.entityCode = entityCode;   // Set entity creation code
        super.duration = duration;      //
    }

    // Execute make from Gameboard function
    public void exec() {}

}
