package se.tribestar.mage.examples;

import android.view.View;

import se.tribestar.mage.frontend.MageGame;
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
public class SimpleCube extends Logic {

    float angle = 20f;
    float scale = 1f;
    Cube cube;

    public SimpleCube(World world){
        super(world);

        //You always need a camera in the scene
        Camera camera = new Camera();
        //We'll position it at 0,1,3
        camera.transform.position = new Vector3(0,1,3);
        //Lets make it look at 0,0,0
        camera.lookAt = new Vector3(0,0,0);
        //Register the camera as our viewport for the world
        world.addCamera(camera);

        //Add some objects to be shown
        cube = new Cube();
        //Setup positions and rotations
        cube.transform.position = new Vector3(0,0,0);
        cube.transform.rotation = new Vector3(0,0,0);
        //Change color if you want
        cube.setColor(Color.RED);
        //Register the object to be drawn in the world
        world.addObject(cube);

        //Scenes can be lit or unlit, lets add a light
        DirectionalLight dLight = new DirectionalLight();
        dLight.ambientColor = new Color(1,1,1,1);
        dLight.diffuseColor = new Color(1,1,1,1);
        dLight.direction = new Vector3(1,0,0);
        //register the light to be illuminating our world
        world.addLight(dLight);
    }

    @Override
    public void update(float deltaTime){
        //We can use the update method to manipulate our object's properties every frame
        cube.transform.rotation.x += angle*deltaTime; //multiply by deltatime to ensure smoothness
        cube.transform.rotation.y += angle*deltaTime;
        cube.transform.scale.x = (float) Math.sin(scale);
        cube.transform.scale.y = (float) Math.sin(scale);
        cube.transform.scale.z = (float) Math.sin(scale);
        scale += deltaTime;
    }
}
