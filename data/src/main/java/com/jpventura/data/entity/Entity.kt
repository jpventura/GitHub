package com.jpventura.data.entity

import com.jpventura.core.domain.bean.Bean
import java.util.*

interface Entity<K> : Bean<K> {
    override val key: K
    val createdAt: Date
    val updatedAt: Date
}
