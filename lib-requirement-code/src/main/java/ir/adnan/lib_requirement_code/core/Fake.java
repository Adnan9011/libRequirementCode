package ir.adnan.lib_requirement_code.core;

import android.content.Context;
import android.util.DisplayMetrics;
import android.widget.Toast;

/**
 * Created by adnan on 12/27/16.
 */
public class Fake {

    public static void getDensity(Context context , int x) {

        switch(x){
            case DisplayMetrics.DENSITY_LOW:
                Toast.makeText(context , "ldpi" , Toast.LENGTH_LONG).show();
                break;
            case DisplayMetrics.DENSITY_MEDIUM:
                Toast.makeText(context , "mdpi" , Toast.LENGTH_LONG).show();
                break;
            case DisplayMetrics.DENSITY_HIGH:
                Toast.makeText(context , "hdpi" , Toast.LENGTH_LONG).show();
                break;
            case DisplayMetrics.DENSITY_XHIGH:
                Toast.makeText(context , "xhdpi" , Toast.LENGTH_LONG).show();
                break;
            case DisplayMetrics.DENSITY_XXHIGH:
                Toast.makeText(context , "xxhdpi" , Toast.LENGTH_LONG).show();
                break;
            case DisplayMetrics.DENSITY_XXXHIGH:
                Toast.makeText(context , "xxxhdpi" , Toast.LENGTH_LONG).show();
                break;

        }
    }
}
