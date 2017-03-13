package game.entities.units;

import game.entities.EntityId;
import game.entities.stats.UnitStats;
import game.gameboard.Location;

public class Melee extends Unit{
    public Melee(UnitStats stats, Location location, EntityId entityId){ super(stats, location, entityId); }

    public void onTurnEnded() {

    }
}
