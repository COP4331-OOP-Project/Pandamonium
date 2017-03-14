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

public class Fort extends Structure {
    private ArrayList<Worker> unassigned;
    private ArrayList<SoldierGenerator> unitBuilder;
    private WorkerManager workerManager;
    private UnitManager unitManager;

    public Fort(StructureStats stats, Location location , EntityId entityId , PlacementManager placementManager, WorkerManager workerManager, UnitManager unitManager, DeathNotifier notifier){
        super(stats, location, entityId, placementManager, notifier);
        unassigned=new ArrayList<>();
        unitBuilder=new ArrayList<>();
        this.workerManager=workerManager;
        addCommand(CommandEnum.ATTACK);
        addCommand(CommandEnum.ASSIGN_WORKER);
        addCommand(CommandEnum.UNASSIGN_ALL_WORKERS);
        addCommand(CommandEnum.CREATE_SOLDIERS);
        this.unitManager=unitManager;
    }

    public void assignToUnitBuilder(Location location)throws WorkerLimitExceededException, WorkerTypeDoesNotExist, WorkerDoesNotExistException {
        if(!unassigned.isEmpty()){
            Iterator<Worker> iterator = unassigned.iterator();
            while(iterator.hasNext()){
                Worker holder = iterator.next();
                iterator.remove();
                TransferWorkerVisitor transferWorkerVisitor = new TransferWorkerVisitor(holder.getId(), WorkerTypeEnum.SOLDIER_GENERATOR, location);
                unitBuilder.add((SoldierGenerator) workerManager.accept(transferWorkerVisitor));
                return;
            }
        }
    }

    public void unassignUnitBuilder()throws WorkerLimitExceededException, WorkerTypeDoesNotExist, WorkerDoesNotExistException{
        if(!unitBuilder.isEmpty()){
            Iterator<SoldierGenerator> iterator = unitBuilder.iterator();
            while(iterator.hasNext()){
                Worker holder = iterator.next();
                iterator.remove();
                TransferWorkerVisitor transferWorkerVisitor = new TransferWorkerVisitor(holder.getId(), holder.getWorkerType(), this.location);
                unassigned.add(workerManager.accept(transferWorkerVisitor));
                return;
            }
        }
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
