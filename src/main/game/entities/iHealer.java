package game.entities;

// Interface to denote entities that can heal
public interface iHealer {
    double getHealing();        // Get heal value of actor
    double getRange();          // Get range of actor's heal
}
