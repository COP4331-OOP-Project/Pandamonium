package game.commands;
import game.entities.Entity;

// Clear the queued commands of the actor
// Is this needed?
public class CancelQueueCommand extends Command {
	private Entity actor;

	public CancelQueueCommand(Entity actor) {
		super(0); // No duration
		this.actor = actor; // Set actor
	}

	// Execute command
	public void exec() {
	}

}
