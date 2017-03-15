package game.commands;

public enum SubCommandEnum {

    // Make Subcommands
    CREATE_MELEE("Create Melee Unit"),
    CREATE_COLONIST("Create Colonist Unit"),
    CREATE_RANGED("Create Ranged Unit"),
    CREATE_EXPLORER("Create Explorer Unit"),
    CREATE_WORKER("Create Worker"),
    BUILD_FORT("Build Fort"),
    BUILD_CAPITOL("Build Capitol"),
    BUILD_OBSERVER("Build Observation Tower"),
    BUILD_UNIVERSITY("Build University"),
    BUILD_MINE("Build Mine"),
    BUILD_POWER_PLANT("Build Power Plant"),
    BUILD_FARM ("Build Farm"),

    // Worker Assignmnet
    ASSIGN_MINER ("Assign As Miner"),
    ASSIGN_GENERATOR ("Assign As Generator"),
    ASSIGN_FARMER ("Assign As Farmer"),
    ASSIGN_BREEDER ("Assign As Breeder"),
    ASSIGN_SOLDIER_GENERATOR ("Assign As Soldier Generator"),
    ASSIGN_RESEARCHER ("Assign As Researcher"),
    ASSIGN_INACTIVE_WORKER ("Set Worker Inactive");

    private final String description;

    private SubCommandEnum(final String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return this.description;
    }

}
