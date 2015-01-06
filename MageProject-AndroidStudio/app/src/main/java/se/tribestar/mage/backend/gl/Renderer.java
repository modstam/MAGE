package se.tribestar.mage.backend.gl;

import android.opengl.GLU;

import javax.microedition.khronos.opengles.GL10;

import se.tribestar.mage.world.drawable.Drawable;

/**
 * Created by modstam on 2015-01-06.
 */
public class Renderer {

    public void draw(Drawable drawable, Vertices3 vertices){

        GLGraphics glGraphics = vertices.getGL();
        GL10 gl = glGraphics.getGL();
        gl.glViewport(0, 0, glGraphics.getWidth(), glGraphics.getHeight());
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        GLU.gluPerspective(gl, 67,
                glGraphics.getWidth() / (float) glGraphics.getHeight(),
                0.1f, 10.0f);
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
        gl.glEnable(GL10.GL_DEPTH_TEST);
        //gl.glEnable(GL10.GL_TEXTURE_2D);
        //texture.bind();
        vertices.bind();
        gl.glTranslatef(0,0,-3);
        //gl.glRotatef(angle, 0, 1, 0);
        vertices.draw(GL10.GL_TRIANGLES, 0, 36);
        vertices.unbind();
        //gl.glDisable(GL10.GL_TEXTURE_2D);
        gl.glDisable(GL10.GL_DEPTH_TEST);
    }
}
