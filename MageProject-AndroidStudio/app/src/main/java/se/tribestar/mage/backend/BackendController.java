package se.tribestar.mage.backend;

import se.tribestar.mage.backend.gl.GLWorld;
import se.tribestar.mage.backend.gl.Vertices3;
import se.tribestar.mage.world.drawable.Drawable;

/**
 * Created by Andreas Stjerndal on 04-Jan-2015.
 */
public interface BackendController {
    public Input getInput();

    public FileIO getFileIO();

    public Graphics getGraphics();

    public Audio getAudio();

    public void setWorld(GLWorld world);

    public GLWorld getCurrentWorld();

    public GLWorld getWorld();

    public void render(Drawable drawable);

    public boolean loadObject(Drawable drawable);
}
