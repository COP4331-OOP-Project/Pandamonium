package game.gameboard.areaEffect;

/**
 * Created by khariollivierre on 3/13/17.
 */

/*
TODO: Handle effect actions based on enum values
 */
public class AreaEffect {
    private AreaEffectEnum effect;

    public AreaEffect(AreaEffectEnum effect) throws EffectNotFoundException{
        if(effect != AreaEffectEnum.POISON || effect != AreaEffectEnum.HEALING || effect != AreaEffectEnum.DEATH){
            throw new EffectNotFoundException("This effect does not exist!");
        } else this.effect = effect;
    }

    /* Accessors */
    public AreaEffectEnum getEffect() { return effect; }

    /* Mutators */
    public void setEffect(AreaEffectEnum effect) { this.effect = effect; }
}
