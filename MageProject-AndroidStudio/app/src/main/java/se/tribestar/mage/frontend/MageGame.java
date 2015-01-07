package se.tribestar.mage.frontend;

import se.tribestar.mage.backend.gl.GLBackendController;
import se.tribestar.mage.backend.gl.GLWorld;
import se.tribestar.mage.world.World;

/**
 * Users of the engine expands this abstract class with the main class of their game.
 *
 * Created by Andreas Stjerndal on 07-Jan-2015.
 */
public abstract class MageGame {
    GLBackendController controller;
    World world;

    public MageGame() {
        controller = new GLBackendController() {
            @Override
            public GLWorld getWorld() {
                return world;
            }
        };
    }
}
