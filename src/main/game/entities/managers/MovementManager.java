package game.entities.managers;

import game.entities.*;
import game.entities.structures.Structure;
import game.entities.units.Unit;
import game.gameboard.Gameboard;
import game.gameboard.Location;
import game.visitors.iMovementManageVisitor;

public class MovementManager {
    private Gameboard gameboard;

    public MovementManager(Gameboard gb){
        gameboard=gb;
    }

    public void addUnit(Unit unit, Location loc){
        gameboard.getTiles()[loc.getX()][loc.getY()].addUnit(unit);
    }

    public void addStructure(Structure structure, Location loc){
        gameboard.getTiles()[loc.getX()][loc.getY()].addStructure(structure);
    }

    public void addArmy(BattleGroup battleGroup, Location loc){
        gameboard.getTiles()[loc.getX()][loc.getY()].addBattleGroup(battleGroup);
    }

    public void addRallyPoint(RallyPoint rallyPoint, Location loc){
        gameboard.getTiles()[loc.getX()][loc.getY()].addRallyPoint(rallyPoint);
    }

    public void remove(EntityId id, Location loc){
        gameboard.getTiles()[loc.getX()][loc.getY()].removeEntity(id);
    }

    public void accept(iMovementManageVisitor v){
        v.visitMoveManage(this);
    }


}
