package ir.adnan.lib_requirement_code.pushe.dialog;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import ir.adnan.lib_requirement_code.R;
import ir.adnan.lib_requirement_code.core.Log;
import ir.adnan.lib_requirement_code.core.Static;
import ir.adnan.lib_requirement_code.pushe.pojo.GCMEnum;
import ir.adnan.lib_requirement_code.view.LibraryTextView;

/**
 * Created by Adnan on 4/16/2017.
 */

public class DialogImageNotification extends Activity {
    private static final String TAG = DialogImageNotification.class.getSimpleName();
    private Context THIS = DialogImageNotification.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dialog_image_notification);

        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String type = extras.getString(GCMEnum.TYPE.getValue());
            final String title = extras.getString(GCMEnum.KEY_TITLE.getValue());
            final String body = extras.getString(GCMEnum.KEY_BODY.getValue());
            final String imageUrl = extras.getString(GCMEnum.KEY_IMAGE.getValue());
            //
            if(type.equalsIgnoreCase(GCMEnum.IMAGE.getValue())) {
                loadImageDialog(THIS , title , body , imageUrl);
            }
        }
    }

    public void loadImageDialog(Context context , String title, final String body , String url) {
        LibraryTextView titleDialog = (LibraryTextView) findViewById(R.id.dialog_title);
        titleDialog.setText(title);
        titleDialog.setTypeface(Static.getMainTypeface(context), Typeface.BOLD);
        final LibraryTextView descriptionDialog = (LibraryTextView) findViewById(R.id.dialog_description);
        descriptionDialog.setAutoLinkMask(Linkify.WEB_URLS);
        descriptionDialog.setText(body);
        final ImageView imageView = (ImageView) findViewById(R.id.dialog_image);
        imageView.setVisibility(View.VISIBLE);
        imageView.setAdjustViewBounds(true);

        Log.e(TAG , "url : "+url);
        Picasso.with(context)
                .load(url)
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

        Button okButton = (Button) findViewById(R.id.dialog_button_ok);
        okButton.setTypeface(Static.getMainTypeface(context), Typeface.BOLD);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
