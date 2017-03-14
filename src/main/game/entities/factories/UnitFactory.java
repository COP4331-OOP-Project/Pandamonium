package game.entities.factories;

import game.Player;
import game.entities.DeathNotifier;
import game.entities.EntityId;
import game.entities.EntitySubtypeEnum;
import game.entities.EntityTypeEnum;
import game.entities.factories.exceptions.UnitTypeDoesNotExistException;
import game.entities.managers.PlacementManager;
import game.entities.stats.UnitStats;
import game.entities.units.*;
import game.entities.units.exceptions.UnitNotFoundException;
import game.gameboard.Gameboard;
import game.gameboard.Location;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class UnitFactory {

    private final static Logger log = LogManager.getLogger(UnitFactory.class);

    private Player player;
    private Map<EntitySubtypeEnum, UnitStats> unitStatistics;
    private PlacementManager placementManager;
    
    public UnitFactory(Player player, PlacementManager placementManager) {

        this.player = player;
        this.unitStatistics = new HashMap<>();
        this.placementManager = placementManager;

        try {
            this.unitStatistics.put(EntitySubtypeEnum.COLONIST, new UnitStats(EntitySubtypeEnum.COLONIST));
            this.unitStatistics.put(EntitySubtypeEnum.EXPLORER, new UnitStats(EntitySubtypeEnum.EXPLORER));
            this.unitStatistics.put(EntitySubtypeEnum.MELEE, new UnitStats(EntitySubtypeEnum.MELEE));
            this.unitStatistics.put(EntitySubtypeEnum.RANGE, new UnitStats(EntitySubtypeEnum.RANGE));
        }   catch(UnitNotFoundException e){
            throw new RuntimeException("Unit factory could not be instantiated: " + e.getLocalizedMessage());
        }
    }
  
    public Unit createUnit(EntitySubtypeEnum unitType, int id, int globalId, Location location)
            throws UnitTypeDoesNotExistException {

        switch(unitType) {
            case COLONIST: {
                EntityId entityId = new EntityId(player.getPlayerId(), EntityTypeEnum.UNIT, EntitySubtypeEnum.COLONIST, id, globalId);
                DeathNotifier notifier = new DeathNotifier(player);
                return new Colonist(unitStatistics.get(unitType), location, entityId, placementManager, notifier);
            }
            case EXPLORER: {
                EntityId entityId = new EntityId(player.getPlayerId(), EntityTypeEnum.UNIT, EntitySubtypeEnum.EXPLORER, id, globalId);
                DeathNotifier notifier = new DeathNotifier(player);
                return new Explorer(unitStatistics.get(unitType),location, entityId, placementManager, notifier);
            }
            case MELEE: {
                EntityId entityId = new EntityId(player.getPlayerId(), EntityTypeEnum.UNIT, EntitySubtypeEnum.MELEE, id, globalId);
                DeathNotifier notifier = new DeathNotifier(player);
                return new Melee(unitStatistics.get(unitType), location, entityId, placementManager, notifier);
            }
            case RANGE: {
                EntityId entityId = new EntityId(player.getPlayerId(), EntityTypeEnum.UNIT, EntitySubtypeEnum.RANGE, id, globalId);
                DeathNotifier notifier = new DeathNotifier(player);
                return new Ranged(unitStatistics.get(unitType), location, entityId, placementManager, notifier);
            }
            default: throw new UnitTypeDoesNotExistException();
        }
    }
}
