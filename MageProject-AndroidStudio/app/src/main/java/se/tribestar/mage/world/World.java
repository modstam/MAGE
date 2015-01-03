package se.tribestar.mage.world;

import java.util.ArrayList;

import se.tribestar.mage.logic.Logic;
import se.tribestar.mage.world.drawable.Drawable;

/**
 * Created by modstam on 2015-01-03.
 *
 * This class is responsible for running the game world
 * It will update and draw all game and logic objects that are in the world
 */
public class World {
    public ArrayList<GameObject> objects; //the game objects
    public ArrayList<Logic> logics; //the logic objects
    public float deltaTime; // time since the last frame
    public boolean running;

    public World(){
        objects = new ArrayList<GameObject>();
        logics = new ArrayList<Logic>();
        running = true;
    }

    /**
     * This method is responsible for the infinite loop
     */
    public void run(){
        while(running){
            update();
        }
    }

    /**
     * This method is responsible for updating all the objects
     * in the world, one run of this method corresponds to one frame
     * @return the time it took to complete the frame
     */
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
