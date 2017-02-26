package game.entities.structures;

import game.entities.EntityId;
import game.entities.Percentage;
import game.entities.PowerState;
import game.entities.units.Unit;
import game.gameboard.Location;
import game.visitors.iTileActionVisitor;

public class Fort extends Structure {
    //private ArrayList<worker> workers;
    //private ArrayList<worker> unitBuilder;

    public Fort(Location loc , EntityId entityId ){
        super(loc, entityId);
    }

    public void assignToUnitBuilder(){

    }

    public void unassignUnitBuilder(){

    }

    public void addWorker(){

    }

    public void removeWorker(){

    }

    public void combatState(){
        setPowerState(PowerState.COMBAT);
    }

    public Unit buildUnit(){
        //TODO Creating Unit Handling
        return null;
    }

}
