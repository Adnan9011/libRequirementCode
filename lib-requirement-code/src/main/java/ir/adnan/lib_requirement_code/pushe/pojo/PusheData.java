package ir.adnan.lib_requirement_code.pushe.pojo;

import java.util.List;

/**
 * Created by Adnan on 7/3/2017.
 */

public class PusheData {

    private String type;
    private List<Data> Data;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<PusheData.Data> getData() {
        return Data;
    }

    public void setData(List<PusheData.Data> data) {
        Data = data;
    }

    //

    public static class Data {
        private String Key;
        private String Value;
        private String TypeValue;

        public String getKey() {
            return Key;
        }

        public void setKey(String key) {
            Key = key;
        }

        public String getValue() {
            return Value;
        }

        public void setValue(String value) {
            Value = value;
        }

        public String getTypeValue() {
            return TypeValue;
        }

        public void setTypeValue(String typeValue) {
            TypeValue = typeValue;
        }
    }
}
