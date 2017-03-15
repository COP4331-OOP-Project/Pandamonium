package game.entities.factories;

import game.entities.*;
import game.entities.managers.PlacementManager;
import game.entities.units.Unit;
import game.gameboard.Gameboard;
import game.gameboard.Location;
import game.gameboard.PathFinding;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class ArmyFactory {

    // Logger
    private final static Logger log = LogManager.getLogger(ArmyFactory.class);

    private int playerId;   // Player Id
    private PlacementManager placementManager;  // Placement Manager
    private PathFinding pathFinding;

    // Constructor
    public ArmyFactory(int playerId, Gameboard gb) {
        this.playerId = playerId;
        this.placementManager = new PlacementManager(gb);
        this.pathFinding = new PathFinding(gb);
    }

    // Create Army Function
    public Army createArmy(ArrayList<Unit> units, int id, Location location) {

        // Create rally point
        EntityId rpId = new EntityId(this.playerId, EntityTypeEnum.RALLYPOINT, null, id, id);
        RallyPoint rp = new RallyPoint(rpId, location);

        // Create army
        EntityId armyId = new EntityId(this.playerId, EntityTypeEnum.ARMY, null, id, id);
        Army newArmy = new Army(armyId, units, rp, placementManager, pathFinding);

        return newArmy; // Return army

    }

}
