package ir.adnan.lib_requirement_code.pushe.webservice;

/**
 * Created by adnan on 12/12/16.
 */
public enum HandleTypeResponseEnum {
    OK (200) , SignOut (500) , Error (404) , Main(100);

    int code;

    HandleTypeResponseEnum(int x) {
        code = x;
    }

    public int getValue() {
        return code;
    }
}
