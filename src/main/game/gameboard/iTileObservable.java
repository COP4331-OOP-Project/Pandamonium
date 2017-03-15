package game.gameboard;

import game.entities.ObserverNotFoundException;

public interface iTileObservable {
    void attach(iTileObserver o);
    void detach(iTileObserver o) throws ObserverNotFoundException;
}
