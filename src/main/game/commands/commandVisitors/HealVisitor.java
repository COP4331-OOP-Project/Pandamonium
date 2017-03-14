package game.commands.commandVisitors;

import game.entities.Entity;

// Visitor to perform a heal on an entity
public class HealVisitor implements iTileActionVisitor {

    private double healAmount;    // Healing to visit entity with

    // Construct heal visitor with given heal value of the actor
    public HealVisitor(double healAmount) {
        this.healAmount = healAmount;
    }

    // Visit entity and heal based on healAmount
    public void visitEntity(Entity e) { e.heal(healAmount); }

}
