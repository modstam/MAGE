package se.tribestar.mage.backend.gl;

import android.opengl.GLU;
import android.util.Log;

import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import se.tribestar.mage.world.drawable.Drawable;
import se.tribestar.mage.world.light.*;
import se.tribestar.mage.world.viewport.ViewPort;

/**
 * Created by modstam on 2015-01-06.
 */
public class ObjectRenderer {

    public boolean isLit = true;
    LookAtCamera camera;

    public ObjectRenderer(GLGraphics glGraphics) {
        camera = new LookAtCamera(67, glGraphics.getWidth()
                / (float) glGraphics.getHeight(), 0.1f, 10f);
        camera.getPosition().set(0, 1, 3);
        camera.getLookAt().set(0, 0, 0);
    }

    public void prerender(List<Light> lights, List<ViewPort> viewPorts, GLGraphics glGraphics){
        GL10 gl = glGraphics.getGL();


        clearFrame(glGraphics);
        gl.glEnable(GL10.GL_DEPTH_TEST);
        gl.glViewport(0, 0, glGraphics.getWidth(), glGraphics.getHeight());

        camera.setFieldOfView(67);
        camera.setAspectRatio(glGraphics.getWidth()
                / (float) glGraphics.getHeight());
        camera.getPosition().set(0, 1, 3);
        camera.getLookAt().set(0, 0, 0);
        camera.setMatrices(gl);

        //Lighting loop, enables all lights
        if(isLit){
            gl.glEnable(GL10.GL_LIGHTING);
            for(int i = 0; i<lights.size(); i++){
                if(lights.get(i).isEnabled){
                    enableLight(glGraphics,i,lights.get(i));
                }
            }
        }
    }

    public void draw(Drawable drawable, Vertices3 vertices, GLGraphics glGraphics){
        GL10 gl = glGraphics.getGL();

        setMaterial(drawable, glGraphics);
        setWorldPosition(drawable, glGraphics);
        setScale(drawable, glGraphics);
        setWorldRotation(drawable, glGraphics);
        setupVertices(drawable,vertices,glGraphics);
        vertices.draw(GL10.GL_TRIANGLES, 0, vertices.getNumVertices());
        disableVertices(drawable,vertices,glGraphics);
    }


    public void clearFrame(GLGraphics glGraphics){
        GL10 gl = glGraphics.getGL();
        gl.glClearColor(0.1f, 0.1f, 0.1f, 1.0f);
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

        gl.glTranslatef(x,y,z);
    }

    public void setWorldRotation(Drawable d, GLGraphics glGraphics){
        GL10 gl = glGraphics.getGL();

        float angleX = d.transform.rotation.x;
        float angleY = d.transform.rotation.y;
        float angleZ = d.transform.rotation.z;

        gl.glRotatef(angleX,1,0,0);
        gl.glRotatef(angleY,0,1,0);
        gl.glRotatef(angleZ,0,0,1);

    }

    public void setScale(Drawable d, GLGraphics glGraphics){
        //TODO
    }

    public void setupVertices(Drawable drawable,Vertices3 vertices, GLGraphics glGraphics){
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
        vertices.unbind();
        if(drawable.hasColors()){

        }
        if(drawable.hasTexture()){
            gl.glDisable(GL10.GL_TEXTURE_2D);
        }
        if(drawable.hasNormals()){

        }

    }

    public void postrender(List<Light> lights, List<ViewPort> viewPorts, GLGraphics glGraphics){
        GL10 gl = glGraphics.getGL();
        //disable stuff
        if(isLit){
          int id = GL10.GL_LIGHT0;
          for(int i = 0; i<lights.size(); i++){
              gl.glDisable(id+i);
          }
        }
        gl.glDisable(GL10.GL_DEPTH_TEST);
    }

    public void enableLight(GLGraphics glGraphics, int lightId, Light light){
        int id = GL10.GL_LIGHT0 + lightId;
        GL10 gl = glGraphics.getGL();
        if(light instanceof DirectionalLight){
            DirectionalLight l= (DirectionalLight) light;
            GLDirectionalLight dlight = new GLDirectionalLight();
            dlight.setAmbient(l.ambientColor.r, l.ambientColor.g, l.ambientColor.b, l.ambientColor.a);
            dlight.setDiffuse(l.diffuseColor.r, l.diffuseColor.g, l.diffuseColor.b, l.diffuseColor.a);
            dlight.setSpecular(l.specularColor.r, l.specularColor.g, l.specularColor.b, l.specularColor.a);
            if(l.direction != null){
                dlight.setDirection(l.direction.x, l.direction.y, l.direction.z);
            }
            dlight.enable(gl, id);
        }
    }



}
