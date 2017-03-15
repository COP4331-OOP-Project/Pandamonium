package game.commands;

import game.entities.Army;
import game.entities.factories.exceptions.ArmyLimitExceededException;
import game.entities.managers.ArmyManager;
import game.entities.managers.exceptions.ArmyDoesNotExistException;
import game.entities.units.Unit;
import game.gameboard.Location;

import java.util.ArrayList;

/**
 * Band an army
 */
public class BandArmyCommand extends Command {

    private ArrayList<Unit> actors;                 // army actor to disband
    private Location location;
    private ArmyManager manager;

    // Constructor
    public BandArmyCommand(ArrayList<Unit> units, Location location, ArmyManager armyManager) {
        super(0);
        this.actors = actors;             // Set actor
        this.location = location;
        this.manager = armyManager;
    }

    // Execute army banding
    public void exec() {

        try {
            this.manager.addArmy(actors, location);
        } catch (ArmyDoesNotExistException e) {
            e.getLocalizedMessage();
        } catch (ArmyLimitExceededException e) {
            e.getLocalizedMessage();
        }

    }

}
