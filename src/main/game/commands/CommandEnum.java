package game.commands;

// Enumeration of command types
public enum CommandEnum {
    //Rally Point Only
    MOVE_RALLY_POINT,
	
	//All Entities
	DECOMMISSION,
    POWER_UP,
    POWER_DOWN,
    CANCEL_QUEUE,

    //Units Only
    MOVE,
    REINFORCE_ARMY,
    
    //Forts and Battle Group
    ATTACK,
    
    //Fort Only
    CREATE_SOLDIERS,
    
    //All Structures Except Observation Tower
    ASSIGN_WORKER,
    UNASSIGN_ALL_WORKERS,
    
    //All Structures
    DEFEND,
    
    //Capitol
    HEAL,
    
    //Colonist
	FOUND_CAPITOL,
    
    //Explorer Only
    START_PROSPECTING,
    STOP_PROSPECTING,
    
    //Worker Actions
    BREED_WORKERS,
    WORKER_FARM,
    WORKER_MINE,
    WORKER_GENERATE,
    
    //Battle Group
    DROP_OFF_WORKER,
    PICK_UP_WORKER,
    BUILD_STRUCTURE,
    DISBAND_ARMY,
}
