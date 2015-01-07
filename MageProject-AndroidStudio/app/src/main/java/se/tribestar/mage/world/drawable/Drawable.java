package se.tribestar.mage.world.drawable;

import se.tribestar.mage.backend.gl.Texture;
import se.tribestar.mage.backend.gl.Vertices;
import se.tribestar.mage.world.GameObject;
import se.tribestar.mage.world.drawable.visuals.Color;
import se.tribestar.mage.world.drawable.visuals.Material;

/**
 * Created by modstam on 2015-01-03.
 */
public class Drawable extends GameObject {
    public String type;
    public Material material;
    public Color color;

    public boolean draw(){

        return false;
    }
}
