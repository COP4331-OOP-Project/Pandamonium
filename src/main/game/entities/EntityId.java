package game.entities;

public class EntityId implements Comparable<EntityId>  {
    private int playerId;
    private EntityTypeEnum typeId;
    private Enum subTypeId;
    private int instanceId;

    public EntityId(int playerId, EntityTypeEnum typeId, Enum subTypeId, int instanceId){
        this.playerId = playerId;
        this.typeId = typeId;
        this.subTypeId = subTypeId;
        this.instanceId = instanceId;
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

    //If entityId equals, return 1, else return 0;
    public int compareTo(EntityId entityId){
        if(this.playerId == entityId.getPlayerId()
                &&this.instanceId==entityId.getInstanceId()
                &&this.subTypeId == entityId.getSubTypeId()
                &&this.typeId == entityId.getTypeId())
            return 1;
        else
            return 0;
    }
}
