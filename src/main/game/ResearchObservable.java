package game;

import game.resources.Resource;

import java.util.ArrayList;

public class ResearchObservable implements WorkerResearchObservable {

    private ArrayList<WorkerResearchObserver> observers;

    public ResearchObservable() {
        this.observers = new ArrayList<>();
    }

    public void attach(WorkerResearchObserver observer) {
        this.observers.add(observer);
    }

    public void changeProductionRate(int productionRate) {
        for (WorkerResearchObserver observer : this.observers) {
            observer.onProductionRateChanged(productionRate);
        }
    }

    public void changeUpkeep(Resource upkeep) {
        for (WorkerResearchObserver observer : this.observers) {
            observer.onUpkeepChanged(upkeep);
        }
    }
}
