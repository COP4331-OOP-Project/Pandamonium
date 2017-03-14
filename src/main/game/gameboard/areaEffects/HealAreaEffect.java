package game.gameboard.areaEffects;

import game.entities.units.Unit;

public class HealAreaEffect implements AreaEffect {

    private int healAmount;

    public HealAreaEffect(int healAmount) {
        this.healAmount = healAmount;
    }

    public int getHealAmount() {
        return this.healAmount;
    }

    public void affectUnit(Unit u) {
        u.heal(this.healAmount);
    }
}
