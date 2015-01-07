package se.tribestar.mage.world;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import se.tribestar.mage.backend.gl.GLBackendController;
import se.tribestar.mage.backend.gl.GLWorld;
import se.tribestar.mage.backend.gl.Vertices;
import se.tribestar.mage.backend.gl.Vertices3;
import se.tribestar.mage.logic.Logic;
import se.tribestar.mage.world.drawable.Cube;
import se.tribestar.mage.world.drawable.Drawable;
import se.tribestar.mage.world.drawable.Mesh;
import se.tribestar.mage.world.drawable.Sphere;

/**
 * Created by modstam on 2015-01-03.
 *
 * This class is responsible for running the game world
 * It will update and draw all game and logic objects that are in the world
 */
public class World extends GLWorld{
    public ArrayList<GameObject> objects; //the game objects
    public ArrayList<Logic> logics; //the logic objects
    public ArrayList<Drawable> drawables;
    public HashMap<String, List<GameObject>> namedObjects;

    public float deltaTime; // time since the last frame
    public boolean running;
    public GLBackendController controller;

    public World(GLBackendController controller ){
        super(controller);
        objects = new ArrayList<GameObject>();
        logics = new ArrayList<Logic>();
        namedObjects = new HashMap<String, List<GameObject>>();
        this.controller = controller;
        running = true;
    }

    /**
     * This method is responsible for updating all the objects
     * in the world, one run of this method corresponds to one frame
     * @return the time it took to complete the frame
     */
    @Override
    public void update(float deltaTime){
        this.deltaTime = deltaTime;

        for(GameObject object : objects) {
            object.update(deltaTime);
        }

        for(Logic logic : logics){
            logic.update(deltaTime);
        }
    }

    /**
     * This method is responsible to draw all objects in the frame
     */
    @Override
    public void draw(float deltaTime){
        for(Drawable object : drawables) {
            controller.render(object);
        }
    }

    /**
     * This method registers a game object to
     * the world object, there by notifying the world
     * that this object needs to be updated/drawn
     * @param object to add
     */
    public void addObject(GameObject object){
        if(!namedObjects.containsKey(object.name)){
            namedObjects.put(object.name, new ArrayList<GameObject>());
            namedObjects.get(object.name).add(object);
        }
        if(object instanceof Drawable){
            drawables.add((Drawable) object);
            controller.loadObject((Drawable) object);
        }
        objects.add(object);
    }

    public void addLogic(Logic logic){
        logics.add(logic);
    }

    /**
     * This method will return all game objects of a certain type
     * @param type - the type of object that should be found
     * @return a list with the game objects of the given type
     */
    public List<GameObject> findObjectsOfType(Class<? extends GameObject> type){
        ArrayList<GameObject> returnList = new ArrayList<GameObject>();

        for(GameObject object : objects){
            if(object.getClass() == type){
                returnList.add(object);
            }
        }
        return returnList;
    }

    /**
     * This method returns all objects registered to the
     * world by the given name.
     * @param name - the name to search for
     * @return a list of the objects with names mathing the parameter
     * or an empty list if none were found
     */
    public List<GameObject> findObjectsByName(String name){
        if(namedObjects.containsKey(name)){
            return namedObjects.get(name);
        }
        return new ArrayList<GameObject>();
    }


    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
