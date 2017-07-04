package ir.baselibrary.test.push;

import android.os.Bundle;
import android.support.annotation.Nullable;

import ir.adnan.lib_requirement_code.core.Log;
import ir.adnan.lib_requirement_code.pushe.activity.ClickNotificationActivity;

/**
 * Created by Adnan on 7/4/2017.
 */

public class TestClickNotificationActivity extends ClickNotificationActivity {

    private static final String TAG = "TestClickNotificationActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.e(TAG , "onCreate");
    }
}
