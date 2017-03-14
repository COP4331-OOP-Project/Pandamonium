package game.entities;

import game.entities.workers.workerTypes.WorkerTypeEnum;

// Communicate death of entity to entity's player
public interface iDeathNotificationMediator {
    void publishEntityDeath(EntityTypeEnum type, EntitySubtypeEnum subtype, EntityId id);
}
