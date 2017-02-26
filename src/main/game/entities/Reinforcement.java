package game.entities;

import game.entities.units.Unit;

import java.util.ArrayList;

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
