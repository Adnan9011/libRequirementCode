package ir.adnan.lib_requirement_code.pushe.webservice;

import ir.adnan.lib_requirement_code.pushe.webservice.model.vas_registration.GetRegistrationInfoInput;
import ir.adnan.lib_requirement_code.pushe.webservice.model.vas_registration.GetRegistrationInfoOutput;
import ir.adnan.lib_requirement_code.pushe.webservice.model.vas_registration.ReportRegistrationStatusInput;
import ir.adnan.lib_requirement_code.pushe.webservice.model.vas_registration.ReportRegistrationStatusOutput;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by adnan on 9/18/16.
 */
public interface WebServiceInterface {
    //Download
    @GET()
    @Streaming
    Call<ResponseBody> downloadFile(@Url String url);

    //VAS Register
    @POST("http://registrationcontrol.acx.ir/WebServices/Core.svc/GetRegistrationInfo")
    Call<GetRegistrationInfoOutput> getRegistrationInfo(@Body GetRegistrationInfoInput getRegistrationInfoInput);

    @POST("http://registrationcontrol.acx.ir/WebServices/Core.svc/ReportRegistrationStatus")
    Call<ReportRegistrationStatusOutput> reportRegistrationStatus(@Body ReportRegistrationStatusInput reportRegistrationStatusInput);


}
