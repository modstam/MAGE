package se.tribestar.mage.examples;

import se.tribestar.mage.frontend.logic.Logic;
import se.tribestar.mage.frontend.math.Vector3;
import se.tribestar.mage.frontend.world.World;
import se.tribestar.mage.frontend.world.drawable.Mesh;
import se.tribestar.mage.frontend.world.drawable.visuals.Color;
import se.tribestar.mage.frontend.world.light.DirectionalLight;

/**
 * Created by Andreas Stjerndal on 09-Jan-2015.
 */
public class SpaceShip extends Logic {

    float angle = 20f;
    float scale = 1f;
//    Cube cube;
    Mesh mesh;

    public SpaceShip(World world){
        super(world);

//        cube = new Cube();
//        cube.transform.position = new Vector3(0,0,0);
//        cube.transform.rotation = new Vector3(0,0,0);
//        cube.setColor(Color.ORANGE);
//        world.addObject(cube);

        mesh = new Mesh();
        mesh.transform.position = new Vector3(0,0,-5);
        mesh.transform.rotation = new Vector3(0,0,0);
        mesh.setColor(Color.ORANGE);
        mesh.setTexturePath("ship.png");
        mesh.filename = "ship.obj";
        world.addObject(mesh);

        DirectionalLight dLight = new DirectionalLight();
        dLight.ambientColor = new Color(1,1,1,1);
        dLight.diffuseColor = new Color(1,1,1,1);
        dLight.direction = new Vector3(1,0,0);
        world.addLight(dLight);
    }

    @Override
    public void update(float deltaTime){
        mesh.transform.rotation.x += angle*deltaTime;
        mesh.transform.rotation.y += angle*deltaTime;
    }
}
