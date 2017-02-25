package game;

//import game.entities.Army;
//import game.entities.RallyPoint;
//import game.entities.factories.EntityFactory;
//import game.entities.factories.UnknownEntityCodeException;
//import game.gameboard.Resource;
import game.entities.structures.Structure;
import game.entities.units.*;
import game.entities.structures.*;
import game.gameboard.Location;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.lang.model.UnknownEntityException;
import java.util.ArrayList;


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

        for (int i = 0; i < 10; i++) {
            //armies.add(null);
            melees.add(null);
            ranges.add(null);
            explorers.add(null);
            colonists.add(null);
            structures.add(null);
            //rallyPoints.add(null);
        }

        for(int i=0;i<100;i++){
            //workers.add(null);
        }
    }

    public void addMelee(Melee melee){
        if (totalUnits.size()>25) {
            log.info("Unit list is full");
            return;
        }
        for(int i = 0;i<melees.size();i++) {
            if (melees.get(i) == null) {
                melees.set(i, melee);
                //melee.seInstanceId(i);
                totalUnits.add(melee);
                return;
            }
        }

        //If the melee list if full
        log.info("Melee list if full");
    }

    public void addRanged(Ranged ranged){
        if (totalUnits.size()>25) {
            log.info("Unit list is full");
            return;
        }
        for(int i = 0;i<ranges.size();i++) {
            if (ranges.get(i) == null) {
                ranges.set(i, ranged);
                //ranges.setEntityId(i);
                totalUnits.add(ranged);
                return;
            }
        }

        //If the rangelist if full
        log.info("range list if full");
    }

    public void addExplorer(Explorer explorer){
        if (totalUnits.size()>25) {
            log.info("Unit list is full");
            return;
        }
        for(int i = 0;i<explorers.size();i++) {
            if (explorers.get(i) == null) {
                explorers.set(i, explorer);
                //explorer.setEntityId(i);
                totalUnits.add(explorer);
                return;
            }
        }

        //If the explorerlist if full
        log.info("explorer list if full");
    }

    public void addColonist(Colonist colonist){
        if (totalUnits.size()>25) {
            log.info("Unit list is full");
            return;
        }
        for(int i = 0;i<colonists.size();i++) {
            if (colonists.get(i) == null) {
                colonists.set(i, colonist);
                //colonist.setEntityId(i);
                totalUnits.add(colonist);
                return;
            }
        }

        //If the colonist list if full
        log.info("colonist list if full");
    }

    /*
    public void addWorker(Worker worker){
        for(int i = 0;i<workers.size();i++){
            if(workers.get(i)==null){
                workers.set(i,worker);
                workers.setEntityId(i);
                return;
            }
        }
        //If the worker list if full
        log.info("worker list if full");
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
        for(int i = 0;i<armies.size();i++){
            if(armies.get(i)==null){
                armies.set(i,army);
                army.setInstanceId(i);
                return;
            }
        }
    }

    public void addRallyPoint(RallyPoint rallyPoint){
        if (rallyPoints.get(i) == null) {
            rallyPoints.set(i, rallyPoint);
            rallyPoint.setRallyID(i);
            rallyPointsCount++;
            return;
        }
    }
    */
    /*
    public void removeArmy(Army army){
        armies.set(army.getInstanceId(), null);
    }*/

    public ArrayList<Melee> getMelees() {
        ArrayList<Melee> gMelee = new ArrayList<Melee>();
        for (int i = 0; i < melees.size(); i++) {
            if (melees.get(i) != null) {
                gMelee.add(melees.get(i));
            }
        }
        return gMelee;
    }

    public ArrayList<Ranged> getRanges() {
        ArrayList<Ranged> gRange = new ArrayList<Ranged>();
        for (int i = 0; i < ranges.size(); i++) {
            if (ranges.get(i) != null) {
                gRange.add(ranges.get(i));
            }
        }
        return gRange;
    }

    public ArrayList<Explorer> getExplorers() {
        ArrayList<Explorer> gExplorers = new ArrayList<Explorer>();
        for (int i = 0; i < explorers.size(); i++) {
            if (explorers.get(i) != null) {
                gExplorers.add(explorers.get(i));
            }
        }
        return gExplorers;
    }

    public ArrayList<Colonist> getColonists() {
        ArrayList<Colonist> gColonists = new ArrayList<Colonist>();
        for (int i = 0; i < colonists.size(); i++) {
            if (colonists.get(i) != null) {
                gColonists.add(colonists.get(i));
            }
        }
        return gColonists;
    }

    /*
    public ArrayList<Base> getBases() {
        ArrayList<Base> gBases = new ArrayList<Base>();
        for (int i = 0; i < bases.size(); i++) {
            if (bases.get(i) != null) {
                gBases.add(bases.get(i));
            }
        }
        return gBases;
    }*/

    public ArrayList<Unit> getAllUnit() {
        ArrayList<Unit> gUnit = new ArrayList<Unit>();
        for (int i = 0; i < totalUnits.size(); i++) {
            if (totalUnits.get(i) != null) {
                gUnit.add(totalUnits.get(i));
            }
        }
        return gUnit;
    }
    /*
    public ArrayList<Worker> getWorkers(){
        ArrayList<Worker> gWorker = new ArrayList<~>();
        for(int i = 0; i<workers.size();i++){
            if(workers.get(i) != null){
                gWorker.add(workers.get(i));
            }
        }

        return gWorker;
    }*/

    public void addCapitol(Capitol capitol){
        for(int i = 0;i<structures.size();i++){
            if(structures.get(i)==null){
                //capital.setInstanceId(i);
                structures.set(i, capitol);
                return;
            }
        }
    }

    public void addFarm(Farm farm){
        for(int i = 0;i<structures.size();i++){
            if(structures.get(i)==null){
                //farm.setInstanceId(i);
                structures.set(i, farm);
                return;
            }
        }
    }

    public void addFort(Fort fort){
        for(int i = 0;i<structures.size();i++){
            if(structures.get(i)==null){
                // fort.setInstanceId(i);
                structures.set(i, fort);
                return;
            }
        }
    }

    public void addMine(Mine mine){
        for(int i = 0;i<structures.size();i++){
            if(structures.get(i)==null){
                //mine.setInstanceId(i);
                structures.set(i, mine);
                return;
            }
        }
    }

    public void addObservationTower(ObservationTower tower){
        for(int i = 0;i<structures.size();i++){
            if(structures.get(i)==null){
                // tower.setInstanceId(i);
                structures.set(i, tower);
                return;
            }
        }
    }

    public void addPowerPlant(PowerPlant powerPlant){
        for(int i = 0;i<structures.size();i++){
            if(structures.get(i)==null){
                //powerPlant.setInstanceId(i);
                structures.set(i, powerPlant);
                return;
            }
        }
    }

    public void addUniversity(University university){
        for(int i = 0;i<structures.size();i++){
            if(structures.get(i)==null){
                //university.setInstanceId(i);
                structures.set(i, university);
                return;
            }
        }
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