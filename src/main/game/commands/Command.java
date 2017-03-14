package game.commands;


// Command abstract
public abstract class Command {
    protected int duration;                                 // Duration of command until execution
    public abstract void exec();                            // Basic execute signature for commands
    public int getDuration() { return duration; }           // Get time until activation
    public void iterateDuration() { this.duration--; }      // Iterate activation time
    
    public Command(int duration) {
    	this.duration = duration;
    }
}
