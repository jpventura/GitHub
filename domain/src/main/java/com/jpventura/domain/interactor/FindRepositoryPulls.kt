package com.jpventura.domain.interactor

import com.jpventura.domain.UseCases
import com.jpventura.domain.bean.Pull
import com.jpventura.domain.bean.Repository
import com.jpventura.domain.model.VersionControlModel
import io.reactivex.Observable
import javax.inject.Inject

class FindRepositoryPulls @Inject constructor() : UseCases.FindRepositoryPulls {

    @Inject lateinit var pulls: VersionControlModel.PullModel

    constructor(
        pulls: VersionControlModel.PullModel
    ) : this() {
        this.pulls = pulls
    }

    override fun invoke(param: Repository): Observable<List<Pull>> {
        return pulls.find(param)
    }

}
