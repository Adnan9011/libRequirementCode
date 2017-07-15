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
    public static HandleWebservice response (String code) {
        if("G00000".equalsIgnoreCase(code))
            return new HandleWebservice("" ,HandleTypeResponseEnum.OK);
        else if("G00002".equalsIgnoreCase(code))
            return new HandleWebservice("" ,HandleTypeResponseEnum.SignOut);
        else if("G00001".equalsIgnoreCase(code))
            return new HandleWebservice("" ,HandleTypeResponseEnum.Main);
        //
        else if("GB0001".equalsIgnoreCase(code))
            return new HandleWebservice("" ,HandleTypeResponseEnum.Error);
        else if("GB0002".equalsIgnoreCase(code))
            return new HandleWebservice("" ,HandleTypeResponseEnum.Error);
        else if("GB0004".equalsIgnoreCase(code))
            return new HandleWebservice("" ,HandleTypeResponseEnum.Error);
        else if("GB0003".equalsIgnoreCase(code))
            return new HandleWebservice("" ,HandleTypeResponseEnum.Error);
        else if("GB0005".equalsIgnoreCase(code))
            return new HandleWebservice("" ,HandleTypeResponseEnum.Error);
        else if("GB0007".equalsIgnoreCase(code))
            return new HandleWebservice("" ,HandleTypeResponseEnum.Error);
        else if("GB0014".equalsIgnoreCase(code))
            return new HandleWebservice("" ,HandleTypeResponseEnum.Error);
        //
        else
            return new HandleWebservice("" ,HandleTypeResponseEnum.Error);
    }
}
