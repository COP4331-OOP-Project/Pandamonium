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
import game.entities.workers.workerTypes.*;
import game.gameboard.Location;
import game.visitors.TransferWorkerVisitor;

import java.util.ArrayList;
import java.util.Iterator;

public class University extends Structure {
    private ArrayList<Worker> unassigned;
    private ArrayList<ResearchGenerator> researcher;
    private WorkerManager workerManager;

    public University(StructureStats stats, Location location , EntityId entityId , PlacementManager placementManager, WorkerManager workerManager, DeathNotifier notifier){
        super(stats, location, entityId, placementManager, notifier);
        unassigned=new ArrayList<>();
        researcher=new ArrayList<>();
        this.workerManager=workerManager;
        addCommand(CommandEnum.ASSIGN_WORKER);
        addCommand(CommandEnum.UNASSIGN_ALL_WORKERS);
    }

    public void assignToResearcher(Location location)throws WorkerLimitExceededException, WorkerTypeDoesNotExist, WorkerDoesNotExistException {
        if(!unassigned.isEmpty()){
            Iterator<Worker> iterator = unassigned.iterator();
            while(iterator.hasNext()){
                Worker holder = iterator.next();
                iterator.remove();
                TransferWorkerVisitor transferWorkerVisitor = new TransferWorkerVisitor(holder.getId(), WorkerTypeEnum.RESEARCH_GENERATOR, location);
                researcher.add((ResearchGenerator) workerManager.accept(transferWorkerVisitor));
                return;
            }
        }
    }

    public void unassignResearcher()throws WorkerLimitExceededException, WorkerTypeDoesNotExist, WorkerDoesNotExistException{
        if(!researcher.isEmpty()){
            Iterator<ResearchGenerator> iterator = researcher.iterator();
            while(iterator.hasNext()){
                Worker holder = iterator.next();
                iterator.remove();
                TransferWorkerVisitor transferWorkerVisitor = new TransferWorkerVisitor(holder.getId(), holder.getWorkerType(), this.location);
                unassigned.add(workerManager.accept(transferWorkerVisitor));
                return;
            }
        }
    }

    public void addWorker(ResearchGenerator worker){

    }

    public void removeWorker(){

    }

    public int getTotalWorkers(){
        return unassigned.size() + researcher.size();
    }

    public int getUnassignedWorkers(){
        return unassigned.size();
    }

    public int getBusyWorkers(){
        return researcher.size();
    }

    public void research(){

    }

    public void onTurnEnded() {

    }
}
