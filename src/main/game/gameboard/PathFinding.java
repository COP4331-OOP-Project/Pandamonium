package game.gameboard;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class PathFinding {
    private Gameboard gameboard;

    public PathFinding(Gameboard gb){
        gameboard=gb;
    }

    public int pathAlgorithm(Location start, Location end, int playerId){
        //Initialization
        Set<Location> closed = new HashSet<>();
        Set<Location> open = new HashSet<>();
        HashMap<Location, Location> cameFrom = new HashMap<>();
        HashMap<Location, Integer> g = new HashMap<>();
        HashMap<Location, Integer> f = new HashMap<>();
        open.add(start);
        g.put(start,0);
        f.put(start,hscore(start,end));

        //Begin Algorithm

        while(!open.isEmpty()) {
            Location current = smallestF(f, closed);
            if (current.equals(end)) {
                return getNextDirection(cameFrom, start, end);
            }
            open.remove(current);
            closed.add(current);
            //Check each neighbor
            int direction = 0;
            while(direction!=-1) {
                int directionHold=direction;
                switch (direction){
                    case 0:
                        direction=45;
                        break;
                    case 45:
                        direction=135;
                        break;
                    case 135:
                        direction=180;
                        break;
                    case 180:
                        direction=225;
                        break;
                    case 225:
                        direction=315;
                        break;
                    case 315:
                        direction=-1;
                        break;
                    default:
                        direction=-1;
                }
                Location neighbor = directionLocation(current,directionHold);
                if(!g.containsKey(neighbor)) {
                    g.put(neighbor,10000);
                }
                if (!closed.contains(neighbor)) {
                    //TODO check if tile is player id;
                    if(!gameboard.getTiles()[neighbor.getX()][neighbor.getY()].isImpassable()) {
                        if (!open.contains(neighbor)) {
                            open.add(neighbor);
                        }
                        else if(g.get(current) + 1 >= g.get(neighbor)) {
                            continue;
                        }
                        cameFrom.put(neighbor, current);
                        g.put(neighbor, g.get(current) + 1);
                        f.put(neighbor, g.get(neighbor) + hscore(neighbor, end));
                    }
                }
                else{
                    closed.add(neighbor);
                }
            }
        }
        //Path not found
        return -1;
    }

    public Location smallestF(HashMap<Location, Integer> f, Set<Location> closed){
        Set<Location> key = f.keySet();
        Location smallest = null;
        int compare = 10000;
        Iterator<Location> iterator = key.iterator();
        while(iterator.hasNext()){
            Location holder = iterator.next();
            if(!closed.contains(holder)) {
                if (f.get(holder) < compare) {
                    smallest = holder;
                    compare = f.get(holder);
                }
            }
        }
        return smallest;
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
            default:
                return null;
        }
    }

    public int hscore(Location current, Location end){
        int distance = 0;
        while(!current.equals(end)){
            current=nextBestLoc(current, end);
            distance++;
        }
        return distance;
    }

    public Location nextBestLoc(Location current, Location end){
        Location next=null;
        int compare = 10000;
        int direction = 0;
        while(direction!=-1) {
            Location neighbor = directionLocation(current,direction);
            if(howClose(neighbor,end)<compare){
                compare=howClose(neighbor,end);
                next=neighbor;
            }

            switch (direction){
                case 0:
                    direction=45;
                    break;
                case 45:
                    direction=135;
                    break;
                case 135:
                    direction=180;
                    break;
                case 180:
                    direction=225;
                    break;
                case 225:
                    direction=315;
                    break;
                case 315:
                    direction=-1;
                    break;
                default:
                    direction=-1;
            }
        }
        return next;
    }

    public int getNextDirection(HashMap<Location,Location> cameFrom, Location start, Location end){
        Location current=end;
        Location goesTo=current;
        while(!current.equals(start)){
            goesTo=current;
            current=cameFrom.get(current);
        }
        if(goesTo.equals(directionLocation(current,0))){
            return 0;
        }
        else if(goesTo.equals(directionLocation(current,45))){
            return 45;
        }
        else if(goesTo.equals(directionLocation(current,135))){
            return 135;
        }
        else if(goesTo.equals(directionLocation(current,180))){
            return 180;
        }
        else if(goesTo.equals(directionLocation(current,225))){
            return 225;
        }
        else if(goesTo.equals(directionLocation(current,315))){
            return 315;
        }
        /**SOMETHING WRONG IF THIS IS RETURNED**/
        return -1;
    }
}
