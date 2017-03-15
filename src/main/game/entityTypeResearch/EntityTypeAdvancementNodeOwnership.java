package game.entityTypeResearch;

import game.entities.factories.EntityTypeDoesNotExistException;
import game.entityTypeResearch.nodeTypes.EntityTypeAdvancementNode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EntityTypeAdvancementNodeOwnership {

    private final static Logger log = LogManager.getLogger(EntityTypeAdvancementNode.class);

    private EntityTypeAdvancementNode entityTypeAdvancementNode;
    private int turnsLeft;

    public EntityTypeAdvancementNodeOwnership(EntityTypeAdvancementNode entityTypeAdvancementNode, int productionRate) {
        this.entityTypeAdvancementNode = entityTypeAdvancementNode;
        this.turnsLeft = this.entityTypeAdvancementNode.getProductionRequirement() / productionRate;
    }

    public boolean doTurn() {
        if (--this.turnsLeft <= 0) {
            try {
                this.entityTypeAdvancementNode.doResearch();
            } catch (EntityTypeDoesNotExistException e) {
                log.error("Cannot do research because entity type advancement node was initialized with an invalid type.");
                return false;
            }
            return false;
        }

        return true;
    }
}
