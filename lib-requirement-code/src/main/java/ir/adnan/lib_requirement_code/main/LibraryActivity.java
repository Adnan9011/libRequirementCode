package ir.adnan.lib_requirement_code.main;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.firebase.analytics.FirebaseAnalytics;

import co.ronash.pushe.Pushe;
import ir.adnan.lib_requirement_code.R;
import ir.adnan.lib_requirement_code.core.Static;
import ir.adnan.lib_requirement_code.data.Finals;
import ir.adnan.lib_requirement_code.main.pojo.LibraryActivityView;
import ir.adnan.lib_requirement_code.view.DialogProgressAsync;
import ir.adnan.lib_requirement_code.view.SnackBar;

/**
 * Created by Adnan on 6/25/2017.
 */

public class LibraryActivity extends AppCompatActivity {

    //Drawer
    protected int idDrawer = 0;

    //Analytic
    private FirebaseAnalytics firebaseAnalytics;

    protected FragmentTransaction fragmentTransaction;

    //For Exit
    protected boolean doubleBackToExitPressedOnce = false;

    //Pojo
    protected LibraryActivityView libraryActivityView;

    //Progress
    private DialogProgressAsync dialogLoading;
    private long dialogLoadingTimeBegin;
    private boolean isLoadedLoadingDialog = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void launchPushe(Context context) {
        Pushe.initialize(context, true);
    }

    protected void launchFirebaseAnalytics(Context context) {
        //Firebase
        firebaseAnalytics = FirebaseAnalytics.getInstance(context);
        firebaseAnalytics.setAnalyticsCollectionEnabled(true);
    }

    protected void setView(Activity activity, int idFragment, int idToolbar, int idToolbarTitle, int idImageNavigation, int toolbarImage
            , final int idDrawer, int idRetryNetwork, int idCoordinateLayout) {

        setViewBase(activity, idFragment, idToolbar, idToolbarTitle, idImageNavigation, toolbarImage
            , idDrawer, idRetryNetwork, idCoordinateLayout , 0);
    }

    protected void setView(Activity activity, int idFragment, int idToolbar, int idToolbarTitle, int idImageNavigation, int toolbarImage
            , final int idDrawer, int idRetryNetwork, int idCoordinateLayout , int idImageViewImageNavigation) {

        setViewBase(activity, idFragment, idToolbar, idToolbarTitle, idImageNavigation, toolbarImage
                , idDrawer, idRetryNetwork, idCoordinateLayout , idImageViewImageNavigation);
    }

    private void setViewBase(Activity activity, int idFragment, int idToolbar, int idToolbarTitle, int idImageNavigation, int toolbarImage
            , final int idDrawer, int idRetryNetwork, int idCoordinateLayout ,
                             int idImageViewImageNavigation) {

        //Library Activity View --> parameter : activity
        libraryActivityView = new LibraryActivityView(activity);

        //Drawer
        this.idDrawer = idDrawer;

        //Toolbar
        libraryActivityView.setFragment(idFragment);
        libraryActivityView.setToolbar(idToolbar);
        libraryActivityView.setToolbarTitle(idToolbarTitle);
        libraryActivityView.setImageNavigation(idImageNavigation);
        libraryActivityView.setToolbarImage(toolbarImage);
        libraryActivityView.setDrawer(this.idDrawer);
        libraryActivityView.setCoordinateLayout(idCoordinateLayout);

        if(idImageViewImageNavigation != 0) {
            libraryActivityView.setImageViewimageNavigation(idImageViewImageNavigation);
        }


        libraryActivityView.getImageNavigation().setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (LibraryActivity.this.idDrawer != 0) {
//                    libraryActivityView.setDrawer(idDrawer);
                    if (libraryActivityView.getDrawer().isDrawerOpen(GravityCompat.END)) {
                        libraryActivityView.getDrawer().closeDrawer(GravityCompat.END);
                    } else {
                        libraryActivityView.getDrawer().openDrawer(GravityCompat.END);
                    }
                }
            }
        });

        //Retofit
        libraryActivityView.setRetryNetwork(idRetryNetwork);
    }

    protected void setBackButtonWithListener() {
        if (libraryActivityView.getToolbarImage() != null) {
            libraryActivityView.getToolbarImage().setBackgroundResource(R.drawable.nav_back);

            libraryActivityView.getToolbarImage().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (getFragmentManager().getBackStackEntryCount() == 1) {

                        if (doubleBackToExitPressedOnce) {
                            finish();
                            return;
                        }

                        doubleBackToExitPressedOnce = true;
                        Static.snackbarShowTimeLongExit(LibraryActivity.this, libraryActivityView.getCoordinateLayout(), getResources().getString(R.string.onback_press_for_exit));

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
            });
        }
    }

    /*
     * Fragment
     */

    public void mustKillFragmentExceptMainFragmentAndName(String mainFragment, String nameClassFragment) {
        int count = getFragmentManager().getBackStackEntryCount() - 1;
        for (int j = count; j >= 0; j--) {
            String back = getFragmentManager().getBackStackEntryAt(j).getName();

            if (back.equalsIgnoreCase(mainFragment)) {
            } else if (back.equalsIgnoreCase(nameClassFragment)) {
            } else {
                getFragmentManager().popBackStack();
            }
        }
    }

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

    public void replaceFragmentRemoveItSelf(Fragment fragment, boolean removeAllPreviousFragment) {

        String backStateName = fragment.getClass().getSimpleName();
        boolean fragmentPopped = getFragmentManager().popBackStackImmediate(backStateName, 0);
        String lastFragmentName = getFragmentManager().getBackStackEntryAt(
                getFragmentManager().getBackStackEntryCount() - 1
        ).getName();

        if (!fragmentPopped &&
                getFragmentManager().findFragmentByTag(backStateName) == null) {
            if (!lastFragmentName.equalsIgnoreCase(backStateName)) {
                fragmentTransaction = getFragmentManager().beginTransaction();

                // Remove Previos :
                if (removeAllPreviousFragment) {
                    mustKillFragmentExceptMainFragmentAndName("", "");
                }
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

    public void replaceFragmentSimple(Fragment fragment) {

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

    // Drawer

    public void openDrawer() {
        try {
            if (this.idDrawer != 0)
                libraryActivityView.getDrawer().openDrawer(GravityCompat.END);
        } catch (Exception e) {
        }
    }

    public void closeDrawer() {
        try {
            if (this.idDrawer != 0)
                libraryActivityView.getDrawer().closeDrawer(GravityCompat.END);
        } catch (Exception e) {
        }
    }

    public void disableDrawer() {
        try {
            if (this.idDrawer != 0)
                libraryActivityView.getDrawer().setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        } catch (Exception e) {
        }
    }

    public void enableDrawer() {
        try {
            if (this.idDrawer != 0)
                libraryActivityView.getDrawer().setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        } catch (Exception e) {
        }
    }

    /*
     * Loading
     */
    public void showProgressBar(Context context) {
        try {
            isLoadedLoadingDialog = false;

            if (dialogLoading == null) {
                dialogLoading = new DialogProgressAsync(context);
            }

            //
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (!isLoadedLoadingDialog) {
                        dialogLoading.show();
                        dialogLoadingTimeBegin = System.currentTimeMillis();
                    }
                }
            }, Finals.WAIT_LODING_SHOWING_PROGRESS_DIALOG);
        } catch (Exception e) {
        }
    }

    public void dismissProgressBar() {
        try {
            isLoadedLoadingDialog = true;
            long remindDialogLoadingTime = System.currentTimeMillis() - dialogLoadingTimeBegin;
            //
            if (remindDialogLoadingTime > Finals.WAIT_DELAY_SHOWING_PROGRESS_DIALOG) {
                dialogLoading.dismiss();
            } else {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            dialogLoading.dismiss();
                        } catch (Exception e) {
                        }
                    }
                }, Finals.WAIT_DELAY_SHOWING_PROGRESS_DIALOG - remindDialogLoadingTime);
            }
        } catch (IllegalArgumentException e) {
        } catch (Exception e) {
        }
    }

    public void showSnackBarAfterDialog(final Context context, final String text) {
        long remindDialogLoadingTime = System.currentTimeMillis() - dialogLoadingTimeBegin;
        //
        if (remindDialogLoadingTime > Finals.WAIT_DELAY_SHOWING_PROGRESS_DIALOG) {
            Static.snackbarShowTimeLong(context, libraryActivityView.getCoordinateLayout(), text);
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Static.snackbarShowTimeLong(context, libraryActivityView.getCoordinateLayout(), text);
                }
            }, Finals.WAIT_DELAY_SHOWING_PROGRESS_DIALOG - remindDialogLoadingTime);
        }
    }


    @Override
    public void onBackPressed() {
        try {
            if (libraryActivityView.getRetryNetwork().getVisibility() == View.GONE) {
                if (libraryActivityView.getDrawer() != null && libraryActivityView.getDrawer().isDrawerOpen(GravityCompat.END)) {
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

    // GET && SET
    public LibraryActivityView getLibraryActivityView() {
        return libraryActivityView;
    }

    public void setLibraryActivityView(LibraryActivityView libraryActivityView) {
        this.libraryActivityView = libraryActivityView;
    }
}
