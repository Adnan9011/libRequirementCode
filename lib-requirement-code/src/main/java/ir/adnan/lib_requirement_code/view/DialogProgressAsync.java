package ir.adnan.lib_requirement_code.view;

import android.content.Context;

import com.afollestad.materialdialogs.MaterialDialog;
import com.wang.avi.AVLoadingIndicatorView;

import ir.baselibrary.R;
import ir.adnan.lib_requirement_code.core.Static;


/**
 * Created by Adnan on 4/3/2017.
 */

public class DialogProgressAsync {

//    private static DialogProgressAsync instance = null;

    private Context context;
    private MaterialDialog dialogLoading;

    private AltonTextView title;
    private AVLoadingIndicatorView progress;

//    public static DialogProgressAsync getInstance(Context context) {
//        if(instance == null) {
//            instance = new DialogProgressAsync(context);
//        }
//        return instance;


//    }

    public DialogProgressAsync(Context context) {
        this.context = context;

        onPre();
    }



    protected void onPre() {

        dialogLoading = new MaterialDialog.Builder(context)
                .autoDismiss(false)
                .cancelable(false)
                .customView(R.layout.custom_loading_dialog, false)
                .build()
        ;

        title = (AltonTextView) dialogLoading.findViewById(R.id.progressBarTitle);
        title.setTypeface(Static.getMainTypefaceBold(context));

        progress = (AVLoadingIndicatorView) dialogLoading.findViewById(R.id.progressBar);
        progress.show();
    }

    public void show() {
        dialogLoading.show();
    }
    public void dismiss() {
        dialogLoading.dismiss();
    }
}
