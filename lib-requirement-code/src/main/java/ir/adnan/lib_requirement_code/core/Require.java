package ir.adnan.lib_requirement_code.core;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Vibrator;
import android.provider.Settings;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Base64;

import java.io.File;
import java.io.UnsupportedEncodingException;

import ir.adnan.lib_requirement_code.data.Preferences;

import static android.content.Context.TELEPHONY_SERVICE;

/**
 * Created by adnan on 12/3/16.
 */
public class Require {

    public static final int CLICK_TIME = 200;
    public static final int CODE_BROADCAST_SENDSMS = 6585;

    public static void generateNotification(Context context, int icon, String title, String message, Uri data , Class<?> targetClass) {
        // Show the notification
            long when = System.currentTimeMillis();
            NotificationManager notificationManager = (NotificationManager)
                    (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

//            Notification notification = new Notification(icon, message, when);
            Notification.Builder notification = new Notification.Builder(context)
                    .setContentText(title)
                    .setContentText(message)
                    .setSmallIcon(icon)
                    .setWhen(when);

            Intent notificationIntent = new Intent(context, targetClass);
            notificationIntent.setData(data);

            // set intent so it does not start a new activity
            notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            PendingIntent intent = PendingIntent.getActivity(context, 0, notificationIntent, 0);
            notification.setContentIntent(intent);
//                    (context, title, message, intent);
                notification.setVibrate(new long[]{1000, 1000});
//          notification.sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//                notification.setSound(Uri.parse("android.resource://"
//                        + context.getPackageName() + "/" + R.raw.notification));

            notificationManager.notify(0, notification.build());

    }

    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public static int pxToDp(int px) {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }

    public static void vibrate(Context context, int duration) {
        if (Preferences.isPrefsVibreteSetting(context)) {
            ((Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE)).vibrate(duration);
        }
    }

    /*
     * Base 64
     */
    public static String fromBase64toString(String base64) {
        String text = null;
        byte[] data = Base64.decode(base64, Base64.DEFAULT);
        try {
            text = new String(data, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return text;
    }

    public static String fromStringtoBase64(String text) {
        byte[] data = new byte[0];
        try {
            data = text.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String base64 = Base64.encodeToString(data, Base64.DEFAULT);
        return base64;
    }

    /*
     * 1000 to 1,000
     */
    public static String getSpliteThuosent(int num) {
        return String.format("%,d", num);
    }

    /*
     * Share Application (APK)
     */
    public void shareApp(Context context , String nameOfApplication) {
        try {
            PackageManager pm = context.getPackageManager();
            ApplicationInfo ai = pm.getApplicationInfo(
                    context.getPackageName(), 0);
            File srcFile = new File(ai.publicSourceDir);
            Intent share = new Intent();
            share.setAction(Intent.ACTION_SEND);
            share.setType("application/vnd.android.package-archive");
            share.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(srcFile));
            context.startActivity(Intent.createChooser(share, nameOfApplication));
        } catch (Exception e) {
            Log.e("ShareApp", e.getMessage());
        }
    }

    /*
     * Check App Installed Or Not
     */
    private boolean checkAppInstalledOrNot(Context context , String uri) {
        PackageManager pm = context.getPackageManager();
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
        }

        return false;
    }

    /*
     * Send Sms
     */
    public static void sendSMS(String phoneNumber, String message) {

        Log.e("sendSMS", "Send Sms to : \n " + phoneNumber + "\n" + message);

        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, null, null);
    }

    /*
     *
     */
    public static String getOperatorName(Context context) {

        if (context == null) {
            return null;
        }

        String operator = ((TelephonyManager) context.getSystemService(TELEPHONY_SERVICE)).getSimOperatorName();
        operator = operator.toLowerCase();

        Log.e("getOperatorName", "op : " + operator);
        if (operator.contains("cell") || operator.contains("mtn")) {
            return "MTN";
        }
        if (operator.contains("mci") || operator.contains("tci")) {
            return "MCI";
        }
        if (operator.contains("right")) {
            return "Rightel";
        }
        return "";
    }

    public static String getAndroidId(Context context) {
        return Settings.Secure.getString(
                context.getContentResolver(),
                Settings.Secure.ANDROID_ID);
    }

    public static String getDeviceModel() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return capitalize(model);
        } else {
            return capitalize(manufacturer) + " " + model;
        }
    }

    public static String capitalize(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        char first = s.charAt(0);
        if (Character.isUpperCase(first)) {
            return s;
        } else {
            return Character.toUpperCase(first) + s.substring(1);
        }
    }


}
