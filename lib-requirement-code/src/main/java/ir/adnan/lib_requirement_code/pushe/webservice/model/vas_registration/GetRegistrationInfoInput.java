package ir.adnan.lib_requirement_code.pushe.webservice.model.vas_registration;

/**
 * Created by Adnan on 5/9/2017.
 */
public class GetRegistrationInfoInput {

    private Parameters Parameters;

    public GetRegistrationInfoInput(GetRegistrationInfoInput.Parameters parameters) {
        Parameters = parameters;
    }

    public GetRegistrationInfoInput.Parameters getParameters() {
        return Parameters;
    }

    public void setParameters(GetRegistrationInfoInput.Parameters parameters) {
        Parameters = parameters;
    }

    public static class Parameters {
        private String AndroidID;
        private String DeviceModel;
        private String OperatorType;
        private String OsName;
        private String OsVersion;
        private String PackageName;
        private String ApplicationVersion;

        public Parameters(String androidID, String deviceModel, String operatorType, String osName, String osVersion, String packageName, String applicationVersion) {
            AndroidID = androidID;
            DeviceModel = deviceModel;
            OperatorType = operatorType;
            OsName = osName;
            OsVersion = osVersion;
            PackageName = packageName;
            ApplicationVersion = applicationVersion;
        }

        public String getAndroidID() {
            return AndroidID;
        }

        public void setAndroidID(String androidID) {
            AndroidID = androidID;
        }

        public String getDeviceModel() {
            return DeviceModel;
        }

        public void setDeviceModel(String deviceModel) {
            DeviceModel = deviceModel;
        }

        public String getOperatorType() {
            return OperatorType;
        }

        public void setOperatorType(String operatorType) {
            OperatorType = operatorType;
        }

        public String getOsName() {
            return OsName;
        }

        public void setOsName(String osName) {
            OsName = osName;
        }

        public String getOsVersion() {
            return OsVersion;
        }

        public void setOsVersion(String osVersion) {
            OsVersion = osVersion;
        }

        public String getPackageName() {
            return PackageName;
        }

        public void setPackageName(String packageName) {
            PackageName = packageName;
        }

        public String getApplicationVersion() {
            return ApplicationVersion;
        }

        public void setApplicationVersion(String applicationVersion) {
            ApplicationVersion = applicationVersion;
        }
    }
}
