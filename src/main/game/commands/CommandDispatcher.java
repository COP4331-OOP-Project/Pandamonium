package game.commands;

import game.entities.*;
import game.entities.managers.ArmyManager;
import game.entities.managers.PlacementManager;
import game.entities.managers.StructureManager;
import game.entities.managers.UnitManager;
import game.entities.units.Colonist;
import game.entities.units.Explorer;
import game.gameboard.Location;
import game.gameboard.Tile;

// Class to handle creation and assignment of commands
public class CommandDispatcher {


    CommandDispatcher() {}

    // Player's managers
    UnitManager unitManager;
    StructureManager structureManager;
    ArmyManager armyManager;
    PlacementManager placementManager;

    // Constructor
    CommandDispatcher(UnitManager unitManager,
                      StructureManager structureManager, ArmyManager armyManager, PlacementManager placementManager) {

        // Set managers
        this.unitManager = unitManager;
        this.structureManager = structureManager;
        this.armyManager = armyManager;
        this.placementManager = placementManager;

    }

    // Issue a found capitol command
    public void issueFoundCapitolCommand(Colonist c, Location location) {
        c.addCommandToQueue(new FoundCapitolCommand(c, location, 1, this.structureManager));
    }

    // Issue an attack command
    public void issueAttackCommand(iAttacker a, Tile target) {
        Entity e = (Entity) a;
        e.addCommandToQueue(new AttackCommand(a, target, 1));
    }

    // Issue a defend command
    public void issueDefendCommand(iDefender d, int direction) {
        Entity e = (Entity) d;
        e.addCommandToQueue(new DefendCommand(d, direction, 1));
    }

    // Issue a heal command
    public void issueHealCommand(iHealer h, Tile target) {
        Entity e = (Entity) h;
        e.addCommandToQueue(new HealCommand(h, target, 1));
    }

    // Issue a make unit command
    public void issueMakeUnitCommand(Entity e, Tile target, EntityTypeEnum type, EntitySubtypeEnum subtype) {
       e.addCommandToQueue(new MakeUnitCommand(e, target, 2, type, subtype, this.unitManager));
    }

    // Issue a make unit command
    public void issueMakeStructureCommand(Entity e, Tile target, EntityTypeEnum type, EntitySubtypeEnum subtype) {
        e.addCommandToQueue(new MakeStructureCommand(e, target, 2, type, subtype, this.structureManager));
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
    
    public void issueStartProspectingCommand(Explorer e) {
    	e.addCommandToQueue(new StartProspectingCommand(e, 1));
    }

    public void issueStopProspectingCommand(Explorer e) {
    	e.addCommandToQueue(new StopProspectingCommand(e, 1));
    }
    
    // Issue a cancel queue command
    public void issueCancelQueueCommand(Entity e) {
        e.cancelQueuedCommands();
    }

}
