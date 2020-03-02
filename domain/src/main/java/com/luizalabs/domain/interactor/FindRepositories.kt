package com.luizalabs.domain.interactor

import com.luizalabs.domain.UseCases
import com.luizalabs.domain.UseCases.FindRepositories.Param
import com.luizalabs.domain.bean.Repository
import com.luizalabs.domain.model.RepositoryModel
import io.reactivex.Observable

class FindRepositories(
    val model: RepositoryModel
) : UseCases.FindRepositories {

    override fun invoke(parameter: Param): Observable<List<Repository>> {
        return Observable.error<List<Repository>>(NotImplementedError())
    }

}
