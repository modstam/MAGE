package se.tribestar.mage.examples;

import se.tribestar.mage.frontend.MageGame;
import se.tribestar.mage.world.World;

/**
 * Created by Andreas Stjerndal on 09-Jan-2015.
 */
public class ColoredCubeTest extends MageGame {

    @Override
    public void start() {
        world.addLogic(new SimpleCube(world));
    }
}
