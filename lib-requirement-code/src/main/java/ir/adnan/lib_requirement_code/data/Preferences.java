package ir.adnan.lib_requirement_code.data;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by adnan on 11/28/16.
 */
public class Preferences {

    private final static String SHARED_PREFERENCES_ROOT = "MyPrefsRoot";

    public final static String SMS_PUSH_CUSTOM_DATA = "sms_data";

    /*
     * Vibrate
     */
    public static boolean isPrefsVibreteSetting(Context context) {
        SharedPreferences settings = context.getSharedPreferences(
                SHARED_PREFERENCES_ROOT, 0);
        return settings.getBoolean("vibrate", true);
    }
    public static void setPrefsVibrateSetting(Context context, boolean vibrate) {
        SharedPreferences settings = context.getSharedPreferences(
                SHARED_PREFERENCES_ROOT, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("vibrate", vibrate);

        editor.commit();
    }

    /*
     * String
     */
    public static String getPrefsString(Context context , String type) {
        SharedPreferences settings = context.getSharedPreferences(
                SHARED_PREFERENCES_ROOT, 0);
        return settings.getString(type, "");
    }
    public static void setPrefsString(Context context, String type , String value ) {
        SharedPreferences settings = context.getSharedPreferences(
                SHARED_PREFERENCES_ROOT, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(type, value);

        editor.commit();
    }

    /*
     * int
     */
    public static int getPrefsIntDefaultZero(Context context , String type) {
        SharedPreferences settings = context.getSharedPreferences(
                SHARED_PREFERENCES_ROOT, 0);
        return settings.getInt(type, 0);
    }

    public static int getPrefsIntDefaultOne(Context context , String type) {
        SharedPreferences settings = context.getSharedPreferences(
                SHARED_PREFERENCES_ROOT, 0);
        return settings.getInt(type, 1);
    }

    public static void setPrefsInt(Context context, String type, int value) {
        SharedPreferences settings = context.getSharedPreferences(
                SHARED_PREFERENCES_ROOT, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(type, value);

        editor.commit();
    }

    /*
     * Float
     */
    public static float getPrefsFloatDefaultZero(Context context , String type) {
        SharedPreferences settings = context.getSharedPreferences(
                SHARED_PREFERENCES_ROOT, 0);
        return settings.getFloat(type, 0);
    }

    public static float getPrefsFloatDefaultOne(Context context , String type) {
        SharedPreferences settings = context.getSharedPreferences(
                SHARED_PREFERENCES_ROOT, 0);
        return settings.getFloat(type, 1);
    }

    public static void setPrefsFloat(Context context, String type, float value) {
        SharedPreferences settings = context.getSharedPreferences(
                SHARED_PREFERENCES_ROOT, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putFloat(type, value);

        editor.commit();
    }

    /*
     * Boolean
     */
    public static boolean isPrefsBooleanDefaultTrue(Context context , String type) {
        SharedPreferences settings = context.getSharedPreferences(
                SHARED_PREFERENCES_ROOT, 0);
        return settings.getBoolean(type, true);
    }

    public static boolean isPrefsBooleanDefaultFalse(Context context , String type) {
        SharedPreferences settings = context.getSharedPreferences(
                SHARED_PREFERENCES_ROOT, 0);
        return settings.getBoolean(type, false);
    }

    public static void setPrefsBoolean(Context context, String type, boolean value) {
        SharedPreferences settings = context.getSharedPreferences(
                SHARED_PREFERENCES_ROOT, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(type, value);

        editor.commit();
    }
}
