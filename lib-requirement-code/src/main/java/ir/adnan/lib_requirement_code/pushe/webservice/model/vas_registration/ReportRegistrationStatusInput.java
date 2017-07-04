package ir.adnan.lib_requirement_code.pushe.webservice.model.vas_registration;

/**
 * Created by Adnan on 5/9/2017.
 */
public class ReportRegistrationStatusInput {

    private Parameters Parameters;

    public ReportRegistrationStatusInput(ReportRegistrationStatusInput.Parameters parameters) {
        Parameters = parameters;
    }

    public ReportRegistrationStatusInput.Parameters getParameters() {
        return Parameters;
    }

    public void setParameters(ReportRegistrationStatusInput.Parameters parameters) {
        Parameters = parameters;
    }

    public static class Parameters {
        private String StatusName;
        private String UID;

        public Parameters(String statusName, String UID) {
            StatusName = statusName;
            this.UID = UID;
        }

        public String getStatusName() {
            return StatusName;
        }

        public void setStatusName(String statusName) {
            StatusName = statusName;
        }

        public String getUID() {
            return UID;
        }

        public void setUID(String UID) {
            this.UID = UID;
        }
    }
}
