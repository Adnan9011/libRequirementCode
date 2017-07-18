
-optimizationpasses 5

#When not preverifing in a case-insensitive filing system, such as Windows. Because this tool unpacks your processed jars, you should then use:
-dontusemixedcaseclassnames

#Specifies not to ignore non-public library classes. As of version 4.5, this is the default setting
-dontskipnonpubliclibraryclasses

#Preverification is irrelevant for the dex compiler and the Dalvik VM, so we can switch it off with the -dontpreverify option.
-dontpreverify

#Specifies to write out some more information during processing. If the program terminates with an exception, this option will print out the entire stack trace, instead of just the exception message.
-verbose

#The -optimizations option disables some arithmetic simplifications that Dalvik 1.0 and 1.5 can't handle. Note that the Dalvik VM also can't handle aggressive overloading (of static fields).
#To understand or change this check http://proguard.sourceforge.net/index.html#/manual/optimizations.html
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*

   -keepattributes Signature
   -keep class sun.misc.Unsafe { *; }

   -dontwarn com.squareup.okhttp.**

   -dontwarn okio.**
   -dontwarn retrofit2.Platform$Java8


   ##---------------Begin: proguard configuration for Gson  ----------
   # Gson uses generic type information stored in a class file when working with fields. Proguard
   # removes such information by default, so configure it to keep all of it.
   -keepattributes Signature

   # For using GSON @Expose annotation
   -keepattributes *Annotation*

   # Gson specific classes
   -keep class sun.misc.Unsafe { *; }
   #-keep class com.google.gson.stream.** { *; }

   # Application classes that will be serialized/deserialized over Gson
   -keep class com.google.gson.examples.android.model.** { *; }

   # Prevent proguard from stripping interface information from TypeAdapterFactory,
   # JsonSerializer, JsonDeserializer instances (so they can be used in @JsonAdapter)
   -keep class * implements com.google.gson.TypeAdapterFactory
   -keep class * implements com.google.gson.JsonSerializer
   -keep class * implements com.google.gson.JsonDeserializer

   #Adnan
   -keep public class ir.adnan.lib_requirement_code.pushe.** {*;}

   -keep class com.daimajia.** { *; }
   -keep class com.squareup.okhttp.** { *; }
   -keep class com.squareup.picasso.** { *; }
   -keep class com.google.android.gms.** { *; }

   -renamesourcefileattribute SourceFile
   -keepattributes SourceFile,LineNumberTable

-keepattributes *Annotation*
-keepclassmembers class ** {
    @org.greenrobot.eventbus.Subscribe <methods>;
}
-keep enum org.greenrobot.eventbus.ThreadMode { *; }

# Only required if you use AsyncExecutor
-keepclassmembers class * extends org.greenrobot.eventbus.util.ThrowableFailureEvent {
    <init>(java.lang.Throwable);
}

