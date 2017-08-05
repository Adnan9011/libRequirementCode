package ir.adnan.lib_requirement_code.pushe.webservice;


/**
 * Created by adnan on 12/11/16.
 */
public class StaticWebService {
    /*
     * Check is Successful from WebService
     * 0 : Ok
     * 1 : Error
     * 2 : not Sign In
     */
    public static HandleWebservice response (Status status) {
        if("G00000".equalsIgnoreCase(status.getCode()))
            return new HandleWebservice(status.getDescription() ,HandleTypeResponseEnum.OK);
        else if("G00002".equalsIgnoreCase(status.getCode()))
            return new HandleWebservice(status.getDescription()  ,HandleTypeResponseEnum.SignOut);
        else if("G00001".equalsIgnoreCase(status.getCode()))
            return new HandleWebservice(status.getDescription()  ,HandleTypeResponseEnum.Main);
        //
        else if("GB0001".equalsIgnoreCase(status.getCode()))
            return new HandleWebservice(status.getDescription()  ,HandleTypeResponseEnum.Error);
        else if("GB0002".equalsIgnoreCase(status.getCode()))
            return new HandleWebservice(status.getDescription()  ,HandleTypeResponseEnum.Error);
        else if("GB0004".equalsIgnoreCase(status.getCode()))
            return new HandleWebservice(status.getDescription()  ,HandleTypeResponseEnum.Error);
        else if("GB0003".equalsIgnoreCase(status.getCode()))
            return new HandleWebservice(status.getDescription()  ,HandleTypeResponseEnum.Error);
        else if("GB0005".equalsIgnoreCase(status.getCode()))
            return new HandleWebservice(status.getDescription() ,HandleTypeResponseEnum.Error);
        else if("GB0007".equalsIgnoreCase(status.getCode()))
            return new HandleWebservice(status.getDescription()  ,HandleTypeResponseEnum.Error);
        else if("GB0014".equalsIgnoreCase(status.getCode()))
            return new HandleWebservice(status.getDescription()  ,HandleTypeResponseEnum.Error);
        //
        else
            return new HandleWebservice(status.getDescription()  ,HandleTypeResponseEnum.Error);
    }
}
