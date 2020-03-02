package com.jpventura.domain.bean

import com.jpventura.core.domain.bean.Bean

data class Owner(
    override val key: String,
    val avatarUrl: String,
    val id: Long,
    val name: String? = "",
    val reposUrl: String,
    val userUrl: String?
) : Bean<String>
