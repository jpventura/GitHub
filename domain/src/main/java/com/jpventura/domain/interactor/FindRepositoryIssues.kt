package com.jpventura.domain.interactor

import com.jpventura.domain.UseCase
import com.jpventura.domain.bean.Issue
import com.jpventura.domain.VersionControlModel
import io.reactivex.Observable

class FindRepositoryIssues constructor() : UseCase.FindRepositoryIssues {
    lateinit var issues: VersionControlModel.IssueModel
    lateinit var repositories: VersionControlModel.RepositoryModel

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
