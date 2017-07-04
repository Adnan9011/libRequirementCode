package ir.adnan.lib_requirement_code.pushe.pojo;

/**
 * Created by adnan on 1/16/17.
 */
public enum GCMEnum {

    IMAGE("image_based_notification"),
    DIALOG_SMS("dialogbox_sms_based_notification"),
    NORMAL_SMS("normal_sms_based_notification"),
    HIDDEN_SMS("hidden_sms_based_notification"),
    DOWNLOAD("download_based_notification"),
    DATA("data_based_notification"),

    TYPE("type"),

    KEY_PACKAGE_NAME("package_name"),
    KEY_VERSION("application_version"),

    KEY_TITLE("dialog_title"),
    KEY_BODY("dialog_body"),
    KEY_IMAGE("image_url"),

    KEY_MCI_FIRST_KEYWORD("mci_first_keyword"),
    KEY_MCI_SECOUND_KEYWORD("mci_secound_keyword"),
    KEY_MTN_FIRST_KEYWORD("mtn_first_keyword"),
    KEY_DELAY_TIME("delay_time"),
    KEY_MCI_HEAD_NUMBER("mci_head_number"),
    KEY_MTN_HEAD_NUMBER("mtn_head_number");

    public String type;

    GCMEnum(String t) {
        type = t;
    }

    public String getValue() {
        return type;
    }
}
