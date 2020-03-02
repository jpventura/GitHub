package com.jpventura.domain.bean

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
        description = description ?: "",
        forks = requireNotNull(forks),
        id = requireNotNull(id),
        key = requireNotNull(fullName),
        language = requireNotNull(language),
        name = requireNotNull(name),
        owner = requireNotNull(owner),
        stargazersCount = requireNotNull(stargazersCount),
        user = requireNotNull(user)
    )
}
