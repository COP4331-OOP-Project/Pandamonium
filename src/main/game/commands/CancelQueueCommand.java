package game.commands;
import game.entities.iEntity;

// Clear the queued commands of the actor
public class CancelQueueCommand extends Command {

    private iEntity actor;

    public CancelQueueCommand(iEntity actor) {
        this.actor = actor;     // Set actor
        super.duration = 0;     // No duration
    }

    // Execute command
    public void exec() {}

}
