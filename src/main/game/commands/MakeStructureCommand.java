package game.commands;

import game.entities.Entity;
import game.entities.EntitySubtypeEnum;
import game.entities.EntityTypeEnum;
import game.entities.factories.exceptions.StructureTypeDoesNotExist;
import game.entities.factories.exceptions.StructureTypeLimitExceededException;
import game.entities.factories.exceptions.TotalStructureLimitExceededException;
import game.entities.managers.StructureManager;
import game.entities.structures.Structure;
import game.gameboard.Location;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// Command to create a structure
public class MakeStructureCommand extends Command {

    // Logger
    private final static Logger log = LogManager.getLogger(MakeStructureCommand.class);

    private Entity actor;                           // Actor
    private Location location;                      // Location to build structure on
    private StructureManager structureManager;      // Manager to build structure
    private EntitySubtypeEnum subtype;              // Type of structure to build

    // Constructor
    public MakeStructureCommand(Entity actor, Location location, int duration, EntitySubtypeEnum subtype, StructureManager structureManager) {

        super(duration);
        this.actor = actor;
        this.location = location;
        this.structureManager = structureManager;
        this.subtype = subtype;

    }

    // Executre structure creation
    public void exec() {

        // Attempt to create structure
        try {
            structureManager.addStructure(subtype, location);
        } catch (StructureTypeLimitExceededException | TotalStructureLimitExceededException e) {
            log.warn(e.getMessage());   // Too many structures of this type, or too many structures in general
        } catch (StructureTypeDoesNotExist e) {
            log.error(e.getMessage());  // Unknown structure subtype
        }

    }

}
