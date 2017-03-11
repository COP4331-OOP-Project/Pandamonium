package game.entities.Managers;

import game.entities.EntitySubtypeEnum;
import game.entities.Managers.IdManager.IdManager;
import game.entities.Managers.IdManager.exceptions.IdDoesNotExistException;
import game.entities.Managers.IdManager.exceptions.IdLimitExceededException;
import game.entities.factories.StructureFactory;
import game.entities.factories.exceptions.CapitolLimitExceededException;
import game.entities.factories.exceptions.StructureTypeDoesNotExist;
import game.entities.factories.exceptions.TotalStructureLimitExceededException;
import game.entities.structures.Capitol;
import game.gameboard.Location;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StructureIdManager {
    private static final Integer MAX_STRUCTURE_COUNT = 30;
    private static final Integer MAX_OF_STRUCTURE_TYPE = 10;
    private static final Integer MAX_UNIVERSITIES = 5;
    private static final Integer MAX_CAPITOLS = 1;
    private static final Integer MIN_STRUCTURE_ID = 1;
    private final static Logger log = LogManager.getLogger(StructureFactory.class);

    StructureFactory structureFactory;
    int playerId;
    IdManager allStructuresIdManager;
    IdManager capitolIdManager;

    StructureIdManager(int playerId) {
        this.playerId = playerId;
        this.structureFactory = new StructureFactory();

        this.allStructuresIdManager = new IdManager(MIN_STRUCTURE_ID, MAX_STRUCTURE_COUNT);
        this.capitolIdManager = new IdManager(MIN_STRUCTURE_ID, MAX_CAPITOLS);
    }

    public Capitol createCapitol(Location location)
            throws CapitolLimitExceededException, TotalStructureLimitExceededException, StructureTypeDoesNotExist {

        int newCapitolId;
        try {
            newCapitolId = this.capitolIdManager.getNewId();
        } catch (IdLimitExceededException e) {
            throw new CapitolLimitExceededException("Capitol limit is reached, can't add new capitol.");
        }

        try {
            this.allStructuresIdManager.getNewId();
        } catch (IdLimitExceededException e) {
            try {
                this.capitolIdManager.removeId(newCapitolId);
            } catch (IdDoesNotExistException ex) {
                log.warn("Tried to recover capitol id but it was not yet taken.");
            }
            throw new TotalStructureLimitExceededException("Can't add new capitol, total structure limit has been reached.");
        }

        this.structureFactory.createStructure(EntitySubtypeEnum.CAPITOL, newCapitolId, location);
    }
}
