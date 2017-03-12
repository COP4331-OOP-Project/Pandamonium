package game.entities;

import java.util.ArrayList;
import java.util.Iterator;

import game.entities.stats.UnitStats;
import game.entities.units.BattleGroupUnit;
import game.gameboard.Location;

public class BattleGroup implements iAttacker{
    private EntityId entityId;
    private ArrayList<BattleGroupUnit> bgUnits;
    private Location location;

    public BattleGroup(Location loc, EntityId id){
        bgUnits = new ArrayList<>();
        location=loc;
        //Battlegroup shares entity id with army id. helps with reference removal when there are
        //multiple battlegroups on one tile
        entityId=id;
    }

    public void addUnit(UnitStats unitStats, EntityId entityId){
        //TODO whatever calls this needs to also destroy tile reference to unit
        bgUnits.add(new BattleGroupUnit(unitStats, entityId));
    }

    public double getDamage(){
        double total = 0;
        Iterator<BattleGroupUnit> i = bgUnits.iterator();
        while(i.hasNext()){
            total+=i.next().getDamage();
        }
        return total;
    }

    public int getRange(){
        if(!bgUnits.isEmpty()) {
            int next;
            Iterator<BattleGroupUnit> i = bgUnits.iterator();
            int lowest = i.next().getRange();
            while (i.hasNext()) {
                next = i.next().getRange();
                if (next < lowest) {
                    lowest = next;
                }
            }
            return lowest;
        }
        return 0;
    }

    public int getMoveSpeed(){
        if(!bgUnits.isEmpty()) {
            int next;
            Iterator<BattleGroupUnit> i = bgUnits.iterator();
            int lowest = i.next().getMoveSpeed();
            while (i.hasNext()) {
                next = i.next().getMoveSpeed();
                if (next < lowest) {
                    lowest = next;
                }
            }
            return lowest;
        }
        return 0;
    }

    public void setLocation(Location loc){
        location=loc;
    }

    public Location getLocation(){
        return location;
    }

    public EntityId getEntityId(){
        return entityId;
    }
}
