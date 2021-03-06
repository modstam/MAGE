package se.tribestar.mage.backend.gl;

import se.tribestar.mage.backend.gl.GLBackendController;

/**
 * A user of GLBackendController should extend this class,
 * in order to get notified when Android events such as resume and pause happens.
 *
 * Created by Andreas Stjerndal on 06-Jan-2015.
 */
public abstract class GLWorld {
    protected final GLBackendController controller;

    public GLWorld(GLBackendController controller) {
        this.controller = controller;
    }

    public abstract void update(float deltaTime);

    public abstract void draw(float deltaTime);

    public abstract void pause();

    public abstract void resume();

    public abstract void dispose();
}
