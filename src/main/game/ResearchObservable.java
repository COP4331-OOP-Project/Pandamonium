package game;


import game.semantics.Percentage;
import game.workerResearch.iWorkerResearchObservable;
import game.workerResearch.iWorkerResearchObserver;

import java.util.ArrayList;

public class ResearchObservable implements iWorkerResearchObservable {

    private ArrayList<iWorkerResearchObserver> observers;

    public ResearchObservable() {
        this.observers = new ArrayList<>();
    }

    public void attach(iWorkerResearchObserver observer) {
        this.observers.add(observer);
    }

    public void increaseProductionRateByPercentage(Percentage increasePercentage) {
        for (iWorkerResearchObserver observer : this.observers) {
            observer.onProductionRateIncreased(increasePercentage);
        }
    }

    public void decreaseUpkeepByPercentage(Percentage decreasePercentage) {
        for (iWorkerResearchObserver observer : this.observers) {
            observer.onUpkeepDecreased(decreasePercentage);
        }
    }
}
