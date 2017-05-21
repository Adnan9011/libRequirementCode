package ir.adnan.lib_requirement_code.core;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.View;

import ir.adnan.lib_requirement_code.view.SnackBar;

/**
 * Created by adnan on 12/12/16.
 */
public class Static {

    private static Typeface typeface , typefaceBold;

    public static void snackbarWithAction(Context context, View view, String text) {
        SnackBar.showWithAction(context, view, text);
    }

    public static void snackbarShowTimeLong(Context context, View view, String text) {
        SnackBar.showTimeLong(context, view, text);
    }

    public static void snackbarShowTimeLongExit(Context context, View view, String text) {
        SnackBar.showTimeLongExit(context, view, text);
    }

    public static void snackbarDismiss() {
        SnackBar.dismissSnack();
    }

    //Font
    public static Typeface getMainTypeface(Context context) {

        if(typeface == null) {
            typeface = Typeface.createFromAsset(context.getAssets(), "fonts/iransans.ttf");
            return typeface;
        } else {
            return typeface;
        }
//        return Typeface.createFromAsset(context.getAssets(), "fonts/iransans.ttf");
    }

    //Font
    public static Typeface getMainTypefaceBold(Context context) {
//        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "fonts/iransans.ttf");
//        return Typeface.create(typeface , Typeface.BOLD);
        if(typeface == null) {
            typeface = Typeface.createFromAsset(context.getAssets(), "fonts/iransans.ttf");
        }
        if(typefaceBold == null) {
            typefaceBold =  Typeface.create(typeface , Typeface.BOLD);
            return typefaceBold;
        } else {
            return typefaceBold;
        }
    }

    public static int getColor(Context context , int colorRes) {
        TypedValue typedValue = new TypedValue();
        TypedArray a = context.obtainStyledAttributes(typedValue.data
                , new int[]{colorRes});
        int color = a.getColor(0, 0);
        a.recycle();
        return color;
    }

//    public static Typeface getTypeface(Context context, String fontName) {
//
//        if(fontName.equalsIgnoreCase(FontEnum.SANS.getValue()))
//            return Typeface.createFromAsset(context.getAssets(), "fonts/iransans.ttf");
//        else if(fontName.equalsIgnoreCase(FontEnum.NASKH.getValue()))
//            return Typeface.createFromAsset(context.getAssets(), "fonts/irannaskh.ttf");
//        else if(fontName.equalsIgnoreCase(FontEnum.NASSIM.getValue()))
//            return Typeface.createFromAsset(context.getAssets(), "fonts/nassim.ttf");
//        else if(fontName.equalsIgnoreCase(FontEnum.YEKAN.getValue()))
//            return Typeface.createFromAsset(context.getAssets(), "fonts/yekan.ttf");
//        else
//            return Typeface.createFromAsset(context.getAssets(), "fonts/iransans.ttf");
//    }
}
