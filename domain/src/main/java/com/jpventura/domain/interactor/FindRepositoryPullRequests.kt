package com.jpventura.domain.interactor

import com.jpventura.domain.UseCase
import com.jpventura.domain.bean.PullRequest
import com.jpventura.domain.bean.Repository
import com.jpventura.domain.VersionControlModel
import io.reactivex.Observable

class FindRepositoryPullRequests constructor() : UseCase.FindRepositoryPullRequests {
    lateinit var pulls: VersionControlModel.PullModel

    constructor(
        pulls: VersionControlModel.PullModel
    ) : this() {
        this.pulls = pulls
    }

    override fun invoke(param: Repository): Observable<List<PullRequest>> {
        return pulls.find(param)
    }
}
