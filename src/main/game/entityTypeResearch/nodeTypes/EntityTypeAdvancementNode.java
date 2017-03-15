package game.entityTypeResearch.nodeTypes;

import game.entities.factories.EntityTypeDoesNotExistException;

public abstract class EntityTypeAdvancementNode {

    private EntityTypeAdvancementNode parent;
    private EntityTypeAdvancementNode child;
    private String name;
    private String description;

    protected EntityTypeAdvancementNode(String name, String description) {
        this.name = name;
        this.description = description;
    }

    protected EntityTypeAdvancementNode(String name, String description, EntityTypeAdvancementNode parent) {
        this.name = name;
        this.description = description;
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EntityTypeAdvancementNode getParent() {
        return parent;
    }

    public void setParent(EntityTypeAdvancementNode parent) {
        this.parent = parent;
    }

    public EntityTypeAdvancementNode getChild() {
        return child;
    }

    public void setChild(EntityTypeAdvancementNode child) {
        this.child = child;
    }

    public boolean canCompleteResearch() {
        return !this.isResearchCompleted() && this.parent.isResearchCompleted();
    }

    public int getProductionRequirement() {
        return 5;
    }

    public abstract boolean isResearchCompleted();

    public abstract void doResearch() throws EntityTypeDoesNotExistException;
}

