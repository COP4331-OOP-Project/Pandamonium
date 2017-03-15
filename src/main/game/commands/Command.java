package game.commands;


// Command abstract
public abstract class Command {
    protected int duration;                                 // Duration of command until execution
    protected abstract void exec();                            // Basic execute signature for commands
    public int getDuration() { return duration; }           // Get time until activation

    // Iterate activation time
    public boolean iterateDuration() {

        // Check if duration is done, then execute
        if (--this.duration <= 0) {
            exec();
            return true;
        }

        return false;

    }
    
    public Command(int duration) {
    	this.duration = duration;
    }
}
