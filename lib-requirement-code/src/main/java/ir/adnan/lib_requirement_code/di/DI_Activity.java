//package ir.adnan.lib_requirement_code.di;
//
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v7.app.AppCompatActivity;
//import android.util.Log;
//
//import java.util.ArrayList;
//
//import javax.inject.Inject;
//
//import ir.adnan.lib_requirement_code.di.components.DaggerGitHubComponent;
//import ir.adnan.lib_requirement_code.di.components.DaggerNetComponent;
//import ir.adnan.lib_requirement_code.di.components.GitHubComponent;
//import ir.adnan.lib_requirement_code.di.components.NetComponent;
//import ir.adnan.lib_requirement_code.di.model.GitHubApiInterface;
//import ir.adnan.lib_requirement_code.di.model.Repository;
//import ir.adnan.lib_requirement_code.di.module.AppModule;
//import ir.adnan.lib_requirement_code.di.module.GitHubModule;
//import ir.adnan.lib_requirement_code.di.module.NetModule;
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
///**
// * Created by Adnan on 9/16/2017.
// */
//
//public class DI_Activity extends AppCompatActivity {
//
//    @Inject
//    GitHubApiInterface mGitHubApiInterface;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
////
//        NetComponent mNetComponent = DaggerNetComponent.builder()
//                .appModule(new AppModule(getApplication()))
//                .netModule(new NetModule("https://api.github.com"))
//                .build();
//
//        GitHubComponent mGitHubComponent = DaggerGitHubComponent.builder()
//                .netComponent(mNetComponent)
//                .gitHubModule(new GitHubModule("https://api.github.com"))
//                .build();
//
//        mGitHubComponent.inject(this);
//
//        if (mGitHubApiInterface != null) {
//
//            Call<ArrayList<Repository>> call = mGitHubApiInterface.getRepository("codepath");
//            call.enqueue(new Callback<ArrayList<Repository>>() {
//                @Override
//                public void onResponse(Call<ArrayList<Repository>> call, Response<ArrayList<Repository>> response) {
//                    if (response.isSuccessful()) {
//                        Log.e("%%% %%% DEBUG", response.body().toString());
//                    } else {
//                        Log.e("%%% %%% ERROR", String.valueOf(response.code()));
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<ArrayList<Repository>> call, Throwable t) {
//
//                }
//            });
//        } else {
//            Log.e("%%% %%%", "mGitHubApiInterface is Null");
//        }
//    }
//}
