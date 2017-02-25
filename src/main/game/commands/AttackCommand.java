package game.commands;

import game.entities.iAttacker;
import game.gameboard.Tile;
import game.visitors.AttackVisitor;

// Attack Command
public class AttackCommand extends Command {

    private iAttacker actor;        // Attacking actor
    private Tile target;            // Target tile

    // Constructor
    public AttackCommand(iAttacker actor, Tile target, int duration) {
        this.actor = actor;                 // Set attacker
        this.target = target;               // Set target
        super.duration = duration;          // Set duration until attack executes
    }

    // Create new attack visitor and send it to tile to perform attack
    public void exec() { target.accept(new AttackVisitor(actor.getDamage())); }

}
