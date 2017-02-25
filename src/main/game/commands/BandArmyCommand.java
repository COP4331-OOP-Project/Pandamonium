package game.commands;

import game.entities.units.Unit;

import java.util.ArrayList;

/**
 * Band an established collection of unit actors
 */
public class BandArmyCommand extends Command {

    private ArrayList<Unit> actors;                 // Actor units to band together

    // Constructor
    public BandArmyCommand(ArrayList<Unit> actors, int duration) {
        this.actors = actors;           // Set actors to band
        super.duration = duration;      // Set duration time to band
    }

    // Execute command
    public void exec() {}

}
