package se.tribestar.mage.world.viewport;

import se.tribestar.mage.math.Vector3;
import se.tribestar.mage.world.GameObject;

/**
 * Created by modstam on 2015-01-03.
 */
public class ViewPort extends GameObject {
    public float width;
    public float height;
    public float depth;
    public boolean isEnabled;
    public float fieldOfView;
    public Vector3 lookAt;
    public boolean perspective;

    public ViewPort(){
        super();
        isEnabled = true;
        fieldOfView = 67f;
        lookAt = new Vector3(0,0,0);
        perspective = true;
    }
}
