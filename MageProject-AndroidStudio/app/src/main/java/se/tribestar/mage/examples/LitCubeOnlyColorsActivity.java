package se.tribestar.mage.examples;

import android.opengl.GLU;

import javax.microedition.khronos.opengles.GL10;

import se.tribestar.mage.backend.gl.AmbientLight;
import se.tribestar.mage.backend.gl.CubeVertices;
import se.tribestar.mage.backend.gl.GLWorld;
import se.tribestar.mage.backend.gl.DirectionalLight;
import se.tribestar.mage.backend.gl.GLBackendController;
import se.tribestar.mage.backend.gl.Material;
import se.tribestar.mage.backend.gl.Texture;
import se.tribestar.mage.backend.gl.Vertices3;
import se.tribestar.mage.math.Vector3;

public class LitCubeOnlyColorsActivity extends GLBackendController {

    public GLWorld getWorld() {
        return new LightScreen(this);
    }

    class LightScreen extends GLWorld {
        public final String TEXTURE_FILENAME = "crate.png";

        float angle;
        Vertices3 cube;
        Texture texture;
        AmbientLight ambientLight;
        //PointLight pointLight;
        DirectionalLight directionalLight;
        Material material;

        public LightScreen(GLBackendController controller) {
            super(controller);

            cube = createCube();
            texture = new Texture(controller, TEXTURE_FILENAME);
            cube.color = new Vector3(1,1,0);
            ambientLight = new AmbientLight();
            ambientLight.setColor(1, 1, 1, 1f);
            //pointLight = new PointLight();
            //pointLight.setDiffuse(1, 0, 0, 1);
            //pointLight.setPosition(3, 3, 0);
            directionalLight = new DirectionalLight();
            directionalLight.setAmbient(0.2f,0.2f,0.2f,1f);
            directionalLight.setDiffuse(1, 1, 1, 0.1f);
            directionalLight.setDirection(1, 0, 0);
            material = new Material();
        }

        @Override
        public void resume() {
            texture.reload();
        }

        private Vertices3 createCube() {

            return new CubeVertices(getGLGraphics(), true, false, true);
        }

        @Override
        public void update(float deltaTime) {
            angle += deltaTime * 20;
        }

        @Override
        public void present(float deltaTime) {
            GL10 gl = getGLGraphics().getGL();
            gl.glClearColor(0.2f, 0.2f, 0.2f, 1.0f);
            gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
            gl.glEnable(GL10.GL_DEPTH_TEST);
            gl.glViewport(0, 0, getGLGraphics().getWidth(), getGLGraphics().getHeight());

            gl.glMatrixMode(GL10.GL_PROJECTION);
            gl.glLoadIdentity();
            GLU.gluPerspective(gl, 67, getGLGraphics().getWidth()
                    / (float) getGLGraphics().getHeight(), 0.1f, 10f);
            gl.glMatrixMode(GL10.GL_MODELVIEW);
            gl.glLoadIdentity();
            GLU.gluLookAt(gl, 0, 1, 3, 0, 0, 0, 0, 1, 0);

            gl.glEnable(GL10.GL_LIGHTING);

            //pointLight.enable(gl, GL10.GL_LIGHT0);
            directionalLight.enable(gl, GL10.GL_LIGHT1);
            material.setDiffuse(1,0,0,1f);
            material.setAmbient(1,0,0,1f);
            material.enable(gl);

            //gl.glEnable(GL10.GL_TEXTURE_2D);
            //texture.bind();

            gl.glRotatef(angle, 0, 1, 0);
            cube.bind();
            cube.draw(GL10.GL_TRIANGLES, 0, 6 * 2 * 3);
            cube.unbind();

            //pointLight.disable(gl);
            directionalLight.disable(gl);

            //gl.glDisable(GL10.GL_TEXTURE_2D);
            gl.glDisable(GL10.GL_DEPTH_TEST);
        }

        @Override
        public void pause() {
        }

        @Override
        public void dispose() {
        }
    }

}
