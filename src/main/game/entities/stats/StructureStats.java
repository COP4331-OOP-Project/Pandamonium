package game.entities.stats;

import game.entities.EntitySubtypeEnum;
import game.entities.structures.exceptions.StructureNotFoundException;
import game.resources.Resource;
import game.resources.ResourceTypeEnum;

import java.util.ArrayList;

public class StructureStats {
    private int offPow;         // Damage dealt when attacking [Fort only]
    private int defPow;         // Damage dealt when fending off an attack
    private int armor;          // Amount of damage absorbed when attacked/defended against
    private int health;         // Amount of hit points
    private int prodRate;       // Efficiency rate (per worker) of task completion
    private int influence;
    private ArrayList<Resource> upkeep;       // Resources required to keep structure at full health
    private int visibilityRadius;

    public StructureStats(EntitySubtypeEnum struct) throws StructureNotFoundException {
        this.upkeep = new ArrayList<>();

        switch (struct) {
            case CAPITOL: {
                this.offPow = 0;
                this.defPow = 5;
                this.armor = 20;
                this.health = 20;
                this.prodRate = 1;
                this.influence = 1;
                Resource energyUpkeep = new Resource(1, ResourceTypeEnum.POWER);
                Resource metalUpkeep = new Resource(1, ResourceTypeEnum.METAL);
                this.upkeep.add(energyUpkeep);
                this.upkeep.add(metalUpkeep);
                this.visibilityRadius = 1;
                break;
            }
            case FARM: {
                this.offPow = 0;
                this.defPow = 5;
                this.armor = 20;
                this.health = 20;
                this.prodRate = 1;
                this.influence = 1;
                Resource energyUpkeep = new Resource(1, ResourceTypeEnum.POWER);
                Resource metalUpkeep = new Resource(1, ResourceTypeEnum.METAL);
                this.upkeep.add(energyUpkeep);
                this.upkeep.add(metalUpkeep);
                this.visibilityRadius = 1;
                break;
            }
            case FORT: {
                this.offPow = 10;
                this.defPow = 10;
                this.armor = 20;
                this.health = 50;
                this.prodRate = 1;
                this.influence = 1;
                Resource energyUpkeep = new Resource(1, ResourceTypeEnum.POWER);
                Resource metalUpkeep = new Resource(1, ResourceTypeEnum.METAL);
                this.upkeep.add(energyUpkeep);
                this.upkeep.add(metalUpkeep);
                this.visibilityRadius = 1;
                break;
            }
            case MINE: {
                this.offPow = 0;
                this.defPow = 5;
                this.armor = 20;
                this.health = 20;
                this.prodRate = 1;
                this.influence = 1;
                Resource energyUpkeep = new Resource(1, ResourceTypeEnum.POWER);
                Resource metalUpkeep = new Resource(1, ResourceTypeEnum.METAL);
                this.upkeep.add(energyUpkeep);
                this.upkeep.add(metalUpkeep);
                this.visibilityRadius = 1;
                break;
            }
            case OBSERVE: {
                this.offPow = 0;
                this.defPow = 5;
                this.armor = 20;
                this.health = 20;
                this.prodRate = 1;
                this.influence = 1;
                Resource energyUpkeep = new Resource(1, ResourceTypeEnum.POWER);
                Resource metalUpkeep = new Resource(1, ResourceTypeEnum.METAL);
                this.upkeep.add(energyUpkeep);
                this.upkeep.add(metalUpkeep);
                this.visibilityRadius = 1;
                break;
            }
            case PLANT: {
                this.offPow = 0;
                this.defPow = 5;
                this.armor = 20;
                this.health = 20;
                this.prodRate = 1;
                this.influence = 1;
                Resource energyUpkeep = new Resource(1, ResourceTypeEnum.POWER);
                Resource metalUpkeep = new Resource(1, ResourceTypeEnum.METAL);
                this.upkeep.add(energyUpkeep);
                this.upkeep.add(metalUpkeep);
                this.visibilityRadius = 1;
                break;
            }
            case UNIVERSITY: {
                this.offPow = 0;
                this.defPow = 5;
                this.armor = 20;
                this.health = 20;
                this.prodRate = 1;
                this.influence = 1;
                Resource energyUpkeep = new Resource(1, ResourceTypeEnum.POWER);
                Resource metalUpkeep = new Resource(1, ResourceTypeEnum.METAL);
                this.upkeep.add(energyUpkeep);
                this.upkeep.add(metalUpkeep);
                this.visibilityRadius = 1;
                break;
            }
            default:
                throw new StructureNotFoundException();
        }
    }

    /* Accessors */
    public int getOffPow() { return offPow; }
    public int getDefPow() { return defPow; }
    public int getArmor() { return armor; }
    public int getHealth() { return health; }
    public int getProdRate() { return prodRate; }
    public int getInfluence() { return influence; }
    public int getVisibilityRadius() { return visibilityRadius; }
    public ArrayList<Resource> getUpkeep() { return upkeep; }

    /* Mutators */
    public void setOffPow(int offPow) { this.offPow = offPow; }
    public void setDefPow(int defPow) { this.defPow = defPow; }
    public void setArmor(int armor) { this.armor = armor; }
    public void setHealth(int health) { this.health = health; }
    public void setProdRate(int prodRate) { this.prodRate = prodRate; }
    public void setInfluence(int influence) { this.influence = influence; }
    public void setVisibilityRadius(int visibilityRadius) { this.visibilityRadius = visibilityRadius; }
    public void setUpkeep(ArrayList<Resource> upkeep) { this.upkeep = upkeep; }


    public void increaseVisibilityRadius(int increaseAmount) {
        this.visibilityRadius += increaseAmount;
    }
}
