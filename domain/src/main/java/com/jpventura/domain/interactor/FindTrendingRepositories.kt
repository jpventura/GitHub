package com.jpventura.domain.interactor

import com.jpventura.domain.UseCase
import com.jpventura.domain.bean.Repository
import com.jpventura.domain.VersionControlModel.RepositoryModel
import com.jpventura.domain.toMap
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject

class FindTrendingRepositories constructor() : UseCase.FindTrendingRepositories {
    lateinit var repositories: RepositoryModel

    private val subject: Subject<String> = PublishSubject.create()

    constructor(
        repositories: RepositoryModel
    ) : this() {
        this.repositories = repositories
    }

    override fun invoke(param: UseCase.FindTrendingRepositories.Param): Observable<List<Repository>> {
        return repositories.find(param.toMap())
    }
}
