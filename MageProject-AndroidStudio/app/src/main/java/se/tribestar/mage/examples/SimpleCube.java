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

    float angle = 20f;
    float scale = 1f;
    Cube cube;

    public SimpleCube(World world){
        super(world);

        cube = new Cube();
        cube.transform.position = new Vector3(0,0,0);
        cube.transform.rotation = new Vector3(0,0,0);
        cube.setColor(Color.ORANGE);
        world.addObject(cube);

        DirectionalLight dLight = new DirectionalLight();
        dLight.ambientColor = new Color(1,1,1,1);
        dLight.diffuseColor = new Color(1,1,1,1);
        dLight.direction = new Vector3(1,0,0);
        world.addLight(dLight);
    }

    @Override
    public void update(float deltaTime){
        cube.transform.rotation.x += angle*deltaTime;
        cube.transform.rotation.y += angle*deltaTime;
        cube.transform.scale.x = (float) Math.sin(scale);
        cube.transform.scale.y = (float) Math.sin(scale);
        cube.transform.scale.z = (float) Math.sin(scale);
        scale += deltaTime;
    }
}
