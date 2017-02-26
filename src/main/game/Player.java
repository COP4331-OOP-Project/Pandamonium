package game;

import java.util.ArrayList;

import javax.lang.model.UnknownEntityException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import game.entities.structures.Capitol;
import game.entities.structures.Farm;
import game.entities.structures.Fort;
import game.entities.structures.Mine;
import game.entities.structures.ObservationTower;
import game.entities.structures.PowerPlant;
import game.entities.factories.EntityFactory;
import game.entities.structures.Structure;
import game.entities.structures.University;
import game.entities.units.Colonist;
import game.entities.units.Explorer;
import game.entities.units.Melee;
import game.entities.units.Ranged;
import game.entities.units.Unit;
import game.gameboard.Location;


public class Player {
    private final static Logger log = LogManager.getLogger(Player.class);

    //Player ID of this player
    private int playerId;

    //ArrayLists of this player's instances
    //private ArrayList<Army> armies;
    private ArrayList<Melee> melees;
    private ArrayList<Ranged> ranges;
    private ArrayList<Explorer> explorers;
    private ArrayList<Colonist> colonists;
    //private ArrayList<Worker> workers;
    private ArrayList<Structure> structures;
    //private ArrayList<RallyPoint> rallyPoints;
    private ArrayList<Unit> totalUnits;
    private int totalUnitCount;
    //private SimpleMap


    //Initial Units
    private Colonist initialColonist;

    public Player(int playerId, Location loc) {
        //loc is referring to the starting location of this player
        this.playerId = playerId;

        try {
            //initialColonist = (Colonist)EntityFactory.getEntity(loc, this.playerId, "c")
        } catch (UnknownEntityException e){
            log.error(e.getLocalizedMessage());
        } finally {
            init();
        }
    }

    private void init(){
        //Initialize all the arraylists of thsi player
        //armies = new ArrayList<Army>();
        melees = new ArrayList<Melee>();
        ranges = new ArrayList<Ranged>();
        explorers = new ArrayList<Explorer>();
        colonists = new ArrayList<Colonist>();
        //workers = new ArrayList<Worker>();
        structures = new ArrayList<Structure>();
        //rallyPoints = new ArrayList<RallyPoint>();
        totalUnits = new ArrayList<Unit>();
    }

    public void addMelee(Melee melee){
        melees.add(melee);
    }

    public void addRanged(Ranged ranged){
        ranges.add(ranged);
    }

    public void addExplorer(Explorer explorer){
        explorers.add(explorer);
    }

    public void addColonist(Colonist colonist){
        colonists.add(colonist);
    }

    /*
    public void addWorker(Worker worker){
        workers.add(worker);
    }
    */

    public void removeMelee(Melee melee){
//        for(int i = 0;i<melees.size();i++){
//            if(melees.get(i).getInstanceId() == melee.getInstanceId()){
//                melees.remove(i);
//            }
//        }
    }

    public void removeRanged(Ranged ranged){
//        for(int i = 0;i<ranges.size();i++){
//            if(ranges.get(i).getInstanceId() == ranges.getInstanceId()){
//                ranges.remove(i);
//            }
//        }
    }

    public void removeExplorer(Explorer explorer){
//        for(int i = 0;i<explorers.size();i++){
//            if(explorers.get(i).getInstanceId() == explorer.getInstanceId()){
//                explorers.remove(i);
//            }
//        }
    }

    public void removeColonist(Colonist colonist){
//        for(int i = 0;i<colonists.size();i++){
//            if(colonists.get(i).getInstanceId() == colonist.getInstanceId()){
//                colonists.remove(i);
//            }
//        }
    }
/*
    public void remvoeWorker(Worker worker){
        for(int i = 0;i<melees.size();i++){
            if(workers.get(i).getInstanceId() == worker.getInstanceId()){
                workers.remove(i);
            }
        }
    }
*/
    /*
    public void addArmy(Army army){
        armies.add(army);
    }

    public void addRallyPoint(RallyPoint rallyPoint){
        rallyPoint.add(rallyPoint);
    }
    */
    /*
    public void removeArmy(Army army){
        armies.set(army.getInstanceId(), null);
    }*/

    public ArrayList<Melee> getMelees() {
        return melees;
    }

    public ArrayList<Ranged> getRanges() {
        return ranges;
    }

    public ArrayList<Explorer> getExplorers() {
        return explorers;
    }

    public ArrayList<Colonist> getColonists() {
        return colonists;
    }

    public ArrayList<Unit> getAllUnit() {
        return totalUnits;
    }
    /*
    public ArrayList<Worker> getWorkers(){
        return workers;
    }*/

    public void addCapitol(Capitol capitol){
        structures.add(capitol);
    }

    public void addFarm(Farm farm){
        structures.add(farm);
    }

    public void addFort(Fort fort){
        structures.add(fort);
    }

    public void addMine(Mine mine){
        structures.add(mine);
    }

    public void addObservationTower(ObservationTower tower){
        structures.add(tower);
    }

    public void addPowerPlant(PowerPlant powerPlant){
        structures.add(powerPlant);
    }

    public void addUniversity(University university){
        structures.add(university);
    }

    public void removeCapitol(Capitol capitol){
        for(int i = 0;i<structures.size();i++){
            if(structures.get(i).getInstanceId() == capitol.getInstanceId()){
                structures.remove(i);
            }
        }
    }

    public void removeFarm(Farm farm){
        for(int i = 0;i<structures.size();i++){
            if(structures.get(i).getInstanceId() == farm.getInstanceId()){
                structures.remove(i);
            }
        }
    }

    public void removeFort(Fort fort){
        for(int i = 0;i<structures.size();i++){
            if(structures.get(i).getInstanceId() == fort.getInstanceId()){
                structures.remove(i);
            }
        }
    }

    public void removeMine(Mine mine){
        for(int i = 0;i<structures.size();i++){
            if(structures.get(i).getInstanceId() == mine.getInstanceId()){
                structures.remove(i);
            }
        }
    }

    public void removeObservationTower(ObservationTower tower){
        for(int i = 0;i<structures.size();i++){
            if(structures.get(i).getInstanceId() == tower.getInstanceId()){
                structures.remove(i);
            }
        }
    }

    public void removePowerPlant(PowerPlant powerPlant){
        for(int i = 0;i<structures.size();i++){
            if(structures.get(i).getInstanceId() == powerPlant.getInstanceId()){
                structures.remove(i);
            }
        }
    }

    public void removeUniversity(University university){
        for(int i = 0;i<structures.size();i++){
            if(structures.get(i).getInstanceId() == university.getInstanceId()){
                structures.remove(i);
            }
        }
    }
}