//package ir.adnan.lib_requirement_code.di.module;
//
//import android.app.Application;
//import android.content.SharedPreferences;
//import android.preference.PreferenceManager;
//
//import com.google.gson.FieldNamingPolicy;
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//
//import javax.inject.Singleton;
//
//import dagger.Module;
//import dagger.Provides;
//import ir.adnan.lib_requirement_code.core.Log;
//import okhttp3.OkHttpClient;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;
//
///**
// * Created by Adnan on 9/16/2017.
// */
//
//@Module
//public class NetModule {
//
//    String mBaseUrl;
//
//    public NetModule(String baseUrl) {
//        if(baseUrl != null) {
//            Log.e("%%% ", "baseUrl is exist");
//        } else {
//            Log.e("%%% ", "baseUrl is exist");
//        }
//        this.mBaseUrl = baseUrl;
//    }
//
//    @Provides  // Dagger will only look for methods annotated with @Provides
//    @Singleton
//    Gson provideGson() {
//        Log.e("%%% ","provideGson()");
//
//        GsonBuilder gsonBuilder = new GsonBuilder();
//        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
//        return gsonBuilder.create();
//    }
//
//    @Provides
//    @Singleton
//    Retrofit provideRetrofit() {
//        Log.e("%%% ","provideRetrofit()");
//        Retrofit retrofit = new Retrofit.Builder()
//                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
//                .baseUrl(mBaseUrl)
//                .client(new OkHttpClient())
//                .build();
//        return retrofit;
//    }
//}