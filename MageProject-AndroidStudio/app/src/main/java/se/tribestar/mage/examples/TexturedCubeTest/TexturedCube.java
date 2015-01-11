package se.tribestar.mage.examples.TexturedCubeTest;

import se.tribestar.mage.frontend.logic.Logic;
import se.tribestar.mage.frontend.math.Vector3;
import se.tribestar.mage.frontend.world.World;
import se.tribestar.mage.frontend.world.drawable.Cube;
import se.tribestar.mage.frontend.world.drawable.visuals.Color;
import se.tribestar.mage.frontend.world.light.DirectionalLight;
import se.tribestar.mage.frontend.world.viewport.Camera;

/**
 * Created by modstam on 2015-01-07.
 */
public class TexturedCube extends Logic {

    float angle = 20f;
    float speedX = 1f;
    float speedY = speedX;
    float limitX = 2.5f;
    float limitY = 4.5f;
    float speedUp = 1.08f;
    float anglespeedUp = 1.15f;
    Cube cube;

    public TexturedCube(World world){
        super(world);

        //See the "SimpleCube.java" class for additional info

        Camera camera = new Camera();
        camera.transform.position = new Vector3(0,0,3);
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
        cube.transform.position.x += speedX*deltaTime;
        cube.transform.position.y += speedY*deltaTime;
        if(cube.transform.position.x > limitX) {
            cube.transform.position.x = limitX;
            speedX *= -speedUp;
            angle *= anglespeedUp;
        } else if(cube.transform.position.x < -limitX) {
            cube.transform.position.x = -limitX;
            speedX *= -speedUp;
            angle *= anglespeedUp;
        }

        if(cube.transform.position.y > limitY) {
            cube.transform.position.y = limitY;
            speedY *= -speedUp;
            angle *= anglespeedUp;
        } else if(cube.transform.position.y < -limitY) {
            cube.transform.position.y = -limitY;
            speedY *= -speedUp;
            angle *= anglespeedUp;
        }

    }
}
