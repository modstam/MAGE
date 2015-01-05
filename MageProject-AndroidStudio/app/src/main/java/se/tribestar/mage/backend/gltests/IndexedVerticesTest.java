package se.tribestar.mage.backend.gltests;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

import se.tribestar.mage.backend.BackendController;
import se.tribestar.mage.backend.gl.GLBackendController;
import se.tribestar.mage.backend.gl.GLGraphics;
import se.tribestar.mage.backend.gl.Texture;
import se.tribestar.mage.backend.gl.Vertices;

/**
 * This class demonstrates how to do IndexTextureTest.java using the Vertices.java class.
 *
 * Created by Andreas Stjerndal on 04-Jan-2015.
 */
public class IndexedVerticesTest {
    private final String TEXTURE_FILENAME = "test.png";

    final int VERTEX_SIZE = (2 + 2) * 4;
    GLGraphics glGraphics;
    Vertices vertices;
    //ShortBuffer indices;
    Texture texture;

    public IndexedVerticesTest(BackendController controller) {

        Vertices vertices = new Vertices(glGraphics, 4, 6, false, true);
        vertices.setVertices(new float[]{100.0f, 100.0f, 0.0f, 1.0f,
                228.0f, 100.0f, 1.0f, 1.0f,
              //     x,      y,    u,    v, (texture coords u,v)
                228.0f, 228.0f, 1.0f, 0.0f,
                100.0f, 228.0f, 0.0f, 0.0f}, 0, 16);
        vertices.setIndices(new short[] { 0, 1, 2, 2, 3, 0 }, 0, 6);

        texture = new Texture((GLBackendController)controller, TEXTURE_FILENAME);
    }

    public void draw(float deltaTime) {
        GL10 gl = glGraphics.getGL();
        gl.glViewport(0, 0, glGraphics.getWidth(), glGraphics.getHeight());
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrthof(0, 320, 0, 480, 1, -1);

        gl.glEnable(GL10.GL_TEXTURE_2D);
        texture.bind();

        vertices.draw(GL10.GL_TRIANGLES, 0, 6);
    }

}