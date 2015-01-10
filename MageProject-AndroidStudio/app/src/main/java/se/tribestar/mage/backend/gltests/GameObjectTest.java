package se.tribestar.mage.backend.gltests;

import java.util.Random;

/**
 * Representing an example Game Object.
 * Created by Andreas Stjerndal on 05-Jan-2015.
 */
public class GameObjectTest {
    static final Random rand = new Random();
    public float x, y;
    float dirX, dirY;

    public GameObjectTest() {
        x = rand.nextFloat() * 320;
        y = rand.nextFloat() * 480;
        dirX = 50;
        dirY = 50;
    }

    public void update(float deltaTime) {
        x = x + dirX * deltaTime;
        y = y + dirY * deltaTime;

        if (x < 0) {
            dirX = -dirX;
            x = 0;
        }

        if (x > 320) {
            dirX = -dirX;
            x = 320;
        }

        if (y < 0) {
            dirY = -dirY;
            y = 0;
        }

        if (y > 480) {
            dirY = -dirY;
            y = 480;
        }
    }
}
