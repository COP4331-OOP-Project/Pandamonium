package game.gameboard;

import game.commands.Command;

import java.util.*;

public class PathFinding {
    private Gameboard gameboard;

    public Command pathAlgorithm(Location start, Location end){
        Set<Location> closed = new HashSet<>();
        Queue<Location> open = new LinkedList<>();
        HashMap<Location, Integer> g = new HashMap<>();
        HashMap<Location, Integer> f = new HashMap<>();

        return null;
    }

    public int estimateCost(Location start, Location end){
        Location current = start;

        return 0;
    }

    public int howClose(Location current, Location end){
        int x = Math.abs(current.getX()-end.getX());
        int y = Math.abs(current.getY()-end.getY());
        return x+y;
    }

    public Location directionLocation(Location current, int direction){
        switch (direction){
            case 0:
                return new Location(current.getX(), current.getY()-1);
            case 45:
                return new Location(current.getX()+1, current.getY()-1);
            case 135:
                return new Location(current.getX()+1, current.getY());
            case 180:
                return new Location(current.getX(), current.getY()+1);
            case 225:
                return new Location(current.getX()-1, current.getY()+1);
            case 315:
                return new Location(current.getX()-1, current.getY());
        }

        return null;
    }
}
