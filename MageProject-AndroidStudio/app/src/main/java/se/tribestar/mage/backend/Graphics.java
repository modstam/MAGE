package se.tribestar.mage.backend;

/**
 * Interface for 2D graphics.
 * Created by Andreas Stjerndal on 03-Jan-2015.
 */
public interface Graphics {


    public Pixmap newPixmap(String fileName, PixmapFormat format);

    public void clear(int color);

    public void drawPixel(int x, int y, int color);

    public void drawLine(int x1, int y1, int x2, int y2, int color);

    public void drawRectangle(int x, int y, int width, int height, int color);

    public void drawPixmap(Pixmap pixmap, int x, int y, int srcX, int srcY,
                           int srcWidth, int srcHeight);

    public void drawPixmap(Pixmap pixmap, int x, int y);

    public int getWidth();

    public int getHeight();

    public static enum PixmapFormat {
        ARGB4444, ARGB8888, RGB565
    }
}

