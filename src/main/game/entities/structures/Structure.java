package game.entities.structures;

import game.commands.Command;
import game.gameboard.Location;

/**
 * Created by Alex on 2/21/17.
 */
public abstract class Structure {
    //private EntityID entityID;
    //private PowerState powerState;
    //private float defenseDamage;
    private Location location;

    public Structure(){

    }

    public Structure(Location loc /*, EntityID entityID */){
        location=loc;
    }
    //TODO add command queue? ASK TEAM IS THIS INTERFACE OR NOT?
    public void addCommandToQueue(){

    }

    public void cancelQueueCommand(){

    }
    //TODO add power state
    public void powerUp(){

    }

    public void standby(){

    }

    public void decomission(){

    }
}
