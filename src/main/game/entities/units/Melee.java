package game.entities.units;

import game.entities.*;
import game.gameboard.Location;
import game.visitors.iTileActionVisitor;

public class Melee extends Unit implements iAttacker, iDefender, iMoveable {

    public Melee(UnitStats stats, Location location){ super(stats, location); }

}
