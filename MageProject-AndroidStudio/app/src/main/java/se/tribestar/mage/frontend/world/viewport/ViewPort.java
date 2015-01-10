package se.tribestar.mage.frontend.world.viewport;

import se.tribestar.mage.frontend.math.Vector3;
import se.tribestar.mage.frontend.world.GameObject;

/**
 * Created by modstam on 2015-01-03.
 */
public class ViewPort extends GameObject {
    public float width;
    public float height;
    public float depth;
    public boolean isEnabled; //Signifies whether a camera is active or not
    public float fieldOfView;
    public Vector3 lookAt; // A point in space where the camera will look at
    public boolean perspective; //True = Perspective view, false = Orthographic view

    public ViewPort(){
        super();
        isEnabled = true;
        fieldOfView = 67f;
        lookAt = new Vector3(0,0,0);
        perspective = true;
    }
}
