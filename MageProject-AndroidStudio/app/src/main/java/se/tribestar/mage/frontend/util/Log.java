package se.tribestar.mage.frontend.util;

/**
 * Created by Andreas Stjerndal on 09-Jan-2015.
 */
public class Log {
    public static final String MAGE_LOG_TAG = "MAGE";
    public static void log(String msg) {
        android.util.Log.d(MAGE_LOG_TAG, msg);
    }
}
