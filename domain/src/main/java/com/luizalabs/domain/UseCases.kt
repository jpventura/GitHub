package com.luizalabs.domain

import com.luizalabs.core.domain.interactor.UseCaseWithParameter
import com.luizalabs.domain.bean.Repository

interface UseCases {

    interface FindRepositories : UseCaseWithParameter<FindRepositories.Param, List<Repository>> {

        data class Param(val name: String)

    }

    interface FindTrendingRepositories : FindRepositories

}
