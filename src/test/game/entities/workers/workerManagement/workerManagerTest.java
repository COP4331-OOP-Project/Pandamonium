package game.entities.workers.workerManagement;

import game.entities.EntityId;
import game.entities.factories.WorkerFactory;
import game.entities.managers.WorkerIdManager;
import game.entities.managers.WorkerManager;
import game.entities.managers.exceptions.WorkerDoesNotExistException;
import game.entities.managers.exceptions.WorkerLimitExceededException;
import game.entities.managers.exceptions.WorkerTypeDoesNotExist;
import game.entities.workers.workerTypes.Worker;
import game.entities.workers.workerTypes.WorkerTypeEnum;
import game.gameboard.Location;
import game.semantics.Percentage;
import game.semantics.PercentageOutOfRangeException;
import game.techTree.nodeTypes.WorkerProductionRateResearchNode;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class workerManagerTest {

    WorkerIdManager workerIdManager;
    Location aLocation;

    @Before
    public void setUp() {
        WorkerFactory workerFactory = new WorkerFactory(1);
        this.workerIdManager = new WorkerIdManager(workerFactory);
        this.aLocation = new Location(1,1);
    }

    @Test
    public void createWorker() {
        try {
            Worker w = this.workerIdManager.createWorker(WorkerTypeEnum.FOOD_GATHERER, this.aLocation);
            Assert.assertEquals(w.getId().getInstanceId(), 0);
        } catch (WorkerLimitExceededException | WorkerTypeDoesNotExist e) {
            Assert.fail();
        }
    }
    
    @Test
    public void workerTypeDoesNotExist() {
        
    }

    @Test(expected=WorkerLimitExceededException.class)
    public void workerLimitExceeded() throws WorkerLimitExceededException {
        try {
            for (int i = 0; i <= 99; i++) {
                this.workerIdManager.createWorker(WorkerTypeEnum.FOOD_GATHERER, this.aLocation);
            }

        } catch(WorkerLimitExceededException | WorkerTypeDoesNotExist e) {
            Assert.fail();
        }

        try {
            this.workerIdManager.createWorker(WorkerTypeEnum.FOOD_GATHERER, this.aLocation);
            Assert.fail();
        } catch (WorkerTypeDoesNotExist e) {
            Assert.fail();
        }
    }

    @Test
    public void removeWorker() {
        Worker w1 = null;
        Worker w2 = null;
        try {
            w1 = this.workerIdManager.createWorker(WorkerTypeEnum.FOOD_GATHERER, this.aLocation);
            w2 = this.workerIdManager.createWorker(WorkerTypeEnum.FOOD_GATHERER, this.aLocation);

        } catch (WorkerLimitExceededException | WorkerTypeDoesNotExist e) {
            Assert.fail();
        }

        Assert.assertNotNull(w1);
        Assert.assertNotNull(w2);

        try {
            this.workerIdManager.removeWorker(w1.getId());
        } catch (WorkerDoesNotExistException e) {
            Assert.fail();
        }

        try {
            Worker w3 = this.workerIdManager.createWorker(WorkerTypeEnum.FOOD_GATHERER, this.aLocation);
            Assert.assertEquals(w3.getId().getInstanceId(), 0);
        } catch (WorkerLimitExceededException | WorkerTypeDoesNotExist e) {
            Assert.fail();
        }
    }


    /*
     * TechTree example
     */
//    @Test
//    public void deleteMe() {
//        WorkerManager workerManager = new WorkerManager(1);
//        EntityId entityId;
//
//        try {
//            Worker w = workerManager.addWorker(WorkerTypeEnum.FOOD_GATHERER, new Location(1, 1));
//            entityId = w.getId();
//            Assert.assertEquals(w.getProductionRate(), 1, 0);
//        } catch (WorkerLimitExceededException| WorkerTypeDoesNotExist e) {
//            Assert.fail();
//            return;
//        }
//
//        try {
//            WorkerProductionRateResearchNode node = new WorkerProductionRateResearchNode(workerManager, new Percentage(0.05), "name", "description");
//            node.doResearch();
//            Assert.assertEquals(workerManager.getWorker(entityId).getProductionRate(), 1.05, 0);
//
//        } catch (PercentageOutOfRangeException e) {
//            Assert.fail();
//        }
//
//
//    }
}
