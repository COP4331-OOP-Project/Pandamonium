package game.entities.factories.exceptions;

import game.entities.Army;
import game.entities.units.Explorer;

public class ArmyLimitExceededException extends Exception {

    // Default constructor
    public ArmyLimitExceededException() { super(); }

    // Message constructor
    public ArmyLimitExceededException(String msg) { super(msg); }

}
