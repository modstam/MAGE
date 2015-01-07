package se.tribestar.mage.world.light;

import se.tribestar.mage.math.Vector3;

/**
 * Created by modstam on 2015-01-03.
 */
public class DirectionalLight extends Light {

    public Vector3 direction = new Vector3(1,0,0);

    public DirectionalLight(){
        super();
    }
}
