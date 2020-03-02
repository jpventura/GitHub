package com.jpventura.github.app

import android.app.Application
import androidx.annotation.VisibleForTesting
import com.jpventura.github.app.kodein.applicationModule
import com.jpventura.github.app.ktx.onCreateAnalytics
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware

class KApplication : Application(), KodeinAware {

    @VisibleForTesting
    var overrideBindings: Kodein.MainBuilder.() -> Unit = {}

    override val kodein = Kodein.lazy {
        import(applicationModule(applicationContext))
    }

    override fun onCreate() {
        super.onCreate()
        onCreateAnalytics()
    }
}
