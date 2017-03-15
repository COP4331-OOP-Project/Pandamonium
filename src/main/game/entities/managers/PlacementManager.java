package game.entities.managers;

import game.entities.Army;
import game.entities.BattleGroup;
import game.entities.EntityId;
import game.entities.RallyPoint;
import game.entities.structures.Structure;
import game.entities.units.Unit;
import game.gameboard.Gameboard;
import game.gameboard.Location;
import game.gameboard.iTileObserver;
import game.visitors.iMovementManageVisitor;

public class PlacementManager {
    //TODO UNCOMMENT MOVEMENTMANAGER USAGE IN CONSTRUCTORS
    private Gameboard gameboard;

    public PlacementManager(Gameboard gb){
        gameboard=gb;
    }

    public void addUnit(Unit unit, Location loc){
        gameboard.addUnitToTile(unit, loc);
    }

    public void addStructure(Structure structure, Location loc){
        gameboard.addStructureToTile(structure,loc);
    }

    public void addArmy(Army army, Location loc){
        gameboard.addArmyToTile(army,loc);
    }

    public void addRallyPoint(RallyPoint rallyPoint, Location loc){
        gameboard.addRallyPoinTToTile(rallyPoint,loc);
    }

    public void remove(EntityId id, Location loc){
        gameboard.getTiles()[loc.getX()][loc.getY()].removeEntity(id);
    }

    public void detachInfluencers(iTileObserver o){
        gameboard.detachInfluencers(o);
    }

    public void accept(iMovementManageVisitor v){
        v.visitMoveManage(this);
    }

}
