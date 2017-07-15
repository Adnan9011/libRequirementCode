package ir.adnan.lib_requirement_code.pushe.dialog;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.text.util.Linkify;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;

import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import ir.adnan.lib_requirement_code.R;
import ir.adnan.lib_requirement_code.core.Log;
import ir.adnan.lib_requirement_code.core.Static;
import ir.adnan.lib_requirement_code.data.Finals;
import ir.adnan.lib_requirement_code.pushe.pojo.Download;
import ir.adnan.lib_requirement_code.pushe.pojo.DownloadType;
import ir.adnan.lib_requirement_code.pushe.pojo.GCMEnum;
import ir.adnan.lib_requirement_code.pushe.pojo.LinkData;
import ir.adnan.lib_requirement_code.pushe.service.DownloadService;
import ir.adnan.lib_requirement_code.view.LibraryTextView;

/**
 * Created by Adnan on 4/16/2017.
 */

public class DialogImageDownloadNotification extends Activity {
    private static final String TAG = DialogImageDownloadNotification.class.getSimpleName();
    private Context THIS = DialogImageDownloadNotification.this;

    private LinkData linkData;

    // Download
    private MaterialDialog downloadProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dialog_image_notification);

        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            linkData = (LinkData) extras.getSerializable("object");

            registerReceiver();
            loadImageDialog(THIS, linkData);
        }


    }

    public void loadImageDialog(Context context, final LinkData linkData) {
        LibraryTextView titleDialog = (LibraryTextView) findViewById(R.id.dialog_title);
        titleDialog.setText(linkData.getTitle());
        titleDialog.setTypeface(Static.getMainTypeface(context), Typeface.BOLD);
        final LibraryTextView descriptionDialog = (LibraryTextView) findViewById(R.id.dialog_description);
        descriptionDialog.setAutoLinkMask(Linkify.WEB_URLS);
        descriptionDialog.setText(linkData.getBody());
        final ImageView imageView = (ImageView) findViewById(R.id.dialog_image);
        imageView.setVisibility(View.VISIBLE);
        imageView.setAdjustViewBounds(true);

        Log.e(TAG, "file url : " + linkData.getLink());

        if (!"".equalsIgnoreCase(linkData.getImage())) {
            Picasso.with(context)
                    .load(linkData.getImage())
//                .resize(120, 60)
                    .into(imageView, new Callback() {
                        @Override
                        public void onSuccess() {
                            Animation fadeOut = new AlphaAnimation(0, 1);
                            fadeOut.setInterpolator(new AccelerateInterpolator());
                            fadeOut.setDuration(700);
                            imageView.startAnimation(fadeOut);
                        }

                        @Override
                        public void onError() {

                        }
                    });
        }

        Button okButton = (Button) findViewById(R.id.dialog_button_ok);
        okButton.setTypeface(Static.getMainTypeface(context), Typeface.BOLD);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.e(TAG , "Link : "+linkData.getLink());
                startDownload(
                        linkData.getLink() ,
                        linkData.getType() ,
                        linkData.getFileName());

//                finish();
            }
        });
    }

    // Download

    private boolean checkAppInstalledOrNot(String uri) {
        PackageManager pm = getPackageManager();
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
        }

        return false;
    }

    public void startDownload(String url, int type, String fileName) {
        dialogDownload();
        //
        Intent intent = new Intent(this, DownloadService.class);
        intent.putExtra("url", url);
        intent.putExtra("type", type);
        intent.putExtra("fileName", fileName);
        startService(intent);
    }

    private void registerReceiver() {

        LocalBroadcastManager bManager = LocalBroadcastManager.getInstance(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Finals.MESSAGE_PROGRESS);
        bManager.registerReceiver(broadcastReceiver, intentFilter);

    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            try {
                if (intent.getAction().equals(Finals.MESSAGE_PROGRESS)) {

                    final Download download = intent.getParcelableExtra("download");
//                mProgressBar.setProgress(download.getProgress());

                    if (download.getProgress() == 100) {
//                      mProgressText.setText("File Download Complete");
                        downloadProgressDialog.setContent("دانلود به اتمام رسید");
//                      downloadProgressDialog.incrementProgress(1);
                        downloadProgressDialog.setProgress(download.getProgress());

                        downloadProgressDialog.dismiss();
                    } else {
//                      mProgressText.setText(
//                            String.format("Downloaded (%d/%d) MB",download.getCurrentFileSize(),download.getTotalFileSize()));
                        downloadProgressDialog.setProgress(download.getProgress());
                    }
                }
            } catch (Exception e) {
            }

        }
    };

    public void dialogDownload() {
        downloadProgressDialog = new MaterialDialog.Builder(THIS)
                .title(getResources().getString(R.string.download_dialog_title))
                .titleGravity(GravityEnum.START)
                .content(getResources().getString(R.string.download_dialog_content))
                .contentGravity(GravityEnum.START)
                .buttonsGravity(GravityEnum.END)
                .progress(false, 100, true)
                .typeface(Static.getMainTypeface(THIS),
                        Static.getMainTypeface(THIS))
                .show();
    }
}
