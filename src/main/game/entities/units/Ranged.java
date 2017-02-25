package game.entities.units;

import game.entities.*;
import game.gameboard.Location;
import game.visitors.iTileActionVisitor;

public class Ranged extends Unit implements iEntity, iAttacker, iDefender, iMoveable {

    public Ranged(UnitStats stats, Location location){ super(stats, location); }

}
