package game.gameboard.areaEffect;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by khariollivierre on 3/13/17.
 */
public class AreaEffectFactory {
    private Map<AreaEffectEnum, AreaEffectPower> effectPowers;

    public AreaEffectFactory(int playerId){
        this.effectPowers = new HashMap<>();
        this.effectPowers.put(AreaEffectEnum.POISON, new AreaEffectPower(AreaEffectEnum.POISON));
        this.effectPowers.put(AreaEffectEnum.HEALING, new AreaEffectPower(AreaEffectEnum.HEALING));
        this.effectPowers.put(AreaEffectEnum.DEATH, new AreaEffectPower(AreaEffectEnum.DEATH));
    }

    public AreaEffect createAreaEffect(AreaEffectEnum effect){
        return new AreaEffect(effect, effectPowers.get(effect));
    }

}
