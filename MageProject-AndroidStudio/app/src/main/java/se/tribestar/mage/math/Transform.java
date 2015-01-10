package se.tribestar.mage.math;

/**
 * A transform is a representation of an objects
 * physical presence in the scene, it contains a
 * position, rotation and scale vector.
 * Created by modstam on 2015-01-03.
 */
public class Transform {
    public Vector3 position;
    public Vector3 rotation;
    public Vector3 scale;

    public Transform(){
        position = new Vector3(0,0,0);
        rotation = new Vector3(0,0,0);
        scale = new Vector3(1,1,1);
    }

}
