package ir.adnan.lib_requirement_code.main

import android.app.Application
import android.content.Context

import com.crashlytics.android.Crashlytics

import io.fabric.sdk.android.Fabric


/**
 * Created by Adnan on 7/2/2017.
 */

open class LibraryApplication : Application() {

    companion object {

        var context: Context? = null
            @JvmStatic get() = this.context
    }

    override fun onCreate() {
        super.onCreate()
        context = this

//        launchDaggerRetrofit()
    }

    protected fun launchFabricCrashlytics(context: Context, debuggable: Boolean) {

        val fabric = Fabric.Builder(context)
                .kits(Crashlytics())
                .debuggable(debuggable)
                .build()
        Fabric.with(fabric)
    }
}
