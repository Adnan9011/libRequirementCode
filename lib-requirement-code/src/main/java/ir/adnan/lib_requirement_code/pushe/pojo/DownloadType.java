package ir.adnan.lib_requirement_code.pushe.pojo;

/**
 * Created by Adnan on 3/28/2017.
 */

public enum DownloadType {
    APK (1) , PDF (2);

    int code;

    DownloadType(int x) {
        code = x;
    }

    public int getValue() {
        return code;
    }
}
