package se.tribestar.mage.examples;

import se.tribestar.mage.frontend.MageGame;
import se.tribestar.mage.logic.Logic;
import se.tribestar.mage.math.Vector3;
import se.tribestar.mage.world.World;
import se.tribestar.mage.world.drawable.Cube;
import se.tribestar.mage.world.drawable.visuals.Color;
import se.tribestar.mage.world.light.DirectionalLight;

/**
 * Created by modstam on 2015-01-07.
 */
public class SimpleCube extends Logic {

    public SimpleCube(World world){
        super(world);

        Cube cube = new Cube();
        cube.transform.position = new Vector3(0,0,0);
        cube.transform.rotation = new Vector3(0,0,0);
        world.addObject(cube);

        DirectionalLight dLight = new DirectionalLight();
        dLight.ambientColor = new Color(1,1,1,1);
        world.addLight(dLight);
    }
}
