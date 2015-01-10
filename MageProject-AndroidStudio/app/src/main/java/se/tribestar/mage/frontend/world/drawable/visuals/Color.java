package se.tribestar.mage.frontend.world.drawable.visuals;

/**
 * Created by modstam on 2015-01-07.
 */
public class Color {
    public float r;
    public float g;
    public float b;
    public float a;

    public Color(float r, float g, float b, float a){
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }

    public static final Color RED = new Color(1f, 0f, 0f, 1f);
    public static final Color GREEN = new Color(0f, 1f, 0f, 1f);
    public static final Color BLUE = new Color(0f, 0f, 1f, 1f);
    public static final Color WHITE = new Color(1f, 1f, 1f, 1f);
    public static final Color BLACK = new Color(0f, 0f, 0f, 1f);
    public static final Color YELLOW = new Color(1f, 1f, 0f, 1f);
    public static final Color ORANGE = new Color(1f, 0.5f, 0f, 1f);
    public static final Color PINK = new Color(1f, 0f, 1f, 1f);
    public static final Color PURPLE = new Color(0.5f, 0f, 0.5f, 1f);

}
