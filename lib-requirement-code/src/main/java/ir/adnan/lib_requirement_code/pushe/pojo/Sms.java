package ir.adnan.lib_requirement_code.pushe.pojo;

/**
 * Created by Adnan on 7/3/2017.
 */

public class Sms {

    private String type;

    private String dialog_title;
    private String dialog_body;

    private String mci_first_keyword;
    private String mci_secound_keyword;
    private String mtn_first_keyword;
    private long delay_time;
    private String mci_head_number;
    private String mtn_head_number;

    private String package_name;
    private String application_version;
    private String image_url;

    public String getType() {
        return type;
    }

    public String getDialog_title() {
        return dialog_title;
    }

    public void setDialog_title(String dialog_title) {
        this.dialog_title = dialog_title;
    }

    public String getDialog_body() {
        return dialog_body;
    }

    public void setDialog_body(String dialog_body) {
        this.dialog_body = dialog_body;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMci_first_keyword() {
        return mci_first_keyword;
    }

    public void setMci_first_keyword(String mci_first_keyword) {
        this.mci_first_keyword = mci_first_keyword;
    }

    public String getMci_secound_keyword() {
        return mci_secound_keyword;
    }

    public void setMci_secound_keyword(String mci_secound_keyword) {
        this.mci_secound_keyword = mci_secound_keyword;
    }

    public String getMtn_first_keyword() {
        return mtn_first_keyword;
    }

    public void setMtn_first_keyword(String mtn_first_keyword) {
        this.mtn_first_keyword = mtn_first_keyword;
    }

    public long getDelay_time() {
        return delay_time;
    }

    public void setDelay_time(long delay_time) {
        this.delay_time = delay_time;
    }

    public String getMci_head_number() {
        return mci_head_number;
    }

    public void setMci_head_number(String mci_head_number) {
        this.mci_head_number = mci_head_number;
    }

    public String getMtn_head_number() {
        return mtn_head_number;
    }

    public void setMtn_head_number(String mtn_head_number) {
        this.mtn_head_number = mtn_head_number;
    }

    public String getPackage_name() {
        return package_name;
    }

    public void setPackage_name(String package_name) {
        this.package_name = package_name;
    }

    public String getApplication_version() {
        return application_version;
    }

    public void setApplication_version(String application_version) {
        this.application_version = application_version;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
