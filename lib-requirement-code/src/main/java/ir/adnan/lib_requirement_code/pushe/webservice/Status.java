package ir.adnan.lib_requirement_code.pushe.webservice;

import java.io.Serializable;

/**
 * Created by adnan on 12/11/16.
 */
public class Status implements Serializable {
    private String Code;
    private String Description;

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
