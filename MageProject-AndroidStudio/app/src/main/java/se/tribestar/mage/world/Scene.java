package se.tribestar.mage.world;

/**
 * Created by modstam on 2015-01-03.
 */
public class Scene {
    public String name;
    public World world;

    public Scene(String name){
        this.name = name;
        //this.world = new World();
    }

    public World getWorld(){
        return this.world;
    }

    /*public void start(){
        world.run();
    }*/
}
