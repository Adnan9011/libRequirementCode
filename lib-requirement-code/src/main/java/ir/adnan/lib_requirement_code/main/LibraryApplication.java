package ir.adnan.lib_requirement_code.main;

/**
 * Created by Adnan on 9/18/2017.
 */
import android.app.Application;
import android.content.Context;

import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;

public class LibraryApplication extends Application {

    private static Context THIS_APPLICATION = null;

    @Override
    public void onCreate() {
        super.onCreate();
        THIS_APPLICATION = this;
    }

    public static Context  getContext () {
        return THIS_APPLICATION;
    }

    protected void launchFabricCrashlytics (Context context , boolean debuggable) {

        final Fabric fabric = new Fabric.Builder(context)
                .kits(new Crashlytics())
                .debuggable(debuggable)
                .build();
        Fabric.with(fabric);
    }

}