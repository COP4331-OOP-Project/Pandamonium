package game;

import java.util.ArrayList;

import game.resources.Resource;

public class ResearchObservable implements iWorkerResearchObservable {

    private ArrayList<iWorkerResearchObserver> observers;

    public ResearchObservable() {
        this.observers = new ArrayList<>();
    }

    public void attach(iWorkerResearchObserver observer) {
        this.observers.add(observer);
    }

    public void changeProductionRate(double productionRate) {
        for (iWorkerResearchObserver observer : this.observers) {
            observer.onProductionRateChanged(productionRate);
        }
    }

    public void changeUpkeep(Resource upkeep) {
        for (iWorkerResearchObserver observer : this.observers) {
            observer.onUpkeepChanged(upkeep);
        }
    }
}
