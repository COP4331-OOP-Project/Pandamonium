package game.entities.structures;

import game.commands.Command;
import game.gameboard.Location;

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
