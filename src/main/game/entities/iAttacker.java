package game.entities;

// Interface for entities capable of attacking
public interface iAttacker extends iEntity {
    double getDamage();         // Get damage value of actor
    int getRange();             // Get range value of actor's attack
}
