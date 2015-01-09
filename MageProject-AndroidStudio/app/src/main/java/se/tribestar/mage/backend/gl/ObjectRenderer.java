package se.tribestar.mage.backend.gl;

import android.opengl.GLU;
import android.util.Log;

import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import se.tribestar.mage.backend.BackendController;
import se.tribestar.mage.world.drawable.Drawable;
import se.tribestar.mage.world.light.*;
import se.tribestar.mage.world.viewport.ViewPort;

/**
 * Created by modstam on 2015-01-06.
 */
public class ObjectRenderer {

    public boolean isLit = true;
    LookAtCamera camera;
    GLGraphics glGraphics;
    GLBackendController backendController;

    public ObjectRenderer(GLGraphics glGraphics, GLBackendController backendController) {
        this.glGraphics = glGraphics;
        this.backendController = backendController;
        camera = new LookAtCamera(67, glGraphics.getWidth()
                / (float) glGraphics.getHeight(), 0.1f, 10f);
        camera.getPosition().set(0, 1, 3);
        camera.getLookAt().set(0, 0, 0);
    }

    public void prerender(List<Light> lights, List<ViewPort> viewPorts){
        GL10 gl = glGraphics.getGL();


        clearFrame();
        gl.glEnable(GL10.GL_DEPTH_TEST);
        gl.glViewport(0, 0, glGraphics.getWidth(), glGraphics.getHeight());

        // Uses the first enabled viewport in the list as a camera
        for(ViewPort viewPort : viewPorts){
            if(viewPort.isEnabled){
                setViewPort(viewPort);
            }
        }

        //Lighting loop, enables all lights
        if(isLit){
            gl.glEnable(GL10.GL_LIGHTING);
            for(int i = 0; i<lights.size(); i++){
                if(lights.get(i).isEnabled){
                    enableLight(i,lights.get(i));
                }
            }
        }
    }

    public void draw(Drawable drawable, Vertices3 vertices){
        GL10 gl = glGraphics.getGL();
        if(drawable.hasTexture())
            backendController.textures.get(drawable.getTexturePath()).bind();
        setupVertices(drawable,vertices);
        gl.glPushMatrix();
        setMaterial(drawable);
        setWorldPosition(drawable);
        setWorldRotation(drawable);
        setScale(drawable);
//        vertices.draw(GL10.GL_TRIANGLES, 0, vertices.getNumVertices());
        vertices.draw(GL10.GL_TRIANGLES, 0, 36);
        gl.glPopMatrix();
        disableVertices(drawable,vertices);
    }


    public void clearFrame(){
        GL10 gl = glGraphics.getGL();
        gl.glClearColor(0.1f, 0.1f, 0.1f, 1.0f);
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

    }

    public void setMaterial(Drawable drawable){
        if(drawable.material != null){
            if(drawable.material.isEnabled) {
                drawable.material.enable(glGraphics.getGL());
            }
        }
    }

    public void setWorldPosition(Drawable d){
        GL10 gl = glGraphics.getGL();
        float x = d.transform.position.x;
        float y = d.transform.position.y;
        float z = d.transform.position.z;

        gl.glTranslatef(x, y, z);
    }

    public void setWorldRotation(Drawable d){
        GL10 gl = glGraphics.getGL();

        float angleX = d.transform.rotation.x;
        float angleY = d.transform.rotation.y;
        float angleZ = d.transform.rotation.z;

        gl.glRotatef(angleX, 1, 0, 0);
        gl.glRotatef(angleY,0,1,0);
        gl.glRotatef(angleZ,0,0,1);

    }

    public void setScale(Drawable d) {
        GL10 gl = glGraphics.getGL();
        gl.glScalef(d.transform.scale.x, d.transform.scale.y, d.transform.scale.z);
    }


    public void setupVertices(Drawable drawable,Vertices3 vertices){
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

    public void disableVertices(Drawable drawable, Vertices3 vertices){
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

    public void postrender(List<Light> lights, List<ViewPort> viewPorts){
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

    public void enableLight(int lightId, Light light){
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

    public void setViewPort(ViewPort v){
        camera.setFieldOfView(v.fieldOfView);
        camera.setAspectRatio(glGraphics.getWidth()
                / (float) glGraphics.getHeight());
        //camera.getPosition().set(0, 1, 3);
        camera.getPosition().set(v.transform.position.x, v.transform.position.y, v.transform.position.z);
        camera.getLookAt().set(v.lookAt.x, v.lookAt.y, v.lookAt.z);
        camera.setMatrices(glGraphics.getGL(), v.perspective);
    }


}
