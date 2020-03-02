package com.jpventura.domain.interactor

import com.jpventura.domain.UseCases
import com.jpventura.domain.bean.Repository
import com.jpventura.domain.model.VersionControlModel
import com.jpventura.domain.toMap
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import javax.inject.Inject

class FindTrendingRepositories @Inject constructor() : UseCases.FindTrendingRepositories {

    @Inject lateinit var repositories: VersionControlModel.RepositoryModel

    private val subject: Subject<String> = PublishSubject.create()

    constructor(
        repositories: VersionControlModel.RepositoryModel
    ) : this() {
        this.repositories = repositories
    }

    override fun invoke(param: UseCases.FindTrendingRepositories.Param): Observable<List<Repository>> {
        return repositories.find(param.toMap())
    }

}
