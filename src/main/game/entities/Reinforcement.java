package game.entities;

import game.entities.units.Unit;
import game.gameboard.Location;

import java.util.ArrayList;
import java.util.Iterator;

public class Reinforcement {
    private ArrayList<Unit> reinforcements;

    public Reinforcement(){
        reinforcements = new ArrayList<>();
    }

    public void addReinforcement(Unit unit){
        reinforcements.add(unit);
    }

    //TODO wait for comparator for entity id
    public void removeReinforcement(EntityId id){
        Iterator<Unit> iterator = reinforcements.iterator();
        while (iterator.hasNext()) {
            Unit holder = iterator.next();
            if (id.compareTo(holder.getEntityId())==1){
                iterator.remove();
                return;
            }
        }
    }

    //If one reinforcement is on battlegroup location return true
    public boolean onLocation(Location location){
        Iterator<Unit> iterator = reinforcements.iterator();
        while (iterator.hasNext()) {
            Unit holder = iterator.next();
            if (holder.getLocation().equals(location)){
                return true;
            }
        }
        return false;
    }

    public Unit getOnLocationUnit(Location location){
        Iterator<Unit> iterator = reinforcements.iterator();
        while (iterator.hasNext()) {
            Unit holder = iterator.next();
            if (holder.getLocation().equals(location)){
                return holder;
            }
        }
        // shouldn't happen. this function should not be called unless the onLocation function returns true.
        return null;
    }
}
