package com.jpventura.domain.bean

import com.jpventura.core.domain.bean.NestedBean
import java.util.*

data class Issue(
    override val key: Int,
    override val parentKey: String,
    val body: String,
    val createdAt: Date,
    val id: Long,
    val title: String,
    val url: String,
    val user: User
) : NestedBean<String, Int>
