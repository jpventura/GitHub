package com.jpventura.github.core.data.cloud

import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber

class TimberInterceptorLogger(val tag: String) : HttpLoggingInterceptor.Logger {
    override fun log(message: String) {
        Timber.tag(tag).d(message)
    }
}
