package game.visitors;
import game.entities.Entity;

// Visitor to perform an attack on an entity
public class AttackVisitor implements iTileActionVisitor {

    private double attackDamage;    // Damage to visit entity with

    // Construct attack visitor with given attack damage value of the
    public AttackVisitor(double attackDamage) {
        this.attackDamage = attackDamage;
    }

    // Visit entity and deal damage based on created attack value
    public void visitEntity(Entity e) {
        e.takeDamage(attackDamage);
    }

}
