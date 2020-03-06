package com.luizalabs.domain.bean

import com.google.gson.annotations.SerializedName

data class Repository(
    val description: String,
    val forks: Int,
    val fullName: String,
    val id: Long,
    val language: String,
    val name: String,
    val owner: Owner,

    @SerializedName("stargazers_count")
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
