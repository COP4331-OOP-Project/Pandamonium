package game.entities.Player;

import game.ResearchObservable;
import game.entities.workers.workerManagement.exceptions.WorkerLimitExceededException;
import game.entities.workers.workerTypes.WorkerTypeEnum;
import game.gameboard.Location;

public class Player{

    private WorkerManager workerManager;
    private ResearchObservable researchObservable;
    private int playerId;

    public Player(int playerId) {
        this.workerManager = new WorkerManager(playerId);
        Location location = new Location(1, 1);

        try {
            this.workerManager.addWorker(WorkerTypeEnum.FOOD_GATHERER, location);
        } catch (WorkerLimitExceededException e) {
            System.out.println("Fuck");
        }

        this.researchObservable = new ResearchObservable();
        this.researchObservable.attach(this.workerManager);
    }


}
