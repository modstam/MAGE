package se.tribestar.mage.backend;

/**
 * Created by Andreas Stjerndal on 04-Jan-2015.
 */
public interface BackendController {
    public Input getInput();

    public FileIO getFileIO();

    public Graphics getGraphics();

    public Audio getAudio();

    /*public void setScreen(Screen screen);

    public Screen getCurrentScreen();

    public Screen getStartScreen();*/
}
