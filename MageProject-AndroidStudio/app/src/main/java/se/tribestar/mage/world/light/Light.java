package se.tribestar.mage.world.light;

import se.tribestar.mage.math.Vector3;
import se.tribestar.mage.world.GameObject;
import se.tribestar.mage.world.drawable.visuals.Color;

/**
 * Created by modstam on 2015-01-03.
 */
public class Light extends GameObject {
    public float intensity;
    public Color ambientColor = new Color(0.1f,0.1f,0.1f,0.5f);
    public Color diffuseColor = new Color(0.5f,0.5f,0.5f,0.5f);
    public Color specularColor = new Color(0.2f,0.2f,0.2f,0.5f);
    public boolean isEnabled = true;

    public Light(){
        super();
    }
}
