package com.jpventura.github.main.ktx

import android.app.Activity
import android.app.SearchManager
import android.content.Context

fun Activity.searchManager(): SearchManager {
    return getSystemService(Context.SEARCH_SERVICE) as SearchManager
}
