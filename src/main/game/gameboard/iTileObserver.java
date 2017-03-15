package game.gameboard;

import game.entities.EntityId;

public interface iTileObserver {
    int getInfluence();
    Location getLocation();
    EntityId getEntityId();
}
