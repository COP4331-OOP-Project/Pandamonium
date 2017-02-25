package game.entities;

import game.entities.units.BattleGroupUnit;

import java.util.ArrayList;
import java.util.Iterator;

public class BattleGroup implements iAttacker{
    private ArrayList<BattleGroupUnit> bgUnits;

    public BattleGroup(){
        bgUnits = new ArrayList<>();
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
            int lowest = bgUnits.get(1).getRange();
            int next;
            Iterator<BattleGroupUnit> i = bgUnits.iterator();
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
}
