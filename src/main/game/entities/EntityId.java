package game.entities;

public class EntityId implements Comparable<EntityId>  {
    private int playerId;
    private EntityTypeEnum typeId;
    private Enum subTypeId;
    private int instanceId;
    private int globalTypeId;

    public EntityId(int playerId, EntityTypeEnum typeId, Enum subTypeId, int instanceId, int globalTypeId){
        this.playerId = playerId;
        this.typeId = typeId;
        this.subTypeId = subTypeId;
        this.instanceId = instanceId;
        this.globalTypeId = globalTypeId;
    }

    public int getPlayerId(){
        return playerId;
    }

    public EntityTypeEnum getTypeId(){
        return typeId;
    }

    public Enum getSubTypeId(){
        return subTypeId;
    }

    public int getInstanceId(){
        return this.instanceId;
    }

    public int getGlobalTypeId() { return this.globalTypeId; }

    //If entityId equals, return 1, else return 0;
    public int compareTo(EntityId entityId){
        if(this.playerId == entityId.getPlayerId()
                &&this.instanceId==entityId.getInstanceId()
                &&this.subTypeId == entityId.getSubTypeId()
                &&this.typeId == entityId.getTypeId()
                &&this.globalTypeId == entityId.getGlobalTypeId())
            return 1;
        else
            return 0;
    }
}
