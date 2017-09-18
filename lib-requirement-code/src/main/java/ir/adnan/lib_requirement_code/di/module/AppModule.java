//package ir.adnan.lib_requirement_code.di.module;
//
//import android.app.Application;
//
//import javax.inject.Singleton;
//
//import dagger.Module;
//import dagger.Provides;
//import ir.adnan.lib_requirement_code.core.Log;
//
///**
// * Created by Adnan on 9/16/2017.
// */
//@Module
//public class AppModule {
//
//    Application mApplication;
//
//    public AppModule(Application application) {
//        if(mApplication != null) {
//            Log.e("%%% ", "application is exist");
//        } else {
//            Log.e("%%% ", "application is exist");
//        }
//        mApplication = application;
//    }
//
//    @Provides
//    @Singleton
//    Application providesApplication() {
//        if(mApplication != null) {
//            Log.e("%%% ", "mApplication is exist");
//        } else {
//            Log.e("%%% ", "mApplication is exist");
//        }
//        return mApplication;
//    }
//}