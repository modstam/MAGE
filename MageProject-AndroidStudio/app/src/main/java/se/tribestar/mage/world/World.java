package se.tribestar.mage.world;

import java.util.ArrayList;

import se.tribestar.mage.logic.Logic;

/**
 * Created by modstam on 2015-01-03.
 */
public class World {
    public ArrayList<GameObject> objects;
    public ArrayList<Logic> logics;
    public float deltaTime;

    public float update(){

        return deltaTime;
    }

    public void addObject(GameObject object){
        objects.add(object);
    }

    public void addLogic(Logic logic){
        logics.add(logic);
    }
}
