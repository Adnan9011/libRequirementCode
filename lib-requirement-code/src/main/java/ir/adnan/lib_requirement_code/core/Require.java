package ir.adnan.lib_requirement_code.core;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Vibrator;
import android.util.Base64;

import java.io.UnsupportedEncodingException;

import ir.adnan.lib_requirement_code.data.Preferences;
import ir.baselibrary.MainActivity;

/**
 * Created by adnan on 12/3/16.
 */
public class Require {

    public static final int CLICK_TIME = 200;

    public static String changeNumberEnToFa(String str) {
        StringBuilder builder = new StringBuilder();

        if (!"null".equalsIgnoreCase(str) && str != null && !"".equalsIgnoreCase(str)) {
            char[] arabicChars = {'۰', '۱', '۲', '۳', '۴', '۵', '۶', '۷', '۸', '۹'};

            for (int i = 0; i < str.length(); i++) {
                if (Character.isDigit(str.charAt(i)) && ((int) str.charAt(i)) < 58 && ((int) str.charAt(i)) > 47) {
//                    Log.e("Adnan","c : "+str.charAt(i));
//                    Log.e("Adnan","i : "+(int) str.charAt(i));
                    builder.append(arabicChars[(int) (str.charAt(i)) - 48]);
                } else {
                    builder.append(str.charAt(i));
                }
            }
        }
        return builder.toString();
    }

    public static String changeNumberFaToEn(String str) {
        StringBuilder builder = new StringBuilder();

        if (!"null".equalsIgnoreCase(str) && str != null && !"".equalsIgnoreCase(str)) {
            char[] arabicChars = {'۰', '۱', '۲', '۳', '۴', '۵', '۶', '۷', '۸', '۹'};

            for (int i = 0; i < str.length(); i++) {
//                if (Character.isDigit(str.charAt(i)) && ((int) str.charAt(i)) < 58 && ((int) str.charAt(i)) > 47) {
////                    Log.e("Adnan","c : "+str.charAt(i));
////                    Log.e("Adnan","i : "+(int) str.charAt(i));
//                    builder.append(arabicChars[(int) (str.charAt(i)) - 48]);
//                } else {
//                    builder.append(str.charAt(i));
//                }
                switch (str.charAt(i)) {
                    case '۰':
                        builder.append("0");
                        break;
                    case '۱':
                        builder.append("1");
                        break;
                    case '۲':
                        builder.append("2");
                        break;
                    case '۳':
                        builder.append("3");
                        break;
                    case '۴':
                        builder.append("4");
                        break;
                    case '۵':
                        builder.append("5");
                        break;
                    case '۶':
                        builder.append("6");
                        break;
                    case '۷':
                        builder.append("7");
                        break;
                    case '۸':
                        builder.append("8");
                        break;
                    case '۹':
                        builder.append("9");
                        break;
                    case ',':
                        break;
                    default:
                        builder.append(str.charAt(i));
                        break;

                }
            }
        }
        return builder.toString();
    }

//    public static DisplayImageOptions getImageLoaderDisplayImageOptions() {
//        return new DisplayImageOptions.Builder()
//                .showImageOnLoading(R.drawable.a1000bazaar_preload)
//                .showImageForEmptyUri(R.drawable.a1000bazaar_preload)
//                .showImageOnFail(R.drawable.a1000bazaar_preload)
//                .cacheInMemory(true)
//                .cacheOnDisk(true)
//                .considerExifParams(true)
////                .displayer(new CircleBitmapDisplayer(Color.WHITE, 5))
//                .build();
//    }

//    public static ImageLoader imageLoader(Context c) {
//        ImageLoader imageLoader = ImageLoader.getInstance(); // Get singleton instance
//        //
//        DisplayImageOptions options = new DisplayImageOptions.Builder()
//                .showImageOnLoading(R.drawable.a1000bazaar_preload) // resource or drawable
//                .showImageForEmptyUri(R.drawable.a1000bazaar_preload) // resource or drawable
//                .showImageOnFail(R.drawable.a1000bazaar_preload) // resource or drawable
//                .displayer(new FadeInBitmapDisplayer(1000))
////                .resetViewBeforeLoading()
//                .cacheInMemory(true)
////                .cacheOnDisc(true)
//                .build();
//        //
//        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder
//                (c)
//                .memoryCacheExtraOptions(1000, 800) // default = device screen dimensions
//                .diskCacheExtraOptions(1000, 800, null)
//                .threadPoolSize(3) // default
//                .threadPriority(Thread.NORM_PRIORITY - 1) // default
//                .denyCacheImageMultipleSizesInMemory()
//                .memoryCache(new LruMemoryCache(2 * 1024 * 1024))
//                .memoryCacheSize(2 * 1024 * 1024)
//                .memoryCacheSizePercentage(13) // default
//                .diskCacheSize(5 * 1024 * 1024)
//                .diskCacheFileCount(50)
//                .diskCacheFileNameGenerator(new HashCodeFileNameGenerator()) // default
//                .imageDownloader(new BaseImageDownloader(c)) // default
//                .defaultDisplayImageOptions(options)
//                .build();
//        imageLoader.init(config);
//
//        //
//        return imageLoader;
//    }
//
//    public static ImageLoader imageLoaderNoCache(Context c) {
//        ImageLoader imageLoader = ImageLoader.getInstance(); // Get singleton instance
//        //
//        DisplayImageOptions options = new DisplayImageOptions.Builder()
//                .showImageOnLoading(R.drawable.a1000bazaar_preload) // resource or drawable
//                .showImageForEmptyUri(R.drawable.a1000bazaar_preload) // resource or drawable
//                .showImageOnFail(R.drawable.a1000bazaar_preload) // resource or drawable
//                .displayer(new FadeInBitmapDisplayer(1000))
////                .resetViewBeforeLoading()
////                .cacheInMemory(true)
////                .cacheOnDisc(true)
//                .build();
//        //
//        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder
//                (c)
//                .memoryCacheExtraOptions(1000, 800) // default = device screen dimensions
//                .diskCacheExtraOptions(1000, 800, null)
//                .threadPoolSize(3) // default
//                .threadPriority(Thread.NORM_PRIORITY - 1) // default
//                .denyCacheImageMultipleSizesInMemory()
////                .memoryCache(new LruMemoryCache(2 * 1024 * 1024))
////                .memoryCacheSize(2 * 1024 * 1024)
////                .memoryCacheSizePercentage(13) // default
////                .diskCacheSize(5 * 1024 * 1024)
////                .diskCacheFileCount(50)
////                .diskCacheFileNameGenerator(new HashCodeFileNameGenerator()) // default
//                .imageDownloader(new BaseImageDownloader(c)) // default
//                .defaultDisplayImageOptions(options)
//                .build();
//        imageLoader.init(config);
//
//        //
//        return imageLoader;
//    }

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



}
