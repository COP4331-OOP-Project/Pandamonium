package game.entities.managers.IdManager;

import java.util.TreeSet;

import game.entities.managers.IdManager.exceptions.IdDoesNotExistException;
import game.entities.managers.IdManager.exceptions.IdLimitExceededException;

public class IdManager {

    private TreeSet<Integer> usedIds;
    private TreeSet<Integer> freeIds;

    public IdManager(int minId, int maxId) {
        if (maxId <= minId) throw new IllegalArgumentException("MaxId must be larger than MinId");
        this.usedIds = new TreeSet<>();
        this.freeIds = new TreeSet<>();
        for (int i = minId; i <= maxId; i++) {
            this.freeIds.add(i);
        }
    }

    public Integer getNewId() throws IdLimitExceededException {
        Integer newId = this.freeIds.pollFirst();
        if (newId == null) throw new IdLimitExceededException("There are no remaining Ids.");
        this.usedIds.add(newId);
        return newId;
    }

    public void removeId(Integer id) throws IdDoesNotExistException {
        if (!this.usedIds.contains(id)) throw new IdDoesNotExistException("Id " + id + " does not exist, so it cannot be removed.");
        this.usedIds.remove(id);
        this.freeIds.add(id);
    }

    public Integer getTotalUsedIds() {
        return this.usedIds.size();
    }

    public boolean isValidId(Integer id) {
        return this.usedIds.contains(id);
    }

}
