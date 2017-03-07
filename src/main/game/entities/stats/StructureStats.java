package game.entities.stats;

import game.entities.structures.exceptions.StructureNotFoundException;
import game.entities.EntitySubtypeEnum;

public class StructureStats {
    private int offPow;         // Damage dealt when attacking [Fort only]
    private int defPow;         // Damage dealt when fending off an attack
    private int armor;          // Amount of damage absorbed when attacked/defended against
    private int health;         // Amount of hit points
    private int prodRate;       // Efficiency rate (per worker) of task completion
    private float upkeep;       // Resources required to keep structure at full health

    public StructureStats(EntitySubtypeEnum struct) throws StructureNotFoundException {
        switch (struct) {
            case CAPITOL:
                this.offPow = 0;
                this.defPow = 5;
                this.armor = 20;
                this.health = 20;
                this.prodRate = 1;
                this.upkeep = .05f;
                break;
            case FARM:
                this.offPow = 0;
                this.defPow = 5;
                this.armor = 20;
                this.health = 20;
                this.prodRate = 1;
                this.upkeep = .05f;
                break;
            case FORT:
                this.offPow = 10;
                this.defPow = 10;
                this.armor = 20;
                this.health = 50;
                this.prodRate = 1;
                this.upkeep = .20f;
                break;
            case MINE:
                this.offPow = 0;
                this.defPow = 5;
                this.armor = 20;
                this.health = 20;
                this.prodRate = 1;
                this.upkeep = .05f;
                break;
            case OBSERVE:
                this.offPow = 0;
                this.defPow = 5;
                this.armor = 20;
                this.health = 20;
                this.prodRate = 1;
                this.upkeep = .05f;
                break;
            case PLANT:
                this.offPow = 0;
                this.defPow = 5;
                this.armor = 20;
                this.health = 20;
                this.prodRate = 1;
                this.upkeep = .05f;
                break;
            case UNIVERSITY:
                this.offPow = 0;
                this.defPow = 5;
                this.armor = 20;
                this.health = 20;
                this.prodRate = 1;
                this.upkeep = .05f;
                break;
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
    public float getUpkeep() { return upkeep; }

    /* Mutators */
    public void setOffPow(int offPow) { this.offPow = offPow; }
    public void setDefPow(int defPow) { this.defPow = defPow; }
    public void setArmor(int armor) { this.armor = armor; }
    public void setHealth(int health) { this.health = health; }
    public void setProdRate(int prodRate) { this.prodRate = prodRate; }
    public void setUpkeep(float upkeep) { this.upkeep = upkeep; }
}
