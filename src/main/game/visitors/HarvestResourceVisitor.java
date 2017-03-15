package game.visitors;

import game.entities.managers.ResourceManager;
import game.entities.workers.workerTypes.Worker;
import game.resources.Resource;

public class HarvestResourceVisitor implements iResourceHarvestVisitor {
    private Worker worker;

    public HarvestResourceVisitor(Worker worker){
        this.worker=worker;
    }

    public Resource visitResourceManager(ResourceManager resourceManager){
        return resourceManager.harvest(worker);
    }
}
