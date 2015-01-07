package se.tribestar.mage.world;

import se.tribestar.mage.math.Transform;

/**
 * Created by modstam on 2015-01-03.
 */
public class GameObject {
    public float deltaTime;
    public Transform transform;
    public World world;
    public String name = " ";
    public String id;
    public boolean draw;

    public void update(float deltaTime){

    }

}
