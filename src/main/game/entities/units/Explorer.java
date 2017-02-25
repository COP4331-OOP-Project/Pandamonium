package game.entities.units;

import game.entities.*;
import game.gameboard.Location;
import game.visitors.iTileActionVisitor;

public class Explorer extends Unit implements iAttacker, iDefender, iMoveable {

    public Explorer(UnitStats stats, Location location){ super(stats, location); }

}
