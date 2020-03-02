package com.jpventura.github.repositories.vm

import com.jpventura.domain.UseCases
import com.jpventura.github.core.ui.Failure
import com.jpventura.github.core.ui.Loading
import com.jpventura.github.core.ui.ObservableViewModel
import com.jpventura.github.core.ui.Success
import com.jpventura.github.repositories.RepositoriesContract
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import javax.inject.Inject
import timber.log.Timber

class RepositoriesViewModel @Inject constructor() : ObservableViewModel(),
    RepositoriesContract.ViewModel {

    @Inject
    lateinit var getRepositories: UseCases.FindTrendingRepositories

    private val subject: Subject<String> = PublishSubject.create()

    constructor(interactor: UseCases.FindTrendingRepositories) : this() {
        this.getRepositories = interactor
    }

    fun getRepositoriesAsync(language: String?, min: Int) {
        if (language.isNullOrBlank()) return

        subject.onNext(language)

        val param = UseCases.FindTrendingRepositories.Param(
            language = language,
            min = min
        )

        if (language.isNotBlank())
        disposables.add(getRepositories(param)
            .doOnSubscribe {
                state = Loading
            }
            .subscribeOn(rxSchedulers.io)
            .observeOn(rxSchedulers.ui)
            .subscribe(
                {
                    state = Success(result = it)
                }, {
                    state = Failure(it)
                    Timber.e(it)
                }
            ))
    }
}
