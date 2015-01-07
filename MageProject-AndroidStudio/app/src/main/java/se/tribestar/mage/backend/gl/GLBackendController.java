package se.tribestar.mage.backend.gl;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.app.Activity;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.Renderer;
import android.os.Bundle;
import android.os.Debug;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import java.util.HashMap;
import java.util.List;

import se.tribestar.mage.backend.Audio;
import se.tribestar.mage.backend.BackendController;
import se.tribestar.mage.backend.FileIO;
import se.tribestar.mage.backend.Graphics;
import se.tribestar.mage.backend.Input;
import se.tribestar.mage.backend.android.AndroidAudio;
import se.tribestar.mage.backend.android.AndroidFileIO;
import se.tribestar.mage.backend.android.AndroidInput;
import se.tribestar.mage.world.drawable.Cube;
import se.tribestar.mage.world.drawable.Drawable;
import se.tribestar.mage.world.drawable.Mesh;
import se.tribestar.mage.world.drawable.Sphere;
import se.tribestar.mage.world.light.Light;
import se.tribestar.mage.world.viewport.ViewPort;

/**
 * Created by Andreas Stjerndal on 04-Jan-2015.
 */
public abstract class GLBackendController extends Activity implements BackendController, Renderer {
    enum GLGameState {
        Initialized,
        Running,
        Paused,
        Finished,
        Idle
    }

    GLSurfaceView glView;
    GLGraphics glGraphics;
    Audio audio;
    Input input;
    FileIO fileIO;
    GLGameState state = GLGameState.Initialized;
    GLWorld world;
    ObjectRenderer renderer;

    Object stateChanged = new Object();
    long startTime = System.nanoTime();
    WakeLock wakeLock;

    HashMap<String, Vertices3> vertices;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("MAGE", "ONCREATING");
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        glView = new GLSurfaceView(this);
        glView.setRenderer(this);
        setContentView(glView);



        vertices = new HashMap<String, Vertices3>();

        glGraphics = new GLGraphics(glView);
        renderer = new ObjectRenderer(glGraphics);

        fileIO = new AndroidFileIO(this);
        audio = new AndroidAudio(this);
        input = new AndroidInput(this, glView, 1, 1);
        PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
        wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "GLBackendController");
    }

    @Override
    public void onResume() {
        super.onResume();
        glView.onResume();
        wakeLock.acquire();
    }

    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        glGraphics.setGL(gl);

        synchronized(stateChanged) {
            if(state == GLGameState.Initialized)
                world = getWorld();
            state = GLGameState.Running;
            world.resume();
            startTime = System.nanoTime();
        }
    }

    public void onSurfaceChanged(GL10 gl, int width, int height) {
    }

    public void onDrawFrame(GL10 gl) {
        GLGameState state = null;

        synchronized(stateChanged) {
            state = this.state;
        }

        if(state == GLGameState.Running) {
            float deltaTime = (System.nanoTime()-startTime) / 1000000000.0f;
            startTime = System.nanoTime();

            world.update(deltaTime);
            world.draw(deltaTime);
        }

        if(state == GLGameState.Paused) {
            world.pause();
            synchronized(stateChanged) {
                this.state = GLGameState.Idle;
                stateChanged.notifyAll();
            }
        }

        if(state == GLGameState.Finished) {
            world.pause();
            world.dispose();
            synchronized(stateChanged) {
                this.state = GLGameState.Idle;
                stateChanged.notifyAll();
            }
        }
    }

    public void preRender(List<Light> lights, List<ViewPort> viewPorts){
        renderer.prerender(lights, viewPorts, glGraphics);
    }

    public void postRender(List<Light> lights, List<ViewPort> viewPorts){
        renderer.postrender(lights, viewPorts, glGraphics);
    }

    public void render(Drawable drawable) {
//        renderer.draw(drawable,);
    }

    @Override
    public boolean loadObject(Drawable drawable) {
        String hashKey = "";
        Vertices3 model;
        if(drawable instanceof Cube){
            hashKey = primitiveHasher("CUBE", drawable);
            if(!vertices.containsKey(hashKey))
               model = new CubeVertices(glGraphics, drawable.hasColors(), drawable.hasTexture(), drawable.hasNormals());
            else
                return true;
        }
        else if(drawable instanceof Sphere){
            hashKey = primitiveHasher("SPHERE", drawable);
            if(!vertices.containsKey(hashKey))
                model = new CubeVertices(glGraphics, drawable.hasColors(), drawable.hasTexture(), drawable.hasNormals());
            else
                return true;
        }
        else if(drawable instanceof Mesh){
            if(!vertices.containsKey(hashKey))
                model = new CubeVertices(glGraphics, drawable.hasColors(), drawable.hasTexture(), drawable.hasNormals());

            return false;
        }
        return true;
    }

    private String primitiveHasher(String primitiveName, Drawable drawable) {
        return "\n" + primitiveName + "-" + drawable.hasColors() + "-" + drawable.hasTexture() + "-" + drawable.hasNormals();
    }

    @Override
    public void onPause() {
        synchronized(stateChanged) {
            if(isFinishing())
                state = GLGameState.Finished;
            else
                state = GLGameState.Paused;
            while(true) {
                try {
                    stateChanged.wait();
                    break;
                } catch(InterruptedException e) {
                }
            }
        }
        wakeLock.release();
        glView.onPause();
        super.onPause();
    }

    public GLGraphics getGLGraphics() {
        return glGraphics;
    }

    public Input getInput() {
        return input;
    }

    public FileIO getFileIO() {
        return fileIO;
    }

    public Graphics getGraphics() {
        throw new IllegalStateException("We are using OpenGL!");
    }

    public Audio getAudio() {
        return audio;
    }

    public void setWorld(GLWorld newWorld) {
        if (newWorld == null)
            throw new IllegalArgumentException("Screen must not be null");

        this.world.pause();
        this.world.dispose();
        newWorld.resume();
        newWorld.update(0);
        this.world = newWorld;
    }

    public GLWorld getCurrentWorld() {
        return world;
    }
}