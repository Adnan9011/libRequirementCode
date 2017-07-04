package ir.adnan.lib_requirement_code.pushe.webservice;

/**
 * Created by adnan on 12/18/16.
 */
public class HandleWebservice {
    private String text;
    private HandleTypeResponseEnum typeResponseEnum;

    public HandleWebservice() {}

    public HandleWebservice(String text, HandleTypeResponseEnum typeResponseEnum) {
        this.text = text;
        this.typeResponseEnum = typeResponseEnum;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public HandleTypeResponseEnum getTypeResponseEnum() {
        return typeResponseEnum;
    }

    public void setTypeResponseEnum(HandleTypeResponseEnum typeResponseEnum) {
        this.typeResponseEnum = typeResponseEnum;
    }
}
