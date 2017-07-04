package ir.adnan.lib_requirement_code.pushe.webservice.model.vas_registration;

import java.io.Serializable;

import ir.adnan.lib_requirement_code.pushe.webservice.Status;


/**
 * Created by Adnan on 5/9/2017.
 */
public class GetRegistrationInfoOutput implements Serializable{

    private Parameters Parameters;
    private Status Status;

    public GetRegistrationInfoOutput(GetRegistrationInfoOutput.Parameters parameters, Status status) {
        Parameters = parameters;
        Status = status;
    }

    public GetRegistrationInfoOutput.Parameters getParameters() {
        return Parameters;
    }

    public void setParameters(GetRegistrationInfoOutput.Parameters parameters) {
        Parameters = parameters;
    }

    public Status getStatus() {
        return Status;
    }

    public void setStatus(Status status) {
        Status = status;
    }

    public static class Parameters implements Serializable{
        private String AgreementText;
        private long DelayTime;
        private String FirstKeyWord;
        private String HeadNumber;
        private String RegistrationType;
        private String SecondKeyWord;
        private String UID;

        public Parameters(String agreementText, long delayTime, String firstKeyWord, String headNumber, String registrationType, String secondKeyWord) {
            AgreementText = agreementText;
            DelayTime = delayTime;
            FirstKeyWord = firstKeyWord;
            HeadNumber = headNumber;
            RegistrationType = registrationType;
            SecondKeyWord = secondKeyWord;
        }

        public String getAgreementText() {
            return AgreementText;
        }

        public void setAgreementText(String agreementText) {
            AgreementText = agreementText;
        }

        public long getDelayTime() {
            return DelayTime;
        }

        public void setDelayTime(long delayTime) {
            DelayTime = delayTime;
        }

        public String getFirstKeyWord() {
            return FirstKeyWord;
        }

        public void setFirstKeyWord(String firstKeyWord) {
            FirstKeyWord = firstKeyWord;
        }

        public String getHeadNumber() {
            return HeadNumber;
        }

        public void setHeadNumber(String headNumber) {
            HeadNumber = headNumber;
        }

        public String getRegistrationType() {
            return RegistrationType;
        }

        public void setRegistrationType(String registrationType) {
            RegistrationType = registrationType;
        }

        public String getSecondKeyWord() {
            return SecondKeyWord;
        }

        public void setSecondKeyWord(String secondKeyWord) {
            SecondKeyWord = secondKeyWord;
        }

        public String getUID() {
            return UID;
        }

        public void setUID(String UID) {
            this.UID = UID;
        }
    }
}
