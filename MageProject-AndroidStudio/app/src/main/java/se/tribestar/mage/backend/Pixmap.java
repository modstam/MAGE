package se.tribestar.mage.backend;

import se.tribestar.mage.backend.Graphics.PixmapFormat;

/**
 * Representing a 2D pixmap.
 * Created by Andreas Stjerndal on 03-Jan-2015.
 * * from the book "Beginning Android Games", by Mario Zechner
 */
public interface Pixmap {
    public int getWidth();

    public int getHeight();

    public PixmapFormat getFormat();

    public void dispose();
}