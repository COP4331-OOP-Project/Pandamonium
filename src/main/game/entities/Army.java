package game.entities;

public class Army {
    private EntityId entityId;
    private BattleGroup battleGroup;
    private Reinforcement reinforcement;
    private RallyPoint rallyPoint;

    public Army(EntityId entityId, RallyPoint rp){
        this.entityId=entityId;
        battleGroup = new BattleGroup();
        reinforcement = new Reinforcement();
        rallyPoint=rp;
    }

}
