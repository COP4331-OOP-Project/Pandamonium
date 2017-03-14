package game;


import game.entities.managers.exceptions.WorkerTypeDoesNotExist;
import game.entities.workers.workerTypes.WorkerTypeEnum;
import game.semantics.Percentage;
import game.research.workerResearch.iWorkerResearchObservable;
import game.research.workerResearch.iWorkerResearchObserver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class ResearchObservable implements iWorkerResearchObservable {

    private final static Logger log = LogManager.getLogger(ResearchObservable.class);

    private ArrayList<iWorkerResearchObserver> observers;

    public ResearchObservable() {
        this.observers = new ArrayList<>();
    }

    public void attach(iWorkerResearchObserver observer) {
        this.observers.add(observer);
    }

    public void increaseProductionRateByPercentage(Percentage increasePercentage, WorkerTypeEnum workerType) {
        for (iWorkerResearchObserver observer : this.observers) {
            try {
                observer.onProductionRateIncreased(increasePercentage, workerType);
            } catch (WorkerTypeDoesNotExist e) {
                log.error("Could not increase production rate because worker type " + workerType + " does not exist.");
            }
        }
    }

    public void changeProductionRateByAmount(int changeAmount, WorkerTypeEnum workerType) {
        for (iWorkerResearchObserver observer : this.observers) {
            try {
                observer.onChangeProductionRateByAmount(changeAmount, workerType);
            } catch (WorkerTypeDoesNotExist e) {
                log.error("Could not increase production rate because worker type " + workerType + " does not exist.");
            }
        }
    }
}
