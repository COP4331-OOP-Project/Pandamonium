package game.entities.units;

import game.entities.*;
import game.gameboard.Location;
import game.visitors.iTileActionVisitor;

public class Colonist extends Unit implements iAttacker, iDefender, iMoveable {

    public Colonist(UnitStats stats, Location location){
        super(stats, location);
    }

}
