package se.tribestar.mage.frontend.math;

/**
 * Created by modstam on 2015-01-03.
 */
public class Vector3 {
    public float x;
    public float y;
    public float z;

    public Vector3(float x, float y, float z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3 set(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
        return this;
    }

    public Vector3 set(Vector3 other) {
        this.x = other.x;
        this.y = other.y;
        this.z = other.z;
        return this;
    }
}
