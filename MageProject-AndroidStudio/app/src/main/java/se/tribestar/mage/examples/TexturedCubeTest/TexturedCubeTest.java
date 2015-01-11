package se.tribestar.mage.examples.TexturedCubeTest;

import se.tribestar.mage.examples.ColoredCubeTest.SimpleCube;
import se.tribestar.mage.frontend.MageGame;

/**
 * Created by Andreas Stjerndal on 09-Jan-2015.
 */
public class TexturedCubeTest extends MageGame {

    @Override
    public void start() {
        //For overriding.
        world.addLogic(new TexturedCube(world));
    }

    @Override
    public void resume() {
        //For overriding.
        world.addLogic(new SimpleCube(world));
    }
}
