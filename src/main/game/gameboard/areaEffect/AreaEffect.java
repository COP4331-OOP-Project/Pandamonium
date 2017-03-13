package game.gameboard.areaEffect;

/**
 * Created by khariollivierre on 3/13/17.
 */

/*
TODO: Handle effect actions
 */
public class AreaEffect {
    private AreaEffectEnum effect;
    private AreaEffectPower power;

    public AreaEffect(AreaEffectEnum effect, AreaEffectPower power){
        this.effect = effect;
        this.power = power;
    }

    /* Accessors */
    public AreaEffectEnum getEffect() { return effect; }
    public AreaEffectPower getPower() { return power; }

    /* Mutators */
    public void setEffect(AreaEffectEnum effect) { this.effect = effect; }
    public void setPower(AreaEffectPower power) { this.power = power; }
}
