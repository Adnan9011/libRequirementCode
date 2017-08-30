package ir.adnan.lib_requirement_mvvm.activity;

import android.app.Activity;
import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Adnan on 8/28/2017.
 */

public class LibraryLifecycleActivity extends LifecycleActivity {

    private Context THIS ;
    private LifecycleActivity THIS_ACTIVITY ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        THIS = this;
        THIS_ACTIVITY = this;
    }

    /*
     * get ViewModelProvide
     */
    public <T extends ViewModel> T getViewModelProvider (Class <T> className) {
        return ViewModelProviders.of(THIS_ACTIVITY).get(className);
    }
}
