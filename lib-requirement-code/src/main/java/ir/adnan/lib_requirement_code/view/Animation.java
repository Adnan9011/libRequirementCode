package ir.adnan.lib_requirement_code.view;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

/**
 * Created by Adnan on 5/20/2017.
 */

public class Animation {

    //Animation
    public static void animation(Techniques techniques, int durationSecound, View view) {
        YoYo.with(techniques)
                .duration(durationSecound * 1000)
                .playOn(view);
    }

    //
    public static void fadeInfadeOutInfinite(View view ) {
        ObjectAnimator fadeIn = ObjectAnimator.ofFloat(view, View.ALPHA, 1,0);
        fadeIn.setDuration(1000);
        fadeIn.setRepeatCount(ValueAnimator.INFINITE);
        fadeIn.setRepeatMode(ValueAnimator.REVERSE);

        AnimatorSet animation = new AnimatorSet();
//        animation.setInterpolator(new AccelerateInterpolator());
        animation.play(fadeIn);

        animation.start();

    }
    public static void translate (View view , int height , int duration){
        ObjectAnimator translateY = ObjectAnimator.ofFloat(view, "translationY", height , 0);
        translateY.setDuration(duration);

        AnimatorSet animation = new AnimatorSet(); //change to false
        animation.play(translateY);
        animation.setInterpolator(new DecelerateInterpolator());

        animation.start();
    }
    public static void translate (View view , int heightOne , int heightTwo , int duration){
        ObjectAnimator translateY = ObjectAnimator.ofFloat(view, "translationY", heightOne , heightTwo);
        translateY.setDuration(duration);

        AnimatorSet animation = new AnimatorSet(); //change to false
        animation.play(translateY);
        animation.setInterpolator(new DecelerateInterpolator());

        animation.start();
    }

    public static void translateAndFadeIn (View view , int height , int duration ) {
        ObjectAnimator translateY = ObjectAnimator.ofFloat(view, "translationY", height , 0);
        ObjectAnimator fadeIn = ObjectAnimator.ofFloat(view, View.ALPHA, 0,1);


        AnimatorSet animation = new AnimatorSet();
        animation.setDuration(duration);
        animation.setInterpolator(new AccelerateDecelerateInterpolator());
        animation.playTogether(translateY,fadeIn);

        animation.start();
    }

    public static void translateAndFadeOut (View view , int heightEnd , int heightBegin, int duration ) {
        ObjectAnimator translateY = ObjectAnimator.ofFloat(view, "translationY", heightEnd , heightBegin);
        ObjectAnimator fadeIn = ObjectAnimator.ofFloat(view, View.ALPHA, 1,0);


        AnimatorSet animation = new AnimatorSet();
        animation.setDuration(duration);
        animation.setInterpolator(new AccelerateDecelerateInterpolator());
        animation.playTogether(translateY,fadeIn);

        animation.start();
    }

    public static void scale (View view , float scale , int duration ) {
        ObjectAnimator scaleDown = ObjectAnimator.ofPropertyValuesHolder(view,
                PropertyValuesHolder.ofFloat("scaleX", scale),
                PropertyValuesHolder.ofFloat("scaleY", scale));
        scaleDown.setDuration(duration);
        scaleDown.start();
    }

    public static void scaleAndFadeOut (View view , float scale , int duration ) {
        ObjectAnimator scaleDown = ObjectAnimator.ofPropertyValuesHolder(view,
                PropertyValuesHolder.ofFloat("scaleX", scale),
                PropertyValuesHolder.ofFloat("scaleY", scale));
        scaleDown.setDuration(duration);
        scaleDown.start();

        ObjectAnimator fadeOut = ObjectAnimator.ofFloat(view, View.ALPHA, 1,0);


        AnimatorSet animation = new AnimatorSet();
        animation.setDuration(duration);
        animation.setInterpolator(new AccelerateInterpolator());
        animation.playTogether(fadeOut,scaleDown);

        animation.start();
    }
}
