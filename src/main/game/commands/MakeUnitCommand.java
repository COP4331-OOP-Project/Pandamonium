package game.commands;

import game.entities.Entity;
import game.entities.EntitySubtypeEnum;
import game.entities.factories.exceptions.*;
import game.entities.managers.StructureManager;
import game.entities.managers.UnitManager;
import game.gameboard.Location;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// Command to create a structure
public class MakeUnitCommand extends Command {

    // Logger
    private final static Logger log = LogManager.getLogger(MakeUnitCommand.class);

    private Entity actor;                           // Actor
    private Location location;                      // Location to build structure on
    private UnitManager unitManager;                // Manager to build unit
    private EntitySubtypeEnum subtype;              // Type of unit to build

    // Constructor
    public MakeUnitCommand(Entity actor, Location location, int duration, EntitySubtypeEnum subtype, UnitManager unitManager) {

        super(duration);
        this.actor = actor;
        this.location = location;
        this.unitManager = unitManager;
        this.subtype = subtype;

    }

    // Execute unit creation
    public void exec() {

        // Attempt to create unit
        try {
            unitManager.addUnit(subtype, location);
        } catch (UnitTypeLimitExceededException | TotalUnitLimitExceededException e) {
            log.warn(e.getMessage());   // Too many units of this type, or too many units in general
        } catch (UnitTypeDoesNotExistException e) {
            log.error(e.getMessage());  // Unknown unit subtype
        }

    }

}
