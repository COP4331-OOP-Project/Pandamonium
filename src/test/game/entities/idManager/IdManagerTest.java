package game.entities.idManager;

import game.entities.Managers.IdManager.exceptions.IdDoesNotExistException;
import game.entities.Managers.IdManager.exceptions.IdLimitExceededException;
import game.entities.Managers.IdManager.IdManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by gavin on 2/23/17.
 */
public class IdManagerTest {

    private int MIN_ID = 0;
    private int MAX_ID = 9;
    private IdManager idManager;

    @Before
    public void setUp() {
        try {
            this.idManager = new IdManager(this.MIN_ID, this.MAX_ID);
        } catch (IllegalArgumentException e) {
            Assert.fail();
        }
    }

    @Test(expected=IllegalArgumentException.class)
    public void invalidIdRangeGreaterThan() throws IllegalArgumentException {
        IdManager idM = new IdManager(2, 1);
    }

    @Test(expected=IllegalArgumentException.class)
    public void invalidIdRangeEquals() throws IllegalArgumentException {
        IdManager idM = new IdManager(5, 5);
    }

    @Test
    public void validIdRange() {
        try {
            IdManager idM = new IdManager(0, 9);
        } catch (IllegalArgumentException e) {
            Assert.fail();
        }
    }

    @Test
    public void getNewIdLimitInRange() {
        for (int i = this.MIN_ID; i <= this.MAX_ID; i++) {
            try {
                this.idManager.getNewId();
            } catch (IdLimitExceededException e) {
                Assert.fail();
            }
        }
    }

    @Test
    public void getNewIdLimitOutOfRange() {
        for (int i = this.MIN_ID; i <= this.MAX_ID + 1; i++) {
            try {
                this.idManager.getNewId();
            } catch (IdLimitExceededException e) {
                return;
            }
        }
        Assert.fail();
    }

    @Test
    public void getNewIdSortedValues() throws IdLimitExceededException {
        for (int i = this.MIN_ID; i <= this.MAX_ID; i++)
            Assert.assertEquals((int)this.idManager.getNewId(), i);
    }

    @Test
    public void totalIdCount() throws IdLimitExceededException {
        this.idManager.getNewId();
        this.idManager.getNewId();
        this.idManager.getNewId();
        this.idManager.getNewId();
        Assert.assertEquals((int)this.idManager.getTotalUsedIds(), 4);
    }

    @Test(expected=IdDoesNotExistException.class)
    public void removeNonexistentId() throws IdDoesNotExistException {
        this.idManager.removeId(4);
    }

    @Test
    public void isValidId() throws IdLimitExceededException {
        this.idManager.getNewId();
        Assert.assertTrue(this.idManager.isValidId(MIN_ID));
    }

    @Test
    public void removeExistingId() throws IdLimitExceededException, IdDoesNotExistException {
        this.idManager.getNewId();
        this.idManager.getNewId();
        this.idManager.getNewId();
        Assert.assertTrue(this.idManager.isValidId(1));
        this.idManager.removeId(1);
        Assert.assertFalse(this.idManager.isValidId(1));
        Assert.assertEquals((int)this.idManager.getTotalUsedIds(), 2);
        Assert.assertTrue(this.idManager.isValidId(0));
        Assert.assertTrue(this.idManager.isValidId(2));
    }

    @Test
    public void getNewFindsFirstOpenSlot() throws IdLimitExceededException, IdDoesNotExistException {
        this.idManager.getNewId();
        this.idManager.getNewId();
        this.idManager.getNewId();
        this.idManager.getNewId();
        this.idManager.getNewId();
        Assert.assertTrue(this.idManager.isValidId(3));
        Assert.assertTrue(this.idManager.isValidId(4));
        this.idManager.removeId(3);
        this.idManager.removeId(4);
        Assert.assertFalse(this.idManager.isValidId(3));
        Assert.assertFalse(this.idManager.isValidId(4));
        Assert.assertEquals((int)this.idManager.getNewId(), 3);
        Assert.assertTrue(this.idManager.isValidId(3));
    }

}
