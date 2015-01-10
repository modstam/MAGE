package se.tribestar.mage.backend;

/**
 * Interface for a sound resource representation.
 * Created by Andreas Stjerndal on 03-Jan-2015.
 */
public interface Sound {
    public void play(float volume);

    public void dispose();
}
