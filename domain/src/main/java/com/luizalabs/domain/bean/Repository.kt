package com.luizalabs.domain.bean

data class Repository(
    val description: String,
    val forks: Int,
    val fullName: String,
    val id: Long,
    val language: String,
    val name: String,
    val owner: Owner,
    val stargazersCount: Int,
    val user: User? = null
)

private fun Repository.check(repository: Repository) {
    check(repository.description.isNotBlank())
    check(repository.forks > 0)
    check(repository.fullName.isNotBlank())
    check(repository.id > 0)
    check(repository.name.isNotBlank())
    check(repository.stargazersCount > 0)
}
