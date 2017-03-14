package game.gameboard.oneShotItem;

import game.entities.units.Unit;
import game.gameboard.areaEffects.AreaEffect;
import game.gameboard.areaEffects.AreaEffectDecalEnum;

public class OneShotItem {

    private AreaEffect areaEffect;
    private boolean isUsed;
    private AreaEffectDecalEnum imageEnum;

    public OneShotItem(AreaEffect areaEffect) {
        this.areaEffect = areaEffect;
        this.isUsed = false;
    }

    public boolean isUsed() {
        return this.isUsed;
    }

    public AreaEffectDecalEnum getDecal() {
        return this.areaEffect.getDecal();
    }

    public void useItem(Unit u) {
        this.isUsed = true;
        this.areaEffect.affectUnit(u);
    }
}
