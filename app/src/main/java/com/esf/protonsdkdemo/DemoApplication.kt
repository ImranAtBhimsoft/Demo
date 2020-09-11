package com.esf.protonsdkdemo

import android.app.Application
import com.metallicus.protonsdk.Proton
import timber.log.Timber

/**
 * Created by m.imran
 * Senior Software Engineer at
 * BhimSoft on 08/09/2020.
 */
class DemoApplication :Application() {
    override fun onCreate() {
        super.onCreate()
        Proton.getInstance(this).initialize(BuildConfig.BASE_URL)
        if(BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }
    }
}