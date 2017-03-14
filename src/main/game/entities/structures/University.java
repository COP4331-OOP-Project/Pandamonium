package game.entities.structures;

import game.entities.DeathNotifier;
import game.entities.EntityId;
import game.entities.managers.PlacementManager;
import game.entities.managers.WorkerManager;
import game.entities.stats.StructureStats;
import game.entities.workers.workerTypes.*;
import game.gameboard.Location;
import game.techTree.TechTreeNodeOwnership;
import game.techTree.nodeTypes.TechTreeNode;

import java.util.ArrayList;

public class University extends StructureWithWorker {

    private ArrayList<Worker> unassigned;
    private ArrayList<ResearchGenerator> researcher;
    private WorkerManager workerManager;
    private TechTreeNodeOwnership techTreeNodeOwnership;

    public University(StructureStats stats, Location location , EntityId entityId , PlacementManager placementManager, WorkerManager workerManager, DeathNotifier notifier){
        super(stats, location, entityId, placementManager, workerManager, notifier);
    }

    public void research(TechTreeNode techTreeNode){
        int researchProductionRate = 0;
        for (ResearchGenerator generator : this.researcher) {
            researchProductionRate += generator.getProductionRate();
        }

        this.techTreeNodeOwnership = new TechTreeNodeOwnership(techTreeNode, researchProductionRate);
    }

    public void onTurnEnded() {
        if (this.techTreeNodeOwnership != null) {
            boolean researchCompleted = this.techTreeNodeOwnership.doTurn();
            if (researchCompleted) this.techTreeNodeOwnership = null;
        }
    }
}
