package ir.baselibrary.test;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import ir.adnan.lib_requirement_code.core.Log;
import ir.adnan.lib_requirement_code.data.Preferences;
import ir.adnan.lib_requirement_code.main.LibraryActivity;
import ir.baselibrary.R;

/**
 * Created by Adnan on 7/2/2017.
 */

public class TestActivityWithoutNavigation extends LibraryActivity {
    private String TAG = "TestActivityWithoutNavigation";
    private Context THIS = this ;
    private Activity THIS_ACTIVITY = this ;

    private static final int ID_FRAGMENT = R.id.fragment;
    private static final int ID_TOOLBAR = R.id.toolbar;
    private static final int ID_TOOLBAR_TITLE = R.id.toolbar_title;
    private static final int ID_IMAGE_NAVIGATION = R.id.toolbar_navigation;
    private static final int ID_TOOLBAR_IMAGE = R.id.toolbar_menu;
    private static final int ID_DRAWER = 0;
    private static final int ID_RETRY_NETWORK = R.id.retry_network;
    private static final int ID_COORDINATE_LAYOUT = R.id.coordinator_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.test_activity_without_navigation);

        launchPushe(THIS);
        launchFirebaseAnalytics(THIS);
        setView(THIS_ACTIVITY , ID_FRAGMENT , ID_TOOLBAR , ID_TOOLBAR_TITLE , ID_IMAGE_NAVIGATION ,ID_TOOLBAR_IMAGE
                , ID_DRAWER  ,  ID_RETRY_NETWORK , ID_COORDINATE_LAYOUT);

        setBackButtonWithListener();

        /*
         * Write Your Code
         */
        testPreferences();

    }

    private void testPreferences() {
        Log.e(TAG , "Preferences INT type , value : "+Preferences.getPrefsIntDefaultZero(THIS , "test"));
        Log.e(TAG , "Preferences INT type , value : "+Preferences.getPrefsIntDefaultOne(THIS , "test"));

        Preferences.setPrefsInt(THIS , "test" , 25);

        Log.e(TAG , "Preferences INT type , value : "+Preferences.getPrefsIntDefaultZero(THIS , "test"));
        Log.e(TAG , "Preferences INT type , value : "+Preferences.getPrefsIntDefaultOne(THIS , "test"));
    }
}
