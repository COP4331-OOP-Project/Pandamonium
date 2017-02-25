package game.commands;

import game.Player;
import game.entities.iAttacker;
import game.entities.iDefender;
import game.entities.iEntity;
import game.entities.iHealer;
import game.gameboard.Tile;

// Class to handle creation and assignment of commands
public class CommandDispatcher {

    Player player;      // Player

    // Constructor
    CommandDispatcher(Player player) {
        this.player = player;   // Set player
    }

    // Issue an attack command
    public void issueAttackCommand(iAttacker a, Tile target) {
        a.addCommandToQueue(new AttackCommand(a, target, 1));
    }

    // Issue a defend command
    public void issueDefendCommand(iDefender d, int direction) {
        d.addCommandToQueue(new DefendCommand(d, direction, 1));
    }

    // Issue a heal command
    public void issueHealCommand(iHealer h, Tile target) {
        h.addCommandToQueue(new HealCommand(h, target, 1));
    }

    // Issue a make command
    public void issueMakeCommand(iEntity e, Tile target, String entityCode) {
        e.addCommandToQueue(new MakeCommand(e, target, entityCode));
    }

    // Issue a power down command
    public void issuePowerDownCommand(iEntity e) {
        e.addCommandToQueue(new PowerDownCommand(e));
    }

    // Issue a power up command
    public void issuePowerUpCommand(iEntity e) {
        e.addCommandToQueue(new PowerUpCommand(e));
    }

    // Issue a decommission command
    public void issueDecommissionCommand(iEntity e) {
        e.addCommandToQueue(new DecommissionCommand(e));
    }

    // Issue a cancel queue command
    public void issueCancelQueueCommand(iEntity e) {
        e.cancelQueuedCommands();
    }

}
