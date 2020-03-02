package com.jpventura.github.core

import timber.log.Timber

class CrashReportingTree : Timber.DebugTree() {

    override fun createStackElementTag(element: StackTraceElement): String? {
        return String.format(
            "%s#%s:%s",
            super.createStackElementTag(element),
            element.methodName,
            element.lineNumber
        )
    }
}
