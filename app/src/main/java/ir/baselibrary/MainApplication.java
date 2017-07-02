package ir.baselibrary;

import ir.adnan.lib_requirement_code.main.LibraryApplication;

/**
 * Created by Adnan on 7/2/2017.
 */

public class MainApplication extends LibraryApplication {
    @Override
    public void onCreate() {
        super.onCreate();

        launchFabricCrashlytics(this);
    }
}
