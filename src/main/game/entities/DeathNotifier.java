package game.entities;

import game.Player;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// Handle commmunication to player about death of one of its entities
public class DeathNotifier implements iDeathNotificationMediator {

    // Logger
    private final static Logger log = LogManager.getLogger(DeathNotifier.class);
    private Player player;  // Player to notify of entity death

    // Constructor
    public DeathNotifier(Player player) {
        this.player = player;
    }

    // Notify player of entity death
    public void publishEntityDeath(EntityTypeEnum type, EntitySubtypeEnum subtype, EntityId id) {

        // Remove entity from player
        try {
            player.removeEntity(type, subtype, id);
        } catch(Exception e) {
                log.error(e.getMessage());
        }

    }

}
