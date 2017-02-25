package game.gameboard;


public class Location {
    private int xIndex;
    private int yIndex;

    public Location(int xIndex, int yIndex) {
        this.xIndex = xIndex;
        this.yIndex = yIndex;
    }

    public int getX() {
        return this.xIndex;
    }

    public int getY() {
        return this.yIndex;
    }
}
