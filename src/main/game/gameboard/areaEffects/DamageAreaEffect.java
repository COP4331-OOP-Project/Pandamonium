package game.gameboard.areaEffects;

import game.entities.units.Unit;

public class DamageAreaEffect implements AreaEffect {

    private int damageAmount;

    public DamageAreaEffect(int damageAmount) {
        this.damageAmount = damageAmount;
    }

    public int getDamageAmount() {
        return this.damageAmount;
    }

    public void affectUnit(Unit u) {
        u.takeDamage(this.damageAmount);
    }

}
