package se.tribestar.mage.backend.gl;

import android.opengl.GLU;

import javax.microedition.khronos.opengles.GL10;

import se.tribestar.mage.math.Vector3;

/**
 * Look at / 3rd person camera
 *
 * Created by Andreas Stjerndal on 07-Jan-2015.
 */
public class LookAtCamera {
    final Vector3 position;
    final Vector3 up;
    final Vector3 lookAt;
    float fieldOfView;
    float aspectRatio;
    float near;
    float far;

    public LookAtCamera(float fieldOfView, float aspectRatio, float near, float far) {
        this.fieldOfView = fieldOfView;
        this.aspectRatio = aspectRatio;
        this.near = near;
        this.far = far;

        position = new Vector3(0, 0, 0);
        up = new Vector3(0, 1, 0);
        lookAt = new Vector3(0,0,-1);
    }
    public void setAspectRatio(float aspectRatio){
        this.aspectRatio = aspectRatio;
    }
    public void setFieldOfView(float fieldOfView){
        this.fieldOfView = fieldOfView;
    }
    public Vector3 getPosition() {
        return position;
    }

    public Vector3 getUp() {
        return up;
    }

    public Vector3 getLookAt() {
        return lookAt;
    }

    public void setMatrices(GL10 gl) {
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        GLU.gluPerspective(gl, fieldOfView, aspectRatio, near, far);
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
        GLU.gluLookAt(gl, position.x, position.y, position.z, lookAt.x, lookAt.y, lookAt.z, up.x, up.y, up.z);
    }
}