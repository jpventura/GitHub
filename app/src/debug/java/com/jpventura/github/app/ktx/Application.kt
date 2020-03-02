package com.jpventura.github.app.ktx

import android.app.Application
import com.facebook.stetho.Stetho
import com.jpventura.github.core.CrashReportingTree
import timber.log.Timber

fun <T : Application> T.onCreateAnalytics() {
    Stetho.initializeWithDefaults(this)
    Timber.plant(CrashReportingTree())
}
