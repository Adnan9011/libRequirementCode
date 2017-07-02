package ir.baselibrary.data;

/**
 * Created by Adnan on 7/2/2017.
 */

public enum PreferencesEnum {
    INT("test_int"), STRING("test_string"), Boolean("test_boolean");

    public String type;

    PreferencesEnum(String t) {
        type = t;
    }

    public String getValue() {
        return type;
    }
}
