package game.entities.structures;

import game.commands.CommandEnum;
import game.entities.DeathNotifier;
import game.entities.EntityId;
import game.entities.managers.PlacementManager;
import game.entities.managers.WorkerManager;
import game.entities.managers.exceptions.WorkerDoesNotExistException;
import game.entities.managers.exceptions.WorkerLimitExceededException;
import game.entities.managers.exceptions.WorkerTypeDoesNotExist;
import game.entities.stats.StructureStats;
import game.entities.workers.workerTypes.Worker;
import game.entities.workers.workerTypes.WorkerTypeEnum;
import game.gameboard.Location;
import game.resources.Resource;
import game.resources.ResourceTypeEnum;
import game.visitors.TransferWorkerVisitor;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class StructureWithWorker extends Structure {
    protected ArrayList<Worker> unassigned;
    protected ArrayList<Worker> busy;
    private WorkerManager workerManager;
    public StructureWithWorker(StructureStats stats, Location location , EntityId entityId , PlacementManager placementManager,WorkerManager workerManager, DeathNotifier notifier){
        super(stats, location,entityId,placementManager, notifier);
        unassigned = new ArrayList<>();
        busy = new ArrayList<>();
        this.workerManager = workerManager;
        addCommand(CommandEnum.ASSIGN_WORKER);
        addCommand(CommandEnum.UNASSIGN_ALL_WORKERS);
    }

    public void assignToBusy(WorkerTypeEnum type, Location location)throws WorkerLimitExceededException, WorkerTypeDoesNotExist, WorkerDoesNotExistException {
        if(!unassigned.isEmpty()){
            Iterator<Worker> iterator = unassigned.iterator();
            while(iterator.hasNext()){
                Worker holder = iterator.next();
                iterator.remove();
                TransferWorkerVisitor transferWorkerVisitor = new TransferWorkerVisitor(holder.getId(), type, location);
                busy.add(workerManager.accept(transferWorkerVisitor));
                return;
            }
        }
    }

    public void unassignWorker()throws WorkerLimitExceededException, WorkerTypeDoesNotExist, WorkerDoesNotExistException{
        if(!busy.isEmpty()){
            Iterator<Worker> iterator = busy.iterator();
            while(iterator.hasNext()){
                Worker holder = iterator.next();
                iterator.remove();
                TransferWorkerVisitor transferWorkerVisitor = new TransferWorkerVisitor(holder.getId(), holder.getWorkerType(), this.location);
                unassigned.add(workerManager.accept(transferWorkerVisitor));
                return;
            }
        }
    }

    public Resource harvestNutrients() {
        Resource food = new Resource(0, ResourceTypeEnum.FOOD);
        Iterator<Worker> iterator = busy.iterator();
        while(iterator.hasNext()) {
            Worker holder = iterator.next();
            if(holder.getWorkerType()==WorkerTypeEnum.FOOD_GATHERER){
                food.combine(holder.doProduction());
            }
        }
        return food;
    }

    public Resource harvestPower() {
        Resource power = new Resource(0, ResourceTypeEnum.PEAT);
        Iterator<Worker> iterator = busy.iterator();
        while(iterator.hasNext()) {
            Worker holder = iterator.next();
            if(holder.getWorkerType()==WorkerTypeEnum.PEAT_GATHERER){
                power.combine(holder.doProduction());
            }
        }
        return power;
    }

    public Resource harvestMetal() {
        Resource metal = new Resource(0, ResourceTypeEnum.ORE);
        Iterator<Worker> iterator = busy.iterator();
        while(iterator.hasNext()) {
            Worker holder = iterator.next();
            if(holder.getWorkerType()==WorkerTypeEnum.ORE_GATHERER){
                metal.combine(holder.doProduction());
            }
        }
        return metal;
    }

    public void addWorkers(ArrayList<Worker> workers){
        this.unassigned.addAll(workers);
    }

    public void removeWorker(int i){
        if(i>=unassigned.size()){
            unassigned.clear();
        }
        else{
            Iterator<Worker> iterator = unassigned.iterator();
            int count = 0;
            while(count<i){
                iterator.next();
                iterator.remove();
                count++;
            }
        }
    }
    
    public int getWorkerCount() {
    	return workerManager.getWorkers().size();
    }
}
