package game.entities;

import game.gameboard.Location;

// Communicate death of entity to entity's player
public interface iDeathNotificationMediator {
    void publishEntityDeath(EntityTypeEnum type, EntitySubtypeEnum subtype, EntityId id, Location location);
}
