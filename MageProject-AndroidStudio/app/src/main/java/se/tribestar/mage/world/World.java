package se.tribestar.mage.world;

import java.util.ArrayList;

import se.tribestar.mage.logic.Logic;
import se.tribestar.mage.world.drawable.Drawable;

/**
 * Created by modstam on 2015-01-03.
 */
public class World {
    public ArrayList<GameObject> objects;
    public ArrayList<Logic> logics;
    public float deltaTime;

    public float update(){
        float time = System.currentTimeMillis();

        for(GameObject object : objects){
            if(object instanceof Drawable){
                ((Drawable) object).draw();
            }
            else{
                object.update(deltaTime);
            }
        }

        for(Logic logic : logics){
            logic.update(deltaTime);
        }

        this.deltaTime = System.currentTimeMillis() - time;
        return deltaTime;
    }

    public void addObject(GameObject object){
        objects.add(object);
    }

    public void addLogic(Logic logic){
        logics.add(logic);
    }
}
