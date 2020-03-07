package com.luizalabs.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.luizalabs.core.domain.bean.Bean

@Entity(tableName = "issues")
data class Issue(
    @PrimaryKey
    override val id: Int,
    val path: String
) : Bean<Int>
