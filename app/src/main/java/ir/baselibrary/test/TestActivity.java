package ir.baselibrary.test;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import ir.adnan.lib_requirement_code.core.Log;
import ir.adnan.lib_requirement_code.data.Preferences;
import ir.adnan.lib_requirement_code.main.LibraryActivity;
import ir.baselibrary.R;
import ir.baselibrary.data.PreferencesEnum;

/**
 * Created by Adnan on 7/2/2017.
 */

public class TestActivity extends LibraryActivity {
    private String TAG = "TestActivity";
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
        testPreferences();

    }

    private void testPreferences() {
        Log.e(TAG , "Preferences INT type , value : "+Preferences.getPrefsIntDefaultZero(THIS , PreferencesEnum.INT.getValue()));
        Log.e(TAG , "Preferences INT type , value : "+Preferences.getPrefsIntDefaultOne(THIS , PreferencesEnum.INT.getValue()));

        Preferences.setPrefsInt(THIS , PreferencesEnum.INT.getValue() , 25);

        Log.e(TAG , "Preferences INT type , value : "+Preferences.getPrefsIntDefaultZero(THIS , PreferencesEnum.INT.getValue()));
        Log.e(TAG , "Preferences INT type , value : "+Preferences.getPrefsIntDefaultOne(THIS , PreferencesEnum.INT.getValue()));
    }
}
