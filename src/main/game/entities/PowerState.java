package game.entities;

/**
 * Enum to handle state of entities w/ associated upkeep values
 */
public enum PowerState {

    POWERED_UP(1.0f),              // Powered up state (upkeep 100% base)
    POWERED_DOWN(0.25f),           // Powered down state (upkeep 25% base)
    STANDBY(0.75f),                // Standby state (upkeep 75% base)
    COMBAT(1.25f);                  // Combat state (upkeep 125% base)

    private float upkeepModifier;           // Set value for enum type

    // Constructor to set enum values
    PowerState(float upkeep) {
        this.upkeepModifier = upkeep;
    }

    // Return upkeep value for this state
    public float getUpkeepModifier() {
        return upkeepModifier;
    }

}
