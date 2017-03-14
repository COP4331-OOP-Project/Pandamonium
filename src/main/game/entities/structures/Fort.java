package game.entities.structures;

import game.commands.CommandEnum;
import game.entities.*;
import game.entities.factories.exceptions.TotalUnitLimitExceededException;
import game.entities.factories.exceptions.UnitTypeDoesNotExistException;
import game.entities.factories.exceptions.UnitTypeLimitExceededException;
import game.entities.managers.PlacementManager;
import game.entities.managers.UnitManager;
import game.entities.managers.WorkerManager;
import game.entities.stats.StructureStats;
import game.gameboard.Location;

public class Fort extends StructureWithWorker implements iAttacker, iDefender {

    private UnitManager unitManager;

    public Fort(StructureStats stats, Location location , EntityId entityId , PlacementManager placementManager, WorkerManager workerManager, UnitManager unitManager, DeathNotifier notifier){
        super(stats, location, entityId, placementManager, workerManager, notifier);
        addCommand(CommandEnum.ATTACK);
        addCommand(CommandEnum.ASSIGN_WORKER);
        addCommand(CommandEnum.UNASSIGN_ALL_WORKERS);
        addCommand(CommandEnum.CREATE_SOLDIERS);
        this.unitManager=unitManager;
    }

    public void combatState(){
        setPowerState(PowerState.COMBAT);
    }

    public void buildUnit(EntitySubtypeEnum type)throws UnitTypeLimitExceededException, TotalUnitLimitExceededException, UnitTypeDoesNotExistException {
        if(type==EntitySubtypeEnum.COLONIST||type==EntitySubtypeEnum.EXPLORER||type==EntitySubtypeEnum.MELEE||type==EntitySubtypeEnum.RANGE){
            unitManager.addUnit(type, this.location);
        }
    }

    @Override
    public double getDamage() {
        return this.stats.getAttackStrength();
    }

    @Override
    public int getRange() {
        return 2;
    }

}
