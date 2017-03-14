package game.entities.structures;

import game.entities.DeathNotifier;
import game.commands.CommandEnum;
import game.entities.EntityId;
import game.entities.EntitySubtypeEnum;
import game.entities.PowerState;
import game.entities.factories.exceptions.TotalUnitLimitExceededException;
import game.entities.factories.exceptions.UnitTypeDoesNotExistException;
import game.entities.factories.exceptions.UnitTypeLimitExceededException;
import game.entities.managers.PlacementManager;
import game.entities.managers.UnitManager;
import game.entities.managers.WorkerManager;
import game.entities.managers.exceptions.WorkerDoesNotExistException;
import game.entities.managers.exceptions.WorkerLimitExceededException;
import game.entities.managers.exceptions.WorkerTypeDoesNotExist;
import game.entities.stats.StructureStats;
import game.entities.workers.workerTypes.SoldierGenerator;
import game.entities.workers.workerTypes.Worker;
import game.entities.workers.workerTypes.WorkerTypeEnum;
import game.gameboard.Location;
import game.visitors.TransferWorkerVisitor;

import java.util.ArrayList;
import java.util.Iterator;

public class Fort extends StructureWithWorker {
    private ArrayList<Worker> unassigned;
    private ArrayList<SoldierGenerator> unitBuilder;
    private UnitManager unitManager;

    public Fort(StructureStats stats, Location location , EntityId entityId , PlacementManager placementManager, WorkerManager workerManager, UnitManager unitManager, DeathNotifier notifier){
        super(stats, location, entityId, placementManager, workerManager, notifier);
        unassigned=new ArrayList<>();
        unitBuilder=new ArrayList<>();
        addCommand(CommandEnum.ATTACK);
        addCommand(CommandEnum.ASSIGN_WORKER);
        addCommand(CommandEnum.UNASSIGN_ALL_WORKERS);
        addCommand(CommandEnum.CREATE_SOLDIERS);
        this.unitManager=unitManager;
    }

    public int getTotalWorkers(){
        return unassigned.size() + unitBuilder.size();
    }

    public int getUnassignedWorkers(){
        return unassigned.size();
    }

    public int getBusyWorkers(){
        return unitBuilder.size();
    }

    public void combatState(){
        setPowerState(PowerState.COMBAT);
    }

    public void buildUnit(EntitySubtypeEnum type)throws UnitTypeLimitExceededException, TotalUnitLimitExceededException, UnitTypeDoesNotExistException {
        if(type==EntitySubtypeEnum.COLONIST||type==EntitySubtypeEnum.EXPLORER||type==EntitySubtypeEnum.MELEE||type==EntitySubtypeEnum.RANGE){
            unitManager.addUnit(type, this.location);
        }
    }

    public void onTurnEnded() {

    }

}
