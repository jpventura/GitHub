package com.jpventura.github.issues.vm

import com.jpventura.domain.UseCases
import com.jpventura.github.core.ui.Failure
import com.jpventura.github.core.ui.Loading
import com.jpventura.github.core.ui.ObservableViewModel
import com.jpventura.github.core.ui.Success
import com.jpventura.github.issues.IssuesContract
import javax.inject.Inject
import timber.log.Timber

class IssuesViewModel @Inject constructor() : ObservableViewModel(), IssuesContract.ViewModel {

    @Inject lateinit var getIssues: UseCases.FindRepositoryIssues

    constructor(interactor: UseCases.FindRepositoryIssues) : this() {
        this.getIssues = interactor
    }

    fun getIssuesAsync(repositoryId: String) {
        disposables.add(getIssues(repositoryId)
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
