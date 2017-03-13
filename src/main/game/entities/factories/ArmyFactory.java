package game.entities.factories;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ArmyFactory {

    // Logger
    private final static Logger log = LogManager.getLogger(ArmyFactory.class);

    private int playerId;

    public ArmyFactory(int playerId) {
        this.playerId = playerId;
    }

}
