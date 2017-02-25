package game.entities;

public class EntityId {
    private int playerId;
    private char typeId;
    private Enum subTypeId;
    private int instanceId;

    public EntityId(int playerId, char typeId, Enum subTypeId, int instanceId){
        this.playerId = playerId;
        this.typeId = typeId;
        this.subTypeId = subTypeId;
        this.instanceId = instanceId;
    }

    public int getPlayerId(){
        return playerId;
    }

    public char getTypeId(){
        return typeId;
    }

    public Enum getSubTypeId(){
        return subTypeId;
    }

    public int getInstanceId(){
        return getInstanceId();
    }
}
