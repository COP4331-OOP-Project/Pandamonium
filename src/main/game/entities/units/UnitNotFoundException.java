package game.entities.units;


public class UnitNotFoundException extends Exception {
    public UnitNotFoundException(){ super("Unit type not found.\n"); }
}
