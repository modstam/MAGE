package se.tribestar.mage.frontend.world;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import se.tribestar.mage.backend.gl.GLBackendController;
import se.tribestar.mage.backend.gl.GLWorld;
import se.tribestar.mage.frontend.logic.Logic;
import se.tribestar.mage.frontend.world.drawable.Drawable;
import se.tribestar.mage.frontend.world.light.Light;
import se.tribestar.mage.frontend.world.viewport.ViewPort;

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
    public ArrayList<Light> lights;
    public ArrayList<ViewPort> viewPorts;
    public HashMap<String, List<GameObject>> namedObjects;

    public float deltaTime; // time since the last frame
    public boolean running;
    public GLBackendController controller;

    public World(GLBackendController controller ){
        super(controller);
        objects = new ArrayList<GameObject>();
        logics = new ArrayList<Logic>();
        namedObjects = new HashMap<String, List<GameObject>>();
        lights = new ArrayList<Light>();
        viewPorts = new ArrayList<ViewPort>();
        drawables = new ArrayList<Drawable>();
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
        controller.preRender(lights, viewPorts);

        for(Drawable object : drawables) {
            controller.render(object);
        }

        controller.postRender(lights, viewPorts);
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

    /**
     * Use this method to register a new logic-class to the world object.
     * The world object will then call its update method every frame
     * @param logic
     */
    public void addLogic(Logic logic){
        logics.add(logic);
    }

    /**
     * Register a light to the world object.
     * This light will then be rendered in the scene.
     * There can only be a maximum of 8 lights active
     * at any given time in the scene due to OpenGL ES 1.0 limitations,
     * if you have more than 8 lights, only the first 8 will be displayed.
     * Use removeLight method to remove unwanted lights.
     * @param light
     * @return Will return true or false depending on success
     */
    public boolean addLight(Light light) {
        if(lights.size() >= 8){
            return false;
        }
        lights.add(light);
        return true;
    }

    /**
     * Remove a light from the scene.
     * @param light
     * @return Will return true or false depending on success
     */
    public boolean removeLight(Light light){
        for(int i = 0; i<lights.size(); i++){
            if(light.id.compareTo(lights.get(i).id) == 0){
                lights.remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Register a camera to the world object.
     * This camera will then be used as the viewport of the game.
     * Note that this engine currently only supports one active camera
     * at a time and will therefor pick the first registered camera
     * that is set enabled as viewport, use the isEnabled member
     * to switch a camera on or off.
     * @param viewPort
     * @return
     */
    public boolean addCamera(ViewPort viewPort){
        viewPorts.add(viewPort);
        return true;
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
