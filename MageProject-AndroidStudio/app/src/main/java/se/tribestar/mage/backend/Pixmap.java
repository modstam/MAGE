package se.tribestar.mage.backend;

import se.tribestar.mage.backend.Graphics.PixmapFormat;

/**
 * Representing a 2D pixmap.
 * Created by Andreas Stjerndal on 03-Jan-2015.
 */
public interface Pixmap {
    public int getWidth();

    public int getHeight();

    public PixmapFormat getFormat();

    public void dispose();
}