package com.jpventura.domain.bean

import com.jpventura.core.domain.bean.NestedBean
import java.util.Date

data class PullRequest(
    override val key: Int,
    override val parentKey: String,
    val body: String,
    val createdAt: Date,
    val id: Long,
    val number: Int,
    val state: String,
    val title: String,
    val user: User
) : NestedBean<String, Int>
