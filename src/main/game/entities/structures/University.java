package game.entities.structures;

import game.entities.DeathNotifier;
import game.entities.EntityId;
import game.entities.managers.PlacementManager;
import game.entities.managers.WorkerManager;
import game.entities.stats.StructureStats;
import game.entities.workers.workerTypes.*;
import game.entityTypeResearch.EntityTypeAdvancementNodeOwnership;
import game.entityTypeResearch.UniversityAlreadyDoingResearchException;
import game.entityTypeResearch.nodeTypes.EntityTypeAdvancementNode;
import game.entities.workers.workerTypes.ResearchGenerator;
import game.gameboard.Location;
import game.techTree.TechTreeNodeOwnership;
import game.techTree.nodeTypes.TechTreeNode;

import java.util.ArrayList;

public class University extends StructureWithWorker {

    private ArrayList<ResearchGenerator> researcher;
    private TechTreeNodeOwnership techTreeNodeOwnership;
    private EntityTypeAdvancementNodeOwnership entityTypeAdvancementNodeOwnership;

    public University(StructureStats stats, Location location , EntityId entityId , PlacementManager placementManager, WorkerManager workerManager, DeathNotifier notifier){
        super(stats, location, entityId, placementManager, workerManager, notifier);
    }

    public void research(TechTreeNode techTreeNode) throws UniversityAlreadyDoingResearchException {
        if (this.entityTypeAdvancementNodeOwnership != null) throw new UniversityAlreadyDoingResearchException();
        int researchProductionRate = 0;
        
        for (Worker w : this.busy) {
            if (w.getWorkerType() == WorkerTypeEnum.RESEARCH_GENERATOR) {
                researchProductionRate += w.getProductionRate();
            }
        }
        if (researchProductionRate == 0) return;    // you can't do research with no production capability. Silly you....
        this.techTreeNodeOwnership = new TechTreeNodeOwnership(techTreeNode, researchProductionRate);
    }
    
    public void research(EntityTypeAdvancementNode entityTypeAdvancementNode) throws UniversityAlreadyDoingResearchException {
        if (this.techTreeNodeOwnership != null) throw new UniversityAlreadyDoingResearchException();
        int researchProductionRate = 0;

        for (Worker w : this.busy) {
            if (w.getWorkerType() == WorkerTypeEnum.RESEARCH_GENERATOR) {
                researchProductionRate += w.getProductionRate();
            }
        }
        if (researchProductionRate == 0) return;

        this.entityTypeAdvancementNodeOwnership = new EntityTypeAdvancementNodeOwnership(entityTypeAdvancementNode, researchProductionRate);
    }

    @Override
    public void onTurnEnded() {
        if (this.techTreeNodeOwnership != null) {
            boolean researchCompleted = this.techTreeNodeOwnership.doTurn();
            if (researchCompleted) this.techTreeNodeOwnership = null;
        }

        if (this.entityTypeAdvancementNodeOwnership != null) {
            boolean researchCompleted = this.entityTypeAdvancementNodeOwnership.doTurn();
            if (researchCompleted) this.entityTypeAdvancementNodeOwnership = null;
        }
    }

}
