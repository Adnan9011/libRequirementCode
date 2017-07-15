package ir.adnan.lib_requirement_code.pushe.service;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import ir.adnan.lib_requirement_code.R;
import ir.adnan.lib_requirement_code.core.Log;
import ir.adnan.lib_requirement_code.data.Finals;
import ir.adnan.lib_requirement_code.pushe.pojo.Download;
import ir.adnan.lib_requirement_code.pushe.pojo.DownloadType;
import ir.adnan.lib_requirement_code.pushe.webservice.WebServiceInterface;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;


public class DownloadService extends IntentService {

    private static final String TAG = "DownloadService";//.class.getSimpleName();

    public DownloadService() {
        super("Download Service");
    }
//
//    private NotificationCompat.Builder notificationBuilder;
    private NotificationManager notificationManager;
    private int totalFileSize;

    private File outputFile, tempFile;

    private String url, fileName;
    private int type;

    @Override
    protected void onHandleIntent(Intent intent) {

        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        url = intent.getExtras().getString("url");
        type = intent.getExtras().getInt("type");
        fileName = intent.getExtras().getString("fileName");

        String name = "";
        if (type == DownloadType.APK.getValue()) {
            name = getResources().getString(R.string.download_default_name_application) + ".apk";
        } else if (type == DownloadType.PDF.getValue()) {
            name = fileName + ".pdf";
        }
        //
        File directory = Environment.getExternalStorageDirectory();
        File tempFolder = new File(directory + File.separator + Finals.FOLDER_TEMP_NAME);
        if (!tempFolder.exists()) {
            tempFolder.mkdirs();
        }
        tempFile = new File(tempFolder + File.separator + name);
        //
        outputFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), name);

        if (outputFile.exists() && type == DownloadType.PDF.getValue()) {

            Download download = new Download();
            download.setProgress(100);
            sendIntent(download);

            try {
                startActivity(getIntent(type));
            } catch (Exception e) {
                Log.e(TAG , "Device doesn't have PDF Reader");
            }
        } else {
            //
            Log.e(TAG , "getApplicationContext() = "+getApplicationContext());
            Log.e(TAG , "intent = "+intent);
            //
//            notificationBuilder = new NotificationCompat.Builder(this)
//                    .setSmallIcon(R.drawable.ic_download)
//                    .setContentTitle(getBaseContext().getResources().getString(R.string.app_name))
//                    .setContentText(getResources().getString(R.string.downloadservice_file_downloding))
//                    .setContentIntent(
//                            PendingIntent.
//                                    getActivity
//                                            (getApplicationContext(),
//                                                    0,
//                                                    getIntent(type)
//                                                    , 0))
//                    .setAutoCancel(true);
//            notificationManager.notify(0, notificationBuilder.build());

            initDownload();
        }
    }

    private void initDownload() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://download.learn2crack.com/")
                .build();

        WebServiceInterface webServiceInterface = retrofit.create(WebServiceInterface.class);

        Call<ResponseBody> request = webServiceInterface.downloadFile(url);
        try {

            downloadFile(request.execute().body());

        } catch (IOException e) {

            e.printStackTrace();
//            Toast.makeText(getApplicationContext(),e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void downloadFile(ResponseBody body) throws IOException {

        int count;
        byte data[] = new byte[1024 * 4];
        long fileSize = body.contentLength();
        InputStream bis = new BufferedInputStream(body.byteStream(), 1024 * 8);
        //
        OutputStream output = new FileOutputStream(tempFile);
        long total = 0;
        long startTime = System.currentTimeMillis();
        int timeCount = 1;
        while ((count = bis.read(data)) != -1) {

            total += count;
            totalFileSize = (int) (fileSize / (Math.pow(1024, 2)));
            double current = Math.round(total / (Math.pow(1024, 2)));

            int progress = (int) ((total * 100) / fileSize);

            long currentTime = System.currentTimeMillis() - startTime;

            Download download = new Download();
            download.setTotalFileSize(totalFileSize);

            if (currentTime > 100 * timeCount) {

                download.setCurrentFileSize((int) current);
                download.setProgress(progress);
                sendNotification(download);
                timeCount++;
            }
            output.write(data, 0, count);
        }
        onDownloadComplete();
        output.flush();
        output.close();
        bis.close();
    }


    private void sendNotification(Download download) {

        Log.e(TAG , "Download : "+download.getProgress());

        sendIntent(download);
//        notificationBuilder.setProgress(100, download.getProgress(), false);
//        notificationBuilder.setContentText(download.getProgress() + " %" + getResources().getString(R.string.downloadservice_file_downloding));
//        notificationManager.notify(0, notificationBuilder.build());
    }

    private void sendIntent(Download download) {

        Intent intent = new Intent(Finals.MESSAGE_PROGRESS);
        intent.putExtra("download", download);
        LocalBroadcastManager.getInstance(DownloadService.this).sendBroadcast(intent);
    }

    private void onDownloadComplete() {

        Download download = new Download();
        download.setProgress(100);
        sendIntent(download);

        notificationManager.cancel(0);
//        notificationBuilder.setProgress(0, 0, false);
//        notificationBuilder.setContentText(getResources().getString(R.string.downloadservice_file_downloaded));
//        notificationManager.notify(0, notificationBuilder.build());

        moveFileFromTempToDownload();

//        //Install App
//        Intent install = new Intent(Intent.ACTION_VIEW);
//        install.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        install.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//
//        if(type == DownloadType.APK.getValue()) {
//            install.setDataAndType(Uri.parse("file://"+outputFile.toString()),"application/vnd.android.package-archive");
//        } else if (type == DownloadType.PDF.getValue()) {
//            install.setDataAndType(Uri.parse("file://"+outputFile.toString()),"application/pdf");
//        }
//        install.setDataAndType(Uri.parse("file://"+outputFile.toString()),"application/vnd.android.package-archive");
        try {
            startActivity(getIntent(type));
        } catch (Exception e) {

        }

    }

    private void moveFileFromTempToDownload() {
        InputStream in = null;
        OutputStream out = null;
        try {


            in = new FileInputStream(tempFile);
            out = new FileOutputStream(outputFile);

            byte[] buffer = new byte[1024];
            int read;
            while ((read = in.read(buffer)) != -1) {
                out.write(buffer, 0, read);
            }
            in.close();
            in = null;

            // write the output file
            out.flush();
            out.close();
            out = null;

            // delete the original file
            tempFile.delete();


        } catch (FileNotFoundException fnfe1) {
            Log.e(TAG, fnfe1.getMessage());
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }

    public Intent getIntent(int type) {
        Intent install = new Intent(Intent.ACTION_VIEW);
        install.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        install.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        if (type == DownloadType.APK.getValue()) {
            return install.setDataAndType(Uri.parse("file://" + outputFile.toString()), "application/vnd.android.package-archive");
        } else if (type == DownloadType.PDF.getValue()) {
            return install.setDataAndType(Uri.parse("file://" + outputFile.toString()), "application/pdf");
        } else {
            return null;
        }
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        notificationManager.cancel(0);
    }

}
