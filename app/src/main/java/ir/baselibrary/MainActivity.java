package ir.baselibrary;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import ir.adnan.lib_requirement_code.core.Static;
import ir.adnan.lib_requirement_code.main.LibraryActivity;
import ir.baselibrary.test.TestActivity;

public class MainActivity extends LibraryActivity {

    private String TAG = "MainActivity";
    private Context THIS = this ;
    private Activity THIS_ACTIVITY = this ;

    private static final int ID_TOOLBAR = R.id.toolbar;
    private static final int ID_TOOLBAR_TITLE = R.id.toolbar_title;
    private static final int ID_IMAGE_NAVIGATION = R.id.toolbar_navigation;
    private static final int ID_DRAWER = R.id.drawer_layout;
    private static final int ID_RETRY_NETWORK = R.id.retry_network;
    private static final int ID_COORDINATE_LAYOUT = R.id.coordinator_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_activity);

        launchPushe(THIS);
        launchFirebaseAnalytics(THIS);
        setView(THIS_ACTIVITY , ID_TOOLBAR , ID_TOOLBAR_TITLE , ID_IMAGE_NAVIGATION
                                , ID_DRAWER , ID_RETRY_NETWORK , ID_COORDINATE_LAYOUT);
        /*
         Write Your Code
         */
        testRetrofit();
        testSnackBar();
//        forceCrash();
//        testStartTestActivity();
    }

    private void testSnackBar() {
        Static.snackbarWithAction(THIS , libraryActivityView.getCoordinateLayout() , "تست اسنک بار با اکشن");
    }

    private void testRetrofit() {
    }

    private void forceCrash() {
        int x = 10/0;
    }

    private void testStartTestActivity() {

    }
}
