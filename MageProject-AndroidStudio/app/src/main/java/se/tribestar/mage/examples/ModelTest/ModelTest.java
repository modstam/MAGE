package se.tribestar.mage.examples.ModelTest;

import se.tribestar.mage.frontend.MageGame;

/**
 * Created by Andreas Stjerndal on 09-Jan-2015.
 */
public class ModelTest extends MageGame {

    @Override
    public void start() {
        //For overriding.
        world.addLogic(new SpaceShip(world));
    }
}
