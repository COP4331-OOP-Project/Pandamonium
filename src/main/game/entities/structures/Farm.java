package game.entities.structures;

import game.entities.DeathNotifier;
import game.commands.CommandEnum;
import game.entities.EntityId;
import game.entities.managers.PlacementManager;
import game.entities.managers.WorkerManager;
import game.entities.managers.exceptions.WorkerDoesNotExistException;
import game.entities.managers.exceptions.WorkerLimitExceededException;
import game.entities.managers.exceptions.WorkerTypeDoesNotExist;
import game.entities.stats.StructureStats;
import game.entities.workers.workerTypes.FoodGatherer;
import game.entities.workers.workerTypes.Worker;
import game.entities.workers.workerTypes.WorkerTypeEnum;
import game.gameboard.Location;
import game.resources.Resource;
import game.resources.ResourceTypeEnum;
import game.visitors.TransferWorkerVisitor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class Farm extends Structure {
    private ArrayList<Worker> unassigned;
    private ArrayList<FoodGatherer> farmer;
    private WorkerManager workerManager;

    public Farm(StructureStats stats, Location location , EntityId entityId , PlacementManager placementManager, WorkerManager workerManager, DeathNotifier notifier){
        super(stats, location, entityId, placementManager, notifier);
        unassigned=new ArrayList<>();
        farmer=new ArrayList<>();
        this.workerManager=workerManager;
        addCommand(CommandEnum.ASSIGN_WORKER);
        addCommand(CommandEnum.UNASSIGN_ALL_WORKERS);
        addCommand(CommandEnum.WORKER_FARM);
    }

    public void assignToFarmer(Location location)throws WorkerLimitExceededException, WorkerTypeDoesNotExist, WorkerDoesNotExistException {
        if(!unassigned.isEmpty()){
            Iterator<Worker> iterator = unassigned.iterator();
            while(iterator.hasNext()){
                Worker holder = iterator.next();
                iterator.remove();
                TransferWorkerVisitor transferWorkerVisitor = new TransferWorkerVisitor(holder.getId(), WorkerTypeEnum.FOOD_GATHERER, location);
                farmer.add((FoodGatherer) workerManager.accept(transferWorkerVisitor));
                return;
            }
        }
    }

    public void unassignFarmer()throws WorkerLimitExceededException, WorkerTypeDoesNotExist, WorkerDoesNotExistException{
        if(!farmer.isEmpty()){
            Iterator<FoodGatherer> iterator = farmer.iterator();
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
        return unassigned.size() + farmer.size();
    }

    public int getUnassignedWorkers(){
        return unassigned.size();
    }

    public int getBusyWorkers(){
        return farmer.size();
    }

    public Resource harvest(){
        Iterator<FoodGatherer> iterator = farmer.iterator();
        Resource resource = new Resource(0, ResourceTypeEnum.FOOD);
        while(iterator.hasNext()){
            resource.combine(iterator.next().doProduction());
        }
        return resource;
    }

    public void onTurnEnded() {

    }
}
