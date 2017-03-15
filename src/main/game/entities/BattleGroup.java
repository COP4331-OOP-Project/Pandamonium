package game.entities;

import game.entities.units.BattleGroupUnit;
import game.entities.units.Unit;
import game.gameboard.Location;

import java.util.ArrayList;
import java.util.Iterator;

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

    public void addUnit(Unit unit){
        //TODO whatever calls this needs to also destroy tile reference to unit
        bgUnits.add(new BattleGroupUnit(unit.getStats(), unit.getEntityId()));
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

    public int getOwnerId(){return entityId.getPlayerId();}
}
