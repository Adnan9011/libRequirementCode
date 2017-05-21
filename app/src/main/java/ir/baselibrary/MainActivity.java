package ir.baselibrary;

import android.app.Activity;
import android.os.Bundle;

import ir.adnan.lib_requirement_code.core.Log;
import ir.adnan.lib_requirement_code.core.Require;
import ir.adnan.lib_requirement_code.core.Static;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.e("TAG " , getResources().getString(R.string.toolbar_title_subproduct));
    }
}
