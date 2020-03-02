package com.jpventura.github.main.vm

import com.jpventura.domain.UseCases
import com.jpventura.github.core.ui.Failure
import com.jpventura.github.core.ui.ObservableViewModel
import com.jpventura.github.core.ui.Success
import javax.inject.Inject
import timber.log.Timber

class MainViewModel @Inject constructor() : ObservableViewModel() {

    @Inject
    lateinit var getRepositories: UseCases.FindTrendingRepositories

    fun getRepositoriesAsync(language: String) {
        val param = UseCases.FindTrendingRepositories.Param(language = "Kotlin")

        disposables.add(getRepositories(param)
            .subscribeOn(rxSchedulers.io)
            .observeOn(rxSchedulers.ui)
            .subscribe(
                {
                    state = Success(result = it)
                    Timber.w(state.toString())
                }, {
                    state = Failure(it)
                    Timber.e(it)
                }
            ))
    }
}
