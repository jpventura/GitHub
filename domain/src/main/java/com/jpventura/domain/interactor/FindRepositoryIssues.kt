package com.jpventura.domain.interactor

import com.jpventura.domain.UseCases
import com.jpventura.domain.bean.Issue
import com.jpventura.domain.model.VersionControlModel
import io.reactivex.Observable
import javax.inject.Inject

class FindRepositoryIssues @Inject constructor() : UseCases.FindRepositoryIssues {

    @Inject lateinit var issues: VersionControlModel.IssueModel

    @Inject lateinit var repositories: VersionControlModel.RepositoryModel

    constructor(
        issues: VersionControlModel.IssueModel,
        repositories: VersionControlModel.RepositoryModel
    ) : this() {
        this.issues = issues
        this.repositories = repositories
    }

    override fun invoke(param: String): Observable<List<Issue>> =
        repositories.findOne(param)
            .flatMapObservable {
                issues.find(it)
            }

}
