package game.entities.stats;

import game.entities.EntitySubtypeEnum;
import game.entities.structures.exceptions.StructureNotFoundException;
import game.resources.Resource;
import game.resources.ResourceTypeEnum;
import game.semantics.Percentage;
import game.semantics.PercentageOutOfRangeException;

import java.util.ArrayList;

public class StructureStats {
    private int attackStrength;         // Damage dealt when attacking [Fort only]
    private int defensiveStrength;         // Damage dealt when fending off an attack
    private int armor;          // Amount of damage absorbed when attacked/defended against
    private int health;         // Amount of hit points
    private int prodRate;       // Efficiency rate (per worker) of task completion
    private int influence;
    private ArrayList<Resource> upkeep;       // Resources required to keep structure at full health
    private int visibilityRadius;
    private Percentage efficiency;

    public StructureStats(EntitySubtypeEnum struct) throws StructureNotFoundException {
        this.upkeep = new ArrayList<>();

        switch (struct) {
            case CAPITOL: {
                this.attackStrength = 0;
                this.defensiveStrength = 5;
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
                this.attackStrength = 0;
                this.defensiveStrength = 5;
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
                this.attackStrength = 10;
                this.defensiveStrength = 10;
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
                this.attackStrength = 0;
                this.defensiveStrength = 5;
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
                this.attackStrength = 0;
                this.defensiveStrength = 5;
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
                this.attackStrength = 0;
                this.defensiveStrength = 5;
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
                this.attackStrength = 0;
                this.defensiveStrength = 5;
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
    public int getAttackStrength() { return attackStrength; }
    public int getDefensiveStrength() { return defensiveStrength; }
    public int getArmor() { return armor; }
    public int getHealth() { return health; }
    public int getProdRate() { return prodRate; }
    public int getInfluence() { return influence; }
    public int getVisibilityRadius() { return visibilityRadius; }
    public ArrayList<Resource> getUpkeep() { return upkeep; }

    /* Mutators */
    public void setAttachStrength(int attackStrength) { this.attackStrength = attackStrength; }
    public void setDefensiveStrength(int defensiveStrength) { this.defensiveStrength = defensiveStrength; }
    public void setArmor(int armor) { this.armor = armor; }
    public void setHealth(int health) { this.health = health; }
    public void setProdRate(int prodRate) { this.prodRate = prodRate; }
    public void setInfluence(int influence) { this.influence = influence; }
    public void setVisibilityRadius(int visibilityRadius) { this.visibilityRadius = visibilityRadius; }
    public void setUpkeep(ArrayList<Resource> upkeep) { this.upkeep = upkeep; }


    public void increaseVisibilityRadius(int increaseAmount) {
        this.visibilityRadius += increaseAmount;
    }

    public void increaseAttackStrength(int increaseAmount) {
        this.attackStrength += increaseAmount;
    }
    public void increaseDefenseStrength(int increaseAmount) {
        this.defensiveStrength += increaseAmount;
    }
    public void increaseArmorStrength(int increaseAmount) {
        this.armor += increaseAmount;
    }

    public void increaseHealth(int increaseAmount) {
        this.health += increaseAmount;
    }

    public void increaseEfficiency(Percentage increasePercentage) {
        try {
            this.efficiency.add(increasePercentage);
        } catch (PercentageOutOfRangeException e) {

        }
    }
}
