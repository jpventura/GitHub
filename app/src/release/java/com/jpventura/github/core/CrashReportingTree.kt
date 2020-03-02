package com.jpventura.github.core

import android.annotation.SuppressLint
import android.util.Log
import java.util.regex.Pattern
import timber.log.Timber

class CrashReportingTree : Timber.Tree() {

    @SuppressLint("LogNotTimber")
    override fun log(priority: Int, tag: String?, message: String, throwable: Throwable?) {
        try {
            crashlytics(priority, tag, message, throwable)
        } catch (e: IllegalStateException) {
            Log.e(TAG, e.message, e)
        }
    }

    private fun crashlytics(
        priority: Int,
        tag: String?,
        message: String,
        throwable: Throwable?
    ) {
        // TODO
    }

    private fun createStackElementTag(element: StackTraceElement): String {
        return String.format(
            "%s [%s:%s]",
            getElementTag(element),
            element.methodName,
            element.lineNumber
        )
    }

    private fun getElementTag(element: StackTraceElement): String {
        var tag = element.className
        val matcher = ANONYMOUS_CLASS.matcher(tag)
        if (matcher.find()) {
            tag = matcher.replaceAll("")
        }

        tag = tag.substring(tag.lastIndexOf(char = '.') + 1)
        return if (tag.length > MAX_LENGTH_LINE) tag.substring(0, MAX_LENGTH_LINE) else tag
    }

    companion object {
        private const val MAX_LENGTH_STACKTRACE = 7
        private const val MAX_LENGTH_LINE = 23
        private const val TAG = "CrashReportTree"

        private val ANONYMOUS_CLASS = Pattern.compile("(\\$\\d+)+$")
    }
}
