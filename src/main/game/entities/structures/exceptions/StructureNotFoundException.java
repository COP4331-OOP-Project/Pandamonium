package game.entities.structures.exceptions;

/**
 * Created by khariollivierre on 3/1/17.
 */
public class StructureNotFoundException extends Exception {
    public StructureNotFoundException(){ super("Structure type not found.\n"); }
}