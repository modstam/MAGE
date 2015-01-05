package se.tribestar.mage.math;

/**
 * Created by modstam on 2015-01-03.
 */
public class Vector2 {
    public float x;
    public float y;

    public Vector2(float x, float y){
        this.x = x;
        this.y = y;
    }

    public Vector2 add(float x, float y) {
        this.x += x;
        this.y += y;
        return this;
    }

    public Vector2 add(Vector2 other) {
        this.x += other.x;
        this.y += other.y;
        return this;
    }

    public Vector2 sub(float x, float y) {
        this.x -= x;
        this.y -= y;
        return this;
    }

    public Vector2 sub(Vector2 other) {
        this.x -= other.x;
        this.y -= other.y;
        return this;
    }
}
