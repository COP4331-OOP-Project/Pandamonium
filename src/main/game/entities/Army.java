package game.entities;

import game.entities.managers.PlacementManager;

import game.commands.CommandEnum;
import game.entities.units.Unit;
import game.gameboard.Location;
import game.visitors.AddArmyVisitor;
import game.visitors.AddRallyPointVisitor;
import game.visitors.RemoveEntityVisitor;

public class Army extends Entity {

    private BattleGroup battleGroup;        // Battlegroup Units
    private Reinforcement reinforcement;    // Reinforcement Units
    private RallyPoint rallyPoint;          // Army's Rally Point

    private Location location;              // Army current location

    public Army(EntityId id, Location location, RallyPoint rp, PlacementManager placementManager){

        super(id, placementManager);
        this.rallyPoint = rp;               // Set rally point
        this.location = location;           // Set location


        battleGroup = new BattleGroup(rp.getLocation(), id);  // Setup Battlegroup
        reinforcement = new Reinforcement();                  // Setup Reinforcements

        AddRallyPointVisitor addRallyPointVisitor = new AddRallyPointVisitor(rallyPoint,rp.getLocation());
        placementManager.accept(addRallyPointVisitor);
        updateArmy();

        addCommand(CommandEnum.DROP_OFF_WORKER);
        addCommand(CommandEnum.PICK_UP_WORKER);
        addCommand(CommandEnum.BUILD_STRUCTURE);
        addCommand(CommandEnum.DISBAND_ARMY);

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

    public BattleGroup getBattleGroup() { return battleGroup; }
    public Reinforcement getReinforcements() { return reinforcement; }
    public RallyPoint getRallyPoint() { return rallyPoint; }

}
