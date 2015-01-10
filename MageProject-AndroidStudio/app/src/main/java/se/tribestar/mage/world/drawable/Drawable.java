package se.tribestar.mage.world.drawable;

import se.tribestar.mage.backend.gl.Texture;
import se.tribestar.mage.backend.gl.Vertices;
import se.tribestar.mage.world.GameObject;
import se.tribestar.mage.world.drawable.visuals.Color;
import se.tribestar.mage.world.drawable.visuals.Material;

/**
 * A drawable is an object that can be drawn (rendered) in the scene.
 * All objects that should be visible in the scene must extend drawable.
 * Created by modstam on 2015-01-03.
 */
public class Drawable extends GameObject {
    public String type;
    public Material material = new Material(); //Every drawable has a material
    public String texturePath;
    public Color color;
    public boolean hasColors = true;
    public boolean hasTexture = false;
    public boolean hasNormals = true;
    public boolean draw;

    public Drawable(){
        super();
        draw = true;
    }

    public boolean draw(){

        return false;
    }

    /**
     * Set a new RGBA color
     * @param color
     */
    public void setColor(Color color) {
        this.color = color;
        material.setColors(color);
    }

    /**
     * Set a new RGBA color
     * @param r
     * @param g
     * @param b
     * @param a
     */
    public void setColor(float r, float g, float b, float a) {
        this.color = new Color(r, g, b, a);
        material.setColors(color);
    }

    /**
     * Set the path to the texture file that will be used as a texture
     * @param texturePath
     */
    public void setTexturePath(String texturePath) {
        if(!texturePath.isEmpty())
            hasTexture = true;
        else
            hasTexture = false;
        this.texturePath = texturePath;
    }

    public boolean hasColors(){
        return hasColors;
    }

    public boolean hasTexture(){
        return hasTexture;
    }

    public boolean hasNormals(){
        return hasNormals;
    }

    public String getTexturePath() {
        return texturePath;
    }
}
