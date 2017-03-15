package game.entities;

import game.commands.MoveCommand;
import game.entities.managers.PlacementManager;

import game.commands.CommandEnum;
import game.entities.units.Unit;
import game.gameboard.Location;
import game.gameboard.PathFinding;
import game.visitors.AddArmyVisitor;
import game.visitors.AddRallyPointVisitor;
import game.visitors.RemoveEntityVisitor;

import java.util.ArrayList;
import java.util.Iterator;

public class Army extends Entity {

    private BattleGroup battleGroup;        // Battlegroup Units
    private Reinforcement reinforcement;    // Reinforcement Units
    private RallyPoint rallyPoint;          // Army's Rally Point

    private Location location;              // Army current location
    private PathFinding pathFinding;

    public Army(EntityId id, ArrayList<Unit> units, RallyPoint rp, PlacementManager placementManager, PathFinding pathFinding){
        super(id, placementManager);
        this.rallyPoint = rp;               // Set rally point
        this.location = rp.getLocation();           // Set location
        battleGroup = new BattleGroup(rp.getLocation(), id);  // Setup Battlegroup
        reinforcement = new Reinforcement();                  // Setup Reinforcements
        AddRallyPointVisitor addRallyPointVisitor = new AddRallyPointVisitor(rallyPoint,rp.getLocation());
        placementManager.accept(addRallyPointVisitor);
        for(Unit u: units){
            reinforcement.addReinforcement(u);
        }
        updateArmy();
        this.pathFinding=pathFinding;
        addCommand(CommandEnum.DROP_OFF_WORKER);
        addCommand(CommandEnum.PICK_UP_WORKER);
        addCommand(CommandEnum.BUILD_STRUCTURE);
        addCommand(CommandEnum.DISBAND_ARMY);

    }

    public void doTurn(){
        Iterator<Unit> iterator = reinforcement.getReinforcements().iterator();
        while(iterator.hasNext()){
            Unit holder = iterator.next();
            MoveCommand command = new MoveCommand(holder, holder.getLocation(), pathFinding.pathAlgorithm(holder.getLocation(), rallyPoint.getLocation(), holder.getOwnerID()), 0);
            holder.addCommandToQueue(command);
            holder.doTurn();
        }
        if(battleGroup.getLocation().equals(rallyPoint.getLocation())){
            if(!commandQueue.isEmpty()) {
                if(commandQueue.peek().iterateDuration()){
                    commandQueue.poll();
                }
            }
        }
        else{
            int direction = pathFinding.pathAlgorithm(battleGroup.getLocation(), rallyPoint.getLocation(), getEntityId().getPlayerId());
            Location location = pathFinding.directionLocation(battleGroup.getLocation(), direction);
            if(location!=null) {
                moveBattleGroup(location);
            }
        }
        updateArmy();
    }

    public void moveRallyPoint(Location loc){
        //Add to new tile
        AddRallyPointVisitor addRallyPointVisitor = new AddRallyPointVisitor(rallyPoint,loc);
        placementManager.accept(addRallyPointVisitor);
        //Remove from old tile
        RemoveEntityVisitor removeEntityVisitor = new RemoveEntityVisitor(rallyPoint.getEntityId(),rallyPoint.getLocation());
        placementManager.accept(removeEntityVisitor);
        rallyPoint.setLocation(loc);
    }

    public void updateArmy(){
        while(reinforcement.onLocation(battleGroup.getLocation())){
            Unit unitToAdd = reinforcement.getOnLocationUnit(battleGroup.getLocation());
            //Remove tile reference to unit
            RemoveEntityVisitor removeEntityVisitor = new RemoveEntityVisitor(unitToAdd.getEntityId(), unitToAdd.getLocation());
            placementManager.accept(removeEntityVisitor);
            battleGroup.addUnit(unitToAdd);
        }
        if(battleGroup.hasUnits()){
            AddArmyVisitor addArmyVisitor = new AddArmyVisitor(battleGroup, battleGroup.getLocation());
            placementManager.accept(addArmyVisitor);
        }
        else{
            RemoveEntityVisitor removeEntityVisitor = new RemoveEntityVisitor(battleGroup.getEntityId(), battleGroup.getLocation());
            placementManager.accept(removeEntityVisitor);
        }
    }

    public void moveBattleGroup(Location loc){
        //Move to Tile
        AddArmyVisitor addArmyVisitor = new AddArmyVisitor(battleGroup, loc);
        placementManager.accept(addArmyVisitor);
        //Delete old Tile reference
        RemoveEntityVisitor removeEntityVisitor = new RemoveEntityVisitor(getEntityId(),battleGroup.getLocation());
        placementManager.accept(removeEntityVisitor);
        //Update battlegroup and army location
        battleGroup.setLocation(loc);
    }

    public Location getLocation(){return battleGroup.getLocation();}
    public int getLocationX(){return getLocation().getX();}
    public int getLocationY(){return getLocation().getY();}

    public Location getRallyPointLocation(){return rallyPoint.getLocation();}

    public double getCurrentHealth(){
        return 0;
    }
    public HealthPercentage getHealthPercentage(){
        return null;
    }
    public void takeDamage(double damage) {}
    public void heal(double healing) {}
    public void decommissionEntity() {}
    public void disband() {}

    public BattleGroup getBattleGroup() { return battleGroup; }
    public Reinforcement getReinforcements() { return reinforcement; }
    public RallyPoint getRallyPoint() { return rallyPoint; }

}
