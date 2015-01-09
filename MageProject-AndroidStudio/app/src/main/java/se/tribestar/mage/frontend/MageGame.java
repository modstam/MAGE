package se.tribestar.mage.frontend;

import android.os.Bundle;

import se.tribestar.mage.backend.gl.GLBackendController;
import se.tribestar.mage.backend.gl.GLWorld;
import se.tribestar.mage.examples.SimpleCube;
import se.tribestar.mage.world.World;

/**
 * Users of the engine expands this class with the main class of their game.
 *
 * Created by Andreas Stjerndal on 07-Jan-2015.
 */
public class MageGame extends GLBackendController {
    public World world;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        start();


    }

    /**
     * Called at startup
     */
    public void start() {
      //For overriding.
    }

    @Override
    public GLWorld getWorld() {
        return world;
    }
}
