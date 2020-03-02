package com.jpventura.domain.bean

import com.jpventura.core.domain.bean.Bean

data class User(
    override val key: String,
    val avatarUrl: String,
    val id: Long,
    val name: String
) : Bean<String>
