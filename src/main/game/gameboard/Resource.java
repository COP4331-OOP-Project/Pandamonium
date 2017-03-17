package game.gameboard;

public class Resource {
    private int food;
    private int mine;
    private int wood;

    public Resource(int food, int mine, int wood){
        this.food = food;
        this.mine = mine;
        this.wood = wood;
    }

    public int getFood(){
        return food;
    }

    public int getMine(){
        return mine;
    }

    public int getWood(){
        return wood;
    }

    public void setFood(int food){
        this.food = food;
    }

    public void setMine(int mine){
        this.mine = mine;
    }

    public void setWood(int wood){
        this.wood = wood;
    }
}
