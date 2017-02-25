package game.entities;

import game.visitors.iTileActionVisitor;

public interface iEntity {

    double getCurrentHealth();                  // Return entity health
    Percentage getHealthPercentage();           // Return entity health pct.
    void takeDamage(double damage);             // Take damage to health
    void heal(double healing);                  // Heal for a given amount
    void accept(iTileActionVisitor v);          // Accept visitors

}
