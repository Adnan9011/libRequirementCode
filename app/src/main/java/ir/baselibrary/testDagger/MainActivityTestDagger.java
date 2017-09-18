package ir.baselibrary.testDagger;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import ir.adnan.lib_requirement_code.di.DI_Activity;
import ir.baselibrary.R;

/**
 * Created by Adnan on 9/16/2017.
 */

public class MainActivityTestDagger extends DI_Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_test_dagger);

    }
}
