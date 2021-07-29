package com.jpventura.domain

import com.jpventura.core.domain.interactor.UseCaseWithParameter
import com.jpventura.domain.bean.Issue
import com.jpventura.domain.bean.PullRequest
import com.jpventura.domain.bean.Repository

interface UseCase {

    interface FindRepositories : UseCaseWithParameter<FindRepositories.Param, List<Repository>> {

        data class Param(
            val language: String,
            val order: String,
            val sort: String,
            val min: Int = 20
        )
    }

    interface FindRepositoryIssues : UseCaseWithParameter<String, List<Issue>>

    interface FindRepositoryPullRequests : UseCaseWithParameter<Repository, List<PullRequest>>

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

fun UseCase.FindRepositories.Param.toMap() = mapOf(
    "language" to language,
    "min" to min,
    "order" to order,
    "sort" to sort
)

fun UseCase.FindTrendingRepositories.Param.toMap() = mapOf(
    "language" to language,
    "min" to min,
    "order" to order,
    "sort" to sort
)
