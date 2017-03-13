package game.entities.managers;

import game.entities.factories.ArmyFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ArmyIdManager {

    // Logger
    private final static Logger log = LogManager.getLogger(ArmyIdManager.class);

    private ArmyFactory armyFactory;

    // Constructor
    public ArmyIdManager(int playerId) {

        this.armyFactory = new ArmyFactory(playerId);

    }


}
