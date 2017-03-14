package game.commands;

import game.entities.iDefender;

// Initiate defenses with an actor
public class DefendCommand extends Command {

    private iDefender actor;       // Actor to perform defend
    private int direction;      // Direction of defend

    // Constructor
    public DefendCommand(iDefender actor, int direction, int duration) {
        super(0);
    	this.actor = actor;             // Set actor
        this.direction = direction;     // Set defending direction
    }

    // Execute from Gameboard defense function
    public void exec() {}

}
