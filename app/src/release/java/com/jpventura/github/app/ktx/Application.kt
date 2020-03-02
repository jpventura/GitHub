package com.jpventura.github.app.ktx

import android.app.Application
import com.jpventura.github.core.CrashReportingTree
import timber.log.Timber

fun <T : Application> T.onCreateAnalytics() {
    Timber.plant(CrashReportingTree())
}
