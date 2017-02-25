package game.commands;

// Exception for unknown command type
public class UnknownCommandTypeException extends Exception {

    // Constructor
    public UnknownCommandTypeException() {
    }

    // Constructor w/ Message
    public UnknownCommandTypeException(String msg) {
        super(msg);
    }

}
