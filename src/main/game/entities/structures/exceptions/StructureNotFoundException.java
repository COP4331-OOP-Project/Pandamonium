package game.entities.structures.exceptions;

public class StructureNotFoundException extends Exception {

    public StructureNotFoundException(String message) {
        super(message);
    }

    public StructureNotFoundException(){ super(); }
}
