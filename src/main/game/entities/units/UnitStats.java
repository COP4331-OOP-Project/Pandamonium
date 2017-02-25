package game.entities.units;

/**
 * Created by khariollivierre on 2/25/17.
 */
public class UnitStats {
    private int offPow;
    private int defPow;
    private int armor;
    private int speed;
    private int health;
    private float upkeep;

    public UnitStats(int offPow, int defPow, int armor, int speed, int health, float upkeep) {
        this.offPow = offPow;
        this.defPow = defPow;
        this.armor = armor;
        this.speed = speed;
        this.health = health;
        this.upkeep = upkeep;
    }

    /* Accessors */
    public int getOffPow() { return offPow; }
    public int getDefPow() { return defPow; }
    public int getArmor() { return armor; }
    public int getSpeed() { return speed; }
    public int getHealth() { return health; }
    public float getUpkeep() { return upkeep; }

    /* Mutators */
    public void setOffPow(int offPow) { this.offPow = offPow; }
    public void setDefPow(int defPow) { this.defPow = defPow; }
    public void setArmor(int armor) { this.armor = armor; }
    public void setSpeed(int speed) { this.speed = speed; }
    public void setHealth(int health) { this.health = health; }
    public void setUpkeep(float upkeep) { this.upkeep = upkeep; }
}
