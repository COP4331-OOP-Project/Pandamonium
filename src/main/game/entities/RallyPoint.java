package game.entities;

import java.util.ArrayList;

import game.commands.CommandEnum;
import game.commands.iCommandable;
import game.gameboard.Location;

public class RallyPoint implements iCommandable{
    private Location location;
    private EntityId entityId;
    private Army army;
    private ArrayList<CommandEnum> commands = new ArrayList<>();

    public RallyPoint(Location loc, EntityId id, Army army){
        location=loc;
        entityId=id;
        this.army=army;
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

	@Override
	public ArrayList<CommandEnum> getCommands() {
		// TODO Auto-generated method stub
		return null;
	}
}
