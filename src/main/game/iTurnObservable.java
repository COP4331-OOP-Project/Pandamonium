package game;

public interface iTurnObservable {

    void attach(iTurnObserver observer);
    void endTurn();
}
