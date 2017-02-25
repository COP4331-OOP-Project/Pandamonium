package game.entities.workers.workerManagement;

import game.entities.workers.workerManagement.exceptions.WorkerDoesNotExistException;
import game.entities.workers.workerManagement.exceptions.WorkerLimitExceededException;
import game.entities.workers.workerTypes.Worker;
import game.entities.workers.workerTypes.WorkerTypeEnum;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class workerManagerTest {

    WorkerIdManager workerIdManager;

    @Before
    public void setUp() {
        this.workerIdManager = new WorkerIdManager(0, 5);
    }

    @Test
    public void constructorTest() throws IllegalArgumentException {
        // check that IllegalArgumentException is not thrown
        new WorkerIdManager(0, 1);
    }

    @Test(expected=IllegalArgumentException.class)
    public void constructorIllegalArgument() throws IllegalArgumentException {
        new WorkerIdManager(1, 0);
    }

    @Test(expected=IllegalArgumentException.class)
    public void constructorIllegalArgumentEqual() throws IllegalArgumentException {
        new WorkerIdManager(5,5);
    }

    @Test
    public void createWorker() {
        try {
            Worker w = this.workerIdManager.createWorker(WorkerTypeEnum.FARMER);
            Assert.assertEquals(w.getId(), 0);
        } catch (WorkerLimitExceededException e) {
            Assert.fail();
        }
    }

    @Test(expected=WorkerLimitExceededException.class)
    public void workerLimitExceeded() throws WorkerLimitExceededException {
        try {
            Worker w1 = this.workerIdManager.createWorker(WorkerTypeEnum.FARMER);
            Worker w2 = this.workerIdManager.createWorker(WorkerTypeEnum.FARMER);
            Worker w3 = this.workerIdManager.createWorker(WorkerTypeEnum.FARMER);
            Worker w4 = this.workerIdManager.createWorker(WorkerTypeEnum.FARMER);
            Worker w5 = this.workerIdManager.createWorker(WorkerTypeEnum.FARMER);
            Worker w6 = this.workerIdManager.createWorker(WorkerTypeEnum.FARMER);

            Assert.assertEquals(w1.getId(), 0);
            Assert.assertEquals(w2.getId(), 1);
            Assert.assertEquals(w3.getId(), 2);
            Assert.assertEquals(w4.getId(), 3);
            Assert.assertEquals(w5.getId(), 4);
            Assert.assertEquals(w6.getId(), 5);

        } catch(WorkerLimitExceededException e) {
            Assert.fail();
        }

        this.workerIdManager.createWorker(WorkerTypeEnum.FARMER);
        Assert.fail();
    }

    @Test
    public void removeWorker() {
        Worker w1 = null;
        Worker w2 = null;
        try {
            w1 = this.workerIdManager.createWorker(WorkerTypeEnum.FARMER);
            w2 = this.workerIdManager.createWorker(WorkerTypeEnum.FARMER);

        } catch (WorkerLimitExceededException e) {
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
            Worker w3 = this.workerIdManager.createWorker(WorkerTypeEnum.FARMER);
            Assert.assertEquals(w3.getId(), 0);
        } catch (WorkerLimitExceededException e) {
            Assert.fail();
        }
    }
}
