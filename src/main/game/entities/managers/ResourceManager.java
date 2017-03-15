package game.entities.managers;

import game.entities.workers.workerTypes.Worker;
import game.entities.workers.workerTypes.WorkerTypeEnum;
import game.gameboard.Gameboard;
import game.resources.Resource;
import game.resources.ResourceTypeEnum;
import game.visitors.iResourceHarvestVisitor;

public class ResourceManager {
    private Gameboard gameboard;

    public ResourceManager(Gameboard gb){
        gameboard=gb;
    }

    public Resource accept(iResourceHarvestVisitor v){
        return v.visitResourceManager(this);
    }

    public Resource harvest(Worker worker){
        if(worker.getWorkerType()==WorkerTypeEnum.FOOD_GATHERER){
            Resource resource = gameboard.getTiles()[worker.getLocation().getX()][worker.getLocation().getY()].getResource(ResourceTypeEnum.FOOD);
            Resource returnResource =resource.possibleAmountRetrieve(worker.getProductionRate());
            resource.decreaseAmountByValue(returnResource.getAmount());
            return returnResource;
        }
        else if(worker.getWorkerType()==WorkerTypeEnum.PEAT_GATHERER){
            Resource resource = gameboard.getTiles()[worker.getLocation().getX()][worker.getLocation().getY()].getResource(ResourceTypeEnum.PEAT);
            Resource returnResource =resource.possibleAmountRetrieve(worker.getProductionRate());
            resource.decreaseAmountByValue(returnResource.getAmount());
            return returnResource;
        }
        else if(worker.getWorkerType()==WorkerTypeEnum.ORE_GATHERER){
            Resource resource = gameboard.getTiles()[worker.getLocation().getX()][worker.getLocation().getY()].getResource(ResourceTypeEnum.ORE);
            Resource returnResource =resource.possibleAmountRetrieve(worker.getProductionRate());
            resource.decreaseAmountByValue(returnResource.getAmount());
            return returnResource;
        }
        return new Resource(0, ResourceTypeEnum.FOOD);
    }
}
