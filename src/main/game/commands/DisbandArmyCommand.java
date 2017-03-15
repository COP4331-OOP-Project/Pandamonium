package game.commands;

import game.entities.Army;

//import game.entities.Army;

/**
 * Disband an established actor army
 */
public class DisbandArmyCommand extends Command {

  private Army actor;                 // army actor to disband

    // Constructor
    public DisbandArmyCommand(Army actor) {
    	super(0);
        this.actor = actor;             // Set actor
    }

    // Execute army disband from Gameboard function
    public void exec() {
      actor.disband();
    }

}
