package com.jpventura.github.core.vm

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

data class RxSchedulers(
    val computation: Scheduler = Schedulers.computation(),
    val io: Scheduler = Schedulers.io(),
    val ui: Scheduler = AndroidSchedulers.mainThread()
)
