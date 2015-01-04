package se.tribestar.mage.backend.android;

import android.view.View;

import java.util.List;

import se.tribestar.mage.backend.Input;

/**
 * Created by Andreas Stjerndal on 04-Jan-2015.
 */
public interface TouchHandler extends View.OnTouchListener {
    public boolean isTouchDown(int pointer);

    public int getTouchX(int pointer);

    public int getTouchY(int pointer);

    public List<Input.TouchEvent> getTouchEvents();
}