package ir.adnan.lib_requirement_code.main;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.crashlytics.android.Crashlytics;
import com.google.firebase.analytics.FirebaseAnalytics;

import co.ronash.pushe.Pushe;
import io.fabric.sdk.android.Fabric;
import ir.adnan.lib_requirement_code.R;
import ir.adnan.lib_requirement_code.core.Static;
import ir.adnan.lib_requirement_code.view.SnackBar;
import ir.adnan.lib_requirement_code.main.pojo.LibraryActivityView;

/**
 * Created by Adnan on 6/25/2017.
 */

public class LibraryActivity extends AppCompatActivity {

    //Analytic
    private FirebaseAnalytics firebaseAnalytics;

    protected FragmentTransaction fragmentTransaction;

    //For Exit
    protected boolean doubleBackToExitPressedOnce = false;

    //Pojo
    protected LibraryActivityView libraryActivityView ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void launchPushe (Context context) {
        Pushe.initialize(context,true);
    }

    protected void launchFirebaseAnalytics (Context context) {
        //Firebase
        firebaseAnalytics = FirebaseAnalytics.getInstance(context);
        firebaseAnalytics.setAnalyticsCollectionEnabled(true);
    }

    protected void setView(Activity activity , int idToolbar , int idToolbarTitle , int idImageNavigation
                                            , final int idDrawer , int idRetryNetwork , int idCoordinateLayout) {

        //Library Activity View --> parameter : activity
        libraryActivityView = new LibraryActivityView(activity);

        //Toolbar
        libraryActivityView.setToolbar(idToolbar);
        libraryActivityView.setToolbarTitle(idToolbarTitle);
        libraryActivityView.setImageNavigation(idImageNavigation);
        libraryActivityView.setDrawer(idDrawer);
        libraryActivityView.setCoordinateLayout(idCoordinateLayout);

        libraryActivityView.getImageNavigation().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                libraryActivityView.setDrawer(idDrawer);
                if (libraryActivityView.getDrawer().isDrawerOpen(GravityCompat.END)) {
                    libraryActivityView.getDrawer().closeDrawer(GravityCompat.END);
                } else {
                    libraryActivityView.getDrawer().openDrawer(GravityCompat.END);
                }
            }
        });

        //Retofit
        libraryActivityView.setRetryNetwork(idRetryNetwork);
    }

    /*
     * Fragment
     */
    public void addFragment(Fragment fragment, boolean isAddtoBackStck) {

        fragmentTransaction = getFragmentManager().beginTransaction();

        String backStateName = fragment.getClass().getSimpleName();

        String lastFragmentName = "";
        if (getFragmentManager() != null &&
                getFragmentManager().getBackStackEntryCount() > 0) {
            lastFragmentName = getFragmentManager().getBackStackEntryAt(
                    getFragmentManager().getBackStackEntryCount() - 1
            ).getName();
        }

        if (lastFragmentName == null)
            lastFragmentName = "";

        if (!lastFragmentName.
                equalsIgnoreCase
                        (backStateName)) {
            if (isAddtoBackStck) {
                fragmentTransaction
//                .disallowAddToBackStack()
                        .addToBackStack(fragment.getClass().getSimpleName())
                        .setCustomAnimations(
                                R.animator.enter_from_left,
                                R.animator.enter_from_right,
                                R.animator.pop_to_left,
                                R.animator.pop_to_right)
                        .add(libraryActivityView.getFragment(), fragment)
                        .commit();
            } else {
                fragmentTransaction
//                .disallowAddToBackStack()
                        .addToBackStack(null)
                        .setCustomAnimations(
                                R.animator.enter_from_left,
                                R.animator.enter_from_right,
                                R.animator.pop_to_left,
                                R.animator.pop_to_right)
                        .add(libraryActivityView.getFragment(), fragment)
                        .commit();
            }
        }
    }

    public void replaceFragment(Fragment fragment) {

        String backStateName = fragment.getClass().getSimpleName();
        boolean fragmentPopped = getFragmentManager().popBackStackImmediate(backStateName, 0);
        String lastFragmentName = getFragmentManager().getBackStackEntryAt(
                getFragmentManager().getBackStackEntryCount() - 1
        ).getName();

        if (!fragmentPopped &&
                getFragmentManager().findFragmentByTag(backStateName) == null) {
            if (!lastFragmentName.equalsIgnoreCase(backStateName)) {
                fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction
                        .setCustomAnimations(
                                R.animator.enter_from_left,
                                R.animator.enter_from_right,
                                R.animator.pop_to_left,
                                R.animator.pop_to_right)
                        .replace(libraryActivityView.getFragment(), fragment)
                        .addToBackStack(fragment.getClass().getSimpleName())
                        .commit();
            }
        }
    }

    private void closeDrawer() {
        libraryActivityView.getDrawer().closeDrawer(GravityCompat.END);
//        materialMenuView.animateIconState(MaterialMenuDrawable.IconState.BURGER);
    }

    @Override
    public void onBackPressed() {
        try {
            if (libraryActivityView.getRetryNetwork().getVisibility() == View.GONE) {
                if (libraryActivityView.getDrawer().isDrawerOpen(GravityCompat.END)) {
                    closeDrawer();
                } else {

                    if (SnackBar.snackbar != null && SnackBar.snackbar.isShownOrQueued()) {
                        SnackBar.snackbar.dismiss();
                    } else if (getFragmentManager().getBackStackEntryCount() == 1) {

                        if (doubleBackToExitPressedOnce) {
                            finish();
                            return;
                        }

                        this.doubleBackToExitPressedOnce = true;
                        Static.snackbarShowTimeLongExit(this, libraryActivityView.getCoordinateLayout(), getResources().getString(R.string.onback_press_for_exit));

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                doubleBackToExitPressedOnce = false;
                            }
                        }, 2000);
                    } else {
                        getFragmentManager().popBackStack();
                    }
                }
            } else {
                if (doubleBackToExitPressedOnce) {
                    finish();
                    return;
                }

                this.doubleBackToExitPressedOnce = true;
                Static.snackbarShowTimeLongExit(this, libraryActivityView.getCoordinateLayout(), getResources().getString(R.string.onback_press_for_exit));

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        doubleBackToExitPressedOnce = false;
                    }
                }, 2000);
            }
        } catch (Exception e) {

        }
    }
}