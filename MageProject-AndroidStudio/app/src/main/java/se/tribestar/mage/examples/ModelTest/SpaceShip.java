package se.tribestar.mage.examples.ModelTest;

import android.text.method.Touch;

import java.util.List;

import se.tribestar.mage.backend.Input;
import se.tribestar.mage.frontend.logic.Logic;
import se.tribestar.mage.frontend.math.Vector3;
import se.tribestar.mage.frontend.util.Log;
import se.tribestar.mage.frontend.world.World;
import se.tribestar.mage.frontend.world.drawable.Mesh;
import se.tribestar.mage.frontend.world.drawable.visuals.Color;
import se.tribestar.mage.frontend.world.light.DirectionalLight;
import se.tribestar.mage.frontend.world.viewport.Camera;

/**
 * Created by Andreas Stjerndal on 09-Jan-2015.
 */
public class SpaceShip extends Logic {

    float angle = 30f;
    float scale = 1f;
//    Cube cube;
    Mesh mesh;

    Input input;
    boolean hasInput = false;

    public SpaceShip(World world){
        super(world);

        //You always need a camera in the scene
        Camera camera = new Camera();
        //We'll position it at 0,1,3
        camera.transform.position = new Vector3(0,1,3);
        //Lets make it look at 0,0,0
        camera.lookAt = new Vector3(0,0,0);
        //Register the camera as our viewport for the world
        world.addCamera(camera);

        mesh = new Mesh();
        mesh.transform.position = new Vector3(0,0,0);
        mesh.transform.rotation = new Vector3(0,0,0);
        mesh.setColor(Color.WHITE);
        mesh.setTexturePath("ship.png");
        mesh.filename = "ship.obj";
        world.addObject(mesh);

        DirectionalLight dLight = new DirectionalLight();
        dLight.ambientColor = new Color(1,1,1,1);
        dLight.diffuseColor = new Color(1,1,1,1);
        dLight.direction = new Vector3(1,0,0);
        world.addLight(dLight);
    }

    public void setInput(Input input) {
        this.hasInput = true;
        this.input = input;
    }

    @Override
    public void update(float deltaTime){
        if(hasInput) {
            if(input.isTouchDown(0))
                mesh.transform.rotation.x += angle * deltaTime;
            if(input.isTouchDown(1))
                mesh.transform.rotation.y += angle * deltaTime;
            if(input.isTouchDown(2))
                mesh.transform.rotation.z += angle * deltaTime;
        }
    }
}
