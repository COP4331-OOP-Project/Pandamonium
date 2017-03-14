package game.entities;

import game.gameboard.Location;

// Interface to denote move distance available to entity
public interface iMoveable {

    int getMoveDistance();                  // Get possible distance that actor can move with each move
    void setLocation(Location location);    // Set location of entity

}
