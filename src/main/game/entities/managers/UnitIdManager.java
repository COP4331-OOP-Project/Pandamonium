package game.entities.managers;

import game.entities.EntityId;
import game.entities.EntitySubtypeEnum;
import game.entities.factories.UnitFactory;
import game.entities.factories.exceptions.TotalUnitLimitExceededException;
import game.entities.factories.exceptions.UnitTypeDoesNotExistException;
import game.entities.factories.exceptions.UnitTypeLimitExceededException;
import game.entities.managers.IdManager.IdManager;
import game.entities.managers.IdManager.exceptions.IdDoesNotExistException;
import game.entities.managers.IdManager.exceptions.IdLimitExceededException;
import game.entities.managers.exceptions.UnitDoesNotExistException;
import game.entities.units.Colonist;
import game.entities.units.Explorer;
import game.entities.units.Melee;
import game.entities.units.Ranged;
import game.gameboard.Gameboard;
import game.gameboard.Location;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UnitIdManager {

    private final static Logger log = LogManager.getLogger(UnitIdManager.class);

    // Static limits on unit counts
    private static final Integer MAX_UNIT_COUNT = 25;
    private static final Integer MIN_UNIT_ID = 1;
    private static final Integer MAX_OF_UNIT_TYPE = 10;

    // Factory
    private UnitFactory unitFactory;

    // Id managers for each Unit Type
    private IdManager allUnitIdManager;
    private IdManager meleeIdManager;
    private IdManager rangedIdManager;
    private IdManager explorerIdManager;
    private IdManager colonistIdManager;

    // Constructor
    public UnitIdManager(int playerId, Gameboard gb) {

        // Setup unit factory
        this.unitFactory = new UnitFactory(playerId, gb);

        // Setup Id managers
        this.allUnitIdManager = new IdManager(MIN_UNIT_ID, MAX_UNIT_COUNT);
        this.meleeIdManager = new IdManager(MIN_UNIT_ID, MAX_OF_UNIT_TYPE);
        this.rangedIdManager = new IdManager(MIN_UNIT_ID, MAX_OF_UNIT_TYPE);
        this.colonistIdManager = new IdManager(MIN_UNIT_ID, MAX_OF_UNIT_TYPE);
        this.explorerIdManager = new IdManager(MIN_UNIT_ID, MAX_OF_UNIT_TYPE);

    }

    Colonist createColonist(Location location)
        throws UnitTypeLimitExceededException, TotalUnitLimitExceededException {

        Integer newColonistId;          // New id
        Integer newColonistGlobalId;   // New Global Id

        // Get new colonist id, assign to unit
        try {
            newColonistId = this.colonistIdManager.getNewId();
        } catch (IdLimitExceededException e) {
            throw new UnitTypeLimitExceededException("Colonist limit is reached, can't add new colonist.");
        }

        // Check if we have exceeded total unit count
        try {
            newColonistGlobalId = this.allUnitIdManager.getNewId();
        } catch (IdLimitExceededException e) {
            try {
                this.colonistIdManager.removeId(newColonistId);
            } catch (IdDoesNotExistException dne) {
                log.warn("Tried to recover colonist id but was not yet taken.");
            }
            throw new TotalUnitLimitExceededException("Can't add new colonist, total unit limit has been reached.");
        }

        // Create new colonist
        try {
            return (Colonist) this.unitFactory.createUnit(EntitySubtypeEnum.COLONIST, newColonistId, newColonistGlobalId, location);
        } catch (UnitTypeDoesNotExistException e) {
            throw new RuntimeException(e.getLocalizedMessage());
        }

    }

    void removeColonist(EntityId entityId) throws UnitDoesNotExistException {
        try {
            this.colonistIdManager.removeId(entityId.getInstanceId());
            this.allUnitIdManager.removeId(entityId.getGlobalTypeId());
        } catch (IdDoesNotExistException e) {
            throw new UnitDoesNotExistException("Colonist " + entityId.getInstanceId() + " does not exist as an id.");
        }
    }

    Explorer createExplorer(Location location)
        throws UnitTypeLimitExceededException, TotalUnitLimitExceededException {

            Integer newExplorerId;        // New id
            Integer newExplorerGlobalId;  // New global id

            // Get new explorer id, assign to unit
            try {
                newExplorerId = this.explorerIdManager.getNewId();
            } catch (IdLimitExceededException e) {
                throw new UnitTypeLimitExceededException("Explorer limit is reached, can't add new explorer.");
            }

            // Check if we have exceeded total unit count
            try {
                newExplorerGlobalId = this.allUnitIdManager.getNewId();
            } catch (IdLimitExceededException e) {
                try {
                    this.explorerIdManager.removeId(newExplorerId);
                } catch (IdDoesNotExistException dne) {
                    log.warn("Tried to recover explorer id but was not yet taken.");
                }
                throw new TotalUnitLimitExceededException("Can't add new explorer, total unit limit has been reached.");
            }

            // Create new colonist
            try {
                return (Explorer) this.unitFactory.createUnit(EntitySubtypeEnum.EXPLORER, newExplorerId, newExplorerGlobalId, location);
            } catch (UnitTypeDoesNotExistException e) {
                throw new RuntimeException(e.getLocalizedMessage());
            }
    }

    void removeExplorer(EntityId entityId) throws UnitDoesNotExistException {
        try {
            this.explorerIdManager.removeId(entityId.getInstanceId());
            this.allUnitIdManager.removeId(entityId.getGlobalTypeId());
        } catch (IdDoesNotExistException e) {
            throw new UnitDoesNotExistException("Explorer " + entityId.getInstanceId() + " does not exist as an id.");
        }
    }

    Melee createMelee(Location location)
            throws UnitTypeLimitExceededException, TotalUnitLimitExceededException {

        Integer newMeleeId;         // New id
        Integer newMeleeGlobalId;   // New global id

        // Get new explorer id, assign to unit
        try {
            newMeleeId = this.meleeIdManager.getNewId();
        } catch (IdLimitExceededException e) {
            throw new UnitTypeLimitExceededException("Melee limit is reached, can't add new melee.");
        }

        // Check if we have exceeded total unit count
        try {
            newMeleeGlobalId = this.allUnitIdManager.getNewId();
        } catch (IdLimitExceededException e) {
            try {
                this.meleeIdManager.removeId(newMeleeId);
            } catch (IdDoesNotExistException dne) {
                log.warn("Tried to recover melee id but was not yet taken.");
            }
            throw new TotalUnitLimitExceededException("Can't add new melee, total unit limit has been reached.");
        }

        // Create new colonist
        try {
            return (Melee) this.unitFactory.createUnit(EntitySubtypeEnum.MELEE, newMeleeId, newMeleeGlobalId, location);
        } catch (UnitTypeDoesNotExistException e) {
            throw new RuntimeException(e.getLocalizedMessage());
        }
    }

    void removeMelee(EntityId entityId) throws UnitDoesNotExistException {
        try {
            this.meleeIdManager.removeId(entityId.getInstanceId());
            this.allUnitIdManager.removeId(entityId.getGlobalTypeId());
        } catch (IdDoesNotExistException e) {
            throw new UnitDoesNotExistException("Melee " + entityId.getInstanceId() + " does not exist as an id.");
        }
    }

    Ranged createRanged(Location location)
            throws UnitTypeLimitExceededException, TotalUnitLimitExceededException {

        Integer newRangedId;            // New id
        Integer newRangedGlobalId;      // New global id

        // Get new explorer id, assign to unit
        try {
            newRangedId = this.rangedIdManager.getNewId();
        } catch (IdLimitExceededException e) {
            throw new UnitTypeLimitExceededException("Ranged limit is reached, can't add new ranged.");
        }

        // Check if we have exceeded total unit count
        try {
            newRangedGlobalId = this.allUnitIdManager.getNewId();
        } catch (IdLimitExceededException e) {
            try {
                this.rangedIdManager.removeId(newRangedId);
            } catch (IdDoesNotExistException dne) {
                log.warn("Tried to recover ranged id but was not yet taken.");
            }
            throw new TotalUnitLimitExceededException("Can't add new ranged, total unit limit has been reached.");
        }

        // Create new colonist
        try {
            return (Ranged) this.unitFactory.createUnit(EntitySubtypeEnum.RANGE, newRangedId, newRangedGlobalId, location);
        } catch (UnitTypeDoesNotExistException e) {
            throw new RuntimeException(e.getLocalizedMessage());
        }
    }

    void removeRanged(EntityId entityId) throws UnitDoesNotExistException {
        try {
            this.rangedIdManager.removeId(entityId.getInstanceId());
            this.allUnitIdManager.removeId(entityId.getGlobalTypeId());
        } catch (IdDoesNotExistException e) {
            throw new UnitDoesNotExistException("Ranged " + entityId.getInstanceId() + " does not exist as an id.");
        }
    }

}
