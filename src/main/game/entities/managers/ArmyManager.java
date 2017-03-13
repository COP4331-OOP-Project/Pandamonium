package game.entities.managers;

import game.entities.units.Unit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class ArmyManager {

    private final static Logger log = LogManager.getLogger(ArmyManager.class);

    private ArrayList<Unit> battlegroup;
    private ArrayList<Unit> reinforcements;

    private ArmyIdManager armyIdManager;

    // Constructor
    public ArmyManager(int playerId) {}

}
