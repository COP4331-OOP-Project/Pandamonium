package game.entities.units;

/**
 * Created by khariollivierre on 2/25/17.
 */
public class UnitNotFoundException extends Throwable {
    public UnitNotFoundException(){
        super("Unit type not found.\n");
    }
}
