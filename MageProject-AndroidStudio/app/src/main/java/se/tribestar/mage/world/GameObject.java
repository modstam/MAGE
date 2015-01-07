package se.tribestar.mage.world;

import java.util.Random;
import java.util.UUID;

import se.tribestar.mage.math.Transform;

/**
 * Created by modstam on 2015-01-03.
 */
public class GameObject {
    public float deltaTime;
    public Transform transform;
    public World world;
    public String name;
    public UUID id;
    public boolean isDestroyed;

    public GameObject(){
        Random r = new Random();
        transform = new Transform();
        name = " ";
        id = new UUID(r.nextLong(), r.nextLong());
        isDestroyed = false;
    }

    public void update(float deltaTime){

    }

    public void destroy(){
        isDestroyed = true;
    }
}
