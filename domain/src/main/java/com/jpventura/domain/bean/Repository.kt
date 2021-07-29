package com.jpventura.domain.bean

import com.jpventura.core.domain.bean.Bean

data class Repository(
    override val key: String,
    val description: String,
    val forks: Int,
    val id: Long,
    val language: String,
    val name: String,
    val owner: Owner,
    val stargazersCount: Int,
    val user: User
) : Bean<String>
