package game.commands;

import game.entities.iDefender;

// Initiate defenses with an actor
public class DefendCommand extends Command {

    private iDefender actor;       // Actor to perform defend
    private int direction;      // Direction of defend

    // Constructor
    public DefendCommand(iDefender actor, int direction, int duration) {
        this.actor = actor;             // Set actor
        this.direction = direction;     // Set defending direction
        super.duration = duration;      // Set duration
    }

    // Execute from Gameboard defense function
    public void exec() {}

}
