package game.entities.units.exceptions;


public class UnitNotFoundException extends Exception {
    public UnitNotFoundException(){ super("Unit type not found.\n"); }
}
