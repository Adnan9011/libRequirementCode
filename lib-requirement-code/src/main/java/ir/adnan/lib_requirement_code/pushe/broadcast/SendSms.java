package ir.adnan.lib_requirement_code.pushe.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import ir.adnan.lib_requirement_code.core.Require;

/**
 * Created by Adnan on 7/4/2017.
 */

public class SendSms extends BroadcastReceiver {

    private static final String TAG = "SendSmsBroad";
    private Context context;

    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;

        String number , message ;
        if(intent != null) {
            number = intent.getExtras().getString("number");
            message = intent.getExtras().getString("message");
            //
            Require.sendSMS(number , message);
        }
    }
}
