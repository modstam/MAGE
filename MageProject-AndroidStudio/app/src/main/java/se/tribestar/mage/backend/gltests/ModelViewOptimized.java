package se.tribestar.mage.backend.gltests;

import javax.microedition.khronos.opengles.GL10;

import se.tribestar.mage.backend.BackendController;
import se.tribestar.mage.backend.gl.FPSCounter;
import se.tribestar.mage.backend.gl.GLBackendController;
import se.tribestar.mage.backend.gl.GLGraphics;
import se.tribestar.mage.backend.gl.Texture;
import se.tribestar.mage.backend.gl.Vertices;

/**
 * Demonstrating how to use model and world space to move 100 gameobjects.
 *
 * Created by Andreas Stjerndal on 05-Jan-2015.
 */
public class ModelViewOptimized {
    private final String TEXTURE_FILENAME = "test.png";
    static final int NUM_GAMEOBJECTS = 100;
    GLGraphics glGraphics;
    Texture gameObjectTexture;
    Vertices gameObjectModel;
    GameObjectTest[] gameObjects;
    FPSCounter fpsCounter;
    BackendController controller;

    public ModelViewOptimized(BackendController controller) {
        this.controller = controller;

        glGraphics = ((GLBackendController)controller).getGLGraphics();

        gameObjectTexture = new Texture((GLBackendController)controller, TEXTURE_FILENAME);

        gameObjectModel = new Vertices(glGraphics, 4, 12, false, true);
        //asuming 2d object of dimensions 32x32,
        gameObjectModel.setVertices(new float[]{-16, -16, 0, 1,
                16, -16, 1, 1,
                16, 16, 1, 0,
                -16, 16, 0, 0,}, 0, 16);
        gameObjectModel.setIndices(new short[]{0, 1, 2, 2, 3, 0}, 0, 6);


        gameObjects = new GameObjectTest[100];
        for(int i = 0; i < 100; i++) {
            gameObjects[i] = new GameObjectTest();
        }

        fpsCounter = new FPSCounter();
    }

    public void update(float deltaTime) {
        controller.getInput().getTouchEvents();
        controller.getInput().getKeyEvents();

        for(int i = 0; i < NUM_GAMEOBJECTS; i++) {
            gameObjects[i].update(deltaTime);
        }
    }

    //@Override
    public void resume() {
        GL10 gl = glGraphics.getGL();
        gl.glViewport(0, 0, glGraphics.getWidth(), glGraphics.getHeight());
        gl.glClearColor(1, 0, 0, 1);
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrthof(0, 320, 0, 480, 1, -1);

        gl.glMatrixMode(GL10.GL_MODELVIEW);

        gameObjectTexture.reload();
        gl.glEnable(GL10.GL_TEXTURE_2D);
        gameObjectTexture.bind();
    }

    public void draw(float deltaTime) {
        GL10 gl = glGraphics.getGL();
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        gameObjectModel.bind();
        for(int i = 0; i < NUM_GAMEOBJECTS; i++) {
            gl.glLoadIdentity();
            gl.glTranslatef((int) gameObjects[i].x, (int) gameObjects[i].y, 0);
            gameObjectModel.draw(GL10.GL_TRIANGLES, 0, 6);
        }
        gameObjectModel.unbind();

        fpsCounter.logFrame();
    }

}
