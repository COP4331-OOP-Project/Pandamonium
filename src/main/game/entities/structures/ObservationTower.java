package game.entities.structures;

import game.entities.EntityId;
import game.entities.Percentage;
import game.gameboard.Location;
import game.visitors.iTileActionVisitor;

public class ObservationTower extends Structure {
    public ObservationTower(Location loc , EntityId entityId ){
        super(loc, entityId);
    }

}
