package se.tribestar.mage.frontend.logic;

import se.tribestar.mage.frontend.world.World;

/**
 * A Logic-class is called every frame by the world-object
 * Extend this class and override the update-method to implement behaviour.
 * Created by modstam on 2015-01-03.
 */
public class Logic {
    public float deltaTime = 0.0f;
    public World world;

    public Logic(World world){
        this.world = world;
    }

    /**
     * This method will be called once every frame by the world object.
     * Extend this class and implement your game update-dependant logic in this method
     * @param deltaTime time since the last frame
     */
    public void update(float deltaTime){

    }
}
