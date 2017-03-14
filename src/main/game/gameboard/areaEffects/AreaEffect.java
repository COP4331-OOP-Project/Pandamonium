package game.gameboard.areaEffects;

import game.entities.units.Unit;

public interface AreaEffect {

    void affectUnit(Unit u);
    AreaEffectDecalEnum getDecal();
}
