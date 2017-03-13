package game.gameboard.areaEffect;

import java.util.ArrayList;

/**
 * Created by khariollivierre on 3/13/17.
 */

/*
TODO: Specify number of effects to place
TODO: Handle effect placement
TODO: Handle effect actions
 */

public class AreaEffectManager {
    private ArrayList<AreaEffect> poison;
    private ArrayList<AreaEffect> heals;
    private ArrayList<AreaEffect> death;
    private AreaEffectFactory factory;

    public AreaEffectManager(){
        poison = new ArrayList<>();
        heals = new ArrayList<>();
        death = new ArrayList<>();
    }

    public ArrayList<AreaEffect> getPoison() { return poison; }
    public ArrayList<AreaEffect> getHeals() { return heals; }
    public ArrayList<AreaEffect> getDeath() { return death; }
    public AreaEffectFactory getFactory() { return factory; }
}
