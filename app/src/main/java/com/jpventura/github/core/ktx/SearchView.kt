package com.jpventura.github.core.ktx

import androidx.appcompat.widget.SearchView
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable

fun SearchView.toObservable(): Observable<String> {

    val disposable = CompositeDisposable()

    this.setOnCloseListener {
        if (!disposable.isDisposed) disposable.dispose()
        true
    }

    return Observable.create<String> { emitter ->
        setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    emitter.onNext(it)
                }
                onActionViewCollapsed()
                return true
            }
        })
    }.doOnSubscribe { disposable.addAll(it) }
}
