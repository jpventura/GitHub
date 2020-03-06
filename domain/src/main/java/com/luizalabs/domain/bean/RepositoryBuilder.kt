package com.luizalabs.domain.bean

import com.luizalabs.core.domain.ktx.requireNotNullOrBlank
import com.luizalabs.core.domain.ktx.requirePositive

fun repository(block: RepositoryBuilder.() -> Unit): Repository = RepositoryBuilder()
    .apply(block)
    .build()

class RepositoryBuilder {
    var description: String? = null
    var forks: Int? = null
    var fullName: String? = null
    var id: Long? = null
    var language: String? = null
    var name: String? = null
    var owner: Owner? = null
    var stargazersCount: Int? = null
    var user: User? = null

    fun build(): Repository = Repository(
        description = requireNotNullOrBlank(description) { "Invalid description " },
        forks = requirePositive(forks) { "Invalid fork " },
        fullName = requireNotNullOrBlank(fullName) { "Invalid full name " },
        id = requirePositive(id) { "Invalid id " },
        language = language ?: "",
        name = requireNotNullOrBlank(name) { "Invalid name " },
        owner = requireNotNull(owner) { "Invalid owner " },
        stargazersCount = requirePositive(stargazersCount) { "Invalid stars " },
        user = user
    )
}
