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
    @Override
    public int hashCode() {
        return (100 * xIndex) + yIndex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Location)) return false;
        Location loc = (Location) o;
        return xIndex == loc.xIndex && yIndex == loc.yIndex;
    }

    public Location directionLocation(int direction) {
        //TODO Calculate new location based off direction
        return this;
    }
}