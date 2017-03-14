package game.commands;

import game.entities.Entity;
import game.entities.iAttacker;
import game.entities.iDefender;
import game.entities.iHealer;
import game.entities.units.Colonist;
import game.gameboard.Location;
import game.gameboard.Tile;

// Class to handle creation and assignment of commands
public class CommandDispatcher {

    // Constructor
    CommandDispatcher() {
    }

    // Issue an attack command
    public void issueAttackCommand(iAttacker a, Tile target) {
        Entity e = (Entity)a;
        e.addCommandToQueue(new AttackCommand(a, target, 1));
    }

    // Issue a defend command
    public void issueDefendCommand(iDefender d, int direction) {
        Entity e = (Entity)d;
        e.addCommandToQueue(new DefendCommand(d, direction, 1));
    }

    // Issue a heal command
    public void issueHealCommand(iHealer h, Tile target) {
        Entity e = (Entity)h;
        e.addCommandToQueue(new HealCommand(h, target, 1));
    }

    // Issue a make command
    public void issueMakeCommand(Entity e, Tile target, String entityCode) {
       // e.addCommandToQueue(new MakeCommand(e, target, entityCode));
    }

    // Issue a power down command
    public void issuePowerDownCommand(Entity e) {
        e.addCommandToQueue(new PowerDownCommand(e));
    }

    // Issue a power up command
    public void issuePowerUpCommand(Entity e) {
        e.addCommandToQueue(new PowerUpCommand(e));
    }

    // Issue a decommission command
    public void issueDecommissionCommand(Entity e) {
        e.addCommandToQueue(new DecommissionCommand(e));
    }

    // Issue a cancel queue command
    public void issueCancelQueueCommand(Entity e) {
        e.cancelQueuedCommands();
    }

	public void issueFoundCapitolCommand(Colonist c) {
		
	}

}
