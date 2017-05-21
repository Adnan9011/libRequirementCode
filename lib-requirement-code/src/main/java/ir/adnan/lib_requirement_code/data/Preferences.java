package ir.adnan.lib_requirement_code.data;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by adnan on 11/28/16.
 */
public class Preferences {

    private final static String SHARED_PREFERENCES_ROOT = "MyPrefsRoot";

    /*
     * Vibrate
     */
    public static boolean isPrefsVibreteSetting(Context context) {
        SharedPreferences settings = context.getSharedPreferences(
                SHARED_PREFERENCES_ROOT, 0);
        return settings.getBoolean("music_setting", true);
    }
    public static void setPrefsVibrateSetting(Context context, boolean vibrate) {
        SharedPreferences settings = context.getSharedPreferences(
                SHARED_PREFERENCES_ROOT, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("music_setting", vibrate);

        editor.commit();
    }

}
