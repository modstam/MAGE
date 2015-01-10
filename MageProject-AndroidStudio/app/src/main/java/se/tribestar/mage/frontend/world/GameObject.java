package se.tribestar.mage.frontend.world;

import java.util.Random;
import java.util.UUID;

import se.tribestar.mage.frontend.math.Transform;

/**
 * Created by modstam on 2015-01-03.
 */
public class GameObject {
    public float deltaTime; // time since last frame
    public Transform transform; // Every object in the scene must have a transform
    public World world; //a pointer to the world
    public String name; // the name of the object
    public UUID id; // Every object has a unique ID
    public boolean isDestroyed; //Signifies if this object is ready to be removed from the scene

    public GameObject(){
        Random r = new Random();
        transform = new Transform();
        name = " ";
        id = new UUID(r.nextLong(), r.nextLong());
        isDestroyed = false;
    }

    /**
     * Update will be called once a frame by the world-object
     * @param deltaTime
     */
    public void update(float deltaTime){

    }

    /**
     * Calling this method will tell the world that the
     * object is ready to be removed from the scene,
     * this will happen on the next frame after the call
     */
    public void destroy(){
        isDestroyed = true;
    }
}
