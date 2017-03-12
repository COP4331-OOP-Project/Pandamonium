package game.entities.managers;

import game.entities.*;
import game.entities.structures.Structure;
import game.entities.units.Unit;
import game.gameboard.Gameboard;
import game.gameboard.Location;

public class MovementManager {
    private Gameboard gameboard;

    public MovementManager(Gameboard gb){
        gameboard=gb;
    }

    public void AddUnit(Unit unit, Location loc){
        gameboard.getTiles()[loc.getX()][loc.getY()].addUnit(unit);
    }

    public void AddStructure(Structure structure, Location loc){
        gameboard.getTiles()[loc.getX()][loc.getY()].addStructure(structure);
    }

    public void AddArmy(BattleGroup battleGroup, Location loc){
        gameboard.getTiles()[loc.getX()][loc.getY()].addBattleGroup(battleGroup);
    }

    public void AddRallyPoint(RallyPoint rallyPoint, Location loc){
        gameboard.getTiles()[loc.getX()][loc.getY()].addRallyPoint(rallyPoint);
    }

    public void Remove(EntityId id, Location loc){
        gameboard.getTiles()[loc.getX()][loc.getY()].removeEntity(id);
    }


}
