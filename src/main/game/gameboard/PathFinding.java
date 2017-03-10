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
        return 0;
    }
}
