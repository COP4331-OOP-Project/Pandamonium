package game.gameboard.areaEffect;

/**
 * Created by khariollivierre on 3/13/17.
 */
public class AreaEffectPower {
    private int power;

    public AreaEffectPower(AreaEffectEnum effect){
        switch (effect){
            case POISON: power = 5;
            case HEALING: power = 10;
            case DEATH: power = -1;
        }
    }

    /* Accessors */
    public int getPower() { return power; }

    /* Mutators */
    public void setPower(int power) { this.power = power; }
}
