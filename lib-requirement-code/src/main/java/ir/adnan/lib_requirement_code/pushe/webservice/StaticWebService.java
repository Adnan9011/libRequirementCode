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
            return new HandleWebservice("شما ثبت نام نیستید" ,HandleTypeResponseEnum.SignOut);
        else if("G00001".equalsIgnoreCase(code))
            return new HandleWebservice("خطای کنترل نشده رخ داده است . لطفا با پشتیبانی تماس حاصل فرمایید" ,HandleTypeResponseEnum.Main);
        //
        else if("GB0001".equalsIgnoreCase(code))
            return new HandleWebservice("فرمت وارد شده صحیح نیست" ,HandleTypeResponseEnum.Error);
        else if("GB0002".equalsIgnoreCase(code))
            return new HandleWebservice("فقط از شماره های همراه اول پشتیبانی خواهیم کرد" ,HandleTypeResponseEnum.Error);
        else if("GB0004".equalsIgnoreCase(code))
            return new HandleWebservice("شما از تعداد مجاز درخواست برای ارسال کد فعال سازی رد شده اید" ,HandleTypeResponseEnum.Error);
        else if("GB0003".equalsIgnoreCase(code))
            return new HandleWebservice("اطلاعات کد فعال سازی وارد شده صحیح نیست" ,HandleTypeResponseEnum.Error);
        else if("GB0005".equalsIgnoreCase(code))
            return new HandleWebservice("شما از تعداد مجاز درخواست برای ارسال کد فعال سازی رد شده اید" ,HandleTypeResponseEnum.Error);
        else if("GB0007".equalsIgnoreCase(code))
            return new HandleWebservice("ورودی خالی است" ,HandleTypeResponseEnum.Error);
        else if("GB0014".equalsIgnoreCase(code))
            return new HandleWebservice("شما حق دسترسی به این بخش را ندارید" ,HandleTypeResponseEnum.Error);
        //
        else
            return new HandleWebservice("خطایی رخ داده است" ,HandleTypeResponseEnum.Error);
    }
}
