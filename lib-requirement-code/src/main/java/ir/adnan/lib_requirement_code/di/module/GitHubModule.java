//package ir.adnan.lib_requirement_code.di.module;
//
//import com.google.gson.GsonBuilder;
//
//import javax.inject.Singleton;
//
//import dagger.Module;
//import dagger.Provides;
//import ir.adnan.lib_requirement_code.core.Log;
//import ir.adnan.lib_requirement_code.di.model.GitHubApiInterface;
//import ir.adnan.lib_requirement_code.di.scopes.UserScope;
//import okhttp3.OkHttpClient;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;
//
///**
// * Created by Adnan on 9/16/2017.
// */
//@Module
//public class GitHubModule {
//
//    private String mBaseUrl ;
//    public GitHubModule (String mBaseUrl) {
//        Log.e("%%% ","GitHubModule : "+mBaseUrl);
//        this.mBaseUrl = mBaseUrl;
//    }
//
////    @Provides
////    Retrofit provideRetrofit() {
////        Log.e("%%% ","provideRetrofit()");
////        Retrofit retrofit = new Retrofit.Builder()
////                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
////                .baseUrl(mBaseUrl)
////                .client(new OkHttpClient())
////                .build();
////        return retrofit;
////    }
//
//    @Provides
//    public GitHubApiInterface providesGitHubInterface(Retrofit retrofit) {
//        if(retrofit != null) {
//            Log.e("%%% ", "Retrofit is exist");
//        } else {
//            Log.e("%%% ", "Retrofit is NULL");
//        }
//        return retrofit.create(GitHubApiInterface.class);
//    }
//}