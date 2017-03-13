package game.gameboard.areaEffect;

/**
 * Created by khariollivierre on 3/13/17.
 */
public class AreaEffectPower {
    private int power;

    public AreaEffectPower(AreaEffectEnum effect){
        switch (effect){
            case POISON: power = 5;     // Do this.power damage to units on tile
            case HEALING: power = 10;   // Heal this.power HP to units on tile
            case DEATH: power = -1;     // Kill all units on tile
        }
    }

    /* Accessors */
    public int getPower() { return power; }

    /* Mutators */
    public void setPower(int power) { this.power = power; }
}
