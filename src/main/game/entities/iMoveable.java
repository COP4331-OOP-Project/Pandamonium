package game.entities;

// Interface to denote move distance available to entity
public interface iMoveable extends iEntity {
    int getMoveDistance();      // Get possible distance that actor can move with each move
}
