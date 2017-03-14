package game.gameboard.areaEffects;

import game.entities.units.Unit;

public class InstantDeathAreaEffect implements AreaEffect {

    public InstantDeathAreaEffect() {

    }

    public void affectUnit(Unit u) {
        u.instantDeath();
    }

    public AreaEffectDecalEnum getDecal() {
        return AreaEffectDecalEnum.INSTANT_DEATH;
    }
}
