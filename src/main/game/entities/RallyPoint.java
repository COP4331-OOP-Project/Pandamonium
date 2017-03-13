package game.entities;

import java.util.ArrayList;

import game.commands.CommandEnum;
import game.commands.iCommandable;
import game.gameboard.Location;

public class RallyPoint implements iCommandable {

    private Location location;  // Current rally point location
    private EntityId entityId;  // Entity ID
    private ArrayList<CommandEnum> commands = new ArrayList<>();

    // Reference to Army

    // Constructor
    public RallyPoint(EntityId id, Location location){
        this.entityId = id;
        this.location = location;
        commands.add(CommandEnum.MOVE_RALLY_POINT);
    }

    public Location getLocation(){
        return location;
    }

    public void setLocation(Location loc){
        location=loc;
    }

    public EntityId getEntityId(){
        return entityId;
    }

	public ArrayList<CommandEnum> getCommands() {
		return commands;
	}

}
