package se.tribestar.mage.backend.gl;

import android.util.Log;

/**
 * Call logFrame() once per frame to output FPS in log.
 *
 * Created by Andreas Stjerndal on 05-Jan-2015.
 *
 * * from the book "Beginning Android Games", by Mario Zechner
 */
public class FPSCounter {
    long startTime = System.nanoTime();
    int frames = 0;

    public void logFrame() {
        frames++;
        if(System.nanoTime() - startTime >= 1000000000) {
            Log.d("FPSCounter", "fps: " + frames);
            frames = 0;
            startTime = System.nanoTime();
        }
    }
}