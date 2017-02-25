package game.entities.units;

public class UnitStats {
    private int offPow;
    private int defPow;
    private int armor;
    private int speed;
    private int health;
    private float upkeep;

    public UnitStats(UnitType unit) {
        switch(unit){
            case COLONIST:
                this.offPow = 1;
                this.defPow = 1;
                this.armor = 1;
                this.speed = 1;
                this.health = 10;
                this.upkeep = .05f;
                break;
            case EXPLORER:
                this.offPow = 2;
                this.defPow = 2;
                this.armor = 2;
                this.speed = 10;
                this.health = 10;
                this.upkeep = .05f;
                break;
            case MELEE:
                this.offPow = 5;
                this.defPow = 5;
                this.armor = 5;
                this.speed = 5;
                this.health = 10;
                this.upkeep = .1f;
                break;
            case RANGED:
                this.offPow = 7;
                this.defPow = 7;
                this.armor = 3;
                this.speed = 5;
                this.health = 10;
                this.upkeep = .1f;
                break;
        }
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
