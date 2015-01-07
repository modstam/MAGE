package se.tribestar.mage.backend.gl;

import android.opengl.GLU;

import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import se.tribestar.mage.world.drawable.Drawable;
import se.tribestar.mage.world.light.Light;
import se.tribestar.mage.world.viewport.Camera;
import se.tribestar.mage.world.viewport.ViewPort;

/**
 * Created by modstam on 2015-01-06.
 */
public class ObjectRenderer {

    public boolean isLit = true;


    public void prerender(List<Light> lights, List<ViewPort> viewPorts, GLGraphics glGraphics){
        GL10 gl = glGraphics.getGL();

        //TEMP------------------------
        gl.glViewport(0, 0, glGraphics.getWidth(), glGraphics.getHeight());

        clearFrame(glGraphics);

        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        GLU.gluPerspective(gl, 67,
                glGraphics.getWidth() / (float) glGraphics.getHeight(),
                0.1f, 10.0f);


        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();

        GLU.gluLookAt(gl, 0, 1, 3, 0, 0, 0, 0, 1, 0);
        gl.glEnable(GL10.GL_DEPTH_TEST);
        //----------------------------



    }

    public void draw(Drawable drawable, Vertices3 vertices){

        GLGraphics glGraphics = vertices.getGL();
        GL10 gl = glGraphics.getGL();


        setupVertices(drawable, vertices, glGraphics);
        setMaterial(drawable, glGraphics);

        setWorldPosition(drawable, glGraphics);
        setWorldRotation(drawable, glGraphics);
        vertices.draw(GL10.GL_TRIANGLES, 0, 36);

        disableVertices(drawable, vertices, glGraphics);

        //setupVertexOptions
        //setMaterial
        //vertices.bind()
        //setPosition
        //setRotation
        //vertices.draw
        //vertices.unbind()
        //disableVertexOptions




    }


    public void clearFrame(GLGraphics glGraphics){
        GL10 gl = glGraphics.getGL();
        gl.glClearColor(0.2f, 0.2f, 0.2f, 1.0f);
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

    }

    public void setMaterial(Drawable drawable, GLGraphics glGraphics){
        if(drawable.material != null){
            if(drawable.material.isEnabled) {
                drawable.material.enable(glGraphics.getGL());
            }
        }
    }

    public void setWorldPosition(Drawable d, GLGraphics glGraphics){
        GL10 gl = glGraphics.getGL();
        float x = d.transform.position.x;
        float y = d.transform.position.y;
        float z = d.transform.position.z;

        gl.glTranslatef(-x,-y,-z);
    }

    public void setWorldRotation(Drawable d, GLGraphics glGraphics){
        GL10 gl = glGraphics.getGL();
            //TODO
        //gl.glRotatef(angle, 0, 1, 0);
    }

    public void setupVertices(Drawable drawable,  Vertices3 vertices, GLGraphics glGraphics){
        GL10 gl = glGraphics.getGL();
        if(drawable.hasColors()){
            if(!isLit){
                vertices.color = drawable.color;
                vertices.hasColor = true;
            }
        }
        if(drawable.hasTexture()){
            vertices.hasTexCoords = true;
            //texture.bind();
            gl.glEnable(GL10.GL_TEXTURE_2D);

        }
        if(drawable.hasNormals()){
            vertices.hasNormals = true;
        }

        vertices.bind();

    }

    public void disableVertices(Drawable drawable, Vertices3 vertices, GLGraphics glGraphics){
        GL10 gl = glGraphics.getGL();
        if(drawable.hasColors()){

        }
        if(drawable.hasTexture()){
            gl.glDisable(GL10.GL_TEXTURE_2D);
        }
        if(drawable.hasNormals()){

        }

        vertices.unbind();

    }

    public void postrender(List<Light> lights, List<ViewPort> viewPorts, GLGraphics glGraphics){
        GL10 gl = glGraphics.getGL();
        //disable stuff
        gl.glDisable(GL10.GL_DEPTH_TEST);
    }



}
