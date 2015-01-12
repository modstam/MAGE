package se.tribestar.mage.backend;

import se.tribestar.mage.backend.gl.GLWorld;
import se.tribestar.mage.frontend.world.drawable.Drawable;

/**
 * Interface for a backend controller.
 * Created by Andreas Stjerndal on 04-Jan-2015.
 *
 * * from the book "Beginning Android Games", by Mario Zechner
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

    public void loadObject(Drawable drawable);
}
