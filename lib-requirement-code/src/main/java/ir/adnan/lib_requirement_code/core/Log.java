package ir.adnan.lib_requirement_code.core;

import ir.adnan.lib_requirement_code.data.Finals;

/**
 * Created by adnan on 2/21/17.
 */

public class Log {

    public static void e (String tag , String log) {
        if(Finals.isDebuggable) {
            android.util.Log.e(tag, log);
        }
    }
}
