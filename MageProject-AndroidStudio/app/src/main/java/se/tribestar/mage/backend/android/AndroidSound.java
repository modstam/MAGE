package se.tribestar.mage.backend.android;

import android.media.SoundPool;

import se.tribestar.mage.backend.Sound;

/**
 * Created by Andreas Stjerndal on 04-Jan-2015.
 */
public class AndroidSound implements Sound {
    int soundId;
    SoundPool soundPool;

    public AndroidSound(SoundPool soundPool, int soundId) {
        this.soundId = soundId;
        this.soundPool = soundPool;
    }

    public void play(float volume) {
        soundPool.play(soundId, volume, volume, 0, 0, 1);
    }

    public void dispose() {
        soundPool.unload(soundId);
    }
}

