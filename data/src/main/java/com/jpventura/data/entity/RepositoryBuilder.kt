package com.jpventura.data.entity

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

    fun build(): Repository = Repository(
        description = description ?: "",
        forks = requireNotNull(forks) { "Missing forks "},
        id = requireNotNull(id) { "Missing id"},
        key = requireNotNull(fullName) { "Missing key"},
        language = requireNotNull(language) { "Missing language"},
        name = requireNotNull(name) { "Missing name"},
        owner = requireNotNull(owner) { "Missing owner"},
        stargazersCount = requireNotNull(stargazersCount) { "Missing stargazers" }
    )
}
