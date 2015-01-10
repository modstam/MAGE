package se.tribestar.mage.examples;

import se.tribestar.mage.logic.Logic;
import se.tribestar.mage.math.Vector3;
import se.tribestar.mage.world.World;
import se.tribestar.mage.world.drawable.Cube;
import se.tribestar.mage.world.drawable.visuals.Color;
import se.tribestar.mage.world.light.DirectionalLight;
import se.tribestar.mage.world.viewport.Camera;

/**
 * Created by modstam on 2015-01-07.
 */
public class TexturedCube extends Logic {

    float angle = 20f;
    Cube cube;

    public TexturedCube(World world){
        super(world);

        //See the "SimpleCube.java" class for additional info

        Camera camera = new Camera();
        camera.transform.position = new Vector3(0,1,3);
        camera.lookAt = new Vector3(0,0,0);
        world.addCamera(camera);

        cube = new Cube();
        cube.transform.position = new Vector3(0,0,-5);
        cube.transform.rotation = new Vector3(0,0,0);
        //We tell the renderer that we want to use this texture file to texture this object
        cube.setTexturePath("crate.png");
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
    }
}
