package game;

import java.util.ArrayList;

public class ResearchObservable implements WorkerResearchObservable {

    private ArrayList<WorkerResearchObserver> observers;

    public ResearchObservable() {
        this.observers = new ArrayList<>();
    }

    public void attach(WorkerResearchObserver observer) {
        this.observers.add(observer);
    }

    public void upgradeWorkRadius(int newWorkRadius) {
        for (WorkerResearchObserver observer : this.observers) {
            observer.onWorkRadiusUpgraded(newWorkRadius);
        }
    }

    public void upgradeWorkerDensity(int newWorkerDensity) {
        for (WorkerResearchObserver observer : this.observers) {
            observer.onWorkerDensityUpgraded(newWorkerDensity);
        }
    }
}
