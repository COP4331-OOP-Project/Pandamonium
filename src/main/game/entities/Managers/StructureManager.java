package game.entities.Managers;

import game.entities.Army;
import game.entities.EntitySubtypeEnum;
import game.entities.RallyPoint;
import game.entities.structures.*;
import game.entities.units.*;
import game.entities.workers.workerTypes.Worker;

import java.util.ArrayList;

public class StructureManager {

    private ArrayList<Capitol> capitols;
    private ArrayList<Farm> farms;
    private ArrayList<Fort> forts;
    private ArrayList<Mine> mines;
    private ArrayList<ObservationTower> observationTowers;
    private ArrayList<PowerPlant> powerPlants;
    private ArrayList<Structure> structures;
    private ArrayList<University> universities;

    private StructureIdManager structureIdManager;

    public StructureManager(int playerId) {
        this.structureIdManager = new StructureIdManager(playerId);
    }

    public void addStructure(EntitySubtypeEnum structureType, Location location) {
        switch (structureType) {
            case CAPITOL:

        }
    }









    public void addWorker(WorkerTypeEnum workerType, Location location) throws WorkerLimitExceededException, WorkerTypeDoesNotExist {
        this.workers.add(this.workerIdManager.createWorker(workerType, location));
    }

    public void removeWorker(EntityId id) throws WorkerDoesNotExistException {
        boolean removed = false;
        for (Worker w : this.workers) {
            if (w.getId() == id) {
                this.workers.remove(w);
                removed = true;
            }
        }

        if (!removed) throw new WorkerDoesNotExistException("Worker does not exist in WorkerManager");

        this.workerIdManager.removeWorker(id);
    }

    public Worker getWorker(EntityId id) {
        for (Worker w : this.workers) {
            if (w.getId() == id) {
                return w;
            }
        }

        return null;
    }
}
