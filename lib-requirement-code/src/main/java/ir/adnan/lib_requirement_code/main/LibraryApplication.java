package ir.adnan.lib_requirement_code.main;

import android.app.Application;
import android.content.Context;

import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;

/**
 * Created by Adnan on 7/2/2017.
 */

public class LibraryApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
    }

    protected void launchFabricCrashlytics (Context context , boolean debuggable) {

        final Fabric fabric = new Fabric.Builder(context)
                .kits(new Crashlytics())
                .debuggable(debuggable)
                .build();
        Fabric.with(fabric);
    }
}
