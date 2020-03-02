package com.jpventura.domain

import com.jpventura.core.domain.interactor.UseCaseWithParameter
import com.jpventura.domain.bean.Repository
import com.jpventura.domain.bean.Issue
import com.jpventura.domain.bean.Pull

interface UseCases {

    interface FindRepositories : UseCaseWithParameter<FindRepositories.Param, List<Repository>> {

        data class Param(
            val language: String,
            val order: String,
            val sort: String,
            val min: Int = 20
        )
    }

    interface FindRepositoryIssues : UseCaseWithParameter<String, List<Issue>>

    interface FindRepositoryPulls : UseCaseWithParameter<Repository, List<Pull>>

    interface FindTrendingRepositories :
        UseCaseWithParameter<FindTrendingRepositories.Param, List<Repository>> {

        data class Param(
            val language: String,
            val order: String = "desc",
            val sort: String = "stars",
            val min: Int = 20
        )
    }

}

fun UseCases.FindRepositories.Param.toMap() = mapOf(
    "language" to language,
    "min" to min,
    "order" to order,
    "sort" to sort
)

fun UseCases.FindTrendingRepositories.Param.toMap() = mapOf(
    "language" to language,
    "min" to min,
    "order" to order,
    "sort" to sort
)
