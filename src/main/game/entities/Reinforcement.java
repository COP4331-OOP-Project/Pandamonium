package game.entities;

import java.util.ArrayList;

import game.entities.units.Unit;

public class Reinforcement {
    private ArrayList<Unit> reinforcements;

    public Reinforcement(){
        reinforcements = new ArrayList<>();
    }

    public void addReinforcement(Unit unit){
        reinforcements.add(unit);
    }

    //TODO wait for comparator for entity id
    public void removeReinforcement(Unit unit){

    }
}
