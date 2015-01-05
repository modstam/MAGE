package se.tribestar.mage.backend.gltests;

import javax.microedition.khronos.opengles.GL10;

import se.tribestar.mage.backend.BackendController;
import se.tribestar.mage.backend.gl.GLBackendController;
import se.tribestar.mage.backend.gl.GLGraphics;
import se.tribestar.mage.backend.gl.Texture;
import se.tribestar.mage.backend.gl.Vertices;

/**
 * Demonstrating how to use model and world space to move 100 gameobjects.
 *
 * Created by Andreas Stjerndal on 05-Jan-2015.
 */
public class ModelViewTest {
    private final String TEXTURE_FILENAME = "test.png";
    static final int NUM_BOBS = 100;
    GLGraphics glGraphics;
    Texture bobTexture;
    Vertices gameObjectModel;
    GameObjectTest[] gameObjects;
    BackendController controller;

    public ModelViewTest(BackendController controller) {
        this.controller = controller;

        glGraphics = ((GLBackendController)controller).getGLGraphics();

        bobTexture = new Texture((GLBackendController)controller, TEXTURE_FILENAME);

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
    }

    public void update(float deltaTime) {
        controller.getInput().getTouchEvents();
        controller.getInput().getKeyEvents();

        for(int i = 0; i < NUM_BOBS; i++) {
            gameObjects[i].update(deltaTime);
        }
    }

    public void draw(float deltaTime) {
        GL10 gl = glGraphics.getGL();
        gl.glClearColor(1,0,0,1);
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrthof(0, 320, 0, 480, 1, -1);

        gl.glEnable(GL10.GL_TEXTURE_2D);
        bobTexture.bind();

        gl.glMatrixMode(GL10.GL_MODELVIEW);
        for(int i = 0; i < NUM_BOBS; i++) {
            gl.glLoadIdentity();
            gl.glTranslatef(gameObjects[i].x, gameObjects[i].y, 0);
            gl.glRotatef(45, 0, 0, 1);
            gl.glScalef(2, 0.5f, 0);
            gameObjectModel.draw(GL10.GL_TRIANGLES, 0, 6);
        }
    }
}
