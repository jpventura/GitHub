package com.luizalabs.data.ktx

import com.luizalabs.data.entity.Repository
import com.luizalabs.domain.bean.User

fun repository(block: RepositoryBuilder.() -> Unit): Repository = RepositoryBuilder()
    .apply(block)
    .build()

fun Repository.asBean(): Repository =
    repository {
        description = this@asBean.description
        forks = this@asBean.forks
        fullName = this@asBean.fullName
        id = this@asBean.id
        language = this@asBean.language
        name = this@asBean.name
        stargazersCount = this@asBean.stargazersCount
    }

fun Repository.asEntity(user: User): com.luizalabs.domain.bean.Repository =
    com.luizalabs.domain.bean.repository {
        description = this@asEntity.description
        forks = this@asEntity.forks
        fullName = this@asEntity.fullName
        id = this@asEntity.id
        language = this@asEntity.language
        name = this@asEntity.name
        stargazersCount = this@asEntity.stargazersCount
        user
    }

class RepositoryBuilder {
    var description: String? = null
    var forks: Int? = null
    var fullName: String? = null
    var id: Long? = null
    var language: String? = null
    var name: String? = null
    var stargazersCount: Int? = null

    fun build(): Repository =
        Repository(
            description = requireNotNull(description),
            forks = requireNotNull(forks),
            fullName = requireNotNull(fullName),
            id = requireNotNull(id),
            language = requireNotNull(language),
            name = requireNotNull(name),
            stargazersCount = requireNotNull(stargazersCount)
        )
}
