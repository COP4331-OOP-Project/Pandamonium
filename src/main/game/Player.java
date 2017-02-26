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
        //melees.set(melee.getInstanceId(), null);
    }

    public void removeRanged(Ranged ranged){
        //ranges.set(ranged.getInstanceId(),null);
    }

    public void removeExplorer(Explorer explorer){
        //explorers.set(explorer.getInstanceId(), null);
    }

    public void removeColonist(Colonist colonist){
        //colonists.set(colonist.getInstanceId(),null);
    }
/*
    public void remvoeWorker(Worker worker){
        workers.set(worker.getInstanceId(), null);
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
        //structures.set(capitol.getInstanceId(),null);
    }

    public void removeFarm(Farm farm){
        //structures.set(farm.getInstanceId(),null);
    }

    public void removeFort(Fort fort){
        //structures.set(fort.getInstanceId(),null);
    }

    public void removeMine(Mine mine){
        //structures.set(mine.getInstanceId(),null);
    }

    public void removeObservationTower(ObservationTower tower){
        //structures.set(tower.getInstanceId(),null);
    }

    public void removePowerPlant(PowerPlant powerPlant){
        //structures.set(powerPlant.getInstanceId(),null);
    }

    public void removeUniversity(University university){
        //structures.set(university.getInstanceId(),null);
    }
}